package org.generation.italy.euris.dao;

import java.util.List;

import org.generation.italy.euris.model.Article;

public interface IDaoArticles {
	
	/**
	 * Metodo che ritorna la lista completa degli articoli.
	 * @return La lista degli articoli.
	 */
	List<Article> articles();
	
	/**
	 * Metodo che ritorna il dettaglio di un singolo articolo secondo un codice identificativo.
	 * @param code Codice identificativo collegato all'articolo.
	 * @return Il dettaglio di un singolo articolo.
	 */
	Article article(int code);
	
	/**
	 * Metodo che permette l'aggiunta di un nuovo articolo nel catalogo.
	 * @param article Oggetto che comprende i dati necessari di un Article.
	 */
	void add(Article article);
	
	/**
	 * Metodo che permette l'eliminazione di un articolo dal catalogo secondo un codice identificativo.
	 * @param code Codice identificativo collegato all'articolo.
	 */
	void delete(int code);
	
	/**
	 * Metodo che permette la modifica di un articolo nel catalogo.
	 * @param article Oggetto che comprende i dati necessari di un Article.
	 */
	void update(Article article);

}
