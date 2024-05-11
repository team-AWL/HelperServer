package com.volodymyrvasylyshyn.helperserver.service;


import com.volodymyrvasylyshyn.helperserver.exeptions.AnnouncementNotFoundException;
import com.volodymyrvasylyshyn.helperserver.facade.AnnouncementFacade;
import com.volodymyrvasylyshyn.helperserver.model.Announcement;
import com.volodymyrvasylyshyn.helperserver.model.User;
import com.volodymyrvasylyshyn.helperserver.repository.AnnouncementRepository;
import com.volodymyrvasylyshyn.helperserver.repository.UserRepository;
import com.volodymyrvasylyshyn.helperserver.request.AnnouncementRequest;
import com.volodymyrvasylyshyn.helperserver.request.DateRangeRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final AnnouncementFacade announcementFacade;
    private final UserService userService;

    public AnnouncementService(AnnouncementRepository announcementRepository, UserRepository userRepository, AnnouncementFacade announcementFacade, UserService userService) {
        this.announcementRepository = announcementRepository;
        this.userRepository = userRepository;
        this.announcementFacade = announcementFacade;
        this.userService = userService;
    }

    public void createAnnouncement(AnnouncementRequest announcementRequest, Principal principal) {
        User user = userService.getCurrentUser(principal);
        Announcement createdAnnouncement = announcementFacade.announcementRequestToAnnouncement(announcementRequest);
        createdAnnouncement.setAnnouncementCreator(user);
        user.getCreatedAnnouncements().add(createdAnnouncement);
        userRepository.save(user);
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

    public List<Announcement> findAnnouncementsSortedByDateAsc() {
        return announcementRepository.findAllByOrderBySeemLastTimeAsc();
    }
    public List<Announcement> findAnnouncementsSortedByDateDesc() {
        return announcementRepository.findAllByOrderBySeemLastTimeDesc();
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
