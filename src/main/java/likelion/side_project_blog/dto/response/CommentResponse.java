package likelion.side_project_blog.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final String content;
    private final LocalDateTime createdAt;

    public CommentResponse(String content, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
    }
}
