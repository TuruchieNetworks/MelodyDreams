package com.turuchie.melodydreams.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turuchie.melodydreams.models.FavoritePlaylist;
import com.turuchie.melodydreams.models.FavoriteSong;
import com.turuchie.melodydreams.models.FollowedUser;
import com.turuchie.melodydreams.models.LikedUser;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;

import jakarta.validation.Valid;

@Component
public class TrackMediaUtils {
    @Autowired
    private UserService userServ;

    @Autowired
    private SongService songServ;

    @Autowired
    private PlaylistService playlistServ;

    public TrackMediaUtils(UserService userServ, SongService songServ, PlaylistService playlistServ, DateUtil dateUtil) {
        this.songServ = songServ;
        this.userServ = userServ;
    }

    public User getUser(Long userId) {
        return userServ.getOne(userId);
    }

    public Long getUserId(User user) {
        return user.getId();
    }

    public String getUserName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

    public Long getSongId(Song song) {
        return song.getId();
    }

    public Song getSong(Long songId) {
        return songServ.getOne(songId);
    }

    public Playlist getPlaylist(Long playlistId) {
        return playlistServ.getOne(playlistId);
    }

    public Long getPlaylistId(Playlist playlist) {
        return playlist.getId();
    }

    public User getUserFromFavoritedPlaylist(Long playlistId) {
        return playlistServ.getOne(playlistId).getUser();
    }

    public Long getUserIdFromFavoritedPlaylist(Long playlistId) {
        return playlistServ.getOne(playlistId).getUser().getId();
    }

    public String getPlaylistTitle(@Valid FavoritePlaylist newFavoritePlaylist) {
        return newFavoritePlaylist.getTitle();
    }

    public String getPlaylistDescription(@Valid FavoritePlaylist newFavoritePlaylist) {
        return newFavoritePlaylist.getDescription();
    }

    public User getPlaylistUser(@Valid FavoritePlaylist newFavoritePlaylist) {
        return newFavoritePlaylist.getUser();
    }
    
    public void setNewFollowedUserAttributes(
    Long userFollowingId, Long userFollowedId,
    String userFollowingName, String userFollowedName, 
    FollowedUser newFollowedUser, User userFollowing) {
        newFollowedUser.setUser(userFollowing);
        newFollowedUser.setFollowingUserId(userFollowingId);
        newFollowedUser.setFollowingUserName(userFollowingName);
        newFollowedUser.setFollowedUserId(userFollowedId);
        newFollowedUser.setFollowedUserName(userFollowedName);
    }
    
    public void setNewLikeUserAttributes(LikedUser newLikedUser, User likingUser,
    	Long userLikedId, Long likingUserId, String userLikedName, String likingUserName) {
        newLikedUser.setUser(likingUser);
        newLikedUser.setUserLikedId(userLikedId);
        newLikedUser.setLikingUserId(likingUserId);
        newLikedUser.setLikedUserName(userLikedName);
        newLikedUser.setLikingUserName(likingUserName);
    }
    
    public void setNewFavoritePlaylistAttributes(FavoritePlaylist newFavoritePlaylist, User user, 
    	Long favoritedPlaylistId, Long favoritedPlaylistUserId, Playlist playlist, String title, String description) {
    	newFavoritePlaylist.setUser(user);
    	newFavoritePlaylist.setTitle(title);
    	newFavoritePlaylist.setDescription(description);
    	newFavoritePlaylist.setFavoritedPlaylistId(favoritedPlaylistId);
    	newFavoritePlaylist.setFavoritedPlaylistUserId(favoritedPlaylistUserId);
    }
    
    public void setNewFavoriteSongAttributes(FavoriteSong newFavoriteSong, User favoritingUser, Song favoritedSong, Long favoritedSongId) {
    	newFavoriteSong.setSong(favoritedSong);
    	newFavoriteSong.setUser(favoritingUser);
    	newFavoriteSong.setFavoritedSongId(favoritedSongId);
    }

    public String generateSuccessNotification(User userObj) {
        return "Successfully Following " + userObj.getFirstName() + "!";
    }

    public String generateFailureNotification(User userObj) {
        return "Already Following " + userObj.getFirstName() + "!";
    }
 
    public String generateFavoriteSongsSuccessNotification(Song songObj) {
        return "Successfully Added" + songObj.getTrackTitle() + " " + "To Favorites!";
    }

    public String generateFavoriteSongsFailureNotification(Song songObj) {
        return "Already Added" + songObj.getTrackTitle() + " " + "To Favorites!";
    }

    public String generateFavoritePlaylistsSuccessNotification(Playlist playlistObj) {
        return "Successfully Added" + playlistObj.getTitle() + " " + "To Favorites!";
    }

    public String generateFavoritePlaylistsFailureNotification(Playlist playlistObj) {
        return "Already Added" + playlistObj.getTitle() + " " + "To Favorites!";
    }

    public String generateLikeSuccessNotification(User userObj) {
        return "Successfully Liked " + userObj.getFirstName() + "!";
    }

    public String generateLikeFailureNotification(User userObj) {
        return "Already Liked " + userObj.getFirstName() + "!";
    }
}
