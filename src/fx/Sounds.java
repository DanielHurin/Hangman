package fx;

import gui.GUIAssets;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import utl.Console;

public class Sounds {

    public static enum Sound implements Runnable{
        
        HAMMERING("FileIO/HangmanRes/HammeringHeavy.wav"),
        HAMMERINGWOOD("FileIO/HangmanRes/HammeringLight.wav"),
        ROPE("FileIO/HangmanRes/RopeSwinging.wav"),
        BELL("FileIO/HangmanRes/BellTolling.wav"),
        JOYFULBELL("FileIO/HangmanRes/BellRinging.wav");

        private AudioInputStream audioIn;
        private Clip clip;
        private Thread thread = new Thread(this);
        private String path;
        
        Sound(String path){
            this.path = path;
            try{
                audioIn = AudioSystem.getAudioInputStream(new File(path));
            }catch(Exception e){
                Console.flag("Error Initializing an Audio File.", e);
            }
        }
        
        public void playAudio(){
            boolean notPlayed = true;
            while(notPlayed){
                try {
                    audioIn = AudioSystem.getAudioInputStream(new File(this.path));
                    clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                    clip.setFramePosition(0);
                    thread = new Thread(Sound.this);
                    thread.start();
                    notPlayed = false;
                }catch (Exception e){
                    Console.flag("Error Playing an Audio File.", e);
                }
            }
        }
        
        public void stopAudio() throws InterruptedException{
            clip.close();
            if(thread.isAlive())
                thread.join(100);
        }

        @Override
        public void run(){
            long lastTime = System.nanoTime();
            long now;
            long delta = 0;
            while((double)delta/1000000000 <= 1.5){
                now = System.nanoTime();
                delta += now - lastTime;
                lastTime = now;
            }
            boolean notDead = true;
            if(this.equals(Sound.BELL)||this.equals(Sound.JOYFULBELL))
                while(clip.isActive() && notDead)
                    if(!GUIAssets.gameFrame.isVisible())
                        notDead = false;
            clip.close();
            try {
                thread.join(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public Thread getThread(){
            return thread;
        }
        
        public Clip getAudio(){
            return clip;
        }
    }
    
    private volatile static Sound sound;
    
    public static void playRandomHammeringNoise(){
        do{
            sound = Sound.values()[(int)(Math.random()*Sound.values().length)];
        }while(sound != Sound.HAMMERING && sound != Sound.HAMMERINGWOOD);
        playSound();
    }
    
    public static void playRandomRopeNoise(){
        do{
            sound = Sound.values()[(int)(Math.random()*Sound.values().length)];
        }while(sound != Sound.ROPE);
        playSound();
    }
    
    public static void playBell(){
        sound = Sound.BELL;
        playSound();
    }
    
    public static void playHappyBell(){
        sound = Sound.JOYFULBELL;
        playSound();
    }
    
    private static void playSound(){
        sound.playAudio();
    }
    
    public static void stopSound(){
        try {
            sound.stopAudio();
        } catch (Exception e) {
            Console.flag("Error Joining Thread: Most likely not running.", e);
        }
    }
}
