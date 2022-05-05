package dataAccess;
import model.Client;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <T>
 */
public class GenericDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(GenericDAO.class.getName());
    private Class<T> type=null;

    /**
     * @param object T
     */
    public GenericDAO(T object) {
        this.type = (Class<T>) object.getClass();
    }

    /**
     * @return String
     */
    private String createSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append("FROM ");
        String reconvertName = type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1) + "s";
        sb.append(reconvertName);
        sb.append(" WHERE " + type.getDeclaredFields()[0].getName() + " =?");
        return sb.toString();
    }

    /**
     * @return String
     */
    private String findAllQuery()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append("FROM ");
        String reconvertName = type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1) + "s";
        sb.append(reconvertName);
        return sb.toString();
    }

    /**
     * @return List<T></T>
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = findAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * @param id int
     * @return T
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            //return createObjects(resultSet).get(0);
            List<T> recievedObjects=createObjects(resultSet);
            if(recievedObjects==null)
            {
                return null;
            }else return recievedObjects.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * @param resultSet ResultSet
     * @return List<T></T>
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
            if (list.size()==0) return null;
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param object Object
     * @return String
     */
    private String createInsertQuery(Object object) {
        StringBuilder columns = new StringBuilder();
        String reconvertName=type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1)+"s";
        columns.append("INSERT INTO " + reconvertName + " (");
        StringBuilder key = new StringBuilder();
        key.append("VALUES "+"(");
        int last = object.getClass().getDeclaredFields().length;
        int contorLoop = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                contorLoop++;
                String capitalLetter = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                if (last == contorLoop) {
                    columns.append(capitalLetter + ") ");
                    if(value instanceof Integer) key.append(value+ ");");
                    if(value instanceof String) key.append("'"+value+"');");
                }
                else {
                    if(value instanceof Integer) key.append(value+",");
                    if(value instanceof String) key.append("'"+value+"',");
                    columns.append(capitalLetter + ",");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String finalString=(new StringBuilder()).append(columns).append(key).toString();
        return finalString.toString();
    }

    /**
     * @param t T
     * @return T
     */
    public T  insert(T t) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        String insertStatementString=createInsertQuery(t);
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return t;
    }

    /**
     * @param id int
     * @return String
     */
    private String deleteInsertQuery(int id) {
        StringBuilder columns = new StringBuilder();
        String reconvertName=type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1)+"s";
        columns.append("DELETE FROM " + reconvertName+" ");
        StringBuilder key = new StringBuilder();
        key.append("WHERE "+type.getDeclaredFields()[0].getName()+"= "+id);
        String finalString=(new StringBuilder()).append(columns).append(key).toString();
        return finalString.toString();
    }

    /**
     * @param id int
     * @return int
     */
    public int delete(int id){
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        String deleteStatementString=deleteInsertQuery(id);
        int deleteId= -1;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.executeUpdate();
            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deleteId = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return deleteId;
    }

    /**
     * @param object Object
     * @return String
     */
    private String updateStatementQuery(Object object) {
        StringBuilder columns = new StringBuilder();
        StringBuilder id=new StringBuilder();
        String reconvertName=type.getSimpleName().substring(0, 1).toLowerCase() + type.getSimpleName().substring(1)+"s";
        columns.append("UPDATE " + reconvertName+" ");
        columns.append("SET ");
        int last = object.getClass().getDeclaredFields().length;
        int contorLoop=0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                if(contorLoop==0) id.append(value);
                contorLoop++;
                String capitalLetter = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                if (last == contorLoop) {
                    columns.append(capitalLetter+" = ");
                    if(value ==null) columns.append(",");
                    if(value instanceof Integer) columns.append(value+" ");
                    if(value instanceof String) columns.append("'"+value+"' ");
                }
                else {
                    columns.append(capitalLetter + " = ");
                    if(value ==null) columns.append(",");
                    if(value instanceof Integer) columns.append(value+",");
                    if(value instanceof String) columns.append("'"+value+"',");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String finalString=(new StringBuilder()).append(columns).append("WHERE id = ").append(id).toString();
        return finalString;
    }

    /**
     * @param t T
     * @return T
     */
    public T update(T t) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        String updateStatementString=updateStatementQuery(t);
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.executeUpdate();
            ResultSet rs = updateStatement.getGeneratedKeys();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally{
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return t;
    }
}

