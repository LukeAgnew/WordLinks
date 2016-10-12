import javax.swing.JOptionPane;
import java.util.Scanner;

public class WordLinks {
	public static void main(String[] args) {
		
		boolean listEmpty = false;
		
		while (!listEmpty)
		{
			try
			{
				String userInput = JOptionPane.showInputDialog("Please enter your minus-separated list of words.");
				
				String[] wordLinks = readWords(userInput);
				
				boolean wordChain = isWordChain(wordLinks);
				
				if (wordChain)
				{
					JOptionPane.showMessageDialog(null, "The list of words: " + userInput + " is a valid word chain.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "The list of words: " + userInput + " is not a valid word chain.");
				}
			}
			catch (NullPointerException exception)
			{
				listEmpty = true;
			}
			catch (java.util.NoSuchElementException exception)
			{
				listEmpty = true;
			}
		}		
	}
	public static String[] readWords(String userInput) {
		
		Scanner inputScanner = new Scanner(userInput);
		inputScanner.useDelimiter("-");
		
		int wordCount = 1;
		
		String[] wordLinks = new String[wordCount];	
		wordLinks[wordCount-1] = inputScanner.next();
		
		while (inputScanner.hasNext())
		{
			wordCount++;
			String[] tmp = new String[wordCount];
			
			for (int index=0; index<wordCount-1; index++)
			{
				tmp[index] = wordLinks[index];
			}
			
			tmp[wordCount-1] = inputScanner.next();
			
			wordLinks = new String[wordCount];
			wordLinks = tmp.clone();			
		}
		
		inputScanner.close();
		
		return wordLinks;
	}
	
	public static boolean isWordChain(String[] wordLinks) {
		boolean wordChain = true;
		
		for (int indexOne=0; indexOne<wordLinks.length; indexOne++)
		{	
			if (!isEnglishWord(wordLinks[indexOne]))
			{
				wordChain = false;
				return wordChain;
			}
		}
		
		if(!isUnique(wordLinks))
		{
			wordChain = false;
			return wordChain;
		}
		else
		{
			for(int indexTwo=0; indexTwo<wordLinks.length-1; indexTwo++)
			{
				if (!isDifferentByOne(wordLinks[indexTwo],wordLinks[indexTwo+1]))
				{
					wordChain = false;
					return wordChain;
				}
			}
		}
		
		return wordChain;		
	}
	
	public static boolean isEnglishWord(String word) {
		
		boolean wordChain = false;
		
		In words = new In("U:/WordLinks/src/words.txt");
		
		while (words.hasNextLine())
		{
			if (word.trim().equals(words.readLine()))
			{
				wordChain = true;
				return wordChain;
			}		
		}
		return wordChain;		
	}
	
	public static boolean isUnique(String[] wordLinks) {
		
		for (int indexOne=0; indexOne<wordLinks.length; indexOne++)
		{
			for (int indexTwo=0; indexTwo<wordLinks.length; indexTwo++)
			{
				if (wordLinks[indexTwo].trim().equals(wordLinks[indexOne].trim()) && indexTwo!=indexOne)
				{
					return false;
				}			
			}		
		}
		
		return true;
	}
	
	public static boolean isDifferentByOne(String firstWord, String secondWord) {
		
		int differentCount = 0;
		
		char[] firstWordChars = firstWord.trim().toCharArray();
		char[] secondWordChars = secondWord.trim().toCharArray();
		
		boolean matchingChars = false;
		
		for (int indexOne=0; indexOne<firstWordChars.length; indexOne++)
		{
			if (firstWordChars[indexOne] == (secondWordChars[indexOne]))
			{
				matchingChars = true;
			}
			
			if (!matchingChars)
			{
				differentCount++;
			}	
		}
			
				
	
		if (differentCount > 1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
