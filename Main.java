import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;





/**
 * Main Class. Starts the program with a simple UI
 * to have some input data from the User
 */
public class Main {
	public static void main(String[] args) {


		//		try {
		//			new Database();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}


		showDialog();	

		if (Utilities.selection_t == SELECTION.ELITIST) {
			showElitisDialog(); 		 
		}



		ArrayList<Conference> p = new ArrayList<Conference>();

		for (int i = 0; i < Utilities.POPULATION_SIZE ; i++) 
			p.add(new Conference(Genetic.generateRandomPopulation()));	

		Genetic g = new Genetic(p);	
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		JLabel text = new JLabel();
		
		text.setText("<html>" + g.getBestConference().toString().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

		panel.add(text);
		JOptionPane.showMessageDialog(null,panel,"Results",JOptionPane.DEFAULT_OPTION);
	}


	public static void showDialog() {

		JTextField maxPopTextField = new JTextField("1000", 10);
		JTextField numberDaysTextField = new JTextField("3", 10);
		JTextField iterationsTextField = new JTextField("200", 10);


		do {

			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			String[] choices = { "Probabilistic","Elitistic" };
			final JComboBox<String> selectionMethodComboBox = new JComboBox<String>(choices);

			JLabel popSizeLabel = new JLabel("What is the size of the population?");
			JLabel selectionMethodLabel = new JLabel("Which selection method would you like to use?");
			JLabel numberDaysLabel = new JLabel("What is the number of days you want?");

			JLabel iterationsLabel = new JLabel("What is the max number of iteretions do you want to perform?");

			panel.add(popSizeLabel);
			panel.add(maxPopTextField);
			panel.add(Box.createVerticalStrut(20));
			panel.add(selectionMethodLabel);
			panel.add(selectionMethodComboBox);
			panel.add(Box.createVerticalStrut(20));
			panel.add(numberDaysLabel);
			panel.add(numberDaysTextField);
			panel.add(Box.createVerticalStrut(20));
			panel.add(iterationsLabel);
			panel.add(iterationsTextField);

			JOptionPane.showMessageDialog(null,panel,"Settings",JOptionPane.DEFAULT_OPTION);

			if (selectionMethodComboBox.getSelectedIndex() == 0) {
				Utilities.selection_t = SELECTION.PROBABILISTIC;
			} else {
				Utilities.selection_t = SELECTION.ELITIST;
			}

			if (Utilities.containsOnlyNumbers(maxPopTextField.getText()) && Utilities.containsOnlyNumbers(numberDaysTextField.getText()) && Utilities.containsOnlyNumbers(iterationsTextField.getText())) {
				Utilities.POPULATION_SIZE = Integer.parseInt(maxPopTextField.getText());
				Utilities.DAYS = Integer.parseInt(numberDaysTextField.getText());
//				System.out.println("DAYS BITS: " + Utilities.DAYS);
				Utilities.DAYSBITS = (int) Math.ceil( Math.log10(Utilities.DAYS) / Math.log10(2.) );
//				System.out.println("DAYS BITS: " + Utilities.DAYSBITS);
				Utilities.MAX_ITERATIONS = Integer.parseInt(iterationsTextField.getText());

				return;
			}

		} while (true);

	}

	public static void showElitisDialog() {

		do {
			JTextField nextPopulationTextField = new JTextField("100", 10);
			JLabel nextGenerationLabel = new JLabel("How many cromossomes do you want to advance to the next generation?");

			JPanel panelElitis = new JPanel();
			panelElitis.setLayout(new BoxLayout(panelElitis, BoxLayout.Y_AXIS));
			panelElitis.add(nextGenerationLabel);
			panelElitis.add(nextPopulationTextField);
			JOptionPane.showMessageDialog(null, panelElitis, "Settings", JOptionPane.DEFAULT_OPTION);

			if (Utilities.containsOnlyNumbers(nextPopulationTextField.getText())) {
				Utilities.ELITIST_NUMBER = Integer.parseInt(nextPopulationTextField.getText());
				return;
			}

		} while(true);
	}

}




