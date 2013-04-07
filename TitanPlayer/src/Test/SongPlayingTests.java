package Test;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import org.junit.Test;

import Logic.Song;
import TitanPlayer.PlayerGUI;

public class SongPlayingTests {

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
		
		// First adds songs
		TitanGUI.addSongToPlayerLibrary("Music,The Mowglis,Love's Not Dead,San Francisco,FAKEDIRECTORY");
		Song thisSong = new Song("This", "Is","A","New","Song");
		TitanGUI.addSongToPlayerLibrary(thisSong);
		TitanGUI.addSongToPlayerLibrary("SomeMusic,Kanye West,My Beautiful Dark Twisted Fantasy,All of the Lights, SOMEDIRECTORY");
		TitanGUI.updateLists();
		try {
			TitanGUI.getMusicLibrary().saveLibrary("TestLibrary.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Tests that the songs are there
		assertEquals(TitanGUI.getMusicLibrary().getSongTitles().size(), 3);
		assertEquals(TitanGUI.getMusicLibrary().getSongTitles().contains("San Francisco"), true);
		// Tests for setting current song and playing the song
		TitanGUI.getMusicLibrary().setCurrentSong(thisSong);
		TitanGUI.setSelectedListValue(thisSong.getTitle());
		TitanGUI.Play();
		assertEquals(TitanGUI.getMusicLibrary().getCurrentSong(), thisSong);
		// Tests the forward function
		TitanGUI.Forward();
		assertEquals(TitanGUI.getMusicLibrary().getCurrentSong().getTitle(), "San Francisco");
		// Tests the stop function
		TitanGUI.Stop();
		assertEquals(TitanGUI.getMusicLibrary().getCurrentSong(), null);
	}

}
