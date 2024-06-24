// Function to load the track details from the hidden input fields in the DOM
export function loadTrackDetailsFromDOM() {
    // Update the track details in the player
    track_name.forEach(element => {
		element.textContent = trackTitle;
	});
    track_artist.forEach(element => {
		element.textContent = trackArtist;
	});
    track_art.forEach(element => {
		element.style.backgroundImage = "url(" + trackImageSrc + ")";
	});
    curr_track.src = audioSrc;
    curr_track.load();
} 

// Function to set repeat mode for the track
export function setRepeatTrack() {
    // Check if repeat mode is active and slider value is at the end
    if (repeatTrack() && parseInt(seek_slider.value) === parseInt(seek_slider.max)) {
        // Reset the track to the beginning
       repeatTrackMode();
    }
}
// Attempt to parse the JSON data And Function to process the musicList
export function parseMusicList() {
	
	try {
	    //const musicListData = document.getElementById('musicListData').innerText;
	    music_list = JSON.parse(musicListData);
		//music_list = musicListMap;
		//music_list = formattedSongs;
		//music_list = formattedMusicMapList;
	    /*
	    audioSrc.forEach(track => {
			music_list = track.src
		});
		*/
	    console.log('Music list parsed successfully: dataLength', music_list.length);
		// Iterate over each song in the formattedSongs array
	    //for (let index = 0; index < music_list.length; index++) {
		    // Call loadTrack for each song
			//loadTrack();

			//curr_tracks += document.createElement('audio');
			/*
		    console.log(music_list[index].img); // Accessing the img property of the first object
		    console.log(music_list[index].name); // Accessing the name property of the first object
		    console.log(music_list[index].artist); // Accessing the artist property of the first object
		    console.log(music_list[index].music); // Accessing the music property of the first object
		    */
		    //console.log('Music list parsed successfully: dataLength', music_list.length);
		//};
	} catch (error) {
	    console.error('Error parsing music list JSON:', error);
	    // Handle parsing error (e.g., show an error message to the user)
	}
}
// Function to calculate and format total durations and time for all audio sources
export function calculateAndFormatTotalDurations() {
    // Clear previous total duration and time
    let totalDurationMinutes = 0;
    let totalDurationSeconds = 0;

    // Iterate over music_list to get audio sources
    for (let idx = 0; idx < music_list.length; idx++) {
        // Set current track source
        curr_track.src = music_list[idx].music;
        // Push the source to the array
        audioSrcs.push(curr_track.src);
    }

    // Calculate total duration
    audioSrcs.forEach(audioSrc => {
        const audio = new Audio(audioSrc);
        audio.onloadedmetadata = function() {
            totalDurationMinutes += Math.floor(audio.duration / 60);
            totalDurationSeconds += Math.floor(audio.duration % 60);

            // Update UI with formatted total duration
            updateTotalDurationUI(totalDurationMinutes, totalDurationSeconds);
        };
    });
}

// Function to load current tracks
export function loadCurrentTracks() {
    for (i = 0; i < music_list.length; i++) {
        curr_tracks[i].src = music_list[i].music;
        curr_tracks[i].load();
        allCurr_tracks.push(curr_track.src);
    }
}

// Function to extract numeric part from IDs mixed with letters and numbers
export function extractNumericParts(ids) {
    const numericParts = [];
    ids.forEach(id => {
        const matches = id.match(/\d+/);
        if (matches) {
            const numericPart = parseInt(matches[0]);
            const namePart = id.split(matches[0])[0];
            numericParts.push({ id: numericPart, name: namePart });
        }
    });
    return numericParts;
}





/////////////////////

/*
function updatePlayPauseButton(idx) {
    const playpauseButton = document.querySelectorAll('.dynamicPlayPauseTrack');
    playpauseButton.forEach(btn => {
		if (idx === music_list[track_index].trackId){
    		btn.classList.toggle('playing', isPlaying);
		}
	});
}
function updatePlayPauseIcon(clickedId) {
    // Get all playpause-track spans
    const playpauseTracks = document.querySelectorAll('.playpause-track');

    // Iterate through each span
    panelPlaypause_btn.forEach(span => {
        // Check if the span's parent id matches the clickedId
        if (span.parentNode.id === clickedId && isPlaying === true) {
            // If yes, show the play/pause icon
             '<i class="fa fa-pause-circle fa-5x"></i>' ;
        } else if (span.parentNode.id === clickedId && isPlaying === true) {: '<i class="fa fa-play-circle fa-5x"></i>'
        } else {
            // If no, remove the icon
            span.innerHTML = '';
        }
    });
}

*/

/*
function extractNumericParts(ids) {
    const numericParts = [];
    ids.forEach(id => {
        const matches = id.match(/\d+/);
        if (matches) {
            const numericPart = parseInt(matches[0]);
            const namePart = id.split(matches[0])[0];
            numericParts.push({ id: numericPart, name: namePart });
        }
    });
    return numericParts;
}
/ Retrieve the id of the current song in the music list
const currentTrackId = music_list[track_index].trackId;

// Highlight the currently playing song
highlightCurrentlyPlayingSong(currentTrackId);

// Function to highlight the currently playing song
function highlightCurrentlyPlayingSong(trackId) {
    // Remove highlight from all songs
    document.querySelectorAll('.song').forEach(song => {
        song.classList.remove('currently-playing');
    });

    // Find the song with the given trackId and add a highlight class
    const currentSong = document.getElementById(trackId);
    if (currentSong) {
        currentSong.classList.add('currently-playing');

        // Optionally, you can scroll to the currently playing song if needed
        currentSong.scrollIntoView({ behavior: 'smooth', block: 'center' });
    }
}
*/