import java.util.ArrayList;

public class Day {

	String day;
	ArrayList<Session> sessions = new ArrayList<Session>();

	public Day(String day, String sessions) {
		System.out.println(sessions);
		this.day = day;
		
//		for (int j = 0; j < Utilities) {
//			
//		}
	
		int index = 0;
		
		for (int j = 0; j < Utilities.SESSIONS_PER_PERIOD ; j ++) {
			this.sessions.add(new Session(sessions.substring(0, index += Utilities.getSessionCount())));
		}
		
		System.out.println("Sessions Number: " + this.sessions.size());
		
	}
	
	
	
}

//Session: 
// TTT DDD PPPPPPP     TTT DDD PPPPPPP
