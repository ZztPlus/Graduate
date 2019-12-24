package connect;

import info.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Sqlstudent {

    private Connection connection;
    public Sqlstudent(){
        //if (connection != null) {
        // return this;
        //}
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");
            String url ="jdbc:Access:///D:\\zztSQL\\user.accdb";
            connection = DriverManager.getConnection(url);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接过程失败");
        }
    }

    public void insert(Student student) {             //添加学生
        String url = "insert into student(xm,tm,id) values(?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(url);
            ps.setString(1, student.getXm());
            ps.setString(2,student.getTm());
            ps.setString(3,student.getId());
            ps.execute();
            System.out.println("添加结果：");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    public void delstudent(Student student){         //删除学生
        String url="delete from student where id=? ";
        try{
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(1,student.getId());
            ps.execute();
            System.out.println("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    public void updatestudent(Student student){           //修改学生
        String url="update student set tm=? where id=?";
        try{
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(1,student.getTm());
            ps.setString(2,student.getId());
            ps.execute();
            System.out.println("修改结果：");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("修改失败");
        }
    }

    public List<Student> select(){                          //查询学生
        try{
            Statement statement=connection.createStatement();
            String url="select*from student";
            ResultSet set= statement.executeQuery(url);
            List<Student> list= new LinkedList<Student>();
            while(set.next()){
                Student student= new Student();
                student.setXm(set.getString("xm"));
                student.setTm(set.getString("tm"));
                student.setId(set.getString("id"));
                list.add(student);
            }
            System.out.println("查询结果：");
            return  list;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            return null;
        }
    }
}
