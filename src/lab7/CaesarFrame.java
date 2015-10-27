package lab7;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CaesarFrame extends JFrame {
	
	private JButton button;
	private JTextField inputField;
	private JTextField outputField;
	private JComboBox<Character> offsetBox;
	
	private boolean inputHasFocus = true;
	
	private CaesarCipher cipher = new CaesarCipher('A');

	public CaesarFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("SwingLab");
		this.setSize(400, 110);
		this.setResizable(true);
		Container pane = this.getContentPane();
		
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		button = new JButton("Code!");
		inputField = new JTextField(20);
		outputField = new JTextField(20);
//		outputField.setEditable(false);
		JLabel label = new JLabel("output");
		
		button.addActionListener(new OkButtonActionListener());
//		inputField.addKeyListener(new InputFieldKeyListener());
		
		Character letters[] = new Character[26];
		for (char c = 'A'; c <= 'Z'; ++c)
			letters[c - 'A'] = c;
		offsetBox = new JComboBox<Character>(letters);
		
		DocumentListener listener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (inputHasFocus) updateOutput();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if (inputHasFocus) updateOutput();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		inputField.getDocument().addDocumentListener(listener );
		
		inputField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				inputHasFocus = true;
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
			
		});
		
		outputField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				inputHasFocus = false;
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
			
		});
		
		topPanel.add(offsetBox);
		topPanel.add(inputField);
		topPanel.add(button);
		
		bottomPanel.add(label);
		bottomPanel.add(outputField);
		
		pane.add(topPanel, BorderLayout.NORTH);
		pane.add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	private void updateOutput() {
		String input, output;
		Character c = (Character) offsetBox.getSelectedItem();
		cipher.setOffset(c);
		if (inputHasFocus) {
			input = inputField.getText();
			output = cipher.encode(input);
			outputField.setText(output);
		} else {
			input = outputField.getText();
			output = cipher.decode(input);
			inputField.setText(output);
		}
	}
	
	private class OkButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			updateOutput();
		}
		
	}
	
	private class InputFieldKeyListener extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyTyped(e);
			updateOutput();
		}
		
	}
}
