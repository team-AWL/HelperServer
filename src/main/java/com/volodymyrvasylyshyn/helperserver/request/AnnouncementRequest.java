package com.volodymyrvasylyshyn.helperserver.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRequest {
    private String fullNameOfPerson;

    private String location;

    private String description;

    private String urlToGoogleForms;

    private LocalDate seemLastTime;

}
