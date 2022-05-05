package model;

public class Client {
    private int id;
    private String name;
    private int age;
    private String email;
    private String adress;


    public Client()
    {

    }

    /**
     * @param id int
     * @param name String
     * @param age int
     * @param email String
     * @param adress String
     */
    public Client(int id, String name, int age, String email, String adress) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.adress = adress;
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
    public String getName() {
        return name;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age int
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return void
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String
     */
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress String
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return String
     *
     */
    @Override
    public String toString() {
        return "Client[" + "id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", adress=" + adress + "]";
    }
}
