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
	
		for (int j = 0; j < Utilities.getSessionCount()  ; j += Utilities.getSessionCount()) {
			this.sessions.add(new Session(sessions.substring(j, j + Utilities.getSessionCount())));
		}
		
		
		
	}
	
	
	
}

//Session: 
// TTT DDD PPPPPPP     TTT DDD PPPPPPP
