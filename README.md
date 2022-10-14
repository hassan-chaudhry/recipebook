# Hell's Kitchen Recipe Book

In Hell's Kitchen Recipe Book, users are allowed to create new recipes, retrieve pre-existing recipes, and modify pre-existing recipes. 
There are TWO main files which the user can compile: RecipeBook.java and RecipeBookGUI.java. The former is a command-line version of the program, and the latter incorporates a GUI via Java Swing.

## Table of Contents
1. RecipeBook.java
2. RecipeBookGUI.java

### 1. RecipeBook.java
This is a command-line version of the program.

#### Create New Recipe

When the program starts running, it'll first ask if the user wants to create a new recipe, look at the pre-existing recipes, modify a recipe, or exit the program. Here, we'll show an example of the user choosing to create a new recipe.

* When the program asks if the user wants to Create, Retrieve, Modify, or Exit, the user should type `1` in order to <u>start creating a  new recipe</u>.
* Then the program will walk the user through the process of Recipe Creation. The user can type in the Name, Description, Ingredients and Steps, following the instructions given. 
    * Note than when typing the ingredients and steps, the user should press `ENTER` each time an ingredient or a step is typed. When the user has typed in all the ingredients or steps, press `ENTER` twice.
    * The capitalization of the first letter is not required, as the program will do it automatically. 

* Sample of Recipe Creation: 

<img width="442" alt="recipecreation" src="https://user-images.githubusercontent.com/82198103/195940368-f737e012-11a9-4dfa-bd0b-23c43cc6d8af.png">

#### Retrieve Existing Recipes

When the program starts running, it'll first ask if the user wants to create a new recipe, look at the pre-existing recipes, modify a recipe, or exit the program. Here, we'll show an example of the user choosing to retrieving recipes that are already created.

* When the program asks if the user wants to Create, Retrieve, Modify, or Exit, the user should type `2` in order to <u>retrieve and view all the pre-existing recipes</u>.
* Then the program will display a menu including 3 options: browse all recipes, search for a recipe by its exact name, search for a recipe by a partial name, or exit.

<img width="333" alt="reciperetrieval1" src="https://user-images.githubusercontent.com/82198103/195941385-41bb844a-817d-4704-85bc-b25c50f99ff7.png">

##### View all recipes

* To view all the recipes as a list, the user should type `1`. Then, the user can select a recipe out of the ones listed.

<img width="349" alt="reciperetrieval2" src="https://user-images.githubusercontent.com/82198103/195941730-1841dc50-a0a5-4b80-bb9d-da757e86ff1c.png">


##### Search for a recipe

* To view a recipe based on the exact name, the user should type `2.` To view a recipe based on a partial name, the user should type `3.` Let's take a look at the former method.
* It'll ask the user to enter a name of the recipe. If there's a recipe under such name in the database, it will be displayed. 
    * The user can choose the display mode of the recipe, either <u>viewing all steps at once</u> or <u>view it step by step</u>.

###### View all steps


<img width="621" alt="recipereading1" src="https://user-images.githubusercontent.com/82198103/195942422-2abf13d7-01cc-4ad1-9802-7d51e19851b8.png">

###### View the recipe step by step

<img width="566" alt="recipereading2" src="https://user-images.githubusercontent.com/82198103/195942439-0fc05f0e-a766-4b17-b431-1581d1164fd3.png">

#### Modify a recipe
When the program starts running, it'll first ask if the user wants to create a new recipe, look at the pre-existing recipes, modify a recipe, or exit the program. Here, we'll show an example of the user choosing to modify a recipe that is already created.

* To modify a recipe the user should type `3.` The user has the option of changing a recipe or deleting it altogether.

###### Changing a recipe
* The user selects the recipe they would like to change. Then, they enter in the new information for the recipe. If there is something they don't want to change, they can leave the field blank. Let's take a look at an example where we change the name of "Mac and Cheese" to "Spicy Mac and Cheese."

<img width="680" alt="recipemodify1" src="https://user-images.githubusercontent.com/82198103/195943743-bc3ab87d-858b-4e55-ad91-cbe6e2606b37.png">

###### Deleting a recipe
* The user selects the recipe they would like to delete.

<img width="411" alt="recipemodify2" src="https://user-images.githubusercontent.com/82198103/195943757-9af7fde9-8624-42f6-a581-fab702374edb.png">

#### Exit

* In the end, the user can type `4` to <u>exit the interface.</u>

<img width="304" alt="recipeend" src="https://user-images.githubusercontent.com/82198103/195943843-274c1458-e7a4-4cfa-82e1-56fffe18b7fc.png">
