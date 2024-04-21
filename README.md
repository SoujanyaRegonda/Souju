Given four files `RecipeController.java`, `RecipeRepository.java`, `RecipeService.java` and  `Recipe.java`.
The `RecipeService` class has a variable named `recipeBook`, which is a HashMap that holds `Integer`s as keys and `Recipe` objects as values. Initially, it contains data of 5 recipes.

### Completion Instructions

- `Recipe.java`: The `Recipe` class should contain the following attributes.

    | Attribute   | Type         | Description                                                     |
    | ----------- | ------------ | --------------------------------------------------------------- |
    | recipeId    | int          | An integer that represents the unique ID of the recipe          |
    | recipeName  | String       | A string that represents the name of the recipe                 |
    | recipeType  | String       | A string that represents the type(veg or non-veg) of the recipe |
    | ingredients | List<String> | A list of strings that represent the ingredients of the recipe. |

- `RecipeRepository.java`: Create an interface containing required methods.
- `ReceoueService.java`: Update the service class with logic for managing recipe data.
- `RecipeController.java`: Create the controller class to handle HTTP requests.  

Implement the following APIs.

### API 1

#### Path: `/recipes`

#### Method: `GET`

#### Description:

Returns a list of all recipes in the `recipeBook`.

#### Response

```
[
    {
        "recipeId": 1,
        "recipeName": "Pasta",
        "recipeType": "veg",
        "ingredients": [
            "pasta",
            "tomatoes",
            "olive oil",
            "garlic",
            "basil"
        ]
    },
   ...
]
```

### API 2

#### Path: `/recipes`

#### Method: `POST`

#### Description:

Creates a new recipe in the recipeBook. `recipeId` is auto-incremented

#### Request

```
{
 
    "recipeName": "Pizza",
    "recipeType": "veg",
    "ingredients": ["pizza dough", "onions", "mozzarella cheese","vegetables", "tomato sauce"]
}
```

#### Response

```
{
    "recipeId": 6,
    "recipeName": "Pizza",
    "recipeType": "veg",
    "ingredients": [
        "pizza dough",
        "onions",
        "mozzarella cheese",
        "vegetables",
        "tomato sauce"
    ]
 }
```

### API 3

#### Path: `/recipes/{recipeId}`

#### Method: `GET`

#### Description:

Returns a recipe based on a recipe ID. If the given recipe ID is not found in the `recipeBook`, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.

#### Response

```
{
    "recipeId": 5,
    "recipeName": "Fish and Chips",
    "recipeType": "non-veg",
    "ingredients": [
        "fish",
        "potatoes",
        "flour",
        "oil",
        "spices"
    ]
}
```

### API 4

#### Path: `/recipes/{recipeId}`

#### Method: `PUT`

#### Description:

Updates the details of a recipe in the recipeBook based on the recipe ID. If the given recipe ID is not found in the `recipeBook`, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.


#### Request

```
{
    "recipeName": "Fish and Potato Chips",
    "ingredients": ["fish","potatoes","flour","oil","spices","water"]
}
```

#### Response

```
{
    "recipeId": 5,
    "recipeName": "Fish and Potato Chips",
    "recipeType": "Non-veg",
    "ingredients": [
        "fish",
        "potatoes",
        "flour",
        "oil",
        "spices",
        "water"
    ]
}

```

### API 5

#### Path: `/recipes/{recipeId}`

#### Method: `DELETE`

#### Description:

Deletes a recipe from the recipeBook  based on the recipe ID. If the given recipe ID is not found in the `recipeBook`, raise `ResponseStatusException` with `HttpStatus.NOT_FOUND`.


**Do not modify the code in `RecipeApplication.java`**