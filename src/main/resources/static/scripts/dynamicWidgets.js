
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
