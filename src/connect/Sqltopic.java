package connect;

import info.Student;
import info.Topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;

public class Sqltopic {
    private Connection connection = Sqlconn.getConnection();
    public void insert(Topic topic) {             //添加课程
        String url = "insert into topic(subject,teacher,classnumber) values(?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(url);
            ps.setString(1, topic.getSubject());
            ps.setString(2, topic.getTeacher());
            ps.setString(3, topic.getClassnumber());
            ps.execute();
            System.out.println("添加结果：");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("添加失败");
        }
    }

    public void deltopic(Topic topic){         //删除课程
        String url="delete from topic where classnumber =? ";
        try{
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(1, topic.getClassnumber());
            ps.execute();
            System.out.println("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("删除失败");
        }
    }

    public void updatenumber(Topic topic){           //修改课程
        String url="update topic set Subject =? set teacher =? where classnumber =?";
        try{
            PreparedStatement ps= connection.prepareStatement(url);
            ps.setString(3, topic.getClassnumber());
            ps.setString(2, topic.getTeacher());
            ps.setString(1, topic.getSubject());
            ps.execute();
            System.out.println("修改结果：");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("修改失败");
        }
    }

    public List<Topic> select(){                          //查询课程
        try{
            String url="select*from topic";
            PreparedStatement ps= connection.prepareStatement(url);
            ResultSet set= ps.executeQuery();

            List<Topic> topicList = new LinkedList<Topic>();
            while(set.next()){
                Topic topic = new Topic();
                topic.setSubject(set.getString("subject"));
                topic.setTeacher(set.getString("teacher"));
                topic.setClassnumber(set.getString("classnumber"));
                topicList.add(topic);
            }
            System.out.println("查询运行");
            return topicList;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            return null;
        }
    }

    public Topic tm_select(){                          //查询课程
        try{
            String url="select topic.*,student.* from topic,student where student.tm=topic.classnumber";
            PreparedStatement ps= connection.prepareStatement(url);
            ResultSet set= ps.executeQuery();

            Topic topic = new Topic();
            while(set.next()){
                Student student=new Student();
                student.setTm(set.getString("tm"));
                topic.setSubject(set.getString("subject"));
                topic.setTeacher(set.getString("teacher"));
                topic.setClassnumber(set.getString("classnumber"));
                //topicList.add(topic);
            }
            System.out.println("查询运行");
            return topic;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败");
            return null;
        }
    }
}
