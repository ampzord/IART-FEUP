import java.util.ArrayList;

public class Genetic {

	public static double getScore(Conference conf) {
		
		ArrayList<Integer> daysRepresentation = new ArrayList<Integer>();

		double score = 0;
		double reprs = 0;
		for (Day d: conf.days) {

			ArrayList<Integer> papersRepresentations = new ArrayList<Integer>();
			if (!d.checkPresenters()) {
				System.out.println("merda1");
				return 0;
			}
			for (Session s: d.sessions) {
				if (s.getNumberOfFullPapers() < 2) {
					System.out.println("merda2");
					return 0;
				}
				
				for (Paper p : s.getPapers()) {
					if (!p.checkIfPresenterIsAuthor()){
						System.out.println("merda3");
						return 0;
					}					
				}
				score += (s.checkThemesID() * Utilities.THEM_WEIGHT);
				
				papersRepresentations.add(s.getDuration());
				
			}
			int repr = calculateRepresentation(papersRepresentations);
		//	System.out.println("difference in sessions of a day: " + repr);
			reprs += 1.0 - repr/7.0;
		//	System.out.println("reprs: " + reprs);
			daysRepresentation.add(repr);
		}
		reprs*= Utilities.DAY_WEIGHT;
		int rep = calculateRepresentation(daysRepresentation);
		//System.out.println("day rep: " + rep);
		score += (1.0 - rep/7.0)*Utilities.CONF_WEIGHT;
		
		//System.out.println("final rep: " + reprs);

		return score + reprs;
	}

	private static int calculateRepresentation(ArrayList<Integer> representations) {
				
		int min = representations.get(0);
	    int max = min;
	    int size = representations.size();
        for (int i = 0; i < size; i++){
        	
        	int value = representations.get(i);
            if (min > value)
                min = value;
            if (max < value)
                max = value;
        }
        return max-min;
	}
	
	
}
