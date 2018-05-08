
public class Genetic {

	public static double getScore(Conference conf) {
		
		for (Day d: conf.days) {
			for (Session s: d.sessions) {
				
				// Se número de full papers for menor que 2 retorna 0
				if (s.getNumberOfFullPapers() < 2) {
					return 0;
				}
				
				
				
				
				
			}
		}
		
		
		
		
		
		return 0;
	}
	
	
}
