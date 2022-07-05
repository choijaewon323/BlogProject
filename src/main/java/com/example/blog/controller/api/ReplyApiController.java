package com.example.blog.controller.api;

import com.example.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("/api/reply/{postID}")
    public void createReply(@RequestBody String writer, @RequestBody String content, @PathVariable Long postID) {
        replyService.createReply(writer, content, postID);
    }

    @PutMapping("/api/reply/{replyID}")
    public void updateReply(@RequestBody String writer, @RequestBody String content, @PathVariable Long replyID) {
        replyService.updateReply(writer, content, replyID);
    }

    @DeleteMapping("/api/reply/{replyID}")
    public void deleteReply(@PathVariable Long replyID) {
        replyService.deleteReply(replyID);
    }
}
