package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utl.Parameters;

public class WordBlanks extends JPanel{
    
    public String word = "";
    public String wordBlanksString = "";
    public JLabel wordBlanks = new JLabel();
    
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
    }
    
    public void simpleUpdate(){
        wordBlanks.setText(Parameters.theWords.getBlanks());
        wordBlanks.updateUI();
    }
    
    public void makeWord(){
        this.wordBlanksString = "";
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
    }
    
    public String getWord(){
        return this.word;
    }
    
    
}
