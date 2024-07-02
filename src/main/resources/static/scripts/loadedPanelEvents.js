
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
const MIN_PLAY_DURATION = 30;
const resetElements = () => {
    
};

const styleMediaButtons = (btnIdx) => {
	media_buttons.forEach(btn => {
		if (btnIdx === current_song_index) {
        	btn.style.backgroundImage = `url(${music_list[btnIdx].img})`;
        	btn.classList.add('imageCover');
        } else {
        	btn.classList.add('imageCover');
        }
    	if (isPlaying){
        	btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
        } else {
        	btn.style.animation = `panel-spreader 3.2s infinte ease-in-out`;
		}
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });

	inner_panel_cover.forEach((btn, i) => {
        btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
        btn.style.backgroundImage = `url(${music_list[i].img})`;
        btn.classList.add('imageCover');
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });

	outer_panel_cover.forEach((btn, i) => {
        //btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
       	btn.style.backgroundImage = `url(${music_list[i].img})`;
       	btn.classList.add('imageCover');
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });

	section_inner_panel_cover.forEach((btn, i) => {
        //btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
        btn.style.backgroundImage = `url(${music_list[i].img})`;
        btn.classList.add('imageCover');
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });

	section_outer_panel_cover.forEach((btn, i) => {
        //btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
       	btn.style.backgroundImage = `url(${music_list[i].img})`;
       	btn.classList.add('imageCover');
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });

	footer_inner_panel_cover.forEach((btn, i) => {
        //btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
        btn.style.backgroundImage = `url(${music_list[i].img})`;
        btn.classList.add('imageCover');
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });

	footer_outer_panel_cover.forEach((btn, i) => {
        //btn.style.animation = `panel-spreader 3.2s 1 ease-in-out`;
       	btn.style.backgroundImage = `url(${music_list[i].img})`;
       	btn.classList.add('imageCover');
    	btn.style.transitionProperty = 'background-image';
        btn.style.transitionDuration = `${bpm / 3.3 / bpm}s`;
    	btn.style.transitionTimingFunction = 'ease-in-out';
        btn.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });
}

const styleMediaCovers = () => {
}

const startUIEvents = () => {
    wave.forEach(element => {
       element.classList.add('loader');
    });

	
    stroke.forEach(element => {
       element.style.background = random_bg_color();
    });
    //applyDynamicStylesToTrackArt();

    // Apply dynamic panel spreader keyframes
    //applyDynamicPanelSpreader();
    //currentTrack = music_list[track_index];
	writeText(currentTrackDescription);

    playerCover.forEach((cover)=> {
        cover.style.cursor = 'pointer';
        cover.style.backgroundImage = `url(${music_list[current_song_index].img})`;
        cover.classList.add('imageCover');
    	cover.style.transitionProperty = 'background-image';
     	cover.style.transitionDuration = '0.5s';
    	cover.style.transitionTimingFunction = 'ease-in-out';
    	cover.style.transition = `background-image ${bpm / 3.3 / bpm}s ease-in-out`;
    });
		
    upper_panel_play_btn.forEach(btn => {
        if (btn.id === `${music_list[current_song_index].trackId}`) {
			btn.animation = `panel-spreader 3.2s 1 ease-in-out`;
            btn.innerHTML = '<i class="rotate track-art-widget fa fa-pause-circle fa-5x"></i>';
			//btn.classList.add('playerCover');
        }
    });
	
	panelPlaypause_btn.forEach(btn => {
        if (btn.id === `${music_list[current_song_index].trackId}`) {
            btn.innerHTML = '<i class="rotate track-art-widget fa fa-pause-circle fa-5x"></i>';
			//btn.classList.add('playerCover');
        }
    });

    footerPanelPlaypauseBtn.forEach(btn => {
        if (btn.id === `${music_list[current_song_index].trackId}`) {
	    	btn.innerHTML = '<i class="track-art-widget fa fa-pause-circle fa-5x"></i>';
			btn.classList.add('rotate');
			//btn.classList.add('playerCover');
		}
    });
}

const resetUIEvents = () => {
    wave.forEach(element => {
        element.classList.remove('loader');
    });

    id_play_panel.forEach(btn => {
        btn.classList.remove('hidePanel');
    });

    non_id_play_panel.forEach(btn => {
        btn.classList.add('hidePanel');
    });
 	
	playpause_btn.forEach((element, i) => {
	   //upper_panel_play_btn[i].innerHTML = '<i class="upper-track-art-widget playerCover fa fa-play-circle fa-5x"></i>';
       element.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
    });

	play_panel.forEach((panel) => {
		panel.classList.remove('playerCover');
	});
	
   upper_panel_play_btn.forEach(btn => {
        btn.innerHTML = '<i class="track-art-widget fa fa-play-circle fa-5x"></i>';
		//btn.classList.remove('playerCover');
    });
	
   footer_panel_play_btn.forEach(btn => {
		btn.classList.remove('rotate');
        btn.innerHTML = '<i class="track-art-widget fa fa-play-circle fa-5x"></i>';
		//btn.classList.remove('playerCover');
    });

    panelPlaypause_btn.forEach(btn => {
		btn.classList.remove('rotate');
		btn.innerHTML = '<i class="upper-track-art-widget fa fa-play-circle fa-5x"></i>';
    });

    panelPlaypauseBtn.forEach(btn => {
		btn.classList.remove('rotate');
		//btn.innerHTML = '<i class="upper-track-art-widget fa fa-play-circle fa-5x"></i>';
    });

    footerPanelPlaypauseBtn.forEach(btn => {
		btn.classList.remove('rotate');
    	//btn.innerHTML = '<i class="track-art fa fa-play-circle fa-5x"></i>';
    });
    		
	spreader.forEach(panel => {
		panel.classList.remove('spreader');
	});

}

const startBackgroundEvents = () => {
    //random_bg_color();
    curr_track.addEventListener('play', () => {
        track_art.forEach(element => {
            element.classList.add('rotate');
        });

		/*play_panel.forEach((panel,i) => {
			if (panel.id === music_list[i].trackId || panel.id === `section-play-panel-${music_list[i].trackId}`) {
				panel.classList.add('playerCover');
			} 
		});*/

	    //panelPlaypause_btn[current_song_index].innerHTML = '<i class="rotate upper-track-art-widget fa fa-pause-circle fa-5x"></i>';

		
 	
		playpause_btn.forEach((element, i) => {
			if (element.id === `upper-panel-play-pause-icon-${music_list[current_song_index].trackId}`) {
				element.innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
			} else {
			   //upper_panel_play_btn[i].innerHTML = '<i class="upper-track-art-widget playerCover fa fa-play-circle fa-5x"></i>';
		       element.innerHTML = '<i class="fa fa-pause-circle fa-5x"></i>';
	       }
	    });
	});
};

const stopBackgroundEvents = () => {   
	track_art.forEach(element => {
        element.classList.remove('rotate');
    });

    non_id_play_panel.forEach(element => {
	   element.style.display = '';
       element.classList.remove('hidePanel');
    });

    id_play_panel.forEach(element => {
       element.classList.add('hidePanel');
    });
}

const loadedPanels = () => {
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
};
loadedPanels()

const showPanels = () => {
	id_seek_slider[current_song_index].classList.remove('hidePanel');
	sectionSlider[current_song_index].classList.remove('hidePanel');
	panel_seek_slider[current_song_index].classList.remove('hidePanel');
	main_media_panel[current_song_index].classList.remove('hidePanel');
	section_media_panel[current_song_index].classList.remove('hidePanel');
	footer_media_panel[current_song_index].classList.remove('hidePanel');
	footer_links[current_song_index].classList.remove('hidePanel');
	//main_media_panel[current_song_index].classList.remove('hidePanel');let current_spreader = document.getElementById(`${music_list[current_song_index].trackId}`);
	//footer_media_panel[current_song_index].classList.remove('hidePanel');current_spreader.classList.add('spreader');
	//div_slicer[current_song_index].classList.remove('hidePanel');
	//section_div_slicer[current_song_index].classList.remove('hidePanel');
	//footer_div_slicer[current_song_index].classList.remove('hidePanel');
	div_slicer.forEach((element, i) => {
		if (element.id === music_list[current_song_index].trackId) {
       		element.classList.remove('hidePanel');
       	} else {
       		element.classList.add('hidePanel');
       	}
	});

	footer_div_slicer.forEach((element, i) => {
		if (element.id === music_list[current_song_index].trackId) {
       		element.classList.remove('hidePanel');
       	} else {
       		element.classList.add('hidePanel');
       	}
	});
}

const hidePanels = () => {
	footer_links.forEach((element, i) => {
       	element.classList.add('hidePanel');
	})
/*	div_slicer.forEach((element, i) => {
		if (element.id !== music_list[track_index].trackId) {
       		element.classList.add('hidePanel');
       	} else {
       		element.classList.remove('hidePanel');
       	}
	});

	
	section_div_slicer.forEach((element, i) => {
		if (i !== current_song_index) {
        element.classList.add('hidePanel');
        }
	});
	*/

	id_seek_slider.forEach(slider => {
	 	slider.classList.add('hidePanel');
	});

	sectionSlider.forEach(slider => {
	 	slider.classList.add('hidePanel');
	});

  	panel_seek_slider.forEach(slider => {
	 	slider.classList.add('hidePanel');
	});

	main_media_panel.forEach(panel => {
		panel.classList.add('hidePanel');
	});

	section_media_panel.forEach(panel => {
		panel.classList.add('hidePanel')
	});
		
	footer_media_panel.forEach(panel => {
		panel.classList.add('hidePanel');
	});
}



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

// Extra Funtion For Panel and track description
const stylePanelCards = () => {
    const panelCards = document.querySelectorAll('.panel-card');
    const trackDescriptions = document.querySelectorAll('.track-description');
 
    panelCards.forEach(panelCard => {
        panelCard.style.background = random_bg_color();
    });


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

const changePlayerCover = (elem) => {
	const mainPlayerCover = document.getElementById("main-player-cover");
	const sectionPlayerCover = document.getElementById("section-player-cover");
	const footerPlayerCover = document.getElementById("footer-player-cover");
	
	mainPlayerCover.classList.add("imageCover");
	mainPlayerCover.style.backgroundImage = "url(" + music_list[current_song_index].img + ")";
	mainPlayerCover.transition = `all ${bpm / 3.3 / bpm}s ease-in`;
	
	sectionPlayerCover.classList.add("imageCover");
	sectionPlayerCover.style.backgroundImage = "url(" + music_list[current_song_index].img + ")";
	sectionPlayerCover.transition = `all ${bpm / 3.3 / bpm}s ease-in`;
	
	footerPlayerCover.classList.add("imageCover");
	footerPlayerCover.style.backgroundImage = "url(" + music_list[current_song_index].img + ")";
	footerPlayerCover.transition = `all ${bpm / 3.3 / bpm}s ease-in`;
	
	playerCover.forEach(e => {
	
			e.style.backgroundImage = "url(" + music_list[current_song_index].img + ")";
	    	e.style.transitionProperty = 'background-image';
	     	e.style.transitionDuration = '0.5s';
	    	e.style.transitionTimingFunction = 'ease-in-out';
	    	e.classList.add("imageCover");
	   
	});
}

const resetPlayerCover = (e) => {
    e.style.transitionProperty = 'background-image';
    e.style.transitionDuration = '0.5s';
    e.style.transitionTimingFunction = 'ease-in-out';
	//e.style.backgroundImage = "url(img/landing-img.jpg)";
	e.style.backgroundImage = "url(img/purplecrowd-unsplash.jpg)";
}



/*	
	//stylePanelCards();
	if (track_index !== current_song_index) {
	    track_art.forEach(element => {
			element.classList.remove('rotate');
		});	
	
		setionPlayPauseBtn.forEach(panel => {
			panel.classList.remove('rotate');
			//panel.classList.add( 'fa', 'fa-play-circle', 'fa-5');
		});
		
		footerPanelPlaypauseBtn.forEach(panel => {
			panel.classList.remove('rotate');
			panel.classList.add( 'fa', 'fa-play-circle', 'fa-5');
	       // panel.innerHTML = '<i class="fa fa-play-circle fa-5x"></i>';
		});
	}
	panelPlaypause_btn.forEach(btn => {
		btn.classList.remove('rotate');
		btn.innerHTML = '<i class="track-art playerCover fa fa-play-circle fa-5x"></i>';
    	btn.style.transitionProperty = 'background-image';
     	btn.style.transitionDuration = '0.5s';
    	btn.style.transitionTimingFunction = 'ease-in-out';
	});
*/
/*            if (curr_track.paused) {
				btn.classList.remove('playerCover');
            } 
            
            else {
                btn.innerHTML = '<i class="rotate upper-track-art-widget playerCover fa fa-pause-circle fa-5x"></i>';
            }
        } else {
            btn.innerHTML = '<i class="track-art-widget playerCover fa fa-play-circle fa-5x"></i>';
*/