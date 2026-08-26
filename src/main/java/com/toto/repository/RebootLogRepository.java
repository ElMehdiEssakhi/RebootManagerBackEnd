package com.toto.repository;
import com.toto.entity.Machine;
import com.toto.entity.RebootLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface RebootLogRepository extends JpaRepository<RebootLog, Long> {

    List<RebootLog> findBySite(String site);

    RebootLog getRebootLogById(Long id);

    List<RebootLog> findRebootLogsBySiteAndStatus(String site, String status);

    List<RebootLog> findRebootLogsByRebootPostponedAt(LocalDateTime rebootPostponedAt);

    List<RebootLog> findRebootLogsByRebootPostponedAtBetween(LocalDateTime rebootPostponedAtAfter, LocalDateTime rebootPostponedAtBefore);

    @Query("""
        SELECT r
        FROM RebootLog r
        WHERE r.machine = :machine
          AND r.status IN ('pending','alert')
          AND FUNCTION('DATE', r.rebootPostponedAt) = :yesterday
    """)
    RebootLog wasPendingYesterday(@Param("machine") Machine machine,@Param("yesterday") LocalDate yesterday);

    List<RebootLog> findRebootLogsBySiteAndStatusIn(String site, Collection<String> statuses);


    List<RebootLog> findByStatus(String status);
}


