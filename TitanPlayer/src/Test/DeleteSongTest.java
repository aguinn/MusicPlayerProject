package Test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Test;

import Logic.Song;
import TitanPlayer.PlayerGUI;

public class DeleteSongTest {

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
		TitanGUI.addSongToPlayerLibrary("Rock,The Grass Roots,NA,Let's Live For Today,NA");
		TitanGUI.addSongToPlayerLibrary(new Song("Blues", "Nina Simone","Not Applicable","Sinnerman","NA"));
		TitanGUI.updateLists();
		try {
			TitanGUI.getMusicLibrary().saveLibrary("TestLibrary.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(TitanGUI.getMusicLibrary().getSongTitles().size(), 2);
		TitanGUI.getMusicLibrary().removeSong("Sinnerman");
		assertEquals(TitanGUI.getMusicLibrary().getSongTitles().size(), 1);
		TitanGUI.updateLists();
		// Sinnerman should now no longer exist, nor should the associated albums, genres, artists, directories
		
	}

}
