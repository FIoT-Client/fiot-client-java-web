package br.ufrn.imd.app.jsf;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public abstract class AbstractBean implements Serializable {

  @Inject private MessageBean message;

  /**
   * Forwards to another page keeping request information.
   *
   * @param uri the uri from resource
   * @return a null for page view reloading
   */
  protected static String forward(String uri) {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    try {
      externalContext.redirect(buildInternalUri(uri, false));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Redirects to another page or resource.
   *
   * @param uri the uri from the page or resource
   * @throws IllegalArgumentException if the uri empty
   * @throws NullPointerException if the uri is null
   * @return a null for page view reloading
   */
  protected static String redirect(String uri) {
    if (uri == null) {
      throw new NullPointerException();
    } else if (uri.isEmpty()) {
      throw new IllegalArgumentException();
    }

    if (uri.charAt(0) == '/') {
      return buildInternalUri(uri, true);
    } else {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      try {
        externalContext.redirect(uri);
      } catch (IOException e) {
        /* Mal-formed URI or there is no file at uri */
        e.printStackTrace();
      }
      return "";
    }
  }

  private static String buildInternalUri(String uri, boolean isRedirect) {
    return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath()
        + uri
        + (isRedirect ? ".xhtml?faces-redirect=true" : "");
  }

  protected void showSuccessMessage(String msg) {
    message.setSuccess(msg);
  }

  protected void showErrorMessage(String msg) {
    message.setError(msg);
  }
}
