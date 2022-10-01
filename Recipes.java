public class Recipes {
    private String name;
    private String description;
    private String ingredients;
    private String steps;

    public Recipes(){}

    public Recipes(String recipe_name, String desc, String ingr, String step) {
        name = recipe_name;
        description = desc;
        ingredients = ingr;
        steps = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = ingredients;
    }

    public void displayRecipe(Recipes recipe) {
        System.out.println("Recipe name: " + recipe.getName());
    }
}
