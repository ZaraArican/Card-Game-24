import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author Sevdenur,Chris,Alexis
 *         Creates a GUI user interface using swing.
 *         Includes use of OOP, Inheritance, exceptions, and Polymorphism.
 */
public class GameGUI extends JFrame {
    private static int crd1, crd2, crd3, crd4;
    private JPanel contentPane;
    private JTextField textField;
    private ImageIcon[] cardpics = new ImageIcon[52];
    private HashMap<String, ImageIcon> imageDeck;
    private String[] deck;
    private static String cCode, cCode1, cCode2, cCode3;
    private Random rnd = new Random();
    private JLabel Card, Card_1, Card_2, Card_3;
    private static String str = "";
    private static String[] genVal, aOpAr;
    private String ans = "";
    private static String pSolution = "";
    private static Map<Character, BinOperation> operations = new HashMap<>();
    private JLabel label_1time;
    private static long endTime, startTime;
    private static JLabel lblNewsolution;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameGUI frame = new GameGUI();
                    frame.setVisible(true);
                    frame.setTitle("Game");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * Simple GUI interface for testing the algorithm. Validates a user-entered expression for 24.
     * Includes JTextFields to read user input and buttons to generate random images and to validate. Records time needed to solve the problem.
     *
     * @throws InterruptedException
     */
    public GameGUI() throws IOException, InterruptedException {
        imageDeck = new HashMap<String, ImageIcon>();
        deck = new String[52];

        long startTime = System.currentTimeMillis();


        operations.put('*', new Multiplication());
        operations.put('+', new Addition());
        operations.put('-', new Subtraction());
        operations.put('\\', new Division());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEnterAnExpression = new JLabel("Enter an Expression:");
        lblEnterAnExpression.setBounds(10, 490, 132, 14);
        contentPane.add(lblEnterAnExpression);

        JLabel label = new JLabel("");
        label.setBounds(175, 518, 383, 20);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBounds(175, 487, 244, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '=') {
                    str = textField.getText().split("=")[0];
                    System.out.println(str);
                    ans = Double.toString(EvaluateArithmeticExpParanthesis.processExpression(str));
                    label.setText(textField.getText() + "= " + ans);
                }
            }
        });
        JButton btnVerify = new JButton("Verify");
        btnVerify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    str = textField.getText();

                    try {
                        ans = Double.toString(EvaluateArithmeticExpParanthesis.processExpression(str));
                        if (ans.equals("24.0")) {
                            label.setText("Correct.");
                        } else {
                            label.setText("Incorrect");
                        }
                        LogFile.writeLog("Verify", str);
                        long endTime = System.currentTimeMillis();
                        label_1time.setText("Solved in " + (endTime - startTime) + " milliseconds");
                        LogFile.writeLog("Manual", "User entered an equation in " + (endTime - startTime) + " millseconds.\n");
                    } catch (EmptyStackException g) {
                        label.setText("Please enter an expression.");
                        LogFile.writeLog("Manual", "Player submitted empty answer.\n");
                    }
                } catch (IOException f) {
                }
            }
        });
        btnVerify.setBounds(495, 486, 89, 23);
        contentPane.add(btnVerify);

        JButton FndNewSoltn = new JButton("Find a new Solution");
        FndNewSoltn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LogFile.writeLog("Find", "dummy solution");
                } catch (IOException f) {
                }
            }
        });
        FndNewSoltn.setBounds(10, 11, 155, 23);
        contentPane.add(FndNewSoltn);

        FndNewSoltn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getSgstExp(crd1, crd2, crd3, crd4);

            }
        });

        Card = new JLabel();
        Card.setBounds(20, 74, 225, 325);
        Card.setBackground(Color.WHITE);
        contentPane.add(Card);

        Card_1 = new JLabel();
        Card_1.setBounds(250, 74, 225, 325);
        contentPane.add(Card_1);

        Card_2 = new JLabel();
        Card_2.setBounds(480, 74, 225, 325);
        contentPane.add(Card_2);

        Card_3 = new JLabel();
        Card_3.setBounds(710, 74, 225, 325);
        contentPane.add(Card_3);

        updateCards();

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    updateCards();
                } catch (IOException f) {
                }
            }
        });
        btnRefresh.setBounds(412, 11, 89, 23);
        contentPane.add(btnRefresh);

        label_1time = new JLabel("");
        label_1time.setBounds(169, 541, 306, 20);
        contentPane.add(label_1time);

        lblNewsolution = new JLabel("");
        lblNewsolution.setBounds(185, 11, 200, 18);
        contentPane.add(lblNewsolution);
    }


    /**
     * This method displays four images (playing cards) randomly.
     *
     * @throws IOException
     */
    public void updateCards() throws IOException {
        crd1 = rnd.nextInt(13) + 1;
        crd2 = rnd.nextInt(13) + 1;
        crd3 = rnd.nextInt(13) + 1;
        crd4 = rnd.nextInt(13) + 1;

        Card.setIcon(new ImageIcon("cards\\S_img_" + crd1 + ".png"));
        Card_1.setIcon(new ImageIcon("cards\\H_img_" + crd2 + ".png"));
        Card_2.setIcon(new ImageIcon("cards\\C_img_" + crd3 + ".png"));
        Card_3.setIcon(new ImageIcon("cards\\D_img_" + crd4 + ".png"));
        LogFile.writeLog("Refresh", String.format("%d, %d, %d, and %d", crd1, crd2, crd3, crd4));

    }

    /**
     * This method processes expressions using operations.
     *
     * @param str
     * @return
     */
    public static double processExp(String str) {
        double inputA, inputB;
        char operator;
        Pattern pt = Pattern.compile("\\+|\\*|\\\\|\\-|");
        String str1 = pt.split(str)[0];
        inputA = Double.valueOf(str1);
        operator = str.split(str1)[1].charAt(0);
        inputB = Double.valueOf(pt.split(str)[1]);
        BinOperation op = operations.get(operator);
        return op.evaluate(inputA, inputB);
    }

    /**
     * This method generates a possible solution for the given card values that is equal to "24"
     *
     * @param genVal
     * @return
     */
    public static double getSgstExp(int... genVal) {
        String[] aOpAr = new String[]{"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int z = 0; z < 4; z++) {
                    for (int w = 0; w < 4; w++) {
                        String str = "((" + genVal[i] + aOpAr[i] + genVal[j] + ")" + aOpAr[j] + "(" + genVal[z] + aOpAr[z] + genVal[w] + "))";
                        String str1 = "(" + genVal[i] + aOpAr[i] + genVal[j] + aOpAr[j] + genVal[z] + aOpAr[z] + genVal[w] + ")";
                        //   str= "((" + genVal[i] + aOpAr[i] + genVal[j]  + aOpAr[j] +  genVal[z] + ")"+aOpAr[z] + genVal[w] + ")";

                        double v = EvaluateArithmeticExpParanthesis.processExpression(str);

                        if ((v - 24) == 0) {
                            lblNewsolution.setText(str);
                            return v;
                        }
                    }
                }
            }
        }
        return 0;
    }
}