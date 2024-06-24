//File Upload Activites-- uploads.js
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
