package utilities;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
Workbook wb;
//constructor for reading excel path
public ExcelFileUtil(String ExcelPath) throws Throwable
{
	FileInputStream fi = new FileInputStream(ExcelPath);
	wb = WorkbookFactory.create(fi);
}
//count no of rows in sheet 
public int rowCount(String sheetName)
{
	return wb.getSheet(sheetName).getLastRowNum();
}
//get cell data
public String getCellData(String sheetName, int row,int column)
{
	String data = "";
	if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(celldata);
	}
	else 
	{
		data = wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
// method for writing cell data
public void setCellData(String SheetName,int row,int column,String status,String WriteExcel) throws Throwable
{
	//get sheet from wb
	Sheet ws = wb.getSheet(SheetName);
	// get row from sheet
	Row rowNum = ws.getRow(row);
	//create cell in row
	Cell cell = rowNum.createCell(column);
	//write status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("Pass"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		//Color with green
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		//Color with red
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
		
	}
	else if (status.equalsIgnoreCase("Blocked")) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		//Color with blue
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	}
	FileOutputStream fo = new FileOutputStream(WriteExcel);
	wb.write(fo);
}
public static void main (String []args) throws Throwable
{
	//create object for class
	ExcelFileUtil xl = new ExcelFileUtil("E:\\Sample\\Sample.xlsx");
	//count no of rows in sheet
	int rc = xl.rowCount("Emp");
	System.out.println("no of rows are::"+rc);
	for(int i=1;i<=rc;i++)
	{
		String fname = xl.getCellData("Emp", i, 0);
		String mname = xl.getCellData("Emp", i, 1);
		String lname = xl.getCellData("Emp", i, 2);
		String eid = xl.getCellData("Emp", i, 3);
		System.out.println(fname+"  "+mname+"   "+lname+"   "+eid);
		xl.setCellData("Emp", i, 4, "Pass", "E:/Results.xlsx");
	}

}
}






























