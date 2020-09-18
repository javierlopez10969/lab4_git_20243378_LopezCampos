package vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PlaceHolder extends JFrame {
	private JTextField field;
	//String Holder
	private String holderString;
	private String string;
	
	public PlaceHolder(String text){
		this.field = new JTextField(text);
		this.holderString = text;
		this.string= "";
		
		field.addKeyListener (new KeyAdapter() {
	        int i = 0 ;
		   	 @Override
		   	public void keyTyped(KeyEvent key) {
		   		if (key.getKeyChar() ==KeyEvent.VK_BACK_SPACE && i==0)  {
		   			field.setText(holderString);
					}
		   		else if (key.getKeyChar()!=KeyEvent.VK_BACK_SPACE) {
		   			if (field.getText().equals(holderString) && i==0 ) {
		   				field.setText("");
						}
		   			i = i+1;
		   			setText(field.getText());
					}
		   		else if (field.getText().equals("") ) {
		   			setText(field.getText());
		   			field.setText(holderString);
		   			i=0;
				}
	   	 	}
		} );
	}
	
	public void setText(String newString) {this.string = newString;}
	public String getText() {return this.string;}
	public JTextField getField() {return this.field;}
}
	

