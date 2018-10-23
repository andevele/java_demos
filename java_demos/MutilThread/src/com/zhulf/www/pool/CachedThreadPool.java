package com.zhulf.www.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task3 implements Runnable {

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
		System.out.println(System.currentTimeMillis() + " : ThreadId: " + Thread.currentThread().getId() + " end");
	}

}

public class CachedThreadPool {
	public static void main(String[] args) {
		Task3 task3 = new Task3();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executorService.execute(task3);
		}
		try {
			Thread.sleep(62000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			executorService.execute(task3);
		}

		executorService.shutdown();
	}
}
