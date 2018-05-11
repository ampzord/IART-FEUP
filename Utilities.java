import java.math.BigInteger;
import java.util.ArrayList;

public class Utilities {
	
//	public static int SESSION_THEME = 5; //32 themes
//	public static int THEME = 5; //32 themes
//	public static int DURATION = 3; // min: 1h(60min)
//	public static int PRESENTER = 6; //idAuthor
//	public static int DAYS = 2; // 3 days 
//	public static int AUTHORS = 6; //idAuthor
//	public static int AUTHORS_PER_PAPER = 2; 
//	public static int PAPERS= 6; //64 themes
//	public static int THEMES_PER_PAPER = 2; 
//	public static int PAPERS_PER_SESSION= 2; //64 themes
	public static int DAY_WEIGHT = 1;
	public static int CONF_WEIGHT = 1;
	public static int SESS_WEIGHT = 1;
	public static int PAPR_WEIGHT = 1;
	public static int THEM_WEIGHT = 1;
	
	
	public static int SESSION_THEME = 2; //32 themes
	public static int THEME = 2; //32 themes
	public static int DURATION = 3; // min: 1h(60min)
	public static int PRESENTER = 2; //idAuthor 
	public static int AUTHORS = 2; //idAuthor
	public static int AUTHORS_PER_PAPER = 2; 
	public static int PAPERS = 2; //64 themes
	public static int THEMES_PER_PAPER = 2; 
	public static int NUM_PERIODS = 4;
	
	public static enum SELECTION {
		ELITIST, PROBABILISTIC
	};
	
	public static int POPULATION_SIZE = 10;
	
//	// Exemplo 1
//	public static int DAYS = 1; // 3 days
//	public static int SESSIONS_PER_PERIOD = 1;
//	public static int PAPERS_PER_SESSION = 1; //64 themes
	
	// Exemplo 2
//		public static int DAYS = 1; // 3 days
//		public static int SESSIONS_PER_PERIOD = 2;
//		public static int PAPERS_PER_SESSION = 1; //64 themes
	
	
	// Exemplo 3
	public static int DAYS = 1; // 3 days
	public static int SESSIONS_PER_PERIOD = 2;
	public static int PAPERS_PER_SESSION = 2; //64 themes

	// Exemplo 4
//	public static int DAYS = 2; // 3 days
//	public static int SESSIONS_PER_PERIOD = 2;
//	public static int PAPERS_PER_SESSION = 2; //64 themes
	
	
	/**
	 * Fully transforms to binary
	 * @param number number to be transformed
	 * @param size size of number in binary
	 * @return binary number as a string
	 */
	public static String transform2Bin(int number, int size) {
		 return normalize(decToBin(number), size);		
	}

	/**
	 * Concatenates 0s to the left of the string to have the desired length
	 * @param word string to be concatenated
	 * @param length desired size
	 * @return string concatenated
	 */
	public static String normalize(String word, int length) {
		int size = length - word.length();

		if (size < 0) {
			System.out.println("Algo de errado n esta certo");
		}

		while (size > 0) {
			size--;
			word = "0".concat(word);
		}

		return word;
	}

	/**
	 * Converts a string on decimal format to binary
	 * @param s string
	 * @return string in binary
	 */
	public static String decToBin(String s) {
		 return new BigInteger(s, 10).toString(2);
	}
	
	/**
	 * Converts a number on decimal format to binary
	 * @param i
	 * @return string in binary
	 */
	public static String decToBin(int i) {
		 String s = String.valueOf(i);
		 return new BigInteger(s, 10).toString(2);
	}
	
	/**
	 * Converts a number on binary format to decimal
	 * @param i
	 * @return string in binary
	 */
	public static String binToDec(String s) {
		return new BigInteger(s, 2).toString(10);
	}
	
	/**
	 * Returns the size of a session in the cromossome
	 * @return Session size
	 */
	public static int getSessionCount() {
		//Periodo (manhã, tarde...)
		int PERIOD = 2;		
		return PERIOD + THEME + DURATION + getPaperSize() * PAPERS_PER_SESSION; 
	}
	
	/**
	 * Returns the size of a paper in the cromossome
	 * @return
	 */
	public static int getPaperSize() {
		int ISFULLPAPER = 1;
				//4									4							
		return (PRESENTER + AUTHORS * AUTHORS_PER_PAPER) + (THEME * THEMES_PER_PAPER) + ISFULLPAPER;
	}
	
	

	public static int calculateRepresentation(ArrayList<Integer> representations) {
				
		int min = representations.get(0);
	    int max = min;
	    int size = representations.size();
        for (int i = 0; i < size; i++){
        	
        	int value = representations.get(i);
            if (min > value)
                min = value;
            if (max < value)
                max = value;
        }
        return max-min;
	}
}
