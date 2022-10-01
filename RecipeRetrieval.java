import java.util.*;
import java.io.*;

public class RecipeRetrieval {
    public static ArrayList<Recipes> LoadRecipes() {
        // Scanner input = new Scanner(System.in);

        ArrayList<String> recipeList = new ArrayList<String> ();
        ArrayList<Recipes> list = new ArrayList<Recipes> ();

        // boolean check = false;

        String fileName = "inputFile.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                recipeList.add(line);
            }

            for (int i = 0; i < recipeList.size(); i++) {
                String current = recipeList.get(i);
                System.out.println(current);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        return list;
    }
}
