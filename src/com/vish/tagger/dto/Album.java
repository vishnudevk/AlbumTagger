package com.vish.tagger.dto;

public class Album {
	long id;
	String artist;
	String title;
	String albumName;
	String albumArtUrl;
	byte[] albumArt;
	long numberOfSongs;

	public Album(long id, String artist, String title,
			String albumName, String albumArtUrl, byte[] albumArt,
			long numberOfSongs) {
		super();
		this.id = id;
		this.artist = artist;
		this.title = title;
		this.albumName = albumName;
		this.albumArtUrl = albumArtUrl;
		this.albumArt = albumArt;
		this.numberOfSongs = numberOfSongs;
	}

	@Override
	public String toString() {
		return "Album [artist=" + artist + ", title=" + title + ", albumName="
				+ albumName + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumArtUrl() {
		return albumArtUrl;
	}

	public void setAlbumArtUrl(String albumArtUrl) {
		this.albumArtUrl = albumArtUrl;
	}

	public byte[] getAlbumArt() {
		return albumArt;
	}

	public void setAlbumArt(byte[] albumArt) {
		this.albumArt = albumArt;
	}

	public long getNumberOfSongs() {
		return numberOfSongs;
	}

	public void setNumberOfSongs(long numberOfSongs) {
		this.numberOfSongs = numberOfSongs;
	}
}
