import java.util.ArrayList;

public class Conference {
	
	String cromossome;
	ArrayList<Day> days;
	int numberRooms;
	int numberSessions;

	Conference(String cromossome){
		this.cromossome = cromossome;
		
		splitCromossome();
	}

	private void splitCromossome() {

		String day1 = cromossome.substring(0, 1);
		String sessions1 = cromossome.substring(2, 32); //ver valor de 30 -> tamanho das sessões, salas
		String day2 = cromossome.substring(33,34);
		String sessions2 = cromossome.substring(35, 65); //ver valor de 30 -> tamanho das sessões, salas
		String day3 = cromossome.substring(66,67);
		String sessions3 = cromossome.substring(68, 38); //ver valor de 30 -> tamanho das sessões, salas
	}
}
