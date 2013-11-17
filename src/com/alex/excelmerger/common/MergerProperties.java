package com.alex.excelmerger.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.alex.excelmerger.common.event.PropertiesValidationEvent;

public class MergerProperties
{
	private static final String DEFAULT_MULTIPLIER = "1.0";
	private static final String EXCEL_MAKAT = "excel.makat";
	private static final String DEFAULT_ROW_BUFFER = "20";
	private static final String OUTPUT_FILE = "outputFile";
	private static final String USE_BUFFER = "useBuffer";
	private static final String OUTPUT_DESCRIPTION = "output.description";
	private static final String OUTPUT_PRICE = "output.price";
	private static final String OUTPUT_SAPAK = "ouput.sapak";
	private static final String OUTPUT_YATZRAN = "output.yatzran";
	private static final String OUTPUT_MAKAT = "output.makat";
	private static final String EXCEL_PRICE_MULTIPLIER = "excel.price.multiplier";
	private static final String EXCEL_SHEMMUZAR = "excel.shemmuzar";
	private static final String EXCEL_MEZUMAN = "excel.mezuman";
	private static final String EXCEL_YATZRAN = "excel.yatzran";
	private static final String BUFFER_ROWS = "bufferRows";
	private static final String EXCEL_FILE = "excelFile";
	private static final String DEFAULT_FILENAME = "merger.properties";
	
	private String filename;
	private Properties properties;
	private boolean valid;
	
	public MergerProperties()
	{
		this(DEFAULT_FILENAME);
	}
	
	public MergerProperties(String filename)
	{
		super();
		this.filename = filename;
		properties = new Properties();
	}
	
	public boolean load() throws IOException
	{
		String formatMissing  = "Property [%s] missing";
		PropertiesValidationEvent event = PropertiesValidationEvent.getEvent();
		valid = true;
		try(InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename),"UTF-8"))
		{
			properties.load(reader);
			String excelFile = properties.getProperty(EXCEL_FILE,null);
			if (excelFile == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "excelFile"));
				valid = false;
			}
			else
			{
				File file = new File(excelFile);
				if (!file.exists()) {
					event.firePropertyInvalid("excelFile", "Excel file " + file.getAbsolutePath()  + " missing");
					valid = false;
				}
			}
			String makatColumnName = properties.getProperty(EXCEL_MAKAT, null);
			if (makatColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "makatColumnName"));
				valid = false;
			}			
			String yatzranColumnName = properties.getProperty(EXCEL_YATZRAN,null);
			if (yatzranColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "yatzranColumnName"));
				valid = false;
			}			
			String mezumanColumnName = properties.getProperty(EXCEL_MEZUMAN, null);
			if (mezumanColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "mezumanColumnName"));
				valid = false;
			}			
			String shemmuzarColumnName = properties.getProperty(EXCEL_SHEMMUZAR,null);
			if (shemmuzarColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "shemmuzarColumnName"));
				valid = false;
			}			
			String outputFile = properties.getProperty(OUTPUT_FILE, null);
			if (outputFile == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "outputFile"));
				valid = false;
			}
			String outputMakatColumnName = properties.getProperty(OUTPUT_MAKAT, null);
			if (outputMakatColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "outputMakatColumnName"));
				valid = false;
			}
			String outputYatzranColumnName = properties.getProperty(OUTPUT_YATZRAN,null);
			if (outputYatzranColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "outputYatzranColumnName"));
				valid = false;
			}
			String outputSapakColumnName = properties.getProperty(OUTPUT_SAPAK, null);
			if (outputSapakColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "outputSapakColumnName"));
				valid = false;
			}
			String outputPriceColumnName = properties.getProperty(OUTPUT_PRICE,null);
			if (outputPriceColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "outputPriceColumnName"));
				valid = false;
			}
			String outputDescriptionColumnName = properties.getProperty(OUTPUT_DESCRIPTION, null);
			if (outputDescriptionColumnName == null)
			{
				event.firePropertyInvalid(excelFile, String.format(formatMissing, "outputDescriptionColumnName"));
				valid = false;
			}
		}
		if (valid)
		{
			event.firePropertiesValidatedSuccessfully();
		}
		return valid;
	}
	
	public String getExcelFile()
	{
		return properties.getProperty(EXCEL_FILE);
	}
	
	public Integer getRowsBuffer()
	{
		return new Integer(properties.getProperty(BUFFER_ROWS,DEFAULT_ROW_BUFFER));
	}
	public Boolean useBuffer()
	{
		String property = properties.getProperty(USE_BUFFER, "false");
		if (property.equals("yes") || property.equals("1"))
		{
			property = "true";
		}
		return new Boolean(property);
	}
	public String getExcelMakat()
	{
		return properties.getProperty(EXCEL_MAKAT);
	}
	public String getExcelYatzran()
	{
		return properties.getProperty(EXCEL_YATZRAN);
	}
	public String getExcelMezuman()
	{
		return properties.getProperty(EXCEL_MEZUMAN);
	}
	public String getExcelShemMuzar()
	{
		return properties.getProperty(EXCEL_SHEMMUZAR);
	}
	public Double getExcelPriceMultiplier()
	{
		return new Double(properties.getProperty(EXCEL_PRICE_MULTIPLIER, DEFAULT_MULTIPLIER));
	}
	public String getOutputFile()
	{
		return properties.getProperty(OUTPUT_FILE);
	}
	public String getOutputDescription()
	{
		return properties.getProperty(OUTPUT_DESCRIPTION);
	}
	public String getOutputSapak()
	{
		return properties.getProperty(OUTPUT_SAPAK);
	}
	public String getOutputYatzran()
	{
		return properties.getProperty(OUTPUT_YATZRAN);
	}
	public String getOutputMakat()
	{
		return properties.getProperty(OUTPUT_MAKAT);
	}
	public String getOutputPrice()
	{
		return properties.getProperty(OUTPUT_PRICE);
	}	
	public boolean isValid()
	{
		return valid;
	}
}
