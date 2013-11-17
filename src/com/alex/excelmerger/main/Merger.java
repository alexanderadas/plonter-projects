package com.alex.excelmerger.main;

import java.text.NumberFormat;
import java.util.Date;

import com.alex.excelmerger.common.MergerProperties;

public class Merger
{
	public static void main(String[] args) throws Exception
	{
		Date before = new Date();
		MergerProperties properties = new MergerProperties();
		if (!properties.load())
		{
			return;
		}
		AbstractMergerRunner runner = properties.useBuffer()? new BufferedMergeRunner(properties) : new MergerRunner(properties);
		runner.merge();
		
		Date after = new Date();
		long diff = after.getTime() - before.getTime();
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(1);
		System.out.println("Took " + format.format(diff/1000.0) + " seconds");
	}
}
