package com.laidians.core.datasources;

public class CustomerContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSource(CustomerType customerType) {
		contextHolder.set(customerType.getDatasource());
	}

	public static String getDataSource() {
		return (String)contextHolder.get();
	}
	
	public static CustomerType getCustomerType() {
		return CustomerType.getDatasource((String)contextHolder.get());
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
}