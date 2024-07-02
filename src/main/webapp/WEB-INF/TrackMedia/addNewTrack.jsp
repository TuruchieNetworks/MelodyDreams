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
<body class="panel-card" style="background:aliceblue;background:rgba(13.2, 0.300, 30.22, 0.9);font-family:cursive; color: azure">
		
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
		<div class="imageCover mid-vert-margin container-fluid p-8"style="">
			<div class="">
				<h1  class="main-greeting-panel mid-vert-margin" style="">
					<a class="main-greeting word-slicer type-writer" href="/melodydreams/login" style="">
						<c:out value="Add A New Track To The Melody Dreams Console Today, ${currentDateTime} ${loggedInUser.firstName}!"/>
					</a>	
				</h1>
				<form:form class="profileShowcase mid-hor-margin track-upload-form" method="post" action="/melodydreams/process/createNewTrack" enctype="multipart/form-data" modelAttribute="song">
					<div class="over-flow imageCover profileShowcase">
						<input type="hidden" name="user.id" value="${loggedInUser.id}" class="track-creation-input"/>
						<!--form:errors path="*" cssClass="text-danger"/!-->
					    <div class="form-group">
					        <label style="text-align:center;width:100%;" class="bright-flash">
					        	<span>
					        		<c:out value="Track Title"/>
					        	</span>
					        </label>
					        <form:input path="trackTitle" class="form-control track-creation-input round-border-radius" placeholder="Please enter track title!" style=""/>
					        <form:errors path="trackTitle" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
					        <c:if test="${not empty trackTitleError}">
					        	<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
					        		<c:out value ="${trackTitleError}"/>
					        	</p>
					    	</c:if>
					    </div> 
	
					    <div class="form-group">
					        <label style="text-align:center;width:100%;" class="bright-flash">
					        	<span>
					        		<c:out value="Track Artist"/>
					        	</span>
					        </label>					        		
					        <form:input path="trackArtist" class="form-control track-creation-input round-border-radius" placeholder="Please enter track artist"/>
					        <form:errors path="trackArtist" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
					        <c:if test="${not empty trackArtistError}">
					        	<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
					        		<c:out value ="${trackArtistError}"/>
					        	</p>
					    	</c:if>
					    </div>
	
					    <div class="form-group">
					        <label style="" class="bright-flash">
					        	<span>
					        		<c:out value="Album"/>
					        	</span>
					        </label>					        
					        <form:input path="album" class="form-control track-creation-input round-border-radius" placeholder="Please enter track album"/>
					    </div>
	
						<div class="form-group">
						    <label style="" class="bright-flash">
					        	<span>
					        		<c:out value="Genre"/>
					        	</span>
					        </label>		
					        <form:errors path="genre" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
					        <c:if test="${not empty genreError}">
					        	<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
					        		<c:out value ="${genreError}"/>
					        	</p>
					    	</c:if>
						    <div class="form-group">
						        <form:select path="genre" class="form-control track-creation-select round-border-radius" style="cursor:pointer">
						    		<option value="" class="text-primary">Genre</option>>
						            <option value="Afro-Hiphop" class="type-writer text-danger">Afro-Hiphop</option>
						            <option value="Classical" class="text-success">Classical</option>
						            <option value="Country" class="text-secondary">Country</option>
						            <option value="Chill-Step" class="text-warning">Chill-Step</option>
						            <option value="Dance" class="text-primary">Dance</option>
						            <option value="Dance-Afro" class="type-writer text-danger">Dance-Afro</option>
						            <option value="Dance-N-Bass" class="type-writer text-danger">Dance-N-Bass</option>
						            <option value="Dance-Hall" class="type-writer text-danger">Dance-Hall</option>
						            <option value="Dub-step" class="text-warning">Dub-step</option>
						            <option value="Dream-Pop" class="type-writer text-danger">Dream-Pop</option>
						            <option value="EDM" class="type-writer text-danger">EDM</option>
						            <option value="Electric" class="text-primary">Electric</option>
						            <option value="Electronic" class="text-primary">Electronic</option>
						            <option value="Gospel" class="text-primary">Gospel</option>
						            <option value="Hard Hip-hop" class="text-warning">Hard Hip-hop</option>
						            <option value="Hip-hop" class="text-warning">Hip-hop</option>
						            <option value="House" class="text-primary">House</option>
						            <option value="Hymns" class="text-primary">Hymns</option>
						            <option value="Instrumental" class="text-warning">Instrumental</option>
						            <option value="Pop" class="text-warning">Pop</option>
						            <option value="Metal" class="text-warning">Metal</option>
						            <option value="Orchestral" class="text-warning">Orchestral</option>
						            <option value="Jazz" class="text-success">Jazz</option>
						            <option value="Raggae" class="text-secondary">Raggae</option>
						            <option value="RnB" class="text-secondary">RnB</option>
						            <option value="Soul" class="text-secondary">Soul</option>
						            <option value="Techno" class="text-primary">Techno</option>
						            <option value="Trap" class="text-primary">Trap</option>
						            <option value="Trip-Hop" class="text-primary">Trip-Hop</option>
						            <option value="Uplifting-Trance" class="text-warning">Uplifting Trance</option>
						            <option value="Uplifting-Trap" class="text-warning">Uplifting Trap</option>
						        </form:select>
						    </div>
						</div>           
						<div class="">
			                <label style="" class="bright-colors bright-flash">
					        	<span>
					        		<c:out value="Track Descriptions"/>
					        	</span>
					        </label>
			                <form:errors path="description" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
			                <form:textarea path="description" class="form-control track-creation-text-area round-border-radius" rows="5" maxlength="500" placeholder="Please Describe Your Track"></form:textarea>
					        <c:if test="${not empty descriptionError}">
					        	<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
					        		<c:out value ="${descriptionError}"/>
					        	</p>
					    	</c:if>
			            </div>
					</div>
		 
					<c:if test="${not empty trackImageDataError or not empty audioDataError}">
					    <div class="bg-danger flexCover leadShowcase" style="">
					        <div class="  flexCover leadShowcase" style="color:rgba(323, 332, 320, 0.9); border-radius:5%; color:khaki;">
					            <c:if test="${not empty trackImageDataError}">
					                <p>${trackImageDataError}</p>
					            </c:if>
					        </div>
					        <div class="  flexCover leadShowcase" style="color:rgba(323, 332, 320, 0.9); color:khaki; border-radius:5%;">
					            <c:if test="${not empty audioDataError}">
					                <p>${audioDataError}</p>
					            </c:if>
					        </div>
					    </div>
					</c:if>

					<div class="flexCover mid-vert-margin" style="">						                  
						<div class="image-input-panel" style="">
						    <label for="imageData" style="">Enter Track Art Cover</label>
						    <div class="custom-file " style="">
						        <form:input type="file" class="custom-file-input track-creation-input" path="imageData" value="${song.imageData}" accept="image/*" style="cursor:pointer;" onchange="displayFileName('imageData', 'selectedImageName', 'trackDetailsDiv')"/>
						        <label class="custom-file-label" for="imageData" style="cursor:pointer;">Select Image</label>
						    </div>
						</div>

						<div class="audio-input-panel" style="">
						    <label for="audioData">Enter Track Audio</label>
						    <div class="custom-file" style="width:100%;">
						        <form:input type="file" class="custom-file-input track-creation-input" path="audioData" value="${song.audioData}" accept="audio/*" style="cursor: pointer;" onchange="displayFileName('audioData', 'selectedAudioName', 'trackDetailsDiv')"/>
						        <label class="custom-file-label" for="audioData" style="cursor:pointer;">Select Audio File</label>
						    </div>
						</div>
					</div>

					<!-- Display file URLs and upload process -->
					<div id="trackDetailsDiv" class="form-group imageCover" style="">

					    <h1 style="width:100%;color:aliceblue;font-size:18px;border-bottom: 2px dashed aqua;padding-bottom:5px">Track Details</h1>
						<div class="form-group" id="trackDetailsDiv" style="background:rgba(1.12, 11, 11, 0.9);border-radius:5%;padding:10px;margin:10px 0;">
						    <div class="form-group" style=";display:flex;justify-content:space-between; align-items:center;border-radius:5%;padding:10px;margin:10px 0;">
						        <div id="selectedImageName" style="color:aqua;">
						        </div>  
						        <div id="selectedAudioName" style="color:khaki;">
						        </div>
						    </div>	
						</div> 
					    <div class="form-group" style="display:flex;justify-content:space-between; align-items:center;border-radius:5%;padding:10px;margin:10px 0;">
						    <div id="selectedImageName" style="margin-top: 10px;color:aqua;">
						        <div id="selectedImageSnippet" style="margin-top:3px;"></div>
						        <p>Image URL: ${trackImageFileName}</p>
						        <form:input type="hidden" path="trackImageFileName" value="${song.trackImageFileName}" />
						    </div>
						    <div id="selectedAudioName" style="margin-top: 10px;color:khaki;">
						        <div id="selectedAudioSnippet" style="margin-top: 3px;"></div>
						        <p>Audio URL: ${audioFileName}</p> 
						        <form:input type="hidden" path="audioFileName" value="${song.audioFileName}" />
						    </div>
						</div>
					</div> 

					<div class="form-group bluebtn"style="">
					  	<div class="form-group flexCover" style="">
					    	<input type="submit" value="Add New Track Today, ${currentDateTime}" class="leadShowcase submit-track" id="submitBtn"/>
				 			<a href="/melodydreams/artists" class="btn btn-outline-warning leadShowcase back-btn" style="">GO BACK</a>
					    </div>
					</div>
			    </div>
			</form:form>
		    <div id="" class="flex-panel bright-flash imageCover panel-card mid-vert-margin" style="">
		        <div class="flex-panel sm-hor-margin" style="cursor:pointer">
		        	<span id="" class="leadShowcase">
		        		<c:out value="Creativity Lives Within!"/>
			   		</span> 
			        <!--<span id="id-play-panel-0" class="playpause-icon"></span>  Span to hold the play/pause icon -->
		        	<span id="" class="rotate track-art upper-panel-play-btn">
		   				<i id=""  class="rotate fa fa-pause-circle fa-5x"></i>
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
