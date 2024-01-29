
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		String tail = "";
		for (int i = 1; i < str.length(); i++) {
			tail += str.charAt(i);
		}

		return tail;

	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		int a = word1.length();
		int b = word2.length();

		if (b == 0) {
			return a;
		} else if (a == 0) {
			return b;
		} else if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			return (1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))),
					levenshtein(tail(word1), tail(word2))));
		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int mindis = 0;
		String res = "";
		for (int i = 0; i < dictionary.length; i++) {
			if (mindis > levenshtein(word, dictionary[i])) {
				mindis = levenshtein(word, dictionary[i]);
				res = dictionary[i];
			}
		}
		if (mindis > threshold) {
			res = word;
		}
		return res;
	}

}
