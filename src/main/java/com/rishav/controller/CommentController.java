package com.rishav.controller;

import com.rishav.config.JwtConstant;
import com.rishav.model.Comment;
import com.rishav.model.User;
import com.rishav.request.CreateCommentRequest;
import com.rishav.response.MessageResponse;
import com.rishav.service.CommentService;
import com.rishav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest request,
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        Comment createComment = commentService.createComment(request.getIssueId(),
                user.getId(),
                request.getContent());
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader(JwtConstant.JWT_HEADER) String jwt
    ) throws Exception {
        User user = userService.findByProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse response = new MessageResponse();
        response.setMessage("Comment deleted Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(
            @PathVariable Long issueId
    ) {
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
