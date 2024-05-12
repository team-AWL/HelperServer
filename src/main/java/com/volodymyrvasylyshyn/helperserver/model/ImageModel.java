package com.volodymyrvasylyshyn.helperserver.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(columnDefinition = "LONGBLOB") // minion
    private byte[] imageBytes;
    @OneToOne(mappedBy = "imageModel")
    private User user;

}