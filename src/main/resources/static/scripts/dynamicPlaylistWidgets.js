//Dynamic Playlist Widgets
const togglePlaylistForm = (songId) => {
    const newPlaylistForm = document.getElementById(`newPlaylistForm-${songId}`);
    const addSongToPlaylistForm = document.getElementById(`addSongToPlaylistForm-${songId}`);
    const showPlaylistPrompt = document.querySelector(`.show-playlist[onclick="togglePlaylistForm(${songId})"]`);
    const chevronIcon = showPlaylistPrompt.querySelector('.playlist-chevron-icon');
    
    if (newPlaylistForm.style.display === 'none' || newPlaylistForm.style.display === '') {
        newPlaylistForm.style.display = 'block';
        addSongToPlaylistForm.style.display = 'block';
        showPlaylistPrompt.firstChild.textContent = 'Close Playlist Panel ';
        chevronIcon.classList.add('rotate');
    } else {
        newPlaylistForm.style.display = 'none';
        addSongToPlaylistForm.style.display = 'none';
        showPlaylistPrompt.firstChild.textContent =  `Add ${music_list[current_song_index].name} To Playlist `;
        chevronIcon.classList.remove('rotate');
    }
};

const toggleMetricsForm = (songId) => {
    const metricsForm = document.getElementById(`trackStatId-${songId}`);
    const showMetricsParagraph = document.querySelector(`.show-stats[onclick="toggleMetricsForm(${songId})"]`);
    const chevronIcon = showMetricsParagraph.querySelector('.metrics-chevron-icon');
    
    if (metricsForm.style.display === 'none' || metricsForm.style.display === '') {
        metricsForm.style.display = 'block';
        showMetricsParagraph.firstChild.textContent = 'Hide Track Metrics ';
        chevronIcon.classList.add('rotate');
    } else {
        metricsForm.style.display = 'none';
        showMetricsParagraph.firstChild.textContent = 'Show Track Metrics ';
        chevronIcon.classList.remove('rotate');
    }
};

const showPlaylistForm = () => {
      //const playlistForm = document.getElementById('playlistForm');      const chevronIcon = showPlaylistPrompt.querySelectorAll('.chevron-icon');
      const playlist_form = document.querySelectorAll('.playlist-form');
      const section_playlist_form = document.querySelectorAll('.section-playlist-form');
      const footer_playlist_form = document.querySelectorAll('.footer-playlist-form');

	  playlist_form.forEach((playlist, i) => {
	  const showPlaylistPrompt = document.querySelectorAll('.show-playlist-prompt');
	  const chevronIcon = document.querySelectorAll('.playlist-chevron-icon');
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showPlaylistPrompt[i].firstChild.textContent = 'Close Playlist Panel ';
		          chevronIcon[i].classList.add('rotate')//, 'imageCover');
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showPlaylistPrompt[i].firstChild.textContent =  `Add ${music_list[current_song_index].name} To Playlist `;
		          chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      } else {
		          playlist.style.display = 'none';
		          showPlaylistPrompt[i].firstChild.textContent =  `Add ${music_list[current_song_index].name} To Playlist `;
		          chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      }
		  }
  	  });

	  const showSectionPlaylistPrompt = document.querySelectorAll('.show-section-playlist-prompt');
	  const sectionChevronIcon = document.querySelectorAll('.section-playlist-chevron-icon');
	  section_playlist_form.forEach((playlist, i) => {
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showSectionPlaylistPrompt[i].firstChild.textContent = 'Close Playlist Panel ';
		          sectionChevronIcon[i].classList.add('rotate')
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showSectionPlaylistPrompt[i].firstChild.textContent =  `Add ${music_list[current_song_index].name} To Playlist `;
		          sectionChevronIcon[i].classList.remove('rotate');
		      } else {
		          playlist.style.display = 'none';
		          showSectionPlaylistPrompt[i].firstChild.textContent =  `Add ${music_list[current_song_index].name} To Playlist `;
		          sectionChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });

	  const showFooterPlaylistPrompt = document.querySelectorAll('.show-footer-playlist-prompt');
	  const footerChevronIcon = document.querySelectorAll('.footer-playlist-chevron-icon');
	  footer_playlist_form.forEach((playlist, i) => {
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showFooterPlaylistPrompt[i].firstChild.textContent = 'Close Playlist Panel ';
		          footerChevronIcon[i].classList.add('rotate');
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showFooterPlaylistPrompt[i].firstChild.textContent = `Add ${music_list[current_song_index].name} To Playlist `;
		          footerChevronIcon[i].classList.remove('rotate');
		      } else {
		          playlist.style.display = 'none';
		          showFooterPlaylistPrompt[i].firstChild.textContent =  `Add ${music_list[current_song_index].name} To Playlist `;
		          footerChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });
}
const showNewPlaylistForm = () => {
      //const id_playlistForm = document.getElementById(`'newPlaylistForm-${music_list[current_song_index].trackId}'`); 
      //const section_id_playlistForm = document.getElementById(`'newSectionPlaylistForm-${music_list[current_song_index].trackId}'`); 
      //const footer_id_playlistForm = document.getElementById(`'newFooterPlaylistForm-${music_list[current_song_index].trackId}'`);
      const playlist_form = document.querySelectorAll('.new-playlist-form');
      const section_playlist_form = document.querySelectorAll('.new-section-playlist-form');
      const footer_playlist_form = document.querySelectorAll('.new-footer-playlist-form');

	  playlist_form.forEach((playlist, i) => {
	  const showPlaylistPrompt = document.querySelectorAll('.show-new-playlist-prompt');
	  const chevronIcon = document.querySelectorAll('.new-playlist-chevron-icon');
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showPlaylistPrompt[current_song_index].firstChild.textContent = 'Close New Playlist Panel ';
		          chevronIcon[current_song_index].classList.add('rotate')//, 'imageCover');
		      }  else {
		          playlist.style.display = 'none';
		          showPlaylistPrompt[current_song_index].firstChild.textContent = 'Create A New Playlist ';
		          chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      }
		  }
  	  });

	  const showSectionPlaylistPrompt = document.querySelectorAll('.show-section-new-playlist-prompt');
	  const sectionChevronIcon = document.querySelectorAll('.new-section-playlist-chevron-icon');
	  section_playlist_form.forEach((playlist, i) => {
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showSectionPlaylistPrompt[current_song_index].firstChild.textContent = 'Close New Playlist Panel ';
		          sectionChevronIcon[current_song_index].classList.add('rotate')
		      }else {
		          playlist.style.display = 'none';
		          showSectionPlaylistPrompt[current_song_index].firstChild.textContent = 'Create A New Playlist ';
		          sectionChevronIcon[current_song_index].classList.remove('rotate');
		      }
		  }
  	  });

	  const showFooterPlaylistPrompt = document.querySelectorAll('.show-footer-new-playlist-prompt');
	  const footerChevronIcon = document.querySelectorAll('.new-footer-playlist-chevron-icon');
	  footer_playlist_form.forEach((playlist, i) => {
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showFooterPlaylistPrompt[i].firstChild.textContent = 'Close New Playlist Panel ';
		          footerChevronIcon[i].classList.add('rotate');
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showFooterPlaylistPrompt[i].firstChild.textContent = 'Create New Playlist ';
		          footerChevronIcon[i].classList.remove('rotate');
		      } else {
		          playlist.style.display = 'none';
		          showFooterPlaylistPrompt[i].firstChild.textContent = 'Create A New Playlist ';
		          footerChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });
}

const showNonIdentifiedPlayers = () => {
    if (curr_track && !curr_track.paused) {
        // When the current track is playing
        non_id_play_panel[current_song_index].classList.add('hidePanel');
        id_play_panel[current_song_index].classList.remove('hidePanel');
    } else {
        // When the current track is paused
        non_id_play_panel[current_song_index].classList.remove('hidePanel');
        id_play_panel[current_song_index].classList.add('hidePanel');
    }
};

const showFollowForm = () => {
      //const panelForm = document.getElementById('panelForm');      
      const follow_user_panel = document.querySelectorAll('.follow-user-panel');
	  const showFollowUserParagraph = document.querySelectorAll('.show-follow-user');
	  const follow_chevronIcon = document.querySelectorAll('.follow-user-chevron-icon');
	  follow_user_panel.forEach((panel, i) => {
		  if (i === current_song_index) {
		      if (panel.style.display === 'none' || panel.style.display === '') {
		          panel.style.display = 'flex';
		          panel.classList.add('flex-btw');
		          showFollowUserParagraph[i].firstChild.textContent = 'Close Social Panel ';
		          follow_chevronIcon[i].classList.add('rotate')//, 'imageCover');
		      } else if (panel.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          panel.style.display = 'none';
		          showFollowUserParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Social Panel `;
		          follow_chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      } else {
		          panel.style.display = 'none';
		          showFollowUserParagraph[i].firstChild.textContent = `${music_list[i].artist} Social Panel `;
		          follow_chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      }
		  }
  	  });

      const section_follow_user_panel = document.querySelectorAll('.section-follow-user-panel');
	  const showSectionFollowUserParagraph = document.querySelectorAll('.show-section-follow-user');
	  const sectionChevronIcon = document.querySelectorAll('.section-follow-user-chevron-icon');
	  section_follow_user_panel.forEach((panel, i) => {
		  if (i === current_song_index) {
		      if (panel.style.display === 'none' || panel.style.display === '') {
		          panel.style.display = 'flex';
		          panel.classList.add('flex-btw');
		          showSectionFollowUserParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Social Panel `;
		          sectionChevronIcon[i].classList.add('rotate');
		      } else if (panel.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          panel.style.display = 'none';
		          showSectionFollowUserParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Social Panel `;
		          sectionChevronIcon[i].classList.remove('rotate');
		      } else {
		          panel.style.display = 'none';
		          showSectionFollowUserParagraph[i].firstChild.textContent = `${music_list[i].artist} Social Panel `;
		          sectionChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });

	  //const id_footer_social_panel = document.getElementById(`'#footer-social-actions-Panel-${music_list[current_song_index].trackId}'`)
      const footer_follow_user_panel = document.querySelectorAll('.footer-follow-user-panel');
	  const showFooterFollowUserParagraph = document.querySelectorAll('.show-footer-follow-user');
	  const footerChevronIcon = document.querySelectorAll('.footer-follow-user-chevron-icon');
	  footer_follow_user_panel.forEach((panel, i) => {
		  if (i === current_song_index) {
		      if (panel.style.display === 'none' || panel.style.display === '') {
		          panel.style.display = 'block';
		          //panel.classList.add('flex-btw');
		          showFooterFollowUserParagraph[i].firstChild.textContent = 'Close Social Panel ';
		          footerChevronIcon[i].classList.add('rotate')//, 'imageCover');
		      }  else {
		          panel.style.display = 'none';
		          showFooterFollowUserParagraph[i].firstChild.textContent = `${music_list[i].artist} Social Panel `;
		          footerChevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      }
		  }
  	  });
}

// Function to toggle the albums panel
const showAlbumsPanel = () => {
    //const id_albums_Panel = document.getElementById(`'#upper-albums-panel-${music_list[current_song_index].trackId}'`);
    const albumsPanel = document.querySelectorAll('.show-albums-panel-paragraph');
    const albumsChevronIcon = document.querySelectorAll('.albums-chevron-icon');
    const showAlbumsPanelParagraph= document.querySelectorAll('.show-albums-panel-paragraph');

	albumsPanel.forEach((panel, i) => {
	    if (panel.style.display === 'none' || panel.style.display === '') {
	        panel.style.display = 'block';
	        albumsChevronIcon.classList.add('rotate');
	        showAlbumsPanelParagraph[i].firstChild.textContent = `Close ${music_list[i].album} Album Panel `;
	    } else {
	        panel.style.display = 'none';
	        albumsChevronIcon.classList.remove('rotate');
	        showAlbumsPanelParagraph[i].firstChild.textContent = `Open ${music_list[i].album} Album Panel `;
	    }
	 });
};

//Dynamic Delete Panel
const showDeleteForm = () => {
    const deleteForm = document.querySelectorAll('.upper-delete-form');
    const sectionDeleteForm = document.querySelectorAll('.section-delete-form');
    const footerDeleteForm = document.querySelectorAll('.footer-delete-form');
    const showDeleteParagraph = document.querySelectorAll('.show-delete');
    const showSectionDeleteParagraph = document.querySelectorAll('.show-section-delete');
    const showFooterDeleteParagraph = document.querySelectorAll('.show-footer-delete');
    const chevronIcon = document.querySelectorAll('.show-delete .chevron-icon');
    const sectionChevronIcon = document.querySelectorAll('.section-delete-chevron-icon');
    const footerChevronIcon = document.querySelectorAll('.footer-delete-chevron-icon');

    deleteForm.forEach((form, i) => {
		if (form.id === `delete-${music_list[i].trackId}` && i === current_song_index) {
	        if (form.style.display === 'none' || form.style.display === '') {
	            form.style.display = 'block';
	            showDeleteParagraph[i].firstChild.textContent = 'Close Delete Panel';
	            chevronIcon[i].classList.add('rotate');
	        }  else {
	            form.style.display = 'none';
	            showDeleteParagraph[i].firstChild.textContent = 'Delete Track';
	            chevronIcon[i].classList.remove('rotate');
	        }
	    }
    });

    sectionDeleteForm.forEach((form, i) => {
		if (form.id === `section-delete-${music_list[i].trackId}` && i === current_song_index) {
	        if (form.style.display === 'none' || form.style.display === '') {
	            form.style.display = 'block';
	            showSectionDeleteParagraph[i].firstChild.textContent = 'Close Delete Panel ';
	            sectionChevronIcon[i].classList.add('rotate');
	        }  else {
	            form.style.display = 'none';
	            showSectionDeleteParagraph[i].firstChild.textContent = 'Delete Track';
	            sectionChevronIcon[i].classList.remove('rotate');
	        }
	    }
    });

    footerDeleteForm.forEach((form, i) => {
		if (form.id === `footer-delete-${music_list[i].trackId}` && i === current_song_index) {
	        if (form.style.display === 'none' || form.style.display === '') {
	            form.style.display = 'block';
	            showFooterDeleteParagraph[i].firstChild.textContent = 'Close Delete Panel ';
	            footerChevronIcon[i].classList.add('rotate');
	        }  else {
	            form.style.display = 'none';
	            showFooterDeleteParagraph[i].firstChild.textContent = 'Delete Track';
	            footerChevronIcon[i].classList.remove('rotate');
	        }
	    }
    });
}    

// Favorites Panel
const showFavoriteSongForm = () => {
      /*const panelForm = document.getElementById('panelForm');  
	  const currentTrackId = music_list[current_song_index].trackId;
      const upper_favorites_panel = document.getElementById(`upper-favorite-actions-Panel-${currentTrackId}`);
      const idShowFavoriteSongParagraph = document.getElementById(`show-favorite-panel-paragraph-${currentTrackId}`);
      const id_favorites_chevronIcon = document.getElementById(`upper-favorites-chevron-icon-${currentTrackId}`);*/

      const upper_favorite_songs_panel = document.querySelectorAll('.upper-favorite-song-panel');
	  const showFavoriteSongsParagraph = document.querySelectorAll('.show-favorite-panel-paragraphs');
	  const upperFavoritesSongsChevronIcon = document.querySelectorAll('.upper-favorites-chevron-icon');

	  upper_favorite_songs_panel.forEach((panel, i) => {
		  if (i === current_song_index) {
		      if (panel.style.display === 'none' || panel.style.display === '') {
		          panel.style.display = 'flex';
		          panel.classList.add('flex-btw');
		          showFavoriteSongsParagraph[current_song_index].firstChild.textContent = `Close Favorite Song Panel `;
		          upperFavoritesSongsChevronIcon[current_song_index].classList.add('rotate');
		      } else if (panel.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          panel.style.display = 'none';
		          showFavoriteSongsParagraph[current_song_index].firstChild.textContent = `Open Favorite Songs Panel `;
		          upperFavoritesSongsChevronIcon[i].classList.remove('rotate');
		      } else {
		          panel.style.display = 'none';
		          showFavoriteSongsParagraph[current_song_index].firstChild.textContent = `Add ${music_list[current_song_index].name} To Favorites `;
		          upperFavoritesSongsChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });

      const section_favorite_songs_panel = document.querySelectorAll('.section-favorite-song-panel');
	  const showSectionFavoriteSongsParagraph = document.querySelectorAll('.show-section-favorite-panel-paragraphs');
	  const sectionFavoritesSongsChevronIcon = document.querySelectorAll('.section-favorites-chevron-icon');
	  section_favorite_songs_panel.forEach((panel, i) => {
		  if (i === current_song_index) {
		      if (panel.style.display === 'none' || panel.style.display === '') {
		          panel.style.display = 'flex';
		          panel.classList.add('flex-btw');
		          showSectionFavoriteSongsParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Favorite Song Panel `;
		          sectionFavoritesSongsChevronIcon[i].classList.add('rotate');
		      } else if (panel.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          panel.style.display = 'none';
		          showSectionFavoriteSongsParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Favorite Song Panel `;
		          sectionFavoritesSongsChevronIcon[i].classList.remove('rotate');
		      } else {
		          panel.style.display = 'none';
		          showSectionFavoriteSongsParagraph[i].firstChild.textContent = `Add ${music_list[current_song_index].name} To Favorites `;
		          sectionFavoritesSongsChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });

	  //const id_footer_social_panel = document.getElementById(`'#footer-social-actions-Panel-${music_list[current_song_index].trackId}'`)
      const footer_favorite_songs_panel = document.querySelectorAll('.footer-favorite-song-panel');
	  const showFooterFavoriteSongsParagraph = document.querySelectorAll('.show-footer-favorite-panel-paragraphs');
	  const footerFavoritesSongsChevronIcon = document.querySelectorAll('.footer-favorites-chevron-icon');
	  footer_favorite_songs_panel.forEach((panel, i) => {
		  if (i === current_song_index) {
		      if (panel.style.display === 'none' || panel.style.display === '') {
		          panel.style.display = 'block';
		          panel.classList.add('flex-btw');
		          showFooterFavoriteSongsParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Favorite Song Panel `;
		          footerFavoritesSongsChevronIcon[i].classList.add('rotate');
		      }else if (panel.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          panel.style.display = 'none';
		          showFooterFavoriteSongsParagraph[i].firstChild.textContent = `Open ${music_list[i].artist} Favorite Song Panel `;
		          footerFavoritesSongsChevronIcon[i].classList.remove('rotate');
		      } else {
		          panel.style.display = 'none';
		          showSectionFavoriteSongsParagraph[i].firstChild.textContent = `Add ${music_list[current_song_index].name} To Favorites `;
		          footerFavoritesSongsChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });
}

// Function to toggle favorite song form display
const toggleFavoriteSongForm = (songId) => {
    const favoriteForm = document.getElementById(`favoriteSongForm-${songId}`);
    const unfavoriteForm = document.getElementById(`dynamicUnfavoriteSongForm-${songId}`);
  
    if (favoriteForm.style.display === 'none' || favoriteForm.style.display === '') {
        favoriteForm.classList.add('hidePanel');
		favoriteForm.style.display = 'none';
		unfavoriteForm.style.display = 'block';
        //unfavoriteForm.classList.remove('hidePanel');
    } else {
		favoriteForm.style.display = 'block';
        //favoriteForm.classList.remove('hidePanel');
        unfavoriteForm.classList.add('hidePanel');
		unfavoriteForm.style.display = 'none';
    }
};

// Dynamic Social Metrics Panel
const showMetricsForm = () => {
    const trackStat = document.querySelectorAll('.track-stat');
    const sectionTrackStat = document.querySelectorAll('.section-track-stat');
    const footerTrackStat = document.querySelectorAll('.footer-track-stat');
    //const followUserForm = document.querySelectorAll('.follow-user-form');
    const showLikeParagraph = document.querySelectorAll('.show-stats');
    const showSectionLikeParagraph = document.querySelectorAll('.show-section-stats');
    const showFooterLikeParagraph = document.querySelectorAll('.show-footer-stats');
    const chevronIcon = document.querySelectorAll('.metrics-chevron-icon');
    const sectionChevronIcon = document.querySelectorAll('.section-metrics-chevron-icon');
    const footerChevronIcon = document.querySelectorAll('.footer-metrics-chevron-icon');

    /*
    followUserForm.forEach((stat, i) => {
		if (i === current_song_index) {
	        if (stat.classList.contains('hidePanel')) {
	            stat.classList.remove('hidePanel');
	            showFooterLikeParagraph[i].firstChild.textContent = 'Close Track Metrics ';
	            footerChevronIcon[i].classList.add('rotate');
	        } else {
	            stat.classList.add('hidePanel');
	            showFooterLikeParagraph[i].firstChild.textContent = 'Show Track Metrics ';
	            footerChevronIcon[i].classList.remove('rotate');
	        }
	    }
    });
    */

    trackStat.forEach((stat, i) => {
		if (i === current_song_index) {
	        if (stat.classList.contains('hidePanel')) {
	            stat.classList.remove('hidePanel');
	            showLikeParagraph[i].firstChild.textContent = 'Close Track Metrics ';
	            chevronIcon[i].classList.add('rotate');
	        } else {
	            stat.classList.add('hidePanel');
	            showLikeParagraph[i].firstChild.textContent = 'Show Track Metrics ';
	            chevronIcon[i].classList.remove('rotate');
	        }
	    }
    });

    sectionTrackStat.forEach((stat, i) => {
		if (i === current_song_index) {
	        if (stat.classList.contains('hidePanel')) {
	            stat.classList.remove('hidePanel');
	            showSectionLikeParagraph[i].firstChild.textContent = 'Close Panel Track Metrics ';
	            sectionChevronIcon[i].classList.add('rotate');
	        } else {
	            stat.classList.add('hidePanel');
	            showSectionLikeParagraph[i].firstChild.textContent = 'Show Panel Track Metrics ';
	            sectionChevronIcon[i].classList.remove('rotate');
	        }
	    }
    });

    footerTrackStat.forEach((stat, i) => {
		if (i === current_song_index) {
	        if (stat.classList.contains('hidePanel')) {
	            stat.classList.remove('hidePanel');
	            showFooterLikeParagraph[i].firstChild.textContent = 'Close Track Metrics ';
	            footerChevronIcon[i].classList.add('rotate');
	        } else {
	            stat.classList.add('hidePanel');
	            showFooterLikeParagraph[i].firstChild.textContent = 'Show Track Metrics ';
	            footerChevronIcon[i].classList.remove('rotate');
	        }
	    }
    });
}    

// Function to toggle form visibility
const toggleForm = (formClass, buttonClass, displayText, hideText) => {
    const forms = document.querySelectorAll(formClass);
    const buttons = document.querySelectorAll(buttonClass);
    const chevrons = document.querySelectorAll(`${buttonClass} .chevron-icon`);

    forms.forEach((form, i) => {
	        if (form.style.display === 'none' || form.style.display === '') {
	            form.style.display = 'block';
	            buttons[i].firstChild.textContent = hideText;
	            chevrons[i].classList.add('rotate');
	        } else if (form.id !== music_list[i].trackId && i === current_song_index) {
	            form.style.display = 'none';
	            buttons[i].firstChild.textContent = displayText;
	            chevrons[i].classList.remove('rotate');
	        } else {
	            form.style.display = 'none';
	            buttons[i].firstChild.textContent = displayText;
	            chevrons[i].classList.remove('rotate');
	        }
    });
};

// Helper Methods To Get Liked Songs
const getLikedSongsJson = (likedSongs) => {
    let json = '"likedSongs": [';
    if (likedSongs && likedSongs.length > 0) {
        for (let i = 0; i < likedSongs.length; i++) {
            const likedSong = likedSongs[i];
            json += `{"likingUserId": "${likedSong.likingUserId}"}`;
            if (i < likedSongs.length - 1) {
                json += ', ';
            }
        }
    }
    json += `], "likedSongsCount": "${likedSongs ? likedSongs.length : 0}"`;
    return json;
};

// Function to toggle the playlist panel
const showFavoritePlaylistForm = () => {
    const upper_favorite_playlists_panel = document.querySelectorAll('.upper-favorite-playlist-panel');
    const showFavoritePlaylistsParagraph = document.querySelectorAll('.show-favorite-playlist-panel-paragraphs');
    const upperFavoritesPlaylistsChevronIcon = document.querySelectorAll('.upper-favorites-chevron-icon');

    upper_favorite_playlists_panel.forEach((panel, i) => {
        const playlistId = musicListMap[current_song_index].playlistId;
        if (panel.id === `favorite-playlist-panel-${playlistId}`) {
            if (panel.style.display === 'none' || panel.style.display === '') {
                panel.style.display = 'flex';
                panel.classList.add('flex-btw');
                showFavoritePlaylistsParagraph[i].firstChild.textContent = `Close Favorite Playlist Panel `;
                upperFavoritesPlaylistsChevronIcon[i].classList.add('rotate');
            } else {
                panel.style.display = 'none';
                showFavoritePlaylistsParagraph[i].firstChild.textContent = `Open ${musicListMap[i].name} Favorite Playlists Panel `;
                upperFavoritesPlaylistsChevronIcon[i].classList.remove('rotate');
            }
        }
    });
};

// Example call
//showFavoritePlaylistForm();

/*
const applyDynamicStylesToTrackArt = () => {
    const curr_track = document.getElementById(`audio-${music_list[current_song_index].trackId}`);

    // Check conditions for applying dynamic styles
    const shouldApplyStyles = curr_track.currentTime > 0 || !curr_track.paused;

    if (shouldApplyStyles) {
        const borderColor = random_hex_color();
        const boxShadowColor = random_hex_color();

        const style = document.createElement('style');
        style.innerHTML = `
            .track-art-widget,
            .upper-track-art-widget {
                -moz-box-shadow: 0px 6px 5px ${boxShadowColor};
                -webkit-box-shadow: 6px 6px 5px ${boxShadowColor};
                box-shadow: 0px 6px 5px ${boxShadowColor};
                border: 2px solid ${borderColor};
                transition: all ${bpm / 3.3 / bpm}s ease-in;
            }
        `;

        // Remove existing style if it exists
        const existingStyle = document.head.querySelector('.dynamic-track-art-styles');
        if (existingStyle) {
            document.head.removeChild(existingStyle);
        }

        // Append the new style with a unique class name
        style.classList.add('dynamic-track-art-styles');
        document.head.appendChild(style);
    } else {
        // Remove the style if conditions are not met
        const existingStyle = document.head.querySelector('.dynamic-track-art-styles');
        if (existingStyle) {
            document.head.removeChild(existingStyle);
        }
    }
};


// Initialize panels to be hidden by default
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('albums-panel').style.display = 'none';
    document.getElementById('favorite-songs-panel').style.display = 'none';
    document.getElementById('favorite-playlists-panel').style.display = 'none';
    document.getElementById('favorite-albums-panel').style.display = 'none';
});
*/

/*/ Start the interval for updating gradients after a delay matching the duration of the track
    
	gradientUpdateTimeout = setTimeout(() => {
        updateGradient();
        // Update the gradient every 10 seconds after the initial delay
        setInterval(updateGradient, 10000);
    }, curr_track.duration * 1000); // Start after the duration of the track
	*/

/*/ Start the interval for updating gradients
const startGradientUpdateInterval = () => {
    setInterval(updateGradient, 10000);
}
*/