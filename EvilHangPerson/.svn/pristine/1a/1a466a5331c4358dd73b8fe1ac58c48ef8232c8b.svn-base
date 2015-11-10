import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Hashtable;
/**
 * 
 * @author Shal Xu
 *
 * This is controller of the Evil Hang Man to process words in the dictionary.
 */
public class EvilHangPersonController 
{
	/**
	 * This data structure sorts through all words in the dictionary based on number of letters in the word and all letters it does not contain.
	 * First based on the length of word, it is sorted the corresponding hastable in the array of hashtable.
	 * Then the hashtable contains arraylist of words corresponding to the alphabet. The word is added to all arraylists whose letter is does not contain.
	 * This technique saves time from looking through the whole file to find words that does not contain the current character.
	 */
	Hashtable<Character,ArrayList<String>> [] words;
	
	/**
	 * Constructor
	 * Initializes. Reads through the file and sorts all words into array of hashtable of arraylists of strings.
	 */
	protected EvilHangPersonController()
	{
		Scanner dictionary=null;
		try
		{
			dictionary=new Scanner(new File("CompleteDictionary.txt"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		words=new Hashtable[30];
		for(int i=0;i<30;i++)
		{
			words[i]=new Hashtable(30,1);
			for(char c='a';c<='z';c++)
			{
				words[i].put(new Character(c),new ArrayList<String>());
			}
		}
		while(dictionary.hasNextLine())
		{
			String temp=dictionary.nextLine();
			for(char c='a';c<='z';c++)
			{
				if(temp.toLowerCase().indexOf(c)==-1)
				words[temp.length()].get(c).add(temp);
			}
		}
	}
	/**
	 * Returns all possible words left after a character is entered by the user.
	 * @param previousWords all possible words left from the last move. null if no words have been previously filtered
	 * @param currentChar the character chosen this time by the user
	 * @param number number of letters in the word. Unchangable once it is entered at the beginning of game
	 * @return all possible words left. null if none of the words will be left if eliminating all that contains the current character.
	 */
	protected ArrayList<String> searchWords(ArrayList<String> previousWords,char currentChar, int number)
	{
		if(previousWords==null)
		{
			return words[number].get(currentChar);
		}
		else
		{
			ArrayList<String> newWords=new ArrayList<String>();
			for(String temp:previousWords)
			{
				if(words[number].get(currentChar).contains(temp))
				{
					newWords.add(temp);
					System.out.println(temp);
				}
			}
			if(newWords.isEmpty())
				return null;
			else
				return newWords;
		}
	}
}