
package TitanPlayer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.MusicLibrary;
import Logic.Song;

public class AddSongDialog extends JDialog {
	JLabel instructions;
	JLabel directoryLabel;
	JLabel artistLabel;
	JLabel genreLabel;
	JLabel titleLabel;
	JLabel albumLabel;
	JTextField songDirectory;
	JTextField artist;
	JTextField genre;
	JTextField title;
	JTextField album;
	JButton addSong;
	JButton cancel;
	JPanel buttonPanel;
	MusicLibrary musicLibrary;
	
	public AddSongDialog(MusicLibrary musicLib){
		super();
		setModal(true);
		musicLibrary = musicLib;
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		instructions = new JLabel("Type in the information for a song to add it.");
		artistLabel = new JLabel("Artist: ");
		genreLabel = new JLabel("Genre: ");
		titleLabel = new JLabel("Title: ");
		albumLabel = new JLabel("Album: ");
		directoryLabel = new JLabel("Directory: ");
		
		songDirectory = new JTextField();
		artist = new JTextField();
		genre = new JTextField();
		title = new JTextField();
		album = new JTextField();
		songDirectory.setPreferredSize(new Dimension(500, 25));
		artist.setPreferredSize(new Dimension(500, 25));
		genre.setPreferredSize(new Dimension(500, 25));
		title.setPreferredSize(new Dimension(500, 25));
		album.setPreferredSize(new Dimension(500, 25));
		
		addSong = new JButton("Add");
		cancel = new JButton("Cancel");
		buttonPanel = new JPanel();
		buttonPanel.add(addSong);
		buttonPanel.add(cancel);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		this.add(instructions, constraints);
		
		constraints.gridwidth = 1;
		constraints.gridy = 1;
		this.add(artistLabel, constraints);
		constraints.gridx = 1;
		this.add(artist, constraints);
		
		constraints.gridy = 2;
		constraints.gridx = 0;
		this.add(albumLabel, constraints);
		constraints.gridx = 1;
		this.add(album, constraints);
		
		constraints.gridy = 3;
		constraints.gridx = 0;
		this.add(titleLabel, constraints);
		constraints.gridx = 1;
		this.add(title, constraints);
		
		constraints.gridy = 4;
		constraints.gridx = 0;
		this.add(genreLabel, constraints);
		constraints.gridx = 1;
		this.add(genre, constraints);
		
		constraints.gridy = 5;
		constraints.gridx = 0;
		this.add(directoryLabel, constraints);
		constraints.gridx = 1;
		this.add(songDirectory, constraints);
		
		constraints.gridy = 6;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		this.add(buttonPanel, constraints);
		pack();
		
		addSong.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String tempArtist, tempGenre, tempTitle, tempAlbum, tempDirectory;
				tempArtist = artist.getText();
				tempGenre = genre.getText();
				tempAlbum = album.getText();
				tempTitle = title.getText();
				tempDirectory = songDirectory.getText();
				if(tempArtist.length() > 0 && tempGenre.length() > 0 && tempAlbum.length() > 0 && tempTitle.length() > 0 && tempDirectory.length() > 0){
					musicLibrary.addSong(tempGenre, tempArtist, tempAlbum, tempTitle, tempDirectory);
				}
				setVisible(false);
				dispose();
			}
		});
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}

}