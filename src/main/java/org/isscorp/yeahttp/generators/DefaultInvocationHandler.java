package org.isscorp.yeahttp.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLConnection;
import org.isscorp.yeahttp.annotations.Get;

/**
 *
 */
public class DefaultInvocationHandler implements InvocationHandler {

  @Override
  public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {

    final var yeahAnnotation = method.getAnnotation(Get.class);
    if (null == yeahAnnotation) {
      throw new IllegalArgumentException("Interface " + proxy.getClass().getName() + "doesn't have "
          + "annotations");
    }

    final var endpoint = yeahAnnotation.endpoint();

    final var getURL = new URI(endpoint).toURL();
    final URLConnection connection = getURL.openConnection();
    connection.setRequestProperty("accept", "application/json");
    connection.connect();

    return new ObjectMapper().readValue(connection.getInputStream(), method.getReturnType());
  }
}
