import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PowerPrime {

	public static void main(String[] args) {
						
		BlockingQueue destQ = new LinkedBlockingQueue();
		BlockingQueue resultQ = new LinkedBlockingQueue();
		
		
		Prime prime = new Prime(destQ, resultQ);
		Randomizer rand = new Randomizer(destQ, resultQ);
		Thread t1 = new Thread(prime);
		Thread t2 = new Thread(rand);
		
		t1.start();
		t2.start();
		
		System.out.println("program started");

	}

}
