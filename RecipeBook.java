import java.util.*;
import java.io.*;

public class RecipeBook {

	private static Scanner obj;
	
	public static void main(String [] args) {	
               
		 while(true) {

			// retrieve recipes already in recipes.txt file
			Recipes recipe = new Recipes();
			ArrayList<Recipes> recipeList = new ArrayList<Recipes> ();	
			RecipeRetrieval data = new RecipeRetrieval();
			ArrayList<Recipes> hold = data.LoadRecipes();
			recipeList = hold;
		
			// ask user for command
			obj = new Scanner(System.in);

			System.out.println("-------------- Recipe Book --------------");
			System.out.println("1. Create a recipe");
			System.out.println("2. Retrieve a recipe");
			System.out.println("3. Modify");
			System.out.println("4. Exit");
			System.out.println();
			System.out.print("Number choice: ");

			String command = obj.nextLine();

			System.out.println();
			boolean main_menu = true;

			// execute user command
			if (command.equals("1")) {
				RecipeCreation.createRecipe(); // create a recipe 
			}

			else if (command.equals("2")) { // retrieve recipe in two ways
				while(true) {
					System.out.println("-------------- Recipe Retrieval --------------");
					System.out.println("1. View all recipes");
					System.out.println("2. Search for a recipe");
					System.out.println("3. Exit");
					System.out.println("");
					System.out.print("Please type 1 or 2 or 3: ");

					String retrieve = obj.nextLine();
					retrieve = retrieve.toLowerCase();
					
					System.out.println();

					if (retrieve.equals("1")) { // browse all recipes 
						recipe = RecipeRetrieval.displayAllRecipes(recipeList);
						if (recipe == null) {
							main_menu = false;
						}
						break;
					}
					else if (retrieve.equals("2")) { // search for a recipe 
						recipe = RecipeRetrieval.searchForRecipe(recipeList, 1);
						if (recipe == null) {
							main_menu = false;
						}
						break;
					}
					else if (retrieve.equals("3")) { // exit back to main menu
						main_menu = false;
						break;
					}
					else {
						System.out.println("Error: Invalid input. Please try again.\n");
					}
				}
				
				// display recipe in two ways
				while(main_menu) {
					System.out.println("-------------- Recipe Reading --------------");
					System.out.println("1. Read entire recipe");
					System.out.println("2. Read instructions only");
					System.out.println("3. Exit");
					System.out.println("");
					System.out.print("Please type 1 or 2 or 3: ");
	
					String read = obj.nextLine();
					read = read.toLowerCase();
	
					System.out.println();

					if (read.equals("1")) {
						RecipeReading.readEntire(recipe); // read entire recipe
						break; 
					}
					else if (read.equals("2")) {
						RecipeReading.readSteps(recipe); // step through instructions
						break;
					}
					else if (read.equals("3")) {
						break;
					}
					else {
						System.out.println("Error: Invalid input. Please try again.\n");
					}
				}
				
			} else if (command.equals("3")) { // modify menu
				while (true) {
					System.out.println("-------------- Modify Recipes --------------");
					System.out.println("1. Edit an existing recipe");
					System.out.println("2. Delete a recipe");
					System.out.println("3. Exit");
					System.out.println("");
					System.out.print("Please type 1 or 2 or 3: ");

					String read = obj.nextLine();
					read = read.toLowerCase();
					System.out.println();

					if (read.equals("1")) { // user wants to edit an existing recipe
						System.out.println("Edit section\n");
						break; 
					}
					else if (read.equals("2")) {  // user wants to delete an existing recipe
						// now look to see if that recipe exists, if not then let them know it does not
						recipe = RecipeRetrieval.searchForRecipe(recipeList, 2);
						if (recipe == null) {
							break;
						} else {
							RecipeRetrieval.deleteRecipe(recipeList, recipe);
						}
					}
					else if (read.equals("3")) {
						break;
					}
					else {
						System.out.println("Error: Invalid input. Please try again.\n");
					}
				}


			} else if (command.equals("4")) { // exit
				break;

			
			} else { // invalid number choice
				System.out.println("Error: Invalid input. Please try again.\n");
			}
		}
	
	obj.close();

    }
}
