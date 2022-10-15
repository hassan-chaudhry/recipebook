import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

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
		setSize(600, 500);
		
		// set CardLayout
		cardPanel = new JPanel(); 
		cardLayout = new CardLayout(); 
		cardPanel.setLayout(cardLayout);

		/*
			"Recipe Book" page
		*/

		// create panel
		pMain = new JPanel(); // create new panel
		pMain.setBackground(Color.decode("#F5E9A3"));
		pMain.setLayout(null);

		// create & format labels
		lMain = new JLabel("Recipe Book",JLabel.CENTER); 
		lMain.setOpaque(true);
		lMain.setFont(new Font("Serif", Font.BOLD, 50));
		lMain.setBackground(Color.decode("#FEFEEE"));
		lMain.setForeground(Color.decode("#DF7861"));
        //lMain.setForeground(Color.decode("#7D6E83"));
		lMain.setBounds(0, 50, 600, 150);

		// create & format buttons
        bCreation = new roundButton("Create Recipe", Color.decode("#FFFDE3"));
        bCreation.setBounds(50,235,220, 70);

        bRetrieval = new roundButton("Retrieve Recipe", Color.decode("#FFFDE3")); 
		bRetrieval.setBounds(320,235,220, 70);

		bModify = new roundButton("Modify Recipe", Color.decode("#FFFDE3"));
		bModify.setBounds(50,340,220, 70);

        bExit = new roundButton("Exit", Color.decode("#7D6E83"));
        bExit.setForeground(Color.decode("#EDDBC0"));
        bExit.setBounds(320,340,220, 70);

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
        lCreation.setFont(new Font("Sans-Serif", Font.BOLD, 30));
        lCreation.setBounds(0, 0, 600, 100);
        //lCreation.setForeground(Color.decode("#1C6758"));

		lCreation1 = new JLabel("Enter recipe name: ");
        lCreation1.setBounds(50, 70, 600, 40);
		recipeNameTF = new roundTextField("", 30, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeNameTF.setBounds(50, 100, 485, 30);
        recipeNameTF.setMargin(new Insets(0,10,0,10));

		lCreation2 = new JLabel("Enter recipe description: ");
        lCreation2.setBounds(50, 125, 600, 40);
		recipeDescriptionTF = new roundTextField("", 30, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeDescriptionTF.setBounds(50, 155, 485, 30);
        recipeDescriptionTF.setMargin(new Insets(0,10,0,10));

		lCreation3 = new JLabel("Enter recipe ingredients (place each ingredient on a new line): ");
        lCreation3.setBounds(50, 180, 600, 40);
        recipeIngredientsTA = new roundTextArea("", 10, 45, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeIngredientsTA.setBounds(50, 210, 485, 60);
        recipeIngredientsTA.setMargin(new Insets(5,10,10,10));

		lCreation4 = new JLabel("Enter recipe steps (place each step on a new line): ");
        lCreation4.setBounds(50, 265, 600, 40);
        recipeStepsTA = new roundTextArea("", 10, 45, Color.decode("#EEF2E6"), Color.decode("#82A284"));
        recipeStepsTA.setBounds(50, 295, 485, 100);
        recipeStepsTA.setMargin(new Insets(5,10,10,10));

		// create buttons
		bRecipeToCreate = new roundButton("Submit",Color.decode("#FFFDE3"),Color.decode("#FFFDE3"));
        bRecipeToCreate.setBounds(330, 410, 170, 35);

		bBack1 = new roundButton("Go Back",Color.decode("#446A46"),Color.decode("#446A46"));
        bBack1.setForeground(Color.decode("#EEF2E6"));
        bBack1.setBounds(90, 410, 170, 35);

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
		pRetrieval.setBackground(Color.decode("#FFFDE3"));
        pRetrieval.setLayout(null);

		// create labels
		// lRetrieval = new JLabel("Recipe Retrieval",JLabel.CENTER);
		// lRetrieval.setForeground(Color.decode("#446A46"));
        // lRetrieval.setFont(new Font("Sans-Serif", Font.BOLD, 25));
        // lRetrieval.setBounds(0, 0, 600, 100);
        JLabel lRetrieval1 = new JLabel("Recipe",JLabel.RIGHT);
        JLabel lRetrieval2 = new JLabel("Retrieval",JLabel.RIGHT);
        lRetrieval1.setFont(new Font("Serif", Font.BOLD, 70));
        lRetrieval2.setFont(new Font("Serif", Font.BOLD, 70));
        lRetrieval1.setBounds(0,130,228,100);
        lRetrieval2.setBounds(0,200,300,100);

		// create buttons
		bBrowse = new roundButton("Browse Recipes", Color.decode("#FFFDE3"), Color.decode("#E3C770"),3);
        bBrowse.setBounds(350, 100, 200, 80);
		bSearch = new roundButton("Search For A Recipe", Color.decode("#FFFDE3"), Color.decode("#E3C770"),3);
        bSearch.setBounds(350, 200, 200, 80);
		bBack2 = new roundButton("Go Back", Color.decode("#E3C770"));
        //bBack2.setForeground(Color.decode("#FFFDE3"));
        bBack2.setBounds(350, 300, 200, 80);

		// add all components to panel
		//pRetrieval.add(lRetrieval);
        pRetrieval.add(lRetrieval1);
        pRetrieval.add(lRetrieval2);
		pRetrieval.add(bBrowse);
		pRetrieval.add(bSearch);
		pRetrieval.add(bBack2);

			/*
				"Recipe Browse" page
			*/

			// create panel
			pBrowse = new JPanel();
			pBrowse.setBackground(Color.decode("#FFFDE3"));
            pBrowse.setLayout(null);

			// create labels
			lBrowse = new JLabel("Browse Recipes");
			//lBrowse.setForeground(Color.WHITE);
            lBrowse.setBounds(70,20,600,100);
            lBrowse.setFont(new Font("Serif", Font.BOLD, 40));

			// get all recipes
			String allRecipes = RecipeRetrieval.allRecipeNames(); // returns string of recipe list where recipes are seperated by \n
			recipes = new roundTextArea(allRecipes, 15, 45, Color.decode("#FEFEEE"), Color.decode("#E3C770"));
            recipes.setBounds(70,110,440,220);
            recipes.setEditable(false);
            recipes.setMargin(new Insets(15,15,15,15) );
            recipes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
			// create buttons
			bBack4 = new roundButton("Go Back", Color.decode("#E3C770"));
            bBack4.setBounds(300,350,210,40);

			// add all components to panel
			pBrowse.add(lBrowse);
			pBrowse.add(recipes);
			pBrowse.add(bBack4);

			/*
				"Recipe Search" page
			*/
			// create panel
			pSearch = new JPanel();
			pSearch.setBackground(Color.decode("#FFFDE3"));
            pSearch.setLayout(null);

			// create labels & text fields
			lSearch = new JLabel("Search For A Recipe",JLabel.CENTER); // spaces for formatting purposes
            lSearch.setFont(new Font("Serif", Font.BOLD, 40));
            lSearch.setBounds(0,0,570,200);

			lSearch1 = new JLabel("Enter recipe name: ");
            lSearch1.setFont(new Font("Serif", Font.PLAIN, 20));
            lSearch1.setBounds(70, 150, 440, 50);

			recipeToSearchTF = new roundTextField("", 35,Color.decode("#FEFEEE"),Color.decode("#E3C770"));
			recipeToSearchTF.setBounds(70,200,440,100);
            recipeToSearchTF.setMargin(new Insets(5,20,50,15));

            // create buttons
			bRecipeToSearch = new roundButton("Submit",Color.decode("#F0F2B6"));
            bRecipeToSearch.setBounds(320,320,170,40);
			bBack5 = new roundButton("Go Back", Color.decode("#E3C770"));
            bBack5.setBounds(90, 320, 170, 40);

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
		pModify.setBackground(Color.decode("#FCFFE7"));
        pModify.setLayout(null);
        
		// create labels
		lModify = new JLabel("Recipe Modification",JLabel.CENTER); // spaces for formatting purposes
        lModify.setFont(new Font("Serif", Font.BOLD, 40));
        lModify.setBounds(0, 0, 600, 200);

		lModify1 = new JLabel("Feature Coming Soon in the Next Update!",JLabel.CENTER);
		lModify1.setFont(new Font("Serif", Font.PLAIN, 25));
		lModify1.setBounds(0,150,590,100);

        lModify2 = new JLabel("Check Back in 2023.",JLabel.CENTER); // spaces for formatting purposes
		lModify2.setFont(new Font("Serif", Font.PLAIN, 25));
		lModify2.setBounds(0,200,590,100);

		// create buttons
		bBack3 = new roundButton("Go Back",Color.decode("#E0D8B0"));
        bBack3.setBounds(190, 330, 220, 50);

		// add all components to panel
		pModify.add(lModify);
		pModify.add(lModify1);
		pModify.add(lModify2);
		pModify.add(bBack3);

        drawRectangle R1 = new drawRectangle(50,100,400,20,"#E0D8B0");
        R1.setBounds(100,110,400,15);
        pModify.add(R1);


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
        private Color fillColor;
        private Color borderColor;
        private int thickness;
    
        public roundButton(String s ,Color fill, Color border,int thickness)  {
            super(s);
            setOpaque(false); 
            setContentAreaFilled(false);
            this.fillColor = fill;
            this.borderColor = border;
            this.thickness=thickness;
        }

        public roundButton(String s ,Color fill, Color border)  {
            super(s);
            setOpaque(false); 
            setContentAreaFilled(false);
            this.fillColor = fill;
            this.borderColor = border;
            this.thickness=1;
        }

        public roundButton(String s ,Color fill)  {
            super(s);
            setOpaque(false); 
            setContentAreaFilled(false);
            this.fillColor = fill;
            this.borderColor = fill;
            this.thickness=1;
        }

        public void paintComponent(Graphics g)   
        {
            g.setColor(this.fillColor);
            g.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 12, 12);
            super.paintComponent(g);	
        }
        
        public void paintBorder(Graphics g) {
            Color oldColor = g.getColor();
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(this.borderColor);
            for(int i = 0; i < thickness; i++)  {
                g2d.setStroke(new BasicStroke(thickness));
                if(thickness>1){
                    g2d.drawRoundRect(thickness/2, thickness/2, getWidth()-thickness, getHeight()-thickness, 12, 12);
                } else {        
                    g2d.drawRoundRect(0, 0, getWidth()-2, getHeight()-2, 12, 12);       
                }

            }
            g2d.setColor(oldColor);        
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
        public roundTextField(String text, int colomns, Color fill) {
            super(text,colomns);
            setOpaque(false); 
            this.fillColor = fill;
            this.borderColor = fill;
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

        public roundTextArea(String text, int rows, int colomns, Color fill) {
            super(text,rows,colomns);
            setOpaque(false); 
            this.fillColor = fill;
            this.borderColor = fill;
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

    public class drawRectangle extends JPanel{
        private int x;
        private int y;
        private int width;
        private int height;
        private Color fillColor;
        private Color borderColor;

        public drawRectangle(int x, int y, int width, int height, String fill, String border){
            //super();
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.fillColor = Color.decode(fill);
            this.borderColor = Color.decode(border);
        }

        public drawRectangle(int x, int y, int width, int height, String fill){
            super();
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.fillColor = Color.decode(fill);
            this.borderColor = Color.decode(fill);
        }

        @Override
        protected void paintComponent(Graphics g){
            //Graphics2D g2 = (Graphics2D) g;
            g.setColor(fillColor);
            g.fillRect(0,0, width, height);
            g.setColor(borderColor);
            g.drawRect(0, 0, width, height);
        }

    }
}
