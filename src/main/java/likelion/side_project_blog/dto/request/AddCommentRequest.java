package likelion.side_project_blog.dto.request;

import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.Comment;
import likelion.side_project_blog.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddCommentRequest {
    private String content;

    public Comment toEntity(AddCommentRequest request, Article article, User user) {
        return Comment.builder()
                .article(article)
                .user(user)
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
