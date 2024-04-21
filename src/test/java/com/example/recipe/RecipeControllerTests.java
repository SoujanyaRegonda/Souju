package com.example.recipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@WebMvcTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeControllerTests {

        @Autowired
        private MockMvc mockMvc;

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer();

        @Test
        @Order(1)
        public void testGetRecipes() throws Exception {
                mockMvc.perform(get("/recipes"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(5)))

                                .andExpect(jsonPath("$[0].recipeId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].recipeName", Matchers.equalToIgnoringCase("Pasta")))
                                .andExpect(jsonPath("$[0].recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$[0].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Pasta"),
                                                Matchers.equalToIgnoringCase("Tomatoes"),
                                                Matchers.equalToIgnoringCase("Olive Oil"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Basil"))))

                                .andExpect(jsonPath("$[1].recipeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].recipeName", Matchers.equalToIgnoringCase("Chicken Curry")))
                                .andExpect(jsonPath("$[1].recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$[1].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Chicken"),
                                                Matchers.equalToIgnoringCase("Onion"),
                                                Matchers.equalToIgnoringCase("Tomato"),
                                                Matchers.equalToIgnoringCase("Ginger"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Spices"))))

                                .andExpect(jsonPath("$[2].recipeId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].recipeName", Matchers.equalToIgnoringCase("Sushi")))
                                .andExpect(jsonPath("$[2].recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$[2].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("sushi rice"),
                                                Matchers.equalToIgnoringCase("tuna fish"),
                                                Matchers.equalToIgnoringCase("seaweed"),
                                                Matchers.equalToIgnoringCase("wasabi"),
                                                Matchers.equalToIgnoringCase("ginger"))))

                                .andExpect(jsonPath("$[3].recipeId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].recipeName",
                                                Matchers.equalToIgnoringCase("Mushroom Risotto")))
                                .andExpect(jsonPath("$[3].recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$[3].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Rice"),
                                                Matchers.equalToIgnoringCase("Mushrooms"),
                                                Matchers.equalToIgnoringCase("Onion"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Butter"),
                                                Matchers.equalToIgnoringCase("Parmesan"))))

                                .andExpect(jsonPath("$[4].recipeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].recipeName", Matchers.equalToIgnoringCase("Fish and Chips")))
                                .andExpect(jsonPath("$[4].recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$[4].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Fish"),
                                                Matchers.equalToIgnoringCase("Potatoes"),
                                                Matchers.equalToIgnoringCase("floUr"),
                                                Matchers.equalToIgnoringCase("oil"),
                                                Matchers.equalToIgnoringCase("Spices"))));
        }

        @Test
        @Order(2)
        public void testgetNotfound() throws Exception {
                mockMvc.perform(get("/recipes/13")).andExpect(status().isNotFound());
        }

        @Test
        @Order(3)
        public void testGetRecipeById() throws Exception {
                mockMvc.perform(get("/recipes/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("Pasta")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Pasta"),
                                                Matchers.equalToIgnoringCase("Tomatoes"),
                                                Matchers.equalToIgnoringCase("Olive Oil"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Basil"))));

                mockMvc.perform(get("/recipes/2"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("Chicken Curry")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Chicken"),
                                                Matchers.equalToIgnoringCase("Onion"),
                                                Matchers.equalToIgnoringCase("Tomato"),
                                                Matchers.equalToIgnoringCase("Ginger"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Spices"))));

                mockMvc.perform(get("/recipes/3"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("Sushi")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("sushi rice"),
                                                Matchers.equalToIgnoringCase("tuna fish"),
                                                Matchers.equalToIgnoringCase("seaweed"),
                                                Matchers.equalToIgnoringCase("wasabi"),
                                                Matchers.equalToIgnoringCase("ginger"))));

                mockMvc.perform(get("/recipes/4"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("Mushroom Risotto")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Rice"),
                                                Matchers.equalToIgnoringCase("Mushrooms"),
                                                Matchers.equalToIgnoringCase("Onion"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Butter"),
                                                Matchers.equalToIgnoringCase("Parmesan"))));

                mockMvc.perform(get("/recipes/5"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("Fish and Chips")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Fish"),
                                                Matchers.equalToIgnoringCase("Potatoes"),
                                                Matchers.equalToIgnoringCase("Flour"),
                                                Matchers.equalToIgnoringCase("oil"),
                                                Matchers.equalToIgnoringCase("Spices"))));
        }

        @Test
        @Order(4)
        public void testPostRecipe() throws Exception {
                Recipe recipe = new Recipe(6, "pizza", "veg",
                                Arrays.asList("pizza dough", "onions", "mozzarella cheese", "vegetables",
                                                "tomato sauce"));

                String content = objectWriter.writeValueAsString(recipe);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/recipes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("pizza")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("pizza dough"),
                                                Matchers.equalToIgnoringCase("onions"),
                                                Matchers.equalToIgnoringCase("mozzarella cheese"),
                                                Matchers.equalToIgnoringCase("vegetables"),
                                                Matchers.equalToIgnoringCase("tomato sauce"))));
        }

        @Test
        @Order(5)
        public void testAfterPost() throws Exception {

                mockMvc.perform(get("/recipes/6"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(6)))
                                .andExpect(jsonPath("$.recipeName", Matchers.equalToIgnoringCase("pizza")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("pizza dough"),
                                                Matchers.equalToIgnoringCase("onions"),
                                                Matchers.equalToIgnoringCase("mozzarella cheese"),
                                                Matchers.equalToIgnoringCase("vegetables"),
                                                Matchers.equalToIgnoringCase("tomato sauce"))));

        }

        @Test
        @Order(6)
        public void testPutNotfound() throws Exception {
                Recipe recipe = new Recipe(5, "Fish and Potato Chips", "non-veg",
                                Arrays.asList("fish", "potatoes", "flour", "oil", "spices", "water"));
                String content = objectWriter.writeValueAsString(recipe);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/recipes/19")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());
        }

        @Test
        @Order(7)
        public void testPut() throws Exception {
                Recipe recipe = new Recipe(5, "Fish and Potato Chips", "non-veg",
                                Arrays.asList("fish", "potatoes", "flour", "oil", "spices", "water"));
                String content = objectWriter.writeValueAsString(recipe);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/recipes/5")
                                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest).andExpect(status().isOk())
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.recipeName",
                                                Matchers.equalToIgnoringCase("Fish and Potato Chips")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Fish"),
                                                Matchers.equalToIgnoringCase("Potatoes"),
                                                Matchers.equalToIgnoringCase("Flour"),
                                                Matchers.equalToIgnoringCase("oil"),
                                                Matchers.equalToIgnoringCase("Spices"),
                                                Matchers.equalToIgnoringCase("water"))));
        }

        @Test
        @Order(8)
        public void testAfterPut() throws Exception {
                mockMvc.perform(get("/recipes/5")).andExpect(status().isOk())
                                .andExpect(jsonPath("$.recipeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$.recipeName",
                                                Matchers.equalToIgnoringCase("Fish and Potato Chips")))
                                .andExpect(jsonPath("$.recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$.ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Fish"),
                                                Matchers.equalToIgnoringCase("Potatoes"),
                                                Matchers.equalToIgnoringCase("Flour"),
                                                Matchers.equalToIgnoringCase("oil"),
                                                Matchers.equalToIgnoringCase("Spices"),
                                                Matchers.equalToIgnoringCase("water"))));
        }

        @Test
        @Order(9)
        public void testDeleteNotfound() throws Exception {
                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/recipes/99")
                                .contentType(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest).andExpect(status().isNotFound());

        }

        @Test
        @Order(10)
        public void testDelete() throws Exception {
                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/recipes/6")
                                .contentType(MediaType.APPLICATION_JSON);
                mockMvc.perform(mockRequest).andExpect(status().isNoContent());
        }

        @Test
        @Order(11)
        public void testAfterDelete() throws Exception {
                mockMvc.perform(get("/recipes"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(5)))

                                .andExpect(jsonPath("$[0].recipeId", Matchers.equalTo(1)))
                                .andExpect(jsonPath("$[0].recipeName", Matchers.equalToIgnoringCase("Pasta")))
                                .andExpect(jsonPath("$[0].recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$[0].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Pasta"),
                                                Matchers.equalToIgnoringCase("Tomatoes"),
                                                Matchers.equalToIgnoringCase("Olive Oil"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Basil"))))

                                .andExpect(jsonPath("$[1].recipeId", Matchers.equalTo(2)))
                                .andExpect(jsonPath("$[1].recipeName", Matchers.equalToIgnoringCase("Chicken Curry")))
                                .andExpect(jsonPath("$[1].recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$[1].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Chicken"),
                                                Matchers.equalToIgnoringCase("Onion"),
                                                Matchers.equalToIgnoringCase("Tomato"),
                                                Matchers.equalToIgnoringCase("Ginger"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Spices"))))

                                .andExpect(jsonPath("$[2].recipeId", Matchers.equalTo(3)))
                                .andExpect(jsonPath("$[2].recipeName", Matchers.equalToIgnoringCase("Sushi")))
                                .andExpect(jsonPath("$[2].recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$[2].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("sushi rice"),
                                                Matchers.equalToIgnoringCase("tuna fish"),
                                                Matchers.equalToIgnoringCase("seaweed"),
                                                Matchers.equalToIgnoringCase("wasabi"),
                                                Matchers.equalToIgnoringCase("ginger"))))

                                .andExpect(jsonPath("$[3].recipeId", Matchers.equalTo(4)))
                                .andExpect(jsonPath("$[3].recipeName",
                                                Matchers.equalToIgnoringCase("Mushroom Risotto")))
                                .andExpect(jsonPath("$[3].recipeType", Matchers.equalToIgnoringCase("veg")))
                                .andExpect(jsonPath("$[3].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Rice"),
                                                Matchers.equalToIgnoringCase("Mushrooms"),
                                                Matchers.equalToIgnoringCase("Onion"),
                                                Matchers.equalToIgnoringCase("Garlic"),
                                                Matchers.equalToIgnoringCase("Butter"),
                                                Matchers.equalToIgnoringCase("Parmesan"))))

                                .andExpect(jsonPath("$[4].recipeId", Matchers.equalTo(5)))
                                .andExpect(jsonPath("$[4].recipeName",
                                                Matchers.equalToIgnoringCase("Fish and Potato Chips")))
                                .andExpect(jsonPath("$[4].recipeType", Matchers.equalToIgnoringCase("non-veg")))
                                .andExpect(jsonPath("$[4].ingredients", Matchers.containsInAnyOrder(
                                                Matchers.equalToIgnoringCase("Fish"),
                                                Matchers.equalToIgnoringCase("Potatoes"),
                                                Matchers.equalToIgnoringCase("Flour"),
                                                Matchers.equalToIgnoringCase("oil"),
                                                Matchers.equalToIgnoringCase("Spices"),
                                                Matchers.equalToIgnoringCase("water"))));

        }

}