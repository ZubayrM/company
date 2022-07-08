package com.company;

import com.company.domain.models.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class ExcelParser {
    public static List<Product> excelParsing (String filePath){
        List<Product> productList = new ArrayList<Product>();

        InputStream inputStream = null;
        HSSFWorkbook workbook = null;
        try {
            inputStream = new FileInputStream(filePath);
            workbook = new HSSFWorkbook(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(1);

        for (int i = 8; i < 860; i++){
            Product product = new Product();
            product.setCipher(sheet.getRow(i).getCell(1).getRichStringCellValue().toString());
            product.setName(sheet.getRow(i).getCell(3).getRichStringCellValue().toString());
            product.setType(typeParsing(sheet.getRow(i).getCell(2).getRichStringCellValue().toString()));
            product.setRoute(routeParsing(sheet.getRow(i).getCell(4)));
            productList.add(product);
        }
        return productList;
    }

    //парсинг маршрута детали
    private static String routeParsing(Cell cell){
        int cellType = cell.getCellType();
        String res = "";
        switch (cellType){
            case Cell.CELL_TYPE_STRING:
                res = cell.getRichStringCellValue().toString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                res = Double.toString(cell.getNumericCellValue());
                break;
        }
        return res;
    }

    //парсинг типа детали
    private static Product.Type typeParsing (String cellValue){
        if (cellValue.equals("Сб")) {
            return Product.Type.ASSEMBLY;
        }
        else if (cellValue.equals("Д")) {
            return Product.Type.DETAIL;
        }
        else if (cellValue.equals("Н")){
            return Product.Type.NORMALIZED;
        }
        else if (cellValue.equals("ПКИ")){
            return Product.Type.PURCHASED;
        }
        else if (cellValue.equals("М")){
            return Product.Type.MATERIAL;
        }
        return null;
    }
}
