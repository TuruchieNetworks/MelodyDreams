package com.turuchie.melodydreams.models;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String albumName;

    private String albumDescription;
    
    private boolean isPlaylist;

    @Lob
    private byte[] albumImage;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    
    @ManyToMany
    @JoinTable(
        name = "album_songs", // You can customize the table name
        joinColumns = @JoinColumn(name = "album_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<Song> songs;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;
    
    // Constructors, getters, setters
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAlbumName() {
		return albumName;
	}
	
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public String getAlbumDescription() {
		return albumDescription;
	}
	
	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}
	
	public byte[] getAlbumImage() {
		return albumImage;
	}
	
	public void setAlbumImage(byte[] albumImage) {
		this.albumImage = albumImage;
	}
		
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isPlaylist() {
		return isPlaylist;
	}
	
	public void setPlaylist(boolean isPlaylist) {
		this.isPlaylist = isPlaylist;
	}
	
	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}    // Method to play random song from the album
    public Song playRandom() {
        List<Song> albumSongs = this.getSongs();
        if (albumSongs != null && !albumSongs.isEmpty()) {
            Collections.shuffle(albumSongs);
            return albumSongs.get(0); // Assuming you want to play the first randomly selected song
        }
        return null; // Return null if no songs in the album
    }

    // Method to play all songs from the album
    public List<Song> playAll() {
        return this.getSongs();
    }

    // Method to repeat the first song from the album
    public Song repeatOne() {
        List<Song> albumSongs = this.getSongs();
        if (albumSongs != null && !albumSongs.isEmpty()) {
            return albumSongs.get(0); // Assuming you want to repeat the first song
        }
        return null; // Return null if no songs in the album
    }
    }
