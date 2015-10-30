package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import utl.Console;
import utl.Parameters;

public class Gallows extends JPanel{
    
    private int gallowsTier = 0;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;
    private Image guess1;
    private Image guess2;
    private Image guess3;
    private Image guess4;
    private Image guess5;
    private Image guess6;
    private Image guess7;
    private Image guess8;
    
    public Gallows(){
        init();
    }
    
    private void init(){
        
        this.setLocation(Parameters.gallowLocation);
        this.setSize(Parameters.gallowDimension);
        this.setPreferredSize(Parameters.gallowDimension);
        
        canvas = new Canvas();
        canvas.setSize(Parameters.gallowDimension);
        canvas.setPreferredSize(Parameters.gallowDimension);
        
        this.add(canvas);
        try{
            guess8 = ImageIO.read(new File("FileIO/HangmanRes/Guess8.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess7 = ImageIO.read(new File("FileIO/HangmanRes/Guess7.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess6 = ImageIO.read(new File("FileIO/HangmanRes/Guess6.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess5 = ImageIO.read(new File("FileIO/HangmanRes/Guess5.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess4 = ImageIO.read(new File("FileIO/HangmanRes/Guess4.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess3 = ImageIO.read(new File("FileIO/HangmanRes/Guess3.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess2 = ImageIO.read(new File("FileIO/HangmanRes/Guess2.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
            guess1 = ImageIO.read(new File("FileIO/HangmanRes/Guess1.png")).getScaledInstance(this.getSize().width, this.getSize().height,1);
        }catch (Exception e){
            Console.flag("Error Making Gallow Images", e);
        }            
    }
    
    public boolean renderGallows(){
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return true;
        }
        g = bs.getDrawGraphics();
        return false;
    }
    
    public void renderBoard(){
        try{
            switch(this.gallowsTier){
                case 8:
                    g.drawImage(guess8, 0, 0, this);
                case 7:
                    g.drawImage(guess7, 0, 0, this);
                case 6:
                    g.drawImage(guess6, 0, 0, this);
                case 5:
                    g.drawImage(guess5, 0, 0, this);
                case 4:
                    g.drawImage(guess4, 0, 0, this);
                case 3:
                    g.drawImage(guess3, 0, 0, this);
                case 2:
                    g.drawImage(guess2, 0, 0, this);
                case 1:
                    g.drawImage(guess1, 0, 0, this);
                    break;
                default:
                    g.clearRect(0, 0, canvas.getSize().width, canvas.getSize().height);
            }
            if(gallowsTier == 8){
                Parameters.theWords.solve();
                GUIAssets.word.simpleUpdate();
            }
        }catch (Exception e){
            Console.flag("Error Rendering the Gallows.", e);
        }
        bs.show();
        bs.show();
        bs.show();
        g.dispose();
        GUIAssets.optionMenu.setVisible(false);
        GUIAssets.optionMenu.setVisible(true);
    }
    
    public void setGallowsTier(int gallowsTier){
        this.gallowsTier = gallowsTier;
        simpleUpdate();
    }
    
    public void badGuess(){
        this.gallowsTier++;
        while(renderGallows());
        renderBoard();
    }
    
    public void simpleUpdate(){
        while(renderGallows());
        renderBoard();
    }
    
}
