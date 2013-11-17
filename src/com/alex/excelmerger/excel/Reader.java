package com.alex.excelmerger.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.alex.excelmerger.model.ExcelModel;

public class Reader
{
	private String excelFilename;
	private Integer rowsBuffer;
	private String makatColumnName;
	private String yatzranColumnName;
	private String mezumanColumnName;
	private String shemmuzarColumnName;
	private Integer firstRow;
	private Double priceMultiplier;
	private List<String>invalidColumns;
	
	private Workbook workbook;
	
	public boolean findColumns(Sheet sheet)
	{
		invalidColumns = new ArrayList<>();
		if (makatColumnName == null || yatzranColumnName == null || mezumanColumnName == null || shemmuzarColumnName == null)
		{
			return false;
		}
		int matches = 4;
		Cell makatCell = sheet.findCell(makatColumnName);
		if (makatCell != null)
		{
			matches--;
			ExcelModel.idColumn = makatCell.getColumn();
			firstRow = makatCell.getRow() + 1;
		}
		else{
			invalidColumns.add(makatColumnName);
		}
		Cell yatzranCell = sheet.findCell(yatzranColumnName);
		if (yatzranCell != null)
		{
			matches--;
			ExcelModel.manufacturerColumn = yatzranCell.getColumn();
		}
		else{
			invalidColumns.add(yatzranColumnName);
		}
		Cell priceCell = sheet.findCell(mezumanColumnName);
		if (priceCell != null)
		{
			matches--;
			ExcelModel.priceColumn = priceCell.getColumn();
		}
		else{
			invalidColumns.add(mezumanColumnName);
		}
		Cell descCell = sheet.findCell(shemmuzarColumnName);
		if (descCell != null)
		{
			matches--;
			ExcelModel.descriptionColumn = descCell.getColumn();
		}
		else{
			invalidColumns.add(shemmuzarColumnName);
		}
		return matches == 0;
	}
	
	public Integer readSheet(Sheet sheet,Integer startRow,ExcelModel [] rowsModel)
	{
		int i;
		for (i = 0; i < rowsModel.length; i++)
		{
			rowsModel[i] = new ExcelModel(sheet, Math.min(i + startRow, sheet.getRows() - 1));
		}
		return i;
	}
	
	public List<ExcelModel> readSheet(Sheet sheet)
	{
		
		ArrayList<ExcelModel>models = new ArrayList<>();
		Map<Integer, Cell[]>sheetData = new HashMap<>();
		Integer column;
		column = ExcelModel.idColumn;
		sheetData.put(column, sheet.getColumn(column));
		column = ExcelModel.manufacturerColumn;
		sheetData.put(column, sheet.getColumn(column));
		column = ExcelModel.priceColumn;
		sheetData.put(column, sheet.getColumn(column));
		column = ExcelModel.descriptionColumn;
		sheetData.put(column, sheet.getColumn(column));
		for (int i = 0; i < sheet.getRows(); i++)
		{
			ExcelModel model = new ExcelModel(sheetData,i);
//			ExcelModel model = new ExcelModel(sheet, i);
			if (model.isValid())
			{
				models.add(model);
			}
		}
		return models;
	}
	
	public Sheet[] sheets() throws Exception
	{
		workbook = Workbook.getWorkbook(new File(excelFilename));
		return workbook.getSheets();
	}
	public void close()
	{
		workbook.close();
	}
	
	public ExcelModel[] createModel(Sheet sheet,Integer currentRow)
	{
		return new ExcelModel[Math.min(rowsBuffer, sheet.getRows() - currentRow - 1)];
	}
	
	public Integer getFirstRow()
	{
		return firstRow;
	}
	public void setExcelFilename(String excelFilename)
	{
		this.excelFilename = excelFilename;
	}
	public void setMakatColumnName(String makatColumnName)
	{
		this.makatColumnName = makatColumnName;
	}
	public void setRowsBuffer(Integer rowsBuffer)
	{
		this.rowsBuffer = rowsBuffer;
	}
	public void setYatzranColumnName(String yatzranColumnName)
	{
		this.yatzranColumnName = yatzranColumnName;
	}
	public void setMezumanColumnName(String mezumanColumnName)
	{
		this.mezumanColumnName = mezumanColumnName;
	}
	public void setShemmuzarColumnName(String shemmuzarColumnName)
	{
		this.shemmuzarColumnName = shemmuzarColumnName;
	}
	public void setPriceMultiplier(Double priceMultiplier)
	{
		this.priceMultiplier = priceMultiplier;
		ExcelModel.priceFactor = this.priceMultiplier;
	}
	public List<String> getInvalidColumns()
	{
		return invalidColumns;
	}
}
