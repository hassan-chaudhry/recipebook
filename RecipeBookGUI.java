import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RecipeBookGUI extends JFrame {

	// define variables
	private static CardLayout cardLayout;
	private static JFrame frame;
	private static JPanel cardPanel, pMain, pCreation, pRetrieval, pModify;
	private static JLabel lMain, lCreation, lRetrieval, lModify;
	private static JButton bBack1, bBack2, bBack3, bCreation, bRetrieval, bBrowse, bSearch, bModify;
	
	public static void main(String args[]) {
		RecipeBookGUI rbg = new RecipeBookGUI();
		rbg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		rbg.setVisible(true); 

		cardLayout.first(cardPanel);
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

		pMain.add(lMain); // add label to panel
		pMain.add(bCreation); // add button to panel
		pMain.add(bRetrieval); // add button to panel
		pMain.add(bModify); // add button to panel

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

		bModify.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent ae) {  
				cardLayout.show(cardPanel, "" + "Recipe Modification");
			}		  
		}); 

	}
}

