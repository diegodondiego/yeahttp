package org.isscorp.yeahttp.test.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
import org.isscorp.yeahttp.generators.ImplGenerator;
import org.isscorp.yeahttp.test.classes.GetCalls;
import org.junit.jupiter.api.Test;

/**
 * unit testing for {@link org.isscorp.yeahttp.generators.ImplGenerator}
 */
class ImplGeneratorTests {

  // dummies

  @Test
  void argumentIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new ImplGenerator<>(null));
  }

  @Test
  void argumentIsNotInterface() {
    assertThrows(IllegalArgumentException.class, () -> new ImplGenerator<>(String.class));
  }

  @Test
  void checkImplementedClassType() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    assertNotNull(new ImplGenerator<>(GetCalls.class).generateImplementation());
  }

  @Test
  void testWithFixedValue() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    assertEquals("test", new ImplGenerator<>(GetCalls.class).generateImplementation().myFirstGet());
  }
}
