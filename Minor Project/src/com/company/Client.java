package com.company;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.net.*;
import java.net.Socket;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Client implements ActionListener {
    JFrame frame;

    JPanel Top;
    JPanel Down;
    JPanel Right;
    JPanel Left;
    JPanel Center;

    JButton send;
    JButton disconnect;
    JButton enter;
    JTextArea text;
    JTextArea usersname;
    JTextArea usersage;

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
    JLabel status;
    JLabel sendtext;
    JLabel receivetext;
    JLabel sendbyserver;
    JLabel sendbyclient;
    JLabel l1, l2, l3, l4, l5, l6;

    DataOutputStream out;
    DataInputStream inp;
    Socket ss;

    String getname, getage;
    boolean st = false;

    public static void main(String[] args) {
        Client obj = new Client();
        obj.design();
    }

    public void design() {

        Border Tborder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);
        Border Lborder = BorderFactory.createMatteBorder(1, 0, 0, 1, Color.BLACK);
        Border Dborder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK);
        Border Rborder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
        Border allside = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);

        frame = new JFrame("Client");
        Top = new JPanel();
        Down = new JPanel();
        Right = new JPanel();
        Left = new JPanel();
        Center = new JPanel();

        // Panels Visibility

        Top.setBounds(0, 0, 500, 60);
        Left.setBounds(0, 60, 100, 250);
        Down.setBounds(0, 310, 500, 55);
        Right.setBounds(100, 60, 400, 250);
        Center.setBounds(0, 0, 500, 400);

        Top.setBorder(Tborder);
        Left.setBorder(Lborder);
        Down.setBorder(Dborder);
        Right.setBorder(Rborder);
        Top.setBackground(new Color(189, 195, 199));
        Left.setBackground(new Color(189, 195, 199));
        Down.setBackground(new Color(127, 140, 141));
        Right.setBackground(new Color(178, 190, 195));
        Center.setBackground(new Color(0, 155, 213));

        frame.add(Top);
        frame.add(Left);
        frame.add(Down);
        frame.add(Right);
        frame.add(Center);

        Top.setVisible(false);
        Down.setVisible(false);
        Left.setVisible(false);
        Right.setVisible(false);

        // Center Design

        enter = new JButton("Enter");
        JLabel titlename = new JLabel("Name : ");
        JLabel titleage = new JLabel("Age : ");
        usersname = new JTextArea("");
        usersage = new JTextArea("");

        titlename.setBounds(100, 100, 100, 30);
        titlename.setFont(new Font("Serif", Font.BOLD, 16));
        titlename.setForeground(new Color(0, 0, 0));
        titleage.setBounds(100, 150, 100, 30);
        titleage.setFont(new Font("Serif", Font.BOLD, 16));
        titleage.setForeground(new Color(0, 0, 0));
        usersname.setBounds(200, 100, 150, 30);
        usersage.setBounds(200, 150, 150, 30);
        enter.setBounds(160, 230, 100, 30);
        // enter.setBackground(new Color(89, 236, 216, 255));
        enter.setForeground(new Color(0, 155, 213));
        enter.setFont(new Font("Serif", Font.BOLD, 16));
        Font t1 = usersname.getFont();
        Font t2 = usersage.getFont();

        usersname.setFont(new Font("Serif", Font.ITALIC, 16));
        usersage.setFont(new Font("Serif", Font.ITALIC, 16));

        usersname.setMargin(new Insets(2, 3, 2, 2));
        usersage.setMargin(new Insets(2, 3, 2, 2));

        Center.setLayout(null);
        Center.add(usersname);
        Center.add(usersage);
        Center.add(titlename);
        Center.add(titleage);
        Center.add(enter);
        enter.addActionListener(this);

        // --------Down Panel Component
        Down.setLayout(null);
        send = new JButton("Send");
        text = new JTextArea("hi i am ");

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
        disconnect = new JButton("Disconnect");
        name = new JLabel("Name : ");
        age = new JLabel("Age : ");
        clientname = new JLabel("ClientName");
        clientage = new JLabel("xxx");
        server = new JLabel("Server IP : ");
        serverip = new JLabel("0.0.0.0");

        disconnect.setBackground(Color.RED);
        disconnect.setForeground(Color.WHITE);
        disconnect.setEnabled(true);

        disconnect.setBounds(380, 10, 100, 30);
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
        Top.add(disconnect);
        disconnect.addActionListener(this);
        Top.add(server);
        Top.add(serverip);

        // ------Left

        Left.setLayout(null);

        sample = new JLabel("Client1");
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

        Right.setLayout(null);
        sendtext = new JLabel("");
        receivetext = new JLabel("");
        sendbyclient = new JLabel("You: / ");
        sendbyserver = new JLabel("Server: / ");
        status = new JLabel("Status: ");
        status.setBounds(100, 10, 200, 20);

        sendbyserver.setBounds(10, 50, 50, 20);
        sendbyclient.setBounds(10, 100, 50, 20);
        sendtext.setBounds(60, 100, 200, 20);
        receivetext.setBounds(60, 50, 200, 20);

        sendbyclient.setForeground(new Color(142, 68, 173));
        sendbyserver.setForeground(new Color(231, 76, 60));
        Right.add(status);
        Right.add(sendtext);
        Right.add(receivetext);
        sendtext.setVisible(false);
        receivetext.setVisible(false);
        // Right.add(sendbyclient);
        // Right.add(sendbyserver);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InetAddress serverips;
        /* -------------Main WORKING OF PROGRAM------------ */
        try {
            ss = new Socket("localhost", 3303);
            out = new DataOutputStream(ss.getOutputStream());
            inp = new DataInputStream(ss.getInputStream());
            serverips = InetAddress.getByName("localhost");
            StringBuffer c = new StringBuffer();
            serverip.setText(serverips.getHostAddress());
            serverip.setForeground(new Color(231, 76, 60));
            status.setText("Status: / Server Active");
            String zz = " ";
            while (zz != "Disconnected") {
                zz = (String) inp.readUTF();
                zz = zz.trim();
                if (zz != "Disconnected") {
                    l6.setText(l5.getText());
                    l5.setText(l4.getText());
                    l4.setText(l3.getText());
                    l3.setText(l2.getText());
                    l2.setText(l1.getText());
                    l1.setText("Server : / " + zz);

                } else
                    status.setText("Status: / Server InActive");
            }

        } catch (Exception z) {
            send.setEnabled(false);
            text.setEnabled(false);
            disconnect.setEnabled(false);
            serverip.setForeground(new Color(235, 77, 75));
            status.setText("Status: / Server offline");

        }

    }

    public void actionPerformed(ActionEvent e) {
        Object ob = e.getSource();
        if (ob == (Object) send) {
            try {
                String str;
                str = text.getText();
                text.setText("");
                str = str.trim();
                sendtext.setText(str);
                out.writeUTF(str);
                out.flush();
                l6.setText(l5.getText());
                l5.setText(l4.getText());
                l4.setText(l3.getText());
                l3.setText(l2.getText());
                l2.setText(l1.getText());

                l1.setText("Me : / " + str);

            } catch (Exception eu) {
                send.setEnabled(false);
                status.setText("Status: / Server InActive");

            }

        }
        if (ob == (Object) disconnect) {
            try {

                status.setText("Status: / Disconnected");
                String st = "Disconnected";
                sendtext.setText(st);
                out.writeUTF(st);
                out.flush();
                send.setEnabled(false);
                text.setEditable(false);

                ss.close();
            } catch (Exception z) {
                status.setText("Status: / Fault");
            }
        }
        if (ob == (Object) enter) {
            getage = (String) usersage.getText();
            getname = (String) usersname.getText();
            if (getage == null || getname == null || getage == "" || getname == " ") {

            } else {

                Top.setVisible(true);
                Down.setVisible(true);
                Left.setVisible(true);
                Right.setVisible(true);
                Center.setVisible(false);
                try {
                    out.writeUTF(getname);
                    out.flush();
                    out.writeUTF(getage);
                    out.flush();
                } catch (Exception cx) {
                    System.out.println("Error Occurs");
                }
                clientname.setText(getname);
                clientage.setText(getage);

            }

        }

    }

}
