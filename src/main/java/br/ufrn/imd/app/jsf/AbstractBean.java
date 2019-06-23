package br.ufrn.imd.app.jsf;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {

  /**
   * Forwards to another page keeping request information.
   *
   * @param uri the uri from resource
   */
  protected static String forward(String uri) {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      externalContext.redirect(buildInternalUri(uri));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Redirects to another page or resource
   *
   * @param uri the uri from the page or resource
   * @throws IllegalArgumentException if the uri empty
   * @throws NullPointerException if the uri is null
   */
  protected static String redirect(String uri) {
    if (uri == null) {
      throw new NullPointerException();
    } else if (uri.isEmpty()) {
      throw new IllegalArgumentException();
    }

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {

      String finalUri;
      if (uri.charAt(0) == '/') {
        finalUri = buildInternalUri(uri);
      } else {
        finalUri = uri;
      }

      externalContext.redirect(finalUri);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String buildInternalUri(String uri) {
    return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
        + uri
        + ".xhtml";
  }
}
