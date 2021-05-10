package org.generation.italy.euris.dao;

public interface IDaoOperations {
	
	/**
	 * Metodo che ritorna la somma tra due dati inseriti.
	 * @param cost1 Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @param cost2 Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @return La somma tra due dati inseriti.
	 */
	String sum(String cost1, String cost2);
	
	/**
	 * Metodo che ritorna la sottrazione tra due dati inseriti.
	 * @param cost1 Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @param cost2 Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @return La sottrazione tra due dati inseriti.
	 */
	String sub(String cost1, String cost2);
	
	/**
	 * Metodo che ritorna la moltiplicazione tra due dati inseriti.
	 * @param cost Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @param multi Numero intero per effettuare la moltiplicazione col dato inserito.
	 * @return La moltiplicazione tra due dati inseriti.
	 */
	String mult(String cost, int multi);
	
	/**
	 * Metodo che ritorna la divisione tra due dati inseriti.
	 * @param cost Dato contenente un costo col sistema monetario del Regno Unito nel 1970 (Xp Ys Zd) in formato String.
	 * @param div Numero intero per effettuare la divisione col dato inserito.
	 * @return La divisione tra due dati inseriti.
	 */
	String div(String cost, int div);
	
}
