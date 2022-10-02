import java.util.*;
import java.io.*;

public class RecipeRetrieval {

    public RecipeRetrieval() {}

    public static ArrayList<Recipes> LoadRecipes() {

        ArrayList<String> recipeList = new ArrayList<String> ();
        ArrayList<Recipes> list = new ArrayList<Recipes> ();
        
        String fileName = "recipes.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                recipeList.add(line);
            }

            for (int i = 0; i < recipeList.size(); i++) {
                // String current = recipeList.get(i);
                // System.out.println(current);
                String current = recipeList.get(i);
                String [] clean = current.split(";");
                String recipeName = clean[0];
                String description = clean[1];
                String ingredientList = clean[2];
                String stepByStep = clean[3];

                Recipes temp = new Recipes(recipeName, description, ingredientList, stepByStep);
                list.add(temp);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        return list;
    }

    public void displayAllRecipes(ArrayList<Recipes> list) {
        for (int i = 0; i < list.size(); i++) {
            Recipes temp = list.get(i);
            temp.displayRecipe(temp);
        }
    }

    public boolean searchForRecipe(ArrayList<Recipes> list, String recipeName) {
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            Recipes temp = list.get(i);
            if (temp.getName().equals(recipeName)) {
                found = true;
                break;
            }
        }
        if (found) {
            return true;
        } else {
            return false;
        }
    }

    public void retrievalInterface(Scanner input, ArrayList<Recipes> list) {
        while (true) {
            System.out.println("-------------- Recipe Retrieval --------------");
            System.out.println("1. View all recipes");
            System.out.println("2. Search For a recipe");
            System.out.println("3. Exit");
            System.out.print("Please type 1 or 2 or 3: ");
            int choice = input.nextInt();
            input.nextLine();
            System.out.println();
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.println("Sorry, incorrect input. Try again");
                System.out.println();
            } else {
                // user clicked option 1, view all recipes
                if (choice == 1) {
                    displayAllRecipes(list);
                    System.out.println();

                // user clicked option 2, search for a recipe
                } else if (choice == 2) {
                    System.out.print("Name of recipe: ");
                    String searchName = input.nextLine();
                    boolean ifFound = searchForRecipe(list, searchName);
                    if (ifFound) {
                        System.out.println("Recipe was found!");
                    } else {
                        System.out.println("Error: Recipe not found");
                    }

                // user clicked option 3, exit
                } else {
                    System.out.println("Exiting Recipe Retrieval");
                    break;
                }
            }
            System.out.println();
        }
        return;
    }
}
