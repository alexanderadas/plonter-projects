package com.alex.excelmerger.model;

import java.text.NumberFormat;

public class MergedModel extends Model
{
	private static NumberFormat numberFormat = NumberFormat.getInstance();
	static
	{
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setGroupingUsed(false);
	}
	
	
	public MergedModel(Model model)
	{
		super.id = model.id;
		super.manufacturer = model.manufacturer;
		super.price = model.price;
		super.description = model.description;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s\t%s\t%s\t%s\t%s", id,manufacturer,ExcelModel.SUPPLIER,numberFormat.format(price),description);
	}
	
	public String toHtmlTableRow()
	{
		return String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", id,manufacturer,ExcelModel.SUPPLIER,numberFormat.format(price),description);
	}
}
