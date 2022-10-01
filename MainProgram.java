import java.util.*;
import java.io.*;

public class MainProgram {
    
    public static ArrayList<Recipes> recipeList = new ArrayList<Recipes> ();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        File fileName = new File("inputFile.txt");

        RecipeRetrieval data = new RecipeRetrieval();
        ArrayList<Recipes> hold = data.LoadRecipes();
    }
}
