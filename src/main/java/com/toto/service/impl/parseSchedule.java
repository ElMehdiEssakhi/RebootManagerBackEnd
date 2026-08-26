package com.toto.service.impl;



import com.toto.entity.Machine;
import com.toto.entity.RebootLog;
import com.toto.repository.MachineRepository;
import com.toto.repository.RebootLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class parseSchedule {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private RebootLogRepository rebRepository;
    @Autowired
    private MachineRepository machineRepository;
    @Autowired
    private machineService machineService;
    @Autowired
    private mailingService mailingService;

    @Value("${log.drop.path}")
    private String logDropPath;

    @Scheduled(cron = "0 0 7 * * ?")//@Scheduled(cron = "0 */2 * * * ?")
    public void parseFolder(){
        Path logFolder = Paths.get(logDropPath);
        try(Stream<Path> files = Files.walk(logFolder)){
            files.filter(Files::isRegularFile).forEach(path -> {
                String fileName = path.getFileName().toString();
                if (fileName.matches("Reboot_logs.*\\.txt")){
                    parseFileReb(path);
                }else if (fileName.matches("Uptime_memoryCheck_.*\\.txt")){
                    parseFileUp(path);
                }else {
                    System.out.println("Unknown file type: " + fileName);
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void parseFileReb(Path filePath) {
        System.out.println("Parsing reboot log...");
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                try {
                    String[] words = line.trim().split("\\s+");
                    if (words.length >= 13 && "A".equals(words[3])) {
                        RebootLog reboot = new RebootLog();
                        // Parse date and time
                        String scriptExTime = words[1] + " " + words[2];
                        LocalDateTime postOrAutoTime = LocalDateTime.parse(scriptExTime, formatter);
                        Machine machine = machineRepository.findByName(words[0].replace(":", ""));
                        if (machine == null) {
                            System.err.println("Machine not found for name: " + words[0]);
                            return;
                        }
                        RebootLog pendingFromYesterday = rebRepository.wasPendingYesterday(machine, LocalDate.now().minusDays(1));
                        if ( "but".equals(words[8])){
                            if(pendingFromYesterday != null){
                                pendingFromYesterday.setStatus("alert");
                                rebRepository.save(pendingFromYesterday);
                                machineService.increaseAlertCount(machine);
                                //send mail
                                String siteCode = words[0].substring(0, 3).toUpperCase();
                                String recipientEmail = getEmailForSite(siteCode);
                                mailingService.sendSimpleEmail(recipientEmail,"Alert a pending reboot", machine.getName()+" need intervention");
                                return;
                            }
                            reboot.setMachine(machine);
                            reboot.setStatus("pending");
                            reboot.setRebootPostponedAt(postOrAutoTime);
                            reboot.setSite(words[0].substring(0, 3));
                            rebRepository.save(reboot);
                        } else if ("No".equals(words[8])){
                            if(pendingFromYesterday==null){
                                return;
                            }
                            pendingFromYesterday.setStatus("auto");
                            pendingFromYesterday.setRebootedAt(postOrAutoTime);
                            machineService.increaseAutoCount(machine);
                            rebRepository.save(pendingFromYesterday);
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Failed to process line: " + line);
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to read log file.");
            e.printStackTrace();
            return;
        }
        try {
            Files.deleteIfExists(filePath);
            System.out.println("deleted parsed file");
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + filePath);
            e.printStackTrace();
        }
    }
    public void parseFileUp(Path filePath) {
        System.out.println("Parsing reboot log...");
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                try {
                    String[] words = line.trim().split("\\s+");
                    if (words.length >= 23 && "A".equals(words[19])) {
                        RebootLog reboot = new RebootLog();
                        Machine machine = machineRepository.findByName(words[0].replace(":", ""));
                        if (machine == null) {
                            System.err.println("Machine not found for name: " + words[0]);
                            return;
                        }
                        reboot.setMachine(machine);
                        reboot.setSite(words[0].substring(0, 3));
                        // Parse date and time
                        String scriptExTime = words[1] + " " + words[2];
                        LocalDateTime dateTime = LocalDateTime.parse(scriptExTime, formatter);
                        reboot.setRebootPostponedAt(dateTime);
                        reboot.setStatus("pending");
                        rebRepository.save(reboot);
                    }
                } catch (Exception e) {
                    System.err.println("Failed to process line: " + line);
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.err.println("Failed to read log file.");
            e.printStackTrace();
            return;
        }
        try {
            Files.deleteIfExists(filePath);
            System.out.println("deleted parsed file");
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + filePath);
            e.printStackTrace();
        }
    }

    private static final Map<String, String> siteToEmailMap = Map.ofEntries(
        //set emails here 
        Map.entry("zone", "email"),
    );

    private String getEmailForSite(String siteCode) {
        return siteToEmailMap.get(siteCode.toUpperCase());

    }



}
