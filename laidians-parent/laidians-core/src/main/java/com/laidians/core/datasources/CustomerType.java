package com.laidians.core.datasources;

public enum CustomerType {
    AUTO2012Q1(1, 2012, 1, "AUTO2012Q1", "auto2012Q1"),
    AUTO2012Q2(2, 2012, 2, "AUTO2012Q2", "auto2012Q2");

    private int index;
    private int year;
    private int season;
    private String datasource;
    private String dbName;

    private CustomerType(int index, int year, int season, String datasource, String dbName) {
        this.index = index;
        this.year = year;
        this.season = season;
        this.datasource = datasource;
        this.dbName = dbName;
    }

    public int getIndex() {
        return index;
    }

    public int getYear() {
        return year;
    }

    public int getSeason() {
        return season;
    }

    public String getDatasource() {
        return datasource;
    }

    public String getDbName() {
		return dbName;
	}

	public static CustomerType getDatasource(int year, int season) {
        for (CustomerType datasource : CustomerType.values()) {
            if (datasource.getYear() == year && datasource.getSeason() == season) {
                return datasource;
            }
        }
        return AUTO2012Q2;
    }
	
	public static CustomerType getDatasource(String sdatasource) {
        for (CustomerType datasource : CustomerType.values()) {
            if (datasource.getDatasource().equals(sdatasource)) {
                return datasource;
            }
        }
        return AUTO2012Q2;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("index:").append(this.getIndex()).append("; ");
		builder.append("year:").append(this.getYear()).append("; ");
		builder.append("season:").append(this.getSeason()).append("; ");
		builder.append("datasource:").append(this.getDatasource()).append("; ");
		builder.append("dbName:").append(this.getDbName()).append("; ");
		return builder.toString();
	}
}
