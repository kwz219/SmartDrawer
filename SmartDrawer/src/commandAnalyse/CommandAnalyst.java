package commandAnalyse;
/**
 * Last modification time 2019/02/27
 * @author zwk
 * accept a command from CommandField and analyse it;
 */
public class CommandAnalyst {
         public static void AnalyseCommand(String command) {
        	 CommandAnalyse ca=new CommandAnalyse();
        	 ca.commandAnalyse(command);
        	 System.out.println("CommandAnalyst: "+command);
         }
}
