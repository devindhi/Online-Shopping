package Console;
import User.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    public static ArrayList<Product> productsList = new ArrayList<>();
    int productNo ;
    public WestminsterShoppingManager (int productNo) {
        this.productNo=productNo;
    }

    //Saving the data into a file
    @Override
    public void saveProductsToFile(List<Product> productsList) {

        String filename = "online.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true))) { //appending to the file
            for (Product product : productsList) {
                String line = product.getProduct_ID() + "," + product.getProduct_Name() + "," +
                        product.getNo_of_products() + "," + product.getType() + "\n";
                writer.write(line);
            }
            System.out.println("Products saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    //Deleting a product
    @Override
    public String deleteProduct(String productID,List<Product> productsList) {
        String result ="not removed";
        for(int i=0; i<productsList.size();i++){
            if(productsList.get(i).getProduct_ID().equals(productID)){
                System.out.println("Name:"+productsList.get(i).getProduct_Name()+"\nNumber of items:"+productsList.get(i).getNo_of_products());
                productNo = productNo - productsList.get(i).getNo_of_products();
                System.out.println("Stock remaining:"+productNo);
                productsList.remove(i);
                result = "Item removed";
                break;
            }
        }
        return result;
    }

    //Adding product
    @Override
    public String addProduct(Product type) {
        String result = "not added";
        if(productNo < 51){     //checking if there is space
            productsList.add(type);
            result = "added";
        }
        else {
            System.out.println("No more space");
        }
        return result;
    }

    //Printing the products that are in the store
    @Override
    public void printProduct(List<Product> productsList) {
        for(int i=0; i<productsList.size();i++){
            if (productsList.get(i).getType().equals("Console.Clothes")){
                System.out.println("Clothe name: "+productsList.get(i).getProduct_Name()+"\nNumber of products: "+productsList.get(i).getNo_of_products()+"\n"+productsList.get(i).toString()+"\n");
            }
            else{
                System.out.println("Console.Electronic name: "+productsList.get(i).getProduct_Name()+"\nNumber of products: "+productsList.get(i).getNo_of_products()+"\n"+productsList.get(i).toString()+"\n");
            }
        }
    }


    //The menu
    @Override
    public boolean runMenu() {
        boolean exit = false;
        System.out.println("Menu\n Enter 1 if you want to add a product\n Enter 2 if you want to delete a product\n Enter 3 if you want to view the products\n Enter 4 if you want to save the products in a file\n Enter 5 to open Customer.GUI");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1:
                String xt = s.nextLine();
                String type = "";

                //get type
                do {
                    System.out.println("Enter product type Console.Clothes or Electronics (c or e)");
                    type = s.nextLine();
                } while (!type.equals("c") && !type.equals("e"));

                    //get name
                    String productName = "";
                    do {
                        System.out.println("Enter product Name");
                        productName = s.nextLine();
                    } while (productName.isEmpty());

                    String productId = "";
                    do {
                        System.out.println("Enter productId");
                        productId = s.nextLine();
                        // Perform additional validation checks if necessary
                    } while (productId.isEmpty());

                    int no_of_items = 0;
                    boolean items = false;
                    do {
                        System.out.println("Enter number of items");
                        if (s.hasNextInt() ) {
                            no_of_items = s.nextInt();
                            if(no_of_items<=50){
                                items = true;
                            }
                            else {
                                System.out.println("The total number of items that can be added are 50");
                            }

                        } else {
                            System.out.println("Invalid input. Please enter an integer value for number of items.Or the total number of items that can be added are 50");
                            s.next(); // Clear the invalid input from the scanner buffer
                        }
                    } while (!items);

                    double price = 0;
                    boolean given = false;
                    do {
                        System.out.println("Enter the price");
                        if (s.hasNextDouble()) {
                            price = s.nextInt();
                            given = true;
                        } else {
                            System.out.println("Invalid input. Please enter a valid price.");
                            s.next(); // Clear the invalid input from the scanner buffer
                        }
                    } while (!given);


                s.nextLine();
                if (type.equals("c")) {  //If product is a cloth

                    String colour = "";
                    do {
                        System.out.println("Enter colour");
                        colour = s.nextLine();
                        // Perform additional validation checks if necessary
                    } while (colour.isEmpty());

                    int size = 0;
                    boolean validSize = false;
                    do {
                        System.out.println("Enter size");
                        if (s.hasNextInt()) {
                            size = s.nextInt();
                            validSize = true;
                        } else {
                            System.out.println("Invalid input. Please enter an integer value for size.");
                            s.next(); // Clear the invalid input from the scanner buffer
                        }
                    } while (!validSize);

                    //creating a clothing object
                    Product c1 = new Clothing(productId, productName, no_of_items, colour, size, price);

                    //adding the product
                    this.addProduct(c1);
                    productNo = productNo + c1.getNo_of_products();


                    s.nextLine(); // Consume the newline character


                }else {    //If product is an electronic
                    String brand = "";
                    do {
                        System.out.println("Enter brand");
                        brand = s.nextLine();
                    } while (brand.isEmpty());

                    int warranty = 0;
                    boolean warrantyc = false;
                    do {
                        System.out.println("Enter warranty in years");
                        if (s.hasNextInt()) {
                            warranty = s.nextInt();
                            warrantyc = true;
                        } else {
                            System.out.println("Invalid input. Please enter an integer value for warranty.");
                            s.next();
                        }
                    } while (!warrantyc);

                    s.nextLine(); // Consume the newline character
                    productNo = productNo + no_of_items;

                    //creating an electronic object
                    Product c1 = new Electronic(productId, productName, no_of_items, brand, warranty,price);

                    //adding product to the lis
                    this.addProduct(c1);
                }

                break;

                //deleting a product
            case 2:
                String productDeleted1 = s.nextLine();
                System.out.println("Enter the product Id you want to delete");
                String productDeleted = s.nextLine();
                deleteProduct(productDeleted,productsList);
                break;

            case 3://view products
                printProduct(productsList);
                break;

            case 4://save products
                saveProductsToFile(productsList);
                break;

            case 5://access the user GUI
                User.Login loginWindow = new User.Login();// Create an instance of Login
                break;

            default:
                System.out.println("Error please try again");
        }

        return exit;
    }


}
