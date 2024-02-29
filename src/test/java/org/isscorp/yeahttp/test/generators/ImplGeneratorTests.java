package org.isscorp.yeahttp.test.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.isscorp.yeahttp.generators.ImplGenerator;
import org.isscorp.yeahttp.test.classes.GetCalls;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * unit testing for {@link org.isscorp.yeahttp.generators.ImplGenerator}
 */
class ImplGeneratorTests {

  private ImplGenerator<GetCalls> getCallsImplGenerator;

  // dummies

  @BeforeEach
  void setUp() {
    this.getCallsImplGenerator = new ImplGenerator<>(GetCalls.class);
  }

  @Test
  void argumentIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new ImplGenerator<>(null));
  }

  @Test
  void argumentIsNotInterface() {
    assertThrows(IllegalArgumentException.class, () -> new ImplGenerator<>(String.class));
  }

  @Test
  void testCheckImplementedClassType() {
    assertNotNull(this.getCallsImplGenerator.generateImplementation());
  }

  @Test
  void testWithFixedValue() {
    assertEquals("test", this.getCallsImplGenerator.generateImplementation().myFirstGet());
  }
}
