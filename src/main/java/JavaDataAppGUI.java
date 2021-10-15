
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JavaDataAppGUI extends JFrame {
    private JTextField tickerInput;
    private JButton searchButton;
    private JLabel ticker;
    private JLabel tickerOutput;
    private JPanel DataApp;

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
    }

    private void onSearch() throws UnirestException {
        // add your code here
        String ticker = tickerInput.getText();
        JavaDataAppV2 data = new JavaDataAppV2();
        String URL = data.buildURL(ticker.toUpperCase());
        tickerOutput.setText(data.handleURL(URL));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new JavaDataAppGUI().DataApp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
