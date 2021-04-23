package com.company.tables;

import com.company.DBWorker;
import com.company.basicClasses.Orders;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class OrdersTable extends AbstractTableModel {
    private List<Orders> data;
    public OrdersTable () throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllorders();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllorders();
        DBWorker.closeConnection();
        this.fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return data.size();
    }
    public String getColumnName(int column) {
        switch (column){
            case 0: return "id";
            case 1: return "Наименование услуги";
            case 2: return "Имя работника";
            case 3: return "Номер машины";
            case 4: return "Комментарий";
        }
        return "";
    }
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Orders orders=data.get(rowIndex);
        switch (columnIndex){
            case 0: return orders.getId();
            case 1: return orders.getWork();
            case 2: return orders.getEmp();
            case 3: return orders.getCar();
            case 4: return orders.getComment();
        }
        return "";
    }
    public Orders getOrder(int row){
        return data.get(row);
    }

    public void add(Orders orders) throws SQLException {
        DBWorker.initDB();
        DBWorker.addOrder(orders);
        update();
        DBWorker.closeConnection();
    }
    public void  remove(int id) throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteOrders(getOrder(id));
        update();
        DBWorker.closeConnection();
    }
    public void updateO(int id, Orders orders) throws SQLException {
        DBWorker.initDB();
        DBWorker.updateOrder(id, orders);
        update();
        DBWorker.closeConnection();
    }
    public void deleteAll() throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteAllorders();
        update();
        DBWorker.closeConnection();
    }
}
