package gui;

import fx.Animations;
import fx.Sounds;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import utl.Console;
import utl.Parameters;

public class WordBlanks extends JPanel{
    
    public String word = "";
    public String wordBlanksString = "";
    public JLabel wordBlanks = new JLabel();
    
    Animations animations = new Animations();
    
    public WordBlanks(){
        init();
    }
    
    private void init(){
        this.setSize(Parameters.wordDimension);
        this.setPreferredSize(Parameters.wordDimension);
        this.setLayout(new GridBagLayout());

        wordBlanks.setSize(Parameters.wordDimension);
        wordBlanks.setPreferredSize(Parameters.wordDimension);
        
        this.add(wordBlanks);
        
        this.setLocation(Parameters.wordBlanksPoint);
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.black,3,true));
        
        makeWord();
    }
    
    public void renderBlanks(char let){
        Parameters.theWords.checkLetter(let);
        wordBlanks.setText(Parameters.theWords.getBlanks());
        wordBlanks.updateUI();
        if(Parameters.theWords.checkWin()&&GUIAssets.gallows.getGallowsTier()!=8){
            animations.start();
            GUIAssets.keyBoard.disableKeys();
            GUIAssets.optionMenu.toggleForfeit();
            GUIAssets.keyBoard.disableKeys();
            GUIAssets.statsPanel.won();
            Sounds.playHappyBell();
            JFrame test = new JFrame();
            test.setSize(100,100);
            test.setLocation((Parameters.gameFrameDimension.width/2)-test.getWidth(),Parameters.gallowLocation.y+Parameters.gallowDimension.height/3-test.getHeight());
            test.setVisible(true);
            JOptionPane.showMessageDialog(test,"You Won!  Congrats!");
            test.dispose();
        }
    }
    
    public void simpleUpdate(){
        wordBlanks.setText(Parameters.theWords.getBlanks());
        wordBlanks.updateUI();
    }
    
    public void makeWord(){
        this.wordBlanksString = "";
        Sounds.stopSound();
        animations.stop();
        try{
            GUIAssets.gallows.setGallowsTier(0);
            GUIAssets.gallows.simpleUpdate();
        }catch(Exception e){
            Console.flag("Error Trying to update Gallows.  Probably first time.",e);
        }if(!GUIAssets.optionMenu.isForfeitEnabled())
            GUIAssets.optionMenu.toggleForfeit();
        if(GUIAssets.diffButtons.getEasyButton().isSelected())
            this.word = Parameters.theWords.getEasyWord();
        if(GUIAssets.diffButtons.getMediumButton().isSelected())
            this.word = Parameters.theWords.getMediumWord();
        if(GUIAssets.diffButtons.getHardButton().isSelected())
            this.word = Parameters.theWords.getHardWord();
        int fontSize = this.getWidth()/word.length();
        if(fontSize >= this.getHeight()-80)
            fontSize = this.getHeight()-80;
        wordBlanks.setFont(new Font("CustomFont", wordBlanks.getFont().getStyle(), fontSize));
        Parameters.theWords.generateBlanks();
        wordBlanks.setText(Parameters.theWords.getBlanks());
        Console.outl(Parameters.theWords.toString());
    }
    
    public String getWord(){
        return this.word;
    }
    
    
}
