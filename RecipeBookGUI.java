import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class RecipeBookGUI extends JFrame {

	// define variables
	private static CardLayout cardLayout;
	private static JFrame frame;
	private static JPanel cardPanel, pMain, pCreation, pRetrieval, pBrowse, pSearch, pRead, pModify;
	private static JLabel lMain, lCreation, lCreation1, lCreation2, lCreation3, lCreation4, lRetrieval, lBrowse, lSearch, lSearch1, lRead, lModify;
	private static JButton bBack1, bBack2, bBack3, bBack4, bBack5, bBack6, bCreation, bRecipeToCreate, bRetrieval, bBrowse, bSearch, bRecipeToSearch, bReadEntire, bReadSteps, bNext, bModify, bExit;
	private static JTextField recipeNameTF, recipeDescriptionTF, recipeToSearchTF;
	private static JTextArea recipes, recipeIngredientsTA, recipeStepsTA, readRecipeTA;
	int stepCounter;
	// retrieve recipes already in recipes.txt file
	private static Recipes recipe = new Recipes();
	private static ArrayList<Recipes> recipeList = new ArrayList<Recipes> ();	
	private static RecipeRetrieval data = new RecipeRetrieval();
	private static ArrayList<Recipes> hold = data.LoadRecipes();

	public static void main(String args[]) {

			RecipeBookGUI rbg = new RecipeBookGUI();
			rbg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			rbg.setResizable(false);
			rbg.setVisible(true);

	}

	public RecipeBookGUI() {

		recipeList = hold;

		// set frame
		setTitle("Recipe Book");
		setSize(600, 375);
		
		// set CardLayout
		cardPanel = new JPanel(); 
		cardLayout = new CardLayout(); 
		cardPanel.setLayout(cardLayout);

		// main "Recipe Book" page
		pMain = new JPanel(); // create new panel
		pMain.setBackground(Color.decode("#ECB390"));
        pMain.setLayout(null);

		lMain = new JLabel("Recipe Book",JLabel.CENTER); // spaces for formatting purposes
        lMain.setOpaque(true);
        lMain.setFont(new Font("Serif", Font.BOLD, 40));
        lMain.setBackground(Color.decode("#FCF8E8"));
		lMain.setForeground(Color.decode("#DF7861"));
        lMain.setBounds(0, 0, 600, 100);


		bCreation = new JButton("Create Recipe"); // create new button
		bRetrieval = new JButton("Retrieve Recipe"); // create new button
		bModify = new JButton("Modify Recipe");
		bExit = new JButton("Exit");

        bCreation.setBounds(60,150,200, 50);
        bCreation.setBorderPainted(false);
        bCreation.setBackground(Color.decode("#EDDBC0"));
        //bCreation.setFont(new Font("Helvetica", Font.BOLD, 14));

        bRetrieval.setBounds(320,150,200, 50);
        bRetrieval.setBorderPainted(false);
        bRetrieval.setBackground(Color.decode("#EDDBC0"));

        bModify.setBounds(60,230,200, 50);
        bModify.setBorderPainted(false);
        bModify.setBackground(Color.decode("#EDDBC0"));

        bExit.setBounds(320,230,200, 50);
        //bExit.setBorderPainted(false);
        bExit.setForeground(Color.decode("#EDDBC0"));
        bExit.setBackground(Color.decode("#7D6E83"));


		pMain.add(lMain); // add label to panel
		pMain.add(bCreation); // add button to panel
		pMain.add(bRetrieval); // add button to panel
		pMain.add(bModify); // add button to panel
		pMain.add(bExit); // add button to panel

		// "Recipe Creation" page
		pCreation = new JPanel();
		pCreation.setBackground(new java.awt.Color(44,238,144));

		lCreation = new JLabel("                                                 Recipe Creation                                                 "); // spaces for formatting purposes
		lCreation.setForeground(Color.WHITE);

		lCreation1 = new JLabel("Enter recipe name: ");
		recipeNameTF = new JTextField("", 33);

		lCreation2 = new JLabel("Enter recipe description: ");
		recipeDescriptionTF = new JTextField("", 30);

		lCreation3 = new JLabel("Enter recipe ingredients (place each ingredient on a new line): ");
		recipeIngredientsTA = new JTextArea("", 5, 45);

		lCreation4 = new JLabel("Enter recipe steps (place each step on a new line): ");
		recipeStepsTA = new JTextArea("", 5, 45);

		bRecipeToCreate = new JButton("Submit");

		bBack1 = new JButton("Go Back");

		pCreation.add(lCreation);
		pCreation.add(lCreation1);
		pCreation.add(recipeNameTF);
		pCreation.add(lCreation2);
		pCreation.add(recipeDescriptionTF);
		pCreation.add(lCreation3);
		pCreation.add(recipeIngredientsTA);
		pCreation.add(lCreation4);
		pCreation.add(recipeStepsTA);
		pCreation.add(bRecipeToCreate);
		pCreation.add(bBack1);

		// "Recipe Retrieval" page
		pRetrieval = new JPanel();
		pRetrieval.setBackground(new java.awt.Color(66, 135, 245));

		lRetrieval = new JLabel("                                                 Recipe Retrieval                                                 "); // spaces for formatting purposes
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
			pBrowse.setBackground(new java.awt.Color(102, 204, 255));

			lBrowse = new JLabel("Browse Recipes");
			lBrowse.setForeground(Color.WHITE);

			String allRecipes = RecipeRetrieval.allRecipeNames(); // returns string of recipe list where recipes are seperated by \n
			recipes = new JTextArea(allRecipes, 15, 45);

			bBack4 = new JButton("Go Back");

			pBrowse.add(lBrowse);
			pBrowse.add(recipes);
			pBrowse.add(bBack4);

			// "Search For A Recipe" page 
			pSearch = new JPanel();
			pSearch.setBackground(new java.awt.Color(102, 204, 255));

			lSearch = new JLabel("                                                 Search For A Recipe                                                 "); // spaces for formatting purposes
			lSearch.setForeground(Color.WHITE);

			lSearch1 = new JLabel("Enter recipe name: ");

			recipeToSearchTF = new JTextField("", 35);
			bRecipeToSearch = new JButton("Submit");

			bBack5 = new JButton("Go Back");

			pSearch.add(lSearch);
			pSearch.add(lSearch1);
			pSearch.add(recipeToSearchTF);
			pSearch.add(bRecipeToSearch);
			pSearch.add(bBack5);

		// "Recipe Reading" page
		pRead = new JPanel();
		pRead.setBackground(new java.awt.Color(238,130,238));

		// create labels
		lRead = new JLabel("                                                 Recipe Reading                                                 "); // spaces for formatting purposes
		lRead.setForeground(Color.WHITE);

		// create text fields
		readRecipeTA = new JTextArea("", 15, 45);

		// create buttons
		bReadEntire = new JButton("Read Entire Recipe");
		bReadSteps = new JButton("Read Recipe Steps Only");
		bBack6 = new JButton("Go Back");

		// add all components to pRead
		pRead.add(lRead);
		pRead.add(bReadEntire);
		pRead.add(bReadSteps);
		pRead.add(bBack6);
		pRead.add(readRecipeTA);

		// "Recipe Modification" page
		pModify = new JPanel();
		pModify.setBackground(new java.awt.Color(255, 149, 72));

		lModify = new JLabel("                                                 Recipe Modification                                                 "); // spaces for formatting purposes
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
		cardPanel.add(pRead, "Recipe Reading");

		// display "Recipe Book" page
		getContentPane().add(cardPanel, BorderLayout.CENTER);

		// buttons to switch pages
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

		bBack6.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				readRecipeTA.setText("");
				cardLayout.show(cardPanel, "" + "Recipe Retrieval");
			}		  
		});


		bCreation.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Creation");
			}		  
		});   

		bRecipeToCreate.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				String recipeName = recipeNameTF.getText().trim();
				String recipeDescription = recipeDescriptionTF.getText().trim();
				String recipeIngredients = recipeIngredientsTA.getText().trim();
				String recipeSteps = recipeStepsTA.getText().trim();

				RecipeCreation.createRecipeGUI(recipeName,recipeDescription,recipeIngredients,recipeSteps); // creates recipe based on user input

				// clear text fields
				recipeNameTF.setText("");
				recipeDescriptionTF.setText("");
				recipeIngredientsTA.setText("");
				recipeStepsTA.setText("");

				// retrieve recipes already in recipes.txt file
	

				cardLayout.show(cardPanel, "" + "Recipe Book");
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

		bRecipeToSearch.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				String recipeToSearch = recipeToSearchTF.getText();

				Recipes myRecipe = RecipeRetrieval.searchByNameGUI(recipeToSearch);
                myRecipe.displayFullRecipe(); // replace line with method that has inputs recipe name outputs recipe name
				
				// if recipe found go to "Recipe Reading Page," else print error
				if(myRecipe != null) {
				    cardLayout.show(cardPanel, "" + "Recipe Reading");
				}
				else {
					JOptionPane.showMessageDialog(null, "Recipe '" + recipeToSearch + "' not found. Please try again.", "Error", JOptionPane.WARNING_MESSAGE); // error message
				}

				recipeToSearchTF.setText("");
			}		  
		}); 

		bReadEntire.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				Recipes readRecipe = recipeList.get(0);
				readRecipeTA.setLineWrap(true);
				readRecipeTA.setText("Recipe Name: " + readRecipe.getName() + "\nRecipe Description: " + readRecipe.getDescription() + "\nRecipe Ingredients: " + readRecipe.getIngredients() + "\nRecipe Steps: " + readRecipe.getSteps());
			}		  
		}); 

		bReadSteps.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				Recipes readRecipe = recipeList.get(0);
				readRecipeTA.setLineWrap(true);
				readRecipeTA.setText("Recipe Steps: \n");

				// button to get next step
				bNext = new JButton("Next Step");
				pRead.add(bNext);
				bNext.setVisible(true);

				stepCounter = 0;
				String steps[] = readRecipe.getSteps().split("(?<=.  )"); 
				readRecipeTA.append(steps[stepCounter] + " \n"); // display first recipe step

				bNext.addActionListener(new ActionListener() { // iterates through & displays rest of recipe steps 
					public void actionPerformed(ActionEvent ae) {  
					stepCounter++;
					if (stepCounter < steps.length) {
						readRecipeTA.append(steps[stepCounter] + " \n");
					}

					if (stepCounter >= steps.length) {
						bNext.setVisible(false);
					}

				}			  
				});
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
