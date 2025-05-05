package likelion.side_project_blog.repository;

import likelion.side_project_blog.domain.Article;
import likelion.side_project_blog.domain.Likes;
import likelion.side_project_blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findByUserAndArticle(User user, Article article);

    boolean existsByUserAndArticle(User user, Article article);
}
