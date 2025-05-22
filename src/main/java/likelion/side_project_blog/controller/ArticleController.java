package likelion.side_project_blog.controller;

import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.request.AddArticleRequest;
import likelion.side_project_blog.dto.request.UpdateArticleRequest;
import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.dto.response.ArticleResponse;
import likelion.side_project_blog.security.UserDetailsImpl;
import likelion.side_project_blog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    /*게시글 전체 조회*/
    @GetMapping
    public ResponseEntity<ApiResponse<List<ArticleResponse>>> getAllArticles(){

        List<ArticleResponse> articles=articleService.getAllArticles();
        return ResponseEntity.ok(new ApiResponse(true, 200, "게시글 조회 성공", articles));

    }

    /*게시글 추가*/
    @PostMapping
    public ResponseEntity<ApiResponse> addArticle(@RequestBody AddArticleRequest request,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user=userDetails.getUser(); //현재 로그인한 사용자
        articleService.addArticle(request,user);
        return ResponseEntity.ok(new ApiResponse(true,200,"게시글 등록 성공"));
    }

    /*게시글 단일 조회*/
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getArticle(@PathVariable long id,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user=userDetails.getUser();
        ArticleResponse articleResponse=articleService.getArticle(id,user);
        return ResponseEntity.ok(new ApiResponse(true,200,"게시글 조회 성공", articleResponse));

    }

    /*게시글 수정*/
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateArticle(@PathVariable long id,
                                                     @RequestBody UpdateArticleRequest request,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user=userDetails.getUser();
        articleService.updateArticle(id,request,user);
        return ResponseEntity.ok(new ApiResponse(true,200,"게시글 수정 성공"));

    }

    /*게시글 삭제*/
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteArticle(@PathVariable long id,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        User user=userDetails.getUser();
        articleService.deleteArticle(id,user);
        return ResponseEntity.ok(new ApiResponse(true,200,"게시글 삭제 성공"));

    }
}
