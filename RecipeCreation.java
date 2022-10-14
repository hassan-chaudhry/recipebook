import java.util.*; 
import java.io.*;

public class RecipeCreation {
    private static Scanner scan = new Scanner(System.in);
    final private static String fileName = "recipes.txt";

    public RecipeCreation(){};
    
    public static void createRecipe(){
        System.out.println("Please enter the name of your recipe: ");
        String name = scan.nextLine();
        while(duplicatedName(name)){
            System.out.println("The recipe already exists! Please enter a new name: ");
            name = scan.nextLine();
        }
        name = name.substring(0, 1).toUpperCase()+name.substring(1);
        //System.out.println("You entered: "+name);
        //System.out.println(duplicatedName(name));

        System.out.println("\nPlease enter the description of your recipe: ");
        String description = scan.nextLine();
        description = formattedSentence(description);
        //System.out.println("You entered: "+description);

        System.out.println("\nPlease enter the ingredients here, press ENTER 2 times to end: ");
        ArrayList<String> ingredientsArr = new ArrayList<>();
        String ing = scan.nextLine();
        while(ing != null && !ing.equals("")){
            ingredientsArr.add(ing);
            ing = scan.nextLine();
        }
        String ingredients = ingredientsToString(ingredientsArr);

        System.out.println("\nPlease enter the steps here, press ENTER 2 times to end: ");
        ArrayList<String> stepsArr = new ArrayList<>();
        String step = scan.nextLine();
        while(step != null && !step.equals("")){
            step = formattedSentence(step);
            stepsArr.add(step);
            step = scan.nextLine();
        }
        String steps = stepsToString(stepsArr);

        Recipes newRecipe = new Recipes(name, description, ingredients, steps);

        try {
            writeRecipeToFile(newRecipe);
        } catch (Exception e) {
            System.out.println("Recipe Saving Error!");
            e.printStackTrace();
        }
        
        System.out.println("New Recipe "+newRecipe.getName()+" successfully saved!");
    }

    public static void createRecipeGUI(String name, String description, String ingreds, String stps){
        String[] ingList = ingreds.split("\n");
        String[] stepList = stps.split("\n");

        String ingredients = ingredientsToString(ingList);
        String steps = stepsToString(stepList);

        Recipes newRecipe = new Recipes(name, description, ingredients, steps);

        try {
            writeRecipeToFile(newRecipe);
        } catch (Exception e) {
            System.out.println("Recipe Saving Error!");
            e.printStackTrace();
        }
        
        System.out.println("New Recipe "+name+" successfully saved!");
    }

    public static Boolean duplicatedName(String name){
        ArrayList<Recipes> reList = RecipeRetrieval.LoadRecipes();
        for (Recipes r: reList){
            if (r.getName().toLowerCase().equals(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public static String formattedSentence(String str){
        // Capitalize the first letter
        String newStr = str.substring(0, 1).toUpperCase()+str.substring(1);
        // Adding period to the end
        if (!(newStr.charAt(newStr.length() - 1)=='.')){
            newStr += '.';
        }       
        return newStr;
    }

    public static String ingredientsToString(ArrayList<String> ingredients) {
        String IngStr = "";
    	for (int i = 0; i<ingredients.size();i++) {
    		IngStr = IngStr + ingredients.get(i) + (i==ingredients.size()-1?"":", ");
        }
    	return IngStr;
    }

    public static String ingredientsToString(String[] ingredients) {
        String IngStr = "";
    	for (int i = 0; i<ingredients.length;i++) {
    		IngStr = IngStr + ingredients[i] + (i==ingredients.length-1?"":", ");
        }
    	return IngStr;
    }

    public static String stepsToString(ArrayList<String> steps) {
    	String stepStr = "";
    	for (int i = 0; i<steps.size();i++) {
    		stepStr = stepStr + (i==0?"":"  ") + String.format("%d. %s", i+1, steps.get(i));
        }
    	return stepStr;
	}

    public static String stepsToString(String[] steps) {
    	String stepStr = "";
    	for (int i = 0; i<steps.length; i++) {
            stepStr = formattedSentence(stepStr);
    		stepStr = stepStr + (i==0?"":"  ") + String.format("%d. %s", i+1, steps[i]);
        }
    	return stepStr;
	}
    
    public static void writeRecipeToFile(Recipes recipe) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(recipe.getName());
        writer.append(";");
        writer.append(recipe.getDescription());
        writer.append(";");
        writer.append(recipe.getIngredients());
        writer.append(";");
        writer.append(recipe.getSteps());
        writer.append("\n");

        writer.close();
	    System.out.println();
    }

}
