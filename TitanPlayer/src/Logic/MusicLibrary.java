
package Logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicLibrary {
	/* Everything could be performed using a single ArrayList<Song>,
	 * However, the code is simplified (for me) by having separate lists used
	 * for updating the JLists. I don't expect to be constrained in terms of memory,
	 * so the tradeoff is worth it for me until I have the time to make readable code
	 * that uses only a single ArrayList.
	 */
	private Map<String, Song> songLibraryMap = new HashMap<String, Song>();
	private Map<String, Playlist> playlistLibraryMap = new HashMap<String, Playlist>();
	private HashSet<String> artists;
	private HashSet<String> albums;
	private HashSet<String> genres;
	private HashSet<String> directories;
	private HashSet<String> songTitles;
	private File libraryFile;
	private Clip clip;
	private Song currentSong;
	
	public Song getCurrentSong(){
		return currentSong;
	}
	public Map<String, Song> getSongLibrary(){
		return songLibraryMap;
	}
	public HashSet<String> getArtists(){
		return artists;
	}
	public HashSet<String> getAlbums(){
		return albums;
	}
	public HashSet<String> getGenres(){
		return genres;
	}
	public HashSet<String> getDirectories(){
		return directories;
	}
	public HashSet<String> getSongTitles(){
		return songTitles;
	}
	public HashSet<Playlist> getPlaylists(){
		HashSet<Playlist> returnSet = new HashSet<Playlist>(playlistLibraryMap.values());
		return returnSet;
	}
	
	// The default MusicLibrary constructor is different only in that it uses a default storage file: Library.txt
	public MusicLibrary(){
		artists = new HashSet<String>();
		albums = new HashSet<String>();
		genres = new HashSet<String>();
		directories = new HashSet<String>();
		songTitles = new HashSet<String>();
		libraryFile = new File("Library.txt");
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
		}
	}
	
	public MusicLibrary(String fileLocation){
		//songLibrary = new ArrayList<Song>();
		artists = new HashSet<String>();
		albums = new HashSet<String>();
		genres = new HashSet<String>();
		directories = new HashSet<String>();
		songTitles = new HashSet<String>();
		libraryFile = new File(fileLocation);
	}
	/* All iterations of add song have to do two basic things:
	 * 1. Add a Song object to the ArrayList of song objects
	 * 2. Populate the appropriate lists for artists, albums, genres, and songTitles
	 */
	public void addSong(String genre, String artist, String album, String title, String directory){
		Song newSong = new Song(genre, artist, album, title, directory);
		songLibraryMap.put(title, newSong);
		if(artists.contains(artist) == false){
			artists.add(artist);
		}
		if (albums.contains(album) == false){
			albums.add(album);
		}
		if (genres.contains(genre) == false){
			genres.add(genre);
		}
		if (songTitles.contains(title) == false){
			songTitles.add(title);
		}
	}
	// This method is mostly a wrapper around the Song constructor that takes a CSV string as a parameter
	public void addSong(String CSVSong){
		Song newSong = new Song(CSVSong);
		songLibraryMap.put(newSong.getTitle(), newSong);
		if(artists.contains(newSong.getArtist()) == false){
			artists.add(newSong.getArtist());
		}
		if (albums.contains(newSong.getAlbum()) == false){
			albums.add(newSong.getAlbum());
		}
		if (genres.contains(newSong.getGenre()) == false){
			genres.add(newSong.getGenre());
		}
		if (songTitles.contains(newSong.getTitle()) == false){
			songTitles.add(newSong.getTitle());
		}
	}
	public void addSong(Song newSong){
		songLibraryMap.put(newSong.getTitle(), newSong);
		if(artists.contains(newSong.getArtist()) == false){
			artists.add(newSong.getArtist());
		}
		if (albums.contains(newSong.getAlbum()) == false){
			albums.add(newSong.getAlbum());
		}
		if (genres.contains(newSong.getGenre()) == false){
			genres.add(newSong.getGenre());
		}
		if (songTitles.contains(newSong.getTitle()) == false){
			songTitles.add(newSong.getTitle());
		}
	}
	// Gets the next song alphabetically. May be modified in the future to get it based on something else
	public Song getNextSong(Song curSong){
		Song nextSong = new Song();
		String[] tempArray = new String[songTitles.size()];
		int i = 0;
		for(String entry : songTitles){
			tempArray[i] = entry;
			i++;
		}
		Arrays.sort(tempArray);
		int index = Arrays.asList(tempArray).indexOf(curSong.getTitle());
		if(index == songTitles.size() - 1){
			index = -1;
		}
		String newSongTitle = tempArray[index + 1];
		nextSong = songLibraryMap.get(newSongTitle);
		return nextSong;
	}
	public void setCurrentSong(Song curSong){
		currentSong = curSong;
	}
	public void addPlaylist(Playlist playlist){
		playlistLibraryMap.put(playlist.getTitle(), playlist);
	}
	public void addDirectory(String directory){
		directories.add(directory);
	}
	public void addArtist(String artist){
		artists.add(artist);
	}
	public void addGenre(String genre){
		genres.add(genre);
	}
	public void addAlbum(String album){
		albums.add(album);
	}
	public void addSongTitle(String song){
		songTitles.add(song);
	}
	
	// Default saveLibrary function saves to the libraryFile, which is set by default to "Library.txt", but can be changed
	public void saveLibrary() throws IOException{
		FileWriter libraryFileWriter = new FileWriter(libraryFile, false);
		BufferedWriter libraryWriter = new BufferedWriter(libraryFileWriter);
		
		libraryWriter.write(directories.size()+ "\n");
		for(String s : directories){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(genres.size()+ "\n");
		for(String s : genres){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(artists.size()+ "\n");
		for(String s : artists){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(albums.size()+ "\n");
		for(String s : albums){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(playlistLibraryMap.size() + "\n");
		for(Map.Entry<String, Playlist> entry: playlistLibraryMap.entrySet()){
			libraryWriter.write(entry.getValue().getTitle() + "\n");			
		}
		libraryWriter.write(songLibraryMap.size() + "\n");
		for(Map.Entry<String,Song> entry : songLibraryMap.entrySet()){
			Song write = entry.getValue();
			libraryWriter.write(write.getArtist() +","+write.getAlbum()+","+write.getTitle()+","+write.getGenre()+","+write.getDirectory()+"\n");
		}
		
		libraryWriter.close();
		libraryFileWriter.close();
	}
	
	// This saveLibrary saves to a specified location. Primary useful for testing and exporting purposes.
	public void saveLibrary(String fileLocation) throws IOException{
		File libFile = new File(fileLocation);
		FileWriter libraryFileWriter = new FileWriter(libFile, false);
		BufferedWriter libraryWriter = new BufferedWriter(libraryFileWriter);
		libraryWriter.write(directories.size()+ "\n");
		for(String s : directories){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(genres.size()+ "\n");
		for(String s : genres){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(artists.size()+ "\n");
		for(String s : artists){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(albums.size()+ "\n");
		for(String s : albums){
			libraryWriter.write(s + "\n");
		}
		libraryWriter.write(playlistLibraryMap.size() + "\n");
		for(Map.Entry<String, Playlist> entry: playlistLibraryMap.entrySet()){
			libraryWriter.write(entry.getValue().getTitle() + "\n");			
		}
		libraryWriter.write(songLibraryMap.size() + "\n");
		for(Map.Entry<String,Song> entry : songLibraryMap.entrySet()){
			Song write = entry.getValue();
			libraryWriter.write(write.getArtist() +","+write.getAlbum()+","+write.getTitle()+","+write.getGenre()+","+write.getDirectory()+"\n");
		}
		libraryWriter.close();
		libraryFileWriter.close();
	}
	
	/* This method is used for creating a new MusicLibrary given a file location
	 * Making it static simplifies the code and makes it more portable
	 */
	public static MusicLibrary parseLibrary(String libraryFileLocation) throws IOException{
		MusicLibrary newLib = new MusicLibrary();
		File libraryFile = new File(libraryFileLocation);
		FileReader libReader = new FileReader(libraryFile);
		BufferedReader libraryReader = new BufferedReader(libReader);
		String curLine = libraryReader.readLine();
		if(curLine != null && curLine.length() > 0){
			System.out.println(curLine);
			int readLength = Integer.parseInt(curLine);
			for(int i = 0; i < readLength; i++){
				newLib.addDirectory(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				newLib.addGenre(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				newLib.addArtist(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				newLib.addAlbum(libraryReader.readLine());
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				Playlist newPlaylist = new Playlist(libraryReader.readLine());
				newLib.addPlaylist(newPlaylist);
			}
			readLength = Integer.parseInt(libraryReader.readLine());
			for(int i = 0; i < readLength; i++){
				Song newSong = new Song(libraryReader.readLine());
				newLib.addSong(newSong);
			}
		}
		return newLib;
	}
	
	/* This is an unused method for now.
	 * In the future, it'll be either deleted, or used as a means of retrieving a song
	 * based on the title of the song (i.e. for playing and deleting songs)
	 */
	public Song getSong(String title){
		Song newSong = new Song();
		newSong = songLibraryMap.get(title);
		return newSong;
	}
	
	public void updateSets(){
		artists.clear();
		albums.clear();
		directories.clear();
		songTitles.clear();
		genres.clear();
		for(Map.Entry<String,Song> entry : songLibraryMap.entrySet()){
			Song write = entry.getValue();
			artists.add(write.getArtist());
			albums.add(write.getAlbum());
			directories.add(write.getAlbum());
			songTitles.add(write.getTitle());
			genres.add(write.getGenre());
		}
	}
	
	public void removeSong(Song removeSong){
		songLibraryMap.remove(removeSong.getTitle());
		updateSets();
	}
	public void removeSong(String title){
		songLibraryMap.remove(title);
		updateSets();
	}
	/*
	public void playSong(Song songToPlay){
		try {
			clip.stop();
			clip.close();
			File playSongFile = new File(songToPlay.getDirectory());
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(playSongFile);
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
	}
	public void stopSong(){
		try{
		clip.stop();
		clip.close();
		}
		catch(Exception e){
			
		}
	}*/
}