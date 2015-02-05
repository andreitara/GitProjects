import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by andreita on 2/5/2015.
 */
public class GUI extends JFrame {
    private JPanel rootPanel;
    private JPanel clientPanel;
    private JPanel CAPanel;
    private JPanel TGSPanel;
    private JPanel ServicePanel;
    private JButton a1CerereCAButton;
    private JButton a2CerereTGSButton;
    private JButton a3TransmiteTGSButton;
    private JButton a8EfectuiazaCerereaButton;
    private JTextArea outputClient;
    private JTextArea outputCA;
    private JTextArea outputTGS;
    private JTextArea outputService;
    private JButton a4TransmiteTGSTGTButton;
    private JButton a5TransmiteTGTTGSButton;
    private JButton a6TranmiteSessionKeyButton;
    private JButton a7CerereServiciuButton;
    private JButton a0GenerareaChilorButton;

    RSAEncrypt rsaKeys;
    Integer tgs;
    Integer clientTGS;
    byte[] tgt;
    byte[] a4messageCipherTGS;
    byte[] a4messageCipherTGT;

    public Integer sesionKey;
    public Integer clientSessionKey;

    byte[] a5tgs;
    byte[] a5tgt;
    byte[] a5tgtPlane;

    GUI() {
        super("Reprezentarea modelului Kerberos");
        $$$setupUI$$$();
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(1200, 700));

        appendOutputClient("Buna ziua. Eu sunt Client");
        appendOutputCA("Buna ziua. Eu sunt Centru de Autentificare");
        appendOutputTGS("Buna ziua. Eu sunt TGS");
        appendOutputService("Buna ziua. Eu sunt Serviciul");

        a0GenerareaChilorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsaKeys = new RSAEncrypt();
                rsaKeys.initialize();
                appendOutputCA("Cheile au fost generate");
                appendOutputClient(rsaKeys.clientKeys.getPublic().toString());
                appendOutputCA(rsaKeys.caKeys.getPublic().toString());
                appendOutputTGS(rsaKeys.tgsKeys.getPublic().toString());
                appendOutputService(rsaKeys.serverKeys.getPublic().toString());
                a0GenerareaChilorButton.setEnabled(false);
                a1CerereCAButton.setEnabled(true);
            }
        });

        a1CerereCAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendOutputClient("Spre CA : Client, Service");
                a1("Client", "Service");
                a1CerereCAButton.setEnabled(false);
                a2CerereTGSButton.setEnabled(true);
            }
        });

        a2CerereTGSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendOutputCA("Spre TGS : Da-mi te rog TGS");
                a2();
                a2CerereTGSButton.setEnabled(false);
                a3TransmiteTGSButton.setEnabled(true);
            }
        });

        a3TransmiteTGSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendOutputTGS("Spre CA : Tine TGS=" + tgs);
                a3();
                a3TransmiteTGSButton.setEnabled(false);
                a4TransmiteTGSTGTButton.setEnabled(true);
            }
        });

        a4TransmiteTGSTGTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a4();
                a4TransmiteTGSTGTButton.setEnabled(false);
                a5TransmiteTGTTGSButton.setEnabled(true);
            }
        });

        a5TransmiteTGTTGSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a5();
                a5TransmiteTGTTGSButton.setEnabled(false);
                a6TranmiteSessionKeyButton.setEnabled(true);
            }
        });

        a6TranmiteSessionKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a6();
                a6TranmiteSessionKeyButton.setEnabled(false);
                a7CerereServiciuButton.setEnabled(true);
            }
        });

        a7CerereServiciuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a7();
                a7CerereServiciuButton.setEnabled(false);
                a8EfectuiazaCerereaButton.setEnabled(true);
            }
        });

        a8EfectuiazaCerereaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a8();
                a8EfectuiazaCerereaButton.setEnabled(false);
                a0GenerareaChilorButton.setEnabled(true);
            }
        });

        pack();
        setVisible(true);
    }

    public void a0(String client, String service) {

    }

    public void a1(String client, String service) {
        appendOutputCA("De la " + client + ": " + client + ", " + service);
    }

    public void a2() {
        Random rand = new Random();
        tgs = rand.nextInt(20000000);
        appendOutputTGS("My TGS is : " + tgs);
    }

    public void a3() {
        appendOutputCA("De la TGS: TGS=" + tgs);
    }

    public void a4() {
        String tgtPlane = "Client,Serviciu";
        appendOutputCA("TGT simplu : " + tgtPlane);
        tgt = rsaKeys.encryptTGS(tgtPlane.getBytes());
        appendOutputCA("TGT criptat : " + new String(tgt));
        String messagePlane = "[" + tgs + "],[" + new String(tgt) + "]";
        appendOutputCA("Mesajul simplu : " + messagePlane);

        a4messageCipherTGS = rsaKeys.encryptClient((tgs + "").getBytes());
        a4messageCipherTGT = rsaKeys.encryptClient(tgt);
        appendOutputCA("Mesajul criptat : " + new String(a4messageCipherTGS) + new String(a4messageCipherTGT));
        appendOutputCA("Spre Client : " + new String(a4messageCipherTGS) + new String(a4messageCipherTGT));
        appendOutputClient("De la CA : " + new String(a4messageCipherTGS) + new String(a4messageCipherTGT));

        a5tgs = rsaKeys.decryptClient(a4messageCipherTGS);
        a5tgt = rsaKeys.decryptClient(a4messageCipherTGT);
        clientTGS = Integer.parseInt(new String(a5tgs));
        appendOutputClient("TGS: " + new String(a5tgs));
        appendOutputClient("TGT: " + new String(a5tgt));
    }

    public void a5() {
        appendOutputClient("Spre TGS: tgs si tgt");
        appendOutputTGS("De la Client: tgs si tgt");
        appendOutputTGS("TGS: " + new String(a5tgs));
        appendOutputTGS("TGT: " + new String(a5tgt));
        a5tgtPlane = rsaKeys.decryptTGS(a5tgt);
        appendOutputTGS("TGT Simplu : " + new String(a5tgtPlane));
    }

    public void a6() {
        if (tgs.equals(clientTGS)) {
            appendOutputTGS("TGS de la client este corect");
            Random random = new Random();
            sesionKey = random.nextInt();
            appendOutputTGS("Session Key Simplu : " + sesionKey);
            byte[] b1 = rsaKeys.encryptClient((sesionKey + "").getBytes());
            appendOutputTGS("Session Key Criptat : " + new String(b1));
            appendOutputTGS("Spre Client : " + new String(b1));
            appendOutputClient("De la TGS : " + new String(b1));
            byte[] b2 = rsaKeys.decryptClient(b1);
            clientSessionKey = Integer.parseInt(new String(b2));
            appendOutputClient("Session Key : " + clientSessionKey);
        }
    }

    public void a7() {
        appendOutputClient("Spre Serviciu: " + clientSessionKey);
        appendOutputService("De la Client: " + clientSessionKey);
    }

    public void a8() {
        if (sesionKey.equals(clientSessionKey)) {
            appendOutputService("Cheia de sesiune este corecta");
            appendOutputService("Felicitari conexiunea dintre client si serviciu a fost stabilita");
        }
    }


    public void appendOutputClient(String text) {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
        outputClient.append(date + "  :  " + text + "\n");
    }

    public void appendOutputCA(String text) {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
        outputCA.append(date + "  :  " + text + "\n");
    }

    public void appendOutputTGS(String text) {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
        outputTGS.append(date + "  :  " + text + "\n");
    }

    public void appendOutputService(String text) {
        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE);
        outputService.append(date + "  :  " + text + "\n");
    }

    private void createUIComponents() {
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
        rootPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        clientPanel = new JPanel();
        clientPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        clientPanel.setName("");
        clientPanel.setOpaque(true);
        clientPanel.setToolTipText("");
        rootPanel.add(clientPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        clientPanel.setBorder(BorderFactory.createTitledBorder("Client"));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        clientPanel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        a1CerereCAButton = new JButton();
        a1CerereCAButton.setEnabled(false);
        a1CerereCAButton.setText("1 Cerere CA");
        panel1.add(a1CerereCAButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a5TransmiteTGTTGSButton = new JButton();
        a5TransmiteTGTTGSButton.setEnabled(false);
        a5TransmiteTGTTGSButton.setText("5 Transmite TGT, TGS");
        panel1.add(a5TransmiteTGTTGSButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a7CerereServiciuButton = new JButton();
        a7CerereServiciuButton.setEnabled(false);
        a7CerereServiciuButton.setText("7 Cerere Serviciu");
        panel1.add(a7CerereServiciuButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        clientPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputClient = new JTextArea();
        scrollPane1.setViewportView(outputClient);
        CAPanel = new JPanel();
        CAPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(CAPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        CAPanel.setBorder(BorderFactory.createTitledBorder("CA"));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        CAPanel.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        a2CerereTGSButton = new JButton();
        a2CerereTGSButton.setEnabled(false);
        a2CerereTGSButton.setText("2 Cerere TGS");
        panel2.add(a2CerereTGSButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a4TransmiteTGSTGTButton = new JButton();
        a4TransmiteTGSTGTButton.setEnabled(false);
        a4TransmiteTGSTGTButton.setText("4 Transmite TGS, TGT");
        panel2.add(a4TransmiteTGSTGTButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a0GenerareaChilorButton = new JButton();
        a0GenerareaChilorButton.setText("0 Generarea Chilor");
        panel2.add(a0GenerareaChilorButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        CAPanel.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputCA = new JTextArea();
        scrollPane2.setViewportView(outputCA);
        TGSPanel = new JPanel();
        TGSPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(TGSPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        TGSPanel.setBorder(BorderFactory.createTitledBorder("TGS"));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        TGSPanel.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        a3TransmiteTGSButton = new JButton();
        a3TransmiteTGSButton.setEnabled(false);
        a3TransmiteTGSButton.setText("3 Transmite TGS");
        panel3.add(a3TransmiteTGSButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        a6TranmiteSessionKeyButton = new JButton();
        a6TranmiteSessionKeyButton.setEnabled(false);
        a6TranmiteSessionKeyButton.setText("6 Tranmite SessionKey");
        panel3.add(a6TranmiteSessionKeyButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        TGSPanel.add(scrollPane3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputTGS = new JTextArea();
        scrollPane3.setViewportView(outputTGS);
        ServicePanel = new JPanel();
        ServicePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.add(ServicePanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ServicePanel.setBorder(BorderFactory.createTitledBorder("Service"));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        ServicePanel.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        a8EfectuiazaCerereaButton = new JButton();
        a8EfectuiazaCerereaButton.setEnabled(false);
        a8EfectuiazaCerereaButton.setText("8 Efectuiaza cererea");
        panel4.add(a8EfectuiazaCerereaButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane4 = new JScrollPane();
        ServicePanel.add(scrollPane4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        outputService = new JTextArea();
        scrollPane4.setViewportView(outputService);
        final JLabel label1 = new JLabel();
        label1.setText("SI - Laboratorul Nr.1 - Kerberos - st.gr. TI-141M Andrei Tara");
        rootPanel.add(label1, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
