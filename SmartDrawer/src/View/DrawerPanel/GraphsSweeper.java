package View.DrawerPanel;

import Logic.ListTraverseHelper;
import Logic.PointsFittingHelper.Pointtype;
/**
 * 
 * @author zwk
 * 删除功能，通过该图形在对应图形列表中的位置来删除图形，删除后需更新PointMap
 */
public class GraphsSweeper {
	public static void delpoint_byindex(int index) {
 	   ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(),Pointtype.Singlepoint,DrawerPanel.getDrawer().mpointList.get(index).getCoordinate());
 	   DrawerPanel.getDrawer().mpointList.remove(index);
 	   DrawerPanel.getDrawer().repaint();
 }
 
 public static void delline_byindex(int index) {
 	  ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(),Pointtype.Lineend,DrawerPanel.getDrawer().lineList.get(index).getStartpoint().getCoordinate());
 	  ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(),Pointtype.Lineend,DrawerPanel.getDrawer().lineList.get(index).getEndpoint().getCoordinate());
 	  DrawerPanel.getDrawer().lineList.remove(index);
 	 DrawerPanel.getDrawer().repaint();
 }
 
 public static void deltriangle_byindex(int index) {
 	  
 	  ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(), Pointtype.Triangleend, DrawerPanel.getDrawer().triangleList.get(index).getVertex1().getCoordinate());
 	  ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(), Pointtype.Triangleend, DrawerPanel.getDrawer().triangleList.get(index).getVertex2().getCoordinate());
 	  ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(), Pointtype.Triangleend, DrawerPanel.getDrawer().triangleList.get(index).getVertex3().getCoordinate());
 	 DrawerPanel.getDrawer().triangleList.remove(index);
 	DrawerPanel.getDrawer().repaint();
 }
 
 public static void delcircle_byindex(int index) {
 	  
 	  ListTraverseHelper.delete_byCorType(PointMapDealer.getPointMap(), Pointtype.Circlecenter, DrawerPanel.getDrawer().circleList.get(index).getCenter().getCoordinate());
 	 DrawerPanel.getDrawer().circleList.remove(index);
 	DrawerPanel.getDrawer().repaint();
 }
}
