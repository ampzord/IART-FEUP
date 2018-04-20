import java.util.ArrayList;

public class Conference {
	
	String cromossome;
	ArrayList<Day> days;
	int numberRooms;
	int numberSessions;
	int nDays;
	Conference(String cromossome){
		this.cromossome = cromossome;
		
		splitCromossome();
	}

	private void splitCromossome() {
		String day = cromossome.substring(0, 1);
		String sessions = cromossome.substring(2, 32); //ver valor de 30 -> tamanho das sess�es, salas
		for (int i =0; i <  nDays; i++){
			days.add(new Day(day, sessions));
		}

		//verificar valor sde substrings
	}
}

