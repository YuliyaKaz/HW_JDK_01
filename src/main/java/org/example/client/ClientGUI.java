package org.example.client;

import org.example.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int POS_X = 300;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final JTextField textServ = new JTextField("127.0.0.1");
    private static final JTextField textPort = new JTextField("8180");
    private static final JTextField textLogin = new JTextField("John Doe");
    private static final JTextField login1 = new JTextField("NickPick");
    private JPasswordField textPassword = new JPasswordField("******");
    private JTextField textMessage = new JTextField();
    private final JButton buttonLogin = new JButton("Login");
    private final JButton buttonSend = new JButton("Send");
    private final JButton buttonConnect = new JButton("Connect");
    private JTextArea log = new JTextArea();
    boolean connectionIsDone = false;

    ServerWindow serverWindow;
    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextLogin(textLogin.getText());
            }
        });

        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getConnectionIsDone()) {
                    serverWindow.sendMessage(textLogin.getText() + ": " + textMessage.getText() + "\n");
                    log.setText(serverWindow.getLogDialog());
                    textMessage.setText("");
                } else {
                    setConnectionIsDone();
                    log.append("\n Server is not working or your connection is failed. Please try again \n");
                }
            }
        });
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.getIsServerWorking()) {
                    if (getConnectionIsDone()) {
                        setConnectionIsDone();
                        log.append("Your connection is success \n");
                        log.append(serverWindow.getLogDialog());
                    }
                } else {
                    log.setText("");
                    setConnectionIsCancel();
                    log.append("Your connection is failed \n");
                    return;
                };
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");

        JPanel panelTop = new JPanel();

        panelTop.setLayout(new GridLayout(2,3));
        panelTop.add(textServ);
        panelTop.add(textPort);
        panelTop.add(buttonConnect);
        panelTop.add(textLogin);
        panelTop.add(textPassword);
        panelTop.add(buttonLogin);
        add(panelTop,BorderLayout.NORTH);

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,2));
        panelBottom.add(textMessage);
        panelBottom.add(buttonSend);
        add(panelBottom,BorderLayout.SOUTH);

        JList<String> listOfUsers = new JList<>();
        listOfUsers.add(login1);
        listOfUsers.add(textLogin);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new GridLayout(1,1));
        panelRight.add(listOfUsers);
        add(panelRight,BorderLayout.EAST);

        log.setEditable(true);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);
    }
    public void setConnectionIsDone() {
        this.connectionIsDone = true;
    }
    public void setConnectionIsCancel() {
        this.connectionIsDone = false;
    }
    public boolean getConnectionIsDone() {
        return serverWindow.getIsServerWorking();
    }
    public void setTextLogin(String textLogin) {
        this.textLogin.setText(textLogin);
    }

    public static void main(String[] args) {
        new ClientGUI(new ServerWindow());
    }

}
