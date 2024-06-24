package com.turuchie.melodydreams.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First Name is required!")
    @Size(min = 3, max = 50, message = "First Name must be between 3 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last Name is required!")
    @Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters")
    private String lastName;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotEmpty(message = "Password is required!")
    @Size(min = 8, max = 80, message = "Password must be between 8 and 80 characters")
    private String password;

    @Transient
    @NotEmpty(message = "Passwords Must Match!")
    @Size(min = 8, max = 80, message = "Confirmed Passwords Must Be Between 8 and 80 characters")
    private String confirmPassword;
    
    @NotEmpty(message = "Please Select Gender!")
    private String gender;

    @NotNull(message = "Please Your Date Of Birth!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Use this annotation for binding LocalDate
    private LocalDate dateOfBirth;
 
    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Song> songs;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Metrics> metrics;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Playlist> playlists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PlaylistSongs> playlistSongs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoritePlaylist> favoritePlaylists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteAlbum> favoriteAlbums;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavoriteSong> favoriteSongs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Album> albums;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikedAlbum> likedAlbums;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikedSong> likedSongs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LikedUser> LikedUsers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPlaylist> userPlaylists;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FollowedUser> followedUsers;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserImageDairy> userImageDairies;

    public User() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
	
	public String getGender() {
		return gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<PlaylistSongs> getPlaylistSongs() {
		return playlistSongs;
	}

	public void setPlaylistSongs(List<PlaylistSongs> playlistSongs) {
		this.playlistSongs = playlistSongs;
	}

	public List<UserImageDairy> getUserImageDairies() {
		return userImageDairies;
	}

	public void setUserImageDairies(List<UserImageDairy> userImageDairies) {
		this.userImageDairies = userImageDairies;
	}

	public List<FavoriteAlbum> getFavoriteAlbums() {
		return favoriteAlbums;
	}

	public void setFavoriteAlbums(List<FavoriteAlbum> favoriteAlbums) {
		this.favoriteAlbums = favoriteAlbums;
	}

	public List<FavoriteSong> getFavoriteSongs() {
		return favoriteSongs;
	}

	public void setFavoriteSongs(List<FavoriteSong> favoriteSongs) {
		this.favoriteSongs = favoriteSongs;
	}

	public List<FavoritePlaylist> getFavoritePlaylists() {
		return favoritePlaylists;
	}

	public void setFavoritePlaylists(List<FavoritePlaylist> favoritePlaylists) {
		this.favoritePlaylists = favoritePlaylists;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public List<LikedAlbum> getLikedAlbums() {
		return likedAlbums;
	}

	public void setLikedAlbums(List<LikedAlbum> likedAlbums) {
		this.likedAlbums = likedAlbums;
	}

	public List<LikedSong> getLikedSongs() {
		return likedSongs;
	}

	public void setLikedSongs(List<LikedSong> likedSongs) {
		this.likedSongs = likedSongs;
	}

	public List<LikedUser> getLikedUsers() {
		return LikedUsers;
	}

	public void setLikedUsers(List<LikedUser> likedUsers) {
		LikedUsers = likedUsers;
	}

	public List<UserPlaylist> getUserPlaylists() {
		return userPlaylists;
	}

	public void setUserPlaylists(List<UserPlaylist> userPlaylists) {
		this.userPlaylists = userPlaylists;
	}

	public List<FollowedUser> getFollowedUsers() {
		return followedUsers;
	}

	public void setFollowedUsers(List<FollowedUser> followedUsers) {
		this.followedUsers = followedUsers;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Metrics> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}

	public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Transient
    private String formSongs;

    public String getFormSongs() {
        return formSongs;
    }

    public void setFormSongs(String formSongs) {
        this.formSongs = formSongs;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
