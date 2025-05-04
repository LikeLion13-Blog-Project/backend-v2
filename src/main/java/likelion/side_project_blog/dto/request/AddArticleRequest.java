package likelion.side_project_blog.dto.request;

import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(User user){
        return Article.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
