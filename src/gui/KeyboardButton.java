package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import utl.Parameters;

public class KeyboardButton extends JButton implements ActionListener{
    
    private final int ASCIIVALUE;
    private final char LETTER;
    
    private final Dimension buttonSize = Parameters.buttonDimension;
    
    private Insets buttonInsets = new Insets(Parameters.buttonPadding,Parameters.buttonPadding,Parameters.buttonPadding,Parameters.buttonPadding);
    
    public KeyboardButton(int charToUse){
        ASCIIVALUE = charToUse;
        LETTER = (char)ASCIIVALUE;
        init();
    }
    
    private void init(){
    
        this.setSize(buttonSize);
        this.setPreferredSize(buttonSize);
        this.setMargin(buttonInsets);
        
        this.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.darkGray));
        
        String toShow = "";
        toShow += LETTER;
        
        this.setText(toShow);
        this.addActionListener(this);
        
        int fontWidth = (this.getWidth()-this.getInsets().left-this.getInsets().right)/3*2;
        
        this.setFont(new Font("CustomFont",this.getFont().getStyle(),fontWidth));
        
    }
    
    public int getLetter(){
        return this.ASCIIVALUE;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ASCIIVALUE <= Parameters.ASCIIUPSTART + 26)
            GUIAssets.word.renderBlanks((char)(this.ASCIIVALUE+Parameters.CONVERT));
        else
            GUIAssets.word.renderBlanks((char)(this.ASCIIVALUE-Parameters.CONVERT));
        this.setEnabled(false);
    }

}
