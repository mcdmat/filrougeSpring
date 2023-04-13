package fr.blog.blog.repository;

import fr.blog.blog.entity.Article;
import fr.blog.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticlesByCategory(Category category);
    List<Article> findTop3ByOrderByCreatedAtDesc();
}
