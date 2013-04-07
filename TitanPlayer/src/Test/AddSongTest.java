package Test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Test;

import Logic.Song;
import TitanPlayer.PlayerGUI;

public class AddSongTest {

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
		
		// Test for adding a song code below:
		// (String genre, String artist, String album, String title, String directory)
		TitanGUI.addSongToPlayerLibrary("Music,The Mowglis,Love's Not Dead,San Francisco,FAKEDIRECTORY");
		TitanGUI.addSongToPlayerLibrary(new Song("This", "Is","A","New","Song"));
		TitanGUI.updateLists();
		try {
			TitanGUI.getMusicLibrary().saveLibrary("TestLibrary.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(TitanGUI.getMusicLibrary().getSongTitles().size(), 2);
		assertEquals(TitanGUI.getMusicLibrary().getSongTitles().contains("San Francisco"), true);

	}

}
