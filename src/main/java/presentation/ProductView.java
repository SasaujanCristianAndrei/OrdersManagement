package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView extends JFrame {

    private JTextField idProductText;
    private JTextField priceText;
    private JTextField nameProductText;
    private JTextField stockText;


    private JButton addProductButton;
    private JButton deleteProductButton;
    private JButton editProductButton;
    private JButton backProductButton;
    private JButton viewProductButton;

    //private JFrame frame;

    public ProductView() {
        //frame = new JFrame();
        this.getContentPane().setBackground(new Color(255, 250, 240));
        this.setBounds(100, 100, 1000, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titluLabel = new JLabel("Product operations");
        titluLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titluLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titluLabel.setBounds(399, 11, 171, 27);
        this.getContentPane().add(titluLabel);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(36, 86, 19, 14);
        this.getContentPane().add(lblNewLabel);

        idProductText = new JTextField();
        idProductText.setBounds(55, 84, 181, 20);
        this.getContentPane().add(idProductText);
        idProductText.setColumns(10);

        JLabel lblName = new JLabel("NAME");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(10, 113, 50, 14);
        this.getContentPane().add(lblName);

        priceText = new JTextField();
        priceText.setColumns(10);
        priceText.setBounds(55, 140, 182, 20);
        this.getContentPane().add(priceText);

        JLabel lblAge = new JLabel("Price");
        lblAge.setHorizontalAlignment(SwingConstants.CENTER);
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAge.setBounds(10, 141, 50, 14);
        this.getContentPane().add(lblAge);

        nameProductText = new JTextField();
        nameProductText.setColumns(10);
        nameProductText.setBounds(55, 110, 182, 20);
        this.getContentPane().add(nameProductText);

        stockText = new JTextField();
        stockText.setColumns(10);
        stockText.setBounds(55, 169, 182, 20);
        this.getContentPane().add(stockText);

        JLabel lblEmail = new JLabel("Stock");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(11, 171, 50, 14);
        this.getContentPane().add(lblEmail);

        addProductButton = new JButton("Add product");
        addProductButton.setBounds(21, 200, 110, 23);
        this.getContentPane().add(addProductButton);

        editProductButton = new JButton("Edit product");
        editProductButton.setBounds(141, 200, 120, 23);
        this.getContentPane().add(editProductButton);

        deleteProductButton = new JButton("Delete product");
        deleteProductButton.setBounds(21, 234, 110, 23);
        this.getContentPane().add(deleteProductButton);

        viewProductButton = new JButton("View products");
        viewProductButton.setBounds(141, 234, 120, 23);
        this.getContentPane().add(viewProductButton);

        backProductButton = new JButton("Back");
        backProductButton.setBounds(10, 477, 89, 23);
        this.getContentPane().add(backProductButton);
        this.setVisible(false);
    }

    /**
     * @return int
     */
    public int getIdProductText() {
        if (idProductText.getText().equals(""))
        {
            return -1;
        }
        return Integer.parseInt(idProductText.getText());

    }

    /**
     * @return int
     */
    public int getPriceText() {
        if (priceText.getText().equals(""))
        {
            return -1;
        }
        return Integer.parseInt(priceText.getText());
    }

    /**
     * @return String
     */
    public String getNameProductText() {
        if (nameProductText.getText().equals(""))
        {
            return null;
        }
        return nameProductText.getText();
    }

    /**
     * @return int
     */
    public int getStockText() {

        if (stockText.getText().equals("")) {
            return -1;
        }
        return Integer.parseInt(stockText.getText());
    }

    /**
     * @return boolean
     */
    public boolean checkForEmpty() {
        if (stockText.getText().equals("")) {
            return true;
        }
        if (priceText.getText().equals("")) {
            return true;
        }
        if (nameProductText.getText().equals("")) {
            return true;
        }
        if (idProductText.getText().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * @param action ActionListener
     */
    public void backProductListener(ActionListener action) {
        backProductButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void viewProductListener(ActionListener action) {
        viewProductButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void deleteProductListener(ActionListener action) {
        deleteProductButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void editProductListener(ActionListener action) {
        editProductButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void addProductListener(ActionListener action) {
        addProductButton.addActionListener(action);
    }

    /**
     * @param message String
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void refresh() {
        idProductText.setText(null);
        nameProductText.setText(null);
        priceText.setText(null);
        stockText.setText(null);
    }
}
