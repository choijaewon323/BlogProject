package com.example.blog.controller.api;

import com.example.blog.dto.AccountRequestDto;
import com.example.blog.service.HeartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/api")
@RestController
public class HeartApiController {
    private final HeartService heartService;

    public HeartApiController(HeartService heartService) {
        this.heartService = heartService;
    }

    @PostMapping("/heart/{postId}")
    public ResponseEntity<Void> push(@PathVariable Long postId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        AccountRequestDto account = (AccountRequestDto)session.getAttribute("success");

        String username = account.getUsername();

        try {
            heartService.push(postId, username);

            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();

            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/heart/{postId}")
    public ResponseEntity<Void> pop(@PathVariable Long postId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        AccountRequestDto account = (AccountRequestDto)session.getAttribute("success");
        String username = account.getUsername();

        try {
            heartService.pop(postId, username);

            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
