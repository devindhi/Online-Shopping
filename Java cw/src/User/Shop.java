package User;

import Console.Product;
import Console.WestminsterShoppingManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Shop  implements ActionListener {

    JFrame frame;
    JLabel category;
    JComboBox<String> comboBox;
    JButton shopping;
    DefaultTableModel model;
    JTable table;
    JButton addShopping;
    DefaultListModel<String> l1 = new DefaultListModel<>();
    private String productIdD="";


    //Colouring the items with number of items lower than or is 3
    class CustomCellRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


            if (row < WestminsterShoppingManager.productsList.size()) {  //check if row is in list
                Product product = WestminsterShoppingManager.productsList.get(row); //gets row with the product
                int productNumber = product.getNo_of_products();

                // Check if the product_number is <= 3
                if (productNumber <= 3) {
                    comp.setBackground(Color.RED); // Set background color for rows where the value is <= 3
                } else {
                    comp.setBackground(table.getBackground()); // Reset background color to default
                }
            }
            return comp;
        }
    }
    public Shop(){

        frame = new JFrame("_________________________________________Westminster Shopping Center_____________________________ ");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        category = new JLabel("Select Product Category");
        category.setBounds(80, 45, 150, 30);
        frame.add(category);

        String[] options = {"Clothing", "Electronic", "All"};
        comboBox = new JComboBox<>(options);
        comboBox.addActionListener(this);
        comboBox.setBounds(230, 45, 200, 30);
        frame.add(comboBox);

        shopping = new JButton("Shopping Cart");
        shopping.setBounds(530,20,140,30);
        shopping.addActionListener(this);
        frame.add(shopping);

        model = new DefaultTableModel(new String[]{"Product_ID","Name", "Type", "Price(€)", "Info"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 130, 550, 240);
        frame.add(scrollPane);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 380, 700, 10);
        frame.add(separator);

        JList<String> list = new JList<>(l1);
        list.setBounds(0,385,700,252);
        frame.add(list);

        addShopping = new JButton("Add to shopping cart");
        addShopping.addActionListener(this);
        addShopping.setBounds(240, 648, 180, 30);
        frame.add(addShopping);

        frame.setSize(700, 900);
        frame.setResizable(false);
        frame.setVisible(true);

        for (int i=0;i<table.getColumnCount();i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new CustomCellRenderer());
        }

        //getting selected row
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String value = String.valueOf(table.getValueAt(selectedRow, 0));
                        displayInfo(value); //displaying the selected row information below the table

                    }
                }
            }
        });



    }

    public void displayInfo(String id) {
        l1.clear();
        for (Product product : WestminsterShoppingManager .productsList) {
            if (id.equals(product.getProduct_ID())) {
                l1.addElement("Product ID:  "+ product.getProduct_ID());
                l1.addElement("Product name:  " + product.getProduct_Name());
                l1.addElement("Type:  "+ product.getType());
                l1.addElement("Description  " + product);
                l1.addElement("No.of products:  " + product.getNo_of_products());
                l1.addElement("Price:  " + product.getPrice());
                productIdD = id;
                break;
            }

        }

    }
    private void updateTableData() {
        model.setRowCount(0);
        for (Product product : WestminsterShoppingManager.productsList) {
            model.addRow(new Object[]{
                    product.getProduct_ID(),
                    product.getProduct_Name(),
                    product.getType(),
                    product.getPrice(),
                    product.toString()
            });
        }
    }

    public void refreshTableData() {
        updateTableData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addShopping){
            for (Product product : WestminsterShoppingManager .productsList){
                if (productIdD.equals(product.getProduct_ID())){
                    ShoppingCart sc = new ShoppingCart();
                    sc.addProduct(product);
                }
            }
        }
        else if (e.getSource() == comboBox) {
            try {
                String filter = (String) comboBox.getSelectedItem();
                refreshTableData(); // Refresh the table with all data
                if (!filter.equals("All")) {
                    for (int i = model.getRowCount() - 1; i >= 0; i--) {
                        if (!model.getValueAt(i, 2).equals(filter)) {
                            model.removeRow(i); // Remove rows that don't match the filter
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex + " Items are not added");
            }
        }
        else if (e.getSource() == shopping) {
                frame.dispose();
                ShoppingGUI s1 = new ShoppingGUI();
                s1.refreshTableData1();
            }
        }

}
