package Console;

abstract public class Product {

    private String product_ID;
    private String product_Name;
    private int no_of_products;
    private double price;

    //constructor
    public Product(String product_ID,String product_Name,int no_of_products,double price) {
        this.product_ID = product_ID;
        this.product_Name = product_Name;
        this.no_of_products = no_of_products;
        this.price = price;

    }
    public abstract String getType();

    public String getProduct_ID() {
        return product_ID;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public int getNo_of_products() {
        return no_of_products;
    }

    public void setNo_of_products(int no_of_products) {
        this.no_of_products = no_of_products;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //toString
    @Override
    public String toString() {
        return
                "product_ID='" + product_ID + '\'' +
                        ", product_Name='" + product_Name + '\'' +
                        ", no_of_products=" + no_of_products +
                        ", price=" + price ;
    }
}


