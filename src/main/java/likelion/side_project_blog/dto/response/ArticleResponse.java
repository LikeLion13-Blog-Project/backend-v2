package likelion.side_project_blog.dto.response;

import likelion.side_project_blog.domain.Article;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ArticleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private int totalLike;
    private int totalComments;
    //댓글목록
    private final List<CommentResponse> comments;
    private Boolean isLiked;



    public static ArticleResponse of(Article article, List<CommentResponse> commentList, boolean isLiked) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getUser().getUserId())
                .createdAt(article.getCreatedAt())
                .totalLike(article.getTotalLike())
                .totalComments(article.getTotalComments())
                .comments(commentList)
                .isLiked(isLiked)
                .build();
    }

    public static ArticleResponse of(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getUser().getUserId())
                .createdAt(article.getCreatedAt())
                .totalLike(article.getTotalLike())
                .totalComments(article.getTotalComments())
                .build();
    }
}
