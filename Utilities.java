import java.math.BigInteger;

public class Utilities {
	
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
}
