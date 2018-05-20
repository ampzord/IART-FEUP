import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * This class represents the used algorithm
 * It contains all required phases in the algorithm: selection, crossing over and mutation
 */
public class Genetic {

	private ArrayList<Conference> currentPopulation;
	private int population_size;
	private Conference bestConference;

	/**
	 * Constructor for a Genetic Algorithm. It starts the algorithm with the Selection Phase
	 * @param initialPopulation population to be used in the algorithm
	 */
	public Genetic(ArrayList<Conference> initialPopulation) {
		this.currentPopulation = initialPopulation;
		population_size = this.currentPopulation.size();

		for (int i = 0; i < Utilities.MAX_ITERATIONS; i++){

			selectionPhase();
			pairingPhase();
			crossingOverPhase();
			mutationPhase();
			setBestConference();
		}
		System.out.println("Best Conference");
		System.out.println(bestConference.getScore() + "\n");
		System.out.println(bestConference.toString());

	}

	/**
	 * Selection Phase.
	 * It sets whether to use an elitist or probabilistic selection, 
	 * based on user input
	 */
	private void selectionPhase() {
		

		int N = Utilities.ELITIST_NUMBER;

		switch(Utilities.selection_t) {
		case ELITIST :
			currentPopulation = elitist(N);
			break;

		case PROBABILISTIC :
			currentPopulation = probabilistic();
			break;
		}

	}

	private ArrayList<Conference> setCurrentPopulationForRoulette() {

		ArrayList<Conference> populationForRoulette = setPopulationForRoulette();

		while (populationForRoulette.size() == 0) {
			currentPopulation = new ArrayList<Conference>();
			for (int i = 0; i < Utilities.POPULATION_SIZE; i++) 
				currentPopulation.add(new Conference(Genetic.generateRandomPopulation()));

			int sum = 0;
			for (Conference c : currentPopulation)
				if (c.getScore() != 0)
					sum++;
			populationForRoulette = setPopulationForRoulette();
		}

		return populationForRoulette;

	}

	/**
	 * Probabilistic Selection
	 * @return New Population after being selected
	 */
	private ArrayList<Conference> probabilistic() {	

		ArrayList<Conference> newPopulation = setCurrentPopulationForRoulette();

		return selectionWheel(newPopulation, population_size, new Random());
	}

	/**
	 * Elitist Selection
	 * @param N Sets the N best individuals that always pass on the next generation(s)
	 * @return New Population after being selected
	 */
	private ArrayList<Conference> elitist(int N) {

		ArrayList<Conference> bestN = getNBestConfs(N);

		currentPopulation.removeAll(bestN);
		
		ArrayList<Conference> populationForRoulette = setCurrentPopulationForRoulette();

		ArrayList<Conference> w = selectionWheel(populationForRoulette, population_size, new Random());

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

		currentPopulation.sort(Comparator.comparingDouble(Conference::getScore).reversed());

		return new ArrayList<Conference>(currentPopulation.subList(0,N));
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


	/**
	 * Pairing Phase
	 * In this phase, the algorithm pairs some cromossomes from the population
	 * so they can be crossed
	 */
	private void pairingPhase() {
		setCurrentPopulation(pairCromossomes());
	}

	/**
	 * Crossing Phase.
	 * The paired cromossomes are crossed and their children 
	 * replace the parents
	 */
	private void crossingOverPhase() {
		int counter = currentPopulation.size() % 2 == 0 ? currentPopulation.size()-1 : currentPopulation.size();
		ArrayList<Conference> aux = new ArrayList<Conference>();

		for (int i = 0; i < counter-2 ; i+=2)
			aux.addAll(crossCromossomes(currentPopulation.get(i), currentPopulation.get(i+1)));
		
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
		int times = currentPopulation.size() * cromossomeSize;
		for (int i = 0; i < times; i++) {
			double n = rand.nextDouble();
			if (n < Utilities.MUTATION_PROB)
				mutations.add(i);
		}

		for (int a: mutations) {
			int cromossomeIndex = a / cromossomeSize;
			int posInCromossome = a % cromossomeSize;
			currentPopulation.get(cromossomeIndex).setMutateBit(posInCromossome);
		}

	}

	/**
	 * Crosses two cromossomes and returns their children
	 * @param c1 Cromossome to be crossed
	 * @param c2 Cromossome to be crossed
	 * @return The children of the crossing
	 */
	private static ArrayList<Conference> crossCromossomes(Conference c1, Conference c2) {

		if (c1.getCromossome().length() != c2.getCromossome().length()) {
			throw new java.lang.RuntimeException("Error! The cromossome's sizes are different.");
		}

		String offspring1 = "";
		String offspring2 = "";
		int offset = 0;

		ArrayList<String> aux;

		for (int days = 0 ; days < Utilities.DAYS ; days ++) {
			aux = crossAux(c1.getCromossome().substring(offset, offset + Utilities.DAYSBITS), c2.getCromossome().substring(offset, offset+Utilities.DAYSBITS));
			offspring1 += aux.get(0);
			offspring2 += aux.get(1);

			offset += Utilities.DAYSBITS;

			for (int sessions = 0 ; sessions < Utilities.SESSIONS_PER_PERIOD ; sessions++) {
			
				aux = crossAux(c1.getCromossome().substring(offset, offset+2), c2.getCromossome().substring(offset, offset+2));
				offspring1 += aux.get(0);
				offspring2 += aux.get(1);
				offset += 2;
						
				aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.SESSION_THEME), c2.getCromossome().substring(offset, offset+Utilities.SESSION_THEME));
				offspring1 += aux.get(0);
				offspring2 += aux.get(1);
				offset += Utilities.SESSION_THEME;
						
				aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.DURATION), c2.getCromossome().substring(offset, offset+Utilities.DURATION));
				offspring1 += aux.get(0);
				offspring2 += aux.get(1);
				offset += Utilities.DURATION;
			
				for (int paper = 0 ; paper < Utilities.PAPERS_PER_SESSION ; paper++) {
			
					aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.PRESENTER), c2.getCromossome().substring(offset, offset+Utilities.PRESENTER));
					offspring1 += aux.get(0);
					offspring2 += aux.get(1);
					offset += Utilities.PRESENTER;
			
					for (int authors = 0 ; authors < Utilities.AUTHORS_PER_PAPER ; authors++) {
			
						aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.AUTHORS), c2.getCromossome().substring(offset, offset+Utilities.AUTHORS));
						offspring1 += aux.get(0);
						offspring2 += aux.get(1);
						offset += Utilities.AUTHORS;						
					}

					for (int themes = 0 ; themes < Utilities.THEMES_PER_PAPER; themes++) {
			
						aux = crossAux(c1.getCromossome().substring(offset, offset+Utilities.THEME), c2.getCromossome().substring(offset, offset+Utilities.THEME));
						offspring1 += aux.get(0);
						offspring2 += aux.get(1);
						offset += Utilities.THEME;						
					}

					aux = crossAux(c1.getCromossome().substring(offset, offset+1), c2.getCromossome().substring(offset, offset+1));
					offspring1 += aux.get(0);
					offspring2 += aux.get(1);
					offset += 1;

				}

			}
		}

		if (c1.getCromossome().length() != offset) {
			throw new java.lang.RuntimeException("Error! The cromossome and the generated cromossome size are different.");
		}

		ArrayList<Conference> children = new ArrayList<Conference>();

		children.add(new Conference(offspring1));
		children.add(new Conference(offspring2));
		
		return children;
	}

	/**
	 * Decides to which child the bits from the parents go
	 * @param s1 Cromossome 1's bits
	 * @param s2 Cromossome 2's bits
	 * @return Returns part of the child
	 */
	private static ArrayList<String> crossAux(String s1, String s2) {
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
			if (n < 50) 
				newCromossome += "0";
			else 
				newCromossome += "1";
		}

		return newCromossome;
	}


	/**
	 * Pairs some cromossomes from the population
	 * @return the paired cromossomes
	 */
	private ArrayList<Conference> pairCromossomes() {

		ArrayList<Conference> paired = new ArrayList<Conference>();
		for(int i = 0; i < population_size; i++) {
			Random rand = new Random();		
			double n = rand.nextDouble();
			if (n < Utilities.PAIRING_PROB)
				paired.add(currentPopulation.get(i));
		}
		return paired;
	}



	/**
	 * Sets the Best Conference found
	 */
	private void setBestConference() {

		bestConference = currentPopulation.stream().max(Comparator.comparing(v -> v.getScore())).get();

	}

	/**
	 * Sets the Current Population with a new population
	 * @param newPopulation new population
	 */
	private void setCurrentPopulation(ArrayList<Conference> newPopulation) {
		this.currentPopulation = newPopulation;
	}

}
