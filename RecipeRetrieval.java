import java.util.*;
import java.io.*;

public class RecipeRetrieval {

    private static Scanner obj;

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

    public static void displayAllRecipes(ArrayList<Recipes> list) {
        for (int i = 0; i < list.size(); i++) {
            Recipes temp = list.get(i);
            temp.displayRecipe(temp);
        }
	System.out.println();
    }

    public static Recipes searchForRecipe(ArrayList<Recipes> list) {
        obj = new Scanner(System.in);
        Recipes recipe = new Recipes();
	while(true) {

	        System.out.println("Name of recipe: ");
	        String searchName = obj.nextLine();
	

	        for (int i = 0; i < list.size(); i++) {
        	    Recipes temp = list.get(i);
            	    if (temp.getName().equals(searchName)) {
                	System.out.println("\nRecipe was found!\n");
               		Recipes current = list.get(i);
                	return current;
            	    }
		}
  
       	System.out.println("\nError: Recipe was not found. Please try again.\n");
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
                for (int i = 0; i < list.size(); i++) {
                    Recipes current = list.get(i);
                    String currentName = current.getName();
                    if (currentName.toLowerCase().contains(searchName.toLowerCase())) {
                        System.out.println("Found:  "+currentName);

                        searchResults.add(current);
                    }
                }
                System.out.printf("Found %d recipes related to %s\n",searchResults.size(), searchName);
                break;
            }
        }

        return searchResults;
     
     }
}
