package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop  = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private JTextArea logDialog = new JTextArea();
    private Path path;

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
        path = Paths.get("logDialog.txt");
//        logFile = new File("logDialog.txt");
        setVisible(true);
    }
    public boolean getIsServerWorking() {
        return this.isServerWorking;
    }
    public void sendMessage(String message) {
        if (isServerWorking) {
            logDialog.append(message);
            try {
                Files.write(path, message.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                logDialog.append(e.getMessage());
            }
        } else {
            logDialog.append("\n Server is not working or your connection is failed. Please try again \n");
        };
    }
    public String getLogDialog() {
        try {
//            BufferedReader reader = Files.newBufferedReader(path);
//            String line = reader.readLine();
            if (logDialog.getText().isEmpty()) {
                Stream<String> lines = Files.lines(path);
                String data = lines.collect(Collectors.joining("\n"));
                lines.close();
                logDialog.setText("");
                logDialog.setText(data);
                return logDialog.getText();
            } else {
                return logDialog.getText(); }
        } catch (IOException e) {
            return e.getMessage();
        }

    }
}
