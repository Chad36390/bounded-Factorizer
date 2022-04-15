import java.util.Scanner;

/**
 * This class will be used to initiate the other classes 
 * 
 * @author Chad Andreniuk
 * 
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {

        Backend be = new Backend();

        boolean loopStatus = true;
        
        long totalTime = 0;
        
        for(int i = 0; i<= 10; i++) {
        //while (loopStatus) {
            Scanner kb = new Scanner(System.in);
            UI.startUI();

            //int processing_choice = kb.nextInt();
            
            int processing_choice = 1;

            UI.factoringMenu();
            

            //int entered_num = kb.nextInt();
            
            int entered_num = 1000000;

            System.out.println();
            
            if (processing_choice == 1) 
                be.unthreaded(entered_num);
            
            if (processing_choice == 2) 
                be.unbounded(entered_num);
            
            if (processing_choice == 3) {

                UI.boundedPoolMenu();
                //int cores = kb.nextInt();
                
                int cores = 50;
                if (cores == 0) 
                    cores = Runtime.getRuntime().availableProcessors() + 1;
                
               totalTime = totalTime + be.bounded(entered_num, cores);
            }

            UI.endMenu();
            //String choice = kb.next();
            
            String choice = "y";

            if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                loopStatus = false;
                kb.close();
            }
            
            

        //}
        }
        
       
      
        System.out.println("Average Time using option: 3 is " + totalTime/10 );

    }
}