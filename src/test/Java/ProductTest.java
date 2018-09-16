


import com.javaman.dao.ProductMapper;
import com.javaman.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring-mybatis.xml"})
public class ProductTest {

    @Autowired
    private ProductMapper productMapper;
      
    @Test
    public void insertAndSelect() {

        List<Product> products = new ArrayList<>();
        for (int i = 0;i < 10; i++){
            Product product = new Product();
            product.setName("chx"+i);
            product.setPrice(99+i);
            products.add(product);
        }

        productMapper.insertByBatch(products);

    }

    @Test
    public void selectByBatch() {

           int[] arr = {1,2,3,4,5};
        List<Product> products = productMapper.selectByBatch(arr);

        for (Product product : products){
            System.out.println(product.toString());
        }
    }


}