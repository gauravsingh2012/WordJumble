import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class TwiceWordJumble {

	private static Set <String> dictionary = new HashSet<String>();
	private static List<String> listOfValidWords = new ArrayList<String>();

	public static void main(String[] args) {
		
		TwiceWordJumble wordJumble = new TwiceWordJumble();
		/*
		 * Time Complexity of the program: O(m + 2^n + (2^n)*n! + k) 
		 * where m = number of words in the dictionary
		 * n = number of letters in the word
		 * k = number of permuted Words
		 * 
		 * Space Compelxity: - O(m + 2^n + (2^n)*n! + k)
		 * where m = number of words in the dictionary
		 * n = number of letters in the word
		 * k = number of permuted Words
		 * 
		 * 
		 * */
		wordJumble.generateAllValidWords("dog",wordJumble);
		System.out.println(wordJumble.getListOfValidWords());

		
	}
	
	public static void generateAllValidWords(String str, TwiceWordJumble wordJumble){
	
		List<String> listOfWords = new ArrayList<String>();
		
		if (str == null || wordJumble == null || str.equals("")){
			throw new IllegalArgumentException("Invalid Input");
		}
		
		/*
		 * Ignore if it is a one letter word. This is my assumption.
		 */
		
		if (str.length() == 1){	
			return;
		}
		
		wordJumble.setDictionary(getDictionaryFromFile());
		/*
		 * Generate all the unique combinations of a word 
		 */
		List<String> comb = combinations(str);
		/*
		 * Generate permutation of each word in the 
		 * listofCombinations
		 */
		
		listOfWords = getPermutationsOfCombinations(comb);
		/*
		 * Filter the listOfPermutedWords using a dictionary
		 */
		getValidWords(listOfWords, wordJumble);
	}
	

	/*
	 * Checks if the words generated exist in the dictionary:
	 * 
	 */
	
	public static void getValidWords(List<String> listOfWords, TwiceWordJumble wordJumble){
		
	
		for (String str : listOfWords){
			if (wordJumble.getDictionary().contains(str)){
				wordJumble.getListOfValidWords().add(str);
			}
		}
		
	}
	
	/* 
	 * Loads the dictionary words from a file into the hashset 
	 * The dictionary that I am using is a 12Dicts dictionary from SCOWL. 
	 * 
	 */
	
	private static Set<String> getDictionaryFromFile(){
		BufferedReader br = null;
		
		Set<String> dictionary = new HashSet<String>(); 
		
		try {
			 
			String currentLine;
 
			br = new BufferedReader(new FileReader("5desk.txt"));
 
			while ((currentLine = br.readLine()) != null) {
				dictionary.add(currentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return dictionary;

	}	
	
	private static List<String> getPermutationsOfCombinations(List<String> listOfCombinations){
		
		List<String> listOfWords = new ArrayList<String>();
		
		List<String> listOfPermutedWords = new ArrayList<String>();
		
		Set<String> setOfUniqueWords = new HashSet<String>();
		
		/*
		 * Ignore the words with length 1 
		 */
		
		for (String str : listOfCombinations){
			if (str.length() != 1){
				listOfPermutedWords = permutations(str);
				listOfWords.addAll(listOfPermutedWords);
			}
		}
		
		setOfUniqueWords = new HashSet<String>(listOfWords);
		listOfWords = new ArrayList<String>(setOfUniqueWords);
		return listOfWords;
	}
	
	/*
	 * 
	 * Generate all combinations of a word or a subset of a word.
	 * 	
	 * For eg:- Combinations of the word "dog" is
	 * 
	 * 	[d,o,do,g,dg,og,dog] 
	 * 
	 * 
	 */
	
	private static List<String> combinations(String str) {
		
		Set<String> uniqueCombination = new HashSet<String>();
		List<String> combination = new ArrayList<String>();
		combination.add("");
		for (int i = 0; i < str.length(); i++) {
			List<String> newSubString = new ArrayList<String>();
			for (String string : combination) {	
				String s = string + str.charAt(i);
				newSubString.add(s);
			}
			combination.addAll(newSubString);
		}
		combination.remove(0);
		
		uniqueCombination = new HashSet<String>(combination);
		combination = new ArrayList<String>(uniqueCombination);
		
		return combination;
	}

	
	/*
	 * 
	 * Generates the permutation of a string. The algorithm uses the previous results
	 * in the stack to generate permutations by appending each character at different positions.
	 * For eg:- TO generate Permutations of "dog"
	 * P(0) = "" 
	 * P(1) = "d" Appends d in the first position
	 * P(2) = "od","do" Appends o in the first position and the second position 
	 * P(3) = "god","gdo","ogd","dgo","odg","dog"
	 *  
	 */
	
	private static List<String> permutations(String str) {
		Stack<ArrayList<String>> stack = new Stack();
		ArrayList<String> permutation = new ArrayList<String>();
		permutation.add("");
		stack.push(permutation);
		for (int i = 0; i < str.length(); i++) {
			permutation = stack.peek();
			ArrayList<String> newPermutations = new ArrayList<String>();
			for (String string : permutation) {
				for (int j = 0; j <= string.length(); j++) {
						String s = insertCharAt(string, str.charAt(i), j);
						newPermutations.add(s);
				}
			}
			stack.push(newPermutations);
		}
		return stack.peek();
	}
	
	private static String insertCharAt(String string, char singleChar, int j) {
		String left = string.substring(0, j);
		String right = string.substring(j);
		return left + singleChar + right;
	}
		
	public Set<String> getDictionary() {
		return dictionary;
	}

	public void setDictionary(Set<String> dictionary) {
		this.dictionary = dictionary;
	}

	public List<String> getListOfValidWords() {
		return listOfValidWords;
	}

	public void setListOfValidWords(List<String> listOfValidWords) {
		this.listOfValidWords = listOfValidWords;
	}
	
}
