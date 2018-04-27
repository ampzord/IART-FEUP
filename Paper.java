import java.util.ArrayList;
import java.util.Iterator;

public class Paper {
	
	private ArrayList<Integer> themes = new ArrayList<Integer>();
	private ArrayList<Integer> authors = new ArrayList<Integer>();
	private int duration;
	private boolean isFullPaper;
	private String gene;
	
	/**
	 * Creates a paper
	 * @param paper String containing all the information related to the paper
	 */
	public Paper(String paper) {
		int index = 0;
		
		for (int i = 0; i < Utilities.AUTHORS_PER_PAPER; i++) {
						
			String subs = paper.substring(index, index += Utilities.AUTHORS);
			String bintoDec = Utilities.binToDec(subs);
		
			int b = Integer.parseInt(bintoDec);
			this.authors.add(b);
		}
		
		for (int i = 0; i < Utilities.THEMES_PER_PAPER; i++) {
			this.themes.add(Integer.parseInt(Utilities.binToDec(paper.substring(index, index += Utilities.THEME))));
		}

		this.isFullPaper = paper.substring(index, index++).equals("0") ? false : true;
		
		if (this.isFullPaper)
			this.duration=30;
		else
			this.duration=20;

		System.out.println("Authors: ");
		for (Integer integer : authors) {
			System.out.print(integer+", ");
			
		}
		System.out.println("Themes:: ");
		for (Integer integer : themes) {
			System.out.print(integer+", ");
			
		}
		
		System.out.println("Duration: " + duration);
		System.out.println("Full Papper: " + isFullPaper);
		
		
		createGene();
	}
	
	// AAAA AAAA AAAA TT TT TT DD
	
	
	/**
	 * Creates a gene of a paper
	 */
	public void createGene() {
		String themesG ="";
		for (int theme : themes) 
			themesG += Utilities.transform2Bin(theme, Utilities.THEME);
		
		String authorsG = "";
		for (int author : authors) 
			authorsG += Utilities.transform2Bin(author, Utilities.AUTHORS);
		
		this.gene = authorsG + themesG + this.isFullPaper;
	}

	/**
	 * Returns the gene related to the paper
	 * @return The gene
	 */
	public String getGene() {
		return this.gene;
	}

	public ArrayList<Integer> getThemes() {
		return themes;
	}
	
	
	public ArrayList<Integer> getAuthors() {
		return authors;
	}

	
	public boolean isFullPaper() {
		return isFullPaper;
	}


	public int getDuration() {
		return duration;
	}
	

}
