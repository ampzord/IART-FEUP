import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Session,
 * which is a gene from a Conference
 */
public class Paper {
	
	private ArrayList<Integer> themes = new ArrayList<Integer>();
	private ArrayList<Integer> authors = new ArrayList<Integer>();
	private int duration;
	private boolean isFullPaper;
	private String gene;
	private int presenter;
	
	/**
	 * Creates a Paper, which is composed by themes, authors,
	 * duration and if it's full paper or short paper
	 * @param paper String containing all the information related to the paper
	 */
	public Paper(String paper) {
		int index = 0;
		
		presenter = Integer.parseInt(Utilities.binToDec(paper.substring(index, index += Utilities.PRESENTER)));
		
	//	System.out.println("Presenter: " + presenter);
		
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

	//	System.out.print("Authors: ");
//		for (Integer integer : authors) {
//			System.out.print(integer+", ");
//			
//		}
	//	System.out.println();
	//	System.out.print("Themes: ");
	//	for (Integer integer : themes) {
	//		System.out.print(integer+", ");
			
	//	}
		
	//	System.out.println("\nDuration: " + duration);
	//	System.out.println("Full Papper: " + isFullPaper);
		
	//	System.out.println("Presenter is an author: " + checkIfPresenterIsAuthor());
		
		
		createGenome();
	}
	
//	Upgrade
	// AAAA AAAA AAAA TT TT TT D
	
	
	
	//	AAAA AAAA AAAA TT TT TT DD
	
	
	/**
	 * Creates the genome of a paper 
	 */
	public void createGenome() {
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

	/**
	 * Returns the themes of the paper
	 * @return themes
	 */
	public ArrayList<Integer> getThemes() {
		return themes;
	}
	
	/**
	 * Returns the authors of the paper
	 * @return authors
	 */
	public ArrayList<Integer> getAuthors() {
		return authors;
	}

	/**
	 * Checks if paper is Full Paper
	 * @return true if full paper, false if short paper
	 */
	public boolean isFullPaper() {
		return isFullPaper;
	}

	/**
	 * Returns the duration of the paper
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Checks if the paper if valid
	 * @return true if valid, false otherwise
	 */
	public boolean isValid() {
		return gene.contains("1");
	}
	
	/**
	 * Checks if the presenter of the paper is an author of the paper
	 * @return true if is author, false otherwise
	 */
	public boolean checkIfPresenterIsAuthor() {
		return authors.contains(presenter);
	}
	
	/**
	 * Returns the presenter of the paper
	 * @return presenter
	 */
	public int getPresenter() {
		return presenter;
	}
	

}
