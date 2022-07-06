package com.example.blog.controller.api;

import com.example.blog.dto.ReplyRequestDto;
import com.example.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("/api/reply/{postID}")
    public void createReply(@RequestBody ReplyRequestDto requestDto, @PathVariable Long postID) {
        replyService.createReply(requestDto.getWriter(), requestDto.getContent(), postID);
    }

    @PutMapping("/api/reply/{replyID}")
    public void updateReply(@RequestBody ReplyRequestDto requestDto, @PathVariable Long replyID) {
        replyService.updateReply(requestDto.getWriter(), requestDto.getContent(), replyID);
    }

    @DeleteMapping("/api/reply/{replyID}")
    public void deleteReply(@PathVariable Long replyID) {
        replyService.deleteReply(replyID);
    }
}
