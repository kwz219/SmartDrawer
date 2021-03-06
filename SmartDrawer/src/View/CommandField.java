package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import View.DrawerPanel.DrawerPanel;
import commandAnalyse.CommandAnalyst;

public class CommandField extends JTextField implements KeyListener{

	private static CommandField Commandfield=new CommandField();
	private CommandField() {
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width-120,15);
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
				}else if(Command.equals("清空")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("print")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("printl")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.contains("Brushsize")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("img")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("killnofit")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("blank")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else if(Command.equals("撤销")) {
					DrawerPanel.getDrawer().getCommand(Command);
				}else {
					//System.out.println(Command);
				CommandAnalyst.AnalyseCommand(Command);
				DrawerPanel.getDrawer().ToBlank();
				DrawerPanel.getDrawer().addCommand(Command);
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
