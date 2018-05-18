import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import jdk.nashorn.internal.runtime.Context.ThrowErrorManager;

/**
 * This class represents the used algorithm
 * It contains all required phases in the algorithm: selection, crossing over and mutation
 */
public class Genetic {

	private ArrayList<Conference> initialPopulation;
	private ArrayList<Conference> currentPopulation;
	private int population_size;

	/**
	 * Constructor for a Genetic Algorithm. It starts the algorithm with the Selection Phase
	 * @param initialPopulation population to be used in the algorithm
	 */
	public Genetic(ArrayList<Conference> initialPopulation) {
		this.initialPopulation = initialPopulation;
		this.currentPopulation = initialPopulation;
		population_size = this.initialPopulation.size();
		selectionPhase();
		pairingPhase();
		crossingOverPhase();
		mutationPhase();
		//TODO
		/*
		 * Emparelhamento
		 * Muta��o
		 * condi��o de paragem
		 * corrigir a gera��o aleat�ria
		 */
	}

	/**
	 * Beginning of the Selection Phase.
	 * It sets whether to use an elitist or probabilistic selection, 
	 * based on user input
	 */
	private void selectionPhase() {

		for (Conference c : initialPopulation) 
			c.calculateScore();

		System.out.println("Initial Population");
		for (Conference c : initialPopulation) 
			System.out.println(c.getScore());

		Utilities.SELECTION selection_t = Utilities.SELECTION.PROBABILISTIC;

		//best n elements -> input asked
		int N = 1;

		switch(selection_t) {
		case ELITIST :
			currentPopulation = elitist(N);
			break;

		case PROBABILISTIC :
			currentPopulation = probabilistic();
			break;
		}


		System.out.println("selected population");
		for (Conference c : currentPopulation) 
			System.out.println(c.getScore());

	}

	/**
	 * Probabilistic Selection
	 * @return
	 */
	private ArrayList<Conference> probabilistic() {	

		ArrayList<Conference> populationForRoulette = setPopulationForRoulette();

		return selectionWheel(populationForRoulette, population_size, new Random());
	}

	/**
	 * Elitist Selection
	 * @param N Sets the N best individuals that always pass on the next generation(s)
	 * @return
	 */
	private ArrayList<Conference> elitist(int N) {

		ArrayList<Conference> bestN = getNBestConfs(N);

		currentPopulation.removeAll(bestN);

		ArrayList<Conference> populationForRoulette = setPopulationForRoulette();

		ArrayList<Conference> w = selectionWheel(populationForRoulette, population_size - bestN.size(), new Random());

		bestN.addAll(w);

		return bestN;
	}

	/**
	 * Sets the Roulette's Population, by calculating 
	 * the population's score and setting each individual's probability
	 * @return Returns the population for the Roulette to use
	 */
	private ArrayList<Conference> setPopulationForRoulette() {

		double sum = 0;
		ArrayList<Conference> populationForRoulette = new ArrayList<Conference>();
		for (Conference c : currentPopulation) 
			if (c.getScore()!= 0) {
				sum+=c.getScore();
				populationForRoulette.add(c);
			}

		for (Conference c : populationForRoulette) 
			c.setProbability(c.getScore()/sum);

		return populationForRoulette;
	}


	/**
	 * Returns the best conferences from the population
	 * @param N Number of desired conferences
	 * @return ArrayList containing the best conferences
	 */
	private ArrayList<Conference> getNBestConfs(int N) {		

		initialPopulation.sort(Comparator.comparingDouble(Conference::getScore).reversed());

		return new ArrayList<Conference>(initialPopulation.subList(0,N));
	}


	private ArrayList<Conference> fillPopulation(ArrayList<Conference> best) {


		int div = Utilities.POPULATION_SIZE / best.size();
		int module = Utilities.POPULATION_SIZE % best.size();

		ArrayList<Conference> newPopulation = new ArrayList<Conference>();

		for (int i = 0 ; i < div ; i++) 
			newPopulation.addAll(best);

		for (int i = 0 ; i < module ; i++)
			newPopulation.add(best.get(i));

		return newPopulation;

	}

	/**
	 * Generates a wheel with all the probabilities from the population and 
	 * selects individuals, depending on the random generated number
	 * @param population Population of individuals
	 * @param selectionSize Size of the initial population
	 * @param rng Random generated number
	 * @return ArrayList with the selected individuals
	 */
	private ArrayList<Conference> selectionWheel(ArrayList<Conference> population, int selectionSize, Random rng){	
		double[] cumulativeFitnesses = new double[population.size()];
		cumulativeFitnesses[0] = population.get(0).getProbability();

		for (int i = 1; i < population.size(); i++)
			cumulativeFitnesses[i] = cumulativeFitnesses[i - 1] + population.get(i).getProbability();

		ArrayList<Conference> selection = new ArrayList<Conference>(selectionSize);
		for (int i = 0; i < selectionSize; i++)
		{			
			double randomFitness = rng.nextDouble() * cumulativeFitnesses[cumulativeFitnesses.length - 1];
			System.out.println("random: " + randomFitness);
			int index = Arrays.binarySearch(cumulativeFitnesses, randomFitness);

			if (index < 0)
				index = Math.abs(index + 1);

			selection.add(population.get(index));
		}
		return selection;
	}


	private void pairingPhase() {
		// TODO Auto-generated method stub

	}

	private void crossingOverPhase() {
		// TODO Auto-generated method stub

	}

	private void mutationPhase() {
		// TODO Auto-generated method stub

	}

	/*private ArrayList<String> emparelhar(ArrayList<String> arr ) {
		ArrayList<String> array2 = new ArrayList<String>();
		for (int i = 0; i < arr.length( ) ; i++) {
			array2.add(cruzamento(arr[i], arr[i+1])); 
		}

	}*/

	private static String crossCromossomes(Conference c1, Conference c2) {

		if (c1.getCromossome().length() != c2.getCromossome().length()) {
			throw new java.lang.RuntimeException("Error! The cromossome's sizes are different.");
		}

		String newCromossome = "";
		int offset = 0;

		//      DIA                (HORA   TEMA    DURA    (AP AA AA TT TT  full)  (papper2))           (HORA TEMA DUR		    (paper)   (paper2))
		//		String cromo =  "00  11   11      001    00 00 01  10 11  1    01011110110           01      00 11 001 00000110111  01011110110"; 
		//		String cromo2 = "01  01   01      000    01 10 00  00 10  1    01101110110           01      10 01 011 01010110101  11010111100";

		System.out.println("Total lenght: " + c1.getCromossome().length());

		for (int days = 0 ; days < Utilities.DAYS ; days ++) {
			newCromossome += crossAux(c1.getCromossome().substring(offset, offset+Utilities.DAYS), c2.getCromossome().substring(offset, offset+Utilities.DAYS));
			offset += Utilities.DAYS;

			for (int sessions = 0 ; sessions < Utilities.SESSIONS_PER_PERIOD ; sessions++) {
				// offset + 2 porque o número de bits que representam o periodo é 2
				newCromossome += crossAux(c1.getCromossome().substring(offset, offset+2), c2.getCromossome().substring(offset, offset+2));
				offset += 2;
				System.out.println("current offset: " + offset);

				// Tema de sessao
				newCromossome += crossAux(c1.getCromossome().substring(offset, offset+Utilities.SESSION_THEME), c2.getCromossome().substring(offset, offset+Utilities.SESSION_THEME));
				offset += Utilities.SESSION_THEME;
				System.out.println("current offset: " + offset);

				// duração da sessao
				newCromossome += crossAux(c1.getCromossome().substring(offset, offset+Utilities.DURATION), c2.getCromossome().substring(offset, offset+Utilities.DURATION));
				offset += Utilities.DURATION;
				System.out.println("current offset: " + offset);

				for (int paper = 0 ; paper < Utilities.PAPERS_PER_SESSION ; paper++) {
					// Presenter
					newCromossome += crossAux(c1.getCromossome().substring(offset, offset+Utilities.PRESENTER), c2.getCromossome().substring(offset, offset+Utilities.PRESENTER));
					offset += Utilities.PRESENTER;
					System.out.println("current offset: " + offset);

					for (int authors = 0 ; authors < Utilities.AUTHORS_PER_PAPER ; authors++) {
						// Authors
						newCromossome += crossAux(c1.getCromossome().substring(offset, offset+Utilities.AUTHORS), c2.getCromossome().substring(offset, offset+Utilities.AUTHORS));
						offset += Utilities.AUTHORS;						
					}

					for (int themes = 0 ; themes < Utilities.THEMES_PER_PAPER; themes++) {
						// Themes
						newCromossome += crossAux(c1.getCromossome().substring(offset, offset+Utilities.THEME), c2.getCromossome().substring(offset, offset+Utilities.THEME));
						offset += Utilities.THEME;						
					}

					// Is full paper (defined always by 1 bit)
					newCromossome += crossAux(c1.getCromossome().substring(offset, offset+1), c2.getCromossome().substring(offset, offset+1));
					offset += 1;

				}


			}
			//			offset += Utilities.DAY;
		}

		System.out.println("offset: "+ offset);

		Utilities.getCromossomeSize();

		if (c1.getCromossome().length() != offset) {
			throw new java.lang.RuntimeException("Error! The cromossome and the generated cromossome size are different.");
		}

		System.out.println("New Cromossome: " + newCromossome);
		return "";
	}

	public static String crossAux(String s1, String s2) {
		Random rand = new Random();
		int n = rand.nextInt(101);

		if (n < Utilities.CROSSING_RATIO * 100) {
			return " " + s1;
		} else {
			return " " + s2;
		}
	}

	/**
	 * Generates a random Cromossome
	 * @return new cromossome
	 */
	public static String generateRandomPoplation() {
		String newCromossome = "";


		for (int i = 0 ; i < Utilities.getCromossomeSize() ; i++) {
			Random rand = new Random();
			int n = rand.nextInt(101);
			if (n < 50) {
				newCromossome += "0";
			} else {
				newCromossome += "1";
			}
		}

		return newCromossome;
	}
	
	
	public static ArrayList<Conference> emparelhate(ArrayList<Conference> arr) {
		ArrayList<Conference> array2 = new ArrayList<Conference>();
		
		for (int i = 0; i < arr.size()-2 ; i+=2) {
			array2.add(new Conference(crossCromossomes(arr.get(i), arr.get(i+1)))); 
		}
		for (int i = 0; i < arr.size()-2 ; i+=2) {
			array2.add(new Conference(crossCromossomes(arr.get(i), arr.get(i+1)))); 
		}
		
		return array2;
	}
	
}
