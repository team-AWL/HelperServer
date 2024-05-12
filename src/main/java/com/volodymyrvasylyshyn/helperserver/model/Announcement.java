package com.volodymyrvasylyshyn.helperserver.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = {"announcementCreator"})

// TODO: add photo of people
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullNameOfPerson;

    private String location;

    private String contactInformation;

    private String description;

    private String imageUrl;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate seemLastTime;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    private User announcementCreator;


}
