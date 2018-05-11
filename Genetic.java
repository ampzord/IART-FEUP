import java.util.ArrayList;
import java.util.Comparator;

import javax.naming.ldap.InitialLdapContext;

public class Genetic {
	
	private ArrayList<Conference> initialPopulation;
	private ArrayList<Conference> population;
	
	
	public Genetic(ArrayList<Conference> initialPopulation) {
		
		this.initialPopulation = initialPopulation;
		
		selection();
	
		
	}

	
	public void selection() {
		Utilities.SELECTION selection_t = Utilities.SELECTION.ELITIST;
			
		//best n elements -> input asked
		int N = 2;
			
		//if elitist
		switch(selection_t) {
			case ELITIST :
					population = elitist(N);
				break;
					
			case PROBABILISTIC :
					population = probabilistic();
				break;
			}
		
	}
	

	private ArrayList<Conference> probabilistic() {

		double totalSum = 0;
		
		for (Conference c : this.initialPopulation)
			totalSum += c.getScore();
			
		for (Conference c : this.initialPopulation)
			c.setProbability( c.getScore()/totalSum);
		
		
		
		
		return null;
	}


	public ArrayList<Conference> elitist(int N) {

		ArrayList<Conference> bestN = getNBestConfs(N);
		
		return fillPopulation(bestN);
		
	}
	
	
	private ArrayList<Conference> getNBestConfs( int N) {		
	
		initialPopulation.sort(Comparator.comparingDouble(Conference::getScore));
		
		return (ArrayList<Conference>) initialPopulation.subList(0,N);
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
	
	
	
}
