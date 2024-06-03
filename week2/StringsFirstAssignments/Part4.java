import edu.duke.URLResource;
/**
 *
 * @author (PJ)
 * @version (1.0 03.06.2024)
 */
public class Part4
{
    public void testing()
    {
        URLResource yt = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String ytString = yt.asString();
        //System.out.println(ytString);
        //System.out.println(ytString.lines());
        System.out.println(yt.words());

    }

    public String getYoutubeURL(String url)
    {
        return "";
    }
}
