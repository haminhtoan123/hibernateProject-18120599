package Swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextPane;
import java.awt.TextField;

public class ImportDSSVFrame extends JFrame {

   // private JFrame mainFrame;
    //private JLabel headerLabel;
    private JLabel statusLabel;
    private String URL;
    TextField tenLop;
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
	        FileFilter filter = new FileNameExtensionFilter("CSV document (*.csv)", "csv");
	        //fileDialog.addChoosableFileFilter(filter);
	        fileDialog.setFileFilter(filter);
	        fileDialog.setMultiSelectionEnabled(false);
	        JButton showFileDialogButton = new JButton("Open File");
	        showFileDialogButton.setBounds(280, 50, 87, 25);
	        getContentPane().add(showFileDialogButton);
	        
	        JLabel lblNewLabel = new JLabel("Nhập Tên lớp");
	        lblNewLabel.setBounds(0, 59, 85, 16);
	        getContentPane().add(lblNewLabel);
	        
	        tenLop = new TextField();
	        tenLop.setBounds(94, 54, 143, 24);
	        getContentPane().add(tenLop);
	        
	        JButton btnNewButton_1 = new JButton("Import");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		importActionPerformed(e);
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
	                    try {
							URL = file.toURL().toString();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                   statusLabel.setText( "Đang mở : "+ file.getName());
	                } else {
	                    statusLabel.setText("Open command cancelled by user.");
	                }
	            }
	        });
	        
	}
	 private void importActionPerformed(ActionEvent e)
	 {
		//  int returnVal = fileDialog.showOpenDialog(ImportDSSVFrame.this);
         // if (returnVal == JFileChooser.APPROVE_OPTION) {
           //   java.io.File file = fileDialog.getSelectedFile();
             // Import.JDBC.ImportDSSV(file.toURI().toString(), textField.getText());
		// System.out.println(tenLop.getText());


		 if(URL.equals("")) statusLabel.setText("Chưa Open file");
		 
		 URL = URL.substring(6, URL.length());
		 System.out.println(URL);
		 if(tenLop.getText().equals(""))
		 statusLabel.setText("Nhập Tên Lớp!!!");
		 
		 else
              statusLabel.setText( Import.JDBC.ImportDSSV(URL, tenLop.getText()));
        
	 }
}
