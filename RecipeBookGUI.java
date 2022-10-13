import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class RecipeBookGUI extends JFrame {

	// define variables
	private static CardLayout cardLayout;
	private static JFrame frame;
	private static JPanel cardPanel, pMain, pCreation, pRetrieval, pBrowse, pSearch, pModify;
	private static JLabel lMain, lCreation, lRetrieval, lBrowse, lSearch, lModify;
	private static JButton bBack1, bBack2, bBack3, bBack4, bBack5, bCreation, bRetrieval, bBrowse, bSearch, bModify, bExit;
	private static JTextField recipes; 
	
	public static void main(String args[]) {
		// retrieve recipes already in recipes.txt file
		// Recipes recipe = new Recipes();
		// ArrayList<Recipes> recipeList = new ArrayList<Recipes> ();	
		// RecipeRetrieval data = new RecipeRetrieval();
		// ArrayList<Recipes> hold = data.LoadRecipes();
		// recipeList = hold;

		RecipeBookGUI rbg = new RecipeBookGUI();
		rbg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		rbg.setVisible(true); 

	}

	public RecipeBookGUI() {
		// set frame
		setTitle("Recipe Book");
		setSize(600, 300);
		
		// set CardLayout
		cardPanel = new JPanel(); 
		cardLayout = new CardLayout(); 
		cardPanel.setLayout(cardLayout);

		// main "Recipe Book" page
		pMain = new JPanel(); // create new panel
		pMain.setBackground(new java.awt.Color(255, 80, 80));

		lMain = new JLabel("Recipe Book"); // create new label
		lMain.setForeground(Color.WHITE);

		bCreation = new JButton("Create Recipe"); // create new button
		bRetrieval = new JButton("Retrieve Recipe"); // create new button
		bModify = new JButton("Modify Recipe");
		bExit = new JButton("Exit");

		pMain.add(lMain); // add label to panel
		pMain.add(bCreation); // add button to panel
		pMain.add(bRetrieval); // add button to panel
		pMain.add(bModify); // add button to panel
		pMain.add(bExit); // add button to panel

		// "Recipe Creation" page
		pCreation = new JPanel();
		pCreation.setBackground(new java.awt.Color(44,238,144));

		lCreation = new JLabel("Recipe Creation");
		lCreation.setForeground(Color.WHITE);

		bBack1 = new JButton("Go Back");

		pCreation.add(lCreation);
		pCreation.add(bBack1);

		// "Recipe Retrieval" page
		pRetrieval = new JPanel();
		pRetrieval.setBackground(new java.awt.Color(66, 135, 245));

		lRetrieval = new JLabel("Recipe Retrieval");
		lRetrieval.setForeground(Color.WHITE);

		bBrowse = new JButton("Browse Recipes");
		bSearch = new JButton("Search For A Recipe");
		bBack2 = new JButton("Go Back");

		pRetrieval.add(lRetrieval);
		pRetrieval.add(bBrowse);
		pRetrieval.add(bSearch);
		pRetrieval.add(bBack2);

			// "Browse Recipes" page
			pBrowse = new JPanel();
			pBrowse.setBackground(new java.awt.Color(66, 135, 245));

			lBrowse = new JLabel("Browse Recipes");
			lBrowse.setForeground(Color.WHITE);

			String recipe = ("Peanut Butter and Jelly, Mac and Cheese, Lemonade"); // method that returns string of recipes 
			recipes = new JTextField(recipe, 45);

			bBack4 = new JButton("Go Back");

			pBrowse.add(lBrowse);
			pBrowse.add(recipes);
			pBrowse.add(bBack4);

			// "Search For A Recipe" page 
			pSearch = new JPanel();
			pSearch.setBackground(new java.awt.Color(66, 135, 245));

			lSearch = new JLabel("Search For A Recipe");
			lSearch.setForeground(Color.WHITE);

			bBack5 = new JButton("Go Back");

			pSearch.add(lSearch);
			pSearch.add(bBack5);

		// "Recipe Modification" page
		pModify = new JPanel();
		pModify.setBackground(new java.awt.Color(203, 195, 227));

		lModify = new JLabel("Recipe Modification");
		lModify.setForeground(Color.WHITE);

		bBack3 = new JButton("Go Back");

		pModify.add(lModify);
		pModify.add(bBack3);

		// add every other panel to main "cardPanel" panel
		cardPanel.add(pMain, "Recipe Book");
		cardPanel.add(pCreation, "Recipe Creation");
		cardPanel.add(pRetrieval, "Recipe Retrieval");
		cardPanel.add(pModify, "Recipe Modification");
		cardPanel.add(pBrowse, "Browse Recipes");
		cardPanel.add(pSearch, "Search For A Recipe");

		// display "Recipe Book" page
		getContentPane().add(cardPanel, BorderLayout.CENTER);

		// switching pages
		bBack1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Book");
			}		  
		}); 

		bBack2.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Book");
			}		  
		}); 

		bBack3.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Book");
			}		  
		});

		bBack4.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Retrieval");
			}		  
		}); 

		bBack5.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Retrieval");
			}		  
		}); 

		bCreation.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Creation");
			}		  
		});   

		bRetrieval.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Retrieval");
			}		  
		});  

		bBrowse.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Browse Recipes");
			}		  
		}); 

		bSearch.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Search For A Recipe");
			}		  
		}); 

		bModify.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Modification");
			}		  
		}); 

		bExit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				System.exit(0);
			}		  
		}); 

	}
}
