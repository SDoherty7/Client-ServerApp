package embedded;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GUI extends Frame implements ActionListener{

	private JFrame frame;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField textField;
	private JButton Enter;
	public CanvasGraph canvas, canvas2, canvas3;

	//Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public GUI() {
		initialize();
		this.Enter.addActionListener(this);
	}


	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Create top panel which contains the canvas
		JPanel graph1 = new JPanel();
		graph1.setBounds(10, 11, 614, 140);
		frame.getContentPane().add(graph1);
	    graph1.setLayout(new FlowLayout());
	    
	    //Create custom canvas object and set bounds
	    this.canvas = new CanvasGraph(394, 130);
	    canvas.setBounds(10, 25, 394, 130);
	    canvas.setBackground(Color.WHITE);
	    graph1.add(canvas);
	    
		JLabel graph1Label = new JLabel("Server IP:");
		graph1.add(graph1Label);
		
		//Textfield for entering the desired server IP address
		txt1 = new JTextField(13);
		graph1.add(txt1);
		txt1.setColumns(13);
		
		
		//Create another panel for the next server temp graph
		JPanel graph2 = new JPanel();
		graph2.setBounds(11, 142, 614, 140);
		frame.getContentPane().add(graph2);
	    graph2.setLayout(new FlowLayout());
	    
	    //Create custom canvas object and set bounds
	    this.canvas2 = new CanvasGraph(394, 130);
	    canvas2.setBounds(10, 25, 394, 130);
	    canvas2.setBackground(Color.WHITE);
	    graph2.add(canvas2);
	    
		JLabel graph2Label = new JLabel("Server IP:");
		graph2.add(graph2Label);
		
		txt2 = new JTextField(13);
		graph2.add(txt2);
		txt2.setColumns(13);
		
		//Third and final panel for the temperature graphs
		JPanel graph3 = new JPanel();
		graph3.setBounds(11, 284, 614, 140);
		frame.getContentPane().add(graph3);
	    graph3.setLayout(new FlowLayout());
	    
	    //Create custom canvas object and set bounds
	    this.canvas3 = new CanvasGraph(394, 130);
	    canvas3.setBounds(10, 25, 394, 130);
	    canvas3.setBackground(Color.WHITE);
	    graph3.add(canvas3);
	    
		JLabel graph3Label = new JLabel("Server IP:");
		graph3.add(graph3Label);
		
		txt3 = new JTextField(13);
		graph3.add(txt3);
		txt3.setColumns(13);
		
		
		//////////////////////////////////////////////////////////
		
	    //Create bottom panel for user control and implement flow layout
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 435, 600, 66);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Sample Rate(sec)");
		panel_1.add(lblNewLabel);
		
		//Choice component to allow control over the sample rate
		textField = new JTextField("5",3);
		panel_1.add(textField);
		textField.setColumns(3);
		
		Enter = new JButton("Graph");
		panel_1.add(Enter);
		
		
	}
	//Action performed when buttons are pressed
	public void actionPerformed(ActionEvent e)
    {
		if (e.getSource().equals(Enter)){
            String serverIP1 = (txt1.getText()); 
            String serverIP2 = (txt2.getText());
            String serverIP3 = (txt3.getText());
            System.out.println("Entered IP address for graph 1 is " + serverIP1);
            System.out.println("Entered IP address for graph 2 is " + serverIP2);
            System.out.println("Entered IP address for graph 3 is " + serverIP3);
            
            //Pass the enteredsample rate and IP address to the client ipConnect method
            int sampleRate = (Integer.parseInt(textField.getText()) * 1000); //Multiply by 1000 to convert to seconds
            System.out.println("Entered value is " + sampleRate);
            Client.ipConnect(serverIP1, sampleRate, canvas);
            Client.ipConnect(serverIP2, sampleRate, canvas2);
            Client.ipConnect(serverIP3, sampleRate, canvas3);
      }
    }
	
}
