package connect;

import info.User;

import java.sql.*;
import java.sql.Connection;

public class Sqluser {
    //private connect.Sqlconn sqlconn = new connect.Sqlconn();
    private Connection connection = Sqlconn.getConnection();
    //private Connection connection;
    /*public Sqlwork init() {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");
            String url ="jdbc:Access:///D:\\zztSQL\\user.accdb";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SQL connect fail");
        }
        return this ;
    }*/
    public void insert(User usertopic) {             //添加账号密
        String url = "insert into user(id,number) values(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(url);
            ps.setString(1, usertopic.getId());
            ps.setString(2, usertopic.getNumber());
            ps.execute();
            System.out.println("添加结果：");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    public void deluser(User usertopic){         //删除学生
        String url="delete from user where id =? ";
        try{
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(1, usertopic.getId());
            ps.execute();
            System.out.println("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    public void updatenumber(User usertopic){           //修改学生
        String url="update user set number =? where id =?";
        try{
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(2, usertopic.getId());
            ps.setString(1, usertopic.getNumber());
            ps.execute();
            System.out.println("修改结果：");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("修改失败");
        }
    }

    public User select(String id){                          //查询学生
        try{
            User usertopic = new User();
            String url="select*from user where id=?";
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(1,id);
            ResultSet set= ps.executeQuery();
            while(set.next()){

                usertopic.setId(set.getString("id"));
                usertopic.setNumber(set.getString("number"));
            }
            System.out.println("查询运行");
            return usertopic;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            return null;
        }
    }
}

