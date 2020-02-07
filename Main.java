import java.util.ArrayList;


public class Test1 {

    public static void main(String[] args) {
    	
        CSVLoader loader = new CSVLoader();

        int rowsLoaded = loader.load("Institutions.csv",
                                     ';');
        int fieldLoaded = loader.fieldCount();


        System.out.println(rowsLoaded + " rows loaded");
        System.out.println(fieldLoaded + " field max");

/*        TextAnalyzer analyzer = new TextAnalyzer("Ghostory.txt", "stop.txt");

        TextAnalyzer.WordCounter[] test = analyzer.topWords(25);

        for(int i = 0; i<test.length; i++)
        {

        	System.out.println(test[i].getWord()+" : "+test[i].getCount());
        }

        System.out.println(analyzer.HTMLCloud(test));*/
    }

}