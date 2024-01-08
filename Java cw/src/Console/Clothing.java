package Console;

public class Clothing extends Product {

    private String color;
    private int size;


    //constructor
    public Clothing(String product_ID, String product_Name,int no_of_products,String color,int size,double price) {
        super(product_ID, product_Name, no_of_products, price);
        this.color=color;
        this.size=size;

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override //getting name of the class
    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return
                "Color: "+ color + ' ' +
                        " Size: "+ size ;
    }
}
