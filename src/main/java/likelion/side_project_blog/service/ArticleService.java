package likelion.side_project_blog.service;

import jakarta.persistence.EntityNotFoundException;
import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.dto.request.AddArticleRequest;
import likelion.side_project_blog.dto.request.UpdateArticleRequest;
import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.dto.response.ArticleResponse;
import likelion.side_project_blog.dto.response.CommentResponse;
import likelion.side_project_blog.repository.ArticleRepository;
import likelion.side_project_blog.repository.CommentRepository;
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

    //글 추가
    public void addArticle(AddArticleRequest request){

        articleRepository.save(request.toEntity());
    }


    //전체 글 조회
    public List<ArticleResponse> getAllArticles(){
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(article -> new ArticleResponse(article,getCommentList(article)))
                .toList();

    }


    //단일 글 조회
    public ArticleResponse getArticle(Long id){
        Article article=articleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다."));
        List<CommentResponse> comments=getCommentList(article);
        return new ArticleResponse(article,comments);
    }


    //글 삭제
    public boolean deleteArticle(Long id){
        Optional<Article> article=articleRepository.findById(id);
        if(article.isPresent()){
            articleRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //글 수정
    public boolean updateArticle(Long id, UpdateArticleRequest request){
        Optional<Article> article=articleRepository.findById(id);
        if(article.isPresent()){
            article.get().update(request.getTitle(),request.getContent());
            articleRepository.save(article.get());
            return true;
        }else return false;
    }


    //댓글 가져오기
    private List<CommentResponse> getCommentList(Article article){
        return commentRepository.findByArticle(article).stream()
                .map(comment->new CommentResponse(comment.getContent(),comment.getCreatedAt()))
                .toList();
    }


}
