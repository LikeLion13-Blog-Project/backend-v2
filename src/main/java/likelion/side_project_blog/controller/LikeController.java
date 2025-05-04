package likelion.side_project_blog.controller;

import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.security.UserDetailsImpl;
import likelion.side_project_blog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse> clickLikes(@PathVariable long id,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user=userDetails.getUser();
        String message=likeService.clickLikes(user,id);
        return ResponseEntity.ok(new ApiResponse(true,200,message));

    }
}

