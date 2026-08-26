package com.toto.repository;

import com.toto.controller.RebByZonesResponse;
import com.toto.controller.alertsByZoneResponse;
import com.toto.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    Machine findByName(String name);

    @Query("SELECT SUM(m.techReboots) FROM Machine m")
    Long sumManualReboots();

    @Query("SELECT SUM(m.taskSchedulerReboots) FROM Machine m")
    Long sumAutomaticReboots();

    @Query(
            value = """
        SELECT 
            SUBSTRING(name, 1, 3) AS zone, 
            COALESCE(SUM(techReboots), 0) AS reboots
        FROM MACHINES
        GROUP BY zone
        """,
            nativeQuery = true
    )
    List<RebByZonesResponse> geManualRebootsByZone();

    @Query(
            value = """
        SELECT 
            SUBSTRING(name, 1, 3) AS zone, 
            COALESCE(SUM(taskSchedulerReboots), 0) AS reboots
        FROM MACHINES
        GROUP BY zone
        """,
            nativeQuery = true
    )
    List<RebByZonesResponse> getAutoRebootsByZone();

    @Query(
            value = """
        SELECT 
            SUBSTRING(name, 1, 3) AS zone, 
            COALESCE(SUM(alertCount), 0) AS alerts
        FROM MACHINES
        GROUP BY zone
        """,
            nativeQuery = true
    )
    List<alertsByZoneResponse> getAlertsByZone();


}
