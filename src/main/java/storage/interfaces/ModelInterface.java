package storage.interfaces;

import java.sql.SQLException;
import java.util.Collection;

/*
 *@category L'interfaccia per la creazione di tutti i model
 *
 * @param <T> Il tipo di bean a cui il model fa riferimento
 */

public interface ModelInterface<T> {

  /**
   * Metodo da utilizzare per prelevare tutte le entry di un elemento in una tabella.
   *
   * @return Una collezione di bean contenenti tutte le entry non duplicate della tabella
   * @throws SQLException Eccezione lanciata da SQL
   */
  Collection<T> doRetrieveAll() throws SQLException;

  /**
   * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella.
   *
   * @param bean il bean da salvare nel database
   * @throws SQLException Eccezione lanciata da SQL
   */
  void doSave(T bean) throws SQLException;

  /**
   * Metodo utilizzato per aggiornare i valori di un bean all'interno del database.
   *
   * @param bean il bean da aggiornare
   * @throws SQLException Eccezione lanciata da SQL
   */
  void doUpdate(T bean) throws SQLException;

  /**
   * Metodo utilizzato per eliminare una riga identificata da un bean all'interno del databse.
   *
   * @param bean Il bean serve per eliminare la riga dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  void doDelete(T bean) throws SQLException;
}
