package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import utl.Parameters;

public class OptionMenu extends JMenuBar implements ActionListener{
    
    private JMenuItem quit;
    private JMenuItem forfeit;
    private JMenuItem restart;
    
    public OptionMenu(){
        init();
    }
    
    private void init(){
        this.setSize(Parameters.menuDimension);
        this.setPreferredSize(Parameters.menuDimension);
        this.setLocation(Parameters.menuLocation);
        
        
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        
        forfeit = new JMenuItem("Forfeit");
        forfeit.addActionListener(this);
        
        restart = new JMenuItem("Restart");
        restart.addActionListener(this);
        
        this.add(quit);
        this.add(forfeit);
        this.add(restart);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==quit)
            GUIAssets.gameFrame.dispose();
        if(ae.getSource()==forfeit)
            GUIAssets.gallows.setGallowsTier(8);
        if(ae.getSource()==restart){
            GUIAssets.keyBoard.resetKeys();
            GUIAssets.gallows.setGallowsTier(0);
            GUIAssets.word.makeWord();
        }
    }
    
    
}
