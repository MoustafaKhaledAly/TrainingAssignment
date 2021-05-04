package com.sumerge.training.utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class Excel_Read {
	static XSSFWorkbook workbook ;
	XSSFSheet sheet;
	public Excel_Read(String Path){
		try{
			File file=new File(Path);
			FileInputStream fs = new FileInputStream(file);
			workbook=new XSSFWorkbook(fs);
		}
		catch(Exception e){
			System.out.println(e.getMessage());

		}
	}
	
	public  int getColNum(int sheet_Num){
		int Col_Num=0;
		try{
		 Col_Num= 12;
		}catch(Exception e){

		}
		return Col_Num;
	}
	public  int getRowNum(int sheet_Num){
		int row_Num=0;
		try{
		 row_Num= workbook.getSheetAt(sheet_Num).getLastRowNum()+1;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return row_Num;
	}
	public String fetchData(int sheet_Num,int row,int column){
sheet =workbook.getSheetAt(sheet_Num);
String data=null;
try{
		 data=sheet.getRow(row).getCell(column).getStringCellValue();
		} catch(Exception e){
	System.out.println(e.getMessage());
		}
data = (data==null) ? "" : data;
		return data;
	}
	

}






























