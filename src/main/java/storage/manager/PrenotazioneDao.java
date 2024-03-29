package storage.manager;

import business.prenotazioni.PrenotazioneBean;
import business.prenotazioni.QRCode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import storage.interfaces.PrenotazioneInterface;

/**
 * Classe DAO per la gestione di PrenotazioneBean.
 */
public class PrenotazioneDao implements PrenotazioneInterface<PrenotazioneBean<String>> {

  /**
   * Metodo da utilizzare per prelevare una singola riga dal database ed inserirla in un bean.
   *
   * @param id id della prenotazione da ricercare
   * @pre id e' non null
   * @post se l'entita' esiste nel database il valore di ritorno e' diverso da null
   * @category Ricerca la prenotazione in base all'id della prenotazione
   */
  @Override
  public PrenotazioneBean<String> doRetrieveByKey(String id) throws SQLException {
    PrenotazioneBean<String> bean = new PrenotazioneBean<>();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM prenotazione WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, id);
      System.out.println("DoRetrieveByKey" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        return bean;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Metodo da utilizzare per prelevare tutte le entry di un elemento in una tabella.
   *
   * @post se la table corrispondente contiene entita', la lista di ritorno non e' vuota
   * @category Ritorna tutte le prenotazioni
   */
  @Override
  public Collection<PrenotazioneBean<String>> doRetrieveAll() throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM prenotazione";
    ArrayList<PrenotazioneBean<String>> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      System.out.println("DoRetriveAll" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        PrenotazioneBean<String> bean = new PrenotazioneBean<>();
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        collection.add(bean);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    return collection;
  }

  /**
   * Metodo utilizzato per salvare i valori contenuti in un bean all'interno di una tabella.
   *
   * @param bean Prenotazione da salvare
   * @pre bean e' una PrenotazioneBean valida e non null
   * @post bean e' reso persistente nel database
   * @category Salva una prenotazione nel database
   */
  @Override
  public void doSave(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "INSERT INTO prenotazione VALUES(?,?,?,?,?,?)";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getIdentificativo().getIdentificativo());
      statement.setString(2, bean.getEmail());
      statement.setDate(3, bean.getDataPrenotazione());
      statement.setInt(4, bean.getSala());
      statement.setInt(5, bean.getFasciaOraria());
      statement.setBoolean(6, bean.isEntrato());
      System.out.println("doSave=" + statement);
      statement.executeUpdate();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  /**
   * Metodo utilizzato per aggiornare i valori di un bean all'interno del database.
   *
   * @param bean Prenotazione con contenuto aggiornato
   * @pre bean e' una PrenotazioneBean valida, non null, gia' esistente nel database
   * @post l'entita' corrispondente nel database rispecchia lo stato di bean
   * @category Aggiorna una prenotazione
   */
  @Override
  public void doUpdate(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "UPDATE prenotazione SET dataPrenotazione=?, entrato = ? WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, bean.getDataPrenotazione());
      statement.setBoolean(2, bean.isEntrato());
      statement.setString(3, bean.getIdentificativo().getIdentificativo());
      System.out.println("doUpdate=" + statement);
      statement.executeUpdate();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  /**
   * Metodo utilizzato per eliminare una riga identificata da un bean all'interno del databse.
   *
   * @param bean Indica il bean da eliminare
   * @pre bean e' una PrenotazioneBean valida, non null, gia' esistente nel database
   * @post l'entita' corrispondente nel database viene eliminata
   * @category Cancella una prenotazione
   */
  @Override
  public void doDelete(PrenotazioneBean<String> bean) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "DELETE FROM prenotazione WHERE id=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setString(1, bean.getIdentificativo().getIdentificativo());
      System.out.println("doUpdate=" + statement);
      statement.executeUpdate();
      con.commit();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
  }

  /**
   * @param date         data della prenotazione
   * @param email        email del consumatore di cui si vuole cercare la prenotazione
   * @param fasciaOraria la fascia oraria in cui cercare la prenotazione
   * @return un PrenotazioneBean che ha dataPrenotazione, email e fasciaOraria corrispondenti ai
   * parametri passati
   * @throws SQLException se c'e' un errore nel dialogo col database
   * @pre date, email e fasciaOraria sono non null
   * @post se nel database e' presente una prenotazione con dataPrenotazione, email e fasciaOraria
   * corrispondenti ai parametri passati, il valore di ritorno e' non null
   */
  @Override
  public PrenotazioneBean<String> doRetrieveByDateAndFascia(
      Date date, String email, int fasciaOraria) throws SQLException {
    PrenotazioneBean<String> bean = new PrenotazioneBean<>();
    Connection con = null;
    PreparedStatement statement = null;
    String sql =
        "SELECT * FROM prenotazione WHERE dataPrenotazione=? and email=? and fasciaOraria=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, date);
      statement.setString(2, email);
      statement.setInt(3, fasciaOraria);
      System.out.println("doRetrieveByDateAndFascia" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        return bean;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * @param date  la data della prenotazione
   * @param email l'email della prenotazione
   * @return un PrenotazioneBean con data e email corrispondenti ai parametri passati
   * @throws SQLException se c'e' un errore nel dialogo col database
   * @pre date e email sono non null
   * @post se nel database e' presente una prenotazione con dataPrenotazione e email corrispondenti
   * ai parametri passati, il valore di ritorno e' non null
   */
  @Override
  public PrenotazioneBean<String> doRetrieveByDateAndMail(Date date, String email)
      throws SQLException {
    PrenotazioneBean<String> bean = new PrenotazioneBean<>();
    Connection con = null;
    PreparedStatement statement = null;
    String sql = "SELECT * FROM prenotazione WHERE dataPrenotazione=? and email=?";
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, date);
      statement.setString(2, email);
      System.out.println("doRetrieveByDateAndMail" + statement);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        return bean;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * @param date   la data delle prenotazioni da ricercare
   * @param sala   la sala delle prenotazioni da ricercare
   * @param fascia la fasciaOraria delle prenotazioni da ricercare
   * @return una collezione di PrenotazioneBean che hanno dataPrenotazione, sala e fasciaOraria
   * corrispondenti ai parametri passati
   * @throws SQLException se c'e' un errore nel dialogo col database
   * @pre date e' non null
   * @post se nel database sono presenti prenotazioni con dataPrenotazione, sala e fasciaOraria
   * corrispondenti ai parametri passati, il valore di ritorno e' non null
   */
  @Override
  public Collection<PrenotazioneBean<String>> doRetrieveByDateSalaFascia(
      Date date, int sala, int fascia) throws SQLException {
    Connection con = null;
    PreparedStatement statement = null;
    String sql =
        "SELECT * FROM prenotazione WHERE dataPrenotazione=? and sala=? and fasciaOraria=?";
    ArrayList<PrenotazioneBean<String>> collection = new ArrayList<>();
    try {
      con = DriverManagerConnectionPool.getConnection();
      statement = con.prepareStatement(sql);
      statement.setDate(1, date);
      statement.setInt(2, sala);
      statement.setInt(3, fascia);
      System.out.println("doRetrieveByDateSalaFascia" + statement);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        PrenotazioneBean<String> bean = new PrenotazioneBean<>();
        bean.setIdentificativo(new QRCode(rs.getString("id")));
        bean.setEmail(rs.getString("email"));
        bean.setDataPrenotazione(rs.getDate("dataPrenotazione"));
        bean.setSala(rs.getInt("sala"));
        bean.setFasciaOraria(rs.getInt("fasciaOraria"));
        bean.setEntrato(rs.getBoolean("entrato"));
        collection.add(bean);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {

      try {

        statement.close();
        DriverManagerConnectionPool.releaseConnection(con);

      } catch (SQLException e) {

        e.printStackTrace();
      }
    }
    return collection;
  }
}
