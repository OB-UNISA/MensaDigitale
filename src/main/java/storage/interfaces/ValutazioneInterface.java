package storage.interfaces;

import business.valutazioni.ValutazioneBean;
import java.sql.SQLException;

public interface ValutazioneInterface<T> extends ModelInterface<ValutazioneBean> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param email  La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @param piatto La chiave primaria dell'elemento della tabella a cui facciamo riferimento
   * @return Il bean dell'elemento preso dal database
   * @throws SQLException Eccezione lanciata da SQL
   */
  T doRetrieveByKey(String email, String piatto) throws SQLException;
}
