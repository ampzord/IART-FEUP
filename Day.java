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
	
	
	
}

//Session: 
// TTT DDD PPPPPPP     TTT DDD PPPPPPP
