package forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.intellij.uiDesigner.core.*;
import util.Colors;

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
    private JLabel treePanelTitle;
    private JLabel paintZoneTitle;
    private JLabel palettePanelTitle;
    private JLabel propertiesPanelTitle;
    private JPanel paintZone;
    private JPanel workPanel;


    private boolean canResizePanel = false;
    private boolean isInResize = false;


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

        //Set Application Icon
        setIconImage(new ImageIcon("images/process_icon.png").getImage());

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


        //Title Zone Dimensions
        Dimension dim = paintZoneTitle.getPreferredSize();
        paintZoneTitle.setPreferredSize(new Dimension(dim.width + 16, dim.height + 4));
        dim = palettePanelTitle.getPreferredSize();
        palettePanelTitle.setPreferredSize(new Dimension(dim.width + 16, dim.height + 4));
        dim = treePanelTitle.getPreferredSize();
        treePanelTitle.setPreferredSize(new Dimension(dim.width + 16, dim.height + 4));
        dim = propertiesPanelTitle.getPreferredSize();
        propertiesPanelTitle.setPreferredSize(new Dimension(dim.width + 16, dim.height + 4));


        //Left Bar Panel
        leftBarPanel.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), 0, 0));

        final VerticalLabel processTreeLabel = new VerticalLabel();
        processTreeLabel.setText("Processes");
        processTreeLabel.setIcon(new ImageIcon("images/process_icon.png"));
        Dimension d = processTreeLabel.getPreferredSize();
        processTreeLabel.setPreferredSize(new Dimension(d.width + 16, d.height + 4));
        processTreeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        processTreeLabel.setOpaque(true);
        processTreeLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_ENTERED);
        processTreeLabel.setRotation(VerticalLabel.ROTATE_LEFT);

        leftBarPanel.add(processTreeLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer = new Spacer();
        leftBarPanel.add(spacer, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

        //Action processLabelTree
        processTreeLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (treePanel.isVisible()) {
                    treePanel.setVisible(false);
                    processTreeLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_EXITED);
                } else {
                    treePanel.setVisible(true);
                    processTreeLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_ENTERED);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!treePanel.isVisible())
                    processTreeLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!treePanel.isVisible())
                    processTreeLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_EXITED);

            }
        });


        //Right Bar Panel
        rightBarPanel.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), 0, 0));

        final VerticalLabel paletteLabel = new VerticalLabel();
        paletteLabel.setText("Palette");
        paletteLabel.setIcon(new ImageIcon("images/palette_icon.png"));
        d = paletteLabel.getPreferredSize();
        paletteLabel.setPreferredSize(new Dimension(d.width + 16, d.height + 4));
        paletteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paletteLabel.setOpaque(true);
        paletteLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_ENTERED);
        paletteLabel.setRotation(VerticalLabel.ROTATE_RIGHT);

        rightBarPanel.add(paletteLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rightBarPanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

        //Action paletteLabel
        paletteLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (palettePanel.isVisible()) {
                    palettePanel.setVisible(false);
                    paletteLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_EXITED);
                } else {
                    palettePanel.setVisible(true);
                    paletteLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_ENTERED);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!palettePanel.isVisible())
                    paletteLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_ENTERED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!palettePanel.isVisible())
                    paletteLabel.setBackground(Colors.LABEL_BACKGROUND_MOUSE_EXITED);

            }
        });


        //**************************************************************************************
        //Make JPanels Resizable
        //**************************************************************************************
        //Tree Processes Panel
        treePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (canResizePanel) isInResize = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (canResizePanel) {
                    isInResize = false;
                    setCursor(Cursor.getDefaultCursor());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isInResize) {
                    setCursor(Cursor.getDefaultCursor());
                    canResizePanel = false;
                }
            }
        });
        treePanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isInResize) {
                    treePanel.setSize(new Dimension(e.getX(), treePanel.getSize().height));
                    treePanel.setPreferredSize(new Dimension(e.getX(), -1));
                    pack();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Dimension dimension = treePanel.getPreferredSize();
                if (e.getX() > dimension.width - 5) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    canResizePanel = true;
                } else
                    setCursor(Cursor.getDefaultCursor());
            }
        });
        //Properties Component Panel
        propertiesPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (canResizePanel) {
                    isInResize = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (canResizePanel) isInResize = false;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isInResize) {
                    setCursor(Cursor.getDefaultCursor());
                    canResizePanel = false;
                }
            }
        });
        propertiesPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isInResize) {
                    int y = propertiesPanel.getSize().height - e.getY();
                    propertiesPanel.setSize(new Dimension(propertiesPanel.getSize().width, y));
                    propertiesPanel.setPreferredSize(new Dimension(-1, y));
                    pack();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getY() < 5) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    canResizePanel = true;
                } else
                    setCursor(Cursor.getDefaultCursor());
            }
        });
        //Palette Panel
        palettePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (canResizePanel) {
                    isInResize = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (canResizePanel) isInResize = false;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isInResize) {
                    setCursor(Cursor.getDefaultCursor());
                    canResizePanel = false;
                }
            }
        });
        palettePanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isInResize) {
                    int x = palettePanel.getSize().width - e.getX();
                    palettePanel.setSize(new Dimension(x, palettePanel.getSize().height));
                    palettePanel.setPreferredSize(new Dimension(x, -1));
                    pack();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getX() < 5) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    canResizePanel = true;
                } else
                    setCursor(Cursor.getDefaultCursor());
            }
        });

        //Resize frame event
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setPreferredSize(new Dimension(getSize().width, getSize().height));
            }
        });


        //**************************************************************************************
        //Test Button
        //**************************************************************************************
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
        externalPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(externalPanel, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treePanel = new JPanel();
        treePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        treePanel.setAlignmentX(0.0f);
        treePanel.setAlignmentY(0.0f);
        treePanel.setBackground(new Color(-328966));
        externalPanel.add(treePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        treePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        treePanelTitle = new JLabel();
        treePanelTitle.setOpaque(true);
        treePanelTitle.setText("Process Tree Zone");
        treePanel.add(treePanelTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        treePanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        workPanel = new JPanel();
        workPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        externalPanel.add(workPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), 0, 0));
        workPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        paintZonePanel = new JPanel();
        paintZonePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        paintZonePanel.setAlignmentX(0.0f);
        paintZonePanel.setAlignmentY(0.0f);
        paintZonePanel.setBackground(new Color(-328966));
        panel1.add(paintZonePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        paintZonePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        paintZoneTitle = new JLabel();
        paintZoneTitle.setOpaque(true);
        paintZoneTitle.setText("Paint Zone");
        paintZonePanel.add(paintZoneTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        paintZone = new JPanel();
        paintZone.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        paintZone.setBackground(new Color(-657931));
        paintZonePanel.add(paintZone, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(200, -1), new Dimension(200, -1), null, 0, false));
        palettePanel = new JPanel();
        palettePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        palettePanel.setBackground(new Color(-328966));
        panel1.add(palettePanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        palettePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        palettePanelTitle = new JLabel();
        palettePanelTitle.setOpaque(true);
        palettePanelTitle.setText("Palette Zone");
        palettePanel.add(palettePanelTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        palettePanel.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rightBarPanel = new JPanel();
        rightBarPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), 0, 0));
        panel1.add(rightBarPanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightBarPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        propertiesPanel = new JPanel();
        propertiesPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        propertiesPanel.setBackground(new Color(-328966));
        workPanel.add(propertiesPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        propertiesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), ""));
        propertiesPanelTitle = new JLabel();
        propertiesPanelTitle.setOpaque(true);
        propertiesPanelTitle.setText("Component Properties Zone");
        propertiesPanel.add(propertiesPanelTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        propertiesPanel.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(panel2, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        final JLabel label1 = new JLabel();
        label1.setText("Starea curenta a aplicatiei");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
