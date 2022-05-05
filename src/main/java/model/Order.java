package model;

public class Order {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;


    public Order()
    {

    }

    /**
     * @param id int
     * @param idClient int
     * @param idProduct int
     * @param quantity int
     */
    public Order(int id, int idClient, int idProduct, int quantity) {
        this.id = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return int
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient int
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return int
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct int
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProduct=" + idProduct +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * @param quantity int
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
