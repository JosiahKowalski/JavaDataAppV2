import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class JavaDataAppGUI extends JFrame {
    private JTextField tickerInput;
    private JButton searchButton;
    private JLabel ticker;
    private JLabel tickerOutput;
    private JPanel DataApp;
    private JComboBox currencyBox;

    public JavaDataAppGUI() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onSearch();
                } catch (UnirestException ex) {
                    ex.printStackTrace();
                }
            }
        });
        tickerInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                    try {
                        onSearch();
                    } catch (UnirestException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void onSearch() throws UnirestException {
        // add your code here
        String ticker = tickerInput.getText();
        String currency = String.valueOf(currencyBox.getSelectedItem());
        JavaDataAppV2 data = new JavaDataAppV2();
        String URL = data.buildURL(ticker.toUpperCase(), currency.toUpperCase());
        String output = data.handleURL(URL);
        if (output == null) tickerOutput.setText("This ticker doesn't seem to be in our records.    Try another.");
        else tickerOutput.setText(output);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new JavaDataAppGUI().DataApp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
