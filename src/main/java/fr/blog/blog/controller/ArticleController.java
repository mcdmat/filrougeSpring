package fr.blog.blog.controller;

import fr.blog.blog.entity.Article;
import fr.blog.blog.entity.Category;
import fr.blog.blog.repository.ArticleRepository;
import fr.blog.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("articles")
    public List<Article> getArticles(@RequestParam(required = false) Category categoryId){
        return articleRepository.findAll();}

    @GetMapping("articles/{id}")
    public Article getArticle(@PathVariable Long id) {return  articleRepository.findById(id).get();}
    @GetMapping("categories/{categoryId}/articles")
    public List<Article> getArticlesByCategory(@PathVariable Category categoryId){
        return articleRepository.findArticlesByCategory(categoryId);
    }
    @GetMapping("articles/latest")
    public List<Article> getLatestArticles () {
        return articleRepository.findTop3ByOrderByCreatedAtDesc();
    }

//    @PostMapping("articles")
//    public Article createArticle(@RequestBody Article article) {
//        return articleRepository.save(article);}
    @PostMapping("categories/{categoryId}/articles")
    public Article createArticleWithCategory( @PathVariable Long categoryId, @RequestBody Article article){
        Category categoryToUse = categoryRepository.findById(categoryId).get();
        article.setCategory(categoryToUse);
        return articleRepository.save(article);
    }
    @PutMapping("articles/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article){
        Article updateArticle = articleRepository.findById(id).get();
        updateArticle.setTitle(article.getTitle());
        updateArticle.setContent(article.getContent());
        updateArticle.setSlug(article.getSlug());
        return articleRepository.save(updateArticle);
    }
    @DeleteMapping("articles/{id}")
    public boolean deleteArticle(@PathVariable Long id){
        articleRepository.deleteById(id);
        return true;
    }

}
