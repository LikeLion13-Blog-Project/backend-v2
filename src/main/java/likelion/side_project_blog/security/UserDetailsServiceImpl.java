package likelion.side_project_blog.security;

import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserId(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 ID의 유저를 찾을 수 없습니다. "+username));

        return new UserDetailsImpl(user);
    }
}
