package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ImportTKBFrame extends JFrame {

	private JPanel contentPane;
	private JLabel statusLabel;
	private String URL;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportTKBFrame frame = new ImportTKBFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ImportTKBFrame() {
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
	   /*     
	        JLabel lblNewLabel = new JLabel("Nhập Tên lớp");
	        lblNewLabel.setBounds(0, 59, 85, 16);
	        getContentPane().add(lblNewLabel);
	        */
//	        URL = new TextField();
//	        URL.setBounds(94, 54, 143, 24);
//	        getContentPane().add(URL);
	        
	        JButton btnNewButton_1 = new JButton("Import");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		importActionPerformed( e);
	        	}
	        });
	        btnNewButton_1.setBounds(150, 84, 129, 43);
	        getContentPane().add(btnNewButton_1);
	        showFileDialogButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int returnVal = fileDialog.showOpenDialog(ImportTKBFrame.this);
	                if (returnVal == JFileChooser.APPROVE_OPTION) {
	                    java.io.File file = fileDialog.getSelectedFile();
	                    try {
							URL = file.toURL().toString();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                   // Import.JDBC.ImportDSSV(file.toURI().toString(), textField.getText());
	                  //  System.out.println(file.toURI().toString());
	                 //  statusLabel.setText( Import.Import.ImportTKB(file.toURI().toString()));
	                } else {
	                    statusLabel.setText("Open command cancelled by user.");
	                }
	            }
	        });
	}
	private void importActionPerformed(ActionEvent e)
	{

		 if(URL.equals("")) statusLabel.setText("Chưa Open file");
		 else
		 {
			 URL = URL.substring(6, URL.length());
			 System.out.println(URL);
             statusLabel.setText( Import.Import.ImportTKB(URL));
		 }
	/*	 if(tenLop.getText().equals(""))
		 statusLabel.setText("Nhập Tên Lớp!!!");*/
		 
		
	}

}
