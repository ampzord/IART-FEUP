import java.util.ArrayList;

public class Day {

	String day;
	ArrayList<Session> sessions = new ArrayList<Session>();

	public Day(String day, String sessions) {
	
		this.day = day;

		int index = 0;
		
		for (int j = 0; j < Utilities.SESSIONS_PER_PERIOD ; j ++) {
			System.out.println("Session " + j);
			this.sessions.add(new Session(sessions.substring(index, index += Utilities.getSessionCount())));
			System.out.println();
		}
		
	}
	
	public ArrayList<Session> getSessions() {
		return sessions;
	}
	
	/**
	 * Checks if presenters on each papers are different on each session in the same period
	 * @return trye if there is a duplicated presenter, otherwise returns false
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
	
	public ArrayList<Session> getSessionsOfPeriod(int period){
		ArrayList<Session> aux = new ArrayList<Session>();
		
		for (Session s : sessions)
			if (s.getSchedule() == period)
				aux.add(s);
		return aux;
		
	}
	
}

//Session: 
// TTT DDD PPPPPPP     TTT DDD PPPPPPP
