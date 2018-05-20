import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
		
		
		try {
			new Database();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JTextField maxPopTextField = new JTextField(10);
//		JTextField maxPopTextField = new JTextField(10);

	    String[] choices = { "Probabilistic","Elitistic" };

	    final JComboBox<String> cb = new JComboBox<String>(choices);

		
		JPanel panel = new JPanel();
//		 panel.add(new JButton("Click"));
	       panel.setLayout(new GridBagLayout());
	      GridBagConstraints gbc = new GridBagConstraints();
	      
	      // adiciona os compnentes na vertical 
//	      panel.add(label1, gbc);
//	        panel.add(label2, gbc);
	      
		 panel.add(new JLabel("What is the size of the population?"));
		 panel.add(maxPopTextField);
		 
		 
		 panel.add(new JLabel("Which selection method would you like to use?"));
		 cb.setVisible(true);
		    panel.add(cb);
		 
		 JOptionPane.showMessageDialog(null,panel,"Information",JOptionPane.INFORMATION_MESSAGE);

		 
		 Utilities.POPULATION_SIZE = Integer.parseInt(maxPopTextField.getText());
				 
		if (cb.getSelectedIndex() == 0)
			Utilities.selection_t = Utilities.SELECTION.PROBABILISTIC;
		else
			Utilities.selection_t = Utilities.SELECTION.ELITIST;
			
		 
//		String stringParaAvaliar = JOptionPane.showInputDialog("Qual o tamanho da strin");
//		do {
//
//
//			stringParaAvaliar = "";
//			stringParaAvaliar = JOptionPane.showInputDialog("Informe a String que deseja avaliar:");
//		} while (stringParaAvaliar != null || !stringParaAvaliar.trim().equals("")); 
//		System.exit(0);
		
		
		
		ArrayList<Conference> p = new ArrayList<Conference>();

		for (int i = 0; i < Utilities.POPULATION_SIZE ; i++) 
			p.add(new Conference(Genetic.generateRandomPopulation()));	
	
		new Genetic(p);	

	}
}

/*
 * 
 * /*
		 * REPRODU��O Os selecionados sao emparelhados aleatoriamente Por par, com
		 * Pcrossover, pelo menos 1 crossover point � escolhido, de 1 a
		 * N(Cromossoma.size()) Troca-se o material genetico
		 */

		/*
		 * MUTA��O Pode acontecer com Pm muito baixo
		 */



		// DIA HORA TEMA DURA PAPERS full


//		System.out.println("Cromossomo padrao com 1 dia, 1 sessao e 1 papers");
		//		 		 DIA    HORA    TEMA     DURA    AA AA	TT TT   full
		//String cromo = "01 -  11      11       001     00 01  10 11   1";

		//		String cromo = "01111100101000110111";
		//		Conference c1 = new Conference(cromo);
		//		System.out.println(c1);



//		System.out.println("Cromossomo padrao com 1 dia, 2 sessao e 1 papers");
		//		          DIA    HORA   TEMA    DURA    AP AA AA	TT TT   full       Sessao 2
		//String cromo = "01 -  11      11       001    00  00 01  10 11   1          0111001  01 000110111

		//		String cromo = "01111100100000110111111100100000110111";
		//		Conference c1 = new Conference(cromo);
		//		System.out.println(c1);



//		System.out.println("Cromossomo padrao com 1 dia, 2 sessao e 2 papers");
		//                DIA    HORA   TEMA    DURA   AP AA AA	TT TT  full  papper2        Sessao 2		     paper 2
		//String cromo = "01 -  11      11       001   00  00 01  10 11  1          			01 11 001 000110111  011110110 
		//           paper				paper 2
		// Cromossomo com apresentadores diferentes e funcionando
//		String cromo = "01 11 11     111 11 10 11 10 11 1   01 00 01 10 11 1    1111001  00000110111  10100000011";
//		Conference c1 = new Conference(cromo);
//		System.out.println(c1);
		
		
		
		
		//Para testar o algoritmo em si
//		String cromo = "01 11 11     111 11 10 11 10 11 1   01 00 01 10 11 1    1111001  00000110111  10100000011";
//		Conference c1 = new Conference(cromo);
//
//		String cromo1 = "01111100100000110110111100100100110011";
//		Conference c2 = new Conference(cromo1);
//
//		String cromo2 = "01111100100000110110110000100100110011";
//		Conference c3 = new Conference(cromo2);
 

//DIA    HORA   TEMA    DURA    AP AA AA	TT TT  full  papper2             Sessao 2		     paper 2
//String cromo = "01 -  11      11       001    00 00 01  10 11  1          			     01 11 001 00000110111  01011110110 
//
//		String cromo = "00  11   11      001    00 00 01  10 11  1    01011110110         01     00 11 001 00000110111  01011110110"; 
//		Conference c1 = new Conference(cromo);
//		System.out.println(c1);
//		
//		String cromo2 = "01  01   01      000    01 10 00  00 10  1    01101110110        01      10 01 011 01010110101  11010111100"; 
//		Conference c2 = new Conference(cromo2);
//		System.out.println(c2);
//		
//		Genetic.crossCromossomes(c1, c2);


//		Conference c3 = new Conference(Genetic.generateRandomPoplation());
//		Conference c4 = new Conference(Genetic.generateRandomPoplation());
//		
//		Genetic.crossCromossomes(c3, c4);


//		ArrayList<Conference> arr = new ArrayList<Conference>();
//		arr.add(c1);
//		arr.add(c2);

//		arr.sort(Comparator.comparingDouble(Conference::getScore));

//		System.out.println("score final: " + Genetic.getScore(c1));
// c1.calculateScore();
// System.out.println("Score final: " + c1.getScore());

// <DIA> <SESS�ES> <DIA> <SESS�ES> <DIA> <SESS�ES>, para 3 dias
// * Ex:
// * 00 01010101010 01 00000000000 10 11101010001
// <SESSOES>
// TT DD PPPPPP PPPPPPP

/*
* THE REAL DEAL NOW
* 
* Indiv�duo : uma solu��o que representa TODOS os dias e todas as sess�es
* associadas
* 
* <DIA> <SESS�ES> <DIA> <SESS�ES> <DIA> <SESS�ES>, para 3 dias Ex: 00
* 01010101010 01 00000000000 10 11101010001
* 
* 
* <Sess�es>: XX YYYYYYYYY XX representa o hor�rio da sess�o(inicio/fim da
* manh�/tarde) YYYY.. represneta o getGenome da classe session
* 
* 
*/


/* --------------------------------------------*/


//ask for user input

