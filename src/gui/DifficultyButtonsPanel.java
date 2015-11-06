package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import utl.Parameters;

public class DifficultyButtonsPanel extends JPanel{
    
    private Dimension difficultyPanelDimension = Parameters.difficultyDimension;
    
    public DifficultyButtonsPanel(){
        init();
    }
    
    private void init(){
        
        this.setSize(difficultyPanelDimension);
        this.setPreferredSize(difficultyPanelDimension);
        this.setLocation(Parameters.difficultyPanelLocation);
        
        this.add(GUIAssets.diffButtons.getEasyButton());
        this.add(GUIAssets.diffButtons.getMediumButton());
        this.add(GUIAssets.diffButtons.getHardButton());
        
        this.setBackground(Color.yellow);
    }
    
}
