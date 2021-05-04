package com.sumerge.training.utilities;

public class ExcelFile {
  public   Excel_Read Excel_Test_File;
 public   Excel_Write Excel_Test_File_Write;
    public ExcelFile(String ExcelPath){
         Excel_Test_File = new Excel_Read(ExcelPath);
         Excel_Test_File_Write = new Excel_Write(ExcelPath);
    }
    public Object[][] excelData(int testCaseStartRow,int testCaseStartColumn,int numOfVariables,int sheetNumber){
        int num_of_rows = Excel_Test_File.getRowNum(sheetNumber);

        String excel_File_Data[][] = new String[num_of_rows
                - testCaseStartRow][numOfVariables];
        for (int i = testCaseStartRow; i <= (num_of_rows-testCaseStartRow); i++) {
            for (int j = testCaseStartColumn; j < (numOfVariables+testCaseStartColumn); j++) {
                excel_File_Data[i - testCaseStartRow][j
                        - testCaseStartColumn] = Excel_Test_File.fetchData(
                        sheetNumber, i, j);
            }
        }

        return excel_File_Data;

    }
    public String getCellFromExcel(int RowNum,int ColNum,int sheetNum){
        return Excel_Test_File.fetchData(sheetNum, RowNum, ColNum);

    }
}





