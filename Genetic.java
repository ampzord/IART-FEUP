
public class Genetic {

	public static double getScore(Conference conf) {
		double score = 0;
		for (Day d: conf.days) {
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
				System.out.println("session " + s.getSchedule());
				score += s.checkThemesID();
				
			}
		}
		
		return score;
	}
	
	
}
