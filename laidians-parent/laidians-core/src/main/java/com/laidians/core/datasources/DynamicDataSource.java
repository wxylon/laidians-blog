package com.laidians.core.datasources;

import java.sql.SQLException;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {

   protected Object determineCurrentLookupKey() {
      return CustomerContextHolder.getDataSource();
   }

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
	
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
}
