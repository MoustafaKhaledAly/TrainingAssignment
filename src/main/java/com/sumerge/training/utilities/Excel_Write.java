package com.sumerge.training.utilities;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.IOException;

//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Write {
	static XSSFWorkbook workbook ;
	XSSFSheet sheet;
	String file_path;
	public Excel_Write(String Path){
		try{
			file_path=Path;
			File file=new File(Path);
			FileInputStream fs = new FileInputStream(file);
			workbook=new XSSFWorkbook(fs);
		}
		catch(Exception e){
			System.out.println(e.getMessage());

		}
	}
	public void writeData(int sheet_Num,int row,int column,String value){
		sheet =workbook.getSheetAt(sheet_Num);
		sheet.getRow(row).createCell(column).setCellValue(value);
		try{
		FileOutputStream fos = new FileOutputStream(file_path);
		workbook.write(fos);
		fos.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
			}

}
























