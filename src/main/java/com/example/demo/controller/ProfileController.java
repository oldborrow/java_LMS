package com.example.demo.controller;

import com.example.demo.service.AvatarStorageService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.ServerException;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private AvatarStorageService avatarStorageService;

    @GetMapping
    public String profile(Model model) {
        return "profile_table";
    }

    @PostMapping("/avatar")
    public void updateAvatarImage(@RequestParam("avatar") MultipartFile avatar){
        logger.info("File name {}, file content type {}, file size {}",avatar.getOriginalFilename(),avatar.getContentType(),avatar.getSize());
    }

    @PostMapping("/avatar")
    public String updateAvatarImage(Authentication auth,
                                    @RequestParam("avatar") MultipartFile avatar) throws ServerException {
        logger.info("File name {}, file content type {}, file size {}", avatar.getOriginalFilename(), avatar.getContentType(), avatar.getSize());
        try {
            avatarStorageService.save(auth.name(), avatar.getContentType(), avatar.getInputStream());
        } catch (Exception ex) {
            logger.info("", ex);
            throw new InternalServerError(5);
        }
        return "redirect:/profile";
    }
}
