package com.alex.excelmerger.main;

import java.io.UnsupportedEncodingException;
import java.util.List;

import jxl.Sheet;

import com.alex.excelmerger.common.MergerProperties;
import com.alex.excelmerger.model.ExcelModel;
import com.alex.excelmerger.model.MergedModel;

class MergerRunner extends AbstractMergerRunner
{

	MergerRunner(MergerProperties properties)throws UnsupportedEncodingException
	{
		super(properties);
	}

	@Override
	protected void mergeSheet(Sheet sheet) throws Exception
	{
		List<ExcelModel> models = reader.readSheet(sheet);
		for (int i = 0; i < models.size(); i++)
		{
			writer.append(new MergedModel(models.get(i)));
		}
	}

	

}
