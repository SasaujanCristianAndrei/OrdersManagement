package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrderView extends JFrame {

    //private JFrame frame;

    private JComboBox<Integer> comboBoxClientID;
    private JComboBox<Integer> comboBoxProductID;

    private JTextField quantityText;
    private JTextField idOrderText;

    private JButton placeOrderButton;
    private JButton backOrderButton;
    private JButton viewOrderButton;
    private JButton editOrderButton;
    private JButton removeOrderButton;


    public OrderView() {
        //frame = new JFrame();
        this.getContentPane().setBackground(new Color(255, 250, 240));
        this.setBounds(100, 100, 1000, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titluLabel = new JLabel("Order operations");
        titluLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titluLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titluLabel.setBounds(399, 11, 171, 27);
        this.getContentPane().add(titluLabel);

        backOrderButton = new JButton("Back");
        backOrderButton.setBounds(10, 477, 89, 23);
        this.getContentPane().add(backOrderButton);

        comboBoxClientID = new JComboBox();
        comboBoxClientID.setBounds(61, 214, 148, 22);
        this.getContentPane().add(comboBoxClientID);

        JLabel lblNewLabel_1 = new JLabel("ClientID");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(96, 189, 74, 14);
        this.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("ProductID");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(96, 247, 74, 14);
        this.getContentPane().add(lblNewLabel_1_1);

        comboBoxProductID = new JComboBox();
        comboBoxProductID.setBounds(61, 272, 148, 22);
        this.getContentPane().add(comboBoxProductID);

        JLabel lblNewLabel_1_1_1 = new JLabel("Quantity");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1_1.setBounds(29, 321, 74, 19);
        this.getContentPane().add(lblNewLabel_1_1_1);

        quantityText = new JTextField();
        quantityText.setBounds(106, 322, 86, 20);
        this.getContentPane().add(quantityText);
        quantityText.setColumns(10);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("ID ORDER");
        lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1_1_1.setBounds(24, 152, 74, 19);
        this.getContentPane().add(lblNewLabel_1_1_1_1);

        idOrderText = new JTextField();
        idOrderText.setColumns(10);
        idOrderText.setBounds(102, 153, 86, 20);
        this.getContentPane().add(idOrderText);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(10, 364, 117, 23);
        this.getContentPane().add(placeOrderButton);

        removeOrderButton = new JButton("Remove Order");
        removeOrderButton.setBounds(132, 364, 117, 23);
        this.getContentPane().add(removeOrderButton);

        editOrderButton = new JButton("Edit Order");
        editOrderButton.setBounds(10, 398, 117, 23);
        this.getContentPane().add(editOrderButton);

        viewOrderButton = new JButton("View Orders");
        viewOrderButton.setBounds(132, 398, 117, 23);
        this.getContentPane().add(viewOrderButton);

        this.setVisible(false);
    }

    /**
     * @param idClient String[]
     */
    public void setComboBoxClientID(String[] idClient) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(idClient);
        comboBoxClientID.setModel(model);
    }

    /**
     * @param idProduct String[]
     */
    public void setComboBoxProductID(String[] idProduct) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(idProduct);
        comboBoxProductID.setModel(model);
    }

    /**
     * @return int
     */
    public int getComboBoxClientID() {
        //return Integer.parseInt(comboBoxClientID.getSelectedItem().toString());
        String aux=comboBoxClientID.getSelectedItem().toString();
        String bun="";
        for(int i=0;i<aux.length();i++)
        {
            if(aux.charAt(i)==')')
            {
                break;
            }
            else bun=bun+aux.charAt(i);
        }
        return Integer.parseInt(bun);
    }

    /**
     * @return int
     */
    public int getComboBoxProductID() {
        //return Integer.parseInt(comboBoxProductID.getSelectedItem().toString());
        String aux=comboBoxProductID.getSelectedItem().toString();
        String bun="";
        for(int i=0;i<aux.length();i++)
        {
            if(aux.charAt(i)==')')
            {
                break;
            }
            else bun=bun+aux.charAt(i);
        }
        return Integer.parseInt(bun);
    }

    /**
     * @return int
     */
    public int getQuantityText() {
        return Integer.parseInt(quantityText.getText());
    }

    /**
     * @return int
     */
    public int getIdOrderText() {
        return Integer.parseInt(idOrderText.getText());
    }

    /**
     * @param action ActionListener
     */
    public void placeOrderListener(ActionListener action) {
        placeOrderButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void viewOrderListener(ActionListener action) {
        viewOrderButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void removeOrderListener(ActionListener action) {
        removeOrderButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void editOrderListener(ActionListener action) {
        editOrderButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void backOrderListener(ActionListener action) {
        backOrderButton.addActionListener(action);
    }

    /**
     * @param message String
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void refresh() {
        quantityText.setText(null);
        idOrderText.setText(null);
    }

    public boolean checkForEmpty() {
        if (idOrderText.getText().equals("")) {
            return true;
        }
        if (quantityText.getText().equals("")) {
            return true;
        }
        return false;
    }


}
