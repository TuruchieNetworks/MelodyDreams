package com.turuchie.melodydreams.models;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorite_albums")
public class FavoriteAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long albumFavoritedId;
    private Long userFavoritingAlbumId;
    private String albumName;
    private String userFavoritingAlbumUserName;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    public FavoriteAlbum() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getAlbumFavoritedId() {
		return albumFavoritedId;
	}

	public void setAlbumFavoritedId(Long albumFavoritedId) {
		this.albumFavoritedId = albumFavoritedId;
	}

	public Long getUserFavoritingAlbumId() {
		return userFavoritingAlbumId;
	}

	public void setUserFavoritingAlbumId(Long userFavoritingAlbumId) {
		this.userFavoritingAlbumId = userFavoritingAlbumId;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getUserFavoritingAlbumUserName() {
		return userFavoritingAlbumUserName;
	}

	public void setUserFavoritingAlbumUserName(String userFavoritingAlbumUserName) {
		this.userFavoritingAlbumUserName = userFavoritingAlbumUserName;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
