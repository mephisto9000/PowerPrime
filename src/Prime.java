import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author mephisto9000
 * desc: Eratosphenes' seive algo
 */
public class Prime implements Runnable {
	
	int n;
	
	Set<Integer> primes;
	boolean sieve[];
	
	BlockingQueue destQueue;
	BlockingQueue<String> resultQueue;
	
	public Prime(BlockingQueue destQueue, BlockingQueue resultQueue)
	{
		this.destQueue = destQueue;
		this.resultQueue = resultQueue;
		
		init();
	}
	
	public void init()
	{
		 	
		n = Parameters.primeLength ;
		
		sieve = new boolean[n + 1];
		primes = new HashSet<Integer>();  
				
		for (int i = 2; i <= n; i++) {   
		    sieve[i] = true;
		}
		 
		for (int i = 2; i <= n; i++) {
		        if (sieve[i]) {    
		            primes.add(i); 
		 
		            for ( int j =  i * i; j <= n; j += i) {
		                sieve[ j] = false;
		            }
		        }
		    }
	}
	
	public boolean isPrime(int x)
	{
		if (primes.contains(x))
			return true;
		else
			return false;
	}
			  

	@Override
	public void run() {
			
		while(true)
		{
			try {
			
				int v = (Integer) destQueue.take();
				String ans = v + (isPrime(v) ? " true" : " false");
				
				resultQueue.add(ans);
			
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
			
	}
	

}
