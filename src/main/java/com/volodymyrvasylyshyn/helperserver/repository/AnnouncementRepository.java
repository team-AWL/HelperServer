package com.volodymyrvasylyshyn.helperserver.repository;


import com.volodymyrvasylyshyn.helperserver.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByOrderBySeemLastTimeAsc();
    List<Announcement> findAllByOrderBySeemLastTimeDesc();
    List<Announcement> findAllByLocation(String location);

    @Query("select announcement from Announcement announcement where announcement.seemLastTime between :fromDate and :toDate")
    List<Announcement> getAnnouncementInDateRange(@Param("fromDate") LocalDate dateBefore, @Param("toDate")LocalDate dateAfter);
    @Query("SELECT a FROM Announcement a WHERE " +
            "LOWER(a.description) LIKE LOWER(CONCAT('%',:keyword, '%')) OR " +
            "LOWER(a.fullNameOfPerson) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Announcement> findAnnouncementByKeywords(@Param("keyword") String keyword);

}
