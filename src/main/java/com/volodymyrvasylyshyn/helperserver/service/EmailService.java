package com.volodymyrvasylyshyn.helperserver.service;


import com.volodymyrvasylyshyn.helperserver.exeptions.EmailAlreadyExistException;
import com.volodymyrvasylyshyn.helperserver.exeptions.EmailNotFoundException;
import com.volodymyrvasylyshyn.helperserver.model.Email;
import com.volodymyrvasylyshyn.helperserver.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public void addEmail(Email email) {
        if(emailRepository.findByEmail(email.getEmail()).isEmpty()){
            emailRepository.save(email);
        }
        else {
            throw new EmailAlreadyExistException("This email already added");
        }
    }
}
