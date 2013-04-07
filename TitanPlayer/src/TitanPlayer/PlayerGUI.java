
package TitanPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import Logic.MusicLibrary;
import Logic.Playlist;
import Logic.Song;


public class PlayerGUI extends JPanel{
	JTree directoryBrowser = new JTree();
	/* After looking into it a bit, I decided on JTables over JLists. JTables
	 * offer possible automatic sorting/filtering, and I may consolidate a few
	 * of the tables into a single, multi-column table in the future. 
	 * As it is, the multiple table layout is inspired by MediaMonkey, which filters
	 * the values of each table based on the selections in the other tables.
	 */
	JList playlistList;
	JList genreList;
	JList artistList;
	JList albumList;
	JList songList;
	DefaultListModel playlistModel;
	DefaultListModel genreModel;
	DefaultListModel artistModel;
	DefaultListModel albumModel;
	DefaultListModel songModel;
	JTextField songInfo;
	JTextField songTrivia;
	JButton startPauseButton;
	JButton backButton;
	JButton forwardButton;
	JButton stopButton;
	JMenuItem sortSong;
	JMenuItem sortArtist;
	JPopupMenu sortMenu;
	JPanel buttonPanel;
	JSlider musicSlider;
	ImageIcon albumArtIcon;
	JLabel albumArtLabel;
	JPanel bottomContainer;
	MusicLibrary musicLibrary;
	GridBagConstraints constraints;
	
	
	PlayerGUI thisGUI;
	
	public PlayerGUI(){
		musicLibrary = new MusicLibrary();
		
		playlistList = new JList();
		genreList = new JList();
		artistList = new JList();
		albumList = new JList();
		songList = new JList();
		
		songModel = new DefaultListModel();
		genreModel = new DefaultListModel();
		artistModel = new DefaultListModel();
		albumModel = new DefaultListModel();
		playlistModel = new DefaultListModel();
		
		
		songInfo = new JTextField();
		songTrivia = new JTextField();
		startPauseButton = new JButton("Start/Pause");
		backButton = new JButton("Back");
		forwardButton = new JButton("Forward");
		stopButton = new JButton("Stop");
		buttonPanel = new JPanel();
		musicSlider = new JSlider();
		bottomContainer = new JPanel();
		albumArtIcon = new ImageIcon("albumart.png");
		albumArtLabel = new JLabel(albumArtIcon);
		constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = constraints.weighty = 1.0;
		thisGUI = this;
		this.setLayout(new GridBagLayout());
		
		constraints.gridheight = 11;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(directoryBrowser, constraints);
		
		constraints.gridheight = 4;
		constraints.gridy = 0;
		constraints.gridx = 1;
		playlistList.setBackground(Color.gray);
		this.add(playlistList, constraints);
		
		constraints.gridy = 4;
		genreList.setBackground(Color.green);
		this.add(genreList, constraints);
		
		constraints.gridheight = 8;
		constraints.gridx = 2;
		constraints.gridy = 0;
		artistList.setBackground(Color.blue);
		this.add(artistList, constraints);
		
		constraints.gridx = 3;
		albumList.setBackground(Color.yellow);
		this.add(albumList, constraints);
		
		constraints.gridx = 4;
		songList.setBackground(Color.orange);
		this.add(songList, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 8;
		constraints.gridheight = 1;
		constraints.weighty = .4;
		songInfo.setText("Test Text");
		songInfo.setBackground(Color.pink);
		this.add(songInfo, constraints);
		
		constraints.gridx = 2;
		constraints.gridwidth = 3;
		songTrivia.setText("Test trivia text");
		songTrivia.setBackground(Color.magenta);
		this.add(songTrivia, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 10;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weighty = 0;
		this.add(bottomContainer, constraints);
		buttonPanel.add(backButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(startPauseButton);
		buttonPanel.add(forwardButton);
		bottomContainer.add(buttonPanel);
		bottomContainer.add(musicSlider);
		bottomContainer.add(albumArtLabel);
		try {
			musicLibrary = MusicLibrary.parseLibrary("Library.txt");
			updateLists();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sortSong = new JMenuItem("Sort by song title");
		sortArtist = new JMenuItem("Sort by artist name");
		sortMenu = new JPopupMenu();
		sortMenu.add(sortSong);
		sortMenu.add(sortArtist);
		
		startPauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Play();
			}
		});
		stopButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Stop();
			}
		});
		forwardButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				Forward();
			}
		});
		songList.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(SwingUtilities.isRightMouseButton(e)){
					sortMenu.show(songList, e.getX(), e.getY());
				}
			}
		});
		sortSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				HashSet<String> songTitles = musicLibrary.getSongTitles();
				String[] tempArray = new String[songTitles.size()];
				int i = 0;
				for(String entry : songTitles){
					tempArray[i] = entry;
					i++;
				}
				Arrays.sort(tempArray);				
				songModel.clear();
				for(String song : tempArray){
					songModel.addElement(song);
				}
			}	
		});
		sortArtist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				HashSet<String> artists = musicLibrary.getArtists();
				String[] tempArray = new String[artists.size()];
				int i = 0;
				for(String entry : artists){
					tempArray[i] = entry;
					i++;
				}
				Arrays.sort(tempArray);
				songModel.clear();
				for(String entry : tempArray){
					for(Song tempSong: musicLibrary.getSongLibrary().values()){
						if(tempSong.getArtist() == entry){
							songModel.addElement(tempSong.getTitle());
						}
					}
				}
			}
		});
	}
	public void Forward(){
		Song curSong = musicLibrary.getCurrentSong();
		Song nextSong = musicLibrary.getNextSong(curSong);
		songInfo.setText(nextSong.getTitle() + " by " + nextSong.getArtist());
		musicLibrary.setCurrentSong(nextSong);
	}
	public void Stop(){
		musicLibrary.setCurrentSong(null);
		songInfo.setText("No song playing");
	}
	public void Play(){
		String songName = (String)songList.getSelectedValue();
		Song songToPlay = musicLibrary.getSong(songName);
		songInfo.setText(songName + " by " + songToPlay.getArtist());
		musicLibrary.setCurrentSong(songToPlay);
	}
	public void createPlayer(JFrame TitanFrame){
		TitanFrame.getContentPane().add(this);
	}
	public MusicLibrary getMusicLibrary(){
		return musicLibrary;
	}
	public void setSelectedListValue(String title){
		songList.setSelectedValue(title, false);
	}
	// Wrappers for adding songs to the musicLibrary
	public void addSongToPlayerLibrary(String CSVSong){
		musicLibrary.addSong(CSVSong);
	}
	public void addSongToPlayerLibrary(Song newSong){
		musicLibrary.addSong(newSong);
	}
	public void createMenu(JFrame TitanFrame){
		JMenuBar Menu = new JMenuBar();
		JMenu FileMenu = new JMenu("File");
		JMenuItem addSong = new JMenuItem("Add Song");
		JMenuItem deleteSong = new JMenuItem("Delete Song");
		JMenuItem addPlaylist = new JMenuItem("Add Playlist");
		JMenuItem addSongToPlaylist = new JMenuItem("Add Song to Playlist");
		Menu.add(FileMenu);
		FileMenu.add(addSong);
		FileMenu.add(deleteSong);
		FileMenu.add(addPlaylist);
		
		addSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AddSongDialog songDialog = new AddSongDialog(musicLibrary);
				songDialog.show();
				updateLists();
				try {
					musicLibrary.saveLibrary();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		deleteSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				DeleteSongDialog deleteDialog = new DeleteSongDialog(musicLibrary);
				deleteDialog.show();
				updateLists();
				try {
					musicLibrary.saveLibrary();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		addPlaylist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				AddPlaylistDialog playlistDialog = new AddPlaylistDialog(musicLibrary);
				playlistDialog.show();
				updateLists();
				try {
					musicLibrary.saveLibrary();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		// Not yet working
		addSongToPlaylist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		
		TitanFrame.setJMenuBar(Menu);
		TitanFrame.revalidate();
		TitanFrame.repaint();	
	}
	
	public void updateLists(){
		songModel.clear();
		albumModel.clear();
		genreModel.clear();
		artistModel.clear();
		playlistModel.clear();
		for(String song : musicLibrary.getSongTitles()){
			songModel.addElement(song);
		}
		for(String album : musicLibrary.getAlbums()){
			albumModel.addElement(album);
		}
		for(String genre : musicLibrary.getGenres()){
			genreModel.addElement(genre);
		}
		for(String artist : musicLibrary.getArtists()){
			artistModel.addElement(artist);
		}
		for(Playlist playlist: musicLibrary.getPlaylists()){
			playlistModel.addElement(playlist.getTitle());
		}
		
		songList.setModel(songModel);
		albumList.setModel(albumModel);
		genreList.setModel(genreModel);
		artistList.setModel(artistModel);
		playlistList.setModel(playlistModel);
	}
}