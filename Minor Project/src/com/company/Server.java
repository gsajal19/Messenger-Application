package com.company;

import java.sql.*;
import javax.sql.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.DataOutputStream;
import java.net.*;
import java.awt.*;
import java.io.DataInputStream;
import java.lang.*;
import java.awt.event.*;

public class Server implements ActionListener {

    JFrame frame;
    JPanel Top;
    JPanel Down;
    JPanel Right;
    JPanel Left;

    ServerSocket servers;
    Socket client1;
    DataInputStream client1input;
    DataOutputStream serveroutput;
    String str1 = " ";
    Connection con;
    Statement st;

    JButton connected;
    JButton send;
    JButton disconnect;

    JTextArea text;

    JLabel name;
    JLabel age;
    JLabel clientname;
    JLabel clientage;
    JLabel server;
    JLabel serverip;
    JLabel sample;
    JLabel contributors;
    JLabel cname1;
    JLabel cname2;
    JLabel cname3;
    JLabel cname4;
    JLabel cname5;
    JLabel sendtext;
    JLabel receivetext;
    JLabel sendbyserver;
    JLabel sendbyclient;
    JLabel status;

    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;
    JLabel l6;

    Border Tborder;
    Border Lborder;
    Border Dborder;
    Border Rborder;
    Border allside;

    String clientsname, clientsage;
    String sql = "insert into data(name,chat) value(";

    public static void main(String[] args) {
        Server obj = new Server();
        obj.display();
    }

    public void display() {

        // --------------FRAME & Panel

        // ----Object Of Frame & Panel

        Tborder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        Lborder = BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK);
        Dborder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
        Rborder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        allside = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);

        frame = new JFrame("Server");
        Top = new JPanel();
        Down = new JPanel();
        Right = new JPanel();
        Left = new JPanel();

        // -----Fixing on Display

        Top.setBounds(0, 0, 500, 60);
        Left.setBounds(0, 60, 100, 250);
        Down.setBounds(0, 310, 500, 55);
        Right.setBounds(100, 60, 400, 250);

        Top.setBorder(Tborder);
        Left.setBorder(Lborder);
        Down.setBorder(Dborder);
        Right.setBorder(Rborder);
        Top.setBackground(new Color(189, 195, 199));
        Left.setBackground(new Color(189, 195, 199));
        Down.setBackground(new Color(127, 140, 141));
        Right.setBackground(new Color(178, 190, 195));

        frame.add(Top);
        frame.add(Left);
        frame.add(Down);
        frame.add(Right);

        // --------Down Panel Component
        Down.setLayout(null);
        send = new JButton("Send");
        text = new JTextArea(" hi i am server");

        text.setBounds(5, 5, 370, 40);
        send.setBounds(380, 5, 100, 40);
        text.setBorder(allside);

        Font textareafont = text.getFont();
        text.setFont(textareafont.deriveFont(textareafont.getSize() + 2.0f));

        Down.add(text);
        Down.add(send);
        send.addActionListener(this);

        // Top Component Object

        Top.setLayout(null);
        connected = new JButton("Server Active");
        disconnect = new JButton("Disconnect");
        name = new JLabel("Name : ");
        age = new JLabel("Age : ");
        clientname = new JLabel("ClientName");
        clientage = new JLabel("xxx");
        server = new JLabel("Server IP : ");
        serverip = new JLabel("127.0.0.1");

        connected.setBackground(new Color(26, 188, 156));
        disconnect.setBackground(new Color(255, 118, 117));
        connected.setForeground(Color.WHITE);
        connected.setEnabled(true);

        connected.setBounds(345, 5, 125, 25);
        disconnect.setBounds(350, 30, 110, 25);
        name.setBounds(10, 10, 50, 20);
        clientname.setBounds(55, 10, 200, 20);
        clientage.setBounds(55, 20, 200, 40);
        age.setBounds(10, 20, 50, 40);
        server.setBounds(180, 15, 250, 35);
        serverip.setBounds(250, 15, 300, 35);

        Top.add(name);
        Top.add(age);
        Top.add(clientname);
        Top.add(clientage);
        Top.add(connected);
        Top.add(disconnect);
        disconnect.addActionListener(this);
        Top.add(server);
        Top.add(serverip);

        // ------Left

        Left.setLayout(null);

        sample = new JLabel("Server");
        contributors = new JLabel("Designed By");
        cname1 = new JLabel("~ Sajal");
        cname2 = new JLabel("");
        cname3 = new JLabel("");
        cname4 = new JLabel("");
        cname5 = new JLabel("");
        sample.setFont(new Font("Tahoma", Font.BOLD, 14));
        sample.setBounds(30, 60, 90, 20);
        contributors.setBounds(5, 130, 90, 20);
        cname1.setBounds(10, 150, 100, 15);
        cname2.setBounds(10, 165, 100, 15);
        cname3.setBounds(10, 180, 100, 15);
        cname4.setBounds(10, 195, 100, 15);
        cname5.setBounds(10, 210, 100, 15);
        Left.add(sample);
        Left.add(contributors);
        Left.add(cname1);
        Left.add(cname2);
        Left.add(cname3);
        Left.add(cname4);
        Left.add(cname5);

        // --------Right

        Right.setLayout(null);
        receivetext = new JLabel("Start");
        sendtext = new JLabel("send here");
        sendbyclient = new JLabel("Client: / ");
        sendbyserver = new JLabel("Server: / ");
        status = new JLabel("Status: ");
        status.setBounds(100, 10, 200, 20);

        sendbyclient.setBounds(10, 50, 50, 20);
        sendbyserver.setBounds(10, 100, 50, 20);
        receivetext.setBounds(60, 50, 200, 20);
        sendtext.setBounds(60, 100, 200, 20);

        sendbyclient.setForeground(new Color(231, 76, 60));
        sendbyserver.setForeground(new Color(142, 68, 173));

        l6 = new JLabel("");
        l5 = new JLabel("");
        l4 = new JLabel("");
        l3 = new JLabel("");
        l2 = new JLabel("");
        l1 = new JLabel("");
        l6.setBounds(10, 35, 200, 30);
        l5.setBounds(10, 65, 200, 30);
        l4.setBounds(10, 95, 200, 30);
        l3.setBounds(10, 125, 200, 30);
        l2.setBounds(10, 155, 200, 30);
        l1.setBounds(10, 185, 200, 30);

        Right.add(l1);
        Right.add(l2);
        Right.add(l3);
        Right.add(l4);
        Right.add(l5);
        Right.add(l6);
        Right.add(receivetext);
        Right.add(sendtext);
        Right.add(sendbyclient);
        Right.add(sendbyserver);
        Right.add(status);
        receivetext.setVisible(false);
        sendtext.setVisible(false);
        sendbyclient.setVisible(false);
        sendbyserver.setVisible(false);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minor", "root", "");
            st = con.createStatement();

        } catch (Exception dr) {
            System.out.print("DataBase Fault");
        }
        /* -------------Main WORKING OF PROGRAM------------ */
        try {

            servers = new ServerSocket(3303);
            status.setText("Status: / Connecting...");
            client1 = servers.accept();

            status.setText("Status: / Connected");
            Thread.sleep(2000);
            status.setText("Status: / User Active");
            serveroutput = new DataOutputStream(client1.getOutputStream()); // Dataout
            client1input = new DataInputStream(client1.getInputStream());
            clientsname = (String) client1input.readUTF();
            clientname.setText(clientsname);
            sendbyclient.setText(clientsname + ": / ");
            client1input = new DataInputStream(client1.getInputStream());
            clientsage = (String) client1input.readUTF();
            clientage.setText(clientsage);

            while (str1 != "Disconnected") {
                client1input = new DataInputStream(client1.getInputStream());

                str1 = (String) client1input.readUTF();
                l6.setText(l5.getText());
                l5.setText(l4.getText());
                l4.setText(l3.getText());
                l3.setText(l2.getText());
                l2.setText(l1.getText());
                l1.setText(clientsname + " : / " + str1);
                try {
                    st.executeUpdate("insert into data(name,chat) value('" + clientsname + "','" + str1 + "')");

                } catch (Exception vv) {
                    status.setText("Status: / DataBase not connected");
                }

            }
            if (str1 == "Disconnected") {
                status.setText("Status: / User Disconnected");
                con.close();
                send.setEnabled(false);
            }
        } catch (Exception e) {
            status.setText("Status: / User Disconnected");
            try {
                con.close();
            } catch (Exception close) {
            }
            send.setEnabled(false);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();

        if (ob == (Object) disconnect) {
            try {
                connected.setText("Server InActive");
                connected.setBackground(new Color(235, 77, 75));
                send.setEnabled(false);
                disconnect.setEnabled(false);
                serveroutput.writeUTF("Disconnected");
                serveroutput.flush();
                sendtext.setText("Disconnected");
                client1.close();
                servers.close();
                con.close();
                con.close();
                status.setText("Status: / Disconnected");

            } catch (Exception p) {
                status.setText("Status: / User Already Disconnected");
                try {
                    con.close();
                } catch (Exception close) {
                }
            }

        }
        if (ob == (Object) send) {
            try {
                String temp;
                temp = text.getText();
                text.setText("");
                temp = temp.trim();
                serveroutput.writeUTF(temp);
                serveroutput.flush();

                l6.setText(l5.getText());
                l5.setText(l4.getText());
                l4.setText(l3.getText());
                l3.setText(l2.getText());
                l2.setText(l1.getText());

                l1.setText("Me : / " + temp);

                st.executeUpdate("insert into data(name,chat) value('Server','" + temp + "')");

            } catch (Exception z) {
                sendtext.setText("Error");
            }
        }
    }

}
