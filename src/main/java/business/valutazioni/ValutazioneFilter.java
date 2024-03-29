package business.valutazioni;

import business.consumatore.ConsumatoreBean;
import business.utente.Utente;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter per la valutazione.
 */
public class ValutazioneFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;

    Utente utente = (Utente) req.getSession().getAttribute("utente");

    if (utente == null) {
      // non e' loggato
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/login.jsp"));
      return;
    } else if (utente.getClass() != ConsumatoreBean.class
        || ((ConsumatoreBean) utente).getStatoServizi() != 1) {
      // non e' consumatore
      resp.sendRedirect(resp.encodeURL(req.getContextPath() + "/index.jsp"));
      return;
    }

    chain.doFilter(request, response);
  }
}
