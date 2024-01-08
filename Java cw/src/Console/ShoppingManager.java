package Console;

import java.util.List;

public interface ShoppingManager {
    public abstract String addProduct(Product type);
    public abstract void printProduct(List<Product> productsList);
    public abstract boolean runMenu();
    public abstract String deleteProduct(String productID,List<Product> productsList);
    public abstract void saveProductsToFile(List<Product> productsList);
}
