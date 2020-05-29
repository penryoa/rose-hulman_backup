package currency_lab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {

	public JFrame jFrame;	
	public JPanel contentPane;
	public JTextArea inputTextArea;
	public JTextArea outputTextArea;
	public JPanel buttonPanel;
	CurrencyApp app;
		
	public void run(CurrencyApp a, String text) {
		// Setup GUI
		this.app = a;
		jFrame = new JFrame("Currency Observer");
		contentPane = (JPanel)jFrame.getContentPane();
		
		buttonPanel = new JPanel();
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		for (Currency c: app.currencyTable.keySet()) {
			buttonPanel.add(c.currencyButton);
		}
		
		JPanel mainTextPanel = new JPanel();
		mainTextPanel.setLayout(new GridLayout(2,1));
		contentPane.add(mainTextPanel, BorderLayout.CENTER);

		inputTextArea = new JTextArea(text);
		inputTextArea.setPreferredSize(new Dimension(600, 100));
		JPanel sourcePanel = new JPanel(new BorderLayout());
		sourcePanel.setBorder(BorderFactory.createTitledBorder("Instructions"));
		mainTextPanel.add(sourcePanel);
		
		JScrollPane sourceScrollPane = new JScrollPane(inputTextArea);
		sourcePanel.add(sourceScrollPane);
		
		JPanel resultPanel = new JPanel(new BorderLayout());
		resultPanel.setBorder(BorderFactory.createTitledBorder("Conversion Results"));
		mainTextPanel.add(resultPanel);
		
		outputTextArea = new JTextArea();
		outputTextArea.setPreferredSize(new Dimension(600, 300));
		JScrollPane resultScrollPane = new JScrollPane(outputTextArea);
		resultPanel.add(resultScrollPane);

		// Show GUI
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public double getAmount(){
		String subText = JOptionPane.showInputDialog(jFrame, 
				"Please enter the amount to convert", 
				"Input", 
				JOptionPane.QUESTION_MESSAGE);
		double doubleEquivalent = new Double(subText);
		return doubleEquivalent;
	}
	
}
