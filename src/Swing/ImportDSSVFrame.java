package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.TextField;

public class ImportDSSVFrame extends JFrame {

   // private JFrame mainFrame;
    //private JLabel headerLabel;
    private JLabel statusLabel;

	/**
	 * Launch the application.
	 */

    public static void main(String[] args) {
        ImportDSSVFrame demo = new  ImportDSSVFrame();
    }

	/**
	 * Create the frame.
	 */

	 public ImportDSSVFrame() {
		 setTitle("Import Danh Sách Sinh Viên");
	     setSize(500, 250);
	     addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e)
	            {
	                System.out.println("Closed");
	                e.getWindow().dispose();
	            }
	        });
	        //headerLabel = new JLabel("", JLabel.CENTER);
	        statusLabel = new JLabel("", JLabel.CENTER);
	        statusLabel.setLocation(0, 136);
	        statusLabel.setSize(482, 67);
	        getContentPane().setLayout(null);
	        getContentPane().add(statusLabel);
	        setVisible(true);
	       // headerLabel.setText("Control in action: JFileChooser");
	        final JFileChooser fileDialog = new JFileChooser();
	        JButton showFileDialogButton = new JButton("Open File");
	        showFileDialogButton.setBounds(280, 50, 87, 25);
	        getContentPane().add(showFileDialogButton);
	        
	        JLabel lblNewLabel = new JLabel("Nhập Tên lớp");
	        lblNewLabel.setBounds(0, 59, 85, 16);
	        getContentPane().add(lblNewLabel);
	        
	        TextField textField = new TextField();
	        textField.setBounds(94, 54, 143, 24);
	        getContentPane().add(textField);
	        
	        JButton btnNewButton_1 = new JButton("Import");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnNewButton_1.setBounds(150, 84, 129, 43);
	        getContentPane().add(btnNewButton_1);
	        showFileDialogButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int returnVal = fileDialog.showOpenDialog(ImportDSSVFrame.this);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    java.io.File file = fileDialog.getSelectedFile();
	                   // Import.JDBC.ImportDSSV(file.toURI().toString(), textField.getText());
	      
	                    statusLabel.setText( Import.JDBC.ImportDSSV(file.toURI().toString(), textField.getText()));
	                } else {
	                    statusLabel.setText("Open command cancelled by user.");
	                }
	            }
	        });
	        
	}
}
