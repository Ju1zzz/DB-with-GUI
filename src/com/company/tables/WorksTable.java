package com.company.tables;

import com.company.DBWorker;
import com.company.basicClasses.Works;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class WorksTable extends AbstractTableModel {
    private List<Works> data;
    public WorksTable () throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllworks();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllworks();
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
            case 1: return "Наименование услуги";
            case 2: return "Время выполнения";
            case 3: return "Цена";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Works works=data.get(rowIndex);
        switch (columnIndex){
            case 0: return works.getId();
            case 1: return works.getTitle();
            case 2: return works.getHours();
            case 3: return works.getPrice();
        }
        return "";
    }
    public Works getWork(int row){
        return data.get(row);
    }

    public void add(Works works) throws SQLException {
        DBWorker.initDB();
        DBWorker.addWork(works);
        update();
        DBWorker.closeConnection();
    }
    public void  remove(int id) throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteWorks(getWork(id));
        update();
        DBWorker.closeConnection();
    }
    public void updateW(int id, Works works) throws SQLException {
        DBWorker.initDB();
        DBWorker.updateWork(id, works);
        update();
        DBWorker.closeConnection();
    }
    public void deleteAll() throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteAllworks();
        update();
        DBWorker.closeConnection();
    }
}
