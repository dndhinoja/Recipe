/*
 * package com.example.Recipe.services;
 * 
 * import static org.mockito.Mockito.when;
 * 
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.mockito.Mock; import org.mockito.Mockito; import
 * org.mockito.MockitoAnnotations;
 * 
 * import com.example.Recipe.Domain.Recipe; import
 * com.example.Recipe.repositories.RecipeRepository;
 * 
 * class ImageServiceImplTest {
 * 
 * @Mock RecipeRepository recipeRepository;
 * 
 * ImageService imageService;
 * 
 * @BeforeEach void setUp() throws Exception {
 * MockitoAnnotations.initMocks(imageService); imageService = new
 * ImageServiceImpl(); }
 * 
 * @Test void test() { Recipe recipe = new Recipe(); recipe.setId(1L);
 * Optional<Recipe> recipeOptional = Optional.of(recipe);
 * when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional)
 * ;
 * 
 * 
 * 
 * }
 * 
 * }
 */