package com.example.recipe;

import java.util.*;

public interface RecipeRepository {
    ArrayList<Recipe> getAllRecipes();

    Recipe getRecipeById(int recipeId);

    Recipe addRecipe(Recipe recipe);

    Recipe updateRecipe(int recipeId, Recipe recipe);

    Void deleteRecipe(int recipeId);

}