package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class StateLabel extends JLabel{
       public static StateLabel st=new StateLabel();
       
       private StateLabel() {
    	            this.setPreferredSize(new Dimension(150,15));
    	            this.setBackground(Color.darkGray);
    	            this.setForeground(Color.RED);
    	            this.setHorizontalAlignment(0);
    	            this.setText(" Blank ");
    	            
       }
       
       public static StateLabel getLabel() {
    	          return st;
       }
       public  void changetext(String text) {
    	         st.setText(" "+text+" ");
       }
}
