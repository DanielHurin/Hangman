package gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import utl.Console;
import utl.Parameters;

public class Keyboard extends JPanel implements KeyListener{
    
    public Keyboard(){
        init();
    }
    
    private void init(){
        
        this.setSize(Parameters.keyBoardDimension);
        this.setPreferredSize(Parameters.keyBoardDimension);
        this.setLocation(Parameters.keyBoardPoint);
        
        for(int x = Parameters.ASCIIUPSTART; x < Parameters.ASCIIUPSTART+Parameters.NUMLETTERS; x++){
            this.add(new KeyboardButton(x));
            this.getComponent(x-Parameters.ASCIIUPSTART).addKeyListener(this);
        }
        
        this.setBackground(Color.lightGray);
        this.setBorder(BorderFactory.createLineBorder(Color.black,3,true));
    }
    
    public void resetKeys(){
        for(int x = 0; x < this.getComponentCount(); x++)
            this.getComponent(x).setEnabled(true);
    }
    
    public void disableKeys(){
        for(int x = 0; x < this.getComponentCount(); x++)
            this.getComponent(x).setEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Console.outl("HelloWorld!");
        for(int x = 0; x < this.getComponentCount(); x++)
            if(this.getComponent(x).isEnabled())
                try{
                    Console.outl(((KeyboardButton)this.getComponent(x)).getLetter());
                    Console.outl((int)ke.getKeyChar());
                    if(((KeyboardButton)this.getComponent(x)).getLetter()==(int)ke.getKeyChar() ||
                       ((KeyboardButton)this.getComponent(x)).getLetter()+Parameters.CONVERT==(int)ke.getKeyChar()){
                       ((KeyboardButton)this.getComponent(x)).setSelected(true);
                       ((KeyboardButton)this.getComponent(x)).doClick();
                       ((KeyboardButton)this.getComponent(x)).setSelected(false);
                    }
                }catch (Exception e){
                    Console.flag("Most Likely Cast Class Exception Checking Components in Keboard.  Please do not add anything other than KeyboardButtons to the Keyboard!", e);
                }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
