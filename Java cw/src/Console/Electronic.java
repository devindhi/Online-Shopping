package Console;

public class Electronic extends Product {
    private String brand;
    private int warrantyPeriod;

    //constructor
    public Electronic(String product_ID, String product_Name, int no_of_products,String brand,int warrantyPeriod,double price) {
        super(product_ID, product_Name, no_of_products,price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override  //getting the name of the class
    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return
                "brand: " + brand + ' ' +
                        " warrantyPeriod: " + warrantyPeriod
                ;
    }
}
