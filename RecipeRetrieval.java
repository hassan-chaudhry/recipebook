import java.util.*;
import java.io.*;
import java.net.Inet4Address;

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
            Recipes.displayRecipeList(list);
            obj = new Scanner(System.in);
    
            System.out.println("If there's a recipe you want to read in details, please enter its number. Otherwize please enter 0.\n");
             choice = obj.nextInt();
            
            if (choice < 0 || choice >list.size()){
                System.out.println("Invalid Input. Please try again.\n");
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

    public static void modifyInterface(ArrayList<Recipes> list){
        int choice = 0;
        Recipes target = null;

        // display a list and let the user too choose a recipe to edit
        while(true){
            Recipes.displayRecipeList(list);
            obj = new Scanner(System.in);
    
            System.out.println("Choosing mode: Please choose the recipe you want to modify. Enter 0 to switch to searching mode. \n");
             choice = obj.nextInt();
            
            if (choice < 0 || choice >list.size()){
                System.out.println("Invalid Input. Please try again.\n");
            } else {
                break;
            }
        }

        System.out.println();
        if (choice==0){
            target = RecipeRetrieval.searchForRecipe(list, 0);
        } else {
            target = list.get(choice-1);
        }

        modifyRecipe(list, target.getName());
    }

    public static void modifyRecipe(ArrayList<Recipes> list, String recipeName) {
        System.out.println("You are editing "+recipeName);

        int index = -1;
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).getName().equals(recipeName)){
                index = i;
                break;
            }
        }
        if (index>=list.size()||index<0){
            System.out.println("error");
            return;
        } 

        Scanner scan = new Scanner(System.in);
        String temp = "";
        ArrayList<String> tmpList = new ArrayList<>();

        System.out.println("Please enter the new data for each part of the recipe.");
        System.out.println("If you don't want to change the content of certain part, just leave it blank.");
        System.out.println();


        System.out.println("\nEnter a new name: ");
        temp = scan.nextLine();
        while(RecipeCreation.duplicatedName(temp)){
            System.out.println("The recipe already exists! Please enter a new name: ");
            temp = scan.nextLine();
        }
        if (temp != ""){
            list.get(index).setName(temp.substring(0, 1).toUpperCase()+temp.substring(1));
        }
        temp = "";

        System.out.println("\nEnter a new description: ");
        temp = scan.nextLine();
        if (temp != ""){
            temp = RecipeCreation.formattedSentence(temp);
            list.get(index).setDescription(temp);
        }
        temp = "";

        System.out.println("\nEnter the new ingredients, press ENTER 2 times to end: ");
        temp = scan.nextLine();
        while(temp != null && !temp.equals("")){
            tmpList.add(temp);
            temp = scan.nextLine();
        }
        if (tmpList.size()>0){
            list.get(index).setIngredients(RecipeCreation.ingredientsToString(tmpList));
        }
        temp = "";
        tmpList.clear();

        System.out.println("\nEnter the new steps, press ENTER 2 times to end: ");
        temp = scan.nextLine();
        while(temp != null && !temp.equals("")){
            temp = RecipeCreation.formattedSentence(temp);
            tmpList.add(temp);
            temp = scan.nextLine();
        }
        if (tmpList.size()>0){
            list.get(index).setSteps(RecipeCreation.stepsToString(tmpList));
        }
        temp = "";
        tmpList.clear();

        System.out.println("Your new recipe is: ");
        list.get(index).displayFullRecipe();


        File file =new File("recipes.txt");
        file.delete();
        file = new File("recipes.txt");

        try {
            for(int i = 0; i< list.size();i++){
                RecipeCreation.writeRecipeToFile(list.get(i));
            }
        } catch (Exception e) {
            System.out.println("Recipe Editing Error!");
            e.printStackTrace();
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
