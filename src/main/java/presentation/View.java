package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    //private JFrame this;
    private JButton clientButton;
    private JButton productOperationsButton;
    private JButton ordersButton;

    public View() {
        //frame = new JFrame();
        this.getContentPane().setBackground(new Color(255, 250, 240));
        this.setBounds(100, 100, 1000, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titluLabel = new JLabel("Orders Management");
        titluLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titluLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titluLabel.setBounds(399, 11, 171, 27);
        this.getContentPane().add(titluLabel);

        clientButton = new JButton("Client operations");
        clientButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        clientButton.setBounds(399, 157, 159, 43);
        this.getContentPane().add(clientButton);

        productOperationsButton = new JButton("Product operations");
        productOperationsButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        productOperationsButton.setBounds(399, 252, 159, 43);
        this.getContentPane().add(productOperationsButton);

        ordersButton = new JButton("Orders");
        ordersButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ordersButton.setBounds(399, 355, 159, 43);
        this.getContentPane().add(ordersButton);
        this.setVisible(true);
    }

    /**
     * @param action ActionListener
     */
    public void clientButtonListener(ActionListener action) {
        clientButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void ordersButtonListener(ActionListener action) {
        ordersButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void productOperationsButtonListener(ActionListener action) {
        productOperationsButton.addActionListener(action);
    }
}
