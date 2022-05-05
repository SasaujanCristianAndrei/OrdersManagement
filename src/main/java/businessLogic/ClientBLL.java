package businessLogic;

import dataAccess.GenericDAO;
import model.Client;
import model.Order;

import java.util.ArrayList;
import java.util.List;


public class ClientBLL {

    GenericDAO<Client> clientDAO = new GenericDAO<Client>(new Client());
    GenericDAO<Order> orderDAO = new GenericDAO<Order>(new Order());

    /**
     * @param client Client
     * @return Client
     */
    public Client findBy(Client client) {
        return clientDAO.findById(client.getId());
    }

    /**
     * @param client Client
     * @return int
     */
    public int addClient(Client client) {
        if (clientDAO.findById(client.getId()) == null) {
            clientDAO.insert(client);
            return 0;
        }
        return -1;
    }

    /**
     * @return List<Client></Client>
     */
    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    /**
     * @param id int
     * @return int
     */
    public int deleteClient(int id) {
        List<Order>orders=orderDAO.findAll();
        int contor=0;
        for(Order order:orders)
        {
            if(order.getIdClient()==id)
            {
                return -1;
            }
        }
        clientDAO.delete(id);
        return 0;
    }

    /**
     * @param id int
     * @param name String
     * @param age int
     * @param email String
     * @param adress  String
     */
    public void updateClient(int id, String name, int age, String email, String adress) {
        clientDAO.update(new Client(id, name, age, email, adress));
    }

    /**
     * @return String[]
     */
    public String[] getAll() {
        List<Client> clienti = clientDAO.findAll();
        String[] result = new String[clienti.size()];
        int index = 0;
        for (Client ex : clienti) {
            result[index++] = Integer.toString(ex.getId())+") "+ex.getName();
        }
        return result;
    }
}
