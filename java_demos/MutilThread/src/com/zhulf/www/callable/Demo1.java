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
 * callable,future,futuretaskʹ�÷���
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
			 * �ύRunnable��û�з���ֵ��futurnû������ ʹ��submit�ύ����᷵��Future���󣬶�ʹ��executeû�з���ֵ��
			 * submit�ύRunnable��������Ҳ��ת��ΪCallableȥִ��
			 */
			Future<?> result = mExecutor.submit(new Runnable() {
				@Override
				public void run() {
					fibc(20);
				}
			});
			System.out.println("future result from runnable:" + result.get());

			/**
			 * �ύCallable,�з���ֵ��future���ܹ���ȡ����ֵ
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
			 * FutureTask��һ��RunnableFuture<V>����RunnableFutureʵ����Runnbale��ʵ����Futrue<V>�������ӿ�
			 * ͬʱ��װ��Runnable��Callable<V>�� �ɹ��캯��ע��������
			 * Runnableע��ᱻExecutors.callable()����ת��ΪCallable���ͣ���FutureTask���ն���ִ��Callable���͵�����
			 */
			FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					// TODO Auto-generated method stub
					return fibc(20);
				}
			});
			// �ύfutureTask
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

		System.out.println("�Ѿ��������е����߳�");
		mExecutor.shutdown();
		System.out.println("shutdown()������һ��˳��رգ�ִ����ǰ�ύ�����񣬵�������������");
		while (true) {
			if (mExecutor.isTerminated()) {
				System.out.println("���е����̶߳������ˣ�");
				break;
			}
		}
	}
}
