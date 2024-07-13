import edu.duke.*;
import java.util.*;

public class CharactersInPlay{
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int index = characters.indexOf(person);
        if (index == -1) {
            characters.add(person);
            counts.add(1);
        } else {
            int value = counts.get(index);
            counts.set(index, value + 1);
        }
    }
    
    public void findAllCharacters() {
        characters.clear();
        counts.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int period = line.indexOf(".");
            if (period != -1) {
                String character = line.substring(0, period);                
                
                if(!character.equals("")) {
                    update(character);
                }
            }
        }
        // System.out.println(character);
    }
      
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characters.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(characters.get(i));
            }
        }
    }
    
    public void tester() {
        findAllCharacters();        
        for (int i = 0; i < characters.size(); i++) {
            if (counts.get(i) > 5) {
                System.out.println(characters.get(i) + " " + counts.get(i));
            }
        }
        int num1 = 10;
        int num2 = 15;
        System.out.println("Characters with speaking parts between " + num1 + " and " + num2 + " is:");
        charactersWithNumParts(num1, num2);
        
        // for (int i = 0; i < characters.size(); i++) {
            // System.out.println(characters.get(i) + " " + counts.get(i));
        // }
    }
}
