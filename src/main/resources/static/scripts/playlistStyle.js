// Function to style panel display
export const stylePanelDisplay = () => {
    const panelDynamicDisplay = document.createElement('div');
    panelDynamicDisplay.classList.add('lead', 'btn', 'btn-outline-danger', 'panel-card');
    panelDynamicDisplay.style.padding = '5px';
    panelDynamicDisplay.style.margin = '5px';
    panelDynamicDisplay.style.borderRadius = '5%';
    panelDynamicDisplay.style.width = '100%';
    panelDynamicDisplay.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';
    panelDynamicDisplay.style.backgroundImage = 'url(../img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)';
    panelDynamicDisplay.style.backgroundRepeat = 'no-repeat';
    panelDynamicDisplay.style.backgroundPosition = 'center';
    panelDynamicDisplay.style.backgroundSize = 'cover';
    return panelDynamicDisplay;
}

// Function to style panel pad
export const stylePanelPad = () => {
    const panelPad = document.createElement('div');
    panelPad.classList.add('lead');
    panelPad.style.padding = '5px';
    panelPad.style.borderRadius = '5%';
    panelPad.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';
    panelPad.style.backgroundImage = 'url(../img/landing-img.jpg)';
    panelPad.style.backgroundRepeat = 'no-repeat';
    panelPad.style.backgroundPosition = 'center';
    panelPad.style.backgroundSize = 'cover';
    return panelPad;
}

// 
export const stylePanelCards = () => {
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

export const removeStylePanelCards = () => {
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
// Function to update frequency canvas
export const updateFrequencyCanvas = () => {
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

export const displayFileName = (inputId, targetId, detailsDivId) => {
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

export const updateMusicListData = (newSong) => {
    const musicListDataElement = document.getElementById('musicListData');
    let currentMusicList = JSON.parse(musicListDataElement.getAttribute('data-musiclist'));

    currentMusicList.push(newSong);

    const updatedMusicListJson = JSON.stringify(currentMusicList);
    musicListDataElement.setAttribute('data-musiclist', updatedMusicListJson);
    musicListDataElement.innerText = updatedMusicListJson;
}
/*
export function updateMusicListData(newSong) {
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
*/

// Function to set download link
export const setDownloadLink = () => {
    const downloadLink = document.createElement('a');
    downloadLink.style.display = 'flex';
    downloadLink.style.justifyContent = 'space-between';
    downloadLink.style.flexWrap = 'wrap';
    downloadLink.style.margin = '5px 0';
    downloadLink.style.backgroundImage = 'url(../img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)';
    downloadLink.style.backgroundRepeat = 'no-repeat';
    downloadLink.style.backgroundPosition = 'center';
    downloadLink.style.backgroundSize = 'cover';
    return downloadLink;
}
