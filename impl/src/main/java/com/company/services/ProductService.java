package com.company.services;

import com.company.API.model.ProductDto;
import com.company.API.responseDto.ProductDtoResponse;
import com.company.ExcelParser;
import com.company.ImageAdding;
import com.company.ProductMapper;
import com.company.domain.models.Image;
import com.company.domain.models.Product;
import com.company.repositories.ImageRepository;
import com.company.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public String addProduct (ProductDto dto){
        Product product1 = null;
        Product product = null;
        Optional<Product> oProduct1 = productRepository.findByCipher(dto.getCipher());
        if (!oProduct1.isPresent()){
        //if (productRepository.findByCipher(dto.getCipher()) == null){
            if (dto.getProduct() != null){
                Optional<Product> oProduct = productRepository.findByCipher(dto.getProduct());
                if (oProduct.isPresent()){
                    Product pr = productRepository.findByCipher(dto.getProduct()).get();
                    product = productRepository.save(Product.builder()
                            .name(pr.getName())
                            .cipher(pr.getCipher())
                            .route(pr.getRoute())
                            .type(pr.getType())
                            .build());
                }
            }
            product1 = productRepository
                    .save( Product.builder()
                            .name(dto.getName())
                            .cipher(dto.getCipher())
                            .route(dto.getRoute())
                            .type(Product.Type.valueOf(dto.getType()))
                            .mainProduct(product)
                            .build());
        }
        else {
            Product pr = oProduct1.get();
            if (dto.getProduct().isEmpty() || !dto.getProduct().equals(pr.getMainProduct().getCipher())){
                product = productRepository.getProductByCipher(dto.getProduct());
                product1 = productRepository
                        .save( Product.builder()
                                .name(dto.getName())
                                .cipher(dto.getCipher())
                                .route(dto.getRoute())
                                .type(Product.Type.valueOf(dto.getType()))
                                .mainProduct(product)
                                .build());
            }
        }
        return product1.getCipher();
    }

    public void addProductList (MultipartFile multipartFile){//для теста
        List<Product> list = ExcelParser.excelParsing(multipartFile);
        for (Product product : list){
            Optional<Product> oProduct = productRepository.findByCipher(product.getCipher());
            if (!oProduct.isPresent()){
                productRepository.save(product);
            }
        }
    }

    public ProductDtoResponse getProduct (String cipher){
        Product product = productRepository.findByCipher(cipher).get();
        ProductMapper productMapper = new ProductMapper();
        return productMapper.toDto(product);
    }

    public List<ProductDtoResponse> getProductList (){
        List<ProductDtoResponse> list = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();
        for (Product product : productRepository.findAll()){
            list.add(productMapper.toDto(product));
        }
        return list;
    }

    public List<ProductDtoResponse> getProductListByCipher (String value){
        List<ProductDtoResponse> list = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();
        for (Product product : productRepository.findAll()){
            if (product.getCipher().contains(value)){
                list.add(productMapper.toDto(product));
            }
        }
        return list;
    }

    public List<ProductDtoResponse> getProductListByName (String value){
        List<ProductDtoResponse> list = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();
        for (Product product : productRepository.findAll()){
            if (product.getName().contains(value)){
                list.add(productMapper.toDto(product));
            }
        }
        return list;
    }

    public List<ProductDtoResponse> getProductListByRoute (String value){
        List<ProductDtoResponse> list = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();
        for (Product product : productRepository.findAll()){
            if (product.getRoute().contains(value)){
                list.add(productMapper.toDto(product));
            }
        }
        return list;
    }

    public List<ProductDtoResponse> getProductListByType (String value){
        List<ProductDtoResponse> list = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();
        for (Product product : productRepository.findAll()){
            if (product.getType().getType().equals(value)){
                list.add(productMapper.toDto(product));
            }
        }
        return list;
    }

    public List<ProductDtoResponse> getProductListByMainProduct (String MP){
        List<ProductDtoResponse> list = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();
        for (Product product : productRepository.findAll()){
            if (product.getMainProduct() != null){
                if (product.getMainProduct().getCipher().equals(MP)){
                    list.add(productMapper.toDto(product));
                }
            }
        }
        return list;
    }

    public List<String> getMainProductsList (){
        Set<String> set = new HashSet<>();
        for (Product product : productRepository.findAll()){
            if (product.getMainProduct() != null)
            set.add(product.getMainProduct().getCipher());
        }
        List<String>list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    public List<ProductDtoResponse> getProductListBySearch(String name, String cipher, String type, String route, String mp){
        List<Product> list = new ArrayList<>();
        List<ProductDtoResponse> finalList = new ArrayList<>();
        ProductMapper productMapper = new ProductMapper();

        if (!name.equals("")){
            for (Product product : productRepository.findAll()){
                if (product.getName().contains(name)){
                    list.add(product);
                }
            }
        }
        if (!cipher.equals("")){
            if (!list.isEmpty()){
                Iterator<Product>iterator = list.iterator();
                while (iterator.hasNext()){
                    if (!iterator.next().getCipher().contains(cipher) || iterator.next()!=null){
                        iterator.remove();
                    }
                }
            }
            else {
                for (Product product : productRepository.findAll()){
                    if (product.getCipher().contains(cipher) || product.getCipher()!=null){
                        list.add(product);
                    }
                }
            }
        }
        if (!type.equals("Выберите тип")){
            if (!list.isEmpty()){
                Iterator<Product>iterator = list.iterator();
                while (iterator.hasNext()){
                    if (!iterator.next().getType().getType().contains(type)){
                        iterator.remove();
                    }
                }
            }
            else {
                for (Product product : productRepository.findAll()){
                    if (product.getType().getType().contains(type)){
                        list.add(product);
                    }
                }
            }
        }
        if (!route.equals("")){
            if (!list.isEmpty()){
                Iterator<Product>iterator = list.iterator();
                while (iterator.hasNext()){
                    if (!iterator.next().getRoute().contains(route)){
                        iterator.remove();
                    }
                }
            }
            else {
                for (Product product : productRepository.findAll()){
                    if (product.getRoute().contains(route)){
                        list.add(product);
                    }
                }
            }
        }
        if (!mp.equals("Выберите узел")){

            if (!list.isEmpty()){
                Iterator<Product>iterator = list.iterator();
                while (iterator.hasNext()){
                    Product product = iterator.next();
                    if (product.getMainProduct() != null){
                        String mPr = product.getMainProduct().getCipher();
                        if (!mPr.equals(mp)){
                            iterator.remove();
                        }
                    }
                }
            }
            else {
                for (Product product : productRepository.findAll()){
                    if (product.getMainProduct() != null){
                        if (product.getMainProduct().getCipher().equals(mp)){
                            list.add(product);
                        }
                    }
                }
            }
            Set<Product> productSet = new HashSet(list);
            list = new ArrayList<>(productSet);
        }
        for (Product product : list){
            finalList.add(productMapper.toDto(product));
        }

        return finalList;
    }

    public void deleteProduct (String cipher){
        Product product = productRepository.findByCipher(cipher).get();
        productRepository.delete(product);
    }

    public void deleteAll (){
        productRepository.deleteAll();
    }

    public void putImage(String cipher, MultipartFile multipartFile) throws IOException {
        Product prod = productRepository.getProductByCipher(cipher);
        byte[] encodeBase64 = Base64.getEncoder().encode(multipartFile.getBytes());
        String base64Encoded = new String(encodeBase64, StandardCharsets.UTF_8);
        Image image = new Image();
        image.setProduct(prod);
        image.setBiteCode(base64Encoded);
        Image saveImage = imageRepository.save(image);
        prod.getImages().add(saveImage);
        productRepository.save(prod);
    }

    public void deleteImage (String cipher, Long imageId){
        Product product = productRepository.getProductByCipher(cipher);
        Iterator <Image> iterator = product.getImages().iterator();
        while (iterator.hasNext()){
            Image image = iterator.next();
            if (image.getId().equals(imageId)){
                iterator.remove();
            }
        }
        Image image = imageRepository.getReferenceById(imageId);
        Long id = image.getId();
        String imageCode = image.getBiteCode();
        imageRepository.deleteById(id);
    }
}
