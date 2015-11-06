package fx;

import gui.GUIAssets;
import utl.Console;

public class Animations implements Runnable{
    
    private volatile Thread thread;
    private boolean running = false;
    
    public void start(){
        if(running)
            return;
        thread = new Thread(this);
        running = true;
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
    
    @Override
    public void run(){
        
        long now;
        long past = System.nanoTime();
        long delta = 1000000000;
        double phase = 4;
        int numRuns = 0;
        while(running&&GUIAssets.gameFrame.isVisible()){
            if(delta*24 >= 1000000000){
                delta = 0;
                try {
                    phase+=.125;
                    if(phase>=5)
                        phase = 1;
                    GUIAssets.gallows.fireUpdate((int)phase,numRuns);
                    numRuns++;
                } catch (Exception ex) {
                    Console.flag("Error Drawing Graphics", ex);
                }
            }
            now = System.nanoTime();
            delta += now - past;
            past = now;
        }
    }
    
    public void stop(){
        try{
            if(!running)
                return;
            thread.join(100);
            running = false;
            Console.outl("JoinedThread");
        }catch(Exception e){
            Console.flag("Error Trying to Join Animations Thread",e);
        }
    }
    
}
