package com.alex.excelmerger.output;

import java.io.UnsupportedEncodingException;

import com.alex.excelmerger.common.MergerProperties;

public class OutputFactory
{
	private static OutputFactory factory;
	
	private OutputFactory()
	{
		
	}
	
	public static OutputFactory getFactory()
	{
		if (factory == null)
		{
			factory = new OutputFactory();
		}
		return factory;
	}
	
	public String createHeader(MergerProperties properties)
	{
		String outputMakatColumnName = properties.getOutputMakat();
		String outputYatzranColumnName = properties.getOutputYatzran();
		String outputSapakColumnName = properties.getOutputSapak();
		String outputPriceColumnName = properties.getOutputPrice();
		String outputDescriptionColumnName = properties.getOutputDescription();
		//print headers
		return String.format("%s\t%s\t%s\t%s\t%s", 
				outputMakatColumnName ,
				outputYatzranColumnName ,
				outputSapakColumnName ,
				outputPriceColumnName ,
				outputDescriptionColumnName );
	}
	
	public OutputWriter createWriter(MergerProperties properties) throws UnsupportedEncodingException
	{
		OutputWriter writer = new OutputWriter();
		writer.setFilename(properties.getOutputFile());
		return writer;
	}
}
