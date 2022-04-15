/**
 * This class will be used to determine if an entered number is Prime
 * 
 * @author Chad Andreniuk 
 * 
 */

public class PrimeIdentifier {

  /**
   * This method will take in a number
   * and return a boolean depending on if its prime or not
   *  
   * @param n this is number to be evaluated 
   * @return true will be returned if n is a Prime number
   * 		   false will be returned if n is not a prime number
   */
  public static boolean isPrime(int n) {
    if (n == 2)
      return true;

    if (n % 2 == 0)
      return false;

    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      if (n % i == 0)
        return false;
    }
    return true;
  }

}