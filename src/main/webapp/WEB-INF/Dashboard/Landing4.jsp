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
	<link rel="stylesheet" href="/styles/staticStyles.css"/>
	<link rel="stylesheet" href="/styles/privateStyles.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Melody Dreams!</title>
</head>
<body class="container-fluid p-8" style="background:rgba(110.2, 100.4, 336.6, 0.9);font-family:cursive;">
   	<nav id="navbar" class="playerCover imageCover" 
	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;"
   	onmouseout="changePlayerCover(this)" >
	    <ul class="navbar-list " style="">
	        <li class="purple-circle-container lead" 
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
					   	<a class="bluebtn mid-fonts"  href="/melodydreams/tracks/newTrack" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
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
					   	<a class="bluebtn mid-fonts word-slicer" href="/melodydreams/artist/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
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
	<div class="main-container" style="">
	<h1  class="main-greeting-panel" style="">
		<a class="main-greeting word-slicer" href="/melodydreams/artists/${loggedInUser.id}" style="">
			<c:out value="Welcome To Cloud Melody Dreams ${loggedInUser.firstName}!"/>
		</a>
	</h1>
	<!-- <h1>Main Music Player</h1> -->
	<div id = "main-player-cover" class="profileShowcase imageCover playerCover" 
	onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'" 
	onmouseout="changePlayerCover(this)"
	style="width:100%;border-radius:5%;">	<!-- onmouseout="changePlayerCover(this)" -->
	     <div class="flexCover lrg-padding " style="">
	         	<div class="playerCover profileShowcase details imageCover mid-margin-right"  
			  	onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'" 
			  	onmouseout="changePlayerCover(this)" onclick="playpauseTrack(this)" style="" >
			        <div class="now-playing" style="">
			        </div>

				    <div class="audioLink leadShowcase">
					    <div class="track-art"></div>
					</div> 
			       	<!-- a class="audioLink leadShowcase" href="">
					    <div class="track-art"></div>
					</a--> 

					<!-- Mini Panel Display -->
			        <div class="leadShowcase flexCover">
						 <!-- Track Details -->
						 <div class="bluebtn  sm-hor-margin" style="">
				        	  <div class="leadShowcase flexCover">
					        		<div class="leadShowcase track-name sm-padding sm-fonts artist-link-card panel-card" style="cursor:pointer;">
					        		</div>
					          </div>
					          <div class="track-artist artist-link-card" style="">
					          </div>
				          </div>

				          <!-- Left Mini Slider Panel -->
				          <div class="leadShowcase columnCover" style="">
						       <div class="bluebtn slider_container  flexCover mid-padding sm-vert-margin" style="">
			  					   <input id="main-slider" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(this)" style="width:100%;text-align:center">
						       </div>
							   <div class="bluebtn slider_container  panel-info mid-padding" style="">
							      	<div class="panel-remaining-durations-card leadShowcase mid-margin-right" style="font-size:10px;">
							      	 	<c:out value="Time Remaining"></c:out>
							      	 	<div class="remaining_duration" style="font-size:10px;">00.00
							      	 	</div>
							        </div>
							   	 	<div class="total-duration" style="font-size:10px;">00.00
								   	</div>
							    </div>
						   </div> 
			           </div>
		
				       <div class="wave">
					        <span class="stroke"></span>
					        <span class="stroke"></span>
					        <span class="stroke"></span>
					        <span class="stroke"></span>
					        <span class="stroke"></span>
					        <span class="stroke"></span>
					        <span class="stroke"></span>
				       </div>
				  </div>
				  
				  <!-- Right Upper Panel Playlist Container -->
				  <div class="profileShowcase columnCover display-block over-flow" id="" style=";">
					  <c:forEach items="${allSongs}" var="song">
					  <div class="leadShowcase mid-margin-bottom full-width" id="" style="">			    
							
								<input type="hidden" class="songId" value="${song.id}"/>
								<input type="hidden" id="musicListData2" value="${musicList}"/>
				  				<!-- Right Panel Play-list Card -->
								<div class="imageCover playerCover full-width" style="
			        				background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
			        				border-radius:7%;">
									<div class="leadShowcase circle-dark-overlay " style=" 
										<c:choose>
								            <c:when test="${song.genre ==  'Hiphop' }">
									            color:silk;
								            </c:when>
								            <c:when test="${song.genre ==  'hiphop' }">
									            color:silk; 
								            </c:when>
								            <c:otherwise>
								                color: rgb(211, 180, 255);
								            </c:otherwise>
								       	</c:choose>
							       		">
				  						<!-- Right Play Panel -->
							       		<div class="play-panel sm-padding" style="" id ="${song.id}">
											<a href="/melodydreams/tracks/${song.id}" id ="${song.id}" class="leadShowcase word-slicer" style="">
												<c:out value="${song.trackTitle} By ${song.user.firstName}"/>
											</a> 
							       		
								       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
								       		<div class="panelPlayPauseBtn" id="${song.id}" onclick="panelPlaypauseTrack(this)">
										      	<div class="lead flexCover" style="" id="${song.id}" >
												    <div id="${song.id}" class="playpause-Track flex-panel  panel-card" style="">
												        <div class="flex-panel sm-hor-margin" style="">
													        <c:out value="Play ${song.trackTitle}"/>
													        <span id="${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
													        <span id="${song.id}" class="playpauseTrack panelPlaypause_btn" onclick="playpauseTrack(this)">
									        					<i class="fa fa-play-circle fa-5x"></i>
									        				</span> <!-- Span to hold the play/pause button -->
									                        <!-- Hidden Form for Metrics -->
												            <!-- form id="metricsForm-play${song.id}" action="/melodydreams/process/createNewMetrics" method="POST">
												                <input type="hidden" name="playCount" value="1">
												                <input type="hidden" name="seekSliderValue" value="0">
												                <input type="hidden" name="reposts" value="0">
												                <input type="hidden" name="downloads" value="0">
												                <input type="hidden" name="user_id" value="${loggedInUser.id}">
												                <input type="hidden" name="song_id" value="${song.id}">
											   					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
												            </form-->
										 		   		 </div>
												     </div>
											 	</div>
										   </div>

									  	   <!-- Right Upper Panel Display -->
									  	   <div class="time-panel sm-fonts sm-margin-top" style="">
											   	<div class="current-time prev-track sm-fonts" id="${song.id}" style=""> 00.00</div>
											    <div class="panel-total-durations sm-fonts" id="${song.id}" onclick="playpauseTrack(this)" style="font-size:12px;">00.00</div>
											    <div class="panel-remaining-durations next-track sm-fonts" id="${song.id}" >00.00</div>
									  	   </div>

									       <!-- Upper Panel Slider Display -->
										   <!-- input id ="${song.id}" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(${JSON.stringify(song.id)})" style="width:100%;text-align:center; margin:5px 0;"-->
										   <input id ="${song.id}" type="range" min="1" max="100" value="0" class="id_seek_slider hidePanel" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
										   <div class="mini-info-panel slider_container mid-vert-margin sm-fonts" style="">
										      	<c:out value="Volume Controls "/>
											    <div style="">
												    <div id="total_duration_${song.id}" class="total-durations" style="">00.00</div>
												 </div>
												 <div class="upper-panel-remaining-durations-card" style="">
						      	 					<div><c:out value="Time Remaining: "/></div>
												    <div id="${song.id}" class="remaining_durations" style="">00.00</div>
												 </div>
										         <input type="range" min="1" max="100" value="100" class="volume_slider panel-card" onchange="setVolume(this)" style=""> 
								       		</div>

											<!--Main UpperRight Artist Info -->
											<div class="columnCover">
												<c:if test="${loggedInUser.id == song.user.id}">
													 <p class="mini-panel-info bluebtn" style="">
													    <a class="artist-link-card music-link-card" id="${song.id}" href="/melodydreams/${song.id}" style="">
													   		<c:out value="${song.genre} Music From ${song.user.firstName}"/>
												        </a>

									                    <!-- Download Track -->
									                    <a class="leadShowcase download-link-card" id="${song.id}"  href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download onclick="handleDownload(${song.id})">
									                        <c:out value="Download ${song.trackTitle}"/>
									                        <!-- Hidden Form for Metrics -->
												            <form id="" action="/melodydreams/process/createNewMetrics" method="POST" style="display:none;">
												                <input type="hidden" name="playCount" value="0">
												                <input type="hidden" name="seekSliderValue" value="0">
												                <input type="hidden" name="reposts" value="0">
												                <input type="hidden" name="downloads" value="1">
												                <input type="hidden" name="user_id" value="${loggedInUser.id}">
												                <input type="hidden" name="song_id" value="${song.id}">
											   					<input type="submit" value="Download ${song.trackTitle}">
												            </form>
									                    </a>
												     </p>
												</c:if>
											</div>
										</div>
									    <!-- Image Display -->
										<img id="trackImage" src="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" alt="Track Art" style="border-radius:50%; width: 200px; height: 200px; display: none;">
						
						                <!-- Display the audio player -->
										<input type="hidden" id ="track-${song.id}" class="trackID" value="${song.id}" />
										<input type="hidden" id ="${song.id}" class="trackDescription" value="${song.description}" />
										<input type="hidden" class="trackTitle" value="${song.trackTitle}" />
										<input type="hidden" class="trackArtist" value="${song.trackArtist}" />
										<input type="hidden" class="trackImageSrc" value="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" />
										<input type="hidden" class="audioSrc" value="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" />
										
					
										<audio class="audioPlayer" id=" id="audioPlayer-${song.id}">
										    <source src="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" type="audio/mpeg">
										    <c:out value="Your browser does not support the audio element."/>
										</audio>
							        	<div class="main-media-panel hidePanel" id="${song.id}" style="font-size:12px;">
									        <div class="media-buttons leadShowcase mediaShowcase sm-vert-margin sm-vert-padding" style="font-size:12px;">
										        <div class="random-track" onclick="randomTrack()">
										            <i class="fas fa-random fa-2x"></i>
										        </div>
										        <div class="prev-track">
										             <i class="fa fa-step-backward fa-2x"></i>
										        </div>
										        <div class="playpause-track" onclick="playpauseTrack(this)">
										             <i class=""></i>
										        </div>
										        <div class="next-track">
										             <i class="fa fa-step-forward fa-2x"></i>
										        </div>
										        <div class="repeat-track" onclick="repeatTrack()">
										          <i class="fas fa-repeat fa-2x" aria-hidden="true"></i>
										        </div>
									        </div>
											<c:if test="${loggedInUser.id == song.user.id}">	
									 		<form class="editForm leadShowcase mid-vert-margin " action="/melodydreams/editTrack/${song.id}" id="${song.id}" method="GET" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="GET">
											    <input id="${song.id}" class ="edit-input point-border-radius lrg-padding panel-card" type="submit" value="Edit ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form>	
									 		<form class="deleteForm leadShowcase mid-vert-margin" action="/melodydreams/deleteTrack/${song.id}" id="${song.id}" method="post" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="DELETE">
											    <input class ="delete-input  point-border-radius lrg-padding panel-card" id="${song.id}" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form> 
											<!-- Hidden Form for Metrics -->
								            <form id="metricsForm-${song.id}-play" action="/metrics" method="POST" style="display:none;">
								                <input type="hidden" name="playCount" value="1">
								                <input type="hidden" name="seekSliderValue" value="0">
								                <input type="hidden" name="reposts" value="0">
								                <input type="hidden" name="downloads" value="0">
								                <input type="hidden" name="user_id" value="${loggedInUser.id}">
								                <input type="hidden" name="song_id" value="${song.id}">
								                <input type="hidden" name="action" value="play">
								            </form>
								
								            <form id="metricsForm-${song.id}-download" action="/metrics" method="POST" style="display:none;">
								                <input type="hidden" name="playCount" value="0">
								                <input type="hidden" name="seekSliderValue" value="0">
								                <input type="hidden" name="reposts" value="0">
								                <input type="hidden" name="downloads" value="1">
								                <input type="hidden" name="user_id" value="${loggedInUser.id}">
								                <input type="hidden" name="song_id" value="${song.id}">
								                <input type="hidden" name="action" value="download">
								            </form>
											</c:if>
										</div>
									</div>
								 </div>
						 	</div>
						</c:forEach> 
						</div>
					  </div>
					  
					  <div class ="" style="" >
						  <div class ="track-description hidePanel" style="" >
						  </div>
					  </div>
				      
					  <!-- Main Bottom Media Panel -->
				      <div class="columnCover leadShowcase sm-vert-margin">
						  <div class="leadShowcase mid-vert-margin flexCover lrg-padding cursor-pointer" style="" onclick="playpauseTrack(this)">
							   <div class="current-time prev-track" id="allCurrentTime"> 00.00</div>
							   <div class="total-duration">00.00</div>
							   <div class="remaining-duration-panel">
							   		<div class="remaining_duration next-track">00.00</div>
							   </div>
						  </div>
			
					      <!-- Main Media Controls -->
						  <div class="leadShowcase columnCover" style="">		
							  <!-- Track Status -->
							  <a href="/melodydreams/artists/${loggedInUser.id}"  class ="track-status hidePanel ">
								</a>
							  <input type="range" min="1" max="100" value="0" class="seek_slider main_bottom_seek_slider mid-vert-margin" onchange="seekTo(this)" style="width:100%;">
						      <div class="main-media-buttons media-buttons leadShowcase mediaShowcase bluebtn" style="">
							        <div class="random-track" onclick="randomTrack()">
							            <i class="fas fa-random fa-2x"></i>
							        </div>
							        <div class="prev-track">
							             <i class="fa fa-step-backward fa-2x"></i>
							        </div>
							        <div class="playpause-track" onclick="playpauseTrack(this)">
							             <i class="fa fa-play-circle fa-5x"></i>
							        </div>
							        <div class="next-track">
							             <i class="fa fa-step-forward fa-2x"></i>
							        </div>
							        <div class="repeat-track" onclick="repeatTrack()">
							          <i class="fas fa-repeat fa-2x" aria-hidden="true"></i>
							        </div>
						       </div>
						   </div>
					         
					       <!-- Bottom Main Media Sliders -->
					       <div class=" slider_container mid-vert-margin" style="">
						      	 <c:out value="Volume Controls "/>
						      	 <div style="">
						      	 	<div class="total-duration" style="">00.00</div>
						      	 </div>
						      	 <div class="panel-remaining-durations-card">
						      	 	<div><c:out value="Time Remaining: "/></div>
						      	 	<div class="remaining_duration" style="">00.00</div>
						      	 </div>
						         <input type="range" min="1" max="100" value="100" class="volume_slider" onchange="setVolume()" style="text-align:center;cursor:pointer">
					       </div>
				       </div>	
						<!-- Bottom Player Welcome Note -->
						<a href="/melodydreams/artists/${loggedInUser.id}"  class ="panel-greetings hidePanel mid-vert-margin panel-card">
						</a>
				 </div>
			 </div>
			 
			<a href="/melodydreams/artists/${loggedInUser.id}"  class ="track-description hidePanel mid-vert-margin lrg-fonts sm-padding">
			</a>
	  
	  		 <!-- Mid Section Navigation -->
			 <div class="lead lrg-vert-margin imageCover"
				 onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/hanny-naibaho-aWXVxy8BSzc-unsplash.jpg)'"
				 onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
				 onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
				 onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/anthony-delanoix-hzgs56Ze49s-unsplash.jpg)'">		
				<div class="leadShowcase"
				 style="">
					 <div class="flexCover leadShowcase" style="">
						
							<a href="/melodydreams/newTrack" class="panel-card a-tag-links track-upload" style="">
								<c:out value="UPLOAD NEW TRACK"/>
							</a>
							<a href="/melodydreams/logout" class="panel-card a-tag-links" style="background:rgba(2.11, 0.03, 1, 0.9); color:rgba(12.11, 2.03, 11, 0.9);">
								<c:out value="LOGOUT HERE"/>
							</a>
				 			<a href="/melodydreams/artists/${loggedInUser.id }" class="panel-card a-tag-links word-slicer" style="background:rgba(6.8, 8, 0.8, 0.9); color:rgba(12.11, 2.03, 11, 0.9);">
				 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
				 			</a>
					 </div>	
					 <div class="lead columnPanel mid-section-nav-panel" style="">
						
							<a href="/melodydreams/artists" class="panel-card artist-hub" style="">
								<c:out value="ARTIST HUB"/>
							</a>
					 </div>	
				  </div>	
						<div class ="track-description section-track-description hidePanel" style="" >
						</div>	
				 </div>
			 <div>
	 
	  		<!-- Middle Section Display Panel -->
	  		<div class="profileShowcase lrg-vert-margin playerCover imageCover" 
		    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'" 
		    onmouseout="changePlayerCover(this)"
	    	style="border-radius:5%;">
		  	<!-- <h1>Section Music Player</h1> -->
			      <div class="flexCover" style="">
				      <div class="playerCover lead profileShowcase details imageCover mid-margin-right" 				
			  		 		onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'" 
			  				onmouseout="changePlayerCover(this)" onclick="playpauseTrack(this)" 
					   		style="border-radius:5%;">
					   		
					        <div class="now-playing word-slicer" style="width:100%;text-align:center">
					        </div>				       	
					       	
						    <div class="audioLink leadShowcase mid-vert-margin">
							    <div class="track-art"></div>
							</div> 

							<!-- Section Mini Panel Display -->
					        <div class="leadShowcase flexCover">
								 <!-- Track Details -->
								 <div class="bluebtn " style="">
						        	  <div class="leadShowcase flexCover">
							        		<div class="track-name leadShowcase sm-padding sm-fonts artist-link-card panel-card" style="cursor:pointer;">
							        		</div>
							          </div>
							          <div class="track-artist leadShowcase artist-link-card" style="cursor:pointer;">
							          </div>
						          </div>
	
						          <!-- Left Mini Slider Panel -->
						          <div class="leadShowcase columnCover" style="">
								       <div class="bluebtn slider_container  flexCover mid-padding sm-vert-margin" style="">
					  					   <input id="main-slider" type="range" min="1" max="100" value="0" class="seek_slider panel-card" onchange="seekTo(this)" style="width:100%;text-align:center">
								       </div>
									   <div class="bluebtn slider_container  panel-info" style="cursor:pointer;background:rgba(1.33, 10.64, 0.60, 0.9);">
									      	<div style="display:flex;flex-direction:column;font-size:10px;">
									      	 	<c:out value="Time Remaining"></c:out>
									      	 	<div class="remaining_duration prev-track" style="font-size:10px;">00.00
									      	 	</div>
									        </div>
	  								   	 	<div class="total-duration" style="font-size:10px;">00.00
										   	</div>
									    </div>
								   </div> 
					           </div>
						      <div class="wave">
							        <span class="stroke"></span>
							        <span class="stroke"></span>
							        <span class="stroke"></span>
							        <span class="stroke"></span>
							        <span class="stroke"></span>
							        <span class="stroke"></span>
							        <span class="stroke"></span>
						      </div>  
					  </div>
					  
					  <!-- Section Playlist -->
					  <div class="profileShowcase sm-margin-right" id="" style="overflow-y:auto; max-height:520px;border-radius:5%;margin:0 5px;padding:5px;width:100%;">
						<c:forEach items="${allSongs}" var="song">
					  	<div class="leadShowcase bluebtn" id="table table-dark" style="text-align:center;border-radius:5%;margin:5px 0;">
							 
							<div class="leadShowcase">	
						    
								<td style="">
									<input type="hidden" class="songId" value="${song.id}"/>
									<input type="hidden" id="musicListData2" value="${musicList}"/>
								  	
									<div class="play-panel " style="
				        				background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
				        				background-repeat: no-repeat;background-position: center;background-size: cover;padding:5px;border-radius:5%;">
										<div style="text-decoration:none; 
											<c:choose>
									            <c:when test="${song.genre ==  'Hiphop' }">
										            color:silk;
									            </c:when>
									            <c:when test="${song.genre ==  'hiphop' }">
										            color:silk; 
									            </c:when>
									            <c:otherwise>
									                color: rgb(211, 180, 255);
									            </c:otherwise>
									       	</c:choose>
								       		">
								       		<div class="lead circle-dark-overlay" style="font-size:16px;margin:5px 0;" id ="${song.id}">
												<a href="/melodydreams/tracks/${song.id}" id ="${song.id}" class="mini-panel-info leadShowcase word-slicer" style="width:100%;font-size:16px;">
													<c:out value="${song.trackTitle} By ${song.user.firstName}"/>
												</a> 
								       		
									       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
									       		<div class="panelPlayPauseBtn" id="${song.id}">
											       	<div class="lead panelPlaypauseBtn" style="margin:5px 0;" id="${song.id}" onclick="panelPlaypauseTrack(this)">
													    <div id="${song.id}" class="playpause-Track flex-panel  panel-card" style="font-size:16px;padding:10px 0;width:100%;">
													        <p class="flex-panel" style="margin:5px;">
														        <c:out value="Play ${song.trackTitle}"/>
														        <span id="${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
														        <span id="${song.id}" class="playpauseTrack setionPlayPauseBtn" onclick="playpauseTrack(this)">
										        					<i class="fa fa-play-circle fa-5x"></i>
										        				</span> <!-- Span to hold the play/pause button -->
													   		 </p>
													     </div>
													 </div>
												 </div>
											 </div>
							       		</div>
								       
								  	<!-- Section Upper Panel Display -->
								  	<div class="mini-panel-info" style="cursor:pointer;">
									   <div class="current-time prev-track" id="${song.id}" style=""> 00.00</div>
									   <div class="panel-total-durations" id="${song.id}" onclick="playpauseTrack(this)" style="font-size:12px;">00.00</div>
									   <div class="panel-remaining-durations next-track" id="${song.id}" >00.00</div>
								  	</div>
									  
								      <!-- Section Panel Slider Display -->
									  <!-- input id ="${song.id}" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(${JSON.stringify(song.id)})" style="width:100%;text-align:center; margin:5px 0;"-->
									  <input id ="sectionSlider_${song.id}" type="range" min="1" max="100" value="0" class="seek_slider hidePanel sectionSlider" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
									   <div class="leadShowcase slider_container  mini-panel-info panel-car" style="">
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
					
										<!-- Section Artist Info -->
										<c:if test="${loggedInUser.id == song.user.id}">
										    <p class="mini-panel-info bluebtn" style="align-items:center;font-size:12px;">
										    	<a class="artist-link-card music-link-card" id="${song.id}"  href="/melodydreams/${song.id}" style="">
											    	<c:out value="${song.genre} Music From ${song.user.firstName}"/>
										        </a>
										        <!--Download Track -->
										        <a class="download-link-card" id="${song.id}" href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download>
										        	<c:out value="Download ${song.trackTitle}"/>
										        </a>
									        </p>
									    </c:if>
								        </div>
					
									    <!-- Image Display -->
										<img id="trackImage" src="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" alt="Track Art" style="border-radius:50%; width: 200px; height: 200px; display: none;">
						
						                <!-- Display the audio player -->
										<input type="hidden" id ="track-${song.id}" class="trackID" value="${song.id}" />
										<input type="hidden" class="trackTitle" value="${song.trackTitle}" />
										<input type="hidden" class="trackArtist" value="${song.trackArtist}" />
										<input type="hidden" class="trackImageSrc" value="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" />
										<input type="hidden" class="audioSrc" value="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" />
										
					
										<audio class="audioPlayer" id="audioPlayer">
										    <source src="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" type="audio/mpeg">
										    <c:out value="Your browser does not support the audio element."/>
										</audio>
										<div class ="section-media-panel hidePanel" id="${song.id}">
									        <div class="media-buttons leadShowcase mediaShowcase sm-vert-margin" style="font-size:12px;">
										        <div class="random-track" onclick="randomTrack()">
										            <i class="fas fa-random fa-2x"></i>
										        </div>
										        <div class="prev-track">
										             <i class="fa fa-step-backward fa-2x"></i>
										        </div>
										        <div class="playpause-track" onclick="playpauseTrack(this)">
										             <i class=""></i>
										        </div>
										        <div class="next-track">
										             <i class="fa fa-step-forward fa-2x"></i>
										        </div>
										        <div class="repeat-track" onclick="repeatTrack()">
										          <i class="fas fa-repeat fa-2x" aria-hidden="true"></i>
										        </div>
									        </div>
											<c:if test="${loggedInUser.id == song.user.id}">	
									 		<form class="editForm leadShowcase mid-vert-margin" action="/melodydreams/editTrack/${song.id}" method="get" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="GET">
											    <input class ="edit-input point-border-radius lrg-padding panel-card" id="${song.id}" type="submit" value="Replace ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form>
											<form class="deleteForm leadShowcase mid-vert-margin" action="/melodydreams/deleteTrack/${song.id}" method="post" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="DELETE">
											    <input class ="delete-input point-border-radius lrg-padding panel-card" id="${song.id}" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form>
											</c:if>
										</div>
									</td>
							</div>
						</div> 
					</c:forEach> 
					</div>
			  </div>
			  <div class ="track-description section-track-description hidePanel" style="" >
			  </div>	

			  <div class="leadShowcase flexCover" style="padding:2px;margin:5px 0;font-size:12px;cursor:pointer;">
				   <div class="current-time prev-track" id="allCurrentTime"> 00.00</div>
				   <div class="total-duration" onclick="playpauseTrack(this)">00.00</div>
				   <div class="remaining_duration next-track">00.00</div>
			  </div>
			  <div class="bluebtn" style="align-items:center;width:100%;padding:2px;margin:5px 0;font-size:12px;">
				  <input type="range" min="1" max="100" value="0" class="seek_slider main_bottom_seek_slider" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
			      <div class="media-buttons leadShowcase mediaShowcase" style="">
				        <div class="random-track" onclick="randomTrack()">
				            <i class="fas fa-random fa-2x"></i>
				        </div>
				        <div class="prev-track">
				             <i class="fa fa-step-backward fa-2x"></i>
				        </div>
				        <div class="playpause-track" onclick="playpauseTrack(this)">
				             <i class="fa fa-play-circle fa-5x"></i>
				        </div>
				        <div class="next-track">
				             <i class="fa fa-step-forward fa-2x"></i>
				        </div>
				        <div class="repeat-track" onclick="repeatTrack()">
				          <i class="fas fa-repeat fa-2x" aria-hidden="true"></i>
				        </div>
			      </div>
			   </div>
			  <div class ="track-status section-track-description hidePanel" style="" >
			  </div>	
		         
		      <div class="slider_container " style="display:flex;justify-content:space-between;align-items:center;width:100%;text-align:center;margin:5px 0;padding:10px;cursor:pointer;background:rgba(1.33, 10.64, 0.60, 0.9);">
		      	 <c:out value="Volume Controls "/>
		      	 <div style="display:flex;">
		      	 	<div class="total-duration" style="margin:0 5px">00.00</div>
		      	 </div>
		      	 <div style="display:flex;">
		      	 	<c:out value="Time Remaining: "/><div class="remaining_duration" style="margin:0 5px">00.00</div>
		      	 </div>
		         <input type="range" min="1" max="100" value="100" class="volume_slider" onchange="setVolume()" style="text-align:center;cursor:pointer">
		      </div>
			</div> 

			<a href="/melodydreams/artists/${song.id}"  class="panel-greetings hidePanel">
			</a>

			 <div class="lead lrg-vert-margin playerCover imageCover"
				 style="background-image:url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg);">		
				<div class="leadShowcase imageCover"
				 onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
				 onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/anthony-delanoix-hzgs56Ze49s-unsplash.jpg)'"
				 style="background-image:url(${pageContext.request.contextPath}/img/anthony-delanoix-hzgs56Ze49s-unsplash.jpg);border-radius:5%;">
					 <div class="flexCover leadShowase sm-vert-margin" style="background:rgba(1.12, 11, 11, 0.9);">
							<a href="/melodydreams/newTrack" class="panel-card track-upload" style="">
								<c:out value="UPLOAD NEW TRACK"/>
							</a>
							<a href="/melodydreams/logout" class="panel-card" id="" style="">
								<c:out value="LOGOUT HERE"/>
							</a>
				 			<a style="" href="/melodydreams/artists/${loggedInUser.id }" class="panel-card">
				 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
				 			</a>
					</div>	

					<div class="profileShowcase flex-column playerCover" id="" style="">
						<a href="/melodydreams/artist/${song.id}"  class ="track-status hidePanel">
						</a>

					  	<form class="trackListLink" action="/melodydreams/tracks" method="get" style="">
						    <input type="hidden" name="_method" value="GET">
						    <input class ="panel-card playlist-link" type="submit" value="PLAYLIST PANEL" style="" >
						</form>
				  								  
						<div class ="track-description hidePanel" style="" >
						</div>

					</div>
				</div>
			</div>
		</div>
			
  		<!-- Footer Upload and Logout Panel -->
		<p id="musicListData" data-musiclist="${musicList}" style="display: none;">
			${musicList}
		</p>

		<p id="allTrackListData" data-musiclist="${allTrackList}" style="display: none;">
			${allTrackList}
		</p>
			
		<p id="loggedUser" data-musiclist="${loggedInUser.id}" style="display: none;">
			${loggedInUser.id}
		</p>

  		<!-- Footer Playlist Panel -->
		<div class="profileShowcase flexBox playerCover x-lrg-padding imageCover"	
		onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'" 
		onmouseout="changePlayerCover(this)" style="border-radius:5%;">
			<div class="profileShowcase imageCover over-flow"
				onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
				onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
				onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
				onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/anthony-delanoix-hzgs56Ze49s-unsplash.jpg)'" 
			id="" style="">
			<c:forEach items="${allSongs}" var="song">
			<input type="hidden" class="songId" value="${song.id}"/>
			<input type="hidden" id="musicListData2" value="${musicList}"/>
			  	<div class="imageCover mid-vert-margin"style="text-align:center;border-radius:5%;">
		  		
					<div class="lead" style="border-radius:5%;">
					  	<!--Footer Title Display -->
						<div id="${song.id}" class=" circle-dark-overlay titleCover leadShowcase" style="
				        background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
				   		background-repeat: no-repeat;background-position: center;background-size: cover;padding:5px;border-radius:5%;
			        	font-size:16px;margin:5px 0;" id ="${song.id}">

		            	    <a href="/melodydreams/tracks/${song.id}" id ="${song.id}" class="mini-panel-info leadShowcase word-slicer" style="">
								<c:out value="${song.trackTitle} By ${song.trackArtist}"/>
							</a> 
					  	</div>
					  	<!--Footer Play Display -->
						<div class="" style="font-size:14px;">
							<div style="
				        	background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
				   			background-repeat: no-repeat;background-position: center;background-size: cover;padding:5px;border-radius:5%;text-decoration:none; 
								<c:choose>
						            <c:when test="${song.genre ==  'Hiphop' }">
							            color:silk;
						            </c:when>
						            <c:when test="${song.genre ==  'hiphop' }">
							            color:silk; 
						            </c:when>
						            <c:otherwise>
						                color: rgb(211, 180, 255);
						            </c:otherwise>
						       	</c:choose>
					       		">
	
								<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
					       		<div class="panelPlayPauseBtn" id="${song.id}" onclick="panelPlaypauseTrack(this)">
							       	<div class="lead panelPlaypause_btn" style="margin:5px 0;" id="${song.id}">
									    <div id="${song.id}" class="playpause-Track flex-panel  panel-card" style="font-size:16px;padding:10px 0;width:100%;">
									        <p class="flex-panel" style="margin:5px;">
										        <c:out value="Play ${song.trackTitle} By ${song.trackArtist}"/>
										        <span id="${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
										        <span id="${song.id}" class="footer_playpause_btn playpauseTrack" onclick="playpauseTrack(this)">
						        					<i class="fa fa-play-circle fa-5x"></i>
						        				</span> <!-- Span to hold the play/pause button -->
									   		</p>
									    </div>
									  </div>
	            			    </div>
					       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
					       		
									<p id ="${song.id}" class="panel-card columnCover mid-vert-padding mid-vert-margin word-slicer" onclick="playpauseTrack(this)" style="">
										<c:out value="${song.trackTitle}: ${song.description}"/>
									</p>
				       	   </div>
				       	   
				       	   <!--Footer Panel Duration Details -->
						   <div class="leadShowcase flexCover mid-vert-margin" style="">
							   <div class="current-time prev-track" id="${song.id}"> 00.00</div>
							   <div class="panel-total-durations display-duration-info" id="total_duration_${song.id}" onclick="playpauseTrack()">00.00</div>
							   <div class="panel-remaining-durations next-track" id="${song.id}" >00.00</div>
						   </div>

				       	   <!-- Footer Slider Controls --> 
						   <div class="flexbtn profileShowcase bluebtn panel-card" style="">   
							  	<!-- input id ="${song.id}" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(${JSON.stringify(song.id)})" style="width:100%;text-align:center; margin:5px 0;"-->
							  	<input id ="${song.id}" type="range" min="1" max="100" value="0" class="panel_seek_slider hidePanel footer_slider_specs" onchange="songSeekTo(this)" style="">
							  	<div class="slider_container mid-fonts" style="">
							      	<c:out value="Volume Controls "/>
									<div style="">
									    <span>Time Remaining: </span>
									    <div id="${song.id}" class="remaining_durations" style="">00.00</div>
									</div>
							     	<div style="">
								    	<div id="total_duration_${song.id}" class="total-durations" style="">00.00</div>
								 	</div>
							        <input type="range" min="1" max="100" value="100" class="volume_slider" onchange="setVolume(this)" style="text-align:center;cursor:pointer">
							    </div>
	
								<c:if test="${loggedInUser.id == song.user.id}">
							    <div class="footer-flex-panel">
								    <!--Artist Link  -->
							        <a class="music-link-card" id="${song.id}" href="/melodydreams/users/${song.user.id}" download>
							    		<c:out value="${song.genre} Music From ${song.album} Album"/>

							        </a>
							        <i class="word-slicer">
							    		<c:out value="Artist: ${song.trackArtist} - ${song.genre} Music"/>
							    	</i>
							        <!--Download Track -->
							        <a class="download-link-card" href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download>
							        	<c:out value="Download ${song.trackTitle}"/>
							        </a>
						        </div>
						        </c:if>
						        <div class="footer-media-panel hidePanel" id="${song.id}" style="">
								        <div class="media-buttons leadShowcase mediaShowcase mid-vert-margin" style="">
									        <div class="random-track" onclick="randomTrack()">
									            <i class="fas fa-random fa-2x"></i>
									        </div>
									        <div class="prev-track">
									             <i class="fa fa-step-backward fa-2x"></i>
									        </div>
									        <div class="playpause-track" onclick="playpauseTrack(this)">
									             <i class="fa fa-play-circle fa-5x"></i>
									        </div>
									        <div class="next-track">
									             <i class="fa fa-step-forward fa-2x"></i>
									        </div>
									        <div class="repeat-track" onclick="repeatTrack()">
									          <i class="fas fa-repeat fa-2x" aria-hidden="true"></i>
									        </div>
								       </div>

										<c:if test="${loggedInUser.id == song.user.id}">	
										<div class="flexbtn">
								 		<form class="editForm" action="/melodydreams/editTrack/${song.id}" method="get" style="">
										    <input type="hidden" name="_method" value="GET">
										    <input id="${song.id}" class ="edit-input  panel-card" type="submit" value="Update ${song.trackTitle} By ${song.trackArtist}" style="width: 100%;padding:7px;width:100%;" >
										</form>
								 		<form class="deleteForm" action="/melodydreams/deleteTrack/${song.id}" method="post" style="">
										    <input type="hidden" name="_method" value="DELETE">
										    <input id="${song.id}" class ="delete-input  panel-card" type="submit" value="Delete Track" style="width: 100%;padding:7px;width:100%;" >
										</form>
										</div>
										</c:if>
							      </div>
					         </div>
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
			</c:forEach> 
			</div>
			<div class="mid-vert-margin track-description hidePanel"></div>
	  
	  		<!-- Footer JSPlaylist Info Side Panel -->				
	        <div class="flexCover leadShowcase playerCover imageCover mid-vert-margin imageCover" style=""
				onmouseover="this.style.transition = 'background-image 1s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd.jpg)'"
		    	onmouseout="changePlayerCover(this)"> 
				<form class="trackListLink" action="/melodydreams/artists/${loggedInUser.id}" method="get">
				    <input type="hidden" name="_method" value="GET">
				    <input class =" panel-card" type="submit" value="${loggedInUser.firstName} Access">
				</form>
				<form class="trackListLink" action="/melodydreams/tracks" method="get">
				    <input type="hidden" name="_method" value="GET">
				    <input class =" panel-card" type="submit" value="View All Tracks" >
				</form>
			  	<form class="trackListLink " action="/melodydreams/artists" method="get">
				    <input type="hidden" name="_method" value="GET">
				    <input class =" panel-card" type="submit" value="View All Artists Tracks">
				</form>
			</div>
		
		<div class="lead lrg-vert-margin"
			onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
			onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
			onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/anthony-delanoix-hzgs56Ze49s-unsplash.jpg)'">		
			<div class="leadShowcase flexPanel panel-card imageCover">
				 <div class="flexCover leadShowcase" style="">
						<a href="/melodydreams/newTrack" class="track-upload panel-card" style="">
							<c:out value="UPLOAD NEW TRACK"/>
						</a>
						<a href="/melodydreams/logout" class=" panel-card" id="" style="">
							<c:out value="LOGOUT HERE"/>
						</a>
			 			<a style="" href="/melodydreams/artists/${loggedInUser.id }" class="word-slicer panel-card">
			 				<c:out value="${loggedInUser.firstName} Profile Access"/>
			 			</a>
				</div>	
			
				<div class="profileShowcase columnCover mid-vert-margin" id="" style="">
				  	<form class="flexCover" action="/melodydreams/tracks" method="get" style="width:100%;">
					    <input type="hidden" name="_method" value="GET">
					    <input class ="lead playlist-link panel-card" type="submit" value="Playlists" style="" >
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>

<link rel="stylesheet" href="/styles/staticStyles.css"/>	
<link rel="stylesheet" href="/styles/privateStyles.css"/>	
<script>
    const formattedSongs = [
        <c:forEach items="${allTrackList}" var="song" varStatus="loop">
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
function handlePlay(songId) {
    document.getElementById(`metricsForm-${songId}-play`).submit();
    const audioPlayer = document.getElementById(`audioPlayer-${songId}`);
    if (audioPlayer.paused) {
        audioPlayer.play();
    } else {
        audioPlayer.pause();
    }
}

function handleDownload(songId) {
    document.getElementById(`metricsForm-${songId}-download`).submit();
}
    const musicListMap = ${musicList};
    const deleteTrackLink = 'songs/deleteTrack/'
    const viewTrackLink = 'tracks/'
</script>
<!--  -->
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>
</body>
</html>