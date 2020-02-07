import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Arrays; 
import java.util.Collections;

public class TextAnalyzer{

/**
         * Classe qui lit extraits tous les mots d'un fichier, et les trie par ordre d'apparition
         * @author : Robert Bui
         */


	Map<String, Integer> word;
	Map<String, Integer> finalWord;
	TreeSet<Integer> sortWord;

/**
 * Constructeur de la classe qui lit un fichier, les mets dans une HashMap
 * Puis les trie en fonction du nombre d'occurence
 * @param fichier : nom du fichier texte que l'on veut lire
 */
	/*
	private Map<String, Integer> HmFile(String fichier, String pouet)
	{
		Map<String, Integer> interWord = new HashMap<>();
		List<String> wesh = new ArrayList<>();


		try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {

			String line;

			while ((line = br.readLine()) != null) {

				String[] values = line.split(" ");

		    	for(int i=0; i < values.length ; i++)
		    	{
		    		if(pouet.equals("oui") && )
			    		if(!values[i].trim().equals("") 
			    			&& values[i].matches("[a-zA-Z]+")){
			    			if(interWord.containsKey(values[i].toLowerCase())){
			    				interWord.put(values[i].toLowerCase(), interWord.get(values[i].toLowerCase())+1);
			    			}
			    			else
			    			{
			    				interWord.put(values[i].toLowerCase(), 1);	
			    			}
			    		}
			    		else if(values[i].contains("'") || values[i].contains("-")){
			    			if(interWord.containsKey(values[i].toLowerCase())){
			    				interWord.put(values[i].toLowerCase(), interWord.get(values[i].toLowerCase())+1);
			    			}
			    			else
			    			{
			    				interWord.put(values[i].toLowerCase(), 1);	
			    			}	
			    		}
			    		else{

			    		}
		    	}

			}
		}
        catch (IOException excep)
        {
            throw new RuntimeException("Erreur de chargement du fichier !");
        }

        return interWord;
	}*/


	public String HTMLCloud(WordCounter[] word){

		List<WordCounter> test = new ArrayList<>(Arrays.asList(word));
		Collections.shuffle(test);

		String dra = "<html><body><div>";
		int color = 0;

		for(int i = 0; i<test.size(); i++)
		{
			if(color == 0)
				dra += "<span style=\"color:blue;font-size:";
			if(color == 1)
				dra += "<span style=\"color:red;font-size:";
			if(color == 2)
				dra += "<span style=\"color:orange;font-size:";
			if(color == 3)
				dra += "<span style=\"color:green;font-size:";

			dra += test.get(i).getCount();
			dra += "\">";
			dra += " ";
			dra += test.get(i).getWord();
			dra += "</span>";
			color++;
			if(color == 4)
				color = 0;
		}

		dra += "<div><body><html>";
		return dra;
	}

	public TextAnalyzer(String fichier, String stopWords)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(fichier));
			BufferedReader dr = new BufferedReader(new FileReader(stopWords));) {

			sortWord = new TreeSet<Integer>();
			List<String> stopList = new ArrayList<>();
			String line;

			word = new HashMap<>();

			while ((line = dr.readLine()) != null) {

				String[] values = line.split(" ");
		    	
		    	for(int i=0; i < values.length ; i++)
		    	{
		    		stopList.add(values[i]);
		    	}

			}

			while ((line = br.readLine()) != null) {

				String[] values = line.split(" ");

		    	for(int i=0; i < values.length ; i++)
		    	{
		    		if(stopList.contains(values[i].toLowerCase())){
		    		}
		    		else{
			    		if(!values[i].trim().equals("") 
			    			&& values[i].matches("[a-zA-Z]+")){
			    			if(word.containsKey(values[i].toLowerCase())){
			    				word.put(values[i].toLowerCase(), word.get(values[i].toLowerCase())+1);
			    			}
			    			else
			    			{
			    				word.put(values[i].toLowerCase(), 1);	
			    			}
			    		}
			    		else if(values[i].contains("'") || values[i].contains("-")){
			    			if(word.containsKey(values[i].toLowerCase())){
			    				word.put(values[i].toLowerCase(), word.get(values[i].toLowerCase())+1);
			    			}
			    			else
			    			{
			    				word.put(values[i].toLowerCase(), 1);
			    			}	
			    		}
			    		else{

			    		}
		    		}
		    	}

			}

	      Set<Entry<String, Integer>> setHm = word.entrySet();
	      Iterator<Entry<String, Integer>> it = setHm.iterator();
	      while(it.hasNext()){
	         Entry<String, Integer> e = it.next();
	         sortWord.add(e.getValue());
	      }

		}
        catch (IOException excep)
        {
            throw new RuntimeException("Erreur de chargement du fichier !");
        }
	}

/**
 * Retourne un tableau contenant les mots par fréquence d'apparition
 * @param n : Paramètre correspondant au nombre de mots à afficher dans l'ordre décroissant
 * @return Un tableau de WordCounter
 */

	WordCounter[] topWords(int n){

		List<String> maux = new ArrayList<>();
		List<Integer> ocu = new ArrayList<>();

		int b=0;
		int tab=0;
		Iterator diterator;
	 	diterator = sortWord.descendingIterator();

		while(diterator.hasNext())
		{
			int i = (int)diterator.next();
			if(word.containsValue(i))
			{
				for (Map.Entry<String, Integer> entry : word.entrySet()) {
				    if(entry.getValue().equals(i))
				    {
				      	if(b<n){
				      		maux.add(entry.getKey());
				      		ocu.add(i);
				      		tab++;
				      	}
				    }
				}
			}
			b++;
		}

		WordCounter[] array = new WordCounter[tab];

		for(int i = 0; i< maux.size(); i++)
		{
			array[i] = new WordCounter(maux.get(i), ocu.get(i));
		}

		return array;
	}
/**
 * Classe faisant le lien entre un mot et son nombre d'apparition dans le texte
 */

	public class WordCounter{

		String mot;
		int count;

/**
 * Constructeur de la classe qui lie un mot à sa fréquence d'apparition
 * @param mot : un mot
 * @param count : nombre d'occurence
 */

		public WordCounter(String mot, int count)
		{
			this.mot = mot;
			this.count = count;
		}
/**
* @return retourne le mot
*/
		public String getWord()
		{
			return mot;
		}
/**
* @return retourne le nombre d'apparition du mot
*/
		public int getCount()
		{
			return count;
		}

	}
}