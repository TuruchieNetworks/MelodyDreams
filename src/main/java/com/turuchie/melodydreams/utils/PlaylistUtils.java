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
import com.turuchie.melodydreams.models.User;
import com.turuchie.melodydreams.services.PlaylistService;
import com.turuchie.melodydreams.services.UserService;

@Component
public class PlaylistUtils {
    	@Autowired
    	private UserService userServ;

    	@Autowired
    	private DateUtil dateUtil;
  
		@Autowired
    	private ArtistsUtils artistUtil;
  
    	@SuppressWarnings("unused")
		@Autowired
    	private PlaylistService playlistServ;

        public PlaylistUtils (UserService userServ, DateUtil dateUtil, 
        	PlaylistService playlistServ, ArtistsUtils artistUtil) {
            this.userServ = userServ;
            this.dateUtil = dateUtil;
            this.playlistServ = playlistServ;
            this.artistUtil = artistUtil;
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


	    
	    // Method to search for users by characters
	    public List<User> searchUsersByCharacters(String trimmedSearchTerm) {
	        List<User> matchedUsers = userServ.searchUsersByCharacters(trimmedSearchTerm.toLowerCase());
	        return (matchedUsers != null) ? matchedUsers : Collections.emptyList();
	    }

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
	    
	    public void searchPlaylistByCharacter(Model model, String trimmedSearchTerm) {
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
		                    //LocalDate onset = onePlaylist.getOnset();
		                    LocalDate createdAt = onePlaylist.getCreatedAt().toLocalDate();
		                    //Date searchedUserAccountHistory = onePlaylist.getUser().getCreatedAt();

		                    String formattedDayPlaylistCreatedAtDate = createdAt.format(DateTimeFormatter.ofPattern("EEE, yyyy"));
		                    String formattedPlaylistCreatedAtDate = createdAt.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy"));

		                    //long searchedUserAge = dateUtil.calculateDateDifference(searchedUserAccountHistory, LocalDate.now(), ChronoUnit.YEARS);
		                    long accountLengthDays = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.DAYS);
		                    long accountLengthMonths = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.MONTHS);
		                    long accountLengthYears = dateUtil.calculateDateDifference(createdAt, LocalDate.now(), ChronoUnit.YEARS);
		                   
		                    // Use UserFilterUtil to get the most recent Metrics
		                    Metrics mostRecentMetricRecord = artistUtil.sortMostRecentMetricRecord(model, user.getId());
		                    
		                    // Calculate length of medical metricAccountHistory if Metrics is present
		                    if (mostRecentMetricRecord != null) {
		                        //LocalDate metricAccountHistory = mostRecentMetricRecord.getCreatedAt().toLocalDate();
		                        //long searchedUserMetricAccountHistory = dateUtil.calculateDateDifference(metricAccountHistory, LocalDate.now(), ChronoUnit.YEARS);
				                // Add to the model
			                    model.addAttribute("searchedMostRecentMetricRecord", mostRecentMetricRecord);
				            } 
		                    // Add to the model without Metrics details
			                model.addAttribute("matchedPlaylist", onePlaylist);
	                        model.addAttribute("searchedPlaylistDayCreatedAt",formattedDayPlaylistCreatedAtDate);
			                model.addAttribute("searchedPlaylistSongsList", onePlaylist.getPlaylistsongs());
	                        model.addAttribute("playlistAccountLength", accountLengthYears);
		                    model.addAttribute("searchedPlaylistAccountDaysHistory", accountLengthDays);
		                    model.addAttribute("playlistAccountLengthMonths", accountLengthMonths);
			                model.addAttribute("searchedPlaylistSongsList", onePlaylist.getPlaylistsongs());
	                        model.addAttribute("searchedPlaylistCreatedAt", formattedPlaylistCreatedAtDate);
	                        model.addAttribute("searchedPlaylistDayCreatedAt",formattedDayPlaylistCreatedAtDate);
	                        model.addAttribute("playlistCreatedAt", onePlaylist.getCreatedAt().format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy")));
	                        model.addAttribute("matchedPlaylistDayCreatedAt", formattedDayPlaylistCreatedAtDate);
		                    // Add Playlist to the list
		                    searchedPlaylistLists.add(onePlaylist);
		                    playlistSongLists.addAll(onePlaylist.getPlaylistsongs());
		                }
		            }
		        }

		        model.addAttribute("searchedPlaylistsList", searchedPlaylistLists);
		    } else {
		        // No match found, set the flag and add an empty list to the model
		        model.addAttribute("isSingleMatch", false);
		        model.addAttribute("matchedPlaylistCharacterList", Collections.emptyList());
		        model.addAttribute("searchedPlaylistsLists", Collections.emptyList());
		    }
		} 

	    // Method to search for users and their user cases by characters
	    public List<Playlist> returnSearchPlaylistByCharacter(String trimmedSearchTerm) {
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