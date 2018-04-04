import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class Session {
	//duração, tema, papers(2 full papers no minimo), apresentador, dia da apresentação

	private int themeID;
	private int durationID;
	private ArrayList<Paper> papers;
	private int presenterID;
	private int day;
	private String genome;

	/**
	 * Creates a Session
	 * @param theme Theme id
	 * @param duration Duration id
	 * @param papers List of papers
	 * @param presenter Presenter id
	 * @param day Day id
	 */
	Session(int theme, int duration, ArrayList<Paper> papers, int presenter, int day){
		this.themeID = theme;
		this.durationID = duration;
		this.papers = papers;
		this.presenterID = presenter;
		this.day = day;

		createGenome();
	}

	/**
	 * Creates a genome with all the elements
	 */
	private void createGenome() {
		
		String papersGene = "";
		int fullPapers = 0;
		for (Paper paper : papers) 
			papersGene += paper.getGene();

		this.genome = Utilities.transform2Bin(this.themeID, Utilities.SESSION_THEME)
					+ Utilities.transform2Bin(this.durationID, Utilities.DURATION)
					+ Utilities.transform2Bin(this.presenterID, Utilities.PRESENTER)
					+ Utilities.transform2Bin(this.day, Utilities.DAYS)
					+ papers;
	}


}
