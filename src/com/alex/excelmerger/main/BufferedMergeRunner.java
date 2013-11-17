package com.alex.excelmerger.main;

import java.io.UnsupportedEncodingException;

import com.alex.excelmerger.common.MergerProperties;
import com.alex.excelmerger.model.ExcelModel;
import com.alex.excelmerger.model.MergedModel;

import jxl.Sheet;

public class BufferedMergeRunner extends AbstractMergerRunner
{

	BufferedMergeRunner(MergerProperties properties) throws UnsupportedEncodingException
	{
		super(properties);
	}

	@Override
	protected void mergeSheet(Sheet sheet) throws Exception
	{
		int currentRow = reader.getFirstRow();
		ExcelModel[] models = reader.createModel(sheet,currentRow);
		while (currentRow < sheet.getRows() - reader.getFirstRow() - 1)
		{
			Integer lastRow = reader.readSheet(sheet, currentRow, models);
			for (int i = 0; i < models.length; i++)
			{
				if (models[i].isValid())
				{
					MergedModel htmlRow = new MergedModel(models[i]);
					writer.append(htmlRow);
				}
			}
			currentRow += lastRow;
			models = reader.createModel(sheet,currentRow);
		}
	}

}
