package com.javaman.demo.jms;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author chx
 * @description com.javaman.demo.jms
 * @date 2018/8/30
 */
public class QueueConsumer {
    public static void main(String[] args) throws JMSException, IOException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.218.128:61616");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage msg = (TextMessage) message;
                try {
                    System.out.println(msg.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }
}
