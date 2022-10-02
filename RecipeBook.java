import java.util.*;
import java.io.*;

public class RecipeBook {

private static Scanner obj;
	public static void main(String [] args) {	
        obj = new Scanner(System.in);
		System.out.println("Would you like to: (c)reate, (r)etrieve");
		String command = obj.nextLine();
        System.out.println(command);

		if (command.equals("c")) {
			RecipeCreation.createRecipe(); // create a recipe 
		}
		else if (command.equals("r")) { // retrieve recipe in two ways
			obj = new Scanner(System.in);
			System.out.println("Would you like to: (1) search for a recipe or (2) browse all recipes"); // ask user how to retrieve recipe
			String retrieve = obj.nextLine();
			retrieve = retrieve.toLowerCase();

			Recipes recipe;
			if (retrieve.equals("1")) { // search for a recipe 
				recipe = RecipeRetrieval.searchRecipes();
			}
			else if (retrieve.equals("2")) { // browse all recipes 
				recipe = RecipeRetrieval.browseRecipes();
			}

			RecipeReading.readRecipe(recipe); // read recipe in two eays
		}

    }
}
