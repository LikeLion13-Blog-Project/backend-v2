package likelion.side_project_blog.service;

import jakarta.persistence.EntityNotFoundException;
import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.Likes;
import likelion.side_project_blog.domain.User;
import likelion.side_project_blog.exception.ArticleNotFoundException;
import likelion.side_project_blog.repository.ArticleRepository;
import likelion.side_project_blog.repository.LikeRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;


    public String clickLikes(User user, long id) {
        Article article=articleRepository.findById(id)
                .orElseThrow(()->new ArticleNotFoundException("해당 ID의 글을 찾을 수 없습니다."));
        Optional<Likes> like=likeRepository.findByUserAndArticle(user,article);

        if(like.isEmpty()){
            //좋아요 생성
            Likes newLike=new Likes(user,article);
            likeRepository.save(newLike);
            article.calTotalLikes(1);
            articleRepository.save(article);
            return "좋아요 생성 성공";

        }else{
            //좋아요 삭제
            likeRepository.delete(like.get());
            article.calTotalLikes(-1);
            articleRepository.save(article);
            return "좋아요 삭제 성공";
        }
    }
}
