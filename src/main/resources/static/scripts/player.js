//import { displayTrackList } from './playlistPanels.js';
//import { calculateAndFormatTotalDurations } from './musicList.js';
let main_logo = document.querySelectorAll('.main-logo');
let now_playing = document.querySelectorAll('.now-playing');
let main_greeting = document.querySelectorAll('.main-greeting');
let track_art = document.querySelectorAll('.track-art');
let track_name = document.querySelectorAll('.track-name');
let track_artist = document.querySelectorAll('.track-artist');
let playerCover = document.querySelectorAll('.playerCover');
let outer_panel_cover = document.querySelectorAll('.outer-panel-player-cover');
let inner_panel_cover = document.querySelectorAll('.inner-panel-player-cover');
let section_outer_panel_cover = document.querySelectorAll('.section-outer-panel-player-cover');
let section_inner_panel_cover = document.querySelectorAll('.section-inner-panel-player-cover');
let footer_outer_panel_cover = document.querySelectorAll('.footer-outer-panel-player-cover');
let footer_inner_panel_cover = document.querySelectorAll('.footer-inner-panel-player-cover');
let panel_greetings = document.querySelectorAll('.panel-greetings');
let track_status = document.querySelectorAll('.track-status');
let playlist_link = document.querySelectorAll('.playlist-link');
let track_upload = document.querySelectorAll('.track-upload');
let artist_hub = document.querySelectorAll('.artist-hub');
let word_slicer = document.querySelectorAll('.word-slicer');
let input_slicer = document.querySelectorAll('.input-slicer');
let edit_form = document.querySelectorAll('.editForm');
let delete_form = document.querySelectorAll('.deleteForm');
let edit_input = document.querySelectorAll('.edit-input');
let delete_input = document.querySelectorAll('.delete-input');
let music_link_card = document.querySelectorAll('.music-link-card');
let track_description = document.querySelectorAll('.track-description');
let download_link_card = document.querySelectorAll('.download-link-card');
let spreader = document.querySelectorAll('.spreader');
let div_slicer = document.querySelectorAll('.div-slicer');
let section_div_slicer = document.querySelectorAll('.section-div-slicer');
let footer_div_slicer = document.querySelectorAll('.footer-div-slicer');
let spread_div = document.querySelectorAll('.spread-div');

let next_btn = document.querySelectorAll('.next-track');
let prev_btn = document.querySelectorAll('.prev-track');
let playpause_btn = document.querySelectorAll('.playpause-track');
let panelPlaypause_btn = document.querySelectorAll('.playpauseTrack');
let panelPlaypauseBtn = document.querySelectorAll('.panelPlaypauseBtn');
let panelPauseBtn = document.querySelectorAll('.panelPlayPauseBtn');
let setionPlayPauseBtn = document.querySelectorAll('.setionPlayPauseBtn');
let footerPanelPlaypauseBtn = document.querySelectorAll('.footer_playpause_btn');
const non_id_play_panel = document.querySelectorAll('.non-id-play-panel')
const id_play_panel = document.querySelectorAll('.id-play-panel');
const track_art_widget = document.querySelectorAll('.track-art-widget');
const upper_track_art_widget = document.querySelectorAll('.upper-track-art-widget');
const section_track_art_widget = document.querySelectorAll('.section-track-art-widget');
const footer_track_art_widget = document.querySelectorAll('.footer-track-art-widget');
const upper_panel_play_btn = document.querySelectorAll('.upper-panel-play-btn');
const section_panel_play_btn = document.querySelectorAll('.section-panel-play-btn');
const footer_panel_play_btn = document.querySelectorAll('.footer-panel-play-btn');
const non_id_play_panel_icon = document.querySelectorAll('.playing-state-icon');
let play_panel = document.querySelectorAll('.play-panel');
let play_count = document.querySelectorAll('.play-count');

let randomIcon = document.querySelectorAll('.fa-random');
let repeatIcon = document.querySelectorAll('.repeat-track');

let wave = document.querySelectorAll('.wave');
let stroke = document.querySelectorAll('.stroke');
const panelCard = document.querySelector('.panel-card')
let seek_slider = document.querySelectorAll('.seek_slider');
let volume_slider = document.querySelectorAll('.volume_slider');
let id_seek_slider = document.querySelectorAll('.id_seek_slider');
let panel_seek_slider = document.querySelectorAll('.panel_seek_slider');
let sectionSlider = document.querySelectorAll('.sectionSlider');

//Update File Names
let image_name_input = document.getElementById('hidden-image-name-input');
let audio_name_input = document.getElementById('hidden-audio-name-input');
let imageURL = document.getElementById('image-url');
let audioURL = document.getElementById('audio-url');

//let canvas = document.createElement('canvas');
let canvas = document.createElement('canvas');
//let canvasCtx = canvas.getContext('2d');
let barHeight;

let curr_time = document.querySelectorAll('.current-time');
let totalDuration = document.querySelectorAll('.total-duration');
let total_durations = document.querySelectorAll('total-durations');
let remaining_duration = document.querySelectorAll('.remaining_duration')
let remainingDurations = document.querySelectorAll('.remaining_duration');
let remaining_durations = document.querySelectorAll('.remaining_durations');
let panel_total_durations = document.querySelectorAll('.panel-total-durations');
let panel_remaining_durations = document.querySelectorAll('.panel-remaining-durations');
let footer_remaining_durations = document.querySelectorAll('.footer-remaining-durations');
let footer_total_durations = document.querySelectorAll('.footer-total-durations');

let mediaBackground = document.querySelectorAll('.mediaBackground');
let media_buttons = document.querySelectorAll('.media-buttons');
let media_showcase = document.querySelectorAll('.media-showcase');
let main_media_panel = document.querySelectorAll('.main-media-panel');
let section_media_panel = document.querySelectorAll('.section-media-panel');
let footer_media_panel = document.querySelectorAll('.footer-media-panel');
let footer_links = document.querySelectorAll('.footer-links');

let curr_track = document.createElement('audio');
let curr_tracks = document.createElement('audio');
const allAudio = document.querySelectorAll("audio")
let bpm;

const audioCtx = new (window.AudioContext || window.webkitAudioContext)();
let audioSource;
let analyserNode;
let gainNode;

/*
allAudio.forEach(audio => {
    if (!isNaN(audio.duration)) {
		console.log(audio)
		// audio.play();
	}
});
*/

let isPlaying = false;
let isRandom = false;
let isRepeat = false;
let updateTimer;
let updateAllTimer;
let gradientUpdateTimeout;

let audioLink;
let music_list;
let originalMusicList;
let track_index = 0;
let current_song_index = 0;
let songID;
let scriptID = null;
let metrixIdx = null;
let currentTrack;
let currentTrackDescription;
let infoIdx = 1;
let speedIdx = 1;
let countIdx = 1;
let pointIdx = 1;
let promptIdx = 1;
let speed; //= 300 / 1;
//const speed = 300 / speedElement.value;

const audioContainer = document.querySelector('.audio-container');
const slider_container = document.querySelector('.slider_container');
//const progress = document.querySelectorAll('.current-time');
const trackTitle = document.querySelector('.trackTitle');
const trackArtist = document.querySelector('.trackArtist');
const trackImageSrc = document.querySelectorAll('.trackImageSrc');
const trackIDs = document.querySelectorAll('.trackID').innerText;
const audioSrc = document.querySelectorAll('.audioPlayer');
const loggedId = document.getElementById('loggedUser').innerText;
const deletePad = document.querySelectorAll('.delete-pad');
//let playCounts = document.getElementById('playCountsData').innerText;
const musicListData = document.getElementById('musicListData').innerText;

/*
audioSrc.forEach(e => {
	console.log(e.currentSrc)
});
*/
console.log(loggedId)
console.log(now_playing)
//console.log(musicListData);
//console.log(musicListData.length);
// Function to process the musicList And Attempt to parse the JSON data
const parseMusicList = () => {
	try {
	    music_list = JSON.parse(musicListData);
	    //console.log('Music list parsed successfully: dataLength', music_list.length);
	} catch (error) {
	    console.error('Error parsing music list JSON:', error);
	    // Handle parsing error (e.g., show an error message to the user)
	}
}


// Function to get the play count text based on the count value
const getPlayCountText = (count) => {
    if (count === 0) {
        return 'Not played yet';
    } else if (count === 1) {
        return 'Played once';
    } else if (count === 2) {
        return 'Played twice';
    } else {
        return `Played ${count} times`;
    }
}


// Array to store audio sources
let audioSrcs = [];
let audioSrcNodes = [];
let allCurr_tracks = [];

// Load the first track when the page loads
window.onload = function() {
    parseMusicList();
    loadTrack();

    // loadGradient()
	stylePanelCards();
    setAllUpdate();
	calculateAndFormatUIDurtions();
	calculateAndFormatTotalDurations();
	changePlayerCover();
    clearInterval(updateTimer);


    // Update UI elements    reset();
    updateLoadedUI();

    // Start the interval for updating gradients
    updateGradient();

    loadPanelClasses();
    startBackgroundEvents();
// Call the function initially
	//applyDynamicPanelSpreader();	applyDynamicStylesToTrackArt();

};

const updateLoadedUI = () => {
    // Set the current track source
    curr_track.src = music_list[track_index].music;
    curr_track.load();
	// Create a copy of the original music list
	originalMusicList = [...music_list];

    // Update UI elements
    updateUIElements();


    // Calculate speed based on BPM
    bpm = music_list[track_index].bpm || 120; // Default to 120 BPM if not provided
    const interval =  1000;

    // Start the update timer for UI elements
    updateTimer = setInterval(setUpdate, interval);

    // Repeat track mode handling
    if (repeatTrack()) {
        curr_track.addEventListener('ended', () => {
            repeatTrackMode();
        });
    } else {
        curr_track.addEventListener('ended', () => {
            nextTrack();
            handleSeekSliderFormSubmit();
        });
    }
}

const loadTrack = () => {
    clearInterval(updateTimer);
    reset(); 
    random_hex_color();

    // Update UI elements
    updateLoadedUI();

    // Start the interval for updating gradients
    //startGradientUpdate();

    loadPanelClasses();
    startBackgroundEvents();
}

// Function to update UI elements
const updateUIElements = () => {
    // Update UI elements for play counts
    play_count.forEach(element => {
        //const trackId = music_list[track_index].trackId;
        const playCount = music_list[track_index].playCount || 0;
        element.textContent = getPlayCountText(playCount);
    });

    // Update other UI elements similarly using the music_list object
    track_art.forEach(element => {
        element.style.backgroundImage = "url(" + music_list[track_index].img + ")";
    });

    upper_track_art_widget.forEach(element => {
        element.style.backgroundImage = "url(" + music_list[track_index].img + ")";
    });

    track_name.forEach(element => {
        element.textContent = music_list[track_index].name;
    });

    track_artist.forEach(element => {
        element.textContent = music_list[track_index].artist + ': ' + music_list[track_index].genre + ' Track';
    });

    now_playing.forEach(element => {
        element.textContent = "Playing music " + (track_index + 1) + " of " + music_list.length;
        track_status_message = element.textContent = "Playing music " + (track_index + 1) + " of " + music_list.length;
        now_playing_message.push("Playing music " + (track_index + 1) + " of " + music_list.length);
    });
}


const loadPanelClasses = () => {
    currentTrackDescription = `Now Streaming ${music_list[current_song_index].description} From ${music_list[current_song_index].album}`;
    track_upload_message = 'UPLOAD NEW TRACK';
    artist_hub_message = 'STREAMING ARTISTS FROM ALL OVER THE WORLD!'
    main_greeting.forEach(element => {
		main_greeting_message = ''
		countIdx = 1;
		main_greeting_message = element.innerText;
	}); 

	// Input
	playlist_link_prompt = 'Playlists And Media Console Access!'
}

const reset = () => {
    // Clear the timeout if it exists
    //clearTimeout(gradientUpdateTimeout);
    // Clear the interval if it exists
    //clearInterval(gradientUpdateInterval);
    curr_time.forEach((element) => {
        element.textContent = "00:00";
    });
    total_durations.forEach((element) => {
        element.textContent = "00:00";
    });
    track_art_widget.forEach(element => {
        element.classList.remove('rotate');
    });
};

const repeatTrack = () => {
    isRepeat = !isRepeat;
    repeatIcon.forEach(element => {
        element.innerHTML = isRepeat ?
            '<i class="fas fa-repeat fa-2x"></i>' :
            '<i class="fas fa-repeat fa-3x"></i>';
        return isRepeat;
    });
};

const createCurrentSongList = () => {
    // Create a special list with only the current song
    const currentSong = music_list.find(song => song.trackId === music_list[current_song_index].trackId);
    music_list = currentSong ? [currentSong] : [];
};

const revertToOriginalList = () => {
    // Revert to the original full music list
    music_list = [...originalMusicList];
};
const repeatTrackMode = () => {
    // Ensure only the current song is in the music list
    createCurrentSongList();
    let current_index = current_song_index;
    
    // Load and play the current track
    loadTrack(music_list[0].trackId);
    curr_track[0].addEventListener('loadeddata', () => {
        playTrack(current_index);
    });
    //current_song_index = current_index;
};


next_btn.forEach(element => {
    element.addEventListener('mousedown', () => {
        curr_track.currentTime += 5;
    });
});

next_btn.forEach(element => {
    element.addEventListener('click', () => {
        nextTrack();
    });
});

const nextTrack = () => {
    resetUIEvents();	
    if (track_index < music_list.length - 1 && isRandom === false) {
        track_index += 1;
        hidePanels()
        current_song_index = track_index;
		resetMessages();
    } else if (track_index < music_list.length - 1 && isRandom === true) {
        let random_index = Number.parseInt(Math.random() * music_list.length);
        track_index = random_index;
        hidePanels()
        current_song_index = track_index;
		resetMessages();
    } else if (track_index === 0) {
        current_song_index = 0;
    } else {
        track_index = 0;
        hidePanels()
        current_song_index = 0;
		resetMessages();
    }


    loadTrack(current_song_index);
    playTrack(current_song_index);
}

// Event listener for double click on previous track button
prev_btn.forEach(element => {
    element.addEventListener('dblclick', function() {
	    prevTrack();
	});
});

/*/ Event listener for single click on previous track button
prev_btn.forEach(element => {
    element.addEventListener('click', function() {
    // Reset current track duration
    	curr_track.currentTime = 0;
    });
});
*/

// Event listener for holding down previous track button
prev_btn.forEach((element) => {
    element.addEventListener('mousedown', function() {
    // Increase current track's time
    	curr_track.currentTime -= 5; // You can adjust the increment value as needed
	});
});

const prevTrack = () => {
    resetUIEvents();	
	resetMessages();
    if (track_index > 0) {
        track_index -= 1;
        hidePanels()
        current_song_index = track_index;
    } else {
        track_index = music_list.length - 1;
        hidePanels()
        current_song_index = track_index;
    }

    loadTrack(current_song_index);
    playTrack(current_song_index);
}
const randomTrack = () => {
    isRandom ? pauseRandom() : playRandom();
}

const playRandom = () => {
    isRandom = true;
    randomIcon.forEach(element => {
		element.classList.add('randomActive');
	});
}

const pauseRandom = () => {
    isRandom = false;
    randomIcon.forEach(element => {
		element.classList.remove('randomActive');
	});
}

const pauseTrack = () =>{
    curr_track.pause();
    isPlaying = false;
	current_song_index = track_index;
	//metrixIdx = 'onpaused';
    // Calculate play duration
    const playDuration = (Date.now() - playStartTime) / 1000;

    // Check if play duration meets minimum requirement
    if (playDuration >= MIN_PLAY_DURATION) {
        const songId = music_list[current_song_index].trackId;
        submitMetrics(`metricsForm-pause-${songId}`, 'Paused State updated');
        handleSeekSliderFormSubmit(songId)
    }
    // Stop background animations
   	resetUIEvents();
	resetMessages();
    stopBackgroundEvents();
}

const playpauseTrack = () => {
    isPlaying ? pauseTrack() : playTrack();
}

// Function to find the index of the track with a given ID
const findTrackIndex = (trackId) => {
    for (let i = 0; i < music_list.length; i++) {
        if (music_list[i].trackId === trackId) {
            return i;
        }
    }
}; 

// Function to load and play the track
const playTrack = (e) => {
	hidePanels();   
    resetUIEvents();
    resetMessages();
	
	if (curr_track.paused && (curr_track.currentTime === 0 || curr_track.paused)) {
         curr_track.play(e)
            .then(() => {
                startBackgroundEvents();
    			showNonIdentifiedPlayers();
                isPlaying = true;
                track_index = current_song_index;
                scriptID = current_song_index;
         
                if (metrixIdx !== current_song_index && curr_track.currentTime === 0) {
                	metrixIdx = current_song_index;
				}
  
                showPanels();
                startUIEvents();
                styleMediaButtons(current_song_index);

			    // Submit play metrics form
			    if (metrixIdx === current_song_index && curr_track.currentTime === 0) {
	                const songId = music_list[current_song_index].trackId;
				    submitMetrics(`metricsForm-play-${songId}`, 'Play count updated');
				    metrixIdx = null;
			    }
               // updateFrequencyCanvas()
            })
            .catch(error => {
                console.error('Failed to play track:', error);
            });
     }
}

// Event listener for play/pause button clicks
const loadPlayBtn = (e) => {
        const clickedID = e.target.id;
        const trackIndex = findTrackIndex(clickedID);
        if (typeof trackIndex !== 'undefined') {
            if (curr_track.paused || e.id !== music_list[current_song_index].trackId) {
                loadTrack(trackIndex);
                playTrack(trackIndex);
            } else {
                pauseTrack();
                isPlaying = false;
            }
        }
}

// Panel Play Pause Track
const panelPlaypauseTrack = (e) => {
	resetMessages();
    //const trackId = music_list[current_song_index].trackId;
    //const formId = `metricsForm-play${trackId}`;
    //const playpauseForm = document.getElementById(formId);
    // Event listener to load and play the track     
    for (let i = 0; i < music_list.length; i++) {
        if (music_list[i].trackId === e.id) {
        track_index = i;
            // Set the track index to the clicked track index

            //*// Reset the seek slider value to 0 just before playing
            if (isPlaying && curr_track.currentTime === 0) {
                resetSeekSliders();
            } else 
            // Check if the track is already playing and has been played 
            if (isPlaying && e.id === music_list[current_song_index].trackId) {
                playpauseTrack(i); 
                styleMediaButtons(i)
                //playpauseForm.submit(); 
            // Check If Currently Playing Song Is The Song on playback
            } else if (isPlaying && e.id !== music_list[current_song_index].trackId) {
                // Load and play the selected track
                loadTrack(i);
                playTrack(i); // Play the track
                //playpauseForm.submit(); 
                styleMediaButtons(i)
                current_song_index = i;
                scriptID = i;
                //playpauseForm.submit(); 
            // Reset Slider To Currently Playing Value
            } else if (!isPlaying && curr_track.paused) {
				// Load and play the selected track
                loadTrack(i);
                playTrack(i); // Play the track
                styleMediaButtons(i)
                current_song_index = i;
                scriptID = i;
                //playpauseForm.submit(); 
            }
            // Break the loop once the matching track is found
            break;
        }
    }
}

// Track ID Slider from the slider element
const songSeekTo = (slider) => {
    songID = slider.id; // Get the song id from the slider element
    if (songID === music_list[current_song_index].trackId) {
	    let seekToValue = curr_track.duration * (slider.value / 100); // Calculate the seek value
	    curr_track.currentTime = seekToValue; // Update the current time
	    slider.style.cursor = 'pointer'; // Update the cursor style
		
	}
}

const seekTo = (e) => {
    let seekToValue = curr_track.duration * (e.value / 100); // Access the value of the current seek slider
    curr_track.currentTime = seekToValue;
    e.style.cursor = 'pointer'; // Update the style of the current seek slider
}

const setVolume = (e) => {
    let volumeValue = e.value / 100; // Access the first element of the NodeList
    curr_track.volume = volumeValue;
}

//const trackDescriptionElement = document.getElementById('trackDescription');
//const speedElement = document.getElementById('speed');


// Function to calculate and format total durations and time for all audio sources
const calculateAndFormatTotalDurations = () => {
    // Iterate over music_list to get audio sources
    for (let idx = 0; idx < music_list.length; idx++) {
        // Set current track source
        curr_track.src = music_list[idx].music;
        // Push the source to the array
        audioSrcs.push(curr_track);
        //loadTrack(loadTrack(music_list[idx].trackId););
    }
}

const calculateAndFormatUIDurtions = () => {
	audioSrc.forEach(track => {
        curr_track.src = track.currentSrc;
        // Push the source to the array
        audioSrcNodes.push(curr_track);
        //audioSrcNodes.push(track.currentSrc);
		//console.log(track.currentSrc)
	});
}

// Function to update UI with formatted total duration
const updateDurationUIDetails = (formattedDurationDetails) => {
        total_durations.forEach(total_duration => {
            total_duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
        });
	   
        totalDuration.forEach(total_duration => {
            total_duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
        });

        footer_total_durations.forEach(duration => {
            duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
        });

        // Update remaining duration in the UI
        remainingDurations.forEach(duration => {
            duration.textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
        });

        // Update remaining duration in the UI
        remaining_durations.forEach(track => {
            if (track.id === music_list[current_song_index].trackId) {
                track.textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
            }
        });
 
        panel_remaining_durations.forEach(track => {
            if (track.id === music_list[current_song_index].trackId) {
                track.textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
            }
        });
 
        footer_remaining_durations.forEach(duration => {
            if (duration.id === music_list[current_song_index].trackId) {
            	duration.textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
        	}
        });
}

// Function To Format Track Time Duration Details
const formatDuration = (trackCurrentTime, trackDuration) => {
     // Calculate current time in minutes and seconds
     const currentMinutes = Math.floor(trackCurrentTime / 60);
     const currentSeconds = Math.floor(trackCurrentTime - currentMinutes * 60);
	
     // Calculate duration in minutes and seconds
     const durationMinutes = Math.floor(trackDuration / 60);
     const durationSeconds = Math.floor(trackDuration - durationMinutes * 60);

     // Format current time with leading zeros
     const formattedCurrentMinutes = currentMinutes < 10 ? '0' + currentMinutes : currentMinutes;
     const formattedCurrentSeconds = currentSeconds < 10 ? '0' + currentSeconds : currentSeconds;
	
	 // Format duration with leading zeros
     const formattedTotalDurationMinutes = durationMinutes < 10 ? '0' + durationMinutes : durationMinutes;
     const formattedTotalDurationSeconds = durationSeconds < 10 ? '0' + durationSeconds : durationSeconds;	

	 // Calculate remaining duration
     const remainingDuration = Math.max(0, trackDuration - trackCurrentTime);
     const remainingMinutes = Math.floor(remainingDuration / 60);
     const remainingSeconds = Math.floor(remainingDuration - remainingMinutes * 60);
	
     // Format remaining duration with leading zeros
     let formattedRemainingMinutes = remainingMinutes < 10 ? '0' + remainingMinutes : remainingMinutes;
     let formattedRemainingSeconds = remainingSeconds < 10 ? '0' + remainingSeconds : remainingSeconds;

     let formattedDetails = {
		trackCurrentMinutes:formattedCurrentMinutes,
   		trackCurrentSeconds:formattedCurrentSeconds, 
   		trackTotalDurationMinutes:formattedTotalDurationMinutes,
   		trackTotalDurationSeconds:formattedTotalDurationSeconds,
		trackRemainingMinutes:formattedRemainingMinutes,
   		trackRemainingSeconds:formattedRemainingSeconds,
     };
     return formattedDetails   
}

const setAllUpdate = () => {
	//console.log(e);
	//console.log(audio.duration)   
	for (i = 0; i < allAudio.length; i++) {
		//if (music_list[i].trackId === e.innerText.value) {
	    if (!isNaN(allAudio[i].duration)) {
	    	// Log the list of audio sources
	    	//console.log(e.duration);
	    	//console.log(allAudio[i].duration);
			current_song_index = 0;
	  		const newCurrentTime = allAudio[i].currentTime;
	        const newDuration = allAudio[i].duration;
	        
	        let formattedDurationDetails = formatDuration(newCurrentTime, newDuration);
	
	        // Calculate seek point
	       // const seekPoint = (newCurrentTime / newDuration) * 100;
	
	        // Update seek slider value
	        //sseek_slider[i].value = seekPoint;
	
			//stylePanelCards();
	        // Update current time and total duration in the UI
	        curr_time.forEach((e) => {
	            e.textContent = formattedDurationDetails.trackCurrentMinutes + ':' + formattedDurationDetails.trackCurrentSeconds;
	         });
	
	        // Update total duration in the UI
	        let total_durations = document.querySelectorAll('.total-durations');

	        //if (music_list[i].trackId === total_durations[i].id){
	        total_durations[i].textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
	        total_durations[i].textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
	        panel_total_durations[i].textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;

	        // Update remaining duration in the UIfooter_remaining_durations
	        remaining_durations[i].textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
	        panel_remaining_durations[i].textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
	        //footer_remaining_durations[i].textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
        }
    }
}

const setUpdate = () => {
    if (!isNaN(curr_track.duration)) {
        const newCurrentTime = curr_track.currentTime;
        const newDuration = curr_track.duration;
        let formattedDurationDetails = formatDuration(newCurrentTime, newDuration);

        // Calculate seek point
        const seekPoint = (newCurrentTime / newDuration) * 100;

        // Reset the seek slider value to 0 just before playing
        seek_slider.forEach(slider => {
               slider.value = seekPoint;
        });

        if (curr_track.currentTime === 0) {
            resetSeekSliders();
        } else {
            // Update seek slider value
	        showCurrentlyPlayingSlidersPanels(seekPoint);
        }
        
        // Update current time and total duration in the UI
        curr_time.forEach((e) => {
            if (e.id === music_list[current_song_index].trackId || e.id === 'allCurrentTime') {
                e.textContent = formattedDurationDetails.trackCurrentMinutes + ':' + formattedDurationDetails.trackCurrentSeconds;
            } else {
                e.textContent = '00:00';
            }
        });           
        if (isPlaying) {
			stylePanelCards();
        }          
        else {
			removeStylePanelCards();
        }
        // Update total duration in the UI
		updateDurationUIDetails(formattedDurationDetails);
	}
}

// Function to update frequency canvas
const updateFrequencyCanvas = () => {
		canvasCtx = canvas.getContext('2d');
	    canvas.setAttribute('width', '400');
	    canvas.setAttribute('height', '200');
	    canvas.style.display = 'block'; // Make the canvas visible
	    //document.body.appendChild(canvas);
	
	    canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
	
	    // Create the audio nodes
	    audioSource = audioCtx.createMediaElementSource(curr_track);
	    gainNode = audioCtx.createGain();
	    analyserNode = audioCtx.createAnalyser();
	
	    // Connect the nodes
	    audioSource.connect(gainNode);
	    gainNode.connect(analyserNode);
	    analyserNode.connect(audioCtx.destination);
	
	    // Set up the analyser
	    analyserNode.fftSize = 2048;
	    const bufferLength = analyserNode.frequencyBinCount; //Buffer length is half fft size
	    const dataArray = new Uint8Array(bufferLength); // Returned Analyzed Data 8 Bit integers
	
	    // Get the frequency data
	    const draw = () => {
	        const WIDTH = canvas.width;
	        const HEIGHT = canvas.height;
	
			canvasCtx.clearRect(0, 0, canvas.width, canvas.height)
	        analyserNode.getByteFrequencyData(dataArray);
	
	        canvasCtx.fillStyle = 'rgb(200, 200, 200)';
	        canvasCtx.fillRect(0, 0, WIDTH, HEIGHT);
	
	        const barWidth = (WIDTH / bufferLength) * 2.5;
	        let x = 0;
			let barHeight; 

			// Animation graphic
	        for (let i = 0; i < bufferLength; i++) {
				x = 0;
	            barHeight = dataArray[i] / 2; // Assigment of barheight to variable
		
	            // Paint Canvas
	            canvasCtx.fillStyle = 'rgb(' + (barHeight + 100) + ',50,50)';
	            canvasCtx.fillRect(x, HEIGHT - barHeight, barWidth, barHeight);
	            x += barWidth + 1;
	        }
	
	        requestAnimationFrame(draw);
	    }
	
	    draw();
}

function displayFileName(inputId, targetId, detailsDivId) {
    let input = document.getElementById(inputId);
    let target = document.getElementById(targetId);
    let detailsDiv = document.getElementById(detailsDivId);

    if (input.files.length > 0) {
        let file = input.files[0];
        let fileName = file.name;
        target.innerText = fileName;
        detailsDiv.style.display = 'block';

        if (fileName.match(/\.(jpg|jpeg|png|gif)$/)) {
            let reader = new FileReader();
            reader.onload = function (e) {
                let imgSnippet = '<img src="' + e.target.result + '" alt="' + fileName + '" style="max-width: 100%; max-height: 100px;"/>';
                document.getElementById('selectedImageSnippet').innerHTML = imgSnippet;
                //image_name_input.value = fileName;
                imageURL.textContent = `Image URL: ${fileName}`;
            };
            reader.readAsDataURL(file);
        }

        if (fileName.match(/\.(mp3|ogg|wav)$/)) {
            let reader = new FileReader();
            reader.onload = function (e) {
	            let audioSnippet = '<p>Audio Preview:</p><audio controls src="' + URL.createObjectURL(file) + '" type="audio"></audio>';
	            document.getElementById('selectedAudioSnippet').innerHTML = audioSnippet;             
	            audio_name_input.value = fileName;
	            audioURL.innerText = `Audio URL: ${fileName}`;
	        };
            reader.readAsDataURL(file);

            let audioPlayer = document.getElementById('audioPlayer');
            audioPlayer.src = URL.createObjectURL(file);
            audioPlayer.src = music_list[current_song_index].music;
    		audioPlayer.load();
            

			/*
            let newSong = {
                music: URL.createObjectURL(file),
                img: '',
                artist: 'Uploaded Artist',
                name: fileName
            };
            */

            updateMusicListData(newSong);
        }
    }
}

function updateMusicListData(newSong) {
    const musicListDataElement = document.getElementById('musicListData');
    let currentMusicList = JSON.parse(musicListDataElement.getAttribute('data-musiclist'));

    currentMusicList.push(newSong);

    const updatedMusicListJson = JSON.stringify(currentMusicList);
    musicListDataElement.setAttribute('data-musiclist', updatedMusicListJson);
    musicListDataElement.innerText = updatedMusicListJson;
}

function updateMusicListData(newSong) {
    // Get the current music list
    const musicListDataElement = document.getElementById('musicListData');
    let currentMusicList = JSON.parse(musicListDataElement.getAttribute('data-musiclist'));

    // Add the new song to the list
    currentMusicList.push(newSong);

    // Convert the updated list to JSON string
    const updatedMusicListJson = JSON.stringify(currentMusicList);

    // Update the data-musiclist attribute with the new music list
    musicListDataElement.setAttribute('data-musiclist', updatedMusicListJson);

    // Update the inner content of the paragraph with the new music list
    musicListDataElement.innerText = updatedMusicListJson;
}

/*
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("#trackForm");
    const submitButton = document.querySelector("#submitBtn");
    const inputs = form.querySelectorAll(".track-creation-input");

    // Function to add required attributes and check form validity
    function initializeForm() {
        inputs.forEach(function(input) {
            input.setAttribute("required", "required");
            input.addEventListener("input", checkFormValidity);
            input.addEventListener("change", checkFormValidity);
            input.addEventListener("contextmenu", function(event) {
                event.preventDefault(); // Prevent right-click context menu
            });
        });
        checkFormValidity(); // Initial check on page load
    }

    // Function to check form validity
    function checkFormValidity() {
        let isValid = true;
        inputs.forEach(function(input) {
            if (!input.value.trim()) {
                isValid = false;
                input.classList.add("is-invalid");
            } else {
                input.classList.remove("is-invalid");
            }
        });
        submitButton.disabled = !isValid;
    }

    // Prevent multiple submissions
    form.addEventListener("submit", function(event) {
        if (submitButton.disabled) {
            event.preventDefault();
            alert("Please fill out all required fields.");
            return;
        }
        submitButton.disabled = true; // Disable the submit button to prevent multiple submissions
    });

    // Initialize the form
    initializeForm();
});


function displayFileName(inputId, targetId, detailsDivId) {
    let input = document.getElementById(inputId);
    let target = document.getElementById(targetId);
    let detailsDiv = document.getElementById(detailsDivId);

    // Check if a file has been selected
    if (input.files.length > 0) {
        let file = input.files[0];
        let fileName = file.name;
        target.innerText = fileName;

        // Show the details div
        detailsDiv.style.display = 'block';

        // Display image snippet (assuming the selected file is an image)
        if (fileName.match(/\.(jpg|jpeg|png|gif)$/)) {
            let reader = new FileReader();
            reader.onload = function (e) {
                let imgSnippet = '<img src="' + e.target.result + '" alt="' + fileName + '" style="max-width: 100%; max-height: 100px;"/>';
                document.getElementById('selectedImageSnippet').innerHTML = imgSnippet;
            };
            reader.readAsDataURL(file);
        }

        // Display audio snippet and set audio player source
        if (fileName.match(/\.(mp3|ogg|wav)$/)) {
	        let audioSnippet = '<p>Audio Preview:</p><audio controls><source src="' + URL.createObjectURL(file) + '" type="audio/mpeg"></audio>';
	        document.getElementById('selectedAudioSnippet').innerHTML = audioSnippet;
	
	        // Set audio player source
	        let audioPlayer = document.getElementById('audioPlayer');
	        audioPlayer.src = URL.createObjectURL(file);
	
	        // Create new song object
	        let newSong = [{
	            music: URL.createObjectURL(file),
	            img: '', // Assuming no image associated for simplicity
	            artist: 'Uploaded Artist', // Replace with actual artist info if available
	            name: fileName
	        }];
	
	       // Update music list data
	        updateMusicListData(newSong);
        } 
    }
}
*/