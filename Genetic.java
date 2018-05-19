import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import jdk.nashorn.internal.runtime.Context.ThrowErrorManager;

/**
 * This class represents the used algorithm
 * It contains all required phases in the algorithm: selection, crossing over and mutation
 */
public class Genetic {

	private ArrayList<Conference> currentPopulation;
	
	public ArrayList<Conference> getCurrentPopulation() {
		return currentPopulation;
	}

	public void setCurrentPopulation(ArrayList<Conference> currentPopulation) {
		this.currentPopulation = currentPopulation;
	}



	private int population_size;
	private Conference bestConference;
	private double currentScore;
	private int sameScoreIter = 0;

	/**
	 * Constructor for a Genetic Algorithm. It starts the algorithm with the Selection Phase
	 * @param initialPopulation population to be used in the algorithm
	 */
	public Genetic(ArrayList<Conference> initialPopulation) {
		this.currentPopulation = initialPopulation;
		population_size = this.currentPopulation.size();

		for (int i = 0; i < Utilities.MAX_ITERATIONS; i++){
//			System.out.println("----------------------------------------------------");
//			System.out.println("Current Iteration " + i);

			selectionPhase();
			pairingPhase();
			crossingOverPhase();
			mutationPhase();
			setBestConference();
		}
		System.out.println("Best Conference");
		System.out.println(bestConference.getScore() + "\n");
		System.out.println(bestConference.toString());

		
		//TODO
		/*
		 * Emparelhamento
		 * corrigir a gera��o aleat�ria
		 */
	}

	/**
	 * Selection Phase.
	 * It sets whether to use an elitist or probabilistic selection, 
	 * based on user input
	 */
	private void selectionPhase() {


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



	}

	/**
	 * Probabilistic Selection
	 * @return
	 */
	private ArrayList<Conference> probabilistic() {	

		ArrayList<Conference> populationForRoulette = setPopulationForRoulette();
		
		while (populationForRoulette.size() == 0) {
			System.out.println("tamamho nao aceitavewl -> " + populationForRoulette.size());
			currentPopulation = new ArrayList<Conference>();
			for (int i = 0; i < Utilities.POPULATION_SIZE; i++) 
				currentPopulation.add(new Conference(Genetic.generateRandomPopulation()));
			 populationForRoulette = setPopulationForRoulette();
		}
		
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

//		System.out.println("poprolette" +populationForRoulette.size());
		return populationForRoulette;
	}


	/**
	 * Returns the best conferences from the population
	 * @param N Number of desired conferences
	 * @return ArrayList containing the best conferences
	 */
	private ArrayList<Conference> getNBestConfs(int N) {		

		currentPopulation.sort(Comparator.comparingDouble(Conference::getScore).reversed());

		return new ArrayList<Conference>(currentPopulation.subList(0,N));
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

			int index = Arrays.binarySearch(cumulativeFitnesses, randomFitness);

			if (index < 0)
				index = Math.abs(index + 1);


			selection.add(new Conference(population.get(index).getCromossome()));
		}
		return selection;
	}



	private void pairingPhase() {
		setCurrentPopulation(emparelhate());
	}

	private void crossingOverPhase() {
		int counter = currentPopulation.size() % 2 == 0 ? currentPopulation.size()-1 : currentPopulation.size();
		ArrayList<Conference> aux = new ArrayList<Conference>();
		
		for (int i = 0; i < counter-2 ; i+=2) {
//			System.out.println(i+1);
			
			aux.addAll(crossCromossomes(currentPopulation.get(i), currentPopulation.get(i+1)));
		}

		setCurrentPopulation(aux);
	}

	/**
	 * Mutation Phase.
	 * In order to have diversity, it tries to mutate a bit from one string of the population,
	 * by fliping said bit
	 */
	private void mutationPhase() {
		Random rand = new Random();
		ArrayList<Integer> mutations = new ArrayList<Integer>();
		int cromossomeSize =  Utilities.getCromossomeSize();
		double pm = 0.01;
		int times = currentPopulation.size() * cromossomeSize;
		for (int i = 0; i < times; i++) {
			double n = rand.nextDouble();
			if (n < pm)
				mutations.add(i);
		}

		for (int a: mutations) {
			int cromossomeIndex = a / cromossomeSize;
			int posInCromossome = a % cromossomeSize;
			currentPopulation.get(cromossomeIndex).setMutateBit(posInCromossome);
		}

		
	}

	/*private ArrayList<String> emparelhar(ArrayList<String> arr ) {
		ArrayList<String> array2 = new ArrayList<String>();
		for (int i = 0; i < arr.length( ) ; i++) {
			array2.add(cruzamento(arr[i], arr[i+1])); 
		}

	}*/

	private static ArrayList<Conference> crossCromossomes(Conference c1, Conference c2) {

		if (c1.getCromossome().length() != c2.getCromossome().length()) {
			throw new java.lang.RuntimeException("Error! The cromossome's sizes are different.");
		}

		String offspring1 = "";
		String offspring2 = "";
		int offset = 0;

		//      DIA                (HORA   TEMA    DURA    (AP AA AA TT TT  full)  (papper2))           (HORA TEMA DUR		    (paper)   (paper2))
		//		String cromo =  "00  11   11      001    00 00 01  10 11  1    01011110110           01      00 11 001 00000110111  01011110110"; 
		//		String cromo2 = "01  01   01      000    01 10 00  00 10  1    01101110110           01      10 01 011 01010110101  11010111100";

//		System.out.println("Total lenght: " + c1.getCromossome().length());
		
		ArrayList<String> aux;
				
		for (int days = 0 ; days < Utilities.DAYS ; days ++) {
			aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.DAYS), c2.getCromossome().substring(offset, offset+Utilities.DAYS));
			offspring1 += aux.get(0);
			offspring2 += aux.get(1);

			offset += Utilities.DAYS;

			for (int sessions = 0 ; sessions < Utilities.SESSIONS_PER_PERIOD ; sessions++) {
				// offset + 2 porque o número de bits que representam o periodo é 2
				aux = crossAux(c1.getCromossome().substring(offset, offset+2), c2.getCromossome().substring(offset, offset+2));
				offspring1 += aux.get(0);
				offspring2 += aux.get(1);
				offset += 2;
//				System.out.println("current offset: " + offset);

				// Tema de sessao
				aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.SESSION_THEME), c2.getCromossome().substring(offset, offset+Utilities.SESSION_THEME));
				offspring1 += aux.get(0);
				offspring2 += aux.get(1);
				offset += Utilities.SESSION_THEME;
//				System.out.println("current offset: " + offset);

				// duração da sessao
				aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.DURATION), c2.getCromossome().substring(offset, offset+Utilities.DURATION));
				offspring1 += aux.get(0);
				offspring2 += aux.get(1);
				offset += Utilities.DURATION;
//				System.out.println("current offset: " + offset);

				for (int paper = 0 ; paper < Utilities.PAPERS_PER_SESSION ; paper++) {
					// Presenter
					aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.PRESENTER), c2.getCromossome().substring(offset, offset+Utilities.PRESENTER));
					offspring1 += aux.get(0);
					offspring2 += aux.get(1);
					offset += Utilities.PRESENTER;
//					System.out.println("current offset: " + offset);

					for (int authors = 0 ; authors < Utilities.AUTHORS_PER_PAPER ; authors++) {
						// Authors
						aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.AUTHORS), c2.getCromossome().substring(offset, offset+Utilities.AUTHORS));
						offspring1 += aux.get(0);
						offspring2 += aux.get(1);
						offset += Utilities.AUTHORS;						
					}

					for (int themes = 0 ; themes < Utilities.THEMES_PER_PAPER; themes++) {
						// Themes
						aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.THEME), c2.getCromossome().substring(offset, offset+Utilities.THEME));
						offspring1 += aux.get(0);
						offspring2 += aux.get(1);
						offset += Utilities.THEME;						
					}

					// Is full paper (defined always by 1 bit)
					aux = crossAux(c1.getCromossome().substring(offset, offset+1), c2.getCromossome().substring(offset, offset+1));
					offspring1 += aux.get(0);
					offspring2 += aux.get(1);
					offset += 1;

				}


			}
			//			offset += Utilities.DAY;
		}

//		System.out.println("offset: "+ offset);

//		Utilities.getCromossomeSize();

		if (c1.getCromossome().length() != offset) {
			throw new java.lang.RuntimeException("Error! The cromossome and the generated cromossome size are different.");
		}
		
		ArrayList<Conference> children = new ArrayList<Conference>();
		
		children.add(new Conference(offspring1));
		children.add(new Conference(offspring2));
		return children;
	}

	public static ArrayList<String> crossAux(String s1, String s2) {
		Random rand = new Random();
		int n = rand.nextInt(101);
		ArrayList<String> aux = new ArrayList<String>();

		if (n < Utilities.CROSSING_RATIO * 100) {
			aux.add(" " + s1);
			aux.add(" " + s2);
		} else {
			aux.add(" " + s2);
			aux.add(" " + s1);
		}
		
		return aux;
	}

	/**
	 * Generates a random Cromossome
	 * @return new cromossome
	 */
	public static String generateRandomPopulation() {
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


	ArrayList<Conference> emparelhate() {

		double pc = 0.25; //user input
		ArrayList<Conference> paired = new ArrayList<Conference>();
		for(int i = 0; i < population_size; i++) {
			Random rand = new Random();		
			double n = rand.nextDouble();
			if (n < pc)
				paired.add(currentPopulation.get(i));
		}

//		ArrayList<Conference> array2 = new ArrayList<Conference>();

//		if (paired.size()%2 == 0) {
//			for (int i = 0; i < paired.size()-2 ; i+=2) {
//				array2.addAll(crossCromossomes(paired.get(i), paired.get(i+1)));  
//			}
//		}

//		System.out.println(paired.size());
		return paired;
	}



	/**
	 * Sets the Best Conference found
	 */
	private void setBestConference() {

		bestConference = currentPopulation.stream().max(Comparator.comparing(v -> v.getScore())).get();
		
	}

}
