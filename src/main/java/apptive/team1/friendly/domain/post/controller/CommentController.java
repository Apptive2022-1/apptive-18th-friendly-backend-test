package apptive.team1.friendly.domain.post.controller;

import apptive.team1.friendly.domain.post.dto.CommentFormDto;
import apptive.team1.friendly.domain.post.service.CommentService;
import apptive.team1.friendly.domain.post.service.PostService;
import com.amazonaws.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Long> addComment(@PathVariable(value = "postId") Long postId, @RequestBody CommentFormDto commentFormDto) {
        Long commentId = commentService.addComment(commentFormDto, postId);
        return new ResponseEntity<>(commentId, HttpStatus.OK);
    }

}
