package com.sumerge.training.utilities;

public class ExcelFile {
  public   Excel_Read Excel_Test_File;
 public   Excel_Write Excel_Test_File_Write;
    public ExcelFile(String ExcelPath){
         Excel_Test_File = new Excel_Read(ExcelPath);
         Excel_Test_File_Write = new Excel_Write(ExcelPath);
    }
    public Object[][] excelData(int sheetNumber){
        int testCaseStartRow=1;
        int testCaseStartColumn=0;
        int num_of_rows = Excel_Test_File.getRowNum(sheetNumber);
        int num_of_col = Excel_Test_File.getColNum(sheetNumber);
      //  System.out.println(num_of_col);
        String excel_File_Data[][] = new String[num_of_rows
                - testCaseStartRow][num_of_col];
        for (int i = testCaseStartRow; i <= (num_of_rows-testCaseStartRow); i++) {
            for (int j = testCaseStartColumn; j < (num_of_col+testCaseStartColumn); j++) {
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





