package br.ufrn.imd.app.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/** Keeps JPA utilities methods, as EntityManager generation etc. */
public final class JpaUtils {

  /** EntityManagerFactory with the Fiot Persistence Unit (fiot). */
//  private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("fiot");

  /**
   * Private Constructor, should not use as this as a static utility class.
   *
   * @deprecated should not be used
   */
  @Deprecated
  private JpaUtils() {
  }

  /**
   * Generates a EntityManager connection ready for persistence data manipulation.
   *
   * @return a Entity Manager ready
   */
//  public static @Produces EntityManager getEntityManager() {
//    return factory.createEntityManager();
//  }
}
