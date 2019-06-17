package br.ufrn.imd.app.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** Keeps JPA utilities methods, as EntityManager generation etc. */
public final class JPAUtils {

  /** EntityManagerFactory with the Fiot Persistence Unit (fiotPU). */
  private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("fiotPU");

  /**
   * Private Constructor, should not use as this as a static utility class.
   *
   * @deprecated should not be used
   */
  @Deprecated
  private JPAUtils() {}

  /**
   * Generates a EntityManager connection ready for persistence data manipulation.
   *
   * @return a Entity Manager ready
   */
  public static @Produces EntityManager getEntityManager() {
    return factory.createEntityManager();
  }
}
