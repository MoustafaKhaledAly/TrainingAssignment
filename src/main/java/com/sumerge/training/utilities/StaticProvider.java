package com.sumerge.training.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class StaticProvider {
    public static int CartValidationSheet = 0;
    public static int LogInSheet_InValid = 1;
    public static int LogInSheet_Valid = 0;
    public static int signUpSheet_Valid = 0;
    @DataProvider(name = "SignUpSheetValid")
    public Object[][] getExcel4() throws IOException {
         String SignUpSheetExcelPath = System.getProperty("user.dir")
                + "\\src\\main\\resources\\SignUpSheet.xlsx";
        ExcelFile SignUpSheetExcel= new ExcelFile(SignUpSheetExcelPath);
        Object[][] TestData4=  SignUpSheetExcel.excelData(signUpSheet_Valid);
        return TestData4;

    }
    @DataProvider(name = "CartValidation")
    public Object[][] getExcel1() throws IOException {
        String CartValidationSheetExcelPath = System.getProperty("user.dir")
                + "\\src\\main\\resources\\CartValidationSheet.xlsx";
        ExcelFile CartValidationSheetExcel= new ExcelFile(CartValidationSheetExcelPath);
        Object[][] TestData1=  CartValidationSheetExcel.excelData(CartValidationSheet);
        return TestData1;

    }
    @DataProvider(name = "LogInSheetInValid")
    public Object[][] getExcel2() throws IOException {
       String LogInSheetExcelPath = System.getProperty("user.dir")
                + "\\src\\main\\resources\\LogInSheet.xlsx";
        ExcelFile LogInSheetExcel= new ExcelFile(LogInSheetExcelPath);
        Object[][] TestData2=  LogInSheetExcel.excelData(LogInSheet_InValid);
        return TestData2;

    }
    @DataProvider(name = "LogInSheetValid")
    public Object[][] getExcel3() throws IOException {
         String LogInSheetExcelPath = System.getProperty("user.dir")
                + "\\src\\main\\resources\\LogInSheet.xlsx";
        ExcelFile LogInSheetExcel= new ExcelFile(LogInSheetExcelPath);
        Object[][] TestData3=  LogInSheetExcel.excelData(LogInSheet_Valid);
        return TestData3;

    }

}
