package com.zhulf.www.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis() + " : ThreadId: " + Thread.currentThread().getId());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

public class FixThreadPool {
	public static void main(String[] args) {
		Task task = new Task();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			executorService.execute(task);
		}
//		executorService.shutdown();
	}
}
