package ui;

import connect.Sqluser;
import info.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Widow extends JFrame {
    private JButton enterButton;
    private JButton rejisterButton;
    private JButton rewrite;
    private JTextField usernumber;
    private JTextField userid;
    private Sqluser sqluser =new Sqluser();
    private User usertopic =new User();

    public Widow (){                                                   //登陆窗口
       init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addListener();


    }

    private void init(){
        setTitle("毕业论文管理系统");
        setBounds(600,300,600,450);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(600,300,600,450));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        JPanel panel= new JPanel();
        JLabel usernamelabel=new JLabel("用户名");
        usernamelabel.setBounds(150,78,100,30);
        panel.add(usernamelabel);
        JLabel passwordLabel = new JLabel("密  码");
        passwordLabel.setBounds(150,124,100,30);
        panel.add(passwordLabel);
        BufferedImage img =null;
        try{
            img = ImageIO.read(new File("./imgs/6.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getContentPane().setLayout(null);
        JLabel label=new JLabel(new ImageIcon(img));
        getContentPane().add(label);
        label.setBounds(0,0,600,450);


        Color color1=new Color(158, 182, 255);                           //拙略的方法拼接起来的背景色
        contentPane.setBackground(color1);                                         //拙略的方法拼接起来的背景色
        Color color2=new Color(255, 87, 62);
        panel.setBackground(color2);                                              //拙略的方法拼接起来的背景色
        panel.setBounds(0,80,3230,1710);
        contentPane.add(panel);
        panel.setLayout(null);
        JLabel title=new JLabel("欢迎使用毕业论文管理系统(学生端)");
        title.setBounds(220,-280,400,600);
        panel.add(title);
        //JLabel usernamelabel=new JLabel("用户名");
        //usernamelabel.setBounds(150,78,100,30);
        //panel.add(usernamelabel);
        userid = new JTextField();
        userid.setBounds(230,75,150,30);
        panel.add(userid);
        userid.setColumns(10);
        //JLabel passwordLabel = new JLabel("密  码");
       // passwordLabel.setBounds(150,124,100,30);
        //panel.add(passwordLabel);
        usernumber = new JTextField();
        usernumber.setBounds(230,130,150,30);
        panel.add(usernumber);
        usernumber.setColumns(10);
        enterButton = new JButton("登录");                              //登陆按钮的名字
        enterButton.setBounds(130,190,150,40);
        panel.add(enterButton);
        rejisterButton = new JButton("注册账号");                              //退出按钮的名字
        rejisterButton.setBounds(310,190,150,40);
        panel.add(rejisterButton);
        rewrite = new JButton("忘记密码");                              //登陆按钮的名字
        rewrite.setBounds(400,116,100,30);
        panel.add(rewrite);
    }

    private void addListener(){
        enterButton.addActionListener(new ActionListener() {                         //监听登陆按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                String a=userid.getText();
                String b =usernumber.getText();
                User c = sqluser.select(a);
                if (b.equals(c.getNumber())){
                    JOptionPane.showMessageDialog(Widow.this, "登陆成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    Manage manage=new Manage(a);
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(Widow.this, "账号或密码不正确", "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        rejisterButton.addActionListener(new ActionListener() {                     //监听注册按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                String a=userid.getText();
                String b=usernumber.getText();
                User c= sqluser.select(a);
                usertopic.setId(a);
                usertopic.setNumber(b);
                if (a.equals(c.getId())) {
                    JOptionPane.showMessageDialog(Widow.this, "该用户已被注册", "错误", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    sqluser.insert(usertopic);
                    JOptionPane.showMessageDialog(Widow.this, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        rewrite.addActionListener(new ActionListener() {                         //监听修改密码
            @Override
            public void actionPerformed(ActionEvent e) {
                String a=userid.getText();
                String b=usernumber.getText();
                User c= sqluser.select(a);
                usertopic.setId(a);
                usertopic.setNumber(b);
                if (a.equals(c.getId())) {
                    sqluser.updatenumber(usertopic);
                    JOptionPane.showMessageDialog(Widow.this, "修改成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(Widow.this, "用户名错误，修改失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }






    //主界面部分测试
    public static void main(String[] args){
        Widow widow= new Widow();
    }
}