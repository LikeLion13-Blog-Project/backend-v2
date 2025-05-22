package likelion.side_project_blog.dto.request;

import likelion.side_project_blog.domain.User;
import lombok.Getter;

@Getter
public class LoginRequest {
    private String userId;
    private String password;

    public User toEntity(LoginRequest request) {
        return User.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .build();
    }
}
