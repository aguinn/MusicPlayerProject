package Logic;

import java.util.ArrayList;
// Incomplete Playlist class. Not yet used. Used in future assignment.
public class Playlist {
	private String title;
	private ArrayList<Song> songList;
	public Playlist(String title){
		this.title = title;
	}
	public void addSong(Song newSong){
		songList.add(newSong);
	}
	public String getTitle(){
		return title;
	}

}
