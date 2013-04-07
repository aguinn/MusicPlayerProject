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
import Logic.Playlist;

public class AddPlaylistDialog extends JDialog{
	MusicLibrary musicLibrary;
	JLabel instructions;
	JLabel playlistTitleLabel;
	JTextField playlistTitleField;
	JButton addPlaylist;
	JButton cancel;
	JPanel buttonPanel;
	String deleteSongTitle;
	public AddPlaylistDialog(MusicLibrary musicLib){
		super();
		setModal(true);
		musicLibrary = musicLib;
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		instructions = new JLabel("Type in a title to create a playlist.");
		playlistTitleLabel = new JLabel("Title: ");
		playlistTitleField = new JTextField();
		buttonPanel = new JPanel();
		playlistTitleField.setPreferredSize(new Dimension(500, 25));
		addPlaylist = new JButton("Add Playlist");
		cancel = new JButton("Cancel");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		this.add(instructions, constraints);
		constraints.gridwidth = 1;
		constraints.gridy = 1;
		this.add(playlistTitleLabel, constraints);
		constraints.gridx = 1;
		this.add(playlistTitleField, constraints);
		constraints.gridy = 2;
		constraints.gridx = 0;
		buttonPanel.add(addPlaylist);
		buttonPanel.add(cancel);
		this.add(buttonPanel, constraints);
		pack();
		
		addPlaylist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(playlistTitleField.getText().length() > 0){
					Playlist playlistToAdd = new Playlist(playlistTitleField.getText());
					musicLibrary.addPlaylist(playlistToAdd);
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
