package likelion.side_project_blog.service;

import jakarta.persistence.EntityNotFoundException;
import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.request.AddArticleRequest;
import likelion.side_project_blog.dto.request.UpdateArticleRequest;
import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.dto.response.ArticleResponse;
import likelion.side_project_blog.dto.response.CommentResponse;
import likelion.side_project_blog.exception.ArticleNotFoundException;
import likelion.side_project_blog.exception.PermissionDeniedException;
import likelion.side_project_blog.repository.ArticleRepository;
import likelion.side_project_blog.repository.CommentRepository;
import likelion.side_project_blog.repository.LikeRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Builder
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    //글 추가
    public void addArticle(AddArticleRequest request, User user){
        articleRepository.save(request.toEntity(user));
    }


    //전체 글 조회
    public List<ArticleResponse> getAllArticles(){
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> new ArticleResponse(article,getCommentList(article)))
                .toList();

    }



    //단일 글 조회
    public ArticleResponse getArticle(Long id, User user){
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));
        boolean isLiked=checkLikes(user,article);
        List<CommentResponse> comments=getCommentList(article);
        return new ArticleResponse(article,comments,isLiked);
    }


    //글 삭제
    public void deleteArticle(Long id, User user){
        Article article=articleRepository.findById(id)
                        .orElseThrow(()->new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다"));
        if(!article.getUser().getUserId().equals(user.getUserId())){
            throw new PermissionDeniedException("해당 글에 대한 삭제 권한이 없습니다");
        }
        articleRepository.deleteById(id);

    }

    //글 수정
    public void updateArticle(Long id, UpdateArticleRequest request, User user){
        Article article=articleRepository.findById(id)
                        .orElseThrow(()->new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다"));
        if(!article.getUser().getUserId().equals(user.getUserId())){
            throw new PermissionDeniedException("해당 글에 대한 수정 권한이 없습니다");
        }
        article.update(request.getTitle(),request.getContent());
        articleRepository.save(article);

    }


    //댓글 가져오기
    private List<CommentResponse> getCommentList(Article article){
        return commentRepository.findByArticle(article).stream()
                .map(comment->new CommentResponse(comment))
                .toList();
    }

    //좋아요 눌렀는지
    private boolean checkLikes(User user,Article article){
        return likeRepository.existsByUserAndArticle(user,article);
    }


}
