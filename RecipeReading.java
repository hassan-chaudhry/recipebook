import java.util.*;
import java.io.*;

public class RecipeReading {

	private static Scanner obj;

	public static void readEntire(Recipes recipe) {
        	String steps[] = recipe.getSteps().split("(?<=.  )"); // parsing steps and splitting them

		System.out.printf("---------------- %s ----------------\nDescription: %s\nIngredients: %s\nSteps: \n", recipe.getName(), recipe.getDescription(), recipe.getIngredients());
       		for (String s: steps){
            		System.out.println("  "+s);
        	}
        	System.out.println();
	}

	public static void readSteps(Recipes recipe) {
		System.out.println("Press ENTER for the next step.");

        	obj = new Scanner(System.in);
        	String enter = obj.nextLine();      

		String steps[] = recipe.getSteps().split(".  "); // parsing steps and splitting them
 
        	// print each step one at a time
	        while(enter != null) {
        	    for (int i = 0; i < steps.length; i++) {
                	if(enter.isEmpty()) {
                    		System.out.print(steps[i]); 
                	}
            	    
            	    	if (obj.hasNextLine()) {
                    		enter = obj.nextLine();
            	    	}
               	    	
			if (i == steps.length-1) { // break loop when all steps printed
                		enter = null;
                 	}
		    }
		
		}
		
		System.out.println();		
	}
}
