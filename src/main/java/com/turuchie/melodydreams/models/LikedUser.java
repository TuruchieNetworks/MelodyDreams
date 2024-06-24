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
@Table(name = "liked_users")
public class LikedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private Long userLikedId;
    private Long likingUserId;
    private String likedUserName;
    private String likingUserName;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LikedUser() {
    }

    public Long getUserLikedId() {
		return userLikedId;
	}

	public void setUserLikedId(Long userLikedId) {
		this.userLikedId = userLikedId;
	}

	public Long getLikingUserId() {
		return likingUserId;
	}

	public void setLikingUserId(Long likingUserId) {
		this.likingUserId = likingUserId;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLikedUserName() {
		return likedUserName;
	}

	public void setLikedUserName(String likedUserName) {
		this.likedUserName = likedUserName;
	}

	public String getLikingUserName() {
		return likingUserName;
	}

	public void setLikingUserName(String likingUserName) {
		this.likingUserName = likingUserName;
	}

	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
