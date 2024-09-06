package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop  = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private JTextArea logDialog = new JTextArea();

    private boolean isServerWorking;

    public static void main(String[] args) {
        new ServerWindow();
    }

    public ServerWindow(){
        isServerWorking = false;

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                log.append("Server stopped " + "\n");
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                log.append("Server started " + "\n");
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setAlwaysOnTop(true);

        setLayout(new GridLayout(1,2));
        add(btnStart);
        add(btnStop);

        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new GridLayout(1,1));
        panelBottom.add(log);
        add(panelBottom,BorderLayout.SOUTH);

        setVisible(true);
    }
    public boolean getIsServerWorking() {
        return this.isServerWorking;
    }
    public void sendMessage(String message) {
        if (isServerWorking) {
            logDialog.append(message);
        } else {
            logDialog.append("Server is not working or your connection is failed. Please try again \n");
        };
    }
    public String getLogDialog() {
        return logDialog.getText();
    }
}
