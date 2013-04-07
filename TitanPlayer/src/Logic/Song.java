
package Logic;

import java.io.File;

public class Song {
	private String genre, artist, album, title, directory, UID;
	private File songFile;
	public Song(String genre, String artist, String album, String title, String directory){
		this.setGenre(genre);
		this.setArtist(artist);
		this.setAlbum(album);
		this.setTitle(title);
		this.setDirectory(directory);
		setSongFile(new File(directory));
	}
	public Song(String CSVSong){
		String[] valueArray = CSVSong.split(",");
		this.setGenre(valueArray[0]);
		this.setArtist(valueArray[1]);
		this.setAlbum(valueArray[2]);
		this.setTitle(valueArray[3]);
		this.setDirectory(valueArray[4]);
	}
	public Song(){
		
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public File getSongFile() {
		return songFile;
	}
	public void setSongFile(File songFile) {
		this.songFile = songFile;
	}
	
	public boolean equals(Song compareSong){
		if (this.title != compareSong.getTitle()){
			return false;
		}
		if (this.artist != compareSong.getArtist()){
			return false;
		}
		if(this.album != compareSong.getAlbum()){
			return false;
		}
		if(this.title != compareSong.getTitle()){
			return false;
		}
		if(this.directory != compareSong.getDirectory()){
			return false;
		}
		return true;
	}
	public boolean equals(String title){
		if(this.title == title){
			return true;
		}
		else{
			return false;
		}
	}
}