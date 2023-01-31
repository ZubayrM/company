package com.company;

import com.company.domain.models.Product;
import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ExcelParser {
    public static List<Product> excelParsing (MultipartFile multipartFile){
        List<Product> productList = new ArrayList<Product>();

        HSSFWorkbook workbook = null;
        try {
            File convFile = new File(multipartFile.getOriginalFilename());
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(multipartFile.getBytes());
            fos.close();

            workbook = new HSSFWorkbook(new FileInputStream(convFile));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = workbook.getSheetAt(1);
        Iterator<Row> rowIterator = sheet.iterator();

        int startField = 0;

        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.iterator();
            if (startField == 0){
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    if (cell.getRichStringCellValue().toString().equals("№п/п")){
                        startField = row.getRowNum() + 1;
                        break;
                    }
                }
            }
            else {
                if (row.getCell(0) != null){
                    if (!row.getCell(1).getStringCellValue().isEmpty()){
                        Product product = new Product();
                        product.setCipher(row.getCell(1).getRichStringCellValue().toString().trim());//присваиваем шифр
                        product.setName(row.getCell(3).getRichStringCellValue().toString().trim());//присваиваем имя
                        product.setType(typeParsing(row.getCell(2).getRichStringCellValue().toString().trim()));//присваиваем тип
                        product.setRoute(routeParsing(row.getCell(4)));//присваиваем маршрут
                        product.setImage("");
                        for (Product product1 : productList){
                            if (product1.getCipher().equals(row.getCell(6).getStringCellValue())){//присваиваем изделие-входимость
                                product.setMainProduct(product1);
                                break;
                            }
                        }
                        productList.add(product);
                    }
                }
                else
                    break;
            }
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
                res = Double.toString(cell.getNumericCellValue()).trim();
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
        else if (cellValue.equals("СН")){
            return Product.Type.ASSEMBLY_NORMAL;
        }
        return null;
    }
}
