package com.company.tables;

import com.company.DBWorker;
import com.company.basicClasses.Employees;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class EmployeesTable extends AbstractTableModel {
    private List<Employees> data;

    public EmployeesTable() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllemps();
        DBWorker.closeConnection();

    }
    public  void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllemps();
        DBWorker.closeConnection();
        this.fireTableDataChanged();
    }
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    public String getColumnName(int column) {
        switch (column){
            case(0):
                return "Номер работника";
            case(1):
                return "Имя работника";
            case(2):
                return "Должность";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employees employees=data.get(rowIndex);
        switch (columnIndex){
            case 0: return employees.getId();
            case 1: return employees.getName();
            case 2: return employees.getJob();
        }
        return "";
    }
    public Employees getEmp(int row){
        return data.get(row);
    }

    public void add(Employees emp) throws SQLException {
        DBWorker.initDB();
        DBWorker.addEmp(emp);
        update();
        DBWorker.closeConnection();
    }
    public void  remove(int id) throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteEmps(getEmp(id));
        update();
        DBWorker.closeConnection();
    }
    public void updateEm(int id, Employees emp) throws SQLException {
        DBWorker.initDB();
        DBWorker.updateEmp(id, emp);
        update();
        DBWorker.closeConnection();
    }
    public void deleteAll() throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteAllemps();
        update();
        DBWorker.closeConnection();
    }
}
