package com.turuchie.melodydreams.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Track title cannot be blank!")
    @Size(min = 2, max = 150, message = "Track Title must be between 2 and 150 characters!")
    private String trackTitle;

    @NotBlank(message = "Track Artist must be between 3 and 50 characters!")
    @Size(min = 3, max = 50, message = "Please enter Track Artist!")
    private String trackArtist;
    
    private String album;

    @NotBlank(message = "Genre cannot be empty!")
    @Size(min = 3, max = 50, message = "Please enter the genre of your song!")
    private String genre;

    @NotBlank(message = "Track description must be between 3 and 500 characters!")
    @Size(min = 3, max = 500, message = "Please Describe Your Track!")
    private String description;

    private String audioFileName;

    private String trackImageFileName;

    @NotBlank(message = "Please Enter Track Art Cover!")
    private String trackImageDataUrl;

    @NotBlank(message = "Please Enter Audio File Url!")
    private String audioDataUrl;

    @Transient
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imageData;

    @Transient
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] audioData;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<UserPlaylist> userPlaylists;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<FavoriteSong> favoriteSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<LikedSong> likedSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<PlaylistSongs> playlistSongs;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Metrics> metrics;

    public Song() {
    }

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getTrackTitle() {
		return trackTitle;
	}
    
	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}
	
	public String getTrackArtist() {
		return trackArtist;
	}
	
	public void setTrackArtist(String trackArtist) {
		this.trackArtist = trackArtist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String album) {
		this.album = album;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrackImageFileName() {
		return trackImageFileName;
	}

	public void setTrackImageFileName(String trackImageFileName) {
		this.trackImageFileName = trackImageFileName;
	}

    public String getTrackImageDataUrl() {
		return trackImageDataUrl;
	}

    public void setTrackImageDataUrl(String trackImageDataUrl) {
		this.trackImageDataUrl = trackImageDataUrl;
	}

	public String getAudioDataUrl() {
		return audioDataUrl;
	}

	public void setAudioDataUrl(String audioDataUrl) {
		this.audioDataUrl = audioDataUrl;
	}

	public String getAudioFileName() {
		return audioFileName;
	}

	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public byte[] getAudioData() {
		return audioData;
	}

	public void setAudioData(byte[] audioData) {
		this.audioData = audioData;
	}

	public List<PlaylistSongs> getPlaylistSongs() {
		return playlistSongs;
	}

	public void setPlaylistSongs(List<PlaylistSongs> playlistSongs) {
		this.playlistSongs = playlistSongs;
	}

	public List<UserPlaylist> getUserPlaylists() {
		return userPlaylists;
	}

	public void setUserPlaylists(List<UserPlaylist> userPlaylists) {
		this.userPlaylists = userPlaylists;
	}

	public List<FavoriteSong> getFavoriteSongs() {
		return favoriteSongs;
	}

	public void setFavoriteSongs(List<FavoriteSong> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}

	public List<LikedSong> getLikedSongs() {
		return likedSongs;
	}

	public void setLikedSongs(List<LikedSong> likedSongs) {
		this.likedSongs = likedSongs;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Metrics> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
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
    }


    @Transient
    public String getTrackImagePath() {
    	if (trackImageDataUrl == null || id == null)
    		return null;
    	return "/trackArtImages/" + id + "/" + trackImageFileName;
    }
 
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}