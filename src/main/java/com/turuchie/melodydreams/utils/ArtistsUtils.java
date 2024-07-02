package com.turuchie.melodydreams.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
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

@Component
public class ArtistsUtils {
    @Autowired
    private UserService userServ;
 
    @Autowired
    private DateUtil dateUtil;

    public ArtistsUtils(UserService userServ, SongService songServ, DateUtil dateUtil) {
        this.userServ = userServ;
        this.dateUtil = dateUtil;
    }

    // Method To Set Single Artist Attributes
    public void setArtistAttributes(Model model, Long id) {
    	sortMostRecentSong(model, id);
    	sortMostRecentPlaylist(model, id);
    	sortMostRecentMetricRecord(model, id);
    	findPlaylistAndPlaylistSongs(model, id);
    }

    // Method To Set All Artist Attributes
    public void setSearchedArtistAttributes(Model model, List<User> matchedUsers) {
        for (User user : matchedUsers) {
            Long userId = user.getId();
            sortMostRecentSong(model, userId);
            sortMostRecentPlaylist(model, userId);
            sortMostRecentMetricRecord(model, userId);
            findPlaylistAndPlaylistSongs(model, userId);
        }
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

	// Method To Search For Users And Return Their Tracks By Characters
	public List<Song> searchUsersFirstTrackByCharacter(String trimmedSearchTerm) {
	    if (trimmedSearchTerm != null) {
	        List<User> matchedUsers = userServ.getAllUsersMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
	        List<Song> firstSearchedSong = new ArrayList<>();
	
	        for (User user : matchedUsers) {
	            if (user.getSongs() != null && !user.getSongs().isEmpty()) {
	                // Add only the first track
	                firstSearchedSong.add(user.getSongs().get(0));
	            }
	        }
	
	        return firstSearchedSong;
	    }

	    return Collections.emptyList(); // Return an empty list if the search term is null
	}   

	// Method To Search For Users And Return Their Tracks By Characters
	public List<Song> searchUsersTrackByCharacter(String trimmedSearchTerm) {
	    if (trimmedSearchTerm != null) {
	        List<User> matchedUsers = userServ.getAllUsersMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
	        List<Song> searchedSongs = new ArrayList<>();
	
	        for (User user : matchedUsers) {
	            if (user.getSongs() != null && !user.getSongs().isEmpty()) {
	                // Add only their tracks by characters
	                searchedSongs.addAll(user.getSongs());
	            }
	        }
	
	        return searchedSongs;
	    }
	    return Collections.emptyList(); // Return an empty list if the search term is null
	}

	// Populate followed users' songs and related attributes into the model
    public void populateFollowedUsersSongs(Model model, Long userId) {
        User user = userServ.getOne(userId);

        if (user != null) {
            List<FollowedUser> followedUsers = user.getFollowedUsers();

            // Lists to hold followed users' songs and following users' songs
            List<Song> followedUsersSongs = new ArrayList<>();

            for (FollowedUser followedUser : followedUsers) {
            	Long followingUserId = followedUser.getFollowingUserId();
            	User followingUser = userServ.getOne(followingUserId);
                followedUsersSongs.addAll(followingUser.getSongs());
            }

            // Add followed users and their songs to the model
            model.addAttribute("followedUsers", followedUsers);
            model.addAttribute("followedUsersSongs", followedUsersSongs);

            // Add any other necessary attributes to the model based on your requirements
        } else {
            model.addAttribute("error", "User not found.");
        }
    }

    // Method to search for followed users followers by characters
    public List<FollowedUser> returnSearchedUserFollowedUserByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
	        List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm);
	        List<FollowedUser> searchedFollowedUser = new ArrayList<>();
	
	        for (User user : matchedUsers) {
	            if (user.getFollowedUsers() != null) {
	                searchedFollowedUser.addAll(user.getFollowedUsers());
	            }
	        }
	
	        return searchedFollowedUser;
	    }
		return null;
    }

    // Method to search for followed users songs by characters
    public List<Song> returnSearchedUserFollowedSongsByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
	        List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm);
	        List<Song> searchedFollowedUserSongs = new ArrayList<>();
	
	        for (User user : matchedUsers) {
	            if (user.getFollowedUsers() != null) {
                    searchedFollowedUserSongs.addAll(user.getSongs());
	            }
	        }
	
	        return searchedFollowedUserSongs;
	    }
		return null;
    }

    // Method to search for followers songs by characters
    public List<Song> returnSearchedUserFollowedUserSongsByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
	        List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm);
	        List<Song> searchedFollowedUserSongs = new ArrayList<>();
	
	        for (User user : matchedUsers) {
	            if (user.getFollowedUsers() != null) {
                    for (FollowedUser followedUser : user.getFollowedUsers()) {
                    	searchedFollowedUserSongs.addAll(followedUser.getUser().getSongs());
                    }
	            }
	        }
	
	        return searchedFollowedUserSongs;
	    }
		return null;
    }

    // Populate liked songs and related attributes into the model
    public void populateLikedSongs(Model model, Long userId) {
        User user = userServ.getOne(userId);

        if (user != null) {
            List<LikedUser> likedUsers = user.getLikedUsers();

            // Lists to hold liked users' songs
            List<Song> likedUsersSongs = new ArrayList<>();

            for (LikedUser likedUser : likedUsers) {
                Long likedUserId = likedUser.getUserLikedId();
                User likedUserEntity = userServ.getOne(likedUserId);
                if (likedUserEntity != null) {
                    likedUsersSongs.addAll(likedUserEntity.getSongs());
                }
            }

            // Add liked users and their songs to the model
            model.addAttribute("likedUsers", likedUsers);
            model.addAttribute("likedUsersSongs", likedUsersSongs);

            // Add any other necessary attributes to the model based on your requirements
        } else {
            model.addAttribute("error", "User not found.");
        }
    }

    // Method to search for liked users by characters
    public List<LikedUser> returnSearchedLikedUserByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
            List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm);
            List<LikedUser> searchedLikedUsers = new ArrayList<>();

            for (User user : matchedUsers) {
                if (user.getLikedUsers() != null) {
                    searchedLikedUsers.addAll(user.getLikedUsers());
                }
            }

            return searchedLikedUsers;
        }
        return null;
    }

    // Method to search for liked users' songs by characters
    public List<Song> returnSearchedUserLikedSongsByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
            List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm);
            List<Song> searchedLikedUserSongs = new ArrayList<>();

            for (User user : matchedUsers) {
                if (user.getLikedUsers() != null) {
                    for (LikedUser likedUser : user.getLikedUsers()) {
                        searchedLikedUserSongs.addAll(likedUser.getUser().getSongs());
                    }
                }
            }

            return searchedLikedUserSongs;
        }
        return null;
    }

    // Populate liked users and related attributes into the model
    public void populateLikedUsers(Model model, Long userId) {
        User user = userServ.getOne(userId);

        if (user != null) {
            List<LikedUser> likedUsers = user.getLikedUsers();

            // Lists to hold liked users
            List<User> likedUsersList = new ArrayList<>();

            for (LikedUser likedUser : likedUsers) {
                Long likedUserId = likedUser.getUserLikedId();
                User likedUserEntity = userServ.getOne(likedUserId);
                if (likedUserEntity != null) {
                    likedUsersList.add(likedUserEntity);
                }
            }

            // Add liked users to the model
            model.addAttribute("likedUsers", likedUsersList);

            // Add any other necessary attributes to the model based on your requirements
        } else {
            model.addAttribute("error", "User not found.");
        }
    }

    // Method to search for liked users' followers by characters
    public List<LikedUser> returnSearchedUserLikedUserByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
            List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm);
            List<LikedUser> searchedLikedUsers = new ArrayList<>();

            for (User user : matchedUsers) {
                if (user.getLikedUsers() != null) {
                    searchedLikedUsers.addAll(user.getLikedUsers());
                }
            }

            return searchedLikedUsers;
        }
        return null;
    }

    // Helper method to find playlist and playlist songs
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

    // Helper method to sort metric record by start date and populate model attributes
    public Metrics sortMostRecentMetricRecord(Model model, Long userId) {
        // Fetch the logged-in user
        User loggedInUser = userServ.getOne(userId);

        // Get the follow-up records for the logged-in user
        List<Metrics> metrics = loggedInUser.getMetrics();

        // Sort the follow-up records by start date in descending order
        metrics.sort(Comparator.comparing(Metrics::getCreatedAt).reversed());

        // Get the most recent Metric Record (if any)
        Metrics mostRecentMetrics = metrics.isEmpty() ? null : metrics.get(0);

        // Add model attributes related to the most recent Metric Record
        if (mostRecentMetrics != null) {
            LocalDateTime createdAt = mostRecentMetrics.getCreatedAt();
            long accountLengthDays = dateUtil.calculateDaysLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
            long accountLengthYears = dateUtil.calculateLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
            long accountLengthMonths = dateUtil.calculateMonthsLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());

            String dayFormattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
            String formattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

            model.addAttribute("mostRecentMetricCreatedAt", formattedCreatedAt);
            model.addAttribute("mostRecentMetricDaysCreatedAt", dayFormattedCreatedAt);
            model.addAttribute("mostRecentMetricAccountDaysHistory", accountLengthDays);
            model.addAttribute("mostRecentMetricAccountMonthsHistory", accountLengthMonths);
            model.addAttribute("mostRecentMetricAccountYearsHistory", accountLengthYears);
            model.addAttribute("mostRecentMetrics", mostRecentMetrics);
        }

        return mostRecentMetrics;
    }
 
    // Helper method to sort users song by start date and populate model attributes
    public Song sortMostRecentSong(Model model, Long userId) {
      // Fetch the logged-in user
      User loggedInUser = userServ.getOne(userId);

      // Get all songs for the logged-in user
      List<Song> songs = loggedInUser.getSongs();

      // Sort all songs by start date in descending order
      songs.sort(Comparator.comparing(Song::getCreatedAt).reversed());

      // Get the most recent Song (if any)
      Song mostRecentSong = songs.isEmpty() ? null : songs.get(0);

      // Add model attributes related to the most recent Song Record
      if (mostRecentSong != null) {
          LocalDateTime createdAt = mostRecentSong.getCreatedAt();
          long accountLengthDays = dateUtil.calculateDaysLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
          long accountLengthYears = dateUtil.calculateLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
          long accountLengthMonths = dateUtil.calculateMonthsLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());

          String dayFormattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
          String formattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

          model.addAttribute("mostRecentSongCreatedAt", formattedCreatedAt);
          model.addAttribute("mostRecentSongDaysCreatedAt", dayFormattedCreatedAt);
          model.addAttribute("mostRecentSongAccountDaysHistory", accountLengthDays);
          model.addAttribute("mostRecentSongAccountMonthsHistory", accountLengthMonths);
          model.addAttribute("mostRecentSongAccountYearsHistory", accountLengthYears);
          model.addAttribute("mostRecentSongs", mostRecentSong);
      }

      return mostRecentSong;
  }

    // Helper method to sort users playlist by start date and populate model attributes
    public Playlist sortMostRecentPlaylist(Model model, Long userId) {
      // Fetch the logged-in user
      User loggedInUser = userServ.getOne(userId);

      // Get all playlists for the logged-in user
      List<Playlist> playlists = loggedInUser.getPlaylists();

      // Sort all playlists by start date in descending order
      playlists.sort(Comparator.comparing(Playlist::getCreatedAt).reversed());

      // Get the most recent Playlist (if any)
      Playlist mostRecentPlaylist = playlists.isEmpty() ? null : playlists.get(0);

      // Add model attributes related to the most recent Playlist Record
      if (mostRecentPlaylist != null) {
          List<PlaylistSongs> playlistSongList = mostRecentPlaylist.getPlaylistsongs();
          LocalDateTime createdAt = mostRecentPlaylist.getCreatedAt();
          long accountLengthDays = dateUtil.calculateDaysLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
          long accountLengthYears = dateUtil.calculateLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());
          long accountLengthMonths = dateUtil.calculateMonthsLocalDateDifference(createdAt.toLocalDate(), LocalDate.now());

          String dayFormattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
          String formattedCreatedAt = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

          model.addAttribute("mostRecentPlaylistCreatedAt", formattedCreatedAt);
          model.addAttribute("mostRecentPlaylistDaysCreatedAt", dayFormattedCreatedAt);
          model.addAttribute("mostRecentPlaylistAccountDaysHistory", accountLengthDays);
          model.addAttribute("mostRecentPlaylistAccountMonthsHistory", accountLengthMonths);
          model.addAttribute("mostRecentPlaylistAccountYearsHistory", accountLengthYears);
          model.addAttribute("mostRecentPlaylists", mostRecentPlaylist);
          model.addAttribute("mostRecentPlaylistSongs", playlistSongList);
      }

      return mostRecentPlaylist;
  }

    // Method to search for users and their insurance information by characters
    public void searchUsersTracksByCharacter(Model model, String trimmedSearchTerm) {
        // If a non-empty search value is provided
        List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm.toLowerCase());

        if (!matchedUsers.isEmpty()) {
            // Single or multiple matches found, set the flag and add to the model
            model.addAttribute("isSingleMatch", matchedUsers.size() == 1);
            model.addAttribute("matchedUserCharacterList", matchedUsers);

            // Populate Song list for each user
            List<List<Song>> searchedUserSongRecordLists = new ArrayList<>();
            for (User user : matchedUsers) {
                List<Song> searchedTrackListList = new ArrayList<>();

                // Check for null to avoid potential NullPointerException
                if (user.getSongs() != null) {
                    for (Song oneSongRecord : user.getSongs()) {
                        // You can directly add the existing Song objects to the list
                        searchedTrackListList.add(oneSongRecord);
                    }
                }

                searchedUserSongRecordLists.add(searchedTrackListList);
            }

            model.addAttribute("searchedUserSongRecordLists", searchedUserSongRecordLists);
        } else {
            // No match found, set the flag and add an empty list to the model
            model.addAttribute("isSingleMatch", false);
            model.addAttribute("matchedUserCharacterList", Collections.emptyList());
            model.addAttribute("searchedUserSongRecordLists", Collections.emptyList());
        }
    }

}