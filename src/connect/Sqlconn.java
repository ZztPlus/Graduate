package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sqlconn {
    private static Connection connection;

    static {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");
            String url ="jdbc:Access:///D:\\zztSQL\\user.accdb";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SQL connect fail");
        }
    }


    public static Connection getConnection() {
        return connection;
    }

    //连接部分测试
    /*public static void main(String[] args){
        connect.Sqlconn sqlconn=new connect.Sqlconn();
        sqlconn.init();
    }*/

}
