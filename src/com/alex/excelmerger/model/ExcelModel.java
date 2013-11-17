package com.alex.excelmerger.model;

import java.util.Map;

import com.alex.excelmerger.common.Utilities;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;
import jxl.Sheet;

public class ExcelModel extends Model
{
	
	public static Double priceFactor;
	public static Integer idColumn;
	public static Integer manufacturerColumn;
	public static Integer priceColumn;
	public static Integer descriptionColumn;
	
	private boolean valid = false;
	
	public ExcelModel(Sheet sheet,Integer row)
	{
		Cell idCell = sheet.getCell(idColumn,row);
		Cell manuCell = sheet.getCell(manufacturerColumn,row);
		Cell priceCell  = sheet.getCell(priceColumn,row);
		Cell descCell = sheet.getCell(descriptionColumn,row);
		set(idCell, manuCell, priceCell, descCell);
	}

	public ExcelModel(Map<Integer, Cell[]>sheetData,Integer row)
	{
		Cell idCell =  read(sheetData.get(idColumn), row);
		Cell manuCell = read(sheetData.get(manufacturerColumn),row);
		Cell priceCell  = read(sheetData.get(priceColumn),row);
		Cell descCell = read(sheetData.get(descriptionColumn),row);
		set(idCell, manuCell, priceCell, descCell);
	}
	
	private Cell read(Cell[] cells,Integer index)
	{
		return index < cells.length ? cells[index]: null;
	}
	
	private void set(Cell idCell,Cell manuCell,Cell priceCell,Cell descCell)
	{
		valid = true;
		if (idCell != null)
		{
			id = idCell.getContents().trim();
		}
		if (manuCell != null)
		{
			manufacturer = manuCell.getContents().trim();
		}
		if (priceCell != null && priceCell.getType() == CellType.NUMBER)
		{
			NumberCell cell = (NumberCell)priceCell;
			price = cell.getValue() * priceFactor;
		}
		if (descCell != null)
		{
			description = descCell.getContents().trim();
		}
		valid = validate();
	}
	
	private boolean validate()
	{
		return Utilities.validateStrings(id,manufacturer,description) && price != null;
	}
	public boolean isValid()
	{
		return valid;
	}
}
