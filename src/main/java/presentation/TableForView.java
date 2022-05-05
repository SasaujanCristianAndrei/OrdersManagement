package presentation;

import model.Order;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class TableForView<T>{
    JTable table;
    private String[]columnNames;
    private String[][]data;

    /**
     * @param object List<T></T>
     */
    @SuppressWarnings("unchecked")
    public TableForView(List<T> object) {
        columnNames=new String[object.get(0).getClass().getDeclaredFields().length];
        data=new String[object.size()][object.get(0).getClass().getDeclaredFields().length];
        buildTable(object);
        setData();
    }

    /**
     * @param object List<T></T>
     */
    public void buildTable(List<T> object)
    {
        int contor=0;
        for(int i=0;i<object.size();i++)
        {
            contor=0;
            for (Field field : object.get(i).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(object.get(i));
                    columnNames[contor]=field.getName();
                    data[i][contor++]=value.toString();
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setData()
    {
        table = new JTable(data,columnNames);
        table.setBounds(285, 49, 674, 435);
    }

    public JTable getTable() {
        return table;
    }
}
