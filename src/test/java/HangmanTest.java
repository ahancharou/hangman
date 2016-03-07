import com.mentoring.game.Hangman;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HangmanTest {

    private Hangman hangman;

    @Before
    public void setup(){
        hangman = new Hangman();
    }

    @Test
    public void checkWordNotNull(){
        Assert.assertNotNull(hangman.getWord());
    }

    @Test
    public void checkWordLengthMoreThan1(){
        Assert.assertTrue(hangman.getWord().length()>1);
    }

    @Test
    public void checkIfLetterCorrectItCounts(){
        String word = hangman.getWord();
        char letter = word.charAt(0);
        int tries = hangman.getTries();
        boolean result = hangman.checkLetter(letter);
        Assert.assertTrue(result);
        Assert.assertEquals(tries,hangman.getTries());
    }

    @Test
    public void checkIfLetterIncorrectItCounts(){
        int tries = hangman.getTries();
        boolean result = hangman.checkLetter('Ъ');
        Assert.assertFalse(result);
        Assert.assertTrue(tries - hangman.getTries() == 1);
    }

    @Test
    public void checkAtStartThereIs6Tries(){
        Assert.assertEquals(6,hangman.getTries());
    }

    @Test
    public void checkIfYouFail6TimesYouLose(){
        for (int i = 0; i < 6; i++){
            hangman.checkLetter('Ъ');
        }
        Assert.assertTrue(hangman.gameOver());
    }

    @Test
    public void checkIfYouNameAllCharactersYouWin(){
        Hangman customHangman = new Hangman("word");
        customHangman.checkLetter('w');
        customHangman.checkLetter('o');
        customHangman.checkLetter('r');
        customHangman.checkLetter('d');
        Assert.assertFalse(customHangman.gameOver());
        Assert.assertEquals(customHangman.lettersLeft(), 0);
    }

    @Test
    public void checkMaskGeneratesCorrectly(){
        int length = hangman.getWord().length();
        Assert.assertEquals(length*2+1, hangman.getMask().length());
    }

    @Test
    public void checkLettersLeftAfterGuessing(){
        int letters = hangman.lettersLeft();
        hangman.checkLetter(hangman.getWord().charAt(0));
        Assert.assertEquals(letters, hangman.lettersLeft()+1);
    }
}
