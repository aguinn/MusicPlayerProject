
package TitanPlayer;

import java.awt.Dimension;

import javax.swing.JFrame;

import Logic.Song;


public class TitanPlayer {
	public static void main(String [] args){
		JFrame TitanFrame = new JFrame();
		TitanFrame.setPreferredSize(new Dimension(1000, 600));
		TitanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerGUI TitanGUI = new PlayerGUI();
		TitanGUI.createPlayer(TitanFrame);
		TitanGUI.createMenu(TitanFrame);
		TitanFrame.pack();
		TitanFrame.setVisible(true);
	}
}