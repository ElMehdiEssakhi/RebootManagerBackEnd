package com.toto.service.impl;

import com.toto.entity.Machine;
import com.toto.entity.RebootLog;
import com.toto.repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

@Service
public class fillMachines {
    @Autowired
    private MachineRepository machineRepository;
    //@Scheduled(fixedRate = 500000)
    public void parseFolder(){
        Path logFolder = Paths.get("");
        Path parsedDir = Paths.get("");
        try(Stream<Path> files = Files.walk(logFolder)){
            files.filter(Files::isRegularFile).forEach(path -> {parseFile(path,parsedDir);});
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void parseFile(Path filePath,Path parsedDir) {
        System.out.println("Parsing reboot log...");
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                try {
                    String[] words = line.trim().split("\\s+");
                    Machine machine = new Machine();
                    machine.setName(words[0].replace(":", ""));
                    machineRepository.save(machine);
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
            Path targetPath = parsedDir.resolve(filePath.getFileName());
            Files.move(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved parsed file to: " + targetPath);
        } catch (IOException e) {
            System.err.println("Failed to move file: " + filePath);
            e.printStackTrace();
        }
    }
}
