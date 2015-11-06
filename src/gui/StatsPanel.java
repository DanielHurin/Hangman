package gui;

import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import utl.Console;
import utl.Parameters;

public class StatsPanel extends JPanel{

    private JTextPane stats;
    
    private int numWins = 0;
    private int numGames = 0;
    private double winPercent = 0.0;
    private DecimalFormat percent = new DecimalFormat("0.00");
    
    public StatsPanel(){
        init();
    }
    
    private void init(){
        
        StyledDocument document = new DefaultStyledDocument();
        Style defaultStyle = document.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_CENTER);
        
        setSize(Parameters.statsDimension);
        setPreferredSize(Parameters.statsDimension);
        
        setLocation(Parameters.statsLocation);
        setLayout(null);
        
        stats = new JTextPane();
        
        stats.setDocument(document);
        stats.setSize(getSize());
        stats.setLocation(0,0);
        stats.setEditable(false);
        
        updateText();
        
        add(stats);
        
    }
    
    public void updateText(){
        Console.outl(stats.getFontMetrics(stats.getFont()).getHeight());
        stats.setText("Number of Wins: "+numWins+" | Number of Games: "+numGames+"\nWin Percentage: "+percent.format(winPercent)+"%");
        int fontSize = stats.getFont().getSize();
        while(stats.getFontMetrics(stats.getFont()).getStringBounds(stats.getText().split("\\n")[0], stats.getGraphics()).getMaxX() <= this.getSize().width && stats.getFontMetrics(stats.getFont()).getStringBounds(stats.getText(), stats.getGraphics()).getHeight() <= getHeight()){
            fontSize++;
            stats.setFont(stats.getFont().deriveFont(stats.getFont().getStyle(), fontSize));
        }
        fontSize--;
        stats.setFont(stats.getFont().deriveFont(stats.getFont().getStyle(), fontSize));
        Console.outl(stats.getFontMetrics(stats.getFont()).getHeight());
    }
    
    public void won(){
        this.numWins++;
        this.numGames++;
        this.winPercent = (double) this.numWins / this.numGames * 100;
        updateText();
    }
    
    public void lost(){
        this.numGames++;
        this.winPercent = (double) this.numWins / this.numGames * 100;
        updateText();
    }
    
}
