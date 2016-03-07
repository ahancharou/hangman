import com.mentoring.game.Hangman;

import java.util.Random;

public class Runner {

    public static void main (String [] args) {
        Hangman hangman = new Hangman("abcdefghijklmnopqrstuwxyz");
        System.out.println("Word: "+hangman.getWord());
        char letter;
        while (!hangman.gameOver()&&(hangman.lettersLeft()!=0)){
            System.out.println(hangman.getMask());
            letter = generateLetter();
            System.out.println("Check letter "+letter);
            if (hangman.checkLetter(generateLetter())){
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect, tries left: "+ hangman.getTries());
            }
        }
        if (hangman.gameOver()){
            System.out.print("Lose");
        } else {
            System.out.print("Win");
        }
    }

    private static char generateLetter()
    {
        String alphabet = "abcdefghijklmnopqrstuwxyz";
        Random random = new Random();
        return alphabet.charAt(random.nextInt(alphabet.length()));
    }
}
