package word;

import gui.GUIAssets;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import utl.Console;
import utl.Parameters;

public class Word {
    
    private static ArrayList<String> words;
    
    private static ArrayList<String> easyWords;
    private static ArrayList<String> mediumWords;
    private static ArrayList<String> hardWords;
    
    private String currentWord;
    private String currentWordBlanks;
    private String badGuesses;
    
    private ArrayList<Character> guessedLetters = new ArrayList();
    
    public static void readFile(String path){
        try{
            Scanner sc = new Scanner(new File(path));
            words = new ArrayList();
            while(sc.hasNext()){
                words.add(sc.nextLine());
            }
            analyzeStrings();
            sc.close();
        }catch(Exception e){
            Console.flag("Error Trying to read file at Path: "+path, e);
        }//catch
    }//readFile
    
    public static void analyzeStrings(){
        
        easyWords = new ArrayList();
        mediumWords = new ArrayList();
        hardWords = new ArrayList();
        
        for(String word:words){
            int numVowels = countVowels(word);
            int numCL = countCommonLetters(word);
            int numL = word.length();
            if(numL <= 4 && (numCL >= 1 || numVowels <= 2))
                easyWords.add(word);
            else
                if((numL < 8 && numL > 4) && (numVowels >= 2 || numCL >= 2))
                    mediumWords.add(word);
            else
                    hardWords.add(word);
        }//for(String word:words)
    }//analyzeStrings
    
    public static int countCommonLetters(String word){
        int numCL = 0;
        
        String commonLetters = "rstln";
        for(int x = 0; x < commonLetters.length(); x++){
            String currentLetter = commonLetters.substring(x, x+1);
            for(int y = 0; y < word.length(); y++){
                y = word.indexOf(currentLetter,y);
                if(y != -1)
                    numCL++;
                else
                    y = word.length();
            }
        }//for(int x  0; x < commonLetters.length(); x++)
        return numCL;
    }//countCommonLetters
    
    public static int countVowels(String word){
        int numVowels = 0;
        
        String vowels = "aeiou";
        for(int x = 0; x < vowels.length(); x++){
            String currentLetter = vowels.substring(x, x+1);
            for(int y = 0; y < word.length(); y++){
                y = word.indexOf(currentLetter,y);
                if(y != -1)
                    numVowels++;
                else
                    y = word.length();
            }
        }//for(int x = 0; x < vowels.length(); x++)
        return numVowels;
    }//countVowels
    
    public void resetGuessList(){
        this.guessedLetters = new ArrayList();
    }
    
    public String getCurrentWord(){
        return currentWord;
    }//
    
    public ArrayList<Character> getGuessedLetters(){
        return this.guessedLetters;
    }
    
    public String getEasyWord(){
        currentWord = easyWords.remove((int)(Math.random()*easyWords.size()-1));
        this.currentWordBlanks = "";
        return currentWord;
    }//getEasyWord
    
    public String getMediumWord(){
        currentWord = mediumWords.remove((int)(Math.random()*mediumWords.size()-1));
        this.currentWordBlanks = "";
        return currentWord;
    }//getMediumWord
    
    public String getHardWord(){
        currentWord = hardWords.remove((int)(Math.random()*hardWords.size()-1));
        this.currentWordBlanks = "";
        return currentWord;
    }//getHardWord
    
    public void solve(){
        int x = Parameters.ASCIILOSTART;
        while(x < Parameters.ASCIILOSTART+26){
            int y = 0;
            String letter = (char)x+"";;
            while((y = currentWord.indexOf(letter, y))!=-1){
                String currentWordBlanksOld = currentWordBlanks;
                currentWordBlanks = currentWordBlanks.substring(0,y*2);
                currentWordBlanks += letter;
                y++;
                currentWordBlanks += currentWordBlanksOld.substring(y*2-1,currentWordBlanksOld.length());
            }
            x++;
        }
    }
    
    public void checkLetter(char let){
        String letter = let+"";
        boolean foundLetter = false;
        int x = 0;
        Console.outl("CheckingLetter:"+let);
        while((x = currentWord.indexOf(letter, x))!=-1){
            String currentWordBlanksOld = currentWordBlanks;
            currentWordBlanks = currentWordBlanks.substring(0,x*2);
            currentWordBlanks += letter;
            x++;
            currentWordBlanks += currentWordBlanksOld.substring(x*2-1,currentWordBlanksOld.length());
            foundLetter = true;
        }
        if(!foundLetter)
            GUIAssets.gallows.badGuess();
    }
    
    public void generateBlanks(){
        int x = 0;
        while(this.currentWord.length()>x){
            this.currentWordBlanks += "_ ";
            x++;
        }
        Console.outl(x);
    }
    
    public String getBlanks(){
        return this.currentWordBlanks;
    }
    
    @Override
    public String toString(){
        return "\nThe Word Is: "+currentWord+
                "\n\tCL: "+countCommonLetters(currentWord)+
                "\n\tNumber of Vowels: "+countVowels(currentWord);
    }//toString
    
}
