package src;
import java.util.ArrayList;

/**
 * This class represents a Day,
 * which is a gene from a Conference
 */
public class Day {

	private String day;
	private ArrayList<Session> sessions = new ArrayList<Session>();

	/**
	 * Creates a Day, which is composed by an identifier and 
	 * the sessions of that day
	 * @param day The day identifier
	 * @param sessions The sessions of the day
	 */
	public Day(String day, String sessions) {
		this.day = day;
		int index = 0;
		
		for (int j = 0; j < Utilities.SESSIONS_PER_PERIOD ; j ++) 
			this.sessions.add(new Session(sessions.substring(index, index += Utilities.getSessionCount())));
	}
	
	
	/**
	 * Checks if presenters on the papers are different on each session in the same period
	 * @return true if there is a duplicated presenter, otherwise returns false
	 */
	public boolean checkPresenters() {
		ArrayList<Session> aux = new ArrayList<Session>();
		
		for (int i = 0; i < Utilities.NUM_PERIODS; i++) {
			aux = getSessionsOfPeriod(i);		
			ArrayList<Integer> presenters = new ArrayList<Integer>();
			for (Session s : aux) 
				for (Paper p : s.getPapers()) {
					int presenter = p.getPresenter();
					if (presenters.contains(presenter))
						return false;
					presenters.add(presenter);
				}		
		}		

		return true;
	}
	
	/**
	 * Returns the sessions of the indicated period
	 * @param period Period to check 
	 * @return ArrayList of the sessions of the indicated period
	 */
	public ArrayList<Session> getSessionsOfPeriod(int period){
		ArrayList<Session> aux = new ArrayList<Session>();
		
		for (Session s : sessions)
			if (s.getSchedule() == period)
				aux.add(s);
		return aux;
		
	}
	

	/**
	 * Returns the sessions of the Day
	 * @return sessions
	 */
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	
	/**
	 * Converts a Day to a human-friendly string
	 */
	@Override
	public String toString() {
		String info = "";
		for (int i = 0; i < sessions.size() ; i++) 
			info += "Session: " + "\n" + sessions.get(i).toString() + "\n";
	
		return info;
	}
	
}
