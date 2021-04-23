package com.company;

import com.company.basicClasses.Clients;
import com.company.basicClasses.Employees;
import com.company.basicClasses.Orders;
import com.company.basicClasses.Works;
import com.company.tables.ClientTable;
import com.company.tables.EmployeesTable;
import com.company.tables.OrdersTable;
import com.company.tables.WorksTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private JPanel clients, employees, works, orders;
    private JPanel butpanelE, butpanelW,butpanelC, butpanelO;
    private JTable tbclients, tbemployees, tbworks, tborders;
    private JButton addWork, deleteWork, changeWork, deleteAllWorks, findWork;
    private JButton addEmp, deleteEmp, changeEmp, deleteAllEmps, findEmp;
    private JButton addClient, deleteClient, changeClient, deleteAllClients, findClient;
    private JButton addOrder, deleteOrder, changeOrder, deleteAllOrders, findOrder;
    private WorksTable wmodel;
    private EmployeesTable emodel;
    private ClientTable cmodel;
    private OrdersTable omodel;
    private JTextField filterText1, filterText2, filterText3, filterText4;
    private TableRowSorter<ClientTable> sorter1;
    private TableRowSorter<WorksTable> sorter2;
    private TableRowSorter<EmployeesTable> sorter3;
    private TableRowSorter<OrdersTable> sorter4;
    public MainWindow(){
        super("Autorepair");
        setSize(400, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DBWorker.initDB();
        init();
        JTabbedPane tabs = new JTabbedPane();
        clients = new JPanel();
        employees = new JPanel();
        works=new JPanel();
        orders = new JPanel();
        try {
            cmodel=new ClientTable();
            emodel=new EmployeesTable();
            wmodel=new WorksTable();
            omodel=new OrdersTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sorter1 = new TableRowSorter<>(cmodel);
        sorter2 = new TableRowSorter<>(wmodel);
        sorter3 = new TableRowSorter<>(emodel);
        sorter4 = new TableRowSorter<>(omodel);
        tbclients = new JTable(cmodel);
        tbemployees=new JTable(emodel);
        tbworks =new JTable(wmodel);
        tborders=new JTable(omodel);
        tbclients.setRowSorter(sorter1);
        tbemployees.setRowSorter(sorter3);
        tbworks.setRowSorter(sorter2);
        tborders.setRowSorter(sorter4);
        JScrollPane jScrollPaneClients = new JScrollPane(tbclients);
        JScrollPane jScrollPaneEmps=new JScrollPane(tbemployees);
        JScrollPane jScrollPaneWork=new JScrollPane(tbworks);
        JScrollPane jScrollPaneOrder=new JScrollPane(tborders);
        employees.add(jScrollPaneEmps);
        clients.add(jScrollPaneClients);
        works.add(jScrollPaneWork);
        orders.add(jScrollPaneOrder);
        clients.add(butpanelC, BorderLayout.WEST);
        employees.add(butpanelE,BorderLayout.WEST);
        works.add(butpanelW, BorderLayout.WEST);
        orders.add(butpanelO, BorderLayout.WEST);
        tabs.addTab("Клиенты", clients);
        tabs.addTab("Работники", employees);
        tabs.addTab("Услуги", works);
        tabs.addTab("Текущие заказы", orders);
        this.add(tabs);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    private void init(){
        addWork = new JButton("Добавить Услугу");
        addWork.addActionListener(new MyListener());
        deleteWork=new JButton("Удалить из перечня Услуг");
        deleteWork.addActionListener(new MyListener());
        changeWork=new JButton("Изменить Услугу");
        changeWork.addActionListener(new MyListener());
        deleteAllWorks = new JButton("Очистить базу услуг");
        deleteAllWorks.addActionListener(new MyListener());
        findWork= new JButton("Найти услугу");
        findWork.addActionListener(new MyListener());
        filterText1 = new JTextField(10);
        addEmp = new JButton("Добавить Работника");
        addEmp.addActionListener(new MyListener());
        deleteEmp=new JButton("Уволить Работника");
        deleteEmp.addActionListener(new MyListener());
        changeEmp=new JButton("Изменить данные о Работнике");
        changeEmp.addActionListener(new MyListener());
        deleteAllEmps = new JButton("Уволить всех работников");
        deleteAllEmps.addActionListener(new MyListener());
        findEmp= new JButton("Найти работника");
        findEmp.addActionListener(new MyListener());
        filterText2 = new JTextField(10);
        addClient = new JButton("Добавить Клиента");
        addClient.addActionListener(new MyListener());
        deleteClient=new JButton("Удалить данные о клиенте");
        deleteClient.addActionListener(new MyListener());
        changeClient=new JButton("Изменить данные о клиенте");
        changeClient.addActionListener(new MyListener());
        deleteAllClients=new JButton("Очистить базу клиентов");
        deleteAllClients.addActionListener(new MyListener());
        findClient= new JButton("Найти клиента");
        findClient.addActionListener(new MyListener());
        filterText3 = new JTextField(10);
        addOrder = new JButton("Добавить заказ");
        addOrder.addActionListener(new MyListener());
        deleteOrder=new JButton("Удалить данные о заказе");
        deleteOrder.addActionListener(new MyListener());
        changeOrder=new JButton("Изменить данные о заказе");
        changeOrder.addActionListener(new MyListener());
        deleteAllOrders=new JButton("Все заказы выполнены");
        deleteAllOrders.addActionListener(new MyListener());
        findOrder= new JButton("Найти заказ");
        findOrder.addActionListener(new MyListener());
        filterText4 = new JTextField(10);
        GridLayout gl=new GridLayout(6,1,3,20);
        butpanelW = new JPanel(gl);
        butpanelW.add(addWork);
        butpanelW.add(deleteWork);
        butpanelW.add(changeWork);
        butpanelW.add(deleteAllWorks);
        butpanelW.add(filterText1);
        butpanelW.add(findWork);
        butpanelE = new JPanel(gl);
        butpanelE.add(addEmp);
        butpanelE.add(deleteEmp);
        butpanelE.add(changeEmp);
        butpanelE.add(deleteAllEmps);
        butpanelE.add(filterText2);
        butpanelE.add(findEmp);
        butpanelC = new JPanel(gl);
        butpanelC.add(addClient);
        butpanelC.add(deleteClient);
        butpanelC.add(changeClient);
        butpanelC.add(deleteAllClients);
        butpanelC.add(filterText3);
        butpanelC.add(findClient);
        butpanelO=new JPanel(gl);
        butpanelO.add(addOrder);
        butpanelO.add(deleteOrder);
        butpanelO.add(changeOrder);
        butpanelO.add(deleteAllOrders);
        butpanelO.add(filterText4);
        butpanelO.add(findOrder);
    }
    class MyListener implements ActionListener{

        @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Добавить Клиента")) {
                AdditionClient();
            }
            else if (e.getActionCommand().equals("Удалить данные о клиенте")) {
                try { int id=tbclients.getSelectedRow();
                      cmodel.remove(id);
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Очистить базу клиентов")) {
                try {
                    cmodel.deleteAll();
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Изменить данные о клиенте")) {
                try {  UpdatingClient();
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getActionCommand().equals("Добавить Работника")) {
                AdditionEmp();
            }
            else if (e.getActionCommand().equals("Уволить Работника")) {
                try { int id=tbemployees.getSelectedRow();
                    emodel.remove(id);
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Изменить данные о Работнике")) {
                try { UpdatingEmps();
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getActionCommand().equals("Уволить всех работников")) {
                try { emodel.deleteAll();
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Добавить Услугу")) {
                AdditionWork();
            }
            else if (e.getActionCommand().equals("Удалить из перечня Услуг")) {
                try { int id=tbworks.getSelectedRow();
                    wmodel.remove(id);
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Изменить Услугу")) {
                try { UpdatingWork();
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (e.getActionCommand().equals("Очистить базу услуг")) {
                try {
                    wmodel.deleteAll();
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Добавить заказ")) {
                try {
                    AdditionOrder();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Удалить данные о заказе")) {
                try { int id=tborders.getSelectedRow();
                      omodel.remove(id);
                } catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Все заказы выполнены")) {
                try {
                    omodel.deleteAll();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals("Изменить данные о заказе")) {
            try { UpdatingOrder();
            } catch (IndexOutOfBoundsException | SQLException ex) {
                JOptionPane.showMessageDialog(MainWindow.this, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
            else if (e.getActionCommand().equals("Найти клиента")) {
                String text = filterText3.getText();
                if (text.length() == 0) {
                    sorter1.setRowFilter(null);
                } else {
                    sorter1.setRowFilter(RowFilter.regexFilter(text));
                }
            }
            else if (e.getActionCommand().equals("Найти услугу")) {
                String text = filterText1.getText();
                if (text.length() == 0) {
                    sorter2.setRowFilter(null);
                } else {
                    sorter2.setRowFilter(RowFilter.regexFilter(text));
                }
            }
            else if (e.getActionCommand().equals("Найти работника")) {
                String text = filterText2.getText();
                if (text.length() == 0) {
                    sorter3.setRowFilter(null);
                } else {
                    sorter3.setRowFilter(RowFilter.regexFilter(text));
                }
            }
            else if (e.getActionCommand().equals("Найти заказ")) {
                String text = filterText4.getText();
                if (text.length() == 0) {
                    sorter4.setRowFilter(null);
                } else {
                    sorter4.setRowFilter(RowFilter.regexFilter(text));
                }
            }
       }
    public void AdditionClient(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(600,500);
        dialog.setTitle("Добавление клиента");
        dialog.setSize(400,200);
        GridLayout glA = new GridLayout(7,2,3,3 );
        JPanel add=new JPanel(glA);
        JLabel name=new JLabel("  Имя клиента");
        JLabel phone=new JLabel("  Номер телефона");
        JLabel car= new JLabel("   Номер машины");
        JTextField Name = new JTextField(50);
        JTextField Phone = new JTextField(20);
        JTextField Car = new JTextField(10);
        JButton Add = new JButton("Добавить");
        add.add(name); add.add(Name); add.add(phone);add.add(Phone); add.add(car); add.add(Car);
        add.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { int id=tbclients.getRowCount()+1;
                    if (Name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Введите имя клиента", "Error", JOptionPane.ERROR_MESSAGE);
                   }
                    else {
                        cmodel.add(new Clients(id, Name.getText(), Phone.getText(), Car.getText()));
                        Name.setText(null);
                        Phone.setText(null);
                        Car.setText(null);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void UpdatingClient(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(500,400);
        dialog.setTitle("Изменить данные о клиенте");
        dialog.setSize(400,200);
        GridLayout glA = new GridLayout(7,2,3,3 );
        JPanel add=new JPanel(glA);
        Clients clients = cmodel.getClient(tbclients.getSelectedRow());
        JLabel name=new JLabel("  Имя клиента");
        JLabel phone=new JLabel("  Номер телефона");
        JLabel car= new JLabel("   Номер машины");
        JTextField Name = new JTextField(50);  Name.setText(clients.getName());
        JTextField Phone = new JTextField(20); Phone.setText(clients.getPhone());
        JTextField Car = new JTextField(10); Car.setText(clients.getCar());
        JButton Upd = new JButton("Изменить");
        add.add(name); add.add(Name); add.add(phone);add.add(Phone); add.add(car); add.add(Car);
        add.add(Upd);
        Upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { int id = tbclients.getSelectedRow();
                    if (Name.getText().equals("")){
                        JOptionPane.showMessageDialog(MainWindow.this, "Введите имя клиента", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else { cmodel.updateCl(id+1, new Clients(id,Name.getText(), Phone.getText(), Car.getText()));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void AdditionEmp(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(500,400);
        dialog.setTitle("Добавление работника");
        dialog.setSize(400,200);
        GridLayout glA = new GridLayout(5,2,3,3 );
        JPanel add=new JPanel(glA);
        JLabel name=new JLabel("  Имя работника");
        JLabel job=new JLabel("  Должность");
        JTextField Name = new JTextField(50);
        JTextField Job = new JTextField(70);

        JButton Add = new JButton("Добавить");
        add.add(name); add.add(Name); add.add(job);add.add(Job);
        add.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { int id=tbemployees.getRowCount()+1;
                    if (Name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(Job.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{emodel.add(new Employees(id, Name.getText(), Job.getText()));
                        Name.setText(null);
                        Job.setText(null);}
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void UpdatingEmps(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(500,400);
        dialog.setTitle("Изменить данные о работнике");
        dialog.setSize(400,200);
        GridLayout glA = new GridLayout(5,2,3,3 );
        JPanel add=new JPanel(glA);
        Employees employees=emodel.getEmp(tbemployees.getSelectedRow());
        JLabel name=new JLabel("  Имя работника");
        JLabel job=new JLabel("  Должность");
        JTextField Name = new JTextField(50);  Name.setText(employees.getName());
        JTextField Job = new JTextField(20); Job.setText(employees.getJob());

        JButton Upd = new JButton("Изменить");
        add.add(name); add.add(Name); add.add(job);add.add(Job);
        add.add(Upd);
        Upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { int id = tbemployees.getSelectedRow();
                    if (Name.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(Job.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                 else {
                     emodel.updateEm(id+1,new Employees(id,Name.getText(), Job.getText()));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void AdditionWork(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(700,400);
        dialog.setTitle("Добавление услуги");
        dialog.setSize(400,200);
        GridLayout glA = new GridLayout(7,2,3,3 );
        JPanel add=new JPanel(glA);
        JLabel title=new JLabel("  Наименование услуги");
        JLabel hours=new JLabel("  Время выполнения");
        JLabel price=new JLabel("  Цена");
        JTextField Title = new JTextField(50);
        JTextField Hours = new JTextField(70);
        JTextField Price = new JTextField(70);
        JButton Add = new JButton("Добавить");
        add.add(title); add.add(Title); add.add(hours);add.add(Hours);add.add(price);add.add(Price);
        add.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { int id=tbworks.getRowCount()+1;
                    if((Title.getText().equals(""))||(Hours.getText().equals(""))||(Price.getText().equals(""))){
                        JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                         try{
                             Float.parseFloat(Hours.getText());
                             Float.parseFloat(Price.getText());
                             wmodel.add(new Works(id,Title.getText(),Float.parseFloat(Hours.getText()), Float.parseFloat(Price.getText())));
                             Title.setText(null);
                             Hours.setText(null);
                             Price.setText(null);
                         }
                         catch (NumberFormatException ex){JOptionPane.showMessageDialog(null, "Введите числа", "Error", JOptionPane.ERROR_MESSAGE);}
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void UpdatingWork(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(500,400);
        dialog.setTitle("Изменить данные о работнике");
        dialog.setSize(400,200);
        GridLayout glA = new GridLayout(7,2,3,3 );
        JPanel add=new JPanel(glA);
        Works works=wmodel.getWork(tbworks.getSelectedRow());
        JLabel title=new JLabel("  Наименование услуги");
        JLabel hours=new JLabel("  Время выполнения");
        JLabel price=new JLabel("  Цена");
        JTextField Title = new JTextField(50);  Title.setText(works.getTitle());
        JTextField Hours = new JTextField(70);  Hours.setText(String.valueOf(works.getHours()));
        JTextField Price = new JTextField(70);   Price.setText(String.valueOf(works.getPrice()));
        JButton Upd = new JButton("Изменить");
        add.add(title); add.add(Title); add.add(hours);add.add(Hours);add.add(price);add.add(Price);
        add.add(Upd);
        Upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try { int id = tbworks.getSelectedRow();
                    if((Title.getText().equals(""))||(Hours.getText().equals(""))||(Price.getText().equals(""))){
                        JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        try{
                            Float.parseFloat(Hours.getText());
                            Float.parseFloat(Price.getText());
                            wmodel.updateW(id+1, new Works(id,Title.getText(), Float.parseFloat(Hours.getText()), Float.parseFloat(Price.getText())));
                        }
                        catch (NumberFormatException ex){JOptionPane.showMessageDialog(null, "Введите числа", "Error", JOptionPane.ERROR_MESSAGE);}
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void AdditionOrder() throws SQLException {

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(600,600);
        dialog.setTitle("Добавление текущего заказа");
        dialog.setSize(500,400);
        GridLayout glA = new GridLayout(9,2,30,3 );
        JPanel add=new JPanel(glA);
        JLabel title=new JLabel("  Услуга");
        JLabel emp=new JLabel("  Исполнитель");
        JLabel comment=new JLabel("  Комментарий");
        JLabel car= new JLabel("   Номер машины");
        JTextField Comment = new JTextField(100);
        JComboBox works = new JComboBox(DBWorker.getAllworksTitles());
        JComboBox emps = new JComboBox(DBWorker.getAllempsName());
        JComboBox cars = new JComboBox(DBWorker.getAllcars());
        JButton Add = new JButton("Добавить");
        add.add(title); add.add(works); add.add(emp); add.add(emps); add.add(car); add.add(cars); add.add(comment); add.add(Comment);
        add.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=tborders.getRowCount()+1;

                try { omodel.add(new Orders(id, (String)works.getSelectedItem(), (String)emps.getSelectedItem(), (String)cars.getSelectedItem(), Comment.getText()));
                    Comment.setText(null);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
    public void UpdatingOrder() throws SQLException {

        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(600,600);
        dialog.setTitle("Изменение текущего заказа");
        dialog.setSize(500,400);
        GridLayout glA = new GridLayout(9,2,30,3 );
        JPanel add=new JPanel(glA);
        JLabel title=new JLabel("  Услуга");
        JLabel emp=new JLabel("  Исполнитель");
        JLabel comment=new JLabel("  Комментарий");
        JLabel car= new JLabel("   Номер машины");
        JTextField Comment = new JTextField(100);
        Orders orders = omodel.getOrder(tborders.getSelectedRow());
        JComboBox works = new JComboBox(DBWorker.getAllworksTitles()); works.setSelectedItem(orders.getWork());
        JComboBox emps = new JComboBox(DBWorker.getAllempsName()); emps.setSelectedItem(orders.getEmp());
        JComboBox cars = new JComboBox(DBWorker.getAllcars()); cars.setSelectedItem(orders.getCar()); Comment.setText(orders.getComment());
        JButton Add = new JButton("Изменить");
        add.add(title); add.add(works); add.add(emp); add.add(emps); add.add(car); add.add(cars);add.add(comment); add.add(Comment);
        add.add(Add);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id=tborders.getSelectedRow();
                try { omodel.updateO(id+1, new Orders(id, (String)works.getSelectedItem(), (String)emps.getSelectedItem(), (String)cars.getSelectedItem(), Comment.getText()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        dialog.add(add);
        dialog.setVisible(true);
    }
}
}
