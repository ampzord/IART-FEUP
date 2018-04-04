import java.util.ArrayList;

public class Paper {
	
	private ArrayList<Integer> themes;
	private ArrayList<Integer> authors;
	private int duration;
	private String durationG;
	private boolean isFullPaper;
	private String gene;
	
	/**
	 * Creates a Paper
	 * @param themes List of themes ids
	 * @param authors List of authors ids
	 * @param isFullPaper
	 */
	public Paper(ArrayList<Integer> themes, ArrayList<Integer> authors, boolean isFullPaper) {
		this.themes = themes;
		this.authors = authors;
		this.isFullPaper = isFullPaper;
		
		if (this.isFullPaper) {
			this.duration = 30; //30
			this.durationG = "1"; //30
		}
		else {
			this.duration = 20; //30
			this.durationG = "0"; //30
		}		
		createGene();
	}
	
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
		
		this.gene = themesG + authorsG + this.durationG;
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
