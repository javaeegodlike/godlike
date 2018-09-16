package com.javaman.demo.email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.omg.CORBA.PRIVATE_MEMBER;

public class EmailUtil {
    /***发件正文***/
    private static String link = "http://www.baihe.com/betatest/betatest_newlandpage.html";

    private static String getContent(String link){
        String content = "<h3>皮皮虾我们走，一起去找女朋友！</h3>"
                + "<a href='"+ link +"'>点此去找</a>";
        return content ;
    }

     /**
     * 给单个人或者多个人发送邮件（不带附件）
     * @param to 收件人邮箱，类似"XXX@163.com,xxx@qq.com"
     * @param content 邮件信息
     * @param host 邮件服务器地址  ，例如smtp.163.com
     * @param port 邮件服务器端口，例如smtp 25  pop3 110
     * @param from 发件人邮箱，类似XXX@163.com、xxx@qq.com
     * @param password 发件人邮箱的授权码，注意第三方登录邮箱使用的是授权码
     * @param sendName 发件人姓名
     * @param subject 主题
     * @throws UnsupportedEncodingException 
     */
    public static void sendEmail(String to, String content,String host,String port,String sendName, String from, String password,String subject) throws UnsupportedEncodingException {

        /*** 1、创建Session ***/
        Properties props = new Properties();
        // 开启调试模式
        props.setProperty("mail.debug", "true");
        // 发送主机需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器名称
        props.setProperty("mail.host", host);
        // 设置邮件服务器端口
        props.setProperty("mail.port", port);
        // 发送协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        final String  fromstr = from;
        final String passwordstr =password;
        // 环境信息
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 在session中设置账户信息，Transport发送邮件时会使用
                return new PasswordAuthentication( fromstr, passwordstr);
            }
        });
        /*** 2、创建邮件对象 ***/
        Message msg = new MimeMessage(session);
        try {
            // 设置发件人
            msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText(sendName) + "\" <"+from+">"));
            // 设置标题
            msg.setSubject(subject);
            // 设置内容
            msg.setContent(content, "text/html;charset=UTF-8");
            /*** 3、发送邮件 ***/
            Transport.send(msg, InternetAddress.parse(to) );

        } catch (MessagingException e) {
            System.out.println("发送邮件失败!!");
            e.printStackTrace();
        }
        System.out.println("不抛异常就是发送成功咯!!");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        sendEmail("javaeedeveloper@163.com,864098639@qq.com", getContent(link ),"smtp.163.com","25","ARES","chenhua141@163.com","Abcd1234","百合网");
    }
}