package org.generation.italy.euris.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Utility creata durante il corso.

/**
 * Questa classe ha lo scopo di fornire gli strumenti base per effettuare ORM.<br />
 * Stabilita la connessione è in grado di eseguie query e restituire in caso di necessità una
 * lista di mappe o una mappa che descrive un ResultSet.<br />
 * La connessione di questo dao è sempre aperta.
 * @author Yuri
 */
public abstract class BasicDao {
	
	private Connection connection;
	
	public BasicDao(String dbAddress, String user, String password) {
		super();
		
		try {
			connection = DriverManager.getConnection(dbAddress, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce l'oggetto di tipo ResultSet derivante dall'esecuzione di una query.
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 * @return L'oggetto ResultSet.
	 * @throws SQLException
	 */
	private ResultSet executeQuery(String sql, Object... conditions) throws SQLException {
		return preparedStm(sql, conditions).executeQuery();
	}

	/**
	 * Restituisce l'oggetto di tipo PreparedStatement per eseguire una query.
	 * <br /><br />
	 * <code>String sql = "SELECT * FROM tabella WHERE id = ?"</code> => conditions[] => 1.<br />
	 * ?(1) => conditions[0]<br />
	 * ?(2) => conditions[1]
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 * @return Il PreparedStatement contenente la query con i placeholders sostituiti.
	 * @throws SQLException
	 */
	private PreparedStatement preparedStm(String sql, Object... conditions) throws SQLException {
		PreparedStatement stm = connection.prepareStatement(sql);
		
		for (int i = 0; i < conditions.length; i++) {
			stm.setObject(i + 1, conditions[i]);
		}
		return stm;
	}
	
	/**
	 * Restituisce la mappa di una singola riga di un ResultSet dove la chiave corrisponde al nome
	 * della colonna della tabella e il valore quello nella cella di quella determinata riga.
	 * @param rs -> Il ResultSet ottenuto dal DB eseguendo una determinata query.
	 * @return La mappa che descrive la singola riga di un ResultSet.
	 * @throws SQLException
	 */
	private Map<String, String> mapFromRS(ResultSet rs) throws SQLException {
		Map<String, String> map = new HashMap<>();
		
		ResultSetMetaData meta = rs.getMetaData();
		
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			map.put(meta.getColumnName(i), rs.getString(i));
		}
		
		return map;
	}
	
	/**
	 * Lista contenente mappe che descrivono delle entità nella persistenza.<br />
	 * La mappa è la rappresentazione di una RIGA di una tabella.<br />
	 * La lista quindi è l'insieme delle righe di una tabella.
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 * @return La lista contenente tutte le mappe restituite dal DB in base alla query inserita.
	 */
	public List<Map<String, String>> getAll(String sql, Object...conditions) {
		// ... Indica che potranno esserci da 0 a infiniti parametri.
		// Si è inserito Object perché si vuole essere il più generici possibile.
		
		List<Map<String, String>> ris = new ArrayList<>();
		
		try  {
			ResultSet rs = executeQuery(sql, conditions);
			
			while (rs.next()) {
				ris.add(mapFromRS(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ris;
	}

	/**
	 * Mappa che descrive un'entità nella persistenza.
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 * @return La mappa restituita dal DB in base alla query inserita.
	 */
	public Map<String, String> getOne(String sql, Object...conditions) {
		Map<String, String> ris = null;
		
		try {
			ResultSet rs = executeQuery(sql, conditions);
			
			if (rs.next()) {
				ris = mapFromRS(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ris;
	}
	
	/**
	 * Esegue una query impostando prima le condizioni.
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 */
	public void execute(String sql, Object...conditions) {
		try {
			preparedStm(sql, conditions).execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce un PreparedStatement che è in grado di fornire l'id dell'inserimento.
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 * @return Il PreparedStatement contenente la query con i placeholders sostituiti.
	 * @throws SQLException
	 */
	private PreparedStatement preparedStatementWithGeneratedKey(String sql, Object...conditions) throws SQLException {
		PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		for (int i = 0; i < conditions.length; i++) {
			stm.setObject(i + 1, conditions[i]);
		}
		
		return stm;
	}
	
	/**
	 * Metodo che effettua una insert e restituisce l'id che viene auto generato dal DB.
	 * @param sql -> La query da inviare.
	 * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
	 * @return L'id generato dal DB per questo insert.
	 */
	public int insertAndGetId(String sql, Object...conditions) {
		int id = 0;
		
		try {
			PreparedStatement stm = preparedStatementWithGeneratedKey(sql, conditions);
			
			stm.executeUpdate();
			
			ResultSet rs = stm.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	/**
     * Metodo che effettua una modifica al database e ritorna un boolean
     * true se la modifica è stata effettuata, false se la modifica non
     * è stata effettuata.
     * @param sql -> La query da inviare.
     * @param conditions -> Il/I valore/i da sostituire ai placeholders della query.
     * @return Un boolean che descrive se è stata effettuata la modifica.
     */
    public boolean executeAndIsModified(String sql, Object... conditions){
        int mod = 0;
        
        try {
            mod = preparedStm (sql, conditions).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if (mod != 0) {
            return true;
        }
        
        return false;
    }

}