package com.example.demo.domain;

import javax.persistence.*;
@Entity
@Table(name = "avatar_images")
public class AvatarImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contentType;

    @Column
    private String filename;

    public AvatarImage() {
    }

    @OneToOne
    private User user;

    public AvatarImage(Object o, String contentType, String filename, User user) {

        this.contentType = contentType;
        this.filename = filename;
        this.user = user;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }
}
