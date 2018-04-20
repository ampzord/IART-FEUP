import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class Session {
	//duração, tema, papers(2 full papers no minimo), apresentador, dia da apresentação

	private int themeID;
	private int durationID;
	private ArrayList<Paper> papers;
	private String genome;


	/**
	 * Creates a session
	 * @param session String containing all the information related to the session
	 */
	public Session(String session) {
		this.themeID = Integer.parseInt(Utilities.binToDec(session.substring(0, Utilities.THEME)));
		int index = Utilities.THEME + Utilities.DURATION;
		this.durationID = Integer.parseInt(Utilities.binToDec(session.substring(Utilities.THEME, index)));
		
		
		for (int i = 0; i < Utilities.PAPERS_PER_SESSION; i++) 
			this.papers.add(new Paper(session.substring(index, index+=Utilities.PAPERS)));
		

		createGenome();
	}
	
	// TT DD PPPPPP PPPPPPP

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
					+ papers;
	}
	
	public String getGenome() {
		return this.genome;
	}


}
