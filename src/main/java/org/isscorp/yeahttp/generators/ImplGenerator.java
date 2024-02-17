package org.isscorp.yeahttp.generators;

import java.lang.reflect.Proxy;

/**
 * This class is the main class to generate the implementations of the annotated objects
 * TODO rename it for a better name
 */
public class ImplGenerator<T> {

  private final Class<T> annotatedInterface;

  public ImplGenerator(final Class<T> annotatedInterface) {
    this.annotatedInterface = annotatedInterface;
    if (annotatedInterface == null || !annotatedInterface.isInterface()) {
      throw new IllegalArgumentException("object " + annotatedInterface + "is not an interface");
    }
  }

  public T generateImplementation() {

    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{this.annotatedInterface},
        new DefaultInvocationHandler());
  }

}
