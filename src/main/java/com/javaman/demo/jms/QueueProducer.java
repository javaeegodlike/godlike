package com.javaman.demo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author chx
 * @description com.javaman.demo.jms
 * @date 2018/8/30
 * 生产者
 */
public class QueueProducer {

    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.218.128:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageProducer producer = session.createProducer(queue);
        TextMessage message = session.createTextMessage("欢迎来到众安保险");
        producer.send(message);
        producer.close();
        session.close();
        connection.close();
    }
}
