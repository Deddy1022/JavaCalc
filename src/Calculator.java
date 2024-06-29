import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JPanel container = new JPanel();
    private String[] value = new String[]{
        "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "0", ".", "=", "C", "+", "-", "*", "/"
    };
    private String operateur = "";
    private JLabel ecran = new JLabel();
    private JButton[] buttons = new JButton[value.length];
    private double chiffre1;
    private boolean clicked = false, update = false;
    Calculator() {
        setSize(300, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponent();
        setContentPane(container);
        setVisible(true);
    }

    public void initComponent() {
        Font police = new Font("Arial", Font.BOLD, 20);
        ecran = new JLabel("0");
        ecran.setFont(police);
        ecran.setHorizontalAlignment(JLabel.RIGHT);
        ecran.setPreferredSize(new Dimension(220, 20));
        JPanel operateur = new JPanel();
        operateur.setPreferredSize(new Dimension(55,
                225));
        JPanel chiffre = new JPanel();
        chiffre.setPreferredSize(new Dimension(165, 225));
        JPanel panEcran = new JPanel();
        panEcran.setPreferredSize(new Dimension(220, 30));

        for(int i = 0; i < value.length; i++) {
            buttons[i] = new JButton(value[i]);
            buttons[i].setPreferredSize(new Dimension(50, 30));
            switch(i) {
                case 11:
                    buttons[i].addActionListener(new EgalListener());
                    chiffre.add(buttons[i]);
                    break;
                case 12:
                    buttons[i].addActionListener(new ResetListener());
                    operateur.add(buttons[i]);
                    break;
                case 13:
                    buttons[i].addActionListener(new PlusListener());
                    operateur.add(buttons[i]);
                    break;
                case 14:
                    buttons[i].addActionListener(new MoinsListener());
                    chiffre.add(buttons[i]);
                    break;
                case 15:
                    buttons[i].addActionListener(new FoisListener());
                    operateur.add(buttons[i]);
                    break;
                case 16:
                    buttons[i].addActionListener(new DivisionListener());
                    operateur.add(buttons[i]);
                    break;
                default:
                    buttons[i].addActionListener(new ChiffreListener());
                    chiffre.add(buttons[i]);
                    break;
            }
        }
        panEcran.add(ecran);
        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(panEcran, BorderLayout.NORTH);
        container.add(chiffre, BorderLayout.CENTER);
        container.add(operateur, BorderLayout.EAST);
    }

    private void operation() {
        if(operateur.equals("+")) {
            chiffre1 = chiffre1 + Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        } else if(operateur.equals("-")) {
            chiffre1 = chiffre1 - Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        } else if(operateur.equals("*")) {
            chiffre1 = chiffre1 * Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        }
    }

    private class EgalListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            operation();
            update = true;
            clicked = false;
        }
    }

    private class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            clicked = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            ecran.setText("");
        }
    }

    private class PlusListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(clicked) {
                operation();
                ecran.setText(String.valueOf(chiffre1));
            } else {
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicked = true;
            }
            operateur = "+";
            update = true;
        }
    }

    private class MoinsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if(clicked) {
                operation();
                ecran.setText(String.valueOf(chiffre1));
            } else {
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicked = true;
            }
            operateur = "-";
            update = true;
        }
    }

    private class FoisListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class DivisionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ChiffreListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String output = ((JButton)event.getSource()).getText();
            if(update) {
                update = false;
            } else {
                if(!ecran.getText().equals("0")){
                    output = ecran.getText() + output;
                }
            }
            ecran.setText(output);
        }
    }
}