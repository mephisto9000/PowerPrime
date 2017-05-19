import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Randomizer implements Runnable {
		
	int n;
	
	BlockingQueue destQueue;
	BlockingQueue resultQueue;
	
	Randomizer(BlockingQueue queue, BlockingQueue resultQueue)
	{
		this.destQueue = queue;
		this.resultQueue = resultQueue;
		n = Parameters.primeLength;
	}
	
	public  int generate() throws InterruptedException
	{
		Random r = new Random();
		
		int x = r.nextInt(n) + 1;
		
		destQueue.put(x);
		
		return x;
	}

	@Override
	public void run() {
		
		while(true)
		{
			try {
				
				int x1 = generate();
				int x2 = generate();
				int x3 = generate();
				
				destQueue.add(x1);
				destQueue.add(x2);
				destQueue.add(x3);
								
				while(!resultQueue.isEmpty())
				{
					String ans = (String) resultQueue.take();
					
					System.out.println("Randomizer :"+ans);
				}

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	 

}
