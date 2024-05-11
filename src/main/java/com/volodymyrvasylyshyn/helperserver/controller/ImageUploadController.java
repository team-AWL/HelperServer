package com.volodymyrvasylyshyn.helperserver.controller;


import com.volodymyrvasylyshyn.helperserver.model.ImageModel;
import com.volodymyrvasylyshyn.helperserver.response.MessageResponse;
import com.volodymyrvasylyshyn.helperserver.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("api/v1/image")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadImageToUser(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {

        imageUploadService.uploadImageToUser(file, principal);
        return new ResponseEntity<>(new MessageResponse("Image Uploaded Successfully"), HttpStatus.OK);
    }

    @PostMapping("/{announcementId}/upload")
    public ResponseEntity<MessageResponse> uploadImageToAnnouncement(@PathVariable("announcementId") String announcementId, @RequestParam("file") MultipartFile file) throws IOException {
        imageUploadService.uploadImageToAnnouncement(file, Long.parseLong(announcementId));
        return new ResponseEntity<>(new MessageResponse("Image Uploaded Successfully"), HttpStatus.OK);

    }

    @GetMapping("/profileImage")
    public ResponseEntity<ImageModel> getImageToUser(Principal principal) {
        ImageModel userImage = imageUploadService.getImageToUser(principal);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }

    @GetMapping("/{announcementId}/image")
    public ResponseEntity<ImageModel> getImageToAnnouncement(@PathVariable("announcementId") String announcementId){
        ImageModel needImage = imageUploadService.getImageToAnnouncement(Long.parseLong(announcementId));
        return new ResponseEntity<>(needImage, HttpStatus.OK);
    }


    @GetMapping("/user/{email}")
    public ResponseEntity<ImageModel> getImageToUserByEmail(@PathVariable("email") String email){
        ImageModel userImage = imageUploadService.getImageToUserByEmail(email);
        return new ResponseEntity<>(userImage, HttpStatus.OK);
    }


}
