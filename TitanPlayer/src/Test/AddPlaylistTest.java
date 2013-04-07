package Test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Test;

import Logic.Playlist;
import TitanPlayer.PlayerGUI;

public class AddPlaylistTest {

	@Test
	public void test() {
		// Code for setting up the JFrame and the Player
		JFrame TitanFrame = new JFrame();
		TitanFrame.setPreferredSize(new Dimension(1000, 600));
		TitanFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerGUI TitanGUI = new PlayerGUI();
		TitanGUI.createPlayer(TitanFrame);
		TitanGUI.createMenu(TitanFrame);
		TitanFrame.pack();
		TitanFrame.setVisible(true);
		
		// Logic for adding a playlist programmatically and updating the list on the GUI
		TitanGUI.getMusicLibrary().addPlaylist(new Playlist("A test playlist"));
		TitanGUI.updateLists();
		try {
			TitanGUI.getMusicLibrary().saveLibrary("TestLibrary.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(TitanGUI.getMusicLibrary().getPlaylists().size(), 1);

	}

}
