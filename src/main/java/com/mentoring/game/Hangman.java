package com.mentoring.game;

import java.util.Random;

public class Hangman {

    private String word;
    private String letters;
    private String mask;
    private int tries;
    private Random random;
    private boolean LOST = false;

    public Hangman (){
        random = new Random();
        word = generateString(random.nextInt(5)+3);
        mask = generateMask(word);
        letters = removeDuplicates(word);
        tries = 6;
    }

    public Hangman (String word){
        random = new Random();
        this.word = word;
        mask = generateMask(word);
        letters = removeDuplicates(word);
        tries = 6;
    }

    public String getWord(){
        return word;
    }


    public boolean checkLetter(char letter) {
        boolean result = false;
        String newMask;
        if (letters.contains(""+letter)){
            result = true;
            for (int j = 1; j < word.length()-1; j++){
                if (word.charAt(j-1)==letter){
                    newMask = mask.substring(0,j*2-1)+letter+mask.substring(j*2,mask.length());
                    mask = newMask;
                }
            }
            letters=letters.replace(""+letter,"");
        } else{
            tries--;
            if (tries == 0) {
                LOST = true;
            }
        }
        return result;
    }

    public int getTries() {
        return tries;
    }

    public String getMask(){
        return mask;
    }

    private static String generateString(int length)
    {
        String alphabet = "abcdefghijklmnopqrstuwxyz";
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return new String(text);
    }

    private static String generateMask(String word){
        StringBuilder mask = new StringBuilder();
        for(int i = 0;i < word.length(); i++) {
            mask.append("|_");
        }
        mask.append("|");
        return mask.toString();

    }

    public boolean gameOver() {
        return LOST;
    }

    public int lettersLeft(){
        return letters.length();
    }

    private static String removeDuplicates(String s) {
        StringBuilder noDupes = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String si = s.substring(i, i + 1);
            if (noDupes.indexOf(si) == -1) {
                noDupes.append(si);
            }
        }
        return noDupes.toString();
    }
}
