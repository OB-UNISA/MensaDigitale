package business.richieste;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import business.consumatore.ConsumatoreBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.manager.ConsumatoreDao;
import storage.manager.RichiestaDao;

class SubmitRichiestaServletTest {
  private static RichiestaDao richiestaDao = new RichiestaDao();
  private static ServletContext ctx = Mockito.mock(ServletContext.class);
  private static HttpSession session = Mockito.mock(HttpSession.class);
  private static HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  private static int nRichieste;
  private static ConsumatoreBean studente = new ConsumatoreBean("m.rossi999@studenti.unisa.it",
      "Mario", "Rossi", 1, "", null, "", "", "", "", "", "", false, false, 0, 0);
  private static ConsumatoreDao consumatoreDao = new ConsumatoreDao();

  private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  private SubmitRichiestaServlet servlet = new SubmitRichiestaServlet() {
    public ServletContext getServletContext() {
      return ctx;
    }
  };

  @BeforeAll
  public static void init() throws SQLException {
    Mockito.doReturn(session).when(request).getSession();
    Collection<RichiestaBean> listaRichieste = richiestaDao.doRetrieveAll();
    nRichieste = listaRichieste.size();
    Mockito.doReturn("true").when(request).getParameter("prelazione");
    Mockito.doReturn("true").when(request).getParameter("responsabilita");
    Mockito.doReturn(studente).when(session).getAttribute("utente");
    consumatoreDao.doSave(studente);
  }

  @AfterAll
  public static void destroy() throws SQLException {
    consumatoreDao.doDelete(studente);
  }

  @Test
  void tc_ars_1_1() {
    /*
     * Lunghezza cognome non rispettata
     */
    String cognome = "A";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_2() {
    /*
     * Lunghezza cognome ok, formato cognome non rispettato
     */
    String cognome = "Rossi123";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_3() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome non rispettato
     */
    String cognome = "Rossi";
    String nome = "M";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_4() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario123";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_5() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "30/02/2020";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_6() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "Napoli";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_7() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "VZ";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_8() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "N";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_9() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napol1";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_10() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA20B99";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_11() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "UnaCittadinanzaMoltoLungaSoloPerFarFallireQuestoTestCase";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_12() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana12";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_13() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_14() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono non
     * rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "08158439384872189231816";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_15() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono ok,
     * lunghezza cellulare non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "0815849942";
    String cellulare = "21388231823189213892139812389";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_16() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono ok,
     * lunghezza cellulare ok, lunghezza email non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "0815849942";
    String cellulare = "393338597471";
    String email = "ab";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_17() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono ok,
     * lunghezza cellulare ok, lunghezza email ok, formato email non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "0815849942";
    String cellulare = "393338597471";
    String email = "rossimario.it";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_18() {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono ok,
     * lunghezza cellulare ok, lunghezza email ok, formato email ok, ripeti email non rispettato
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "0815849942";
    String cellulare = "393338597471";
    String email = "m.rossi999@studenti.unisa.it";
    String confermaEmail = "m.rossi@studenti.unisa.it";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      Mockito.doReturn(confermaEmail).when(request).getParameter("confermaEmail");
      assertThrows(IllegalArgumentException.class, () -> servlet.doPost(request, response));
    } finally {
      System.out.println("");
    }
  }

  @Test
  void tc_ars_1_19() throws ServletException, IOException, SQLException {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza email ok, formato
     * email ok, ripeti email ok
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = null;
    String cellulare = null;
    String email = "m.rossi999@studenti.unisa.it";
    String confermaEmail = "m.rossi999@studenti.unisa.it";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      Mockito.doReturn(confermaEmail).when(request).getParameter("confermaEmail");

      servlet.doPost(request, response);
      assertTrue((richiestaDao.doRetrieveAll()).size() > nRichieste);
    } finally {
      for (RichiestaBean r : richiestaDao.doRetrieveAll()) {
        richiestaDao.doDelete(r);
      }
      nRichieste = 0;
    }
  }

  @Test
  void tc_ars_1_20() throws ServletException, IOException, SQLException {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono ok,
     * lunghezza email ok, formato email ok, ripeti email ok
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "0815849942";
    String cellulare = null;
    String email = "m.rossi999@studenti.unisa.it";
    String confermaEmail = "m.rossi999@studenti.unisa.it";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      Mockito.doReturn(confermaEmail).when(request).getParameter("confermaEmail");

      servlet.doPost(request, response);
      assertTrue((richiestaDao.doRetrieveAll()).size() > nRichieste);
    } finally {
      for (RichiestaBean r : richiestaDao.doRetrieveAll()) {
        richiestaDao.doDelete(r);
      }
      nRichieste = 0;
    }
  }

  @Test
  void tc_ars_1_21() throws ServletException, IOException, SQLException {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza cellulare ok,
     * lunghezza email ok, formato email ok, ripeti email ok
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = null;
    String cellulare = "393338597471";
    String email = "m.rossi999@studenti.unisa.it";
    String confermaEmail = "m.rossi999@studenti.unisa.it";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      Mockito.doReturn(confermaEmail).when(request).getParameter("confermaEmail");

      servlet.doPost(request, response);
      assertTrue((richiestaDao.doRetrieveAll()).size() > nRichieste);
    } finally {
      for (RichiestaBean r : richiestaDao.doRetrieveAll()) {
        richiestaDao.doDelete(r);
      }
    }
  }

  @Test
  void tc_ars_1_22() throws ServletException, IOException, SQLException {
    /*
     * Lunghezza cognome ok, formato cognome ok, lunghezza nome ok, formato nome ok, formato data di
     * nascita ok, lunghezza provincia di nascita ok, formato provincia di nascita ok, lunghezza
     * comune di nascita ok, formato comune di nascita ok, formato codice fiscale ok, lunghezza
     * cittadinanza ok, formato cittadinanza ok, lunghezza indirizzo ok, lunghezza telefono ok,
     * lunghezza cellulare ok, lunghezza email ok, formato email ok, ripeti email ok
     */
    String cognome = "Rossi";
    String nome = "Mario";
    String dataDiNascita = "20/02/1999";
    String provinciaDiNascita = "NA";
    String comuneDiNascita = "Napoli";
    String codiceFiscale = "RSSMRA99B20F839J";
    String cittadinanza = "Italiana";
    String indirizzo = "via Roma 123";
    String telefono = "0815849942";
    String cellulare = "393338597471";
    String email = "m.rossi999@studenti.unisa.it";
    String confermaEmail = "m.rossi999@studenti.unisa.it";
    try {
      Mockito.doReturn(cognome).when(request).getParameter("cognome");
      Mockito.doReturn(nome).when(request).getParameter("nome");
      Mockito.doReturn(dataDiNascita).when(request).getParameter("dataDiNascita");
      Mockito.doReturn(provinciaDiNascita).when(request).getParameter("provinciaDiNascita");
      Mockito.doReturn(comuneDiNascita).when(request).getParameter("comuneDiNascita");
      Mockito.doReturn(codiceFiscale).when(request).getParameter("codiceFiscale");
      Mockito.doReturn(cittadinanza).when(request).getParameter("cittadinanza");
      Mockito.doReturn(indirizzo).when(request).getParameter("indirizzo");
      Mockito.doReturn(telefono).when(request).getParameter("telefono");
      Mockito.doReturn(cellulare).when(request).getParameter("cellulare");
      Mockito.doReturn(email).when(request).getParameter("email");
      Mockito.doReturn(confermaEmail).when(request).getParameter("confermaEmail");

      servlet.doPost(request, response);
      assertTrue((richiestaDao.doRetrieveAll()).size() > nRichieste);
    } finally {
      for (RichiestaBean r : richiestaDao.doRetrieveAll()) {
        richiestaDao.doDelete(r);
      }
      nRichieste = 0;
    }
  }


}
