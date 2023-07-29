import javax.swing.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.net.*;
import java.net.Socket;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.EventListener;


public class Swing implements ActionListener {
    JFrame frame;

    JPanel Top ;
    JPanel Down ;
    JPanel Right;
    JPanel Left ;

    JButton send   ;
    JButton disconnect;

    JTextArea text  ;

    JLabel name       ;
    JLabel age        ;
    JLabel clientname ;
    JLabel clientage  ;
    JLabel server     ;
    JLabel serverip   ;
    JLabel sample       ;
    JLabel contributors ;
    JLabel cname1       ;
    JLabel cname2       ;
    JLabel cname3       ;
    JLabel cname4       ;
    JLabel cname5       ;
    JLabel status;
    JLabel sendtext;
    JLabel receivetext;
    JLabel sendbyserver;
    JLabel sendbyclient;

    DataOutputStream out;
    Socket ss;

    public static void main(String[] args) {
        Swing obj = new Swing();
        obj.design();
    }
    public void design(){


        Border Tborder = BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK);
        Border Lborder = BorderFactory.createMatteBorder(1,0,0,1,Color.BLACK);
        Border Dborder = BorderFactory.createMatteBorder(1,0,0,0,Color.BLACK);
        Border Rborder = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);
        Border allside = BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK);

        frame = new JFrame("Client");
        Top   = new JPanel();
        Down  = new JPanel();
        Right = new JPanel();
        Left  = new JPanel();

        //-----Fixing on Display

        Top.setBounds(0,0,500,60);
        Left.setBounds(0,60,100,250);
        Down.setBounds(0,310,500,55);
        Right.setBounds(100,60,400,250);

        Top.setBorder(Tborder);
        Left.setBorder(Lborder);
        Down.setBorder(Dborder);
        Right.setBorder(Rborder);
        Top.setBackground(new Color(189,195,199));
        Left.setBackground(new Color(189,195,199));
        Down.setBackground(new Color(127,140,141));
        Right.setBackground(new Color(178,190,195));

        frame.add(Top);
        frame.add(Left);
        frame.add(Down);
        frame.add(Right);


        //--------Down Panel Component
        Down.setLayout(null);
        send   = new JButton("Send");
        text = new JTextArea(" Typing....");

        text.setBounds(5,5,370,40);
        send.setBounds(380,5,100,40);
        text.setBorder(allside);


        Font textareafont = text.getFont();
        text.setFont(textareafont.deriveFont(textareafont.getSize()+2.0f));

        Down.add(text);
        Down.add(send);
        send.addActionListener(this);


        //Top Component Object

        Top.setLayout(null);
        disconnect    = new JButton("Disconnect");
        name          = new JLabel("Name : ");
        age           = new JLabel("Age : ");
        clientname    = new JLabel("ClientName");
        clientage     = new JLabel("xxx");
        server        = new JLabel("Server IP : ");
        serverip      = new JLabel("0.0.0.0");

        disconnect.setBackground(Color.RED);
        disconnect.setForeground(Color.WHITE);
        disconnect.setEnabled(true);

        disconnect.setBounds(380,10,100,30);
        name.setBounds(10,10,50,20);
        clientname.setBounds(55,10,200,20);
        clientage.setBounds(55,20,200,40);
        age.setBounds(10,20,50,40);
        server.setBounds(180,15,250,35);
        serverip.setBounds(250,15,300,35);

        Top.add(name);
        Top.add(age);
        Top.add(clientname);
        Top.add(clientage);
        Top.add(disconnect);
        disconnect.addActionListener(this);
        Top.add(server);
        Top.add(serverip);


        //------Left

        Left.setLayout(null);

        sample       = new JLabel("Client1");
        contributors = new JLabel("Contributed By");
        cname1       = new JLabel("~ Sajal");
        cname2       = new JLabel("~ Piyush");
        cname3       = new JLabel("~ Priyanka");
        cname4       = new JLabel("~ Madhur");
        cname5       = new JLabel("~ Varsha");
        sample.setFont(new Font("Tahoma",Font.BOLD,14));
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

        //--------Right
        Right.setLayout(null);
        sendtext=new JLabel("");
        sendbyclient=new JLabel("Client: / ");
        sendbyserver=new JLabel("Server: / ");
        status = new JLabel("Status: ");
        status.setBounds(100, 10, 200, 20);

        sendbyserver.setBounds(10, 50, 50, 20);
        sendbyclient.setBounds(10, 100, 50, 20);
        sendtext.setBounds(60, 100, 200, 20);




        sendbyclient.setForeground(new Color(142,68,173));
        sendbyserver.setForeground(new Color(231,76,60));
        Right.add(status);
        Right.add(sendtext);
        Right.add(sendbyclient);
        Right.add(sendbyserver);




        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500,400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InetAddress serverips;
        /* -------------Main WORKING OF PROGRAM------------*/
        try{
            ss = new Socket("localhost",3303);
            out = new DataOutputStream(ss.getOutputStream());

            serverips=InetAddress.getByName("localhost");
            StringBuffer c = new StringBuffer();

            serverip.setText(serverips.getHostAddress());
            status.setText("Status: / Server Active");

        }catch(Exception z){
            send.setEnabled(false);
            text.setEnabled(false);
            disconnect.setEnabled(false);
            serverip.setForeground(new Color(235,77,75));
            status.setText("Status: / Server offline");

        }
    }
    public void actionPerformed(ActionEvent e){
        Object ob = e.getSource();
        if(ob==(Object)send){
            try{


                String str;
                str=text.getText();
                str=str.trim();
                sendtext.setText(str);
                out.writeUTF(str);
                out.flush();

            }
            catch(Exception eu){
                send.setEnabled(false);
                status.setText("Status: / Server InActive");

            }

        }
        if(ob==(Object)disconnect){
            try{

                status.setText("Status: / Disconnected");
                String st="Disconnected";
                sendtext.setText(st);
                out.writeUTF(st);
                out.flush();
                send.setEnabled(false);
                text.setEditable(false);

                ss.close();
            }catch(Exception z){
                status.setText("Status: / Fault");
            }
        }

    }

}
