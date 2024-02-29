package org.isscorp.yeahttp.test.classes;

import org.isscorp.yeahttp.annotations.Get;

/**
 * This interface is used for testing
 */
public interface GetDogsInfo {

  /**
   * @return a {@link String} containing all the hounds breeds from dog.ceo api
   */
  @Get(endpoint = "https://dog.ceo/api/breed/hound/list")
  DogBreed getAllHoundsBreeds();

}
