/**
 * This class will be used to send all the UI messages
 * 
 * @author Chad Andreniuk
 * 
 */
public class UI {

  /**
   * This method prints the menu that starts the whole program
   */
  public static void startUI() {
    System.out.println("How would you like to run the program?");
    System.out.println("1. Unthreaded.");
    System.out.println("2. Un-bounded Threading.");
    System.out.println("3. Bounded Thread Pool.");
    System.out.print("To make your choice type the number and press enter: ");
  }


  
  /**
   * This method print the UI that is shown 
   * if a user enters an invalid input to the startUI function 
   */
  public static void tryAgainUI() {
    System.err.println("Invalid input. PLease try again");
    System.out.println("1. Unthreaded.");
    System.out.println("2. Un-bounded Threading.");
    System.out.println("3. Bounded Thread Pool.");
    System.out.print("To make your choice type the number and press enter: ");
  }


  /**
   * This method print the UI that is shown 
   * when selecting what number the user wants to factor
   */
  public static void factoringMenu() {
    System.out.print("Enter a number to be factored: ");
  }

  /**
   * This method prints the UI after the computations has finished
   * This simply asks if the program would like to be run again
   */
  public static void endMenu() {
    System.out.println("Would you like to run the program again(Y or N)");
  }
  
  /**
   * This method will print the UI after a user has selected the bounded thread pool option
   * This will inform the user of the choices they have for selecting a pool size
   * Entering a 0 after this will use the cores available on the machine + 1
   * Using a different number will set that to the thread pool size
   */
  public static void boundedPoolMenu() {
	  System.out.println("Choose a number to determine the size of the tread pool to be used:");
	  System.out.println("Entering 0 will use the available cores + 1.");
	  System.out.println("any other number will set that as the thread pool size.");
	  System.out.print("To make your selection type the number and press enter: ");
  }

}