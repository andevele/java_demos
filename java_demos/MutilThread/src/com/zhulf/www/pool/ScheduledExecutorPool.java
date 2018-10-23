package com.zhulf.www.pool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Task4 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis() + " : ThreadId: " + Thread.currentThread().getId());
		try {
			Random random = new Random();
			int value = random.nextInt(2000);
			System.out.println("===random value===" + value);
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
public class ScheduledExecutorPool {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("start run");
		Task4 task4 = new Task4();
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
//		executorService.scheduleAtFixedRate(task4, 1000, 3000, TimeUnit.MILLISECONDS);
		executorService.scheduleWithFixedDelay(task4, 1000, 3000, TimeUnit.MILLISECONDS);
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}
