<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>  
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
  	<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" />
 	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
	<link
	  href="https://fonts.googleapis.com/css?family=Raleway"
	  rel="stylesheet"
	/>
	<!-- Fonts Icon -->
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">  
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>

	<!-- YOUR own local CSS -->
	<link rel="stylesheet" href="/css/main.css"/>
    <link rel="stylesheet" href="/styles/styles.css"/>
    <link rel="stylesheet" href="/styles/privateStyles.css"/>
	<link rel="stylesheet" href="/styles/main_player.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<style>
	    .custom-file-input {
	        width:100%;
	    }
	
	    @media (min-width: 0px) and (max-width: 700px) {
	        .custom-file-input {
	        width:100%;
	        }
	    }
	</style>
    <script>
        function displayFileName(inputId, targetId, detailsDivId) {
            var input = document.getElementById(inputId);
            var target = document.getElementById(targetId);
            var detailsDiv = document.getElementById(detailsDivId);

            // Check if a file has been selected
            if (input.files.length > 0) {
                var fileName = input.files[0].name;
                target.innerText = fileName;

                // Show the details div
                detailsDiv.style.display = 'block';

                // Display image snippet (assuming the selected file is an image)
                if (fileName.match(/\.(jpg|jpeg|png|gif)$/)) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var imgSnippet = '<img src="' + e.target.result + '" alt="' + fileName + '" style="max-width: 100%; max-height: 100px;"/>';
                        document.getElementById('selectedImageSnippet').innerHTML = imgSnippet;
                    };
                    reader.readAsDataURL(input.files[0]);
                }
            }  
            
            // Display audio snippet and set audio player source
            if (fileName.match(/\.(mp3|ogg|wav)$/)) {
                var audioSnippet = '<p>Audio Preview:</p><audio controls><source src="' + URL.createObjectURL(input.files[0]) + '" type="audio/mpeg"></audio>';
                document.getElementById('selectedAudioSnippet').innerHTML = audioSnippet;

                // Set audio player source
                var audioPlayer = document.getElementById('audioPlayer');
                audioPlayer.src = URL.createObjectURL(input.files[0]);
            } else {
                // Clear audio snippet if not an audio file
                document.getElementById('selectedAudioSnippet').innerHTML = '';
            }
        }
    </script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Create Song</title>
</head>
<body class="container-fluid p-6" style="background:aliceblue;background:rgba(13.2, 0.300, 30.22, 0.9);font-family:cursive; color: azure">
		
   	<nav id="navbar" class="playerCover imageCover" 
	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;"
   	onmouseout="changePlayerCover(this)" >
	    <ul class="navbar-list navShowCase" style="" onmouseout="changePlayerCover(this)" >
	        <li class="purple-circle-container lead" 
				onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
    			onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
	            <a class="main-logo leadShowcase playerCover imageCover" href="/melodydreams/tracks" style="">
	            	<c:out value="Cloud Melody Dreams"/>
	            </a>
	        </li>
	        <li class="purple-circle-containe" style="">
			    <form action="/melodydreams/artists/${loggedInUser.id}" class="bluebtn" style="display:flex;flex-wrap:wrap;justify-content:space-between;align-items:center;text-align:center; padding:1px;background:rgba(1.33, 50.64, 70.60, 0.9);border-radius:7%;width:100%;">    
			        <input style="border-radius:7%;margin:5px" type="text" name="searchedArtistName" placeHolder="Enter Artist Name"/>
			        <input class="btn btn-outline-primary" type="submit" value="Search Artist" style="margin:5px;"/>
			    </form>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Artists Hub"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase playerCover imageCover bluebtn"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts  word-slicer" href="/melodydreams/tracks/${mostRecentSong.id}" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="Most Recent Track"/>
					    </a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts" href="/melodydreams/artists" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="View All Artists"/>
					   	</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts" href="/melodydreams/favoriteArtists" style="background: rgba(9, 6, 53, 0.466);">Favorite Artists</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts" href="/melodydreams/artists/${loggedInUser.id}" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="My Profile"/>
					   	</a>
					</li>
	            </ul>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Playlists Hub"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase playerCover imageCover bluebtn"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover panel-card"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="word-slicer mid-fonts" href="/melodydreams/usersPlaylists" style="">
					   		<c:out value="${loggedInUser.playlists.size()} Created Playlists!"/>
					   	</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts" href="/melodydreams/favoritePlaylists" style="">
				   			<c:out value="Favorite Playlists"/>
				   		</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts" href="/melodydreams/favoriteSongs" style="">
				   			<c:out value="Favorite Songs"/>
				   		</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/albumns" style="">
					   		<c:out value="Veiw Albums"/>
					   	</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/favoriteAlbums" style="">
					   		<c:out value="Favorite Albums"/>
					   	</a>
					</li>
	            </ul>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Album Panel"/> 
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn panel-card mid-fonts"  href="/melodydreams/albums" style="background: rgba(9, 6, 13, 0.466);">
	                			
	                    <i class ="word-slicer mid-fonts ">
	                    	<c:out value="${loggedInUser.albums.size()} Uploaded Albums Today"/>
	                    </i>
	                	</a>
			        </li> 
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts"  href="/melodydreams/userPlaylists/${loggedInUser.id}" style="background: rgba(9, 6, 13, 0.466);">
	                			<c:out value="My Albums"/>
	                	</a>
	                    <i class ="mid-fonts ">
	                    	<c:out value="${loggedInUser.albums.size()} New"/>
	                    </i>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts"  href="/melodydreams/albums" style="background: rgba(9, 6, 13, 0.466);">
	                		<c:out value="Favorite Albums"/>
	                	</a>
	                    <i class ="mid-fonts ">
	                    	<c:out value="${loggedInUser.favoriteAlbums.size()} New"/>
	                    </i>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts word-slicer"  href="/melodydreams/albums/newAlbum" style="background: rgba(9, 6, 13, 0.466);">
	                        <c:out value="Add New Album"/>
	                    </a>
	                </li>
	            </ul>
	        </li>

	        <li class="navShowcase">
	            <c:out value="Track Console Panel"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/artists/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Update Profile"/>
	                	</a>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts"  href="/melodydreams/newTrack" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Upload New Track"/>
	                	</a>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/userImageDairy/newUserImageDairy" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Upload New Image"/>
	                	</a>
	                </li>
	            </ul>
	        </li>

	        <li class="navShowcase">
	            <c:out value="Mellow Profile"/>
	            <i class="fas fa-angle-down"></i>
	            <ul class="navShowcase column-card"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts word-slicer" href="/melodydreams/artists/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
			                <c:out value="${loggedInUser.firstName} Profile Access"/>
			            </a>
			        </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/userAddresses/newUserAddress" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
			                <c:out value="Update Contact Details"/>
			            </a>
			        </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/logout" style="background:rgba(30.1, 0.2, 0.9, 0.6);">
			                <c:out value="Console Log Out"/>
			            </a>
			        </li>
	            </ul>
	        </li>  
	    </ul>
	</nav>
		<div class="mid-vert-margin"style="">
			<div class="">
				<h1  class="main-greeting-panel mid-vert-margin" style="">
					<a class="main-greeting word-slicer type-writer" href="/melodydreams/login" style="">
						<c:out value="Add A New Track To The Melody Dreams Console Today, ${currentDateTime} ${loggedInUser.firstName}!"/>
					</a>	
				</h1>
				<div id="selectedAudioName" style="margin-top: 10px;color:khaki;">
			        <div id="selectedAudioSnippet">		       
			       		<div style="font-size:16px;margin:5px 0;" id ="${song.id}">					       		
				       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
				       		<div class="panelPlayPauseBtn" id="${song.id}">
					      	<div class="lead panelPlaypauseBtn" style="" id="${song.id}" onclick="playpauseTrack(this)">
								    <div id="${song.id}" class="playpause-Track flex-panel btn btn-outline-warning panel-card" style="font-size:16px;padding:10px 0;width:100%;">
								        <p class="flex-panel sm-hor-margin" style="">
									        <c:out value="Play ${song.trackTitle}"/>
									        <span id="${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
									        <span id="${song.id}" class="playpauseTrack panelPlaypause_btn">
					        					<i class="fa fa-play-circle fa-5x"></i>
					        				</span> <!-- Span to hold the play/pause button -->
						 		   		 </p>
								     </div>
							 	</div>
						   </div>
				       	<!-- Upper Panel Display -->
					  	<div class="leadShowcase panel-info" style="cursor:pointer;">
						   <div class="current-time prev-track" id="${song.id}" style=""> 00.00</div>
						   <div class="panel-total-durations" id="${song.id}" onclick="playpauseTrack(this)" style="font-size:12px;">00.00</div>
						   <div class="panel-remaining-durations next-track" id="${song.id}" >00.00</div>
					  	</div>
									  
    			       <!-- Panel Slider Display -->
					  <!-- input id ="${song.id}" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(${JSON.stringify(song.id)})" style="width:100%;text-align:center; margin:5px 0;"-->
					  <input id ="${song.id}" type="range" min="1" max="100" value="0" class="id_seek_slider hidePanel" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
		  			  <div class="hidePanel">
			  			  <input id ="${song.id}" type="range" min="1" max="100" value="0" class="panel_seek_slider hidePanel" onchange="songSeekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
					  	  <input id ="sectionSlider_${song.id}" type="range" min="1" max="100" value="0" class="seek_slider hidePanel sectionSlider" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
					   </div>
					   <div class="leadShowcase slider_container btn btn-outline-success panel-info panel-car" style="">
					      	<c:out value="Volume Controls "/>
						    <div style="display:flex;">
							    <div id="total_duration_${song.id}" class="total-durations" style="margin:0 5px">00.00</div>
							 </div>
							 <div style="display:flex;">
							    <span>Time Remaining: </span>
							    <div id="${song.id}" class="remaining_durations" style="margin:0 5px">00.00</div>
							 </div>
					        <input type="range" min="1" max="100" value="100" class="volume_slider" onchange="setVolume(this)" style="text-align:center;cursor:pointer">									  
		       			</div>
					
			    	</div>
			    	<p id="audio-url">Audio URL: ${song.audioFileName}</p> 
			   	</div>
				<form:form class="profileShowcase mid-hor-margin deleteForm footer-delete-form " method="POST" action="/melodydreams/delete/${song.id }">
				<input type="hidden" name="_method" value="DELETE">
					<div class="over-flow profileShowcase imageCover">

							<div class="form-group bluebtn"style="">
							  	<div class="form-group flexCover" style="">
							    	<input type="submit" value="Delete ${song.trackTitle } Today, ${currentDateTime}" class="leadShowcase submit-track" id="submitBtn"/>
						 			<a href="/melodydreams/artists" class="btn btn-outline-warning leadShowcase back-btn" style="">GO BACK</a>
							    </div>
						   </div>
					 </div>
				</form:form>
			    <div id="0" class="flex-panel bright-flash imageCover panel-card sm-vert-margin" style="">
			        <div class="flex-panel sm-hor-margin" style="">
			        	<span id="" class="leadShowcase">
			        		<c:out value="Creativity Lives Within!"/>
				   		</span> 
				        <!--<span id="id-play-panel-0" class="playpause-icon"></span>  Span to hold the play/pause icon -->
			        	<span id="" class="upper-panel-play-btn">
			   				<i id=""  class="track-art-widget fa fa-play-circle fa-5x"></i>
				   		</span> 						      
					 </div>
			    </div>
		  </div>  
	</div>
</body>
</html>
<link rel="stylesheet" href="/styles/main_player.css"/>
<link rel="stylesheet" href="/styles/privateStyles.css"/>
<!--  -->
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/dynamicPlaylistWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>
