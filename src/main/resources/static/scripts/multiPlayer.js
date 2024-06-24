//import { displayTrackList } from './playlistPanels.js';
//import { calculateAndFormatTotalDurations } from './musicList.js';
let now_playing = document.querySelectorAll('.now-playing');
let track_art = document.querySelectorAll('.track-art');
let track_name = document.querySelectorAll('.track-name');
let track_artist = document.querySelectorAll('.track-artist');

let next_btn = document.querySelectorAll('.next-track');
let prev_btn = document.querySelectorAll('.prev-track');
let playpause_btn = document.querySelectorAll('.playpause-track');
let panelPlaypause_btn = document.querySelectorAll('.playpauseTrack');
let panelPlaypauseBtn = document.querySelectorAll('.panelPlaypauseBtn');
let footerPanelPlaypauseBtn = document.querySelectorAll('.footer_playpause_btn');

let randomIcon = document.querySelectorAll('.fa-random');
let repeatIcon = document.querySelectorAll('.repeat-track');

let wave = document.querySelectorAll('.wave');
const panelCard = document.querySelector('.panel-card')
let seek_slider = document.querySelectorAll('.seek_slider');
let volume_slider = document.querySelectorAll('.volume_slider');
let id_seek_slider = document.querySelectorAll('.id_seek_slider');
let panel_seek_slider = document.querySelectorAll('.panel_seek_slider');

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

let curr_track = document.createElement('audio');
let curr_tracks = document.createElement('audio');
const allAudio = document.querySelectorAll("audio")
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

let audioLink;
let music_list;
let track_index = 0;
let current_song_index;

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
	    console.log('Music list parsed successfully: dataLength', music_list.length);
	} catch (error) {
	    console.error('Error parsing music list JSON:', error);
	    // Handle parsing error (e.g., show an error message to the user)
	}
}

// Array to store audio sources
let audioSrcs = [];
let allCurr_tracks = [];

// Load the first track when the page loads
window.onload = function() {
    parseMusicList();
    loadTrack();
    //loadCurrentTracks();
    setAllUpdate()
	calculateAndFormatTotalDurations()
};

// Function to load a track by its index
const loadTrack = () => {
    clearInterval(updateTimer);
    reset();

    const audioLink = document.querySelectorAll('.audioLink');
    audioLink.forEach(element => {
        element.href = music_list[track_index].music;
    });

    curr_track.src = music_list[track_index].music;
    curr_track.load();

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
    });
    panelCard.style.background = random_bg_color();

    updateTimer = setInterval(setUpdate, 1000);

    startBackgroundEvents();

    if (repeatTrack()) {
        curr_track.addEventListener('ended', () => {
            repeatTrackMode();
        });
    } else {
        curr_track.addEventListener('ended', () => {
            nextTrack();
        });
    }
};

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

    let gradient = 'linear-gradient(' + angle + ',' + Color1 + ', ' + Color2 + ")";
    document.body.style.background = gradient;
    return gradient;
};

const reset = () => {
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

const repeatTrack = () => {
    isRepeat = !isRepeat;
    repeatIcon.forEach(element => {
        element.innerHTML = isRepeat ?
            '<i class="fas fa-repeat fa-2x"></i>' :
            '<i class="fas fa-repeat fa-3x"></i>';
        return isRepeat;
    });
};

const repeatTrackMode = () => {
    curr_track.forEach((element) => {
        element.currentTime = 0;
        let current_index = track_index;
        loadTrack(current_index);
        curr_track.addEventListener('loadeddata', playTrack(current_index));
    });
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
    if (track_index < music_list.length - 1 && isRandom === false) {
        track_index += 1;
    } else if (track_index < music_list.length - 1 && isRandom === true) {
        let random_index = Number.parseInt(Math.random() * music_list.length);
        track_index = random_index;
    } else {
        track_index = 0;
    }

    loadTrack();
    playTrack();
}

// Event listener for double click on previous track button
prev_btn.forEach(element => {
    element.addEventListener('dblclick', function() {
	    prevTrack();
	});
});

// Event listener for single click on previous track button
prev_btn.forEach(element => {
    element.addEventListener('click', function() {
    // Reset current track duration
    	curr_track.currentTime = 0;
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
    if (track_index > 0) {
        track_index -= 1;
    } else {
        track_index = music_list.length - 1;
    }
    loadTrack();
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

const playpauseTrack = () => {
    isPlaying ? pauseTrack() : playTrack();
}

// Function to play the track
const playTrack = (e) => {
	//let marker = track_index;
    // Check if the play operation is allowed by the browser and Play the track only if it's paused and at the beginning
    if (curr_track.paused && (curr_track.currentTime === 0 || curr_track.paused)) {

    // Reset all icons to play icon
    panelPlaypause_btn.forEach(btn => {
		btn.classList.remove('rotate');
        btn.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
    });

	footerPanelPlaypauseBtn.forEach(panel => {
		panel.classList.remove('rotate');
        panel.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
	});

	//id_seek_slider[current_song_index].value === music_list[track_index].trackId ? id_seek_slider[current_song_index].value: id_seek_slider[current_song_index].value = 0; 

    //id_seek_slider[i].value = music_list[i].trackId !== id_seek_slider[i].id ? 0 : id_seek_slider[i].value;
	
        curr_track.play()
            .then(() => {
                // Start background animations
                startBackgroundEvents();
                
                // Update UI
                isPlaying = true;
                wave.forEach(element => {
                    element.classList.add('loader');
                });
 
                playpause_btn.forEach(element => {
                    element.innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
					//element.classList.add('rotate');
                });

				panelPlaypause_btn[track_index].classList.add('rotate');
                panelPlaypause_btn[track_index].innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
               	footerPanelPlaypauseBtn[track_index].classList.add('rotate');
                footerPanelPlaypauseBtn[track_index].innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
				//});
            })
            .catch(error => {
                // Handle play failure
                console.error('Failed to play track:', error);
            });
    } else {
        // Track is already playing or has already started playing
        // No action required
    }
}

// Panel Play Pause Track
const panelPlaypauseTrack = (e) => {
    //*/ Reset all icons to play icon

    // Event listener to load and play the track     
    for (let i = 0; i < music_list.length; i++) {
        if (music_list[i].trackId === e.id) {
            // Set the track index to the clicked track index
            track_index = i;

            //*// Reset the seek slider value to 0 just before playing
            if (isPlaying && curr_track.currentTime === 0) {
                id_seek_slider.forEach(slider => {
                    slider.value = 0;
                }); 

                panel_seek_slider.forEach(slider => {
                    slider.value = 0;
                });
            } else 
            // Check if the track is already playing and has been played 
            if (isPlaying && curr_track.currentTime !== 0) {
                pauseTrack(); // Pause the track
                id_seek_slider[current_song_index].id = id_seek_slider[current_song_index].value 
                panel_seek_slider[current_song_index].id = panel_seek_slider[current_song_index].value 
                isPlaying = false;
                //current_song_index = i;
            // Reset Slider To Currently Playing Value
            } else if (!isPlaying && curr_track.paused) {
                id_seek_slider[current_song_index].value = music_list[current_song_index].trackId !== id_seek_slider[current_song_index].id ? 0 : id_seek_slider[current_song_index].value;
                panel_seek_slider[current_song_index].value = music_list[current_song_index].trackId !== panel_seek_slider[current_song_index].id ? 0 : panel_seek_slider[current_song_index].value;

                // Load and play the selected track
                loadTrack(i);
                playTrack(i); // Play the track
                current_song_index = i;
            }

            // Break the loop once the matching track is found
            break;
        }
    }
}

const pauseTrack = () =>{
    curr_track.pause();
    isPlaying = false;

    track_art.forEach(element => {
		element.classList.remove('rotate');
	});

    wave.forEach(element => {
		element.classList.remove('loader');
	});

    // Stop background animations
    stopBackgroundEvents();
}

// Track ID Slider from the slider element
const songSeekTo = (slider) => {
    songId = slider.id; // Get the song id from the slider element
    let seekToValue = curr_track.duration * (slider.value / 100); // Calculate the seek value
    curr_track.currentTime = seekToValue; // Update the current time
    slider.style.cursor = 'pointer'; // Update the cursor style
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

// Function to calculate and format total durations and time for all audio sources
const calculateAndFormatTotalDurations = () => {
    // Iterate over music_list to get audio sources
    for (let idx = 0; idx < music_list.length; idx++) {
        // Set current track source
        curr_track.src = music_list[idx].music;
        // Push the source to the array
        audioSrcs.push(curr_track);
        //loadTrack(idx);
    }
}

// Function to update UI with formatted total duration
function updateTotalDurationUI(totalDurationMinutes, totalDurationSeconds) {
    // Update total duration in the UI
    let total_durations = document.querySelectorAll('.total-duration');
    totalDuration.forEach(duration => {
        duration.textContent = "Total Duration: " + totalDurationMinutes + ':' + totalDurationSeconds;
    });

    total_durations.forEach((duration) =>{
        duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinues + ':' + formattedDurationDetails.trackTotalDurationSeconds;
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

const setAllUpdate = (e) => {
	console.log(e);
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

	        // Update remaining duration in the UI
	        remaining_durations[i].textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
	        panel_remaining_durations[i].textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
        }
    }
}

const setUpdate = () => {
    if (!isNaN(audioSrcs[current_song_index].duration)) {
        const newCurrentTime = audioSrcs[current_song_index].currentTime;
        const newDuration = audioSrcs[current_song_index].duration;
        let formattedDurationDetails = formatDuration(newCurrentTime, newDuration);

        // Calculate seek point
        const seekPoint = (newCurrentTime / newDuration) * 100;

        // Reset the seek slider value to 0 just before playing
        seek_slider.forEach(slider => {
               slider.value = seekPoint;
        });
        if (curr_track.currentTime === 0) {
            id_seek_slider.forEach(slider => {
                if (slider.id !== music_list[current_song_index].trackId && track_index !== current_song_index) {
                	slider.value = 0;
                }
            });
            panel_seek_slider.forEach(slider => {
                if (slider.id !== music_list[current_song_index].trackId && track_index !== current_song_index) {
                slider.value = 0;
                }
            });
        } else {
            // Update seek slider value
            id_seek_slider.forEach(slider => {
                if (slider.id === music_list[current_song_index].trackId) {
                    slider.value = seekPoint;
                }
            });

            
           if (panel_seek_slider[current_song_index].id === music_list[current_song_index].trackId && track_index === current_song_index) {
                    panel_seek_slider[current_song_index].value = seekPoint;
                }
            //});
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
        total_durations.forEach((total_duration) => {
            total_duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
        });
        /*
        panel_total_durations.forEach((total_duration) => {
            total_duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
        });
        */

        totalDuration.forEach((total_duration) => {
            total_duration.textContent = "Total Duration: " + formattedDurationDetails.trackTotalDurationMinutes + ':' + formattedDurationDetails.trackTotalDurationSeconds;
        });

        // Update panel cards with random background color
        let panel_cards = document.querySelectorAll('.panel-card');
        panel_cards.forEach(panel_card => {
            panel_card.style.background = random_bg_color();
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

        // Update remaining duration in the UI
        remainingDurations.forEach(function (remaining_duration) {
            remaining_duration.textContent = formattedDurationDetails.trackRemainingMinutes + ':' + formattedDurationDetails.trackRemainingSeconds;
        });
    }
};
