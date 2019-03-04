package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import commandAnalyse.CommandAnalyst;

public class CommandField extends JTextField implements KeyListener{

	private static CommandField Commandfield=new CommandField();
	private CommandField() {
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width,15);
		this.setEditable(true);
		this.setBackground(Color.LIGHT_GRAY);
		this.setForeground(Color.BLACK);
		addKeyListener(this);
		
	   
	}
	
	public static CommandField getField() {
		return Commandfield;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("keypressed");
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			String Command=this.getText();
			if(Command!="") {
				if(Command.equals("cl")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("ct")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("cc")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else {
				CommandAnalyst.AnalyseCommand(Command);
				}

			}
			this.setText("");
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
