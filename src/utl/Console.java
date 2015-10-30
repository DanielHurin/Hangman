package utl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
public class Console {
    
    private static final boolean SUPPRESS = false;
    
    public static void outl(String out){
        System.out.println(out);
    }
    
    public static void outl(int out){
        System.out.println(out);
    }
    
    public static void outl(double out){
        System.out.println(out);
    }
    
    public static void outl(boolean out){
        System.out.println(out);
    }
    
    //=-=-=\\
    
    public static void out(String out){
        System.out.print(out);
    }
    
    public static void out(int out){
        System.out.print(out);
    }
    
    public static void out(double out){
        System.out.print(out);
    }
    
    public static void out(boolean out){
        System.out.print(out);
    }
    
    //=-=-=\\
    
    public static void flag(String output, Exception e){
        if(SUPPRESS)
            return;        
        System.out.println("\n\n-=-=-= FlagStart =-=-=-\n");
        out(output+"\n\nError:  ");            
        e.printStackTrace(System.out);
        try{
            Thread.sleep(100);
        }catch(InterruptedException ie){
            outl("Whoopsie.  Looks like sleeping went bad!");
        }        
        out("\n-=-=-= FlagEnd =-=-=-\n");
    }
    
}
