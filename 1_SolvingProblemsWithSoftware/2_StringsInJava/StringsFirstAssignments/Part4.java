import edu.duke.*;
/**
 *
 * @author (PJ)
 * @version (1.0 03.06.2024)
 */
public class Part4
{
    public void getYoutubeURL()
    {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
            for (String word : url.words()) {
                String term = "youtube.com";
                int ytIndex = word.toLowerCase().indexOf(term);
                
                if (ytIndex > -1) {
                   //int firstQuote = word.indexOf("\"");
                   int firstQuote2 = word.lastIndexOf("\"", ytIndex);
                   int lastQuote = word.indexOf("\"", firstQuote2 + 1);
                   String getLink = word.substring(firstQuote2 + 1, lastQuote);
                   System.out.println(getLink);
                   //System.out.println(word);
                }
        }
    }
}
