import java.util.LinkedList;

/**
 * This class will take in a number a return a list of all its divisors
 * 
 * @author Chad Andreniuk
 * 
 */
public class Factorizer {

  /**
   * This method will take in a number
   * and return a Integer[] of factors for that number
   * 
   * @param num this is the number going to be factored
   * @return a Integer[] of the parameters factors
   */
  public static Integer[] addDivisors(int num) {
    LinkedList < Integer > factor_list = new LinkedList < Integer > ();

    for (int i = 1; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {

        if (num / i == i)
          factor_list.add(i);

        else
          factor_list.add(i);
        factor_list.add(num / i);
      }
    }

    return factor_list.toArray(new Integer[factor_list.size()]);

  }
}