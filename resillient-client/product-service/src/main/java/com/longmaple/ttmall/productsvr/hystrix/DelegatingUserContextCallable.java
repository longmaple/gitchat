package com.longmaple.ttmall.productsvr.hystrix;

import java.util.concurrent.Callable;

import com.longmaple.ttmall.productsvr.util.UserContextHolder;


public final class DelegatingUserContextCallable<V> implements Callable<V> {
	
	private final Callable<V> delegate;
	private String correlationId;

	public DelegatingUserContextCallable(Callable<V> delegate,
			String userContext) {
		this.delegate = delegate;
		this.correlationId = userContext;
	}

	public V call() throws Exception {
		UserContextHolder.setCorrelationId( correlationId );
		try {
			return delegate.call();
		}
		finally {
			this.correlationId = null;
		}
	}

	public static <V> Callable<V> create(Callable<V> delegate,
			String correlationId) {
		return new DelegatingUserContextCallable<V>(delegate, correlationId);
	}
}
