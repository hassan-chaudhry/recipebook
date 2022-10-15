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
	private static JLabel lMain, lCreation, lCreation1, lCreation2, lCreation3, lCreation4, lRetrieval, lBrowse, lSearch, lSearch1, lRead, lModify, lModify1, lModify2;
	private static JButton bBack1, bBack2, bBack3, bBack4, bBack5, bBack6, bCreation, bRecipeToCreate, bRetrieval, bBrowse, bSearch, bRecipeToSearch, bReadEntire, bReadSteps, bNext, bModify, bExit;
	private static JTextField recipeNameTF, recipeDescriptionTF, recipeToSearchTF;
	private static JTextArea recipes, recipeIngredientsTA, recipeStepsTA, readRecipeTA;
	public Recipes readRecipe; // to keep track os recipe given by usser
	boolean bNextExist = false; // to keep track of "Next Step" button visibility on "Recipe Reading" page
	int stepCounter; // to keep track of step count on "Recipe Reading" page

	// retrieve recipes already in recipes.txt file
	private Recipes recipe = new Recipes();
	private ArrayList<Recipes> recipeList = new ArrayList<Recipes> ();	
	private RecipeRetrieval data = new RecipeRetrieval();
	private ArrayList<Recipes> hold = data.LoadRecipes();

	public static void main(String args[]) {

		// run program;
		RecipeBookGUI rbg = new RecipeBookGUI();
		rbg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		rbg.setResizable(false);
		rbg.setVisible(true);

	}

	public RecipeBookGUI() {

		recipeList = hold;

		// set frame
		setTitle("Recipe Book");
		setSize(600, 450);
		
		// set CardLayout
		cardPanel = new JPanel(); 
		cardLayout = new CardLayout(); 
		cardPanel.setLayout(cardLayout);

		/*
			"Recipe Book" page
		*/

		// create panel
		pMain = new JPanel(); // create new panel
		pMain.setBackground(Color.decode("#ECB390"));
		pMain.setLayout(null);

		// create & format labels
		lMain = new JLabel("Recipe Book",JLabel.CENTER); 
		lMain.setOpaque(true);
		lMain.setFont(new Font("Serif", Font.BOLD, 40));
		lMain.setBackground(Color.decode("#FCF8E8"));
		lMain.setForeground(Color.decode("#DF7861"));
		lMain.setBounds(0, 50, 600, 100);

		// create & format buttons
		/* 
        bCreation = new JButton("Create Recipe"); 
		bRetrieval = new JButton("Retrieve Recipe"); 
		bModify = new JButton("Modify Recipe");
		bExit = new JButton("Exit");
        
		bCreation.setBorderPainted(false);
		bCreation.setBackground(Color.decode("#EDDBC0"));

		bRetrieval.setBorderPainted(false);
 		bRetrieval.setBackground(Color.decode("#EDDBC0"));

		bModify.setBorderPainted(false);
		bModify.setBackground(Color.decode("#EDDBC0"));

        bExit.setBounds(320,230,200, 50);
        bExit.setForeground(Color.decode("#EDDBC0"));
        bExit.setBackground(Color.decode("#7D6E83"));
        */
        bCreation = new roundButton("Create Recipe", Color.decode("#FDEEDC"));
        bCreation.setBounds(50,190,220, 70);

        bRetrieval = new roundButton("Retrieve Recipe", Color.decode("#FDEEDC")); 
		bRetrieval.setBounds(320,190,220, 70);

		bModify = new roundButton("Modify Recipe", Color.decode("#FDEEDC"));
		bModify.setBounds(50,290,220, 70);

        bExit = new roundButton("Exit", Color.decode("#7D6E83"));
        bExit.setForeground(Color.decode("#EDDBC0"));
        bExit.setBounds(320,290,220, 70);

		// add all components to panel
		pMain.add(lMain); 
		pMain.add(bCreation); 
		pMain.add(bRetrieval); 
		pMain.add(bModify); 
		pMain.add(bExit);

		/*
			"Recipe Creation" page
		*/

		// create panel
		pCreation = new JPanel();
		pCreation.setBackground(Color.decode("#C1D5A4"));
        pCreation.setLayout(null);

		// create labels & text fields
		lCreation = new JLabel("Recipe Creation",JLabel.CENTER); // spaces for formatting purposes
        lCreation.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        lCreation.setBounds(0, 0, 600, 80);
        //lCreation.setForeground(Color.decode("#1C6758"));

		lCreation1 = new JLabel("Enter recipe name: ");
        lCreation1.setBounds(50, 50, 600, 40);
		recipeNameTF = new roundTextField("", 30, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeNameTF.setBounds(50, 80, 485, 30);

		lCreation2 = new JLabel("Enter recipe description: ");
        lCreation2.setBounds(50, 100, 600, 40);
		recipeDescriptionTF = new roundTextField("", 30, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeDescriptionTF.setBounds(50, 130, 485, 30);

		lCreation3 = new JLabel("Enter recipe ingredients (place each ingredient on a new line): ");
        lCreation3.setBounds(50, 150, 600, 40);
        recipeIngredientsTA = new roundTextArea("", 10, 45, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeIngredientsTA.setBounds(50, 180, 485, 60);

		lCreation4 = new JLabel("Enter recipe steps (place each step on a new line): ");
        lCreation4.setBounds(50, 230, 600, 40);
        recipeStepsTA = new roundTextArea("", 10, 45, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeStepsTA.setBounds(50, 260, 485, 100);

		// create buttons
		bRecipeToCreate = new roundButton("Submit",Color.decode("#FFFDE3"));
        bRecipeToCreate.setBounds(330, 370, 170, 35);

		bBack1 = new roundButton("Go Back",Color.decode("#446A46"));
        bBack1.setForeground(Color.decode("#EEF2E6"));
        bBack1.setBounds(90, 370, 170, 35);

		// add all components to panel
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

		/*
			"Recipe Retrieval" page
		*/

		// create panel
		pRetrieval = new JPanel();
		pRetrieval.setBackground(new java.awt.Color(66, 135, 245));

		// create labels
		lRetrieval = new JLabel("                                                 Recipe Retrieval                                                 "); // spaces for formatting purposes
		lRetrieval.setForeground(Color.WHITE);

		// create buttons
		bBrowse = new JButton("Browse Recipes");
		bSearch = new JButton("Search For A Recipe");
		bBack2 = new JButton("Go Back");

		// add all components to panel
		pRetrieval.add(lRetrieval);
		pRetrieval.add(bBrowse);
		pRetrieval.add(bSearch);
		pRetrieval.add(bBack2);

			/*
				"Recipe Browse" page
			*/

			// create panel
			pBrowse = new JPanel();
			pBrowse.setBackground(new java.awt.Color(102, 204, 255));

			// create labels
			lBrowse = new JLabel("Browse Recipes");
			lBrowse.setForeground(Color.WHITE);

			// get all recipes
			String allRecipes = RecipeRetrieval.allRecipeNames(); // returns string of recipe list where recipes are seperated by \n
			recipes = new JTextArea(allRecipes, 15, 45);

			// create buttons
			bBack4 = new JButton("Go Back");

			// add all components to panel
			pBrowse.add(lBrowse);
			pBrowse.add(recipes);
			pBrowse.add(bBack4);

			/*
				"Recipe Search" page
			*/

			// create panel
			pSearch = new JPanel();
			pSearch.setBackground(new java.awt.Color(102, 204, 255));

			// create labels & text fields
			lSearch = new JLabel("                                                 Search For A Recipe                                                 "); // spaces for formatting purposes
			lSearch.setForeground(Color.WHITE);

			lSearch1 = new JLabel("Enter recipe name: ");
			recipeToSearchTF = new JTextField("", 35);

			// create buttons
			bRecipeToSearch = new JButton("Submit");
			bBack5 = new JButton("Go Back");

			// add all components to panel
			pSearch.add(lSearch);
			pSearch.add(lSearch1);
			pSearch.add(recipeToSearchTF);
			pSearch.add(bRecipeToSearch);
			pSearch.add(bBack5);

		/*
			"Recipe Reading" page
		*/

		// create panel
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

		/*
			"Recipe Modification" page
		*/

		// create panel
		pModify = new JPanel();
		pModify.setBackground(new java.awt.Color(255, 149, 72));

		// create labels
		lModify = new JLabel("                                                 Recipe Modification                                                 "); // spaces for formatting purposes
		lModify.setForeground(Color.WHITE);

		lModify1 = new JLabel("Feature Coming Soon in the Next Update!");
		lModify1.setFont(new Font("Serif", Font.PLAIN, 25));
		lModify2 = new JLabel("                              Check Back in 2023.                              "); // spaces for formatting purposes
		lModify2.setFont(new Font("Serif", Font.PLAIN, 25));

		// create buttons
		bBack3 = new JButton("Go Back");

		// add all components to panel
		pModify.add(lModify);
		pModify.add(lModify1);
		pModify.add(lModify2);
		pModify.add(bBack3);

		/*
			Combine panels & display
		*/

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

		/*
			Buttons - Allows user to navigate pages and submit text input
		*/

		bBack1.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Book"); // switch to "Recipe Book" page
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
				cardLayout.show(cardPanel, "" + "Recipe Retrieval"); // switch to "Recipe Retrieval" page
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
				if (bNextExist) {
					bNext.setVisible(false);
				}
				cardLayout.show(cardPanel, "" + "Recipe Retrieval");
			}		  
		});

		bCreation.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Creation");  // switch to "Recipe Creation" page
			}		  
		});   

		bRecipeToCreate.addActionListener(new ActionListener() { // to create a recipe
			public void actionPerformed(ActionEvent ae) {  
				// get user input
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

				// get program to read updated recipes.txt file by closing current instance of program and making a new one
				dispose();
				RecipeBookGUI rbg = new RecipeBookGUI();
				rbg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				rbg.setResizable(false);
				rbg.setVisible(true);

			}		  
		}); 

		bRetrieval.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Retrieval");
			}		  
		});  

		bBrowse.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Browse Recipes"); // switch to "Browse Recipes" page
			}		  
		}); 

		bSearch.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Search For A Recipe"); // switch to "Search For A Recipe" page
			}		  
		}); 

		bRecipeToSearch.addActionListener(new ActionListener() { // to retrieve a recipe
			public void actionPerformed(ActionEvent ae) {  
				// get recipe from user
				String recipeToSearch = recipeToSearchTF.getText();
				boolean recipeFound = RecipeRetrieval.existRecipeGUI(recipeToSearch); // replace line with method that returns true if recipe found, else false
				
				// if recipe found go to "Recipe Reading Page," else show error
				if(recipeFound) {
					readRecipe = RecipeRetrieval.searchByNameGUI(recipeToSearch); // replace line with method that has inputs recipe name outputs recipe 
				    cardLayout.show(cardPanel, "" + "Recipe Reading"); // switch to "Recipe Reading" page
				}
				else {
					JOptionPane.showMessageDialog(null, "Recipe '" + recipeToSearch + "' not found. Please try again.", "Error", JOptionPane.WARNING_MESSAGE); // error message
				}

				// clear text field
				recipeToSearchTF.setText("");
			}		  
		}); 

		bReadEntire.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				if (bNextExist) { // if "Next Step" button exists
					bNext.setVisible(false); // delete it
				}
				
				// wrap text field	
				readRecipeTA.setLineWrap(true);
				readRecipeTA.setText("Recipe Name: " + readRecipe.getName() + "\nRecipe Description: " + readRecipe.getDescription() + "\nRecipe Ingredients: " + readRecipe.getIngredients() + "\nRecipe Steps: " + readRecipe.getSteps());
			}		  
		}); 

		bReadSteps.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				
				// wrap text field	
				readRecipeTA.setLineWrap(true);
				readRecipeTA.setText("Recipe Steps for " + readRecipe.getName() + ": \n");

				// button to get next step
				bNext = new JButton("Next Step");
				pRead.add(bNext);
				bNextExist = true;
				bNext.setVisible(true);

				// set up iteration counter
				stepCounter = 0;
				String steps[] = readRecipe.getSteps().split("(?<=.  )"); 
				readRecipeTA.append(steps[stepCounter] + " \n"); // display first recipe step

				bNext.addActionListener(new ActionListener() { // iterates through & displays rest of recipe steps 
					public void actionPerformed(ActionEvent ae) {  
					stepCounter++;
					if (stepCounter < steps.length) { 
						readRecipeTA.append(steps[stepCounter] + " \n"); // print next step
					}

					if (stepCounter >= steps.length) { // if all steps printed, remove "Next Step" button
						bNext.setVisible(false);
					}

				}			  
				});
			}		  
		}); 

		bModify.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Modification"); // switch to "Recipe Modification" page
			}		  
		}); 

		bExit.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) { // to exit program
				System.exit(0);
			}		  
		}); 

	}

    public static class roundButton extends JButton
    {
        String s;

        Color color;
    
        public roundButton(String s ,Color color)  
        {
            super(s);
            setContentAreaFilled(false);
            this.color = color;
        }
        
        public void paintComponent(Graphics g)   
        {
            g.setColor(this.color);
            g.fillRoundRect(0,0,getSize().width-1,getSize().height-1,12,12);    
            super.paintComponent(g);	
        }
        
        public void paintBorder(Graphics g)  
        {
            g.drawRoundRect(0,0,getSize().width-1,getSize().height-1,12,12);
        }
    }

    public static class roundTextField extends JTextField{
	
        private Color fillColor;
        private Color borderColor;

        public roundTextField(String text, int colomns, Color fill, Color border) {
            super(text,colomns);
            setOpaque(false); 
            this.fillColor = fill;
            this.borderColor = border;
        }
        
        protected void paintComponent(Graphics g) {
             g.setColor(this.fillColor);
             g.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 12, 12);
             super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(this.borderColor);
            g2d.drawRoundRect(0, 0, getWidth()-2, getHeight()-2, 12, 12);
        }
    }

    public static class roundTextArea extends JTextArea{
	
        private Color fillColor;
        private Color borderColor;

        public roundTextArea(String text, int rows, int colomns, Color fill, Color border) {
            super(text,rows,colomns);
            setOpaque(false); 
            this.fillColor = fill;
            this.borderColor = border;
        }
        
        protected void paintComponent(Graphics g) {
             g.setColor(this.fillColor);
             g.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 12, 12);
             super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(this.borderColor);
            g2d.drawRoundRect(0, 0, getWidth()-2, getHeight()-2, 12, 12);
        }
    }
}
