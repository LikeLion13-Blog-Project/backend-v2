package likelion.side_project_blog.service;

import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.dto.request.LoginRequest;
import likelion.side_project_blog.exception.PasswordMismatchException;
import likelion.side_project_blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User loginOrRegister(LoginRequest request){
        String id=request.getUserId();
        Optional<User> user=userRepository.findByUserId(id);
        if(user.isEmpty()){
            /*계정 생성*/
            User newUser=request.toEntity(request);
            userRepository.save(newUser);
            return newUser;
        }else{
            /*비밀번호 비교*/
            if(!request.getPassword().equals(user.get().getPassword())){
                throw new PasswordMismatchException("비밀번호가 틀렸습니다.");
            }else{
                return user.get();
            }
        }
    }

}
