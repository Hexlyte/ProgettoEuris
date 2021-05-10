package org.generation.italy.euris.controller;

import java.util.List;

import org.generation.italy.euris.dao.IDaoArticles;
import org.generation.italy.euris.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ControllerArticles {
	
	@Autowired
	private IDaoArticles dao;
	
	@GetMapping
    List<Article> articles() {
		return dao.articles();
	}
    
    @GetMapping("/{code}")
    Article article(@PathVariable int code) {
    	return dao.article(code);
    }
    
    @PostMapping
    void add(@RequestBody Article a) {
    	dao.add(a);
    }
    
    @DeleteMapping("/{code}")
    void delete(@PathVariable int code) {
    	dao.delete(code);
    }
    
    @PutMapping
    void update(@RequestBody Article a) {
    	dao.update(a);
    }

}
