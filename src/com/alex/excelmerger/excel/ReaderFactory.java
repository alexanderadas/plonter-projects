package com.alex.excelmerger.excel;

import com.alex.excelmerger.common.MergerProperties;

public class ReaderFactory
{
	private static ReaderFactory factory;
	
	
	public static ReaderFactory getFactory()
	{
		if (factory == null)
		{
			factory = new ReaderFactory(); 
			
		}
		return factory;
	}
	
	private ReaderFactory()
	{
		
	}
	
	public Reader create(MergerProperties properties)
	{
		Reader reader = new Reader();
		reader.setExcelFilename(properties.getExcelFile());
		reader.setMakatColumnName(properties.getExcelMakat());
		reader.setMezumanColumnName(properties.getExcelMezuman());
		reader.setPriceMultiplier(properties.getExcelPriceMultiplier());
		reader.setRowsBuffer(properties.getRowsBuffer());
		reader.setShemmuzarColumnName(properties.getExcelShemMuzar());
		reader.setYatzranColumnName(properties.getExcelYatzran());
		return reader;
	}
}
