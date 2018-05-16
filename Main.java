import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {
	public static void main(String[] args) {


		// escala-se as variaveis para inteiros multiplicando-as por 10^n, onde n =
		// precis�o
		// precis�o pode ser usada para diferentes desempenhos

		// Transformar novas vari�veis em forma bin�ria ou usar como ints compridos

		/*
		 * SELE��O fa(Ci) -> adapta��o do Cromossoma Ci(fun��o de adapta��o) Sum(fa(ci))
		 * -> soma das adapta��es de toda a popula��o P(Ci escolhido) =
		 * fa(Ci)/Sum(fa(ci)) S�o selecionados alguns daqui
		 */

		/*
		 * REPRODU��O Os selecionados sao emparelhados aleatoriamente Por par, com
		 * Pcrossover, pelo menos 1 crossover point � escolhido, de 1 a
		 * N(Cromossoma.size()) Troca-se o material genetico
		 */

		/*
		 * MUTA��O Pode acontecer com Pm muito baixo
		 */



		// DIA HORA TEMA DURA PAPERS full


		System.out.println("Cromossomo padrao com 1 dia, 1 sessao e 1 papers");
		//		 		 DIA    HORA    TEMA     DURA    AA AA	TT TT   full
		//String cromo = "01 -  11      11       001     00 01  10 11   1";

		//		String cromo = "01111100101000110111";
		//		Conference c1 = new Conference(cromo);
		//		System.out.println(c1);



		System.out.println("Cromossomo padrao com 1 dia, 2 sessao e 1 papers");
		//		          DIA    HORA   TEMA    DURA    AP AA AA	TT TT   full       Sessao 2
		//String cromo = "01 -  11      11       001    00  00 01  10 11   1          0111001  01 000110111

		//		String cromo = "01111100100000110111111100100000110111";
		//		Conference c1 = new Conference(cromo);
		//		System.out.println(c1);



		System.out.println("Cromossomo padrao com 1 dia, 2 sessao e 2 papers");
		//                DIA    HORA   TEMA    DURA   AP AA AA	TT TT  full  papper2        Sessao 2		     paper 2
		//String cromo = "01 -  11      11       001   00  00 01  10 11  1          			01 11 001 000110111  011110110 
		//           paper				paper 2
		// Cromossomo com apresentadores diferentes e funcionando
		String cromo = "01 11 11     111 11 10 11 10 11 1   01 00 01 10 11 1    1111001  00000110111  10100000011";
		Conference c1 = new Conference(cromo);
		System.out.println(c1);


		String cromo1 = "01111100100000110110111100100100110011";
		Conference c2 = new Conference(cromo1);
		System.out.println(c2);

		String cromo2 = "01111100100000110110110000100100110011";
		Conference c3 = new Conference(cromo2);
		ArrayList<Conference> p = new ArrayList<Conference>();
		
		p.add(c1); p.add(c2); p.add(c3);
		

		System.out.println("before wheel");

		double sum = 0;

		int populationSize = p.size();
		ArrayList<Conference> populationForWheel = new ArrayList<Conference>();
		for (Conference c : p) {
			c.calculateScore();
			if (c.getScore()!= 0) {
				sum+=c.getScore();
				System.out.println(c.getScore());
				populationForWheel.add(c);
			}
		}
		for (Conference c : populationForWheel) {
			c.setProbability(c.getScore()/sum);
			System.out.println(c.getProbability());
		}
		
		ArrayList<Conference> w = Genetic.selectionWheel(populationForWheel,populationSize, new Random());


		System.out.println("after wheel");

		for (Conference c : w)
			System.out.println(c.getScore());



		System.out.println("Cromossomo padrao com 2 dia, 1 sessao e 2 papers");
		//                DIA    HORA   TEMA    DURA    AP AA AA	TT TT  full  papper2             Sessao 2		     paper 2
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


		ArrayList<Conference> population = new ArrayList<Conference>();



		//populate population randomly


		//calculate fitness score of all population
		//		for(Conference c : population) {
		//			c.calculateScore();
		//		}
		//		
		//		Genetic g = new Genetic(population);








		//create randomly cromossomes



		//save score


		// if(userInput == "elitist"








	}
}
