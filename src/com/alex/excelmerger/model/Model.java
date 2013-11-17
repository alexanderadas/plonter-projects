package com.alex.excelmerger.model;

public class Model
{
	public static final String SUPPLIER = "CDLOG";
	
	protected String id;
	protected String manufacturer;
	protected Double price;
	protected String description;
	
	public String getId()
	{
		return id;
	}
	
	public String getManufacturer()
	{
		return manufacturer;
	}
	
	public Double getPrice()
	{
		return price;
	}
	
	public String getDescription()
	{
		return description;
	}
	
}
