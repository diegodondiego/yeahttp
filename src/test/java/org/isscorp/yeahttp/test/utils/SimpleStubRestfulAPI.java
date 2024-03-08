package org.isscorp.yeahttp.test.utils;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Simple restful API to return stub data to be used in tests
 */
public class SimpleStubRestfulAPI {

  private final HttpServer restfulServer;

  private final Map<String, String> methodByKey;

  /**
   * Generates the restful api server with the port informed as parameter
   */
  public SimpleStubRestfulAPI(final int port) throws IOException {
    // TODO check if the port is in used somehow
    this.restfulServer = HttpServer.create(new InetSocketAddress(port), 0);
    this.methodByKey = new TreeMap<>();
    this.methodByKey.put("GET_HELLO", "/api/hello");
    this.createStubEndpoints();
  }

  /**
   * creates all the stub endpoints based on the field map {@link SimpleStubRestfulAPI#methodByKey}
   */
  private void createStubEndpoints() {

    this.methodByKey.forEach((key, endpoint) -> {
      this.restfulServer.createContext(endpoint, (exchange -> {

        if (key.contains(exchange.getRequestMethod())) {
          final String responseText = "oh! hello!";
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, responseText.getBytes(StandardCharsets.UTF_8).length);
          final OutputStream output = exchange.getResponseBody();
          output.write(responseText.getBytes(StandardCharsets.UTF_8));
          output.flush();
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, -1);
        }
      }));
    });

  }


  public String getExposedEndpointByKey(final String key) {
    return Optional.ofNullable(this.methodByKey.get(key))
        .orElseThrow(() -> new IllegalArgumentException(String.format("no endpoint created "
            + "for key %s", key)));
  }

  /**
   * method to start the server
   */
  public void startServer() {
    this.restfulServer.start();
  }

  /**
   * method to stop the server
   */
  public void stopServer() {
    this.restfulServer.stop(2);
  }
}
