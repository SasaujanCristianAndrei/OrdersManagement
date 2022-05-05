package presentation;
import businessLogic.ClientBLL;
import businessLogic.OrderBLL;
import businessLogic.ProductBLL;
import dataAccess.ConnectionFactory;
import model.Client;
import model.Order;
import model.Product;
import model.dto.OrderDTO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class Controller {
    private View view;
    private ClientView clientView;
    private OrderView orderView;
    private ProductView productView;
    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private JScrollPane scrollPaneOrders;
    private JScrollPane scrollPaneProducts;
    private JScrollPane scrollPaneClients;
    /**
     * @param view        View
     * @param clientView  ClientView
     * @param orderView   OrderView
     * @param productView ProductView
     */
    public Controller(View view, ClientView clientView, OrderView orderView, ProductView productView) {
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        this.view = view;
        this.clientView = clientView;
        this.orderView = orderView;
        this.productView = productView;
        this.view.clientButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                clientView.setVisible(true);
            }
        });
        this.clientView.backClientListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.setVisible(false);
                view.setVisible(true);
            }
        });
        this.clientView.viewClientButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scrollPaneClients != null) {
                    clientView.remove(scrollPaneClients);
                }
                List<Client> clients = clientBLL.getAllClients();
                TableForView table = new TableForView(clients);
                scrollPaneClients = new JScrollPane(table.getTable());
                scrollPaneClients.setBounds(285, 49, 674, 435);
                clientView.add(scrollPaneClients);
                clientView.repaint();
            }
        });
        this.clientView.addClientButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientView.checkForEmpty()) {
                    clientView.showMessage("Va rugam introduceti toate campurile");
                    return;
                }
                Client client = new Client(clientView.getIdText(), clientView.getNameText(), clientView.getAgeText(), clientView.getEmailText(), clientView.getAdressText());
                if (clientBLL.addClient(client) == -1) {
                    clientView.showMessage("The client with id " + client.getId() + " is already in the DB!");
                }
                clientView.refresh();
            }
        });
        this.clientView.deleteClientButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientBLL.deleteClient(clientView.getIdText())==-1)
                {
                    clientView.showMessage("We can't remove the client with id = "+clientView.getIdText()+" because it has a order placed");
                    return;
                }
                clientView.refresh();
            }
        });
        this.clientView.updateClientButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clientView.checkForEmpty()) {
                    clientView.showMessage("Please fill all the fields");
                    return;
                }
                clientBLL.updateClient(clientView.getIdText(), clientView.getNameText(), clientView.getAgeText(), clientView.getEmailText(), clientView.getAdressText());
                clientView.refresh();
            }
        });
        this.view.productOperationsButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                productView.setVisible(true);
            }
        });
        this.productView.viewProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scrollPaneProducts != null) {
                    productView.remove(scrollPaneProducts);
                }
                List<Product> products = productBLL.getAllProducts();
                TableForView table = new TableForView(products);
                scrollPaneProducts = new JScrollPane(table.getTable());
                scrollPaneProducts.setBounds(285, 49, 674, 435);
                productView.add(scrollPaneProducts);
                productView.repaint();
            }
        });
        this.productView.backProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productView.setVisible(false);
                view.setVisible(true);
            }
        });
        this.productView.addProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productView.checkForEmpty()) {
                    productView.showMessage("Please fill all the fields");
                    return;
                }
                Product product = new Product(productView.getIdProductText(), productView.getNameProductText(), productView.getPriceText(), productView.getStockText());
                if (productBLL.addProduct(product) == -1) {
                    productView.showMessage("The product with id " + product.getId() + " is already in the DB!");
                }
                productView.refresh();
            }
        });
        this.productView.deleteProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = productView.getIdProductText();
                if(productBLL.deleteProduct(id)==-1)
                {
                    productView.showMessage("We can't delete the product with id = "+id+" because it has a order placed");
                }
                productView.refresh();
            }
        });
        this.productView.editProductListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productView.checkForEmpty()) {
                    productView.showMessage("Please fill all the fields");
                    return;
                }
                productBLL.updateProduct(productView.getIdProductText(), productView.getNameProductText(), productView.getPriceText(), productView.getStockText());
                productView.refresh();
            }
        });
        this.view.ordersButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                orderView.setVisible(true);
                orderView.setComboBoxClientID(clientBLL.getAll());
                orderView.setComboBoxProductID(productBLL.getAll());

            }
        });
        this.orderView.backOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderView.setVisible(false);
                view.setVisible(true);
            }
        });
        this.orderView.placeOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (orderView.checkForEmpty()) {
                    orderView.showMessage("Please fill all the fields");
                    return;
                }
                Order order = new Order(orderView.getIdOrderText(), orderView.getComboBoxClientID(), orderView.getComboBoxProductID(), orderView.getQuantityText());
                OrderBLL orderBLL = new OrderBLL();
                List<Product> products = productBLL.getAllProducts();
                int productQuantity = 0;
                try {
                    for (Product product : products) {
                        if (product.getId() == order.getIdProduct()) {
                            productQuantity = product.getStock();
                            if (product.getStock() - order.getQuantity() >= 0) {
                                if (orderBLL.addOrder(order, product) == -1) {
                                    orderView.showMessage("The order with id " + order.getId() + " is already in the DB!");
                                    return;
                                }
                                product.setStock(product.getStock() - order.getQuantity());
                                orderBLL.updateQuantity(product);
                                productQuantity--;
                                break;
                            } else throw new Exception("Out of stock");
                        }
                    }
                } catch (Exception exception) {
                    if (exception.getMessage().equals("Out of stock")) {
                        orderView.showMessage("Out of stock, only " + productQuantity + " are in stock");
                    }
                }
                extractInfo();
            }
        });
        this.orderView.viewOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scrollPaneOrders != null) {
                    orderView.remove(scrollPaneOrders);
                }
                List<OrderDTO> orderDTO = new ArrayList<OrderDTO>();
                List<Order> orders;
                if(orderBLL.getAllOrders()==null) {
                    return;
                }else orders=orderBLL.getAllOrders();
                List<Product> products = productBLL.getAllProducts();
                List<Client> clients = clientBLL.getAllClients();
                for (Order order : orders) {
                    Product targetProduct = products.stream().filter(product -> order.getIdProduct() == product.getId()).findFirst().orElse(null);
                    Client targetClient = clients.stream().filter(client -> order.getIdClient() == client.getId()).findFirst().orElse(null);
                    orderDTO.add(new OrderDTO(order.getId(), targetClient.getName(), targetProduct.getNameProduct(), order.getQuantity()));
                }
                TableForView table = new TableForView(orderDTO);
                scrollPaneOrders = new JScrollPane(table.getTable());
                scrollPaneOrders.setBounds(285, 49, 674, 435);
                orderView.add(scrollPaneOrders);
                orderView.repaint();
            }
        });
        this.orderView.editOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity = orderView.getQuantityText();
                List<Order> orders = orderBLL.getAllOrders();
                List<Product> products = productBLL.getAllProducts();
                int quantityOrder = 0;
                for (Order order : orders) {
                    if (orderView.getIdOrderText() == order.getId()) {
                        quantityOrder = order.getQuantity();
                    }
                }
                for (Product product : products) {
                    if (product.getId() == orderView.getComboBoxProductID()) {
                        product.setStock(product.getStock() + quantityOrder - orderView.getQuantityText());
                        orderBLL.updateQuantity(product);
                    }
                }
                orderBLL.updateOrder(orderView.getIdOrderText(), orderView.getComboBoxClientID(), orderView.getComboBoxProductID(), orderView.getQuantityText());
            }
        });
        this.orderView.removeOrderListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = orderView.getIdOrderText();
                List<Product> products = productBLL.getAllProducts();
                int quantity = 0;
                for (Product product : products) {
                    if (orderView.getComboBoxProductID() == product.getId()) {
                        quantity = orderBLL.getQuantity(id);
                        product.setStock(product.getStock() + quantity);
                        orderBLL.updateQuantity(product);
                    }
                }
                orderBLL.deleteOrder(id);
                orderView.refresh();
            }
        });
    }
    public void extractInfo() {
        Order order = new Order(orderView.getIdOrderText(), orderView.getComboBoxClientID(), orderView.getComboBoxProductID(), orderView.getQuantityText());
        List<Product> products = productBLL.getAllProducts();
        List<Client> clients = clientBLL.getAllClients();
        List<Integer> prices = new ArrayList<Integer>();
        List<Order> orders = orderBLL.getAllOrders();
        List<OrderDTO> orderDTO = new ArrayList<OrderDTO>();
        for (Order orderProduct : orders) {
            Product targetProduct = products.stream().filter(product -> orderProduct.getIdProduct() == product.getId()).findFirst().orElse(null);
            Client targetClient = clients.stream().filter(client -> orderProduct.getIdClient() == client.getId()).findFirst().orElse(null);
            orderDTO.add(new OrderDTO(order.getId(), targetClient.getName(), targetProduct.getNameProduct(), order.getQuantity()));
            prices.add(targetProduct.getPrice());
        }
        int contor = 0;
        for (OrderDTO iterator : orderDTO) {
            File file = new File("order" + iterator.getId() + ".txt");
            try {
                FileWriter bill = new FileWriter(file);
                bill.write("Comanda cu numarul = " + iterator.getId() + "\n");
                bill.write("Numele clientului = " + iterator.getNameClient() + "\n");
                bill.write("Numele produsului = " + iterator.getNameProduct() + "\n");
                bill.write("Cantitate = " + iterator.getQuantity() + "\n");
                bill.write("De platit = " + iterator.getQuantity() * prices.get(contor++) + " RON\n");
                bill.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
