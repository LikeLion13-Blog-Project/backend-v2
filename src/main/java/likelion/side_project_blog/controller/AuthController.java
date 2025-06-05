package likelion.side_project_blog.controller;

import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.request.LoginRequest;
import likelion.side_project_blog.dto.response.ApiResponse;
import likelion.side_project_blog.dto.response.TokenResponse;
import likelion.side_project_blog.service.JwtTokenService;
import likelion.side_project_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> userLogin(@RequestBody LoginRequest request){
        User user=userService.loginOrRegister(request);
        String token=jwtTokenService.createToken(user.getUserId());
        return ResponseEntity.ok(new ApiResponse(true,200,"로그인 성공", new TokenResponse(token)));
    }
}
