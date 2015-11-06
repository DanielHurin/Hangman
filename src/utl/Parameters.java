package utl;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import word.Word;

public class Parameters {
    
    /**
     * Global Vars
     */
    
    public static Word theWords = new Word();
    
    /**
     * GameFrame Vars
     */
    
    public static volatile Dimension gameFrameDimension = new Dimension(600,Toolkit.getDefaultToolkit().getScreenSize().height);
    
    /**
     * Gallow Vars
     */
    
    public static Dimension gallowDimension = new Dimension(gameFrameDimension.width,gameFrameDimension.height/2);
    public static Point gallowLocation = new Point(0,0);
    
    /**
     * Word Vars
     */
    
    public static Dimension wordDimension = new Dimension(gameFrameDimension.width,gameFrameDimension.height/6);
    public static Point wordBlanksPoint = new Point(0,gallowLocation.y+gallowDimension.height);
    
    /**
     * Button Vars
     */
    
    public static Dimension keyBoardDimension = new Dimension(gameFrameDimension.width,gameFrameDimension.height/6);
    public static Point keyBoardPoint = new Point(0,wordBlanksPoint.y+wordDimension.height);
    
    public static Dimension buttonDimension = new Dimension(50,50);
    public static int buttonPadding = 10;
    
    public static final int NUMLETTERS = 26;
    
    public static final int ASCIIUPSTART = 65;   
    public static final int ASCIILOSTART = 97;   
    
    public static final int CONVERT = 32;
    
    /**
     * Difficulty Buttons Panel Vars
     */
    
    public static Dimension difficultyDimension = new Dimension(gameFrameDimension.width, (int) (gameFrameDimension.height/24));
    
    public static Point difficultyPanelLocation = new Point(0,keyBoardPoint.y+keyBoardDimension.height);
    
    public static Dimension difficultyButtonDimension = new Dimension(gameFrameDimension.width/4, 24);
    
    public static int fontSize = 24;
    
    /**
     * Option Menu Vars
     */
    public static int menuFontSize = 24;
    
    public static Dimension menuDimension = new Dimension(gameFrameDimension.width,menuFontSize);
    
    public static Point menuLocation = new Point(0,0);
    
    /**
     * State Panel Vars
     */
    
    public static Dimension statsDimension = new Dimension(gameFrameDimension.width,(difficultyDimension.height+difficultyPanelLocation.y));

    public static Point statsLocation = new Point(0,difficultyPanelLocation.y+difficultyDimension.height);
}
