
import java.util.ArrayList;

/**
 * This class represents a Session, which is a gene from a Conference
 */
public class Session {

	private int themeID;
	private int durationID;
	private ArrayList<Paper> papers = new ArrayList<Paper>();
	private String genome;
	private int sessionSchedule;

	/**
	 * Creates a Session, which is composed by a schedule/period, a theme, a
	 * duration and papers
	 * 
	 * @param session
	 *            String containing all the information related to the session
	 */
	public Session(String session) {
		this.sessionSchedule = Integer.parseInt(Utilities.binToDec(session.substring(0, 2)));// 0..1
		int index = 2;

		this.themeID = Integer.parseInt(Utilities.binToDec(session.substring(index, index + Utilities.THEME)));// 0..1
		index += Utilities.THEME;

		this.durationID = Integer.parseInt(Utilities.binToDec(session.substring(index, index += Utilities.DURATION)));// 2...4

		for (int i = 0; i < Utilities.PAPERS_PER_SESSION; i++) {
			String paper = session.substring(index, index += Utilities.getPaperSize());
			this.papers.add(new Paper(paper));
		}

		createGenome();
	}

	/**
	 * Creates a genome of the session
	 */
	private void createGenome() {

		String papersGene = "";
		for (Paper paper : papers)
			papersGene += paper.getGene();

		this.genome = Utilities.transform2Bin(this.themeID, Utilities.SESSION_THEME)
				+ Utilities.transform2Bin(this.durationID, Utilities.DURATION) + papers;
	}

	/**
	 * Returns the genome of the session
	 * 
	 * @return genome
	 */
	public String getGenome() {
		return genome;
	}

	/**
	 * Returns the schedule of the session
	 * 
	 * @return sessionSchecule
	 */
	public int getSchedule() {
		return sessionSchedule;
	}

	/**
	 * Returns the papers of the session
	 * 
	 * @return papers
	 */
	public ArrayList<Paper> getPapers() {
		return papers;
	}

	/**
	 * Returns the number of fullPapers of the session
	 * 
	 * @return number of fullPapers
	 */
	public int getNumberOfFullPapers() {
		int i = 0;
		for (Paper p : papers)
			if (p.isFullPaper())
				i++;
		return i;
	}

	/**
	 * Returns a score relating the Session theme with the papers themes.
	 * 
	 * @return value
	 */
	public double checkThemesID() {
		double score = 0;
		for (Paper p : papers) {
			for (int t : p.getThemes()) {
				if (t == this.themeID) {
					score += 1.0 / getNumberOfValidPapers();
					break;
				}
			}
		}
		return score;
	}

	/**
	 * Returns the number of valid papers
	 * 
	 * @return number of valid papers
	 */
	public int getNumberOfValidPapers() {
		int validPapers = 0;
		for (Paper p : papers) {
			if (p.isValid()) {
				validPapers++;
			}
		}
		return validPapers;
	}

	/**
	 * Returns the duration value of the session
	 * 
	 * @return durationID
	 */
	public int getDuration() {
		return this.durationID;
	}

	/**
	 * Converts a Session to a human-friendly string
	 */
	@Override
	public String toString() {
		String info = "";
		info += "Session Theme: " + Database.getThemessByID(themeID) + "\n";
		info += "Duration: " + durationID + "\n";
		info += "Period: " + Database.getScheduleByID(sessionSchedule) + "\n";
		for (int i = 0; i < papers.size(); i++) {
			info += "Paper: " + i + "\n" + papers.get(i).toString() + "\n";
		}

		return info;
	}
}
