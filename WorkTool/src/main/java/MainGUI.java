import util.ManualInvocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrei on 6/23/2015.
 */
public class MainGUI extends JFrame{

    private JPanel rootPanel;
    private JTabbedPane menuPane;
    private JButton manualInvocationButton;

    public MainGUI() {
        super("Edifecs Work Tool");
        //$$$setupUI$$$();
        createUIComponents();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));



        manualInvocationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Manual Invocation Triggered");
                ManualInvocation.triggerAllTRsFromDatabase();
            }
        });

        pack();
        setVisible(true);
    }

    private void createUIComponents() {

    }
}
