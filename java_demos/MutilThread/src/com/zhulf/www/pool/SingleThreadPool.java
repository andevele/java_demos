package com.zhulf.www.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task2 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis() + " : ThreadId: " + Thread.currentThread().getId());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

public class SingleThreadPool {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Task2 task2 = new Task2();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			executorService.execute(task2);
		}
		executorService.shutdown();
	}
}
