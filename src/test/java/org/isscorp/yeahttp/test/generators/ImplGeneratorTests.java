package org.isscorp.yeahttp.test.generators;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.isscorp.yeahttp.generators.ImplGenerator;
import org.isscorp.yeahttp.test.classes.GetDogsInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * unit testing for {@link org.isscorp.yeahttp.generators.ImplGenerator}
 */
class ImplGeneratorTests {

  private ImplGenerator<GetDogsInfo> getCallsImplGenerator;

  // dummies

  @BeforeEach
  void setUp() {
    this.getCallsImplGenerator = new ImplGenerator<>(GetDogsInfo.class);
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
    assertTrue(Arrays.asList(this.getCallsImplGenerator.generateImplementation().getAllHoundsBreeds().message()).contains("afghan"));
  }
}
