package com.company.tables;

import com.company.DBWorker;
import com.company.basicClasses.Clients;
import javax.swing.table.AbstractTableModel;

import java.sql.SQLException;
import java.util.List;

public class ClientTable extends AbstractTableModel {
    private List <Clients> data;
    public ClientTable () throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllclients();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllclients();
        DBWorker.closeConnection();
        this.fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    public String getColumnName(int column) {
        switch (column){
            case 0: return "id";
            case 1: return "Имя заказчика";
            case 2: return "Номер телефона";
            case 3: return "Номер машины";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Clients client=data.get(rowIndex);
        switch (columnIndex){
            case 0: return client.getId();
            case 1: return client.getName();
            case 2: return client.getPhone();
            case 3: return client.getCar();
        }
        return "";
    }
    public Clients getClient(int row){
        return data.get(row);
    }

    public void add(Clients clients) throws SQLException {
        DBWorker.initDB();
        DBWorker.addClient(clients);
        update();
        DBWorker.closeConnection();
    }
    public void  remove(int id) throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteClients(getClient(id));
        update();
        DBWorker.closeConnection();
    }
    public void updateCl(int id, Clients clients) throws SQLException {
        DBWorker.initDB();
        DBWorker.updateClient(id, clients);
        update();
        DBWorker.closeConnection();
    }
    public void deleteAll() throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteAllclients();
        update();
        DBWorker.closeConnection();
    }
}