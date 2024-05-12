package com.volodymyrvasylyshyn.helperserver.mapper;


import com.volodymyrvasylyshyn.helperserver.model.Announcement;
import com.volodymyrvasylyshyn.helperserver.request.AnnouncementRequest;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementMapper {


    public Announcement announcementRequestToAnnouncement(AnnouncementRequest announcementRequest){
        Announcement announcement = new Announcement();
        announcement.setDescription(announcementRequest.getDescription());
        announcement.setImageUrl(announcementRequest.getImageUrl());
        announcement.setLocation(announcementRequest.getLocation());
        announcement.setContactInformation(announcementRequest.getContactInformation());
        announcement.setFullNameOfPerson(announcementRequest.getFullNameOfPerson());
        announcement.setSeemLastTime(announcementRequest.getSeemLastTime());
        return announcement;
    }
}
