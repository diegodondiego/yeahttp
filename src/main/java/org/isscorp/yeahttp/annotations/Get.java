package org.isscorp.yeahttp.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation indicates that a method is a HTTP get call
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Get {

  /**
   * the endpoint to be called
   *
   * @return
   */
  String endpoint();

  // TODO maybe a specific timeout?

}
