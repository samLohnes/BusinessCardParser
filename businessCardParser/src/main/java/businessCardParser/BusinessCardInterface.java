package businessCardParser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BusinessCardInterface extends JFrame implements ActionListener{
	
	JLabel heading  = new JLabel("Business Card Information");
	JLabel nameLabel = new JLabel("Enter Name: ");
	JLabel emailLabel = new JLabel("Enter e-Mail: ");
	JLabel phoneLabel = new JLabel("Enter Phone Number: ");
	
	JTextField name  = new JTextField( 20 );
	JTextField email  = new JTextField( 20 );
	JTextField phone = new JTextField( 20 );
	
	JButton fileSelector = new JButton("Select Business Card");
	
	JButton confirmButton = new JButton("Confirm Info");
	  
	// New: the five panels
	  
	JPanel hedPanel = new JPanel();
	JPanel namePanel = new JPanel();
	JPanel emailPanel = new JPanel();
	JPanel phonePanel = new JPanel();   
	JPanel butPanel = new JPanel();
	JPanel fileSelectorPanel = new JPanel();
	
	public  BusinessCardInterface() {
		
		setTitle("Business Card Parser");
		
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		
		
		// Add components to the panels
		hedPanel.add( heading );
		namePanel.add( nameLabel );
	    namePanel.add( name );
	    emailPanel.add( emailLabel );
	    emailPanel.add( email );
	    phonePanel.add( phoneLabel );
	    phonePanel.add( phone );
	    butPanel.add( confirmButton );
	    fileSelectorPanel.add( fileSelector );
		
	    // Add Panels to Frame     
	    add( hedPanel );
	    add( heading );
	    add( namePanel );
	    add( emailPanel );
	    add( phonePanel );
//	    add( button );
	    add( fileSelectorPanel );
	    add( butPanel );

	    
	    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
	    fileSelector.addActionListener( this );
	    confirmButton.addActionListener( this ); 
		
	    
		
	}
	
	
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() == fileSelector ) {
			
			JFileChooser j = new JFileChooser();
            
            int returnValue = j.showOpenDialog(null);
            
            
            String totalString = "";
            if (returnValue == JFileChooser.APPROVE_OPTION) {
    			File selectedFile = j.getSelectedFile();
    			String fileName = selectedFile.getName();
    			String lastFour = fileName.substring(fileName.length() - 4);

    			if (lastFour.equals(".txt")) {
    				
    				try {
    					Scanner scanner = new Scanner(selectedFile);
    					while (scanner.hasNextLine()) {
    						
    						String data = scanner.nextLine();
    						totalString = totalString + data + "\n";
    						
    					}
    						
    				} catch (FileNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
        			String info = BusinessCardParser.getContactInfo(totalString);
        			String[] bCard = info.split("\n");
        			
        			name.setText(bCard[0]);
        			phone.setText(bCard[1]);
        			email.setText(bCard[2]);
        			
        			JOptionPane.showMessageDialog(this, displayResults(bCard));
    				
    			}else {
    				
    				JOptionPane.showMessageDialog(this, "Error: Please select a .txt file");
    				
    			}
			
            }
	
		}
		if (event.getSource() == confirmButton) {
			
			
			if (name.getText().length() != 0 && phone.getText().length() != 0 && email.getText().length() != 0) {
				
				String output = BusinessCardParser.sendInformation(name.getText(), phone.getText(), email.getText());
				
				JOptionPane.showMessageDialog(this, output);
				name.setText("");
				phone.setText("");
				email.setText("");
				
				System.out.println(output);
				
				
			}else {
				
				// generate an alert telling the user that all text blocks must have input
	            JOptionPane.showMessageDialog(this, "Error: Please ensure that all\ntext blocks are filled out");

				
			}
			
		}
	}
	
	public static String displayResults(String[] bCardInfo) {
		
		String missingInfo = "";
		
		if (bCardInfo[0] == "") {
			
			missingInfo += "\nName Missing";
			
		}
		if (bCardInfo[1] == "") {
			
			missingInfo += "\nPhone Number Missing";
			
		}
		if (bCardInfo[2] == "") {
			
			missingInfo += "\ne-Mail missing";
			
		}
		
		if (missingInfo.length() == 0) {
			
			return "Process complete: Please check to confirm\nthat extracted information is accurate!";
			
		}else {
			
			return "Error: Please Enter the below missing Information" + missingInfo;
			
		}
		
		
		
	}

}
