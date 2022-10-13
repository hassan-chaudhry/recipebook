import java.util.*;
import java.io.*;

public class RecipeRetrieval {

    private static Scanner obj;

    public RecipeRetrieval() {}

    // this is a method to get all the recipes stored in the recipes.txt file and store them
    // in an array of Recipes object
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

    // method to display all recipes that are stored
    public static Recipes displayAllRecipes(ArrayList<Recipes> list) {

        Recipes returnRecipe;

        for (int i = 0; i < list.size(); i++) {
            Recipes temp = list.get(i);
            temp.displayRecipe(temp);
        }
	    System.out.println();

        // after looking through all the recipes, we want the user to be able to search for a recipe if they want
        returnRecipe = searchForRecipe(list, 0);

        return returnRecipe;
    }
    
    // method to search for a recipe 
    public static Recipes searchForRecipe(ArrayList<Recipes> list, int choice) {
        obj = new Scanner(System.in);
        Recipes recipe = new Recipes();
	    while(true) {

            String searchName;

            // ask user for recipe
            if (choice == 0) {
                System.out.print("Select recipe (type 'exit' to leave): ");
                searchName = obj.nextLine();
                searchName = searchName.toLowerCase();
            } else if (choice == 1){
                System.out.print("Name of recipe (type 'exit' to leave): ");
                searchName = obj.nextLine();
                searchName = searchName.toLowerCase();
            } else {
                System.out.print("Recipe name to delete (type 'exit' to leave): ");
                searchName = obj.nextLine();
                searchName = searchName.toLowerCase();
            }

            // check if user wants to exit loop
            if (searchName.equals("exit")) {
                System.out.println();
                return null;
            } else {  // user is trying to search for a recipe
                // see if recipe exists, if so return that Recipes object
                for (int i = 0; i < list.size(); i++) {
                    Recipes temp = list.get(i);
                    String loweredTemp = temp.getName().toLowerCase();
                    if (loweredTemp.equals(searchName)) {
                        System.out.println("\nRecipe was found!\n");
                        Recipes current = list.get(i);
                        return current;
                    }
                }
            }
            
            // error message if recipe not found
       	    System.out.println("\nError: Recipe '" + searchName + "' was not found. Please try again.\n");
        }
     }

     public static ArrayList<Recipes> vagueSearchByName(ArrayList<Recipes> list){
        ArrayList<Recipes> searchResults = new ArrayList<>();

        obj = new Scanner(System.in);

        System.out.println("Enter the word you want to search: ");
        String searchName = obj.nextLine();
        System.out.println();

        while(true){
            if (searchName==""){
                System.out.println("Search word can not be empty");
            } else if (!searchName.matches("[a-zA-Z]+")){
                System.out.println("Search word should only contain letters");
            } else {
                //int count =0;
                for (int i = 0; i < list.size(); i++) {
                    Recipes current = list.get(i);
                    String currentName = current.getName();
                    if (currentName.toLowerCase().contains(searchName.toLowerCase())) {
                        //count ++;
                        //System.out.printf("%d. %s\n", count, currentName);
                        searchResults.add(current);
                    }
                }
                System.out.printf("Found %d recipes related to [%s]\n",searchResults.size(), searchName);
                break;
            }
        }
        return searchResults;
     }


    public static Recipes handleSearchedList(ArrayList<Recipes> list){
        int choice = 0;

        while(true){
            for (int i = 0; i < list.size(); i++){
                System.out.printf("   %d. %s\n", i+1, list.get(i).getName());
            }
            obj = new Scanner(System.in);
    
            System.out.println("If there's a recipe you want to read in details, please enter its number. Otherwize please enter 0.\n");
             choice = obj.nextInt();
            
            if (choice < 0 || choice >list.size()){
                System.out.println("Invalid Input");
            } else {
                break;
            }
        }

        System.out.println();
        if (choice==0){
            return null;
        } else {
            return list.get(choice-1);
        }
    } 

    public static void deleteRecipe(ArrayList<Recipes> currentList, Recipes recipe) {
        
        // delete the recipe from the list
        currentList.remove(recipe);
        System.out.println("Successfully deleted '" + recipe.getName() + "'!");
        System.out.println();

        // also delete it from the text file
        File inputFile = new File("recipes.txt");
        File tempFile = new File("myTempFile.txt");

        String line = null;

        try {
            FileReader fileReader = new FileReader(inputFile);
            FileWriter fileWriter = new FileWriter(tempFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // go through each line the file reader and it matches with what we need to delete,
            // then don't write it to the new file 
            while ((line = bufferedReader.readLine()) != null) {
                String [] clean = line.split(";");
                String recipeName = clean[0];
                if (recipeName.equals(recipe.getName())) continue;
                bufferedWriter.write(line + System.getProperty("line.separator"));
            }
            bufferedWriter.close();
            bufferedReader.close();
            // delete the old outdated file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            // rename the temp file
            if (!tempFile.renameTo(inputFile))
                System.out.println("Could not rename file");
                
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + "recipes.txt" + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + "recipes.txt" + "'");
        }
    }
}
