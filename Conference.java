import java.util.ArrayList;

public class Conference {
	
	String cromossome;
	ArrayList<Day> days = new ArrayList<Day>();
	int numberRooms;
	int numberSessions;
	int nDays;
	Conference(String cromossome){
		this.cromossome = cromossome;
		
		splitCromossome();
	}

	private void splitCromossome() {
		String day = cromossome.substring(0, 1);
		String sessions = cromossome.substring(2, cromossome.length()); //ver valor de 30 -> tamanho das sess�es, salas
		
		this.nDays = cromossome.length() / (Utilities.getSessionCount() * Utilities.SESSIONS_PER_PERIOD);
		
//		System.out.println("ndays "+ nDays);
		
		for (int i = 0; i <  Utilities.DAYS; i++){
			days.add(new Day(day, sessions));
		}

		//verificar valor sde substrings
	}
	
	@Override
	public String toString() {
		
		return "Total de Dias: " + Utilities.DAYS;
		
	}
}

