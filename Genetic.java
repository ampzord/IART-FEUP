import java.util.ArrayList;
import java.util.Random;

import jdk.nashorn.internal.runtime.Context.ThrowErrorManager;

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
	
	public static String crossCromossomes(Conference c1, Conference c2) {
		
		if (c1.cromossome.length() != c2.cromossome.length()) {
			throw new java.lang.RuntimeException("Error! The cromossome's sizes are different.");
		}
		
		String newCromossome = "";
		int offset = 0;
		
//      DIA                (HORA   TEMA    DURA    (AP AA AA TT TT  full)  (papper2))           (HORA TEMA DUR		    (paper)   (paper2))
//		String cromo =  "00  11   11      001    00 00 01  10 11  1    01011110110           01      00 11 001 00000110111  01011110110"; 
//		String cromo2 = "01  01   01      000    01 10 00  00 10  1    01101110110           01      10 01 011 01010110101  11010111100";
	
		System.out.println("Total lenght: " + c1.cromossome.length());
		
		for (int days = 0 ; days < Utilities.DAYS ; days ++) {
			newCromossome += crossAux(c1.cromossome.substring(offset, offset+Utilities.DAYS), c2.cromossome.substring(offset, offset+Utilities.DAYS));
			offset += Utilities.DAYS;
			
			for (int sessions = 0 ; sessions < Utilities.SESSIONS_PER_PERIOD ; sessions++) {
				// offset + 2 porque o número de bits que representam o periodo é 2
				newCromossome += crossAux(c1.cromossome.substring(offset, offset+2), c2.cromossome.substring(offset, offset+2));
				offset += 2;
				System.out.println("current offset: " + offset);
				
				// Tema de sessao
				newCromossome += crossAux(c1.cromossome.substring(offset, offset+Utilities.SESSION_THEME), c2.cromossome.substring(offset, offset+Utilities.SESSION_THEME));
				offset += Utilities.SESSION_THEME;
				System.out.println("current offset: " + offset);
				
				// duração da sessao
				newCromossome += crossAux(c1.cromossome.substring(offset, offset+Utilities.DURATION), c2.cromossome.substring(offset, offset+Utilities.DURATION));
				offset += Utilities.DURATION;
				System.out.println("current offset: " + offset);
				
				for (int paper = 0 ; paper < Utilities.PAPERS_PER_SESSION ; paper++) {
					// Presenter
					newCromossome += crossAux(c1.cromossome.substring(offset, offset+Utilities.PRESENTER), c2.cromossome.substring(offset, offset+Utilities.PRESENTER));
					offset += Utilities.PRESENTER;
					System.out.println("current offset: " + offset);
					
					for (int authors = 0 ; authors < Utilities.AUTHORS_PER_PAPER ; authors++) {
						// Authors
						newCromossome += crossAux(c1.cromossome.substring(offset, offset+Utilities.AUTHORS), c2.cromossome.substring(offset, offset+Utilities.AUTHORS));
						offset += Utilities.AUTHORS;						
					}
					
					for (int themes = 0 ; themes < Utilities.THEMES_PER_PAPER; themes++) {
						// Themes
						newCromossome += crossAux(c1.cromossome.substring(offset, offset+Utilities.THEME), c2.cromossome.substring(offset, offset+Utilities.THEME));
						offset += Utilities.THEME;						
					}
					
					// Is full paper (defined always by 1 bit)
					newCromossome += crossAux(c1.cromossome.substring(offset, offset+1), c2.cromossome.substring(offset, offset+1));
					offset += 1;
					
				}
				
				
			}
//			offset += Utilities.DAY;
		}
		
		System.out.println("offset: "+ offset);
		
		Utilities.getCromossomeSize();
		
		if (c1.cromossome.length() != offset) {
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
	
	public static String generateRandomPoplation() {
		String newCromossome = "";
		
		Random rand = new Random();
		int n = rand.nextInt(101);
		
		for (int i = 0 ; i < Utilities.getCromossomeSize() ; i++) {
			if (n < 50) {
				newCromossome += "0";
			} else {
				newCromossome += "1";
			}
		}
		
		return newCromossome;
	}
}









