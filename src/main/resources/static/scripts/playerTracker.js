//import { displayTrackList } from './playlistPanels.js';
//import { calculateAndFormatTotalDurations } from './musicList.js';
let main_logo = document.querySelectorAll('.main-logo');
let now_playing = document.querySelectorAll('.now-playing');
let main_greeting = document.querySelectorAll('.main-greeting');
let track_art = document.querySelectorAll('.track-art');
let track_name = document.querySelectorAll('.track-name');
let track_artist = document.querySelectorAll('.track-artist');
let playerCover = document.querySelectorAll('.playerCover');
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
let div_slicer = document.querySelectorAll('.div-slicer');
let artist_hub_message;
let playlist_link_prompt;
let track_status_message;
let track_upload_message;
let main_greeting_message;
let word_slicer_messages = [];
let input_slicer_messages = [];
let music_link_card_messages = [];
let edit_form_messages = [];
let delete_form_messages = [];
let download_link_card_messages = [];
let now_playing_message = [];

let writeTextTimeout = null;
let currentTrackId = null;
let playStartTime = null;
const MIN_PLAY_DURATION = 30; // Minimum seconds to count as a valid play (example: 30 seconds)
let playDuration = 0;

let next_btn = document.querySelectorAll('.next-track');
let prev_btn = document.querySelectorAll('.prev-track');
let playpause_btn = document.querySelectorAll('.playpause-track');
let panelPlaypause_btn = document.querySelectorAll('.playpauseTrack');
let panelPlaypauseBtn = document.querySelectorAll('.panelPlaypauseBtn');
let setionPlayPauseBtn = document.querySelectorAll('.setionPlayPauseBtn');
let footerPanelPlaypauseBtn = document.querySelectorAll('.footer_playpause_btn');

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
let main_media_panel = document.querySelectorAll('.main-media-panel');
let section_media_panel = document.querySelectorAll('.section-media-panel');
let footer_media_panel = document.querySelectorAll('.footer-media-panel');

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
let scriptID;
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
    setAllUpdate()
	calculateAndFormatUIDurtions()
	calculateAndFormatTotalDurations()
};

const updateLoadedUI = () => {
    // Set the current track source
    curr_track.src = music_list[track_index].music;
    curr_track.load();
	// Create a copy of the original music list
	originalMusicList = [...music_list];

    // Update UI elements
    track_art.forEach(element => {
        element.style.backgroundImage = "url(" + music_list[track_index].img + ")";
    });
 
    track_name.forEach(element => {
        element.textContent = music_list[track_index].name;
    });
 
    track_artist.forEach(element => {
        element.textContent = music_list[track_index].artist + ': ' + music_list[track_index].genre + ' ' + 'Track';
    });
 
    now_playing.forEach(element => {
        element.textContent = "Playing music " + (track_index + 1) + " of " + music_list.length;
    	track_status_message = element.textContent = "Playing music " + (track_index + 1) + " of " + music_list.length;
    	now_playing_message.push("Playing music " + (track_index + 1) + " of " + music_list.length);
    });
 
    // Update UI Dynamic Text Elements
	word_slicer.forEach(element => {
        // Push the source to the array
        word_slicer_messages.push(element.innerText);
	});

	input_slicer.forEach(element => {
        // Push the source to the array
        input_slicer_messages.push(element.value);
	});

	music_link_card.forEach(element => {
        // Push the source to the array
        music_link_card_messages.push(element.innerText);
	});

	edit_input.forEach(element => {
        // Push the source to the array
        edit_form_messages.push(element.value);
	});

	delete_input.forEach(element => {
        // Push the source to the array
        delete_form_messages.push(element.value);
	});

	download_link_card.forEach(element => {
        // Push the source to the array
        download_link_card_messages.push(element.innerText);
	});

    currentTrackDescription = `Now Streaming ${music_list[track_index].description} From ${music_list[track_index].album}`;
    track_upload_message = 'UPLOAD NEW TRACK';
    artist_hub_message = 'STREAMING ARTISTS FROM ALL OVER THE WORLD!'
    main_greeting.forEach(element => {
		main_greeting_message = ''
		countIdx = 1;
		main_greeting_message = element.innerText;
	}); 

	// Input
	playlist_link_prompt = 'Playlists And Media Console Access!'

    // Calculate speed based on BPM
    const bpm = music_list[track_index].bpm || 120; // Default to 120 BPM if not provided
    const interval = (60 / bpm) * 1000;

    // Start the update timer for UI elements
    updateTimer = setInterval(setUpdate, interval);

    /*/ Start the interval for updating gradients after a delay matching the duration of the track
    
	gradientUpdateTimeout = setTimeout(() => {
        updateGradient();
        // Update the gradient every 10 seconds after the initial delay
        setInterval(updateGradient, 10000);
    }, curr_track.duration * 1000); // Start after the duration of the track
	*/
    // Repeat track mode handling
    if (repeatTrack()) {
        curr_track.addEventListener('ended', () => {
            repeatTrackMode();
        });
    } else {
        curr_track.addEventListener('ended', () => {
            nextTrack();
        });
    }
}

// Function to synchronize animations with BPM
const synchronizeAnimations = (bpm) => {
    const interval = (60 / bpm) * 1000;
    const animationElements = document.querySelectorAll('.animated-element');

    animationElements.forEach(element => {
        element.style.animationDuration = `${interval}ms`;
    });

    // Example: rotating album art synchronized with BPM
    track_art.forEach(element => {
        element.style.animation = `rotate ${interval}ms linear infinite`;
    });
}

/*/ Start the interval for updating gradients
const startGradientUpdateInterval = () => {
    setInterval(updateGradient, 10000);
}
*/

const loadTrack = () => {
    clearInterval(updateTimer);
    reset();

    // Update UI elements
    updateLoadedUI();

    // Start the interval for updating gradients
    //startGradientUpdate();

    loadPanelClasses();
    startBackgroundEvents();
}


const loadPanelClasses = () => {
    volume_slider.forEach(element => {
		element.classList.add('panel-card')
	})
	
    seek_slider.forEach(element => {
		element.classList.add('panel-card')
	})

    id_seek_slider.forEach(element => {
		element.classList.add('panel-card')
	})

    sectionSlider.forEach(element => {
		element.classList.add('panel-card')
	})

    panel_seek_slider.forEach(element => {
		element.classList.add('panel-card')
	})
}

const startBackgroundEvents = () => {
    random_bg_color();

    curr_track.addEventListener('pause', () => {
        track_art.forEach(element => {
            element.classList.remove('rotate');
        });
    });

    curr_track.addEventListener('play', () => {
        track_art.forEach(element => {
            element.classList.add('rotate');
        });
    });
};

const stopBackgroundEvents = () => {
    track_art.forEach(element => {
        element.classList.remove('rotate');
    });

	panelPlaypause_btn.forEach(btn => {
		btn.classList.remove('rotate');
		btn.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
	});
 
    playpause_btn.forEach(btn => {
        btn.classList.remove('rotate');
    });

    footerPanelPlaypauseBtn.forEach(btn => {
		btn.classList.remove('rotate');
    	btn.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
    });
};

const random_bg_color = () => {
    let hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e'];
    let a = '#';

    const populate = (a) => {
        for (let i = 0; i < 6; i++) {
            let x = Math.round(Math.random() * 14);
            let y = hex[x];
            a += y;
        }
        return a;
    };

    let Color1 = populate(a);
    let Color2 = populate(a);
    var angle = 'to right';

    let gradient = 'linear-gradient(' + angle + ',' + Color1 + ', ' + Color2 + ')';
    document.body.style.background = gradient;
    return gradient;
}

const random_hex_color = () => {
    let hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e'];
    let a = '#';

    const populate = (a) => {
        for (let i = 0; i < 6; i++) {
            let x = Math.round(Math.random() * 14);
            let y = hex[x];
            a += y;
        }
        return a;
    };

    let Color1 = populate(a);
    let Color2 = populate(a);
    
    let randomRgb = Color1;
    return randomRgb;
}

const random_rgb_color = () => {
    const randomValue = () => Math.floor(Math.random() * 256);
    return `rgb(${randomValue()}, ${randomValue()}, ${randomValue()})`;
}

const updateGradient = () => {
  let gradient1 = random_bg_color();
  let gradient2 = random_bg_color();
  
  // Apply gradients to keyframes
  let styleElement = document.getElementById('dynamic-gradient-style');
  if (!styleElement) {
    styleElement = document.createElement('style');
    styleElement.id = 'dynamic-gradient-style';
    document.head.appendChild(styleElement);
  }

  styleElement.innerHTML = `
    @keyframes gradient-animation {
      0% {
        background: ${gradient1};
      }
      50% {
        background: ${gradient2};
      }
      100% {
        background: ${gradient1};
      }
    }
  `;
};
/*/ Initial setup
const loadGradient = () => {
	//updateGradient();
	document.body.style.animation = 'gradient-animation 10s infinite alternate';
}
*/
// Change gradients every 10 seconds
//setInterval(updateGradient, 10000);

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
    seek_slider.forEach((element) => {
        element.value = 0;
    });
    playpause_btn.forEach((element) => {
        element.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
    });
    track_art.forEach(element => {
        element.classList.remove('rotate');
    });
};
/*
const startGradientUpdate = () => {
    // Start the interval for updating gradients after a delay matching the duration of the track
    gradientUpdateTimeout = setTimeout(() => {
        updateGradient();
        // Update the gradient every 10 seconds after the initial delay
        setInterval(updateGradient, 10000);
    }, curr_track.duration * 1000); // Start after the duration of the track
}
*/
const stopGradientUpdate = () => {
    // Clear the timeout if it exists
    clearTimeout(gradientUpdateTimeout);
    // Clear the interval if it exists
    clearInterval(gradientUpdateInterval);
}

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
    if (current_song_index < music_list.length - 1 && isRandom === false) {
        track_index += 1;
        hidePanels()
        current_song_index = track_index;
		resetMessages();
    } else if (current_song_index < music_list.length - 1 && isRandom === true) {
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
    playTrack();
}

// Event listener for double click on previous track button
prev_btn.forEach(element => {
    element.addEventListener('dblclick', function() {
	    prevTrack();
	});
});


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
    playTrack();
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

const pauseTrack = () => {
    curr_track.pause();
    isPlaying = false;

    // Calculate play duration
    playDuration = (Date.now() - playStartTime) / 1000;

    // Check if play duration meets minimum requirement
    if (playDuration >= MIN_PLAY_DURATION) {
        const songId = music_list[current_song_index].trackId;
        const seekPoint = curr_track.currentTime;
        submitMetrics(`metricsForm-play${songId}`, 'Play count updated', seekPoint);
    }

    // Stop background animations
    resetUIEvents();
    resetMessages();
    stopBackgroundEvents();
};

// Function to handle track end event
const handleTrackEnd = () => {
    // Calculate play duration
    const playDuration = (Date.now() - playStartTime) / 1000;

    // Check if play duration meets minimum requirement
    if (playDuration >= MIN_PLAY_DURATION) {
        const songId = music_list[current_song_index].trackId;
        submitMetrics(`metricsForm-play${songId}`, 'Play count updated');
    }

    // Reset currentTrackId to allow play metrics submission for the next play
    currentTrackId = null;
};

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

const playTrack = (e) => {
    hidePanels();
    resetUIEvents();
    resetMessages();

    const songId = music_list[current_song_index].trackId;
    
    // Retrieve the saved position for the current track (example implementation)
    const savedPosition = getSavedPosition(songId);
    if (savedPosition !== null) {
        curr_track.currentTime = savedPosition;
    }

    if (curr_track.paused && (curr_track.currentTime === 0 || curr_track.paused)) {
        curr_track.play()
            .then(() => {
                startBackgroundEvents();
                isPlaying = true;
                track_index = current_song_index;
                scriptID = current_song_index;
                showPanels();
                startUIEvents();

                // Check if the track is being played for the first time or resumed after pause
                if (currentTrackId !== songId) {
                    currentTrackId = songId;
                    playStartTime = Date.now();

                    // Set up an event listener to handle when the track ends
                    curr_track.onended = () => {
                        handleTrackEnd();
                    };
                }
            })
            .catch(error => {
                console.error('Failed to play track:', error);
            });
    }
};


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
            if (isPlaying && curr_track.currentTime !== 0 && e.id === music_list[current_song_index].trackId) {
                playpauseTrack(); 
                //playpauseForm.submit(); 
                //isPlaying = false;
                //current_song_index = i;
            // Check If Currently Playing Song Is The Song on playback
            } else if (isPlaying && curr_track.currentTime !== 0 && e.id !== music_list[current_song_index].trackId) {
                // Load and play the selected track
                loadTrack(i);
                playTrack(i); // Play the track
                //playpauseForm.submit(); 
                current_song_index = i;
                scriptID = i;
                //playpauseForm.submit(); 
            // Reset Slider To Currently Playing Value
            } else if (!isPlaying && curr_track.paused) {
				// Load and play the selected track
                loadTrack(i);
                playTrack(i); // Play the track
                current_song_index = i;
                scriptID = i;
                //playpauseForm.submit(); 
            }
            // Break the loop once the matching track is found
            break;
        }
    }
}

const hidePanels = () => {
	id_seek_slider.forEach(slider => {
	 	slider.classList.add('hidePanel');
	});

	sectionSlider.forEach(slider => {
	 	slider.classList.add('hidePanel');
	});

  	panel_seek_slider.forEach(slider => {
	 	slider.classList.add('hidePanel');
	});

	if (current_song_index !== track_index) {
		main_media_panel.forEach(panel => {
			panel.classList.add('hidePanel');
		});

		section_media_panel.forEach(panel => {
			panel.classList.add('hidePanel');
		});
		
		footer_media_panel.forEach(panel => {
			panel.classList.add('hidePanel');
		});
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

const showPanels = () => {
	id_seek_slider[current_song_index].classList.remove('hidePanel');
	sectionSlider[current_song_index].classList.remove('hidePanel');
	panel_seek_slider[current_song_index].classList.remove('hidePanel');
	//main_media_panel[current_song_index].classList.remove('hidePanel');
	//footer_media_panel[current_song_index].classList.remove('hidePanel');

	 main_media_panel.forEach((panel, i)=> {
		 if (panel.id === music_list[i].trackId && i === current_song_index) {
		 	 panel.classList.remove('hidePanel');
		 } else {
			 panel.classList.add('hidePanel');
		 }
	 });

	 section_media_panel.forEach((panel, i)=> {
		 if (panel.id === music_list[i].trackId && i === current_song_index) {
		 	 panel.classList.remove('hidePanel');
		 } else {
			 panel.classList.add('hidePanel');
		 }
	 });
	
	 footer_media_panel.forEach((panel, i)=> {
		 if (panel.id === music_list[i].trackId && i === current_song_index) {
		 	 panel.classList.remove('hidePanel');
		 } else {
			 panel.classList.add('hidePanel');
		 }
	 });
}

// Dynamic Playlist Panel
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
		          chevronIcon[i].classList.add('rotate');
		      } else if (playlist.id !== music_list[current_song_index].trackId && i === current_song_index) {
		          playlist.style.display = 'none';
		          showPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist';
		          chevronIcon[i].classList.remove('rotate');
		      } else {
		          playlist.style.display = 'none';
		          showPlaylistParagraph[i].firstChild.textContent = 'Add Song To Playlist';
		          chevronIcon[i].classList.remove('rotate');
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
		          sectionChevronIcon[i].classList.add('rotate');
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

//Dynamic Delete Panel
const showDeleteForm = () => {
    const deleteForm = document.querySelectorAll('.deleteForm');
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

// AJAX function for form submissions
const submitForm = (formId, successMessage, reloadPage = false) => {
    const form = document.getElementById(formId);
    const formData = new FormData(form);
    const url = form.action;
    const method = form.method.toUpperCase();

    fetch(url, {
        method: method,
        body: formData
    })
    .then(response => {
        const contentType = response.headers.get('content-type');
        if (!response.ok) {
            if (contentType && contentType.includes('application/json')) {
                return response.json().then(err => { throw err });
            } else {
                return response.text().then(err => { throw new Error(err) });
            }
        }
        if (contentType && contentType.includes('application/json')) {
            return response.json();
        } else {
            return response.text();
        }
    })
    .then(data => {
        console.log(successMessage);
        //alert(successMessage);
        showCustomNotification(successMessage);
        if (reloadPage) {
            location.reload();
        }
    })
    .catch(error => {
        console.error('Error:', error);
        //alert(`Error: ${error.message}`);
        showCustomNotification(`Error: ${error.message}`, 'error');
    });
};

// Add event listeners for form submissions
document.querySelectorAll('.playlist-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

document.querySelectorAll('.metricsForm-download').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song downloaded successfully');
    });
});

document.querySelectorAll('.like-song-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song liked successfully');
    });
});

document.querySelectorAll('.like-user-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'User liked successfully');
    });
});

document.querySelectorAll('.follow-user-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'User followed successfully');
    });
});

document.querySelectorAll('.deleteForm').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Track deleted successfully', true);
    });
});
 
//Track Metrics
// Function to submit the metrics form// Function to submit the metrics form
const submitMetrics = (formId, successMessage) => {
    const form = document.getElementById(formId);
    if (!form) {
        console.error(`Form with ID ${formId} not found.`);
        return;
    }

    const formData = new FormData(form);
    const url = form.action;
    const method = form.method.toUpperCase();

    fetch(url, {
        method: method,
        body: formData
    })
    .then(response => {
        // Check if the response is JSON
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.indexOf("application/json") !== -1) {
            return response.json().then(data => ({ data, response }));
        } else {
            return response.text().then(text => ({ text, response }));
        }
    })
    .then(({ data, text, response }) => {
        if (!response.ok) {
            throw new Error(data?.message || text);
        }
        console.log(successMessage);
        // Display custom notification
        showCustomNotification(successMessage);
    })
    .catch(error => {
        console.error('Error:', error);
        showCustomNotification(`Error: ${error.message}`, 'error');
    });
};


// Function to get saved position for a track (example implementation)
const getSavedPosition = (songId) => {
    // Retrieve the saved position from local storage or make an API call to retrieve from the backend
    // Example with local storage:
    return parseFloat(localStorage.getItem(`trackPosition-${songId}`)) || 0;
};

// Function to display custom notifications
const showCustomNotification = (message, type = 'success') => {
    const notification = document.createElement('div');
    notification.className = `custom-notification ${type}`;
    notification.textContent = message;
    document.body.appendChild(notification);

    // Remove the notification after 3 seconds
    setTimeout(() => {
        document.body.removeChild(notification);
    }, 3000);
}

// Event listener for download button
const handleDownload = (e) => {
	const downloadMetrics = document.querySelectorAll('.metricsForm-download');
	downloadMetrics.forEach((metric, i) => {
    		submitMetrics(metric.id,`Download count updated for Track ${i}`);
    });
}

// Attach the functions to the global scope if needed

// Attach the functions to the global scope if needed
 
/*/
}const handleDownload = (songId) => {
    submitMetrics(`metricsForm-download${songId}`, 'Download count updated');
};; Delete Track form submission
window.panelPlaypauseTrack = panelPlaypauseTrack;
window.handleDownload = handleDownload;
document.querySelectorAll('.deleteForm').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        // Stop the music (Assuming there's a function or a player to stop)
        if (typeof stopMusic === 'function') {
            stopMusic();
        }
        submitForm(form.id, 'Track deleted successfully', true);
    });
});
*/
const showCurrentlyPlayingSlidersPanels = (seek) => {
	//let id_panel_slider = document.getElementById(`#panelSlider_${music_list[current_song_index].trackId}`)
    if (id_seek_slider[current_song_index].id === music_list[current_song_index].trackId || track_index === current_song_index) { // || `sectionSlider_${music_list[current_song_index].trackId}` === id_seek_slider[current_song_index].id) {
	    //id_panel_slider.value = seek;
	    id_seek_slider[current_song_index].value = seek;
	    id_seek_slider[current_song_index].classList.remove('hidePanel');
	 }
	
	if (sectionSlider[current_song_index].id === music_list[current_song_index].trackId && track_index === current_song_index || `sectionSlider_${music_list[current_song_index].trackId}` === sectionSlider[current_song_index].id) {
	    sectionSlider[current_song_index].value = seek;
	    sectionSlider[current_song_index].classList.remove('hidePanel');
	 }
	         
	 if (panel_seek_slider[current_song_index].id === music_list[current_song_index].trackId && track_index === current_song_index) {
	     panel_seek_slider[current_song_index].value = seek;
	     panel_seek_slider[current_song_index].classList.remove('hidePanel');
	 }
}

const resetSeekSliders = () => {
    id_seek_slider.forEach(slider => {
        slider.value = 0;
    });
    panel_seek_slider.forEach(slider => {
        slider.value = 0;
    });
}

const resetUIEvents = () => {
	//stylePanelCards();
    track_art.forEach(element => {
		element.classList.remove('rotate');
	});

    wave.forEach(element => {
		element.classList.remove('loader');
	});
	
	playpause_btn.forEach(element => {
       element.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
		//element.classList.add('rotate');
    });

    panelPlaypause_btn.forEach(btn => {
		btn.classList.remove('rotate');
        btn.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
    });

	setionPlayPauseBtn.forEach(panel => {
		panel.classList.remove('rotate');
        panel.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
	});
	
	footerPanelPlaypauseBtn.forEach(panel => {
		panel.classList.remove('rotate');
        panel.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
	});
	
}

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

const changePlayerCover = (e) => {
	const mainPlayer = document.getElementById("main-player-cover");
	if (isPlaying === true || e.id === mainPlayer.id) {
    	e.classList.add("imageCover");
		e.style.backgroundImage = "url(" + music_list[current_song_index].img + ")";
    	e.style.transitionProperty = 'background-image';
     	e.style.transitionDuration = '0.5s';
    	e.style.transitionTimingFunction = 'ease-in-out';
		//console.log(e.style.backgroundImage)
	}
}

const resetPlayerCover = (e) => {
    e.style.transitionProperty = 'background-image';
    e.style.transitionDuration = '0.5s';
    e.style.transitionTimingFunction = 'ease-in-out';
	//e.style.backgroundImage = "url(img/landing-img.jpg)";
	//e.style.backgroundImage = "url(img/purplecrowd-unsplash.jpg)";
}

const startUIEvents = () => {		
    wave.forEach(element => {
       element.classList.add('loader');
    });
    media_buttons.forEach(btn => {
       btn.classList.add('playerCover');
    });
	
    stroke.forEach(element => {
       element.style.background = random_bg_color();
    });

    playpause_btn.forEach(element => {
        element.innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
		//element.classList.add('rotate');
    });
    
    //currentTrack = music_list[track_index];
    const bpm = curr_track.bpm || 120; // Default to 120 BPM if not provided
    speed = (60 / bpm) * 1000 / 1; // speedElement.value; // Recalculate speed based on BPM
	//let currentTrackDescription = `Track ${current_song_index}: ${currentTrack.description} From ${currentTrack.album} Album`;

	writeText(currentTrackDescription);

    playerCover.forEach(cover => {
        cover.style.transition = 'background-image 0.5s ease-in-out';
        cover.style.cursor = 'pointer';
        cover.style.backgroundImage = `url(${music_list[current_song_index].img})`;
        cover.classList.add('imageCover');
    });

    panelPlaypause_btn[track_index].classList.add('rotate');
    panelPlaypause_btn[track_index].innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
    setionPlayPauseBtn[track_index].classList.add('rotate');
    setionPlayPauseBtn[track_index].innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
    footerPanelPlaypauseBtn[track_index].classList.add('rotate');
    footerPanelPlaypauseBtn[track_index].innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
}

//const trackDescriptionElement = document.getElementById('trackDescription');
//const speedElement = document.getElementById('speed');

const resetElements = () => {
    track_status.forEach(content => {
        content.textContent = track_status_message;
        content.classList.add('hidePanel');
    });

    track_upload.forEach(content => {
        content.textContent = track_upload_message;
    });

    artist_hub.forEach(content => {
        content.textContent = artist_hub_message;
    });

    word_slicer.forEach((content, i) => {
        content.textContent = word_slicer_messages[i];
        //content.classList.remove('word-slicer');
    });

    input_slicer.forEach((content, i) => {
        content.value = input_slicer_messages[i];
        //content.classList.remove('word-slicer');
    });

    track_description.forEach(panel => {
        panel.textContent = currentTrackDescription;
        panel.classList.add('hidePanel');
    });

    playlist_link.forEach(content => {
        content.value = playlist_link_prompt;
    });

    edit_input.forEach((panel, i) => {
        panel.value = edit_form_messages[i];
    });

    delete_input.forEach((input, i) => {
        input.value = delete_form_messages[i];
    });
 
    track_name.forEach(element => {
        element.textContent = music_list[current_song_index].name;
    });
    
    panel_greetings.forEach(greeting => {
        greeting.textContent = main_greeting_message;
        greeting.classList.add('hidePanel');
    });

    main_greeting.forEach(greeting => {
        greeting.textContent = main_greeting_message;
    });
    
};

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
			//element.style.backgroundImage  =` url(${music_list[track_index].img})`;  
			element.style.animation = '';
			element.classList.add('imageCover'); 
	        element.style.transitionProperty = 'all';
	        element.style.transitionDuration = `${bpm / 3.3 / bpm}s`;
	        element.style.transitionTimingFunction = 'ease-in-out'; 
        } else {
			element.style.animation = `typewriter 4s steps(44) 1s infinite normal both, blinkTextCursor 500ms steps(44) infinite normal`; 
            element.innerText = main_greeting_message;  
        }
    });

    word_slicer.forEach((content, i) => {
        if (isPlaying || content.id === music_list[i].trackId) {
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
        if (isPlaying || input.id === music_list[i].trackId) {
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

// 
const stylePanelCards = () => {
    const panelCards = document.querySelectorAll('.panel-card');
    const trackDescriptions = document.querySelectorAll('.track-description');

    if (panelCards.length > 0) {
        panelCards.forEach(panelCard => {
            panelCard.style.background = random_bg_color();
        });
    } else {
        console.warn('No panel cards found to style.');
    }

    if (trackDescriptions.length > 0) {
        trackDescriptions.forEach(element => {
            element.style.color = 'black';
            element.parentElement.opacity = '0.9';
        });
    } else {
        console.warn('No track descriptions found to style.');
    }
};

const removeStylePanelCards = () => {
    // Update panel cards with random background color
    let x;
    let y;
    var angle = 'to right';
	let gradient = `linear-gradient( ${angle},  ${x} , ${y})`;
    let panel_cards = document.querySelectorAll(".panel-card");
        panel_cards.forEach((panel_card, i) => {
			x = i;
			y = i;
            panel_card.style.background = gradient;
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
               slider.classList.add('panel-card')
        });

        if (curr_track.currentTime === 0) {
            resetSeekSliders();
        } else {
            // Update seek slider value
	        showCurrentlyPlayingSlidersPanels(seekPoint);
        }
        
        if (isPlaying) {
			stylePanelCards();
        }

        // Update current time and total duration in the UI
        curr_time.forEach((e) => {
            if (e.id === music_list[current_song_index].trackId || e.id === 'allCurrentTime') {
                e.textContent = formattedDurationDetails.trackCurrentMinutes + ':' + formattedDurationDetails.trackCurrentSeconds;
            } else {
                e.textContent = '00:00';
            }
        });

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