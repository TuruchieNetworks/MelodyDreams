package com.turuchie.melodydreams.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.turuchie.melodydreams.models.Metrics;
import com.turuchie.melodydreams.models.Playlist;
import com.turuchie.melodydreams.models.PlaylistSongs;
import com.turuchie.melodydreams.models.Song;
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.SongService;
import com.turuchie.melodydreams.services.UserService;

@Component
public class PlaylistUtils {
   	@Autowired
   	private SongService songServ;

   	@Autowired
   	private UserService userServ;

   	@Autowired
   	private PlaylistService playlistServ;

   	@Autowired
   	private DateUtil dateUtil;
  
	@Autowired
   	private ArtistsUtils artistUtil;

    public PlaylistUtils (UserService userServ, DateUtil dateUtil, 
       	SongService songServ, ArtistsUtils artistUtil, PlaylistService playlistServ) {
        this.userServ = userServ;
        this.songServ = songServ;
        this.dateUtil = dateUtil;
        this.artistUtil = artistUtil;
        this.playlistServ = playlistServ;
    }
    
    // Method to search for users by characters
    public List<User> searchUsersByCharacters(String trimmedSearchTerm) {
        List<User> matchedUsers = userServ.getAllUsersMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
        return (matchedUsers != null) ? matchedUsers : Collections.emptyList();
    }
    
    // Method to search for users by characters
    public List<Song> searchSongsByCharacters(String trimmedSearchTerm) {
        List<Song> matchedSongs = songServ.getAllSongsMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
        return (matchedSongs != null) ? matchedSongs : Collections.emptyList();
    }
    
    // Method to search for users by characters
    public List<Playlist> searchPlaylistsByCharacters(String trimmedSearchTerm) {
        List<Playlist> matchedPlaylists = playlistServ.getPlaylistsByLetters(trimmedSearchTerm.toLowerCase());
        return (matchedPlaylists != null) ? matchedPlaylists : Collections.emptyList();
    }

    // Method to search for songs and return their tracks by characters
    public List<Song> searchSongsFirstTrackByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
            List<Song> matchedSongs = songServ.getAllSongsMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
            List<Song> firstSearchedSongs = new ArrayList<>();

            for (Song song : matchedSongs) {
                // Add only the first track of each matched song
                firstSearchedSongs.add(song);
                break;
            }

            return firstSearchedSongs;
        }
        return Collections.emptyList(); // Return an empty list if the search term is null
    }

    // Method to search for songs and return all tracks by characters
    public List<Song> searchSongsTrackByCharacter(String trimmedSearchTerm) {
        if (trimmedSearchTerm != null) {
            List<Song> matchedSongs = songServ.getAllSongsMatchingSearchTerm(trimmedSearchTerm.toLowerCase());
            return matchedSongs;
        }
        return Collections.emptyList(); // Return an empty list if the search term is null
    }

    public void findPlaylistAndPlaylistSongs(Model model, Long userId) {   
        // Retrieve the user by ID
        User user = userServ.getOne(userId);
	        if (user != null) {
	        	// Add user details to the model
	            model.addAttribute("userDetails", user);
	
	            // Retrieve the user's playlists
	            List<Playlist> playlists = user.getPlaylists();
	            List<PlaylistSongs> allPlaylistSongs = new ArrayList<>();
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
		                         allPlaylistSongs.add(song);
	                             playlistSongAccountLengths.add(playlistSongAccountDays);
	                        }
	                    }
	                }
	            }

	            model.addAttribute("allPlaylistsLists", playlists);
	            model.addAttribute("allPlaylistSongs", allPlaylistSongs);
	            model.addAttribute("allUserPlaylistSongLists", allUserPlaylistSongs);
	            model.addAttribute("allPlaylistAccountHistories", playlistAccountLengths);
	            model.addAttribute("allPlaylistSongAccountHistories", playlistSongAccountLengths);
	        } else {
	            model.addAttribute("error", "User not found.");
	        }
	    }

	    // Method to search for single playlist by characters
	    public List<Playlist> returnFirstPlaylistByCharacters(String trimmedSearchTerm) {
	        if (trimmedSearchTerm != null) {
	            List<User> matchedUsers = searchUsersByCharacters(trimmedSearchTerm);
	            List<Playlist> firstPlaylists = new ArrayList<>();

	            for (User user : matchedUsers) {
	                List<Playlist> playlists = user.getPlaylists();
	                if (playlists != null && !playlists.isEmpty()) {
	                    firstPlaylists.add(playlists.get(0));
	                }
	            }

	            return firstPlaylists;
	        }
	        return Collections.emptyList();
	    }	

	    // Main method to search for playlist and populate playlist songs by characters
	    public void searchPlaylistByCharacter(Model model, String trimmedSearchTerm) {
	      // If a non-empty search value is provided
	      List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm.toLowerCase());
	      List<Playlist> matchedPlaylist = playlistServ.getPlaylistsByLetters(trimmedSearchTerm.toLowerCase());
	      if (!matchedUsers.isEmpty()) {
	          // Single or multiple matches found, set the flag and add to the model
	          model.addAttribute("isSingleMatch", matchedUsers.size() == 1);
	          model.addAttribute("matchedSearchUserCharacterList", matchedUsers);

	          // Populate Playlist list for each user
	          List<Playlist> searchedPlaylistLists = new ArrayList<>();
	          List<PlaylistSongs> playlistSongLists = new ArrayList<>();
	          for (User user : matchedUsers) {

	              // Check for null to avoid potential NullPointerException
	              if (matchedPlaylist != null) {
	                  for (Playlist onePlaylist : matchedPlaylist) {
	                      // Handle null Playlist or User
	                      if (onePlaylist == null || onePlaylist.getUser() == null) {
	                          continue;
	                      }

	                      // Calculate date differences
	                      LocalDate createdAt = onePlaylist.getCreatedAt().toLocalDate();
	                      LocalDate playlistUserAge = onePlaylist.getUser().getDateOfBirth();

	                      String formattedDayPlaylistCreatedAtDate = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
	                      String formattedPlaylistCreatedAtDate = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

	                      long searchedUserAge = dateUtil.calculateDateDifference(playlistUserAge, LocalDate.now(), ChronoUnit.YEARS);
	                      long accountLengthDays = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.DAYS);
	                      long accountLengthMonths = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.MONTHS);
	                      long accountLengthYears = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.YEARS);
	                     
	                      // Use UserFilterUtil to get the most recent Metrics
	                      Metrics mostRecentMetricRecord = artistUtil.sortMostRecentMetricRecord(model, user.getId());
	                      
	                      // Calculate length of medical metricAccountHistory if Metrics is present
	                      if (mostRecentMetricRecord != null) {
	                          LocalDate metricAccountHistory = mostRecentMetricRecord.getCreatedAt().toLocalDate();
	                          long searchedUserMetricAccountHistory = dateUtil.calculateDateDifference(metricAccountHistory, LocalDate.now(), ChronoUnit.YEARS);

	                          // Add to the model
	                          model.addAttribute("searchedMostRecentMetricRecord", mostRecentMetricRecord);
	                          model.addAttribute("searchedMostRecentMetricAccountHistory", searchedUserMetricAccountHistory);
		                  } 

	                      // Add to the model without Metrics details
	                      model.addAttribute("matchedPlaylist", matchedPlaylist);
	                      model.addAttribute("matchedUsersAge", searchedUserAge);
	                      model.addAttribute("searchedPlaylistAccountDaysHistory", accountLengthDays);
	                      model.addAttribute("searchedPlaylistAccountYearsHistory", accountLengthYears);
	                      model.addAttribute("searchedPlaylistAccountMonthsHistory", accountLengthMonths);
	                      model.addAttribute("searchedPlaylistSongsList", onePlaylist.getPlaylistsongs());
	                      model.addAttribute("searchedPlaylistCreatedAt", formattedPlaylistCreatedAtDate);
	                      model.addAttribute("searchedPlaylistDayCreatedAt",formattedDayPlaylistCreatedAtDate);

	                      // Add Playlist to the list
	                      searchedPlaylistLists.add(onePlaylist);
	                      playlistSongLists.addAll(onePlaylist.getPlaylistsongs());
	                  }
	              }
	          }
	
	          model.addAttribute("searchedPlaylistsList", searchedPlaylistLists);
	          model.addAttribute("searchedPlaylistSongsList", playlistSongLists);
	      } else {
	          // No match found, set the flag and add an empty list to the model
	          model.addAttribute("isSingleMatch", false);
	          model.addAttribute("matchedPlaylistCharacterList", Collections.emptyList());
	          model.addAttribute("searchedPlaylistsLists", Collections.emptyList());
	      }
	    }

	    // Main method to search for users playlist and populate playlist songs by characters
	    public void searchUserPlaylistByCharacter(Model model, String trimmedSearchTerm) {
		    // If a non-empty search value is provided
		    List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm.toLowerCase());

		    if (!matchedUsers.isEmpty()) {
		        // Single or multiple matches found, set the flag and add to the model
		        model.addAttribute("isSingleMatch", matchedUsers.size() == 1);
		        model.addAttribute("matchedSearchUserCharacterList", matchedUsers);

		        // Populate Playlist list for each user
		        List<Playlist> searchedPlaylistLists = new ArrayList<>();
		        List<PlaylistSongs> playlistSongLists = new ArrayList<>();
		        for (User user : matchedUsers) {

		            // Check for null to avoid potential NullPointerException
		            if (user.getPlaylists() != null) {
		                for (Playlist onePlaylist : user.getPlaylists()) {
		                    // Handle null Playlist or User
		                    if (onePlaylist == null || onePlaylist.getUser() == null) {
		                        continue;
		                    }

		                    // Calculate date differences
		                    LocalDate createdAt = onePlaylist.getCreatedAt().toLocalDate();
		                    LocalDate playlistUserAge = onePlaylist.getUser().getDateOfBirth();

		                    String formattedDayPlaylistCreatedAtDate = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
		                    String formattedPlaylistCreatedAtDate = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

		                    long searchedUserAge = dateUtil.calculateDateDifference(playlistUserAge, LocalDate.now(), ChronoUnit.YEARS);
		                    long accountLengthDays = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.DAYS);
		                    long accountLengthMonths = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.MONTHS);
		                    long accountLengthYears = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.YEARS);
		                   
		                    // Use UserFilterUtil to get the most recent Metrics
		                    Metrics mostRecentMetricRecord = artistUtil.sortMostRecentMetricRecord(model, user.getId());
		                    
		                    // Calculate length of medical metricAccountHistory if Metrics is present
		                    if (mostRecentMetricRecord != null) {
		                        LocalDate metricAccountHistory = mostRecentMetricRecord.getCreatedAt().toLocalDate();
		                        long searchedUserMetricAccountHistory = dateUtil.calculateDateDifference(metricAccountHistory, LocalDate.now(), ChronoUnit.YEARS);

		                        // Add to the model
			                    model.addAttribute("searchedMostRecentMetricRecord", mostRecentMetricRecord);
			                    model.addAttribute("searchedMostRecentMetricAccountHistory", searchedUserMetricAccountHistory);
				            }

		                    // Add to the model without Metrics details
			                model.addAttribute("matchedPlaylist", onePlaylist);
			                model.addAttribute("matchedUsersAge", searchedUserAge);
		                    model.addAttribute("searchedPlaylistAccountDaysHistory", accountLengthDays);
	                        model.addAttribute("searchedPlaylistAccountYearsHistory", accountLengthYears);
		                    model.addAttribute("searchedPlaylistAccountMonthsHistory", accountLengthMonths);
			                model.addAttribute("searchedPlaylistSongsList", onePlaylist.getPlaylistsongs());
	                        model.addAttribute("searchedPlaylistCreatedAt", formattedPlaylistCreatedAtDate);
	                        model.addAttribute("searchedPlaylistDayCreatedAt",formattedDayPlaylistCreatedAtDate);

	                        // Add Playlist to the list
		                    searchedPlaylistLists.add(onePlaylist);
		                    playlistSongLists.addAll(onePlaylist.getPlaylistsongs());
		                }
		            }
		        }

		        model.addAttribute("searchedPlaylistsList", searchedPlaylistLists);
		        model.addAttribute("searchedPlaylistSongsList", playlistSongLists);
		    } else {
		        // No match found, set the flag and add an empty list to the model
		        model.addAttribute("isSingleMatch", false);
		        model.addAttribute("matchedPlaylistCharacterList", Collections.emptyList());
		        model.addAttribute("searchedPlaylistsLists", Collections.emptyList());
		    }
		} 

	    // Method to search for users and their user cases by characters
	    public List<Playlist> returnUserSearchPlaylistByCharacter(String trimmedSearchTerm) {
	        if (trimmedSearchTerm != null) {
		        List<User> matchedUsers = searchUsersByCharacters(trimmedSearchTerm);
		        List<Playlist> searchedPlaylistList = new ArrayList<>();
		
		        for (User user : matchedUsers) {
		            if (user.getPlaylists() != null) {
		                searchedPlaylistList.addAll(user.getPlaylists());
		            }
		        }
		
		        return searchedPlaylistList;
		    }
			return null;
	    }
}