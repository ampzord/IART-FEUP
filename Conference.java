import java.util.ArrayList;

public class Conference  {
	
	String cromossome;
	ArrayList<Day> days = new ArrayList<Day>();
	int numberRooms;
	int numberSessions;
	int nDays;
	private double fitnessScore;
	private double probability;
	Conference(String cromossome){
		
		this.cromossome = cromossome.replaceAll(" ", "");
		
		splitCromossome();
	}

	private void splitCromossome() {
		String day = cromossome.substring(0, 1);
		String sessions = cromossome.substring(2, cromossome.length()); //ver valor de 30 -> tamanho das sess�es, salas
		
		this.nDays = cromossome.length() / (Utilities.getSessionCount() * Utilities.SESSIONS_PER_PERIOD);
		
//		System.out.println("ndays "+ nDays);
		
		for (int i = 0; i <  Utilities.DAYS; i++){
			days.add(new Day(day, sessions));
		}

		//verificar valor sde substrings
	}
	
	
//	public boolean checkAuthors() {
//		
//	}
	
	public void calculateScore() {
		
		ArrayList<Integer> daysRepresentation = new ArrayList<Integer>();

		double score = 0;
		double reprs = 0;
		for (Day d: this.days) {

			ArrayList<Integer> papersRepresentations = new ArrayList<Integer>();
			if (!d.checkPresenters()) {
				System.out.println("merda1");
				this.fitnessScore = 0;
				return;
			}
			for (Session s: d.sessions) {
				if (s.getNumberOfFullPapers() < 2) {
					System.out.println("merda2");
					this.fitnessScore = 0;
					return;
				}
				
				for (Paper p : s.getPapers()) {
					if (!p.checkIfPresenterIsAuthor()){
						System.out.println("merda3");
						this.fitnessScore = 0;
						return;
					}					
				}
				score += (s.checkThemesID() * Utilities.THEM_WEIGHT);
				
				papersRepresentations.add(s.getDuration());
				
			}
			int repr = Utilities.calculateRepresentation(papersRepresentations);
		//	System.out.println("difference in sessions of a day: " + repr);
			reprs += 1.0 - repr/7.0;
		//	System.out.println("reprs: " + reprs);
			daysRepresentation.add(repr);
		}
		reprs*= Utilities.DAY_WEIGHT;
		int rep = Utilities.calculateRepresentation(daysRepresentation);
		//System.out.println("day rep: " + rep);
		score += (1.0 - rep/7.0)*Utilities.CONF_WEIGHT;
		
		//System.out.println("final rep: " + reprs);

		this.fitnessScore =  score + reprs;
	}
	
	public double getScore() {
		return this.fitnessScore;
	}
	
	public double getProbability() {
		return probability;
	}
	
	public void setProbability(double probability) {
		this.probability = probability;
	}
	
	@Override
	public String toString() {
		
		return "Total de Dias: " + Utilities.DAYS;
		
	}

}

