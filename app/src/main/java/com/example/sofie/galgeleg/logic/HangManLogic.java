package com.example.sofie.galgeleg.logic;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by sofie on 23-10-2017.
 */

public class HangManLogic {
    private ArrayList<String> wordList = new ArrayList<String>();
    private String theWord;
    private ArrayList<String> usedLetters = new ArrayList<String>();
    private String word;
    private int guesses;
    private int wrongLetters;
    private boolean validateLetter;
    private boolean gameWon;
    private boolean gameLost;
    private int score;


    public ArrayList<String> getBrugteBogstaver() {
        return usedLetters;
    }

    public String getWord() {
        return word;
    }
    public String setWord(String chosenWord) {
        theWord = chosenWord;
        System.out.println("Ordet er: " + theWord);
        return word;}

    public String getTheWord() {
        return theWord;
    }

    public ArrayList<String> getMuligeOrd() {
        return wordList;
    }
    public int getGuesses() {
        return guesses;
    }
    public int getWrongLetters() {
        return wrongLetters;
    }
    public boolean LastLetter() {
        return validateLetter;
    }
    public boolean gameWon() {
        return gameWon;
    }
    public boolean gameLost() {
        return gameLost;
    }


    public HangManLogic() {
        guesses = 0;
        wordList.add("rover");
        wordList.add("invoice");
        wordList.add("sun");
        wordList.add("iron");
        wordList.add("television");
        wordList.add("bicycle");
        tara();
    }

    public void tara() {
        usedLetters.clear();
        guesses = 0;
        wrongLetters = 0;
        gameWon = false;
        gameLost = false;

        theWord = wordList.get(new Random().nextInt(wordList.size()));
        updateWord();
    }


    private void updateWord() {
        word = "";
        gameWon = true;
        for (int n = 0; n < theWord.length(); n++) {
            String bogstav = theWord.substring(n, n + 1);
            if (usedLetters.contains(bogstav)) {
                word = word + bogstav;
            } else {
                word = word + " _";
                gameWon = false;
            }
        }
    }

    public void guessLetter(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Guess: " + bogstav);
        if (usedLetters.contains(bogstav)) return;
        if (gameWon || gameLost) return;

        guesses++;

        usedLetters.add(bogstav);

        if (theWord.contains(bogstav)) {
            validateLetter = true;
            System.out.println("Letter correct: " + bogstav);
        } else {
            validateLetter = false;
            System.out.println("Letter not correct: " + bogstav);
            wrongLetters = wrongLetters + 1;
            if (wrongLetters == 6) {
                gameLost = true;
            }
        }
        updateWord();
    }

    public void guesWord(String ord) {
        if (usedLetters.contains(ord)) return;
        if (gameWon || gameLost) return;

        guesses++;

        usedLetters.add(ord);

        if (theWord.equals(ord)) {
            validateLetter = true;
            gameWon = true;
            word = theWord;
            return;
        } else {
            validateLetter = false;
            System.out.println("Not correct: " + ord);
            wrongLetters = wrongLetters + 1;
            if (wrongLetters == 6) {
                gameLost = true;
            }
        }
        updateWord();
    }
    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + theWord);
        System.out.println("- synligtOrd = " + word);
        System.out.println("- forkerteBogstaver = " + wrongLetters);
        System.out.println("- brugeBogstaver = " + usedLetters);
        if (gameLost) System.out.println("- SPILLET ER TABT");
        if (gameWon) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }
    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    public void hentOrdFraDr() throws Exception {
        String data = hentUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        wordList.clear();
        wordList.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));
        wordList.equals("Born");
        System.out.println("muligeOrd = " + wordList);
        tara();
    }

}
