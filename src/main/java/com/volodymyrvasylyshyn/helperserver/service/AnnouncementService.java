package com.volodymyrvasylyshyn.helperserver.service;


import com.volodymyrvasylyshyn.helperserver.exeptions.AnnouncementNotFoundException;
import com.volodymyrvasylyshyn.helperserver.mapper.AnnouncementMapper;
import com.volodymyrvasylyshyn.helperserver.model.Announcement;
import com.volodymyrvasylyshyn.helperserver.model.User;
import com.volodymyrvasylyshyn.helperserver.repository.AnnouncementRepository;
import com.volodymyrvasylyshyn.helperserver.repository.UserRepository;
import com.volodymyrvasylyshyn.helperserver.request.AnnouncementRequest;
import com.volodymyrvasylyshyn.helperserver.request.DateRangeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final AnnouncementMapper announcementMapper;
    private final UserService userService;


    public Announcement createAnnouncement(AnnouncementRequest announcementRequest, Principal principal) {
        User user = userService.getCurrentUser(principal);
        Announcement createdAnnouncement = announcementMapper.announcementRequestToAnnouncement(announcementRequest);
        createdAnnouncement.setAnnouncementCreator(user);
        user.getCreatedAnnouncements().add(createdAnnouncement);
        userRepository.save(user);
        return announcementRepository.save(createdAnnouncement);
    }

    public void createAnnouncementFromTelegram(AnnouncementRequest announcementRequest) {
        Announcement createdAnnouncement = announcementMapper.announcementRequestToAnnouncement(announcementRequest);
        announcementRepository.save(createdAnnouncement);
    }



    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();

    }

//    TODO: How to implement update for different fields

    public void updateAnnouncement(Long announcementId, AnnouncementRequest announcementRequest, Principal principal) {

    }
    public void deleteAnnouncement(Long announcementId) {
        Announcement deletedAnnouncement = announcementRepository.findById(announcementId).orElseThrow(()-> new AnnouncementNotFoundException("Announcement with id: "+ announcementId+ " not found" ));
        announcementRepository.delete(deletedAnnouncement);
    }

    public List<Announcement> findAnnouncementsSortedByDate(String order) {
        if (order.equals("desc")) {
            return announcementRepository.findAllByOrderBySeemLastTimeDesc();
        }
        return announcementRepository.findAllByOrderBySeemLastTimeAsc();
    }

    public List<Announcement> findAnnouncementsByLocation(String location) {
        return announcementRepository.findAllByLocation(location);
    }

    public List<Announcement> findAnnouncementsInDateRange(DateRangeRequest dateRangeRequest) {
        return announcementRepository.getAnnouncementInDateRange(dateRangeRequest.getFromDate(),dateRangeRequest.getToDate());
    }

    public List<Announcement> findAnnouncementsByKeyWords(String keywords) {
        return announcementRepository.findAnnouncementByKeywords(keywords);
    }
}
