import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJumbleWords {
	
	private TwiceWordJumble wordJumble;
	
	@Before
	public void setup(){
		this.wordJumble = new TwiceWordJumble();
	}
	
	
	@Test
	public void test() {
		
		List<String> expectedWords =  new ArrayList<>(Arrays.asList("go","do","god","dog"));
		
		wordJumble.generateAllValidWords("dog",wordJumble);
		Assert.assertEquals(expectedWords, wordJumble.getListOfValidWords());

	}
	
	@Test
	public void testWithOneLetterWord() {
		
		wordJumble.setListOfValidWords(new ArrayList<String>());
		List<String> expectedWords = new ArrayList<String>();
		
		wordJumble.generateAllValidWords("a",wordJumble);
		Assert.assertEquals(expectedWords, wordJumble.getListOfValidWords());

	}
	
	@Test
	public void testWithTwoLetterWord() {
		
		wordJumble.setListOfValidWords(new ArrayList<String>());
		List<String> expectedWords = new ArrayList<String>();
		
		wordJumble.generateAllValidWords("dd",wordJumble);
		Assert.assertEquals(expectedWords, wordJumble.getListOfValidWords());

	}
	
	@Test
	public void testWithFourLetterWords() {
		
		wordJumble.setListOfValidWords(new ArrayList<String>());
		List<String> expectedWords = new ArrayList<String>(Arrays.asList("goo","do","good","dog","oo","go","god"));
		
		wordJumble.generateAllValidWords("good",wordJumble);
		Assert.assertEquals(expectedWords, wordJumble.getListOfValidWords());

	}


}
