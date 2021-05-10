package org.generation.italy.euris.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.generation.italy.euris.model.Article;
import org.generation.italy.euris.util.BasicDao;
import org.generation.italy.euris.util.IMappablePro;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DaoArticles extends BasicDao implements IDaoArticles {

	public DaoArticles(
			@Value("${db.address}") String dbAddress,
			@Value("${db.user}") String user,
			@Value("${db.psw}") String password) {
		
		super(dbAddress, user, password);
	}

	@Override
	public List<Article> articles() {
		List<Article> res = new ArrayList<>();
		
		List<Map<String, String>> maps = getAll("SELECT * FROM articles");
		
		for (Map<String, String> map : maps) {
			res.add(IMappablePro.fromMap(Article.class, map));
		}
		
		return res;
	}

	@Override
	public Article article(int code) {
		Article res = null;
		
		Map<String, String> map = getOne("SELECT * FROM articles WHERE code = ?;", code);
		
		if (map != null) {
			res = IMappablePro.fromMap(Article.class, map);
		}
		
		return res;
	}

	@Override
	public void add(Article article) {
		execute("INSERT INTO articles (name, cost) VALUES (?, ?)",
					article.getName(), article.getCost());
	}

	@Override
	public void delete(int code) {
		execute("DELETE FROM articles WHERE code = ?", code);
	}

	@Override
	public void update(Article article) {
		execute("UPDATE articles SET name = ?, cost = ? WHERE code = ?",
				article.getName(), article.getCost(), article.getCode());
	}

}
