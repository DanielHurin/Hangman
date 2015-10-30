
import gui.GUIAssets;
import gui.GameFrame;
import javax.swing.UIManager;
import utl.Console;
import word.Word;

public class Driver {
    
    public static void main(String[] args) {
        Word.readFile("FileIO/wordsHangman.txt");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            Console.flag("Issue creating look and feel.", e);
        }
        GameFrame gameFrame = GUIAssets.gameFrame;
        gameFrame.update(gameFrame.getGraphics());
    }
    
}
