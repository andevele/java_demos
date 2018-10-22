package com.zhulf.www.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class CallableImpl implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("========2008=== ");
		Long start = System.currentTimeMillis();
//		while (true) {
//			Long current = System.currentTimeMillis();
//			if ((current - start) > 3000) {
//				System.out.println("===2009== ");
//				return 1;
//			}
//		}
		try {
			System.out.println("========2009== ");
			Thread.sleep(3000);
			System.out.println("========2010== ");
		}catch(Exception e) {
			System.out.println("========“Ï≥£1== ");
			e.printStackTrace();
		}
		System.out.println("========2011== ");
		return 1;
	}
	
}

public class FutureDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CallableImpl cl = new CallableImpl();
		Future<Integer> future = executorService.submit(cl);
		System.out.println("===1001=== ");
		try {
			boolean iscancle = future.cancel(false);
			System.out.println("===1002=== ");
			System.out.println("  iscancle==" + iscancle);
			Integer result = (Integer) future.get();
			
			System.out.println("===result: " + result);
		} catch (Exception e) {
			System.out.println("===“Ï≥£2=== ");
//			e.printStackTrace();
		}
	}
}
