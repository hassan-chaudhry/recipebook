public class RecipeReading {

	private static Scanner obj;

	public static void readRecipe(Recipes recipe) {
		obj = new Scanner(System.in);
		System.out.println("Would you like to: (1) read entire recipe or (2) step through intructions"); // ask user how to read recipe
		String read = obj.nextLine();
		read = read.toLowerCase();

		if (read.equals("1")) {
			readEntire(recipe); // read entire recipe 
		}
		else if (read.equals("2")) {
			readSteps(recipe); // step through instructions
		}

		obj.close();
	}

	public static void readEntire(Recipes recipe) {
		System.out.println(recipe.getName, recipe.getDescription, recipe.getIngredients, recipe.getSteps);
	}

	public static void readSteps(Recipes recipe) {
		String steps[] = recipe.getSteps.split(".  "); // parsing steps and splitting them

		obj = new Scanner(System.in);
		for (int i = 0; i < steps.length; i++) {
			System.out.println(steps[i]); // print each step one at a time

			System.out.println("Next step? (yes/no)"); // ask user if they want to see next step
			String next = obj.nextLine();
			next= next.toLowerCase();

			if (next.equals("yes")) {
				continue;
			}
			else {
				break;
			}

		}

		obj.close();
	}
}
