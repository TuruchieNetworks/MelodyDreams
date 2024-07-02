//Socials Js
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
                return response.json().then(err => { 
                    console.error('Error:', err);
                    showCustomNotification(`Error: ${err.message}`, 'error');
                    throw err;
                });
            } else {
                return response.text().then(err => { 
                    console.error('Error:', err);
                    showCustomNotification(`Error: ${err}`, 'error');
                    throw new Error(err);
                });
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
        showCustomNotification(successMessage);
        if (reloadPage) {
            location.reload();
        }
    })
    .catch(error => {
        console.error('Error:', error);
        showCustomNotification(`Error: ${error.message}`, 'error');
    });
};

// Add event listeners for tracking playlist form submissions
document.querySelectorAll('.playlist-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

// Add event listeners for tracking playlist form submissions
document.querySelectorAll('.new-playlist-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

// Add event listeners for tracking playlist form submissions
document.querySelectorAll('.new-section-playlist-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

// Add event listeners for tracking playlist form submissions
document.querySelectorAll('.new-footer-playlist-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

// Add event listeners for tracking playlist form submissions
document.querySelectorAll('.section-playlist-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

// Event listener for tracking playlist forms
document.querySelectorAll('.footer-playlist-form ').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song added to playlist successfully');
    });
});

// Event listener for tracking downloads
document.querySelectorAll('.metricsForm-download').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song downloaded successfully');
    });
});

// Event listener for documenting track position
document.querySelectorAll('.metricsForm-seekSlider').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Paused State Updated successfully');
    });
});

// Event listener for liking songs
document.querySelectorAll('.like-song-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song liked successfully');
    });
});

// Event listener for liking users
document.querySelectorAll('.like-user-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'User liked successfully');
    });
});

// Event listener for  following users
document.querySelectorAll('.follow-user-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'User followed successfully');
    });
});

// Event listener for unliking songs
document.querySelectorAll('.unlike-song-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'Song unliked successfully');
    });
});

// Event listener for unliking users
document.querySelectorAll('.unlike-user-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, 'User unliked successfully');
    });
});

// Event listener for unfollowing users
document.querySelectorAll('.unfollow-user-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, `User unfollowed successfully`);
    });
});

// Event listener for delete track form
document.querySelectorAll('.deleteForm').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        submitForm(form.id, `Track deleted successfully`, true);
    });
});

// Event listeners For Favorite Actions
document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.favorite-song-form').forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            submitForm(form.id, 'Favorite song added successfully!');
        });
    });

    document.querySelectorAll('.delete-favorite-song-form').forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            submitForm(form.id, 'Favorite song removed successfully!');
        });
    });

    document.querySelectorAll('.favorite-playlist-form').forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            submitForm(form.id, 'Favorite playlist added successfully!');
        });
    });

    document.querySelectorAll('.delete-favorite-playlist-form').forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            submitForm(form.id, 'Favorite playlist removed successfully!', true);
        });
    });

    document.querySelectorAll('.favorite-album-form').forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            submitForm(form.id, 'Favorite album added successfully!');
        });
    });

    document.querySelectorAll('.delete-favorite-album-form').forEach(form => {
        form.addEventListener('submit', function(event) {
            event.preventDefault();
            submitForm(form.id, 'Favorite album removed successfully!', true);
        });
    });
});

//Track Metrics
const handleSeekSliderFormSubmit = (matrixSongIdx) => {
   submitMetrics(`metricsForm-seek-slider-${matrixSongIdx}`, 'Track Position Has Been Successfully updated');
}

// Event listener for download button
const handleDownload = (songId) => {
    submitMetrics(`metricsForm-download-${songId}`, `Your Download Has Started For Song Id: ${songId}`);
}
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
        // Fetch and update the metrics data
        fetchUpdatedMetrics();
    })
    .catch(error => {
        console.error('Error:', error);
        showCustomNotification(`Error: ${error.message}`, 'error');
    });
};

// Function To Update Metrics
const fetchUpdatedMetrics = () => {
    fetch('/metrics/data')
        .then(response => response.json())
        .then(data => {
            // Update the UI with the fetched data
            updateMetricsUI(data);
        })
        .catch(error => {
            console.error('Error fetching updated metrics:', error);
        });
};

const updateMetricsUI = (data) => {
    // Assuming you have an element to display the play count size
    const playCountSizeElement = document.getElementById('playCountSize');
    if (playCountSizeElement) {
        playCountSizeElement.textContent = `Play count size: ${data.playCountSize}`;
    }

    // Update other parts of the UI as needed
    // Example: Updating a list of songs with new metrics
    // const songsElement = document.getElementById('songs');
    // songsElement.innerHTML = ''; // Clear existing content
    // data.songs.forEach(song => {
    //     const songElement = document.createElement('div');
    //     songElement.textContent = `${song.trackTitle} Played ${song.playCountSize}`;
    //     songsElement.appendChild(songElement);
    // });
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

///Favorite song Form
function updateFavoriteForm(songId, userId, action, favoriteSongId) {
        const favoriteForm = document.querySelector(`#favoriteSongForm-${songId}`);
        const unfavoriteForm = document.querySelector(`#unfavoriteSongForm-${songId}`);

        if (action === 'create') {
            favoriteForm.style.display = 'none';
            unfavoriteForm.style.display = 'block';
            unfavoriteForm.action = `/melodydreams/deleteFavoriteSong/${favoriteSongId}`;
        } else if (action === 'delete') {
            unfavoriteForm.style.display = 'none';
            favoriteForm.style.display = 'block';
            favoriteForm.action = `/melodydreams/process/createNewFavoriteSong/${songId}`;
        }
    }
