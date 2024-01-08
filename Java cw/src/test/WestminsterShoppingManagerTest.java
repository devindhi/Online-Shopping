package test;

import Console.Product;
import Console.WestminsterShoppingManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {
    WestminsterShoppingManager w1;
     ArrayList<Product> productsList = new ArrayList<>();


    @BeforeEach //executed before each test
    void setUp() {
        w1 = new WestminsterShoppingManager(0);
        Product c1 = new Product("1", "Product1", 10, 200) {
            @Override
            public String getType() {
                return null;
            }
        };
        Product c2 = new Product("2", "Product2", 10, 200) {
            @Override
            public String getType() {
                return null;
            }
        };
        productsList.add(c1);
        productsList.add(c2);
    }

    @Test
    void saveProductsToFileTest() {

        w1.saveProductsToFile(productsList);
        File file = new File("online.txt"); //creates a new file object and refers to online file we just created
        assertTrue(file.exists());//check if the file is created

    }

    @Test
    void deleteProductTest() {

        assertEquals("Item removed",w1.deleteProduct("1",productsList));
    }

    @Test
    void addProductTest() {
        Product c3 = new Product("3", "Product3", 10, 200) {
            @Override
            public String getType() {
                return null;
            }
        };
        assertEquals("added", w1.addProduct(c3));
    }






}