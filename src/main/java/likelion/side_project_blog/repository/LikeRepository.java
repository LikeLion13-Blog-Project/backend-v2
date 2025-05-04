package likelion.side_project_blog.repository;

import likelion.side_project_blog.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes,Long> {
}
