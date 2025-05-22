package likelion.side_project_blog.service;

import jakarta.persistence.EntityNotFoundException;
import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.Comment;
import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.request.AddCommentRequest;
import likelion.side_project_blog.exception.ArticleNotFoundException;
import likelion.side_project_blog.exception.CommentNotFoundException;
import likelion.side_project_blog.exception.PermissionDeniedException;
import likelion.side_project_blog.repository.ArticleRepository;
import likelion.side_project_blog.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    /*댓글 작성*/
    public void addComment(long articleId, AddCommentRequest request, User user) {
        Article article=articleRepository.findById(articleId)
                .orElseThrow(()->new ArticleNotFoundException("해당 ID의 게시글을 찾을 수 없습니다"));

        commentRepository.save(request.toEntity(request,article,user));
        article.calTotalComments(1);
        articleRepository.save(article);
    }


    /*댓글 삭제*/
    public void deleteComment(long commentId,User user) {
        Comment comment=commentRepository.findById(commentId)
                .orElseThrow(()->new CommentNotFoundException("해당 ID의 댓글을 찾을 수 없습니다."));

        if(!comment.getUser().getUserId().equals(user.getUserId())){
            throw new PermissionDeniedException("해당 댓글에 대한 삭제 권한이 없습니다.");
        }
        commentRepository.deleteById(commentId);
        comment.getArticle().calTotalComments(-1);
        articleRepository.save(comment.getArticle());

    }




}
