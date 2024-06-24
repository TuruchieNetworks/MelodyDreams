
const writeText = (descriptionDetails) => {
    const bpm = curr_track.bpm || 120; // Default to 120 BPM if not provided
    const speed = (60 / bpm) * 1000 / 1; // Recalculate speed based on BPM

    if (!descriptionDetails) {
        console.error('Description details are undefined or empty.');
        clearTimeout(writeTextTimeout);
        return;
    }

    if (currentTrackId !== music_list[current_song_index].trackId) {
        // Track has changed, reset indices and elements
        resetElements();
        currentTrackId = music_list[current_song_index].trackId;
		infoIdx = 1;
        countIdx = 1;
        pointIdx = 1;
        promptIdx = 1;
        clearTimeout(writeTextTimeout);
    }

    // Update elements
    updateElements();

    if (isPlaying) {
        writeTextTimeout = setTimeout(() => writeText(descriptionDetails), speed);
    }
};

const updateElements = () => {
    track_status.forEach(content => {
        if (isPlaying) {
            content.classList.remove('hidePanel');
            content.textContent = track_status_message.slice(0, pointIdx);
            content.style.background = random_hex_color();
            content.style.color = '#06111c';
        } else {
            content.textContent = track_status_message;
            content.classList.add('hidePanel');
        }
    });

    track_upload.forEach(content => {
        if (isPlaying) {
            content.textContent = track_upload_message.slice(0, countIdx);
            content.style.color = '#06111c';
        } else {
            content.textContent = track_upload_message;
        }
    });

    artist_hub.forEach(content => {
        if (isPlaying) {
            content.textContent = artist_hub_message.slice(0, countIdx);
            content.style.color = '#06111c';
        } else {
            content.textContent = artist_hub_message;
        }
    });

    main_greeting.forEach((element, i) => {
        //element.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`;
        if (isPlaying) {
			//element.innerText = main_greeting_message.slice(0, countIdx);
			element.style.boxShadow =`1px 1px 3px ${random_hex_color()}`;  
			element.style.color = random_hex_color(); 
			element.style.borderBottom =`1px solid ${random_hex_color()}`;  
			//element.style.background  =` url(${music_list[track_index].img})`;  
			element.style.animation = '';
			element.classList.add('imageCover', 'leadShowcase'); 
	        element.style.transitionProperty = 'all';
	        element.style.transitionDuration = `${bpm / 3.3 / bpm}s`;
	        element.style.transitionTimingFunction = 'ease-in-out'; 
        } else {
			element.style.animation = `typewriter 4s steps(44) 1s 1 normal both, blinkTextCursor 500ms steps(44) 1 normal`; 
            element.innerText = main_greeting_message;  
        }
    });

    word_slicer.forEach((content, i) => {
        if (isPlaying) {
            //content.classList.add('word-slicer');
            content.textContent = word_slicer_messages[i].slice(0, countIdx);
	        content.offsetHeight; // Trigger reflow to restart animation
	        //content.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`; 
	        //content.style.animation = `typewriter 4s steps(40) 1s 1 normal both, blinkTextCursor 500ms steps(40) infinite normal` 
            //content.textContent = word_slicer_messages[i].slice(0, countIdx);
            //content.style.width = countIdx;
            content.style.transitionProperty = 'all';
            content.style.transitionDuration = `${bpm / 3.3 / bpm}s`;
            content.style.transitionTimingFunction = 'ease-in-out';
        } else {
            content.textContent = word_slicer_messages[i];
	        //content.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`;  
            //content.classList.remove('word-slicer');
        }
    });

    input_slicer.forEach((input, i) => {
        if (isPlaying) {
            //content.classList.add('word-slicer');
            input.value = input_slicer_messages[i].slice(0, countIdx);
	        input.offsetHeight; // Trigger reflow to restart animation
	        //input.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`; 
	        //input.style.animation = `typewriter 4s steps(40) 1s 1 normal both, blinkTextCursor 500ms steps(40) infinite normal` 
            //content.textContent = word_slicer_messages[i].slice(0, countIdx);
            //content.style.width = countIdx;
            input.style.transitionProperty = 'all';
            input.style.transitionDuration = `${bpm / 3.3 / bpm}s`;
            input.style.transitionTimingFunction = 'ease-in-out';
        } else {
            input.value = input_slicer_messages[i];
	        //input.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`;  
            //content.classList.remove('word-slicer');
        }
    });

    track_description.forEach((panel, i) => {
        if (isPlaying) {
            panel.classList.remove('hidePanel');
            panel.textContent = `Now Streaming ${music_list[track_index].description} From ${music_list[track_index].album}`.slice(0, infoIdx);
            panel.style.width = '100%';
            panel.style.borderBottom = `1px solid ${random_bg_color()}`;
            panel.style.background = random_hex_color();
            panel.classList.add('point-border-radius', 'mid-padding', 'sm-vert-margin');
            panel.style.transitionProperty = 'all';
            panel.style.transitionDuration = `${bpm / 3.3 / bpm}s`;
            panel.style.transitionTimingFunction = 'ease-in-out';
        } else {
            panel.textContent = currentTrackDescription;
            panel.classList.add('hidePanel');
        }
    });

    playlist_link.forEach(content => {
        if (isPlaying) {
            content.value = playlist_link_prompt.slice(0, promptIdx);
            content.style.color = '#06111c';
        } else {
            content.value = playlist_link_prompt;
        }
    });

    edit_input.forEach((panel, i) => {
        if (isPlaying && panel.id === music_list[current_song_index].trackId) {
            panel.value = edit_form_messages[i].slice(0, countIdx);
        } else {
            panel.value = edit_form_messages[i];
        }
    });

    delete_input.forEach((input, i) => {
        if (isPlaying && input.id === music_list[current_song_index].trackId) {
            input.value = delete_form_messages[current_song_index].slice(0, countIdx);
        } else {
            input.value = delete_form_messages[current_song_index];
        }
    });

    track_name.forEach((content) => {
        if (!curr_track.paused) {
			content.style.animation = '';
            content.textContent = music_list[current_song_index].name.slice(0, countIdx);
        } else {
			content.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`; 
            content.textContent = music_list[current_song_index].name;
        }
    });

    panel_greetings.forEach(greeting => {
        if (isPlaying) {
            greeting.classList.remove('hidePanel');
            greeting.textContent = main_greeting_message.slice(0, countIdx);
            greeting.style.color = '#06111c';
        } else {
            greeting.textContent = main_greeting_message;
            greeting.classList.add('hidePanel');
        }
    });

    // Increment indices
    if (isPlaying) {
		infoIdx++;
        countIdx++;
        pointIdx++;
        promptIdx++;

		if (infoIdx > currentTrackDescription.length) {
		    infoIdx = 1;
		}

		if (pointIdx > track_status_message.length) {
		    pointIdx = 1;
		}

	    if (promptIdx > playlist_link_prompt.length) {
	        promptIdx = 1;
		} 

		if (countIdx > main_greeting_message.length) {
		    countIdx = 1;
		}
    }
};
const resetMessages = () => {
    clearTimeout(writeText);
    //currentTrack = music_list[current_song_index];
    track_description.forEach(element => {
        element.classList.add('hidePanel');
        element.innerText = '';
        //element.innerText = `Track ${current_song_index}: ${currentTrack.description} From ${currentTrack.album} Album`;
    });
    
    playlist_link.forEach(element => {
		//element.value = ''
		element.value = playlist_link_prompt;
    	clearTimeout(writeText);
	});
    
    track_status.forEach(element => {
		element.textContent = main_greeting_message;
    	clearTimeout(writeText);
	});
    
    main_greeting.forEach(element => {
		element.textContent = main_greeting_message;
    	clearTimeout(writeText);
	});

    panel_greetings.forEach(element => {
		element.textContent = main_greeting_message;
    	clearTimeout(writeText);
	});
	
}
//Dynamic Playlist Widgets
const togglePlaylistForm = (songId) => {
    const newPlaylistForm = document.getElementById(`newPlaylistForm-${songId}`);
    const addSongToPlaylistForm = document.getElementById(`addSongToPlaylistForm-${songId}`);
    const showPlaylistParagraph = document.querySelector(`.show-playlist[onclick="togglePlaylistForm(${songId})"]`);
    const chevronIcon = showPlaylistParagraph.querySelector('.playlist-chevron-icon');
    
    if (newPlaylistForm.style.display === 'none' || newPlaylistForm.style.display === '') {
        newPlaylistForm.style.display = 'block';
        addSongToPlaylistForm.style.display = 'block';
        showPlaylistParagraph.firstChild.textContent = 'Close Playlist Panel';
        chevronIcon.classList.add('rotate');
    } else {
        newPlaylistForm.style.display = 'none';
        addSongToPlaylistForm.style.display = 'none';
        showPlaylistParagraph.firstChild.textContent = 'Add Song To Playlist';
        chevronIcon.classList.remove('rotate');
    }
};

const toggleMetricsForm = (songId) => {
    const metricsForm = document.getElementById(`trackStatId-${songId}`);
    const showMetricsParagraph = document.querySelector(`.show-stats[onclick="toggleMetricsForm(${songId})"]`);
    const chevronIcon = showMetricsParagraph.querySelector('.metrics-chevron-icon');
    
    if (metricsForm.style.display === 'none' || metricsForm.style.display === '') {
        metricsForm.style.display = 'block';
        showMetricsParagraph.firstChild.textContent = 'Hide Track Metrics';
        chevronIcon.classList.add('rotate');
    } else {
        metricsForm.style.display = 'none';
        showMetricsParagraph.firstChild.textContent = 'Show Track Metrics';
        chevronIcon.classList.remove('rotate');
    }
};

const showPlaylistForm = () => {
      //const playlistForm = document.getElementById('playlistForm');      const chevronIcon = showPlaylistParagraph.querySelectorAll('.chevron-icon');
      const playlist_form = document.querySelectorAll('.playlist-form');
      const section_playlist_form = document.querySelectorAll('.section-playlist-form');
      const footer_playlist_form = document.querySelectorAll('.footer-playlist-form');

	  playlist_form.forEach((playlist, i) => {
	  const showPlaylistParagraph = document.querySelectorAll('.show-playlist');
	  const chevronIcon = document.querySelectorAll('.playlist-chevron-icon');
		  if (i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showPlaylistParagraph[i].firstChild.textContent = 'Close Playlist Panel';
		          chevronIcon[i].classList.add('rotate')//, 'imageCover');
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist';
		          chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      } else {
		          playlist.style.display = 'none';
		          showPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist';
		          chevronIcon[i].classList.remove('rotate')//, 'imageCover');
		      }
		  }
  	  });

	  const showSectionPlaylistParagraph = document.querySelectorAll('.show-section-playlist');
	  const sectionChevronIcon = document.querySelectorAll('.section-playlist-chevron-icon');
	  section_playlist_form.forEach((playlist, i) => {
		  if (playlist.id === `playlistForm-${music_list[i].trackId}` && i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showSectionPlaylistParagraph[i].firstChild.textContent = 'Close Playlist Panel ';
		          sectionChevronIcon[i].classList.add('rotate')
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showSectionPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist';
		          sectionChevronIcon[i].classList.remove('rotate');
		      } else {
		          playlist.style.display = 'none';
		          showSectionPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist';
		          sectionChevronIcon[i].classList.remove('rotate');
		      }
		  }
  	  });

	  const showFooterPlaylistParagraph = document.querySelectorAll('.show-footer-playlist');
	  const footerChevronIcon = document.querySelectorAll('.footer-playlist-chevron-icon');
	  footer_playlist_form.forEach((playlist, i) => {
		  if (playlist.id === `playlistForm-${music_list[i].trackId}` && i === current_song_index) {
		      if (playlist.style.display === 'none' || playlist.style.display === '') {
		          playlist.style.display = 'block';
		          showFooterPlaylistParagraph[i].firstChild.textContent = 'Close Playlist Panel';
		          footerChevronIcon[i].classList.add('rotate');
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showFooterPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist ';
		          footerChevronIcon[i].classList.remove('rotate');
		      } else {
		          playlist.style.display = 'none';
		          showFooterPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist ';
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

//Dynamic Social Metrics Panel
const showMetricsForm = () => {
    const trackStat = document.querySelectorAll('.track-stat');
    const sectionTrackStat = document.querySelectorAll('.section-track-stat');
    const footerTrackStat = document.querySelectorAll('.footer-track-stat');
    const followUserForm = document.querySelectorAll('.follow-user-form');
    const showLikeParagraph = document.querySelectorAll('.show-stats');
    const showSectionLikeParagraph = document.querySelectorAll('.show-section-stats');
    const showFooterLikeParagraph = document.querySelectorAll('.show-footer-stats');
    const chevronIcon = document.querySelectorAll('.metrics-chevron-icon');
    const sectionChevronIcon = document.querySelectorAll('.section-metrics-chevron-icon');
    const footerChevronIcon = document.querySelectorAll('.footer-metrics-chevron-icon');

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

    followUserForm.forEach((form, i) => {
		if (i === current_song_index || form.id === `followUserForm-${music_list[current_song_index].trackId}`) {
	        if (form.classList.contains('hidePanel')) {
	            form.style.display = 'block';
	            form.classList.remove('hidePanel');
	            //showLikeParagraph[i].firstChild.textContent = 'Close Metrics';
	            chevronIcon[i].classList.add('rotate');
	            sectionChevronIcon[i].classList.add('rotate');
	            footerChevronIcon[i].classList.add('rotate');
	        } else {
	            form.classList.add('hidePanel');
	            form.style.display = 'none';
	            //showDeleteParagraph[i].firstChild.textContent = 'follow User';
	            chevronIcon[i].classList.remove('rotate');
	            sectionChevronIcon[i].classList.remove('rotate');
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