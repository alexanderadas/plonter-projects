package com.alex.excelmerger.main;

import java.io.UnsupportedEncodingException;

import jxl.Sheet;

import com.alex.excelmerger.common.MergerProperties;
import com.alex.excelmerger.common.event.SheetChangedEvent;
import com.alex.excelmerger.excel.Reader;
import com.alex.excelmerger.excel.ReaderFactory;
import com.alex.excelmerger.output.OutputFactory;
import com.alex.excelmerger.output.OutputWriter;

abstract class AbstractMergerRunner
{
	protected Reader reader;
	protected OutputWriter writer;
	
	
	AbstractMergerRunner(MergerProperties properties) throws UnsupportedEncodingException 
	{
		reader = ReaderFactory.getFactory().create(properties);
		writer = OutputFactory.getFactory().createWriter(properties);
		writer.append(OutputFactory.getFactory().createHeader(properties));	
	}
	
	abstract protected void mergeSheet(Sheet sheet) throws Exception;
	
	void merge() throws Exception
	{
		SheetChangedEvent event = SheetChangedEvent.getEvent();
		Sheet[] sheets = reader.sheets();
		event.fireBeforeFirst(sheets.length);
		for (int i = 0;i < sheets.length;i++)
		{
			Sheet sheet = sheets[i];
			String sheetName = sheet.getName();
			event.fireSheetChagned(sheetName, i);
			if (reader.findColumns(sheet) == false)
			{
				event.fireSheetColumnMissing(sheetName, reader.getInvalidColumns());
				continue;
			}
			mergeSheet(sheet);
		}
		reader.close();
		writer.flush();
		event.fireSheetLastMerged();
	}
}
