package com.volodymyrvasylyshyn.helperserver.controller;

import com.volodymyrvasylyshyn.helperserver.model.Announcement;
import com.volodymyrvasylyshyn.helperserver.request.AnnouncementRequest;
import com.volodymyrvasylyshyn.helperserver.request.DateRangeRequest;
import com.volodymyrvasylyshyn.helperserver.response.MessageResponse;
import com.volodymyrvasylyshyn.helperserver.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }


    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @PostMapping("/telegram")
    public ResponseEntity<MessageResponse> createAnnouncementFromTelegram(@RequestBody AnnouncementRequest announcementRequest) {
        announcementService.createAnnouncementFromTelegram(announcementRequest);
        return new ResponseEntity<>(new MessageResponse("Announcement created successfully"), HttpStatus.CREATED);
    }
    @PostMapping
    public ResponseEntity<MessageResponse> createAnnouncement(@RequestBody AnnouncementRequest announcementRequest, Principal principal) {
        announcementService.createAnnouncement(announcementRequest, principal);
        return new ResponseEntity<>(new MessageResponse("Announcement created successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/{announcementId}")
    public ResponseEntity<MessageResponse> updateAnnouncement(@RequestBody AnnouncementRequest announcementRequest, @PathVariable("announcementId") Long announcementId, Principal principal) {
        announcementService.updateAnnouncement(announcementId, announcementRequest, principal);
        return new ResponseEntity<>(new MessageResponse("Announcement updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{announcementId}")
    public ResponseEntity<MessageResponse> deleteAnnouncement(@PathVariable("announcementId") Long announcementId){
        announcementService.deleteAnnouncement(announcementId);
        return new ResponseEntity<>(new MessageResponse("Announcement deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<Announcement>> findAnnouncementsByKeyWords(@PathVariable("keywords") String keywords){
        List<Announcement> announcements = announcementService.findAnnouncementsByKeyWords(keywords);
        return ResponseEntity.ok(announcements);
    }
    @GetMapping("/inDateRange")
    public ResponseEntity<List<Announcement>> findAnnouncementsInDateRange(@RequestBody DateRangeRequest dateRangeRequest){
        List<Announcement> announcements = announcementService.findAnnouncementsInDateRange(dateRangeRequest);
        return new ResponseEntity<>(announcements,HttpStatus.OK);
    } // union this
    @GetMapping("/byLocation")
    public ResponseEntity<List<Announcement>> findAnnouncementsByLocation(@RequestParam("location") String location){
        List<Announcement> announcements = announcementService.findAnnouncementsByLocation(location);
        return new ResponseEntity<>(announcements,HttpStatus.OK);
    }
    @GetMapping("/sortedByDate/{order}")
    public ResponseEntity<List<Announcement>> findAnnouncementsSortedByDate(@PathVariable("order") String order){
        List<Announcement> announcements = announcementService.findAnnouncementsSortedByDate(order);
        return new ResponseEntity<>(announcements,HttpStatus.OK);
    }

}
