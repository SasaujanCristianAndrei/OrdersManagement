package businessLogic;

import dataAccess.GenericDAO;
import model.Order;
import model.Product;

import java.util.List;

public class ProductBLL {

    GenericDAO<Product> productDAO = new GenericDAO<Product>(new Product());
    GenericDAO<Order> orderDAO = new GenericDAO<Order>(new Order());
    /**
     * @param product Product
     * @return int
     */
    public int addProduct(Product product)
    {
        if(productDAO.findById(product.getId())==null)
        {
            productDAO.insert(product);
            return 0;
        }
        return -1;
    }

    /**
     * @param idProduct int
     */
    public int deleteProduct(int idProduct)
    {
        List<Order>products=orderDAO.findAll();
        int contor=0;
        for(Order product:products)
        {
            if(product.getIdProduct()==idProduct)
            {
                return -1;
            }
        }
        productDAO.delete(idProduct);
        return 0;
    }

    /**
     * @param idProduct int
     * @param nameProduct String
     * @param Price int
     * @param Stock int
     */
    public void updateProduct(int idProduct, String nameProduct,int Price,int Stock)
    {
        productDAO.update(new Product(idProduct,nameProduct,Price,Stock));
    }

    /**
     * @return List<Product></Product>
     */
    public List<Product> getAllProducts()
    {
        return productDAO.findAll();
    }

    /**
     * @return String[]
     */
    public String[] getAll()
    {
        List<Product> product=productDAO.findAll();
        String [] result=new String[product.size()];
        int index=0;
        for(Product ex : product)
        {
            result[index++]=Integer.toString(ex.getId())+") "+ex.getNameProduct();
        }
        return result;
    }

}
