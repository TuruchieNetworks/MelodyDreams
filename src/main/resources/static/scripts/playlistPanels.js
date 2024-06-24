// Function to style panel display
export function stylePanelDisplay() {
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
export function stylePanelPad() {
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

// Function to set download link
export function setDownloadLink() {
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

// Function to set details display
export function setDetailsDisplay(downloadLink, allFormPanel) {
    const downloadDiv = document.createElement('div');
    downloadDiv.classList.add('bluebtn');
    downloadDiv.style.margin = '5px 0';
    downloadDiv.style.display = 'flex';
    downloadDiv.style.justifyContent = 'space-between';
    downloadDiv.style.flexWrap = 'wrap';
    downloadDiv.appendChild(downloadLink);
    downloadDiv.appendChild(allFormPanel.viewForm);
    downloadDiv.style.backgroundImage = 'url(../img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)';
    downloadDiv.style.backgroundRepeat = 'no-repeat';
    downloadDiv.style.backgroundPosition = 'center';
    downloadDiv.style.backgroundSize = 'cover';
    return downloadDiv;
}

// Function to create track <a> tag
export function setTrackATag() {
    const trackA = document.createElement('a');
    trackA.classList.add('profileShowcas', 'btn', 'btn-outline-danger');
    trackA.style.color = random_bg_color();
    trackA.style.width = '100%';
    trackA.style.borderRadius = '5%';
    return trackA;
}

// Function to create track <p> tag
export function setTrackPTag() {
    const trackP = document.createElement('p');
    trackP.classList.add('profileShowcase', 'btn', 'btn-outline-danger', 'panel-card');
    trackP.style.display = 'flex';
    trackP.style.justifyContent = 'space-between';
    trackP.style.flexWrap = 'wrap';
    trackP.style.alignItems = 'center';
    return trackP;
}

// Function to create delete panel
export function setDeletPanel() {
    const deleteForm = document.createElement('form');
    deleteForm.classList.add('deleteForm');
    deleteForm.method = 'post';
    deleteForm.style.padding = '10px';
    deleteForm.style.borderRadius = '5%';
    deleteForm.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';

    const deleteInput = document.createElement('input');
    deleteInput.type = 'hidden';
    deleteInput.name = '_method';
    deleteInput.value = 'DELETE';
    deleteInput.classList.add('btn', 'btn-outline-danger', 'panel-card');

    const deleteButton = document.createElement('input');
    deleteButton.type = 'submit';
    deleteButton.style.width = '100%';
    deleteButton.style.padding = '7px';
    deleteButton.classList.add('btn', 'btn-outline-danger', 'panel-card');

    let deleteArr = { inp: deleteInput, btn: deleteButton, delForm: deleteForm };
    return deleteArr;
}

// Function to create view form panels
export function viewFormPanels(){
    const viewForm = document.createElement('form');
        viewForm.classList.add('deleteForm');
        viewForm.method = 'get';
        viewForm.style.padding = '5px 0';
        viewForm.style.borderRadius = '5%';
        viewForm.style.width = '100%';
        //viewForm.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';

        const viewInput = document.createElement('input');
        viewInput.type = 'hidden';
        viewInput.name = '_method';
        viewInput.value = 'GET';
        viewInput.classList.add('btn', 'btn-outline-danger', 'panel-card');

        const viewButton = document.createElement('input');
        viewButton.classList.add('btn', 'btn-outline-danger', 'panel-card');
        viewButton.type = 'submit';
        viewButton.style.width = '100%';
        viewButton.style.padding = '7px';
        viewButton.style.width = '100%';

        viewForm.appendChild(viewInput);
        viewForm.appendChild(viewButton);
        let formArr = {inp:viewInput,btn:viewButton, viewForm:viewForm};
        return formArr;
   }

export function viewTrackDetailsPanels() {
    const viewForm = document.createElement('form');
        viewForm.classList.add('trackForm');
        viewForm.method = 'get';
        viewForm.style.padding = '5px 0';
        viewForm.style.borderRadius = '5%';
        viewForm.style.width = '100%';
        //viewForm.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';

        const viewInput = document.createElement('input');
        viewInput.type = 'hidden';
        viewInput.name = '_method';
        viewInput.value = 'GET';
        viewInput.classList.add('btn', 'btn-outline-danger', 'panel-card');

        const viewButton = document.createElement('input');
        viewButton.classList.add('btn', 'btn-outline-danger', 'panel-card');
        viewButton.type = 'submit';
        viewButton.style.width = '100%';
        viewButton.style.padding = '7px';
        viewButton.style.width = '100%';

        viewForm.appendChild(viewInput);
        viewForm.appendChild(viewButton);
        let formArr = {inp:viewInput,btn:viewButton, viewForm:viewForm};
        return formArr;
   }

export function viewAllFormPanels() {
   const viewAllForm = document.createElement('form');
        viewAllForm.classList.add('deleteForm');
        viewAllForm.method = 'get';
        viewAllForm.style.padding = '10px 0';
        viewAllForm.style.borderRadius = '5%';
        viewAllForm.style.width = '100%';
        //viewAllForm.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';

        const viewAllInput = document.createElement('input');
        viewAllInput.type = 'hidden';
        viewAllInput.name = '_method';
        viewAllInput.value = 'GET';

        const viewAllButton = document.createElement('input');
        viewAllButton.classList.add('bluebtn','btn', 'btn-outline-danger', 'panel-card');
        viewAllButton.type = 'submit';
        viewAllButton.style.width = '100%';
        viewAllButton.style.padding = '7px';

        let allFormArr = {inp:viewAllInput,btn:viewAllButton, viewForm:viewAllForm};
        return allFormArr;
}

// Function to display the track list
export function displayTrackList() {
    const trackListElement = document.createElement('div');
    //const trackListElement = document.getElementById('trackList');
    trackListElement.innerHTML = ''; // Clear previous list

    // Create a list of track indices and titles
    for (let i = 0; i < music_list.length; i++) {
        const trackItem = document.createElement('div');
        trackListElement.style.padding = '10px';
        trackListElement.style.width = '100%';
        trackListElement.classList.add = ('profileShowcase');
        
        // Display ;
        let styledPanelPad = stylePanelPad();
        let styledPanelDisplay = stylePanelDisplay()

        // Add a download link for the song
        let downloadLink = setDownloadLink();
        downloadLink.href = music_list[i].music;
        downloadLink.download =  music_list[i].music;
        downloadLink.textContent = 'Download: ' + music_list[i].name;

 		let trackDetailForm = viewTrackDetailsPanels();
        trackDetailForm.btn.value = 'Add To' + ' '+ music_list[i].artist + ' ' + 'Playlist';
        trackDetailForm.viewForm.action = '/melodydreams/userPlaylists/createUserPlaylist/' + music_list[i].trackId;

 		let trackATag = setTrackATag();
        trackATag.href = music_list[i].music;
    	trackATag.download = music_list[i].music;
        trackATag.textContent = 'Download' +' ' + music_list[i].name;
 
 		let trackPTag = setTrackPTag();
 		
        let displayDiv = setDetailsDisplay(trackATag, trackDetailForm)
        //trackP.textContent = music_list[i].album;
        trackItem.style.padding = '15px';
        trackItem.style.width = '100%';
        trackItem.textContent = 'Play Track ' + (i + 1) + ': ' + music_list[i].name + " " + "By " + music_list[i].artist;
        trackItem.classList.add('btn', 'btn-outline-primary');
        trackItem.style.background = random_bg_color();

   		let flexDiv = document.createElement('div');
   		flexDiv.classList.add = ('lead','btn', 'btn-outline-primary');
   		flexDiv.style.display = 'flex';
   		flexDiv.style.flexWrap = 'wrap';
   		flexDiv.style.justifyContent = 'space-between';

        // Event listener to load and play the track
        trackItem.addEventListener('click', function() {
            loadTrack(); // Load the selected track
            track_index = i; // Set the track index to the clicked track index
            playTrack(); // Play the loaded track
        });

        // Conditionally display the delete form based on user ID
        if (music_list[i].trackUserId === loggedId) {
			let deletePack = setDeletPanel()
            deletePack.delForm.action = '/melodydreams/songs/deleteTrack/' + music_list[i].trackId;

            deletePack.btn.value = 'Delete Your ' + music_list[i].name + ' Track';
            deletePack.delForm.appendChild(deletePack.inp);
            deletePack.delForm.appendChild(deletePack.btn);

            trackPTag.appendChild(deletePack.delForm);
        } else {
			let formPanel = viewFormPanels();
            formPanel.viewForm.action = '/melodydreams/tracks/' + music_list[i].trackId;

            //viewForm.style.background = 'rgba(1.33, 10.64, 0.60, 0.9)';
            formPanel.btn.value = music_list[i].name + ' Track';

            formPanel.viewForm.appendChild(formPanel.inp);
            formPanel.viewForm.appendChild(formPanel.btn);
            flexDiv.appendChild(formPanel.viewForm);
        }
        // Veiw All Form
        let allFormPanel = viewAllFormPanels();
            allFormPanel.viewForm.action = '/melodydreams/artists/' + music_list[i].trackUserId;

            allFormPanel.btn.value = 'View All Tracks From ' + music_list[i].artist;




            allFormPanel.viewForm.appendChild(allFormPanel.inp);
            allFormPanel.viewForm.appendChild(allFormPanel.btn);

        // Append elements to the track item
        trackListElement.appendChild(trackItem);
        trackListElement.appendChild(trackPTag);
    	flexDiv.appendChild(trackDetailForm.viewForm);
        //trackPTag.appendChild(trackATag);
        trackListElement.appendChild(displayDiv);
        flexDiv.appendChild(allFormPanel.viewForm);
        //panelDynamicDisplay.appendChild(viewForm);
        //panelDynamicDisplay.appendChild(viewAllFo
        styledPanelPad.appendChild(flexDiv);
        styledPanelPad.appendChild(trackPTag);
        styledPanelDisplay.appendChild(styledPanelPad);
        //styledPanelDisplay.classList.add('bluebtn');
        trackListElement.appendChild(styledPanelDisplay);
    }
}