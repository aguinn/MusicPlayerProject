package TitanPlayer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import Logic.MusicLibrary;

public class AddAccountDialog extends JDialog{
	MusicLibrary musicLib;
	JLabel emailInstructions;
	JLabel passwordInstructions;
	
	public AddAccountDialog(MusicLibrary musicLibrary){
		super();
		setModal(true);
		musicLibrary = musicLib;
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		emailInstructions = new JLabel("Email:");
		passwordInstructions = new JLabel("Password:");
		
	}

}
