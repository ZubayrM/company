package com.company;

import com.company.domain.models.Product;
import com.company.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.company.ExcelParser.excelParsing;

@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class,args);
//        List<Product> products = excelParsing("C:\\Users\\saburlaev\\Desktop\\УВКУ-50У.8750-10.xls");
//
//        for (Product product : products){
//            System.out.println(product.toString());
//        }
    }
}
