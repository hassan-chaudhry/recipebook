import javax.swing.*;
import java.awt.*;

class RecipeBookGUI{
    public static void main(String args[]){

       // create frame
       JFrame frame = new JFrame("Recipe Book");
       frame.setSize(450,400);
       frame.getContentPane().setBackground(new java.awt.Color(255, 80, 80));

       frame.setVisible(true);
       frame.setLayout(null); 

       // create header "Recipe Book"
       JLabel header = new JLabel("Recipe Book", SwingConstants.CENTER);
       header.setForeground(Color.WHITE);
       header.setBounds(150, -25, 150, 150);
       header.setFont(new Font("Verdana", Font.PLAIN, 18));
       frame.add(header);

       // create button "Create Recipe"
       JButton creationButton = new JButton("Create Recipe");
       creationButton.setBounds(150, 100, 150, 75);
       frame.add(creationButton);

       // create button "View Recipes"
       JButton retrievalButton = new JButton("View Recipes");
       retrievalButton.setBounds(150, 200, 150, 75);
       frame.add(retrievalButton); 
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
