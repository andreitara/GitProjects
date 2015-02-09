package forms;

import java.awt.*;
import javax.swing.*;
import com.intellij.uiDesigner.core.*;

/**
 * Created by andreita on 2/9/2015.
 */
public class MainGUI extends JFrame {

    private JPanel rootPanel;
    private JPanel externalPanel;
    private JPanel treePanel;
    private JPanel palettePanel;
    private JPanel propertiesPanel;
    private JPanel paintZonePanel;
    private JPanel rightBarPanel;
    private JPanel leftBarPanel;
    private JMenuBar menuBar;
    private JButton button2;
    private JToolBar toolBar;

    public MainGUI() {
        super("Processes Aggregator");
        $$$setupUI$$$();
        createUIComponents();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        pack();
        setVisible(true);
    }


    private void createUIComponents() {

        //Dimensions
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1200, 700));


        //Menu Bar Creation
        JMenu menu = new JMenu("File");
        menu.add(new JMenuItem("New"));
        menu.add(new JMenuItem("Open"));
        menu.add(new JMenuItem("Save"));
        menuBar.add(menu);

        menu = new JMenu("View");
        menu.add(new JMenuItem("Tree"));
        menu.add(new JMenuItem("Palette"));
        menuBar.add(menu);

        menu = new JMenu("Help");
        menu.add(new JMenuItem("Help Contents"));
        menu.add(new JMenuItem("About"));
        menuBar.add(menu);


        //Left Bar Panel
        leftBarPanel.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), 0, 0));

        VerticalLabel label = new VerticalLabel();
        label.setText("Left");
        label.setRotation(VerticalLabel.ROTATE_LEFT);

        leftBarPanel.add(label, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer = new Spacer();
        leftBarPanel.add(spacer, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));


        //Right Bar Panel
        rightBarPanel.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), 0, 0));

        label = new VerticalLabel();
        label.setText("Right");

        label.setRotation(VerticalLabel.ROTATE_RIGHT);

        rightBarPanel.add(label, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rightBarPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), 0, 0));
        externalPanel = new JPanel();
        externalPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(externalPanel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treePanel = new JPanel();
        treePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        externalPanel.add(treePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        treePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        final JLabel label1 = new JLabel();
        label1.setText("Label");
        treePanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        treePanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        externalPanel.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        paintZonePanel = new JPanel();
        paintZonePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        paintZonePanel.setAlignmentX(0.0f);
        paintZonePanel.setAlignmentY(0.0f);
        panel2.add(paintZonePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        paintZonePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        final JLabel label2 = new JLabel();
        label2.setText("Label");
        paintZonePanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        paintZonePanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        palettePanel = new JPanel();
        palettePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel2.add(palettePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        palettePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        final JLabel label3 = new JLabel();
        label3.setText("Label");
        palettePanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        palettePanel.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rightBarPanel = new JPanel();
        rightBarPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel2.add(rightBarPanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightBarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        propertiesPanel = new JPanel();
        propertiesPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(propertiesPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        propertiesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        final JLabel label4 = new JLabel();
        label4.setText("Label");
        propertiesPanel.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leftBarPanel = new JPanel();
        leftBarPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(leftBarPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        leftBarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        toolBar = new JToolBar();
        toolBar.setAlignmentX(0.0f);
        toolBar.setAlignmentY(0.0f);
        toolBar.setAutoscrolls(false);
        toolBar.setBorderPainted(true);
        toolBar.setFloatable(false);
        rootPanel.add(toolBar, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        button2 = new JButton();
        button2.setActionCommand("Save");
        button2.setAlignmentY(0.0f);
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(true);
        button2.setDefaultCapable(false);
        button2.setEnabled(true);
        button2.setFocusPainted(true);
        button2.setHideActionText(false);
        button2.setIcon(new ImageIcon(getClass().getResource("/images/save_icon.png")));
        button2.setIconTextGap(0);
        button2.setMargin(new Insets(2, 2, 2, 2));
        button2.setText("");
        button2.setToolTipText("Save");
        toolBar.add(button2);
        menuBar = new JMenuBar();
        menuBar.setAlignmentX(0.0f);
        rootPanel.add(menuBar, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(panel3, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        final JLabel label5 = new JLabel();
        label5.setText("Starea curenta a aplicatiei");
        panel3.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel3.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
