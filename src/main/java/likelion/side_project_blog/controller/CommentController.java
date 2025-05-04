package likelion.side_project_blog.controller;

import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.request.AddCommentRequest;
import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.security.UserDetailsImpl;
import likelion.side_project_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("blog/comment")
public class CommentController {
    private final CommentService commentService;

    /*댓글 생성*/
    @PostMapping("/{articleId}")
    public ResponseEntity<ApiResponse> addComment(@PathVariable long articleId,
                                  @RequestBody AddCommentRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails ){
        User user=userDetails.getUser();
        commentService.addComment(articleId,request,user);
        return ResponseEntity.ok(new ApiResponse(true,200,"댓글 등록 성공"));
    }

    /*댓글 삭제*/
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable long commentId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user=userDetails.getUser();
        commentService.deleteComment(commentId,user);
        return ResponseEntity.ok(new ApiResponse(true,200,"댓글 삭제 성공"));
    }





}
