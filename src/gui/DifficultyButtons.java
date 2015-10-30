package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import utl.Console;
import utl.Parameters;

public class DifficultyButtons extends ButtonGroup implements ActionListener{

    private Dimension difficultyDimensions = Parameters.dificultyDimension;

    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton hardButton;
    
    private int fontSize = Parameters.fontSize;
    
    public DifficultyButtons(){
        init();
    }
    
    private void init(){
        
        easyButton = makeButton("Easy");
        mediumButton = makeButton("Medium");
        hardButton = makeButton("Hard");
        
        //Set Default Button\\
        mediumButton.setSelected(true);
        
        this.add(easyButton);
        this.add(mediumButton);
        this.add(hardButton);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Console.outl("HelloWorld!");
    }
    
    public JRadioButton getEasyButton() {
        return easyButton;
    }

    public JRadioButton getMediumButton() {
        return mediumButton;
    }

    public JRadioButton getHardButton() {
        return hardButton;
    }
    
    public JRadioButton makeButton(String text){
        JRadioButton button = new JRadioButton(text);
        button.setText(text);
        button.setSize(Parameters.difficultyButtonDimension);
        button.setPreferredSize(Parameters.difficultyButtonDimension);
        button.setFont(new Font("CustomFont", button.getFont().getStyle(), this.fontSize));
        button.setBackground(null);
        button.setFocusPainted(false);
        return button;
    }
    
    
}
