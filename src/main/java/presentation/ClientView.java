package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {


    private JTextField idText;
    private JTextField ageText;
    private JTextField nameText;
    private JTextField emailText;
    private JTextField adressText;

    private JButton addClientButton;
    private JButton updateClientButton;
    private JButton deteleClientButton;
    private JButton viewClientButton;
    private JButton backClientButton;

    //private JFrame frame;

    public ClientView() {
        //frame = new JFrame();
        this.getContentPane().setBackground(new Color(255, 250, 240));
        this.setBounds(100, 100, 1000, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel titluLabel = new JLabel("Client operations");
        titluLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titluLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titluLabel.setBounds(399, 11, 171, 27);
        this.getContentPane().add(titluLabel);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(36, 86, 19, 14);
        this.getContentPane().add(lblNewLabel);

        idText = new JTextField();
        idText.setBounds(55, 84, 181, 20);
        this.getContentPane().add(idText);
        idText.setColumns(10);

        JLabel lblName = new JLabel("NAME");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(10, 113, 50, 14);
        this.getContentPane().add(lblName);

        ageText = new JTextField();
        ageText.setColumns(10);
        ageText.setBounds(55, 140, 182, 20);
        this.getContentPane().add(ageText);

        JLabel lblAge = new JLabel("AGE");
        lblAge.setHorizontalAlignment(SwingConstants.CENTER);
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAge.setBounds(15, 143, 50, 14);
        this.getContentPane().add(lblAge);

        nameText = new JTextField();
        nameText.setColumns(10);
        nameText.setBounds(55, 110, 182, 20);
        this.getContentPane().add(nameText);

        emailText = new JTextField();
        emailText.setColumns(10);
        emailText.setBounds(55, 169, 182, 20);
        this.getContentPane().add(emailText);

        adressText = new JTextField();
        adressText.setColumns(10);
        adressText.setBounds(55, 202, 182, 20);
        this.getContentPane().add(adressText);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEmail.setBounds(11, 171, 50, 14);
        this.getContentPane().add(lblEmail);

        JLabel lblAdress = new JLabel("Adress");
        lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAdress.setBounds(5, 203, 50, 14);
        this.getContentPane().add(lblAdress);

        addClientButton = new JButton("Add client");
        addClientButton.setBounds(36, 255, 105, 23);
        this.getContentPane().add(addClientButton);

        updateClientButton = new JButton("Update client");
        updateClientButton.setBounds(145, 255, 108, 23);
        this.getContentPane().add(updateClientButton);

        deteleClientButton = new JButton("Delete client");
        deteleClientButton.setBounds(36, 307, 105, 23);
        this.getContentPane().add(deteleClientButton);

        viewClientButton = new JButton("View clients");
        viewClientButton.setBounds(145, 307, 108, 23);
        this.getContentPane().add(viewClientButton);

        backClientButton = new JButton("Back");
        backClientButton.setBounds(10, 477, 89, 23);
        this.getContentPane().add(backClientButton);
        this.setVisible(false);
    }

    /**
     * @return boolean
     */
    public boolean checkForEmpty() {
        if (adressText.getText().equals("")) {
            return true;
        }
        if (idText.getText().equals("")) {
            return true;
        }
        if (ageText.getText().equals("")) {
            return true;
        }
        if (emailText.getText().equals("")) {
            return true;
        }
        if (nameText.getText().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * @return int
     */
    public int getIdText() {
       if(idText.getText().equals(""))
       {
           return -9999;
       }
        return Integer.parseInt(idText.getText());
    }

    /**
     * @return int
     */
    public int getAgeText() {
      if (ageText.getText().equals("")) {
            return -9999;
      }
        return Integer.parseInt(ageText.getText());
    }

    /**
     * @param message String
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * @return String
     */
    public String getNameText() {
        if (nameText.getText().equals("")) {
            return null;
        }
        return nameText.getText();
    }

    /**
     * @return String
     */
    public String getEmailText() {
       if (emailText.getText().equals("")) {
            return null;
        }
        return emailText.getText();
    }

    /**
     * @return String
     */
    public String getAdressText() {
       if (adressText.getText().equals("")) {
            return null;
        }
        return adressText.getText();
    }

    /**
     * @param action ActionListener
     */
    public void backClientListener(ActionListener action) {
        backClientButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void viewClientButtonListener(ActionListener action) {
        viewClientButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void updateClientButtonListener(ActionListener action) {
        updateClientButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void deleteClientButtonListener(ActionListener action) {
        deteleClientButton.addActionListener(action);
    }

    /**
     * @param action ActionListener
     */
    public void addClientButtonListener(ActionListener action) {
        addClientButton.addActionListener(action);
    }

    public void refresh() {
        idText.setText(null);
        ageText.setText(null);
        nameText.setText(null);
        emailText.setText(null);
        adressText.setText(null);
    }
}
