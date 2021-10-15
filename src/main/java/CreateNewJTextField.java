package main.java;

import javax.swing.*;
import java.awt.*;

public class CreateNewJTextField extends JFrame {

    private static final long serialVersionUID = 1L;

    public CreateNewJTextField() {

        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());

        // create empty JTextField
        JTextField field1 = new JTextField();
        field1.setText("Java Code Geeks");

        // create JTextField with default text
        JTextField field2 = new JTextField("Java Code Geeks");

        // create JTextField with specified number of columns
        JTextField field3 = new JTextField(10);

        // create JTextField with default text and columns
        JTextField field4 = new JTextField("Java Code Geeks", 10);

        // add textfields to frame
        add(field1);
        add(field2);
        add(field3);
        add(field4);

    }

    private static void createAndShowGUI() {

        //Create and set up the window.

        JFrame frame = new CreateNewJTextField();

        //Display the window.

        frame.pack();

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:

        //creating and showing this application's GUI.

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                createAndShowGUI();

            }

        });
    }

}