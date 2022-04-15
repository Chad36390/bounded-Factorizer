import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * This class will be used to do the back end work of the Factorizer
 * Depending on which option is chosen, a different method will be invoked
 * 
 * @author Chad Andreniuk
 * 
 */
public class Backend {

    ArrayList < Integer > prime_list = new ArrayList < Integer > ();
    Map < Integer, Integer[] > composite_map = new HashMap < Integer, Integer[] > ();

   

    CopyOnWriteArrayList < Integer > unbounded_prime_list = new CopyOnWriteArrayList < Integer > ();
    Map < Integer, Integer[] > unbounded_composite_map = new ConcurrentHashMap < Integer, Integer[] > ();

    /**
     * This method will take in a number and do the necessary
     * computations to separate every number up to an entered number in a list of primes
     * and a hashmap of  <Integer, Integer[]> all in a sequential implementation
     * 
     * @param num this is the number going to be factored
     * @return void, but the prime_list and composite_map are populated
     */
    public void unthreaded(int entered_num) {

        prime_list.clear();
        composite_map.clear();

        long startTime = System.currentTimeMillis();

        for (int i = 2; i <= entered_num; ++i) {

            if (PrimeIdentifier.isPrime(i))
                prime_list.add(i);
            else
                composite_map.put(i, Factorizer.addDivisors(i));
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println();
        System.out.println("Number of primes betweeen 1 and " + entered_num + ": " + (prime_list.size()));

        System.out.println("Total time in milliseconds: " + totalTime);
    }
    
   

    /**
     * This method will take in a number and do the necessary
     * computations to separate every number up to an entered number in a list of primes
     * and a hashmap of  <Integer, Integer[]> all in a unbounded threaded implementation 
     * 
     * This method uses a CopyOnWriteArrayList and a ConcurrentHashMap
     * These are used to ensure that the information is correct 
     * 
     * @param num this is the number going to be factored
     * @return void, but the unbounded_prime_list and unbounded_composite_map are populated
     */
    public void unbounded(int entered_num) {

        unbounded_prime_list.clear();
        unbounded_composite_map.clear();
        
        long startTime = System.currentTimeMillis();

        class Runner implements Runnable {
            int n;
            Runner(int num) {
                n = num;
            }

            @Override
            public void run() {
                if (PrimeIdentifier.isPrime(n))
                    unbounded_prime_list.add(n);
                else
                    unbounded_composite_map.put(n, Factorizer.addDivisors(n));
            }

        }
        for (int i = 2; i <= entered_num; ++i) {
            Thread t = new Thread(new Runner(i));
            t.start();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println();
        System.out.println("Number of primes betweeen 1 and " + entered_num + ": " + (unbounded_prime_list.size()));

        System.out.println("Total time in milliseconds: " + totalTime);
    }

    
    
    /**
     * This method will take in a number and do the necessary
     * computations to separate every number up to an entered number in a list of primes
     * and a hashmap of  <Integer, Integer[]> all in a unbounded threaded implementation 
     * 
     * This method uses a CopyOnWriteArrayList and a ConcurrentHashMap
     * These are used to ensure that the information is correct 
     * 
     * This method uses a bounded thread pool, doing this will usually speed up
     * execution speeds depending on the size of the pool allocated
     * 
     * This method also 
     * 
     * @param num this is the number going to be factored
     * @param cores, this number will be used to set the size of the tread pool
     * @return void, but the unbounded_prime_list and unbounded_composite_map are populated
     */
    public long bounded(int entered_num, int cores) throws InterruptedException {
        unbounded_prime_list.clear();
        unbounded_composite_map.clear();

        long startTime = System.currentTimeMillis();

        class PrimeCalculator implements Callable < Integer > {

            private final Integer num;

            public PrimeCalculator(Integer num) {
                this.num = num;
            }

            @Override
            public Integer call() throws Exception {
                if (PrimeIdentifier.isPrime(num)) {
                    unbounded_prime_list.add(num);
                    return 0;
                } else {
                    unbounded_composite_map.put(num, Factorizer.addDivisors(num));
                    return 1;

                }
            }

        }


        ExecutorService executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores);



        for (int i = 2; i <= entered_num; i++) {
            executor.submit(new PrimeCalculator(i));

        }

        executor.shutdown();



        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        while (!executor.isTerminated()) {
            Thread.sleep(15);
        }

        System.out.println();
        System.out.println("Number of primes betweeen 1 and " + entered_num + ": " + (unbounded_prime_list.size()));
        System.out.println("Size of thread pool used for execution: " + cores);
        System.out.println("Total time in milliseconds: " + totalTime);
        return totalTime;

    }

}