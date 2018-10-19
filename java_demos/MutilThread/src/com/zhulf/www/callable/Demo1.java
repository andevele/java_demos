package com.zhulf.www.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 
 * @author Administrator
 * callable,future,futuretask使用方法
 */
public class Demo1 {
	static ExecutorService mExecutor = Executors.newSingleThreadExecutor();

	static int fibc(int num) {
		if (num == 0) {
			return 0;
		}
		if (num == 1) {
			return 1;
		}

		return fibc(num - 1) + fibc(num - 2);
	}

	static void runableDemo() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Runnable demo:" + fibc(20));
			}
		}).start();
	}

	static void futureDemo() {
		try {
			/**
			 * 提交Runnable则没有返回值，futurn没有数据 使用submit提交任务会返回Future对象，而使用execute没有返回值。
			 * submit提交Runnable任务本质上也是转化为Callable去执行
			 */
			Future<?> result = mExecutor.submit(new Runnable() {
				@Override
				public void run() {
					fibc(20);
				}
			});
			System.out.println("future result from runnable:" + result.get());

			/**
			 * 提交Callable,有返回值，future中能够获取返回值
			 */
			Future<Integer> result2 = mExecutor.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					while (true) {
			            System.out.println("Task: Test\n");
			            Thread.sleep(100);
			        }
					
				}
			});
			if(!result2.isCancelled()) {
				result2.cancel(true);
			}
			System.out.println("future result from  callable:" + result2.isCancelled());

			/**
			 * FutureTask是一个RunnableFuture<V>，而RunnableFuture实现了Runnbale又实现了Futrue<V>这两个接口
			 * 同时包装了Runnable和Callable<V>， 由构造函数注入依赖。
			 * Runnable注入会被Executors.callable()函数转换为Callable类型，即FutureTask最终都是执行Callable类型的任务
			 */
			FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					return fibc(20);
				}
			});
			// 提交futureTask
			mExecutor.submit(futureTask);
			System.out.println("future result from futureTask(Callable):" + futureTask.get());

			Integer res = 0;

			FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new Runnable() {
				@Override
				public void run() {
					fibc(20);
				}
			}, res);

			mExecutor.submit(futureTask1);

			System.out.println("future result from futureTask(Runable):" + futureTask1.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		runableDemo();
		futureDemo();

		System.out.println("已经开启所有的子线程");
		mExecutor.shutdown();
		System.out.println("shutdown()：启动一次顺序关闭，执行以前提交的任务，但不接受新任务。");
		while (true) {
			if (mExecutor.isTerminated()) {
				System.out.println("所有的子线程都结束了！");
				break;
			}
		}
	}
}
