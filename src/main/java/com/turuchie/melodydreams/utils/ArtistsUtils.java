package com.turuchie.melodydreams.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.melodydreams.models.FollowedUser;
import com.turuchie.melodydreams.models.LikedUser;
import com.turuchie.melodydreams.models.Metrics;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.PlaylistSongs;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;

import jakarta.servlet.http.HttpSession;

@Component
public class ArtistsUtils {
    @Autowired
    private UserService userServ;

    @Autowired
    private SongService songServ;
 
    @Autowired
    private DateUtil dateUtil;

    public Long validateUserAndGetId(HttpSession session) {
        return (Long) session.getAttribute("user_id");
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
    public String generateSuccessNotification(User userObj) {
        return "Successfully Following " + userObj.getFirstName() + "!";
    }

    public String generateFailureNotification(User userObj) {
        return "Already Following " + userObj.getFirstName() + "!";
    }

    public String generateLikeSuccessNotification(User userObj) {
        return "Successfully Liked " + userObj.getFirstName() + "!";
    }

    public String generateLikeFailureNotification(User userObj) {
        return "Already Liked " + userObj.getFirstName() + "!";
    }

//    @Autowired
//    private SongService songServ;
//
//    @Autowired
//    private PlaylistSongsService playlistSongServ;
//
//    @Autowired
//    private PlaylistService playlistServ;

    public ArtistsUtils(UserService userServ, DateUtil dateUtil) {
        this.userServ = userServ;
        this.dateUtil = dateUtil;
    } 

    // Helper method to set common user attributes
    public void setCommonUserAttributes(User existingUser, User userToEdit) {
        userToEdit.setFirstName(existingUser.getFirstName());
        userToEdit.setLastName(existingUser.getLastName());
        userToEdit.setEmail(existingUser.getEmail());
        userToEdit.setPassword(existingUser.getPassword());
        userToEdit.setConfirmPassword(existingUser.getConfirmPassword());
        userToEdit.setDateOfBirth(existingUser.getDateOfBirth());
        userToEdit.setGender(existingUser.getGender());
    }

	// Method to add Common Attributes to User
	public void addOneMatchedUserCommonAttribute(Model model, String trimmedSearchTerm ) {
        // If a non-empty search value is provided
        User matchedUserFirstName = userServ.getOneUserByName(trimmedSearchTerm.toLowerCase());
        User matchedUserFullName = userServ.getOneUserByFullName(trimmedSearchTerm.toLowerCase());
        List<User> matchedUsers = userServ.getAllUsersMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
        List<User> matchedSearchedUsers = userServ.getAllUsersMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
        model.addAttribute("matchedUsers", matchedUsers);
        model.addAttribute("matchedSearchedUsers", matchedSearchedUsers);
        model.addAttribute("matchedUserFirstName", matchedUserFirstName);
        model.addAttribute("matchedUserFullName", matchedUserFullName);
	}  

    public void findPlaylistAndPlaylistSongs(Model model, Long userId) {
        // Retrieve the user by ID
        User user = userServ.getOne(userId);

        if (user != null) {
            // Add user details to the model
            model.addAttribute("userDetails", user);

            // Retrieve the user's playlists
            List<Playlist> playlists = user.getPlaylists();
            List<PlaylistSongs> allUserPlaylistSongs = new ArrayList<>();
            List<Long> playlistAccountLengths = new ArrayList<>();
            List<Long> playlistSongAccountLengths = new ArrayList<>();

            if (playlists != null) {
                for (Playlist playlist : playlists) {
                    List<PlaylistSongs> playlistSongs = playlist.getPlaylistsongs();
                    if (playlistSongs != null) {
                        allUserPlaylistSongs.addAll(playlistSongs);

                        LocalDate createdAt = playlist.getCreatedAt().toLocalDate();
                        long playlistAccountDays = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.DAYS);
                        playlistAccountLengths.add(playlistAccountDays);

                        for (PlaylistSongs song : playlistSongs) {
                            LocalDate songCreatedAt = song.getCreatedAt().toLocalDate();
                            long playlistSongAccountDays = dateUtil.calculateDateDifference(songCreatedAt, LocalDate.now(), ChronoUnit.DAYS);
                            playlistSongAccountLengths.add(playlistSongAccountDays);
                        }
                    }
                }
            }

            model.addAttribute("allUserPlaylistSongLists", allUserPlaylistSongs);
            model.addAttribute("allPlaylistSongAccountHistories", playlistSongAccountLengths);
            model.addAttribute("allPlaylistsLists", playlists);
            model.addAttribute("allPlaylistAccountHistories", playlistAccountLengths);
        } else {
            model.addAttribute("error", "User not found.");
        }
    }

    // Helper method to sort past medical history by start date and populate model attributes
    public Metrics sortMostRecentMetricRecord(Model model, Long userId) {
        // Fetch the logged-in user
        User loggedInUser = userServ.getOne(userId);

        // Get the follow-up records for the logged-in user
        List<Metrics> metrics = loggedInUser.getMetrics();

        // Sort the follow-up records by start date in descending order
        metrics.sort(Comparator.comparing(Metrics::getCreatedAt).reversed());

        // Get the most recent FollowUp Record (if any)
        Metrics mostRecentMetrics = metrics.isEmpty() ? null : metrics.get(0);

        // Add model attributes related to the most recent FollowUp Record
        if (mostRecentMetrics != null) {
            LocalDateTime createdAt = mostRecentMetrics.getCreatedAt();
            long accountLengthDays = dateUtil.calculateDaysLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
            long accountLengthYears = dateUtil.calculateLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
            long accountLengthMonths = dateUtil.calculateMonthsLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());

            String dayFormattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
            String formattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

            model.addAttribute("mostRecentFollowUpCreatedAt", formattedCreatedAt);
            model.addAttribute("mostRecentFollowUpDaysCreatedAt", dayFormattedCreatedAt);
            model.addAttribute("mostRecentFollowUpAccountDaysHistory", accountLengthDays);
            model.addAttribute("mostRecentFollowUpAccountMonthsHistory", accountLengthMonths);
            model.addAttribute("mostRecentFollowUpAccountYearsHistory", accountLengthYears);
            model.addAttribute("mostRecentMetrics", mostRecentMetrics);
        }

        return mostRecentMetrics;
    }
}