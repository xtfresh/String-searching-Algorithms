import java.util.Arrays;


public class StringSearches {

	/**
	 * Return a table for use with Boyer-Moore.
	 * 
	 * map[c] = the length - 1 - last index of c in the needle
	 * map[c] = the length if c doesn't appear in the needle
	 * 
	 * the map should have an entry for every character, 0 to Character.MAX_VALUE
	 */
	public static int[] buildCharTable(String needle) {
		int[] map = new int[Character.MAX_VALUE + 1];
		for (int i = 0; i<map.length;i++){
			map[i] = needle.length();
		}
		for(int i = 0; i < needle.length(); i++){
			map[needle.charAt(i)] = Math.max(needle.length() - needle.lastIndexOf(needle.charAt(i)) - 1, 1);
		}
		return map;
	}

	/**
	 * Run Boyer-Moore on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack. 
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 * 
	 * Running time matters, you will not get full credit if it is not
	 * implemented correctly
	 * 
	 * 
	 */
	public static int[] boyerMoore(String needle, String haystack) {
		int[] charTable = buildCharTable(needle);
		int[] indexes = new int[haystack.length()/needle.length()];
		int lettersMatched = 0;
		int matched = 0;
		int j = needle.length()-1;
		
		for (int i = needle.length()-1; i >= 0;){
			if(j > haystack.length()-1)
				break;
			if(needle.charAt(i)==haystack.charAt(j)){
				lettersMatched++;
				if(lettersMatched == needle.length()){
					indexes[matched] = j;
					matched++;
					i = needle.length()-1;
					j += needle.length()+1;
					lettersMatched = 0;
				}
				j--;
				i--;
				
			}
			else{
				j += needle.length()-Math.min(i, 1 + charTable[j]);
				i = needle.length()-1;
				lettersMatched = 0;
			}
				
				
		}
		
		return Arrays.copyOf(indexes,matched);
	}

	/**
	 * Return a table for use with KMP. In this table, table[i] is the length of
	 * the longest possible prefix that matches a proper suffix in the string
	 * needle.substring(0, i)
	 */
	public static int[] buildTable(String needle) {
		 int[] table = new int[needle.length()];
		 int pos = 2;
		 int cnd = 0;
		 
		 table[0] = -1; 
		 table[1] = 0; 
		 while(pos < needle.length()) {
			 if(needle.charAt(pos-1) == needle.charAt(cnd)) {
				 table[pos] = cnd;
				 cnd += 1;
				 pos += 1;
			 }else if(cnd > 0)
				 	cnd = table[cnd];
			 	 	else{
					 table[pos] = 0;
					 pos += 1;
			 	 	}
			 }
		 return table;
	}

	/**
	 * Run Knuth-Morris-Pratt on the given strings, looking for needle in
	 * haystack. Return an array of the indices of the occurrence of the needle
	 * in the haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] kmp(String needle, String haystack) {
		int[] table = buildTable(needle);
		int[] indexes = new int[haystack.length()/needle.length()];
		int lettersMatched = 0;
		int matched = 0;
		int j = 0;
		
		for (int i = 0; i < haystack.length() -1; i++){
			if(needle.charAt(j) == haystack.charAt(i)){
				lettersMatched++;
				j++;
				if(lettersMatched == needle.length()){
					indexes[matched] = i-needle.length()+1;
					matched++;
					j = 0;
				}
			}else if(j != 0){
					j = table[j];
					i--;
			}
				
		}
			
		return Arrays.copyOf(indexes,matched);
	}

	// This is the base you should use, don't change it
	public static final int BASE = 1332;

	/**
	 * Given the hash for a string, return the hash for that string removing
	 * oldChar from the front and adding newChar to the end.
	 * 
	 * Power is BASE raised to the power of the length of the needle
	 */
	public static int updateHash(int oldHash, int power, char newChar,
			char oldChar) {
		return 0;
	}

	/**
	 * Hash the given string, using the formula given in the homework
	 */
	public static int hash(String s) {
		return 0;
	}

	/**
	 * Run Rabin-Karp on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] rabinKarp(String needle, String haystack) {
		return null;
	}
}
