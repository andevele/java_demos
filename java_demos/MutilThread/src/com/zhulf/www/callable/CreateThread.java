package com.zhulf.www.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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
		System.out.println("==zhulf===ret===" + Thread.currentThread().getId());
		Thread.sleep(2000);
		return 6;
	}
	
}

public class CreateThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CallableThread cl = new CallableThread();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(cl);
		new Thread(futureTask).start();
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
		System.out.println("==zhulf===ret===" + ret + " id===" + + Thread.currentThread().getId());
	}

}
