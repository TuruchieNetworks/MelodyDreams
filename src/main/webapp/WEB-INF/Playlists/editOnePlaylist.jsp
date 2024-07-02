<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
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
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">  
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
	<!-- YOUR own local CSS -->
    <link rel="stylesheet" href="/styles/style.css"/>
	<link rel="stylesheet" href="/styles/main_player.css"/>
	<link rel="stylesheet" href="/styles/privateStyles.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Melody Dreams!</title>
</head>
<body class="container-fluid p-8" style="background:rgba(110.2, 100.4, 336.6, 0.9);font-family:cursive;">
   	<nav id="navbar" class="playerCover imageCover navbar" 
	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;"
   	onmouseout="changePlayerCover(this)" >
	    <ul class="navbar-list" style="">
	        <li class="purple-circle-container lead imageCover" 
				onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
    			onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
	            <a class="bluebtn leadShowcase main-logo playerCover imageCover" href="/melodydreams/tracks" style=""
				onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'">
	            	<c:out value="Cloud Melody Dreams"/>
	            </a>
	        </li>
	        <li class="panel-card point-border-radius sm-padding" style="">
			    <form class="playerCover imageCover point-border-radius" action="/melodydreams/artists/${loggedInUser.id}"style="">    
			        <input class="purple-circle-containe leadShowcase imageCover point-border-radius"  style="" type="text" name="searchedArtistName" placeHolder="Enter Artist Name"/>
			        <input class="leadShowcase panel-card playerCover imageCover point-border-radius" type="submit" value="Search Artist" style=""/>
			    </form>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Artists Hub"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase playerCover imageCover bluebtn"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase imageCover"
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
	
	<!--Main Title Display -->
	<div  class="main-greeting-panel imageCover playerCover word-slicer type-writer point-border-radius" style="">
		<a class="main-greeting leadShowcase" href="/melodydreams/login" style="">
			<c:out value="Make Changes On Previously Uploaded Tracks!"/>
		</a>
	</div>	

	<div class="profileShowcase imageCover mid-vert-margin bright-cover">						      	

				<form:form class="profileShowcase imageCover full-width lrg-hor-margin" method="patch" action="/melodydreams/process/editTrack/${song.id}" enctype="multipart/form-data" modelAttribute="song">
					<input type="hidden" name="user.id" value="${loggedInUser.id}"/>
					<div class="form-group">
			            <form:errors path="*" cssClass="error" element="div"/>
			        </div>
				    <div class="form-group">
				        <label style="text-align:center;width:100%;">Track Title</label>
				        <form:input path="trackTitle" class="form-control" placeholder="Please enter track title!"/>
				        <form:errors path="trackTitle" class="text-danger"/>
				    </div>

				    <div class="form-group">
				        <label style="text-align:center;width:100%;">Track Artist</label>
				        <form:input path="trackArtist" class="form-control" placeholder="Please enter track artist"/>
				        <form:errors path="trackArtist" class="text-danger"/>
				    </div>

				    <div class="form-group">
				        <label style="text-align:center;width:100%;">Album</label>
				        <form:input path="album" class="form-control" placeholder="Please enter track album"/>
				        <form:errors path="album" class="text-danger"/>
				    </div>

					<div class="form-group">
					    <label style="padding:5px 0">Genre</label>
				        <form:errors path="genre" class="text-danger"/>
					    <div class="form-group">
					        <form:select path="genre" class="form-control" style="cursor:pointer">
					    		<option value="${song.genre}" class="text-primary">${song.genre}</option>
					            <option value="Afro-Hiphop" class="text-danger">Afro-Hiphop</option>
					            <option value="Classical" class="text-success">Classical</option>
					            <option value="Country" class="text-secondary">Country</option>
					            <option value="Chill-Step" class="text-warning">Chill-Step</option>
					            <option value="Dance" class="text-primary">Dance</option>
					            <option value="Dance-Afro" class="text-danger">Dance-Afro</option>
					            <option value="Dance-N-Bass" class="text-danger">Dance-N-Bass</option>
					            <option value="Dance-Hall" class="text-danger">Dance-Hall</option>
					            <option value="Dub-step" class="text-warning">Dub-step</option>
					            <option value="Dream-Pop" class="text-danger">Dream-Pop</option>
					            <option value="EDM" class="text-danger">EDM</option>
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

		            <div class="form-group">
		                <label style="padding:10px 0;width:100%;">Track Description</label>
		                <form:errors path="description" class="text-danger"/>
		                <form:textarea path="description" class="form-control" rows="5" maxlength="500" placeholder="Please Describe Your Track"></form:textarea>
		            </div>
					<c:if test="${not empty trackImageDataError or not empty audioDataError}">
					    <div class="bg-danger" style="display:flex; justify-content:space-between; padding:5px 10px; border-radius:5%;">
					        <div class="" style="color:rgba(323, 332, 320, 0.9); border-radius:5%; color:khaki;">
					            <c:if test="${not empty trackImageDataError}">
					                <p>${trackImageDataError}</p>
					            </c:if>
					        </div>
					        <div class="" style="color:rgba(323, 332, 320, 0.9); color:khaki; border-radius:5%;">
					            <c:if test="${not empty audioDataError}">
					                <p>${audioDataError}</p>
					            </c:if>
					        </div>
					    </div>
					</c:if>
						        
	
				    <div class="flexCover" style="">						                  
					    <div class="form-group sm-hor-margin" style="background:rgba(12, 232, 312, 0.9);display:flex;justify-content:space-between;align-items:center;border-radius:5%;padding:10px 15px;margin-right: 10px;cursor:pointer;">
					        <label for="imageData" style="margin-right:5px;">Track Art Cover</label>
					        <div class="custom-file" style="width:100%;">
					            <form:input type="file" class="custom-file-input" path="imageData" accept="image/*" style="cursor:pointer;" onchange="displayFileName('imageData', 'selectedImageName', 'trackDetailsDiv')"/>
					            <label class="custom-file-label" for="imageData" style="cursor:pointer;">Select Image</label>
					        </div>
					    </div>

					    <!-- Hidden file name inputs -->
					    <form:input path="audioFileName" value="${song.audioFileName}" type="hidden"/> 
					    <form:input path="audioDataUrl" value="${song.audioDataUrl}" type="hidden"/> 
					    <form:input path="trackImageDataUrl" value="${song.trackImageDataUrl}" type="hidden"/>
					    <form:input path="trackImageFileName" value="${song.trackImageFileName}" type="hidden"/>
					
					    <div class="form-group" style="background:rgba(123, 232, 12, 0.9);display:flex;justify-content:space-between;align-items:center;border-radius:5%; padding:10px 42px;margin:10px 0;cursor:pointer;">
					        <label for="audioData">Audio File</label>
					        <div class="custom-file" style="width:100%;">
					            <form:input type="file" class="custom-file-input" path="audioData" accept="audio/*" style="cursor: pointer;" onchange="displayFileName('audioData', 'selectedAudioName', 'trackDetailsDiv')"/>
					            <label class="custom-file-label" for="audioData" style="cursor:pointer;">Select Audio File</label>
					        </div>
					    </div>
					</div>


					<!-- Display file URLs and upload process -->
					<div id="trackDetailsDiv" class="form-group imageCover" style="background:rgba(1.12, 11, 11, 0.9);border-radius:5%;padding:10px;margin:10px 0;">
					    <h1 style="width:100%;color:aliceblue;font-size:18px;border-bottom: 2px dashed aqua;padding-bottom:5px">
					    	<c:out value="Track Details"/>
					    </h1>
						<div class="form-group" id="trackDetailsDiv" style="background:rgba(1.12, 11, 11, 0.9);border-radius:5%;padding:10px;margin:10px 0;">
						    <div class="form-group" style=";display:flex;justify-content:space-between; align-items:center;border-radius:5%;padding:10px;margin:10px 0;">
						        <div id="selectedImageName" style="color:aqua;">
						        </div>
						        <div id="selectedAudioName" style="color:khaki;">
						        </div>
						    </div>	
						</div> 
					    <div class="form-group flexCover">
						    <div id="selectedImageName" style="margin-top: 10px;color:aqua;">
						        <div id="selectedImageSnippet" style="margin-top:3px;">
						        	<img class="imageCard" src="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}"/>
						        </div>
						        <p id="image-url">Image URL: ${song.trackImageFileName}</p>
						    </div>
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
					  </div>
				</div>

				<div class="form-group"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
				  	<div class="form-group" style=";width:100%;">
				    	<input type="submit" value="Replace Uploaded Track" class="btn btn-primary" style="margin:10px 0;width:100%;padding:10px 0;"/>
			 			<h1 style="width:100%;">
			 				<a href="/melodydreams/artists" class="btn btn-warning" style="width:100%;display:block;padding:10px 0">GO BACK</a>
			 			</h1>
				    </div>
				</div>
				</form:form>	
				
				<div  id="0" class="lead flexCover track-panel id-play-panel panelPlayPauseBtn" style="">
	    <div id="0" class="flex-panel bright-colors imageCover panel-card full-width" style="">
	        <div class="flex-panel sm-hor-margin" style="">
		        <c:out value="Share Your Music With Fans All Over The World!"/>
		        <span id="id-play-panel-0" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
	        	<span id="" class="upper-panel-play-btn">
	    			<i id=""  class="track-art-widget fa fa-play-circle fa-5x"></i>
	    		</span> 
				<a href="/melodydreams/artists/${loggedInUser.id}" class="word-slicer typewriter imageCover" style="">
					<h1 class="leadShowcase style="">
						<c:out value="Make Changes On Your Previously Uploaded Track ${loggedInUser.firstName}!"/>
					</h1>
				</a>						      
			 </div>
        </div>
 	</div>	
			    <!-- Image Display -->
				<img id="trackImage" src="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" alt="Track Art" style="border-radius:50%; width: 200px; height: 200px; display: none;">
				
		        <!-- Display the audio player -->
				<audio class="audioPlayer" id="audioPlayer">
				    <source src="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" type="audio/mpeg">
				    <c:out value="Your browser does not support the audio element."/>
				 </audio>
				<input type="hidden" id ="track-${song.id}" class="trackID" value="${song.id}" />
				<input type="hidden" class="trackTitle" value="${song.trackTitle}" />
				<input type="hidden" class="trackArtist" value="${song.trackArtist}" />
				<input type="hidden" class="trackImageSrc" value="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" />
				<input type="hidden" class="audioSrc" value="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" />
				
				<p id="musicListData" data-musiclist='[
				    <c:forEach items="${singleSongList}" var="song" varStatus="loop">
				        {
				            "music": "${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}",
				            "img": "${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}",
				            "artist": "${song.trackArtist}",
				            "name": "${song.trackTitle}"
				        }<c:if test="${!loop.last}">,</c:if>
				    </c:forEach>
				]' style="display: none;">
				    [
				    <c:forEach items="${singleSongList}" var="song" varStatus="loop">
				        {
				            "music": "${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}",
				            "img": "${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}",
				            "artist": "${song.trackArtist}",
				            "name": "${song.trackTitle}"
				        }<c:if test="${!loop.last}">,</c:if>
				    </c:forEach>
				    ]
				</p>


		<p id="allTrackListData" data-musiclist="${allTrackList}" style="display: none;">
			${allTrackList}
		</p>
			
		<p id="loggedUser" data-musiclist="${loggedInUser.id}" style="display: none;">
			${loggedInUser.id}
		</p>
<link rel="stylesheet" href="/styles/privateStyles.css"/>
<link rel="stylesheet" href="/styles/main_player.css"/>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script>
    const formattedSongs = [
        <c:forEach items="${singleSongList}" var="song" varStatus="loop">
            {
            	music: "${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}",
                img: "${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}",
                artist: "${song.trackArtist}",
                name: "${song.trackTitle}"
            }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

	//const formattedMusicMapList = formattedSongs; 
    // Now you can use formattedSongs in your player.js file
    //console.log(formattedSongs);
</script>		
<script>
    const musicListMap = ${singleMusicList};
    const deleteTrackLink = 'songs/deleteTrack/'
    const viewTrackLink = 'tracks/'
</script>
<!--  -->
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/dynamicPlaylistWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>

</body>
</html>