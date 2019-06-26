package br.ufrn.imd.app.jsf;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public abstract class AbstractBean implements Serializable {

  private static final Logger logger = Logger.getLogger(AbstractBean.class.getCanonicalName());

  @Inject private MessageBean message;

  /**
   * Forwards to another page keeping request information.
   *
   * @param uri the uri from resource
   * @return a null for page view reloading
   */
  protected static String forward(String uri) {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    String internalUri = buildInternalUri(uri, false);
    try {
      externalContext.dispatch(internalUri);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return internalUri;
  }

  /**
   * Redirects to another page or resource.
   *
   * @param uri the uri from the page or resource
   * @throws IllegalArgumentException if the uri empty
   * @throws NullPointerException if the uri is null
   * @return a null for page view reloading
   */
  static String redirect(String uri) {
    if (uri == null) {
      throw new NullPointerException();
    } else if (uri.isEmpty()) {
      throw new IllegalArgumentException();
    }

    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      if (uri.charAt(0) == '/') {
        String internalUri = buildInternalUri(uri, true);
        externalContext.redirect(internalUri);
        return internalUri;
      } else {
        externalContext.redirect(uri);
      }
    } catch (IOException e) {
      /* Mal-formed URI or there is no file at uri */
      logger.log(Level.WARNING, String.valueOf(e.getLocalizedMessage()));
    }
    return "";
  }

  private static String buildInternalUri(String uri, boolean isRedirect) {
    String internalUri =
        FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
            + uri
            + ".xhtml"
            + (isRedirect ? "?faces-redirect=true" : "");

    logger.log(Level.INFO, internalUri);
    return internalUri;
  }

  void showSuccessMessage(String msg) {
    message.setSuccess(msg);
  }

  void showErrorMessage(String msg) {
    message.setError(msg);
  }
}
