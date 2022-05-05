package model.dto;

public class OrderDTO {

    private int id;
    private String nameClient;
    private String nameProduct;
    private int quantity;

    /**
     * @param id int
     * @param nameClient String
     * @param nameProduct String
     * @param quantity int
     */
    public OrderDTO(int id, String nameClient, String nameProduct, int quantity) {
        this.id = id;
        this.nameClient = nameClient;
        this.nameProduct = nameProduct;
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
     * @return String
     */
    public String getNameClient() {
        return nameClient;
    }

    /**
     * @param nameClient String
     */
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    /**
     * @return String
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * @param nameProduct String
     */
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    /**
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity int
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
