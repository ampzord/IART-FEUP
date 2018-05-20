package src;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import database.Database;


/**
 * Main Class. Starts the program with a simple UI
 * to have some input data from the User
 */
public class Main {
	public static void main(String[] args) {


		try {
			new Database();
		} catch (IOException e) {e.printStackTrace();}


		showDialog();	

		if (Utilities.selection_t == SELECTION.ELITIST) 
			showElitisDialog(); 		 
		

		ArrayList<Conference> p = new ArrayList<Conference>();

		for (int i = 0; i < Utilities.POPULATION_SIZE ; i++) 
			p.add(new Conference(Genetic.generateRandomPopulation()));	

		Genetic g = new Genetic(p);	
		
		showResults(g);
	}
	
	public static void showResults(Genetic g) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        JLabel text = new JLabel();
		text.setText("<html>" + g.getBestConference().toString().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		panel.add(text);        
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(0, 0, 500, 400);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
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

			JLabel popSizeLabel = new JLabel("What is the population size?");
			JLabel selectionMethodLabel = new JLabel("What selection method would you like to use?");
			JLabel numberDaysLabel = new JLabel("What number of that days that you want?");
			JLabel iterationsLabel = new JLabel("What is the max number of iterations that you want to perform?");

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
				Utilities.DAYSBITS = (int) Math.ceil( Math.log10(Utilities.DAYS) / Math.log10(2.) );
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




