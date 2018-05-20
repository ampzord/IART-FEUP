import java.util.ArrayList;

/**
 * This class represents a Conference,
 * which is an individual on the Genetic Algorithm 
 *
 */
public class Conference  {
	
	private String cromossome;
	private ArrayList<Day> days = new ArrayList<Day>();
	private double fitnessScore;
	private double probability;
	
	/**
	 * Constructor for a Conference. Receives a cromossome as argument to be parsed
	 * @param cromossome String that defines the genome
	 */
	Conference(String cromossome){		
		this.setCromossome(cromossome.replaceAll(" ", ""));
		splitCromossome();
		calculateScore();
	}
	
	/**
	 * Splits the cromossome in days and sessions. 
	 * A day has one or more sessions
	 */
	private void splitCromossome() {
		int offset = 0;
		
		days = new ArrayList<Day>(); 
		
		for (int i = 0; i <  Utilities.DAYS; i++) {
			
			String day = getCromossome().substring(offset, offset += Utilities.DAYSBITS);
			String sessions = getCromossome().substring(offset, offset += (Utilities.getSessionCount() * Utilities.SESSIONS_PER_PERIOD));//getCromossome().length()); //ver valor de 30 -> tamanho das sess�es, salas
			
			days.add(new Day(day, sessions));
			
		}

	}
	
	/**
	 * Calculates the fitness score associated to the Conference
	 */
	public void calculateScore() {
		
		ArrayList<Integer> daysRepresentation = new ArrayList<Integer>();

		double score = 0;
		double reprs = 0;
		for (Day d: this.days) {

			ArrayList<Integer> papersRepresentations = new ArrayList<Integer>();
			if (!d.checkPresenters()) {
				this.fitnessScore = 0;
				return;
			}
			for (Session s: d.getSessions()) {
				if (s.getNumberOfFullPapers() < 2) {
					this.fitnessScore = 0;
					return;
				}
				
				for (Paper p : s.getPapers()) {
					if (!p.checkIfPresenterIsAuthor()){
						this.fitnessScore = 0;
						return;
					}					
				}
				score += (s.checkThemesID() * Utilities.THEM_WEIGHT);
				
				papersRepresentations.add(s.getDuration());
				
			}
			int repr = Utilities.calculateRepresentation(papersRepresentations);
	
			reprs += 1.0 - repr/7.0;
			
			daysRepresentation.add(repr);
		}
		reprs*= Utilities.DAY_WEIGHT;
		int rep = Utilities.calculateRepresentation(daysRepresentation);
		
		score += (1.0 - rep/7.0)*Utilities.CONF_WEIGHT;
		
		this.fitnessScore =  score + reprs;
	}
	
	/**
	 * Returns the fitness score
	 * @return fitnessScore
	 */
	public double getScore() {
		return this.fitnessScore;
	}
	
	/**
	 * Returns the probability to be chosen in the selection phase
	 * @return probability
	 */
	public double getProbability() {
		return probability;
	}
	
	/**
	 * Sets the probability to be chosen in the selection phase
	 * @param probability Probability of the conference to set
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}
	
	/**
	 * Returns the cromossome
	 * @return cromossome
	 */
	public String getCromossome() {
		return cromossome;
	}

	/**
	 * Sets the cromossome
	 * @param cromossome the cromossome to set
	 */
	public void setCromossome(String cromossome) {
		this.cromossome = cromossome;
	}

	/**
	 * Prints all the information regarding the Conference
	 */
	@Override
	public String toString() {
		String info = "";
		info += "Total de Dias: " + days.size() + "\n";
		info += "--------------\n";
		for (int i = 0 ; i < days.size() ; i++) {
			info += "Dia: " + i + "\n" + days.toString() + "\n";
			info += "-------------\n";
		}
		
		return info;		
	}
	
	/**
	 * Changes the cromossome with the new mutation bit at the specified index 
	 * and recalculates score
	 * @param index Index of bit that will be modified
	 */
	public void setMutateBit(int index) {
				
		String newCromossome = "";
		for (int i = 0; i < cromossome.length() ; i++) {
			if (i == index) 
				newCromossome += (cromossome.charAt(index) == '0' ) ? '1' : '0';		
			else
				newCromossome += cromossome.charAt(i);
		}
		cromossome = newCromossome;
		splitCromossome();
		calculateScore();
	}

	/**
	 * Sets the fitness score of the Conference
	 * @param score new score to be set
	 */
	public void setScore(int score) {
		this.fitnessScore = score;
		
	}
}

