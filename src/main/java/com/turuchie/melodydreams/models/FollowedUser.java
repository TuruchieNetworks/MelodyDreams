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
@Table(name = "followed_users")
public class FollowedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followedUserId;
    private Long followingUserId;
    private String followedUserName;
    private String followingUserName;
    
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;
    
    public FollowedUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getFollowedUserId() {
		return followedUserId;
	}

	public void setFollowedUserId(Long followedUserId) {
		this.followedUserId = followedUserId;
	}

	public Long getFollowingUserId() {
		return followingUserId;
	}

	public void setFollowingUserId(Long followingUserId) {
		this.followingUserId = followingUserId;
	}

	public String getFollowedUserName() {
		return followedUserName;
	}

	public void setFollowedUserName(String followedUserName) {
		this.followedUserName = followedUserName;
	}

	public String getFollowingUserName() {
		return followingUserName;
	}

	public void setFollowingUserName(String followingUserName) {
		this.followingUserName = followingUserName;
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
