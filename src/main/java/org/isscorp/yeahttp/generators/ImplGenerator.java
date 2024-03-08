package org.isscorp.yeahttp.generators;

import java.lang.reflect.Proxy;

/**
 * This class is the main class to generate the implementations of the annotated objects
 * TODO rename it for a better name
 *
 * @param <T> the type of the class to be enriched
 */
public class ImplGenerator<T> {

  private final Class<T> annotatedInterface;

  /**
   * @param annotatedInterface the type to be enriched with the capabilities of the framework
   */
  public ImplGenerator(final Class<T> annotatedInterface) {
    this.annotatedInterface = annotatedInterface;
    if (null == annotatedInterface || !annotatedInterface.isInterface()) {
      throw new IllegalArgumentException("object " + annotatedInterface + "is not an interface");
    }
  }

  /**
   * @return an implemented instance of T
   */
  public T generateImplementation() {

    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{this.annotatedInterface},
        new HTTPMethodsDefaultHandler());
  }

}
