package gui;

import javax.swing.JFrame;
import utl.Parameters;

public class GameFrame extends JFrame{
    
    
    public GameFrame(){
        init();
    }
    
    private void init(){
        
        this.setUndecorated(true);
        this.setSize(Parameters.gameFrameDimension);
        this.setPreferredSize(Parameters.gameFrameDimension);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        
        this.add(GUIAssets.optionMenu);
        this.add(GUIAssets.gallows);
        this.add(GUIAssets.word);
        this.add(GUIAssets.keyBoard);
        this.add(GUIAssets.statsPanel);
        this.add(GUIAssets.diffButtonsPanel);
        
        GUIAssets.optionMenu.addKeyListener(GUIAssets.keyBoard);
        GUIAssets.gallows.addKeyListener(GUIAssets.keyBoard);
        GUIAssets.word.addKeyListener(GUIAssets.keyBoard);
        GUIAssets.keyBoard.addKeyListener(GUIAssets.keyBoard);
        GUIAssets.statsPanel.addKeyListener(GUIAssets.keyBoard);
        GUIAssets.diffButtonsPanel.addKeyListener(GUIAssets.keyBoard);
        
        
        addKeyListener(GUIAssets.keyBoard);
        
        this.pack();
    }
    
}
