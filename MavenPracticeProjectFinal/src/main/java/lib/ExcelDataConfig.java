package lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	private XSSFWorkbook wb12;
	
	public ExcelDataConfig(String excelpath)
	{
		try {
			File src = new File(excelpath);
			FileInputStream fis = new FileInputStream(src);
			 wb = new XSSFWorkbook(fis);
			 
			 
		
			 						
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		
		}	
	}
	
	
	public String getData(int SheetNumber, int row, int col)
	{
		sheet1 = wb.getSheetAt(0);
		String data = sheet1.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	

	
	public int getrowcount(int sheetindex)
	{
		int row = wb.getSheetAt(sheetindex).getLastRowNum();	    
		row = row + 1;
		return row;
	}
	
	public void createresultcell(String result,int row, int col) throws IOException
	{
		File src = new File("C:\\Users\\user\\eclipse-workspace\\MavenPracticeProjectFinal\\TestData\\LoginGujPharmcoun.xlsx");
        FileInputStream fis = new FileInputStream(src);
        wb12 = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = wb.getSheetAt(0);        
       sheet1.getRow(row).createCell(col).setCellValue(result);    
       FileOutputStream fout = new FileOutputStream(src);
      wb12.write(fout);	
	}
	

}
