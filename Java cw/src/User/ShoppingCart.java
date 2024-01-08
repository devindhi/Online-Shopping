package User;

import Console.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    static List<Product> productList = new ArrayList<>();

    private double total;
    private double discount;
    private double discount3;
    private int clothes;
    private int electronics;
    private double first;

    public ShoppingCart(){
        //productList = new ArrayList<>();
        total =0;
        discount = 0;
        discount3 = 0;
        clothes=0;
        electronics=0;
        first= 0;


    }


    public double getFirst() {
        return first;
    }

    public double getDiscount3() {
        return discount3;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void view(){
        System.out.println(productList);
    }

    //add product to list
    public void addProduct(Product product){
        productList.add(product);
    }


    //checking if this is the customers first sale
    public boolean checking_Sales(int clientId) {
        boolean salesStatus = false;
        try {
            Database db = new Database();
            Connection connection = db.dbConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT sales FROM customers WHERE id = " + clientId;
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int sales = resultSet.getInt("sales");
                salesStatus = (sales == 1); //if sales == 1 then salesStatus is true
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesStatus;
    }


    //calculate the total
    public double Calculatetotal(List<Product> productList){
        for (Product product : productList) {//getting the total
            first+=product.getPrice();
            total += product.getPrice();
        }
        for (Product product: productList){
            if(product.getType().equals("Clothing")){  //checking the types of each product
                clothes+=1;
            }
            else {
                electronics+=1;
            }
        }
        boolean clothesOrElectronicsDiscount = clothes >= 3 || electronics >= 3; //checking condition to give the 20% discount
        boolean salesStatus = checking_Sales(Integer.parseInt(User.getClient()));//checking condition to give the 10% discount


        if (clothesOrElectronicsDiscount && salesStatus) { //applying discount if conditions are true
            discount = total * 0.2;
            discount3 = total * 0.1;
            total = total- (discount + discount3);

        } else if (clothesOrElectronicsDiscount) {
            discount = total * 0.2;
            total -= discount;

        } else if (salesStatus) {
            discount3 = total * 0.1;
            total -= discount3;

        }
        return total;
    }
    //remove product from list
    public void removeProduct(String product){
        for(Product product2: productList){
            if(product2.getProduct_Name().equals(product)){
                productList.remove(product2);
                break;

            }
        }
        //Updating the quantity of the table when a product get removed
        for (int row = 0; row < ShoppingGUI.model.getRowCount(); row++) {
            Object quantityObj = ShoppingGUI.model.getValueAt(row, 1);
            if (quantityObj instanceof Integer) {  //checks if retrieved value is integer
                int quantity = (int) quantityObj;
                quantity--; // Decrement the quantity
                ShoppingGUI.model.setValueAt(quantity, row, 1); //setting up new quantity
                break;
            }

        }

        Calculatetotal(productList);
    }





}
