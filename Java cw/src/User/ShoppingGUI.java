package User;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class ShoppingGUI extends ShoppingCart implements ActionListener {
    JFrame frame;
    static DefaultTableModel model;
    JTable table;
    JLabel total;
    JLabel firstDiscount;
    JLabel secondDiscount;
    JLabel finalTotal;
    JButton back;
    JButton buy;
    JButton remove;
    static HashMap<String, Integer> sales = new HashMap<>();
    static int quantity = 1;

    ShoppingGUI() {

        frame = new JFrame("_________________________________________Shopping Cart_________________________ ");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"Product", "Quantity", "Price"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 40, 400, 200);
        frame.add(scrollPane);

        ShoppingCart s1 = new ShoppingCart();
        s1.Calculatetotal(ShoppingCart.productList);

        total = new JLabel("Total     " + s1.getFirst());
        total.setBounds(278, 250, 200, 25);
        frame.add(total);

        firstDiscount = new JLabel("First purchase discount (10%)      " + s1.getDiscount3());
        firstDiscount.setBounds(140, 280, 300, 25);
        frame.add(firstDiscount);

        secondDiscount = new JLabel("Three items of same category discount (20%)      " + s1.getDiscount());
        secondDiscount.setBounds(50, 310, 350, 25);
        frame.add(secondDiscount);

        finalTotal = new JLabel("Final total      " + s1.getTotal());
        finalTotal.setBounds(250, 340, 200, 25);
        frame.add(finalTotal);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(0, 0, 100, 30);
        frame.add(back);

        buy = new JButton("Buy");
        buy.addActionListener(this);
        buy.setBounds(150, 400, 100, 30);
        frame.add(buy);

        remove = new JButton("Remove item");
        remove.addActionListener(this);
        remove.setBounds(300, 400, 180, 30);
        frame.add(remove);


        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setVisible(true);

        //resetting all the values of keys to 0 to avoid wrong quantity due to looping everytime.
        sales.replaceAll((k, v) -> 0);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                }
            }
        });


    }

    public static void populate() { //adding elements to the table

        for (int i = 0; i < ShoppingCart.productList.size(); i++) {
            String product = ShoppingCart.productList.get(i).getProduct_Name();
            double price = ShoppingCart.productList.get(i).getPrice();

            if (sales.containsKey(product)) {
                int currentQuantity = sales.get(product) + 1;
                sales.put(product, currentQuantity);
                updateTableModel(product, currentQuantity, price);

            } else {
                sales.put(product, 1);
                updateTableModel(product, 1, price);
            }
        }
    }

    //Updating the quantity of the table
    private static void updateTableModel(String product, int quantity, double price) {
        boolean updated = false;
        for (int row = 0; row < ShoppingGUI.model.getRowCount(); row++) {
            if (ShoppingGUI.model.getValueAt(row, 0).equals(product)) {
                ShoppingGUI.model.setValueAt(quantity, row, 1);
                updated = true;
                break;
            }
        }

        if (!updated) {
            ShoppingGUI.model.addRow(new Object[]{product, quantity, price});
        }
    }

    public static void refreshTableData1() {
        populate(); // Call the populate method to refresh the table data
    }

    //updating the persons sale status to false when he/she buys
    public void updateSales(int id) {
        Database db = new Database();

        try {
            Connection connection = db.dbConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE customers SET sales = 0 WHERE id = " + id;
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if ((e.getSource() == back)) {
            frame.dispose();
            Shop s2 = new Shop();
            s2.refreshTableData();
        } else if (e.getSource() == buy) {
            updateSales(Integer.parseInt(User.getClient()));
        } else if (e.getSource() == remove) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String productName = String.valueOf(table.getValueAt(selectedRow, 0));
                removeProduct(productName);

            }
        }
    }

}
