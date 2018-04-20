import java.util.ArrayList;

public class Day {

	String day;
	ArrayList<Session> sessions;

	public Day(String day, String sessions) {
		this.day = day;
		for (int j = 0; j < sessions.length()-1; j+=Utilities.getSessionCount()) 
			this.sessions.add(new Session(sessions.substring(j, j+Utilities.getSessionCount())));
	}
}

//Session: 
// TTT DDD PPPPPPP     TTT DDD PPPPPPP
