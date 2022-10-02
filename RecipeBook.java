import java.util.*;
import java.io.*;

public class RecipeBook {

        private static Scanner obj;
        public static ArrayList<Recipes> recipeList = new ArrayList<Recipes> ();
            public static void main(String [] args) {	
                obj = new Scanner(System.in);
        
                // When the app initially starts, these lines of code will run so it
                // can open the recipes.txt file and get the recipes that are 
                // already created in the text file
                RecipeRetrieval data = new RecipeRetrieval();
                ArrayList<Recipes> hold = data.LoadRecipes();
                recipeList = hold;
        
                // this is how we are going to access all the methods from the RecipeRetrieval class
                RecipeRetrieval recipeRetrieval = new RecipeRetrieval();
        
        
                System.out.println("Would you like to: (c)reate, (r)etrieve");
                String command = obj.nextLine();
                while (!command.equals("c") && !command.equals("r")){
                    System.out.println("Invalid Input!");
                    command = obj.nextLine();
                }
                //System.out.println(command);
        
                if (command.equals("c")) {
                    RecipeCreation.createRecipe(); // create a recipe 
                    // System.out.println("Create tab opened");
                }
                else if (command.equals("r")) { // retrieve recipe in two ways
                    // recipeRetrieval.retrievalInterface(obj, recipeList);
                    // System.out.println("Would you like to: (1) search for a recipe or (2) browse all recipes"); // ask user how to retrieve recipe
                    // String retrieve = obj.nextLine();
                    // retrieve = retrieve.toLowerCase();
        
		            Recipes recipe = new Recipes();
        
                    while (true) {
                        System.out.println("-------------- Recipe Retrieval --------------");
                        System.out.println("1. View all recipes");
                        System.out.println("2. Search For a recipe");
                        System.out.println("3. Exit");
                        System.out.print("Please type 1 or 2 or 3: ");

                        int choice = 0;
                        while(obj.hasNextInt())  //循环输入整型数字
                        {  
                            choice = obj.nextInt();  
                            if (choice != 1 && choice != 2 && choice != 3) {
                                System.out.println("Sorry, incorrect input. Try again");
                                System.out.println();
                            } else {
                                break;
                            }
                        }
                        
                        obj.nextLine();
                        System.out.println();
                        if (choice != 1 && choice != 2 && choice != 3) {
                            System.out.println("Sorry, incorrect input. Try again");
                            System.out.println();
                        } else {
                            // user clicked option 1, view all recipes
                            if (choice == 1) {
                                recipeRetrieval.displayAllRecipes(recipeList);
                                System.out.println();
            
                            // user clicked option 2, search for a recipe
                            } else if (choice == 2) {
                                System.out.print("Name of recipe: ");
                                String searchName = obj.nextLine();
                                boolean ifFound = recipeRetrieval.searchForRecipe(recipeList, searchName);
                                if (ifFound) {
                                    System.out.println("Recipe was found!");
                                    // this for loop is just to get the object that matches the recipe name the user searched for
                                    for (int i = 0; i < recipeList.size(); i++) {
                                        Recipes current = recipeList.get(i);
                                        if (current.getName().equals(searchName)) {
                                            recipe = current;
                                            break;
                                        }
                                    }

                                        obj = new Scanner(System.in);
                                        System.out.println("Would you like to: (1) read entire recipe or (2) step through intructions"); // ask user how to read recipe
                                        String read = obj.nextLine();
                                        read = read.toLowerCase();

                                        String steps[] = recipe.getSteps().split("(?<=.  )"); // parsing steps and splitting them


                                        if (read.equals("1")) {
                                            System.out.printf("---------------- %s ----------------\nDescription: %s\nIngredients: %s\nSteps: \n", 
                                                                recipe.getName(), recipe.getDescription(), recipe.getIngredients());
                                            for (String s: steps){
                                                System.out.println("  "+s);
                                            }
                                            System.out.println();
                                        }
                                        else if (read.equals("2")) {
                                
                                            obj = new Scanner(System.in);
                                            for (int i = 0; i < steps.length; i++) {
                                                System.out.println(steps[i]); // print each step one at a time
                                    
                                                System.out.println("Next step? (yes/no)"); // ask user if they want to see next step
                                                String next = obj.nextLine();
                                                next = next.toLowerCase();
                                    
                                                if (next.equals("yes")) {
                                                    continue;
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                
                                } 
                                else {
                                    System.out.println("Error: Recipe not found");
                                }
                            }
                            
                            // user clicked option 3, exit 
                            else {
                                System.out.println("Exiting Recipe Retrieval");
                                break;
                            }
                        }
                    }
                        System.out.println();
                    }

                    obj.close();
             }
                    
}
