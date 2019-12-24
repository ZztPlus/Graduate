package ui;

import connect.Sqlstudent;
import connect.Sqltopic;
import info.Student;
import info.Topic;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class Manage extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField aTextField;
    private JTextField btextField;
    private JTextField ctextField;


    //private JLabel l1;
   // private JLabel l2;
    private JButton b1;
    private JButton b2;
    //private connect.Sqluser sqluser = new connect.Sqluser();
    //private user_topic usertopic = new user_topic();
    //JFrame frame = new JFrame("欢迎使用论文管理软件");
    //Container container = frame.getContentPane();

    private Sqltopic sqltopic = new Sqltopic();
    private Sqlstudent sqlstudent = new Sqlstudent();
    private String[] columnNames = {"课程名称", "授课老师", "课程编号"};
    private String id;
    private List<Topic> list;

    public Manage(String id) {

        super();
        this.id = id;
        setTitle("毕业论文管理系统");
        setBounds(100, 100, 1000, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        String[][] rowValues = null;
        tableModel = new DefaultTableModel();
        tableModel.setDataVector(rowValues, columnNames);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int type = e.getType();
                int row = e.getFirstRow() + 1;
                int column = e.getColumn() + 1;
                if (type == TableModelEvent.INSERT) {
                    System.out.println("由插入行触发");
                    System.out.println("触发" + row);

                } else if (type == TableModelEvent.UPDATE) {
                    System.out.println("由修改行触发");
                    System.out.println("触发" + row);
                } else if (type == TableModelEvent.DELETE) {
                    System.out.println("由删除行触发");
                    System.out.println("触发" + row);

                } else {
                    System.out.println("由未知触发");
                }

            }
        });

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        final JLabel aLabel = new JLabel("课程名称");
        panel.add(aLabel);
        aTextField = new JTextField(15);
        panel.add(aTextField);
        final JLabel bLabel = new JLabel("教师姓名");
        panel.add(bLabel);
        btextField = new JTextField(15);
        panel.add(btextField);
        final JLabel cLabel = new JLabel("课程编号");
        panel.add(cLabel);
        ctextField = new JTextField(15);
        panel.add(ctextField);
        final JButton addButton = new JButton("选该论文");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = table.getSelectedRow();
                Student student = new Student();
                student.setId(id);
                student.setTm(list.get(selected).getClassnumber());
                sqlstudent.updatestudent(student);
            }
        });
        panel.add(addButton);
        final JButton selectButton = new JButton("查询已选");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            //int select= table.getSelectedRow();
            Student student=new Student();
            String  select=student.getTm();
            Topic topic = sqltopic.tm_select();
            aTextField.setText(topic.getSubject());
            btextField.setText(topic.getTeacher());
            ctextField.setText(topic.getClassnumber());
                /*
                int[] selectedRows = table.getSelectedRows();
                for (int row = 0; row < selectedRows.length; row++) {
                    tableModel.removeRow(selectedRows[row] - row);
                }*/
            }
        });
        panel.add(selectButton);

        addData();
        Frame frame=new Frame();
    }

    private void addData() {
        list = sqltopic.select();
        Object[][] objects = new Object[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Object subject = new Object();
            subject = list.get(i).getSubject();
            Object teacher = new Object();
            teacher = list.get(i).getTeacher();
            Object classnumber = new Object();
            classnumber = list.get(i).getClassnumber();
            objects[i][0] = subject;
            objects[i][1] = teacher;
            objects[i][2] = classnumber;
        }

        tableModel.setDataVector(objects, columnNames);
    }


    /*public static void main(String[] args) {
        ui.Manage frame = new ui.Manage();
        frame.setVisible(true);
    }*/
}