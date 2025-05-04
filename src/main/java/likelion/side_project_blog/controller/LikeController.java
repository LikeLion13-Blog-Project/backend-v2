package likelion.side_project_blog.controller;

import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

//    @PostMapping("/{id}")
//    public ResponseEntity<ApiResponse> clickLikes(){
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deleteLikes(){
//
//    }
}

