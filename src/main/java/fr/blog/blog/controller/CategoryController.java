package fr.blog.blog.controller;

import fr.blog.blog.entity.Category;
import fr.blog.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping ("categories")
    public List<Category> getCategories(){return categoryRepository.findAll();}

    @GetMapping("categories/{id}")
    public Category getCategory(@PathVariable Long id) {return  categoryRepository.findById(id).get();}
    @PostMapping("categories")
    public Category createCategory(@RequestBody Category category) {return categoryRepository.save(category);}
    @PutMapping("categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category){
        Category updateCategory = categoryRepository.findById(id).get();
        updateCategory.setName(category.getName());
        return categoryRepository.save(updateCategory);
    }
    @DeleteMapping("categories/{id}")
    public boolean deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);
        return true;
    }

}
