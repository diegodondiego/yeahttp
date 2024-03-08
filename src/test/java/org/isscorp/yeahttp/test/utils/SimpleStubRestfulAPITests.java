package org.isscorp.yeahttp.test.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Unit test to validate the simple implementation of a restful api to be used in unit tests
 */
class SimpleStubRestfulAPITests {

  static final int STUBS_API_DEFAULT_PORT = 8099;
  private static final SimpleStubRestfulAPI simpleStubRestfulAPI;

  static {
    try {
      simpleStubRestfulAPI = new SimpleStubRestfulAPI(STUBS_API_DEFAULT_PORT);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeAll
  static void beforeAll() throws IOException {
    simpleStubRestfulAPI.startServer();
  }

  @AfterAll
  static void afterAll() {
    simpleStubRestfulAPI.stopServer();
  }

  @Test
  void getHello() throws Exception {
    Assertions.assertEquals("oh! hello!", HttpClient.newHttpClient().send(HttpRequest.newBuilder(new URI(String.format("http://localhost:%d"
                + "/%s",
            STUBS_API_DEFAULT_PORT,
            "api/hello")))
        .GET().build(), HttpResponse.BodyHandlers.ofString()).body());
  }

}
