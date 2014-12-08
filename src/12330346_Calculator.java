/* 
 * A simple calculator which can use to calculate
 * +, -, * and /.
 * 
 * This code is for the exercise in project 1. It is a
 * calculator with GUI. And you can only use it to
 * calculate +, -, * and /. Make sure your input is
 * from clicking the button. You can't input your 
 * mathematical formula directly.
 * 
 * @author xiezhw3
 */

package Calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A <code>Cal</code> is the lower level implements of 
 * the calculator.<br />
 */
class Cal{
	public Cal() {}
	public double add(double a, double b) {
		return a + b;
	}
	public double sub(double a, double b) {
		return a - b;
	}
	public double mul(double a, double b) {
		return a * b;
	}
	public double dev(double a, double b) {
		if (b == 0)
			throw new ArithmeticException("The Divisor Can Not Be 0");
		else
			return a / b;
	}
}

/**
 * A <code>calculator</code> is the GUI implement of the
 * calculator.<br />
 * This class implements the logic of man-machine interaction
 */
public class Calculator implements ActionListener {
	private JFrame jf;  // Content board
	private JTextField tf;  //Text board
	private Cal cal;  // cal object
	// The text to store the input
	private String operNum, oper;
	// The operator of the calculator
	private double num1, num2, result;
	private String cmd; // The value of the button been clicked
	
	// The size of the calculator
	static final int WIDTH = 300;
	static final int HEIGHT = 220;
	
	// initialize
	public Calculator() {
		this.result = 0;
		this.operNum = null;
		this.oper = null;
		this.cmd = "";
		this.cal = new Cal();
		this.jf = new JFrame("Simple Calculator");  //name
		this.tf = new JTextField();
	}
	
	// Set the calculator's interface
	public void setInterface() {
		jf.setSize(WIDTH, HEIGHT); // Set size of calculator window.
		tf.setPreferredSize(new Dimension(100, 35));  // Set size of text file
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exist when close the window
		
		jf.getContentPane().add(tf, BorderLayout.NORTH);   // Add text delay area
		JPanel subGrid = new JPanel();
		jf.getContentPane().add(subGrid);
		
		// These code is to add two area (number area and function area) in 
		// the main window. To do this, we should put these two module
		// in an intermediate module
		//{
			JPanel jpn1 = new JPanel(); // The number calculate area
			JPanel jpn2 = new JPanel(); // The function area like exist and clear
			jpn2.setLayout(new GridLayout(1, 2));  // Two buttons in a row
			jpn1.setLayout(new GridLayout(4, 4)); // 16 buttons, each row four buttons
		//}
		
		//{ //set button
			JButton button;
			
			button = new JButton("Clear");
			button.addActionListener(this);
			jpn2.add(button);
			
			String[] str = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
			for (int i = 0; i != str.length; i++) {
				button = new JButton(str[i]);
				button.addActionListener(this);
				jpn1.add(button);
			}
		//}
		subGrid.add(jpn2, BorderLayout.NORTH);
		subGrid.add(jpn1);
		jf.setVisible(true);
	}
	
	// Whether is a number or a point
	public boolean isNum(String str) {
		String[] strNum = {"7","8","9","4","5","6","1","2","3","0","."};
		for (String s : strNum)
			if (str.equals(s))
				return true;
		return false;
	}
	
	// Set the state of calculator to initial
	private void reinit() {
		operNum = null;
		oper = null;
		num1 = 0;
		num2 = 0;
		result = 0;
		tf.setText("");
	}
	
	private double getResult(String op, double num1, double num2) {
		double resultTmp = 0;
		switch (oper){
		case "+":
			resultTmp = cal.add(num1, num2);
			break;
		case "-":
			resultTmp = cal.sub(num1, num2);
			break;
		case "*":
			resultTmp = cal.mul(num1, num2);
			break;
		case "/":
			try {
				resultTmp = cal.dev(num1, num2);
			} catch(ArithmeticException e) {
				throw e;
			}
			break;
		default:
			break;
		} 
		return resultTmp;
	}

	// Button event logic
	// Each click event will call this method. So
	// it can get each input after you click an button
	public void actionPerformed(ActionEvent act) {
		cmd = act.getActionCommand(); // The value of clicked button
		tf.setText(tf.getText() + cmd);  // The mathematical formula 
		
		if (cmd.equals("Clear")) { // Clear the input, return to original state
			reinit();
		} else if (isNum(cmd)) { // Whether is a number or a point
			if (operNum == null) {  // The first number of operator
				operNum = cmd;
			}
			else {
				operNum += cmd;
			}
		} else {  // operator
			if (cmd.equals("=")) { 
				if (oper == null) {  // Directly click "=" after input a number
					result = new Double(operNum).doubleValue();
				} else {
					num2 = new Double(operNum).doubleValue();   //The second operator
					try {
						result = getResult(oper, num1, num2);
					} catch(ArithmeticException e) {
						tf.setText(e.getMessage()); // Delay error message
					}
				}
				tf.setText(tf.getText() + String.format("%.2f", result));  // The calculate result
			} else {
				if (oper == null) {
					num1 = new Double(operNum).doubleValue();   // The first operator
				} else {
					num1 = result;    // The preceding result as an operator
				}
				oper = cmd;
				operNum = null;
			}
		}
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		cal.setInterface();
	}
}