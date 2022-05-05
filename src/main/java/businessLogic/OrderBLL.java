package businessLogic;

import dataAccess.GenericDAO;
import model.Client;
import model.Order;
import model.Product;

import java.util.List;


public class OrderBLL {
    GenericDAO<Order> orderDAO = new GenericDAO<Order>(new Order());
    GenericDAO<Product> productDAO = new GenericDAO<Product>(new Product());

    /**
     * @param order Order
     * @param product Product
     * @return int
     */
    public int addOrder(Order order,Product product)
    {
        if(orderDAO.findById(order.getId())==null && product.getStock()>=order.getQuantity())
        {
            orderDAO.insert(order);
            return 0;
        }
        return -1;
    }

    /**
     * @param product Product
     */
    public void updateQuantity(Product product)
    {
        productDAO.update(product);
    }

    /**
     * @param idOrder int
     */
    public void deleteOrder(int idOrder)
    {

        orderDAO.delete(idOrder);
    }

    /**
     * @return List<Order></Order>
     */
    public List<Order> getAllOrders()
    {
        if(orderDAO.findAll()==null)
        {
            return null;
        }
        return orderDAO.findAll();
    }

    /**
     * @param idOrder int
     * @return int
     */
    public int getQuantity(int idOrder)
    {
        if(orderDAO.findById(idOrder)!=null)
        {
            return orderDAO.findById(idOrder).getQuantity();
        }
        return -1;
    }

    /**
     * @param idOrder int
     * @param idClient int
     * @param idProduct int
     * @param quantity int
     */
    public void updateOrder(int idOrder, int idClient ,int idProduct,int quantity)
    {
        orderDAO.update(new Order(idOrder,idClient,idProduct,quantity));
    }
}
