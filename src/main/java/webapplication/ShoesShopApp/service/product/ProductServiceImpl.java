package webapplication.ShoesShopApp.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import webapplication.ShoesShopApp.model.*;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;
import webapplication.ShoesShopApp.repository.ShoppingCartRepository;
import webapplication.ShoesShopApp.repository.UserRepository;
import webapplication.ShoesShopApp.service.user.UserServiceImpl;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product, MultipartFile[] file) {
        int count = 0;
        for (MultipartFile images: file
        ) {
            if(count == 0){
                product.setPrimaryImage(images.getOriginalFilename());
            }
            if(count == 1){
                product.setSecondImage(images.getOriginalFilename());
            }
            if(count == 2){
                product.setThirdImage(images.getOriginalFilename());
            }
            if(count == 3){
                product.setFourthImage(images.getOriginalFilename());
            }
            count++;
        }

        productRepository.save(product);

    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }


    public List<Product> getAscList() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    public List<Product> getDescList() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    public List<Product> getAlphabeticallySortedList(){
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(product -> product.getProductName().toLowerCase()));
        return products;

    }


    public List<Product> getFilteredByCategory(Collection<String> listOfCategoryName){
        List<Product> productList = productRepository.findByCategoryCategoryNameIn(listOfCategoryName);
        return productList;
    }

    public List<Product> getFilteredBySize(Collection<String> listOfSizesValue){
        List<Product> productList = productRepository.findBySizesValueIn(listOfSizesValue);
        return productList;
    }

    public List<Product> getFilteredByColor(Collection<String> listOfColorName){
        List<Product> productList = productRepository.findByColorsColorNameIn(listOfColorName);
        return productList;
    }

    public  List<Product> getFilteredBySizesAndCategory(Collection<String> listOfSizesValue,
                                                                 Collection<String> listOfCategoryName){
        List<Product> productList = productRepository
                .findBySizesValueInAndCategoryCategoryNameIn(
                        listOfSizesValue,
                        listOfCategoryName);
        return productList;
}
    public  List<Product> getFilteredBySizesAndColors(Collection<String> listOfSizesValue,
                                                                 Collection<String> listOfColorName){
        List<Product> productList = productRepository
                .findBySizesValueInAndColorsColorNameIn(
                        listOfSizesValue,
                        listOfColorName);
        return productList;
    }
    public  List<Product> getFilteredByCategoryAndColors(
                                                                 Collection<String> listOfCategoryName,
                                                                 Collection<String> listOfColorName){
        List<Product> productList = productRepository
                .findByCategoryCategoryNameInAndColorsColorNameIn(
                        listOfCategoryName,
                        listOfColorName);
        return productList;
    }
    public  List<Product> getFilteredBySizesAndCategoryAndColors(Collection<String> listOfSizesValue,
                                                                 Collection<String> listOfCategoryName,
                                                                 Collection<String> listOfColorName){
        List<Product> productList = productRepository
                .findBySizesValueInAndCategoryCategoryNameInAndColorsColorNameIn(
                        listOfSizesValue,
                        listOfCategoryName,
                        listOfColorName);
        return productList;
    }


   public void editSpecificProduct(long id, Product productDto){
       Product product = getProductById(id);
       product.setProductName(productDto.getProductName());
       product.setAmount(productDto.getAmount());
       product.setPrice(productDto.getPrice());
       product.setPrimaryImage(productDto.getPrimaryImage());
       product.setSecondImage(productDto.getSecondImage());
       product.setThirdImage(productDto.getThirdImage());
       product.setFourthImage(productDto.getFourthImage());
       product.setCategory(productDto.getCategory());
       product.setSizes(productDto.getSizes());
       product.setColors(productDto.getColors());

       productRepository.save(product);

   }



}


