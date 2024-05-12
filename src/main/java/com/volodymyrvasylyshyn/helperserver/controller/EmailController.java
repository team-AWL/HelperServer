package com.volodymyrvasylyshyn.helperserver.controller;


import com.volodymyrvasylyshyn.helperserver.model.Email;
import com.volodymyrvasylyshyn.helperserver.response.ApiResponse;
import com.volodymyrvasylyshyn.helperserver.service.EmailSenderService;
import com.volodymyrvasylyshyn.helperserver.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    private final EmailSenderService emailSenderService;

//    TODO:To lombok
    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);


    @PostMapping
    public ResponseEntity<ApiResponse> addEmail(@RequestBody Email email) {
        emailService.addEmail(email);
        String senderEmail = email.getEmail();
        try {
            emailSenderService.sendMail(senderEmail, "WELCOME", "You are subscribe to are our updates");
        } catch (MailException e) {
            LOG.error("Error while sending out email ..{}", (Object) e.getStackTrace());
        }
        return new ResponseEntity<>(new ApiResponse(true, "Email added success"), HttpStatus.CREATED);
    }

}
