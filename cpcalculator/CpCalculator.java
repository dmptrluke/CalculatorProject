/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cpcalculator;

import java.awt.Font;
import java.awt.event.ItemEvent;

/**
 *
 * @author Luke
 */
public class CpCalculator extends javax.swing.JFrame {
    
    public static final String VERSION = "0.1.1";

    // view state
    private Boolean isExtended;
    private Boolean isBold;
    private String extMode;
    private int rounding;
    
    // isDoingComplex is true if the program is waiting for a second number
    ComplexCalculationModel currentCalculation;
    Boolean isCalculating;
    
    // used to signal txtMain to be cleared on next pressedNum
    private boolean clearOnNumInput;
   
    
    /**
     * Creates new form CpCalculator
     */
    public CpCalculator() {
        initComponents();
        resetUIState();
        setRounding(2);
        resetCalculationState();
        
        clearOnNumInput = true;
        txtMain.setText("0");

    }
    
    private void resetUIState() {
        hideExtended();
        setPlain();
        setDegrees();
    }
    
    private void resetCalculationState() {
        currentCalculation = null;
        isCalculating = false;
    }
    
    // view state stuff
    private void showExtended() {
        pnlExtended.setVisible(true);
        setTitle("CPCalculator " + VERSION +  " [Extended]");
        // not sure if I should be setting the menu items here, but whatever
        mnuExtended.setSelected(true);
        tglExtend.setSelected(true);
        tglExtend.setText("Less");
        setSize(450, 289);
        isExtended = true;
    }
    
    private void hideExtended() {
        pnlExtended.setVisible(false);
        setTitle("CPCalculator " + VERSION +  " [Basic]");
        mnuBasic.setSelected(true);
        tglExtend.setSelected(false);
        tglExtend.setText("More");
        setSize(302, 289);
        isExtended = false;
    }
    
    private void setPlain() {
        txtMain.setFont(new Font("Sans", Font.PLAIN, 14));
        mnuPlain.setSelected(true);
        isBold = false;
    }
    
    private void setBold() {
        txtMain.setFont(new Font("Sans", Font.BOLD, 14));
        mnuBold.setSelected(true);
        isBold = true;
    }
    
   private void setDegrees() {
        extMode = "degrees";
        btnDegrees.setSelected(true);
    }
    
    private void setRadians() {
        extMode = "radians";
        btnRadians.setSelected(true);
    }
    
    private void setRounding(int round) {
        rounding = round;
    }
    
    // controller stuff
    private void pressedNum(byte num) {
        txtMain.setText(txtMain.getText() + String.valueOf(num));
    }
    
    private void pressedNum(String num) {
        if (clearOnNumInput) {
            txtMain.setText(num);
            clearOnNumInput = false;
        } else {
            txtMain.setText(txtMain.getText() + num);
        }
    }
    
    private void doSimpleOperator(String operator) {
        SimpleCalculationModel sc = new SimpleCalculationModel(operator, this.extMode, this.rounding);
        sc.setNumber(txtMain.getText());
        sc.checkInputs();
        sc.calculate();
        String output = sc.toString();
        if (output.contains("Error")) {
            msgMessage.showMessageDialog(this, output,
            "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        } else {
            clearOnNumInput = true;
            txtMain.setText(output);
        }

    }
    
    private void doComplexOperator(String operator) {
        if (clearOnNumInput) clearOnNumInput = false;
        String oper = operator;
        String number;
        if (this.isCalculating && txtMain.getText() != "") {
            calculate();
            // set the number to the output of the previous calculation
            number = txtMain.getText();
        } else {
            number = txtMain.getText();
        }
        resetCalculationState();
        this.isCalculating = true;
        this.currentCalculation = new ComplexCalculationModel(oper, rounding);
        currentCalculation.setFirstNumber(number); 
        txtMain.setText("");
    }

    private void calculate() {
        if (this.isCalculating) {
            currentCalculation.setSecondNumber(txtMain.getText());
            currentCalculation.checkInputs();
            currentCalculation.calculate();
            String output = currentCalculation.toString();
            if (output.contains("Error")) {
                // show an error message and clear txtMain
                msgMessage.showMessageDialog(this, output,
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                txtMain.setText("");
            } else {
                txtMain.setText(output);
            }
            resetCalculationState();
        } else {
            doSimpleOperator("null");
            resetCalculationState();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgrView = new javax.swing.ButtonGroup();
        bgrFont = new javax.swing.ButtonGroup();
        bgrRound = new javax.swing.ButtonGroup();
        bgrExtMode = new javax.swing.ButtonGroup();
        msgMessage = new javax.swing.JOptionPane();
        pnlBasic = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnDecimalPlace = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnNeg = new javax.swing.JButton();
        btnMultiply = new javax.swing.JButton();
        btnDivide = new javax.swing.JButton();
        btnCalculate = new javax.swing.JButton();
        btnExponent = new javax.swing.JButton();
        btnReciprocal = new javax.swing.JButton();
        btnReverse = new javax.swing.JButton();
        btnFactorial = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();
        btnClearEntry = new javax.swing.JButton();
        txtMain = new javax.swing.JTextField();
        tglExtend = new javax.swing.JToggleButton();
        pnlExtended = new javax.swing.JPanel();
        btnDegrees = new javax.swing.JRadioButton();
        btnRadians = new javax.swing.JRadioButton();
        btnSin = new javax.swing.JButton();
        btnAsin = new javax.swing.JButton();
        btnAcos = new javax.swing.JButton();
        btnCos = new javax.swing.JButton();
        btnAtan = new javax.swing.JButton();
        btnTan = new javax.swing.JButton();
        btnPI = new javax.swing.JButton();
        btnE = new javax.swing.JButton();
        mbMain = new javax.swing.JMenuBar();
        mnuView = new javax.swing.JMenu();
        mnuBasic = new javax.swing.JRadioButtonMenuItem();
        mnuExtended = new javax.swing.JRadioButtonMenuItem();
        sepView = new javax.swing.JPopupMenu.Separator();
        mnuReset = new javax.swing.JMenuItem();
        mnuFont = new javax.swing.JMenu();
        mnuPlain = new javax.swing.JRadioButtonMenuItem();
        mnuBold = new javax.swing.JRadioButtonMenuItem();
        mnuRound = new javax.swing.JMenu();
        round1 = new javax.swing.JRadioButtonMenuItem();
        round2 = new javax.swing.JRadioButtonMenuItem();
        round3 = new javax.swing.JRadioButtonMenuItem();
        round4 = new javax.swing.JRadioButtonMenuItem();
        round5 = new javax.swing.JRadioButtonMenuItem();
        round6 = new javax.swing.JRadioButtonMenuItem();
        round7 = new javax.swing.JRadioButtonMenuItem();
        round8 = new javax.swing.JRadioButtonMenuItem();
        round9 = new javax.swing.JRadioButtonMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mnuHotkeys = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("frame1gf"); // NOI18N
        setResizable(false);

        pnlBasic.setMaximumSize(new java.awt.Dimension(325, 203));
        pnlBasic.setLayout(new java.awt.GridBagLayout());

        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn1, gridBagConstraints);

        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn2, gridBagConstraints);

        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn3, gridBagConstraints);

        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn4, gridBagConstraints);

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn5, gridBagConstraints);

        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn6, gridBagConstraints);

        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn7, gridBagConstraints);

        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn8, gridBagConstraints);

        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn9, gridBagConstraints);

        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn0ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btn0, gridBagConstraints);

        btnDecimalPlace.setText(".");
        btnDecimalPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecimalPlaceActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnDecimalPlace, gridBagConstraints);

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnAdd, gridBagConstraints);

        btnNeg.setText("-");
        btnNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNegActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnNeg, gridBagConstraints);

        btnMultiply.setText("*");
        btnMultiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnMultiply, gridBagConstraints);

        btnDivide.setText("/");
        btnDivide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivideActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnDivide, gridBagConstraints);

        btnCalculate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCalculate.setText("=");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnCalculate, gridBagConstraints);

        btnExponent.setText("x^n");
        btnExponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExponentActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnExponent, gridBagConstraints);

        btnReciprocal.setText("1/x");
        btnReciprocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReciprocalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnReciprocal, gridBagConstraints);

        btnReverse.setText("+/-");
        btnReverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReverseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnReverse, gridBagConstraints);

        btnFactorial.setText("x!");
        btnFactorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFactorialActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnFactorial, gridBagConstraints);

        btnClearAll.setText("CA");
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnClearAll, gridBagConstraints);

        btnClearEntry.setText("CE");
        btnClearEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearEntryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(btnClearEntry, gridBagConstraints);

        txtMain.setEditable(false);
        txtMain.setBackground(javax.swing.UIManager.getDefaults().getColor("TextField.background"));
        txtMain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlBasic.add(txtMain, gridBagConstraints);

        tglExtend.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        tglExtend.setText("More");
        tglExtend.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglExtendItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        pnlBasic.add(tglExtend, gridBagConstraints);

        pnlExtended.setName(""); // NOI18N
        pnlExtended.setLayout(new java.awt.GridBagLayout());

        bgrExtMode.add(btnDegrees);
        btnDegrees.setSelected(true);
        btnDegrees.setText("Degrees");
        btnDegrees.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnDegreesItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnDegrees, gridBagConstraints);

        bgrExtMode.add(btnRadians);
        btnRadians.setText("Radians");
        btnRadians.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnRadiansItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnRadians, gridBagConstraints);

        btnSin.setText("sin");
        btnSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSinActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnSin, gridBagConstraints);

        btnAsin.setText("asin");
        btnAsin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsinActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnAsin, gridBagConstraints);

        btnAcos.setText("acos");
        btnAcos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnAcos, gridBagConstraints);

        btnCos.setText("cos");
        btnCos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnCos, gridBagConstraints);

        btnAtan.setText("atan");
        btnAtan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnAtan, gridBagConstraints);

        btnTan.setText("tan");
        btnTan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTanActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnTan, gridBagConstraints);

        btnPI.setFont(btnPI.getFont());
        btnPI.setText("pi");
        btnPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPIActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnPI, gridBagConstraints);

        btnE.setText("e");
        btnE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        pnlExtended.add(btnE, gridBagConstraints);

        mnuView.setText("View");

        bgrView.add(mnuBasic);
        mnuBasic.setSelected(true);
        mnuBasic.setText("Basic");
        mnuBasic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mnuBasicItemStateChanged(evt);
            }
        });
        mnuView.add(mnuBasic);

        bgrView.add(mnuExtended);
        mnuExtended.setText("Extended");
        mnuExtended.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mnuExtendedItemStateChanged(evt);
            }
        });
        mnuView.add(mnuExtended);
        mnuView.add(sepView);

        mnuReset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
        mnuReset.setText("Reset Form");
        mnuReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuResetActionPerformed(evt);
            }
        });
        mnuView.add(mnuReset);

        mbMain.add(mnuView);

        mnuFont.setText("Font");

        bgrFont.add(mnuPlain);
        mnuPlain.setSelected(true);
        mnuPlain.setText("Plain");
        mnuPlain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mnuPlainItemStateChanged(evt);
            }
        });
        mnuFont.add(mnuPlain);

        bgrFont.add(mnuBold);
        mnuBold.setText("Bold");
        mnuBold.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mnuBoldItemStateChanged(evt);
            }
        });
        mnuFont.add(mnuBold);

        mbMain.add(mnuFont);

        mnuRound.setText("Round");

        bgrRound.add(round1);
        round1.setText("1");
        round1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round1ItemStateChanged(evt);
            }
        });
        mnuRound.add(round1);

        bgrRound.add(round2);
        round2.setSelected(true);
        round2.setText("2");
        round2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round2ItemStateChanged(evt);
            }
        });
        round2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                round2ActionPerformed(evt);
            }
        });
        mnuRound.add(round2);

        bgrRound.add(round3);
        round3.setText("3");
        round3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round3ItemStateChanged(evt);
            }
        });
        mnuRound.add(round3);

        bgrRound.add(round4);
        round4.setText("4");
        round4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round4ItemStateChanged(evt);
            }
        });
        round4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                round4ActionPerformed(evt);
            }
        });
        mnuRound.add(round4);

        bgrRound.add(round5);
        round5.setText("5");
        round5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round5ItemStateChanged(evt);
            }
        });
        mnuRound.add(round5);

        bgrRound.add(round6);
        round6.setText("6");
        round6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round6ItemStateChanged(evt);
            }
        });
        mnuRound.add(round6);

        bgrRound.add(round7);
        round7.setText("7");
        round7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round7ItemStateChanged(evt);
            }
        });
        mnuRound.add(round7);

        bgrRound.add(round8);
        round8.setText("8");
        round8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round8ItemStateChanged(evt);
            }
        });
        mnuRound.add(round8);

        bgrRound.add(round9);
        round9.setText("9");
        round9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                round9ItemStateChanged(evt);
            }
        });
        mnuRound.add(round9);

        mbMain.add(mnuRound);

        mnuHelp.setText("Help");

        mnuHotkeys.setText("Hotkeys");
        mnuHotkeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuHotkeysActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuHotkeys);

        mnuAbout.setText("About");
        mnuAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mnuAbout);

        mbMain.add(mnuHelp);

        setJMenuBar(mbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBasic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlExtended, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBasic, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(pnlExtended, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void mnuExtendedItemStateChanged(java.awt.event.ItemEvent evt) {                                             
        if (!isExtended) {
            showExtended();
        }  
    }                                            

    private void mnuBasicItemStateChanged(java.awt.event.ItemEvent evt) {                                          
        if (isExtended) {
            hideExtended();
        }
    }                                         

    private void mnuPlainItemStateChanged(java.awt.event.ItemEvent evt) {                                          
        if (isBold) {
            setPlain(); 
        }
    }                                         

    private void mnuBoldItemStateChanged(java.awt.event.ItemEvent evt) {                                         
        if (!isBold) {
            setBold();
        }
    }                                        

    private void mnuAboutActionPerformed(java.awt.event.ActionEvent evt) {                                         
        msgMessage.showMessageDialog(this, "CPCalculator\nVersion: " + VERSION + "\nFeburary 2014\n\nCreated by 92015052",
                "About", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }                                        

    private void btnDegreesItemStateChanged(java.awt.event.ItemEvent evt) {                                            
        if (extMode != "degrees") {
            setDegrees();
        }
    }                                           

    private void btnRadiansItemStateChanged(java.awt.event.ItemEvent evt) {                                            
        if (extMode != "radians") {
            setRadians();
        }
    }                                           

    private void mnuResetActionPerformed(java.awt.event.ActionEvent evt) {                                         
        resetUIState();
    }                                        

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("1");
    }                                    

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("2");
    }                                    

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("3");
    }                                    

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("4");
    }                                    

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("5");
    }                                    

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("6");
    }                                    

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("7");
    }                                    

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("8");
    }                                    

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("9");
    }                                    

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        pressedNum("0");
    }                                    

    private void btnDecimalPlaceActionPerformed(java.awt.event.ActionEvent evt) {                                                
        pressedNum(".");
    }                                               

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {                                             
        calculate();
        clearOnNumInput = true;
    }                                            

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {                                            
        resetCalculationState();
        txtMain.setText("");
    }                                           

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
        doComplexOperator("add");
    }                                      

    private void btnSinActionPerformed(java.awt.event.ActionEvent evt) {                                       
        doSimpleOperator("sin");
    }                                      

    private void btnAsinActionPerformed(java.awt.event.ActionEvent evt) {                                        
        doSimpleOperator("asin");
    }                                       

    private void btnCosActionPerformed(java.awt.event.ActionEvent evt) {                                       
        doSimpleOperator("cos");
    }                                      

    private void btnAcosActionPerformed(java.awt.event.ActionEvent evt) {                                        
        doSimpleOperator("acos");
    }                                       

    private void btnTanActionPerformed(java.awt.event.ActionEvent evt) {                                       
        doSimpleOperator("tan");
    }                                      

    private void btnAtanActionPerformed(java.awt.event.ActionEvent evt) {                                        
        doSimpleOperator("atan");
    }                                       

    private void btnPIActionPerformed(java.awt.event.ActionEvent evt) {                                      
       txtMain.setText("3.14159265359");
    }                                     

    private void btnClearEntryActionPerformed(java.awt.event.ActionEvent evt) {                                              
        txtMain.setText("");
    }                                             

    private void btnEActionPerformed(java.awt.event.ActionEvent evt) {                                     
        txtMain.setText("2.718281828459045");
    }                                    

    private void btnDivideActionPerformed(java.awt.event.ActionEvent evt) {                                          
        doComplexOperator("divide");
    }                                         

    private void btnNegActionPerformed(java.awt.event.ActionEvent evt) {                                       
        doComplexOperator("neg");
    }                                      

    private void btnMultiplyActionPerformed(java.awt.event.ActionEvent evt) {                                            
        doComplexOperator("multiply");
    }                                           

    private void round2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void round4ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void round1ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(1);
    }                                       

    private void round2ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(2);
    }                                       

    private void round3ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(3);
    }                                       

    private void round4ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(4);
    }                                       

    private void round5ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(5);
    }                                       

    private void round6ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(6);
    }                                       

    private void round7ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(7);
    }                                       

    private void round8ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(8);
    }                                       

    private void round9ItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        setRounding(9);       
    }                                       

    private void tglExtendItemStateChanged(java.awt.event.ItemEvent evt) {                                           
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if (!isExtended) {
                showExtended();
            }
        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
            if (isExtended) {
                hideExtended();
            }
        }
    }                                          

    private void btnReciprocalActionPerformed(java.awt.event.ActionEvent evt) {                                              
        doSimpleOperator("reciprocal");
    }                                             

    private void btnReverseActionPerformed(java.awt.event.ActionEvent evt) {                                           
        doSimpleOperator("reverse");
    }                                          

    private void btnFactorialActionPerformed(java.awt.event.ActionEvent evt) {                                             
        doSimpleOperator("factorial");
    }                                            

    private void mnuHotkeysActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String message = "+ - Addition\n" +
                "- - Subtraction\n" +
                "^ - Multiplication\n" +
                "\\ - Division\n" +
                "\n"+
                "s - Sin\n" +
                "S - Arcsin\n" +
                "c - Cos\n" +
                "C - Arccos\n" +
                "t - Tan\n" +
                "T - Arctan\n" +
                "\n" +
                "i - Inverse\n" +
                "m - +/-\n" +
                "^ - Power\n" +
                "! - Factorial\n" +
                "\n" +
                "d - Clear All (CA)\n" +
                "D - Clear Entry (CE)\n" +
                "Digits 0-9 can be entered from the keypad\n" +
                "of the keyboards digit keys.";
        
        msgMessage.showMessageDialog(this, message,
                "Hotkeys", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }                                          

    private void btnExponentActionPerformed(java.awt.event.ActionEvent evt) {                                            
        doComplexOperator("exponent");
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CpCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CpCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CpCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CpCalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CpCalculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup bgrExtMode;
    private javax.swing.ButtonGroup bgrFont;
    private javax.swing.ButtonGroup bgrRound;
    private javax.swing.ButtonGroup bgrView;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnAcos;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAsin;
    private javax.swing.JButton btnAtan;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnClearEntry;
    private javax.swing.JButton btnCos;
    private javax.swing.JButton btnDecimalPlace;
    private javax.swing.JRadioButton btnDegrees;
    private javax.swing.JButton btnDivide;
    private javax.swing.JButton btnE;
    private javax.swing.JButton btnExponent;
    private javax.swing.JButton btnFactorial;
    private javax.swing.JButton btnMultiply;
    private javax.swing.JButton btnNeg;
    private javax.swing.JButton btnPI;
    private javax.swing.JRadioButton btnRadians;
    private javax.swing.JButton btnReciprocal;
    private javax.swing.JButton btnReverse;
    private javax.swing.JButton btnSin;
    private javax.swing.JButton btnTan;
    private javax.swing.JMenuBar mbMain;
    private javax.swing.JMenuItem mnuAbout;
    private javax.swing.JRadioButtonMenuItem mnuBasic;
    private javax.swing.JRadioButtonMenuItem mnuBold;
    private javax.swing.JRadioButtonMenuItem mnuExtended;
    private javax.swing.JMenu mnuFont;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenuItem mnuHotkeys;
    private javax.swing.JRadioButtonMenuItem mnuPlain;
    private javax.swing.JMenuItem mnuReset;
    private javax.swing.JMenu mnuRound;
    private javax.swing.JMenu mnuView;
    private javax.swing.JOptionPane msgMessage;
    private javax.swing.JPanel pnlBasic;
    private javax.swing.JPanel pnlExtended;
    private javax.swing.JRadioButtonMenuItem round1;
    private javax.swing.JRadioButtonMenuItem round2;
    private javax.swing.JRadioButtonMenuItem round3;
    private javax.swing.JRadioButtonMenuItem round4;
    private javax.swing.JRadioButtonMenuItem round5;
    private javax.swing.JRadioButtonMenuItem round6;
    private javax.swing.JRadioButtonMenuItem round7;
    private javax.swing.JRadioButtonMenuItem round8;
    private javax.swing.JRadioButtonMenuItem round9;
    private javax.swing.JPopupMenu.Separator sepView;
    private javax.swing.JToggleButton tglExtend;
    private javax.swing.JTextField txtMain;
    // End of variables declaration                   
}
