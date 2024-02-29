package org.isscorp.yeahttp.generators;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 */
public class DefaultInvocationHandler implements InvocationHandler {

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

    final var declaredAnnotations = method.getDeclaredAnnotations();
    Arrays.stream(declaredAnnotations)
        //.filter(annotation -> annotation.annotationType().isAssignableFrom(YeahttpAnnotations.class))
        .findFirst().ifPresent(yeahttpAnnotation -> System.out.println(method.getName()));

    return "test";
  }
}
