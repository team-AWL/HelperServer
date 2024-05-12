package com.volodymyrvasylyshyn.helperserver.request;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate seemLastTime;

}
