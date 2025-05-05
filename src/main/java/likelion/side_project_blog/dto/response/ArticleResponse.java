package likelion.side_project_blog.dto.response;

import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private int totalLike;
    //댓글목록
    private final List<CommentResponse> comments;
    private int totalComments;
    private Boolean isLiked;

    public ArticleResponse(Article article, List<CommentResponse> comments,boolean isLiked){
        this.id=article.getId();
        this.title= article.getTitle();;
        this.content= article.getContent();
        this.createdAt=article.getCreatedAt();
        this.comments=comments;
        this.author=article.getUser().getUserId();
        this.totalLike= article.getTotalLike();
        this.totalComments=comments.size();
        this.isLiked=isLiked;
    }

    public ArticleResponse(Article article, List<CommentResponse> comments){
        this.id=article.getId();
        this.title= article.getTitle();
        this.content=article.getContent();
        this.createdAt=article.getCreatedAt();
        this.author=article.getUser().getUserId();
        this.comments=comments;
        this.totalLike=article.getTotalLike();
        this.totalComments=comments.size();
        this.isLiked=null;
    }





}
