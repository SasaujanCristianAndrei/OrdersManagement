package model;

public class Product {
    private int id;
    private String nameProduct;
    private int price;
    private int stock;


    public Product()
    {

    }

    /**
     * @param id int
     * @param nameProduct String
     * @param price int
     * @param stock int
     */
    public Product(int id, String nameProduct, int price, int stock) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.price = price;
        this.stock = stock;
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
    public int getPrice() {
        return price;
    }

    /**
     * @param price int
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return int
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock int
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
