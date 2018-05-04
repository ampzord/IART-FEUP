import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

public class Session {
	//dura��o, tema, papers(2 full papers no minimo), apresentador, dia da apresenta��o

	private int themeID;
	private int durationID;
	private ArrayList<Paper> papers = new ArrayList<Paper>();
	private String genome;
	private int sessionSchedule;
	
	/**
	 * Creates a session
	 * @param session String containing all the information related to the session
	 */
	public Session(String session) {
		this.sessionSchedule = Integer.parseInt(Utilities.binToDec(session.substring(0, 2)));//0..1
		int index = 2;
		
		this.themeID = Integer.parseInt(Utilities.binToDec(session.substring(index, index+Utilities.THEME)));//0..1
		index += Utilities.THEME;

		this.durationID = Integer.parseInt(Utilities.binToDec(session.substring(index, index += Utilities.DURATION)));//2...4
		
//		String cromo = "11 001 000110111";
		for (int i = 0; i < Utilities.PAPERS_PER_SESSION; i++) {
			System.out.println("Paper " + i);
			String paper = session.substring(index, index += Utilities.getPaperSize());
			this.papers.add(new Paper(paper));
		}

		System.out.println("Theme ID: "+ themeID);
		System.out.println("Duration ID: "+ durationID);
		System.out.println("Period: "+ sessionSchedule);
		
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
