package com.zhulf.www.callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author Administrator
 * 创建线程的第三种方法,采用Callable和Future
 */
class CallableThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("子线程id为: " + Thread.currentThread().getId());
		int a = new Random().nextInt(100);
		System.out.println("a: " + a);
		Thread.sleep(2000);
		return a;
	}
	
}

public class CreateThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CallableThread cl = new CallableThread();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(cl);
		
		// 第一种方式用线程池执行任务
//		ExecutorService executor = Executors.newCachedThreadPool();
//		executor.submit(futureTask);
//		executor.shutdown();
		
		// 第二种方式用普通的线程启动方式
		//new Thread(futureTask).start();
		
		// 第三种方式也是用线程池执行任务,把Callable作为submit参数
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> futureResult = executor.submit(cl);
		executor.shutdown();
		try {
			System.out.println("主线程id: " + Thread.currentThread().getId()
					+ "   futureResult: " + futureResult.get());
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int ret = 0;
		try {
			ret = futureTask.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("主线程id: " + Thread.currentThread().getId() 
				+ "   ret: " + ret);
	}

}
