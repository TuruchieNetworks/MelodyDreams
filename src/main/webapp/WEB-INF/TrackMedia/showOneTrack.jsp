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
	<link rel="stylesheet" href="/styles/privateStyles.css"/>
	<link rel="stylesheet" href="/styles/main_player.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Melody Dreams!</title>
</head>
<body class="container-fluid p-8 panel-card" style="background:rgba(110.2, 100.4, 336.6, 0.9);font-family:cursive;">
   	<nav id="navbar" class="playerCover imageCover" 
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
	            	<span class="bright-cover sm-padding full-width">
	            		<c:out value="Cloud Melody Dreams"/>
	            	</span>
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
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts panel-card word-slicer party-lights" href="/melodydreams/tracks/${mostRecentSong.id}" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="Most Recent Track"/>
					    </a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts party-lights" href="/melodydreams/artists" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="View All Artists"/>
					   	</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts party-lights" href="/melodydreams/favoriteArtists" style="background: rgba(9, 6, 53, 0.466);">Favorite Artists</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts party-lights" href="/melodydreams/artists/${loggedInUser.id}" style="background: rgba(9, 6, 53, 0.466);">
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
					   	style="">
					   	<a class="word-slicer mid-fonts" href="/melodydreams/usersPlaylists" style="">
					   		<c:out value="${loggedInUser.playlists.size()} Created Playlists!"/>
					   	</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts party-lights" href="/melodydreams/favoritePlaylists" style="">
				   			<c:out value="Favorite Playlists"/>
				   		</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
				   		<a class="bluebtn mid-fonts party-lights" href="/melodydreams/favoriteSongs" style="">
				   			<c:out value="Favorite Songs"/>
				   		</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts party-lights" href="/melodydreams/albumns" style="">
					   		<c:out value="Veiw Albums"/>
					   	</a>
					</li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts party-lights" href="/melodydreams/favoriteAlbums" style="">
					   		<c:out value="Favorite Albums"/>
					   	</a>
					</li>
	            </ul>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Albums Panel"/> 
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn panel-card mid-fonts party-lights"  href="/melodydreams/albums" style="background: rgba(9, 6, 13, 0.466);">
	                			
	                    <i class ="word-slicer mid-fonts">
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
	                    <i class ="dark-fonts sm-hor-padding round-border-radius sm-margin-top mid-fonts party-lights panel-card">
	                    	<c:out value="${loggedInUser.albums.size()} New"/>
	                    </i>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts" href="/melodydreams/albums" style="background: rgba(9, 6, 13, 0.466);">
	                		<c:out value="Favorite Albums"/>
	                	</a>
	                    <i class ="dark-fonts sm-hor-padding round-border-radius sm-margin-top sm-paddin mid-fonts party-lights panel-card">
	                    	<c:out value="${loggedInUser.favoriteAlbums.size()} New"/>
	                    </i>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts word-slicer bright-colors party-lights"  href="/melodydreams/albums/newAlbum" style="background: rgba(9, 6, 13, 0.466);">
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
					   	<a class="bluebtn mid-fonts party-lights" href="/melodydreams/artists/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Update Profile"/>
	                	</a>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts party-lights"  href="/melodydreams/newTrack" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Upload New Track"/>
	                	</a>
	                </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts panel-card party-lights" href="/melodydreams/userImageDairy/newUserImageDairy" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
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
					   	<a class="bluebtn panel-card mid-fonts word-slicer party-lights" href="/melodydreams/artist/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
			                <c:out value="${loggedInUser.firstName} Profile Access"/>
			            </a>
			        </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts party-lights" href="/melodydreams/userAddresses/newUserAddress" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
			                <c:out value="Update Contact Details"/>
			            </a>
			        </li>
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn mid-fonts party-lights" href="/melodydreams/logout" style="background:rgba(30.1, 0.2, 0.9, 0.6);">
			                <c:out value="Console Log Out"/>
			            </a>
			        </li>
	            </ul>
	        </li>  
	    </ul>
	</nav> 
	<div class="main-container" style="">
	<h1  class="main-greeting-panel" style="" >
		<a class="main-greeting type-writer word-slicer leadShowcase imageCover playerCover" href="/melodydreams/artists/${loggedInUser.id}" style=""
			onmouseover="this.style.transition = 'background-image 1.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			onmouseout="changePlayerCover(this)">
			<c:out value="Welcome To Cloud Melody Dreams ${loggedInUser.firstName}!"/>
		</a>
	</h1>
	<!-- <h1>Main Music Player</h1> -->
	<div id = "main-player-cover" class="profileShowcase imageCover mid-padding" 
	onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'" 
	onmouseout="changePlayerCover(this)"
	style="">	<!-- onmouseout="changePlayerCover(this)" -->
	     <div class="playerCover flexCover lrg-padding" style="">
	         	<div class="playerCover profileShowcase imageCover details mid-margin-right"
				onmouseover="this.style.transition = 'background-image 1.2s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"   
			  	onclick="playpauseTrack(this)" onmouseout="changePlayerCover(this)" style="" >
			        <div class="now-playing" style="">
			        </div>

				    <div class="playerCover audioLink leadShowcase">
					    <div class="imageCover track-art"></div>
					</div> 
					
					<!-- Mini Panel Display -->
			        <div class="leadShowcase flexCover">
						 <!-- Track Details -->
						 <div class="bluebtn sm-hor-margin" style="">
				        	  <div class="playerCover leadShowcase">
					        		<div class="leadShowcase track-name sm-padding sm-fonts artist-link-card panel-card" style="cursor:pointer;">
					        		</div>
					        		<div class="play-count bright-colors sm-padding sm-fonts sm-vert-margin artist-link-card panel-card" style="cursor:pointer;">
					        			<c:out value=""/>
					        		</div>
					          </div>
					          <div class="playerCover" style="">
					          		<div class="track-artist artist-link-card" style="">
					          		</div>
					          </div>
				          </div>

				          <!-- Left Mini Slider Panel -->
				          <div class="leadShowcase columnCover" style="">
						       <div class="playerCover bluebtn slider_container  flexCover mid-padding sm-vert-margin" style="">
			  					   <input id="main-slider" type="range" min="1" max="100" value="0" class="seek_slider left-mini-player-slider" onchange="seekTo(this)" style="width:100%;text-align:center">
						       </div>
							   <div class="playerCover bluebtn slider_container  panel-info mid-padding" style="">
							      	<div class=" panel-remaining-durations-card leadShowcase mid-margin-right" style="font-size:10px;">
							      	 	<c:out value="Time Remaining"></c:out>
							      	 	<div class="remaining_duration" style="font-size:10px;">00.00
							      	 	</div>
							        </div>
							   	 	<div class="total-duration leadShowcase" style="font-size:10px;">00.00
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
				  <div class="profileShowcase playerCover columnCover display-block over-flow playlistContainer" id="" style="" >
					  <c:forEach items="${singleSongList}" var="song">
					  <div class="div-slicer leadShowcase mid-margin-bottom imageCover full-width" id="${song.id}"  
					  style=" background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});">
				        			    			
							<input type="hidden" class="songId" value="${song.id}"/>
							
			  				<!-- Right Panel Play-list Card -->
								<div class="" style="
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
								    <div class="play-panel leadShowcase mid-padding circle-dark-overlay imageCover full-width"  style="
				        				background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});"
				  						id ="${song.id}">
								       	<c:choose>
										    <c:when test="${playCounts[song.id] == 0}">
										        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
										            <c:out value="${song.trackTitle} By ${song.user.firstName}: Not Yet Played!"/>
										        </a>
										    </c:when>
										    <c:when test="${playCounts[song.id] == 1}">
										        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
										            <c:out value="${song.trackTitle} By ${song.user.firstName}: Played ${playCounts[song.id]} Once"/>
										        </a>
										    </c:when>
										    <c:otherwise>
										        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
										            <c:out value="${song.trackTitle} By ${song.user.firstName} Played ${playCounts[song.id]} Times"/>
										        </a>
										    </c:otherwise>
										</c:choose>
	
							       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->							       		
								      	<div  id="id-play-panel-${song.id}" class="lead flexCover bright-colors track-panel id-play-panel" style="">
										    <div id="${song.id}" class="flex-panel panel-card full-width" style=""  onclick="panelPlaypauseTrack(this)">
										        <div class="flex-panel sm-hor-margin outer-panel-player-cover " style="">
											        <c:out value="Play ${song.trackTitle}"/>
											        
											        <span id="span-id-play-panel-${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
											        <span id="${song.id}" class="spreader bright-flash upper-panel-play-btn">
							        					<i id="${song.id}"  class="inner-panel-player-cover track-art-widget fa fa-play-circle fa-5x">
							        					</i>
							        				</span> 
											       	<i class="sm-fonts type-writer word-slicer">
											       	<c:choose>
													    <c:when test="${playCounts[song.id] == 0}">
						        							<c:out value="Not Played Yet"/>
													    </c:when>
													    <c:when test="${playCounts[song.id] == 1}">
						        							<c:out value="Played ${playCounts[song.id]} once"/>
													    </c:when>
													    <c:otherwise>
						        							<c:out value="Played ${playCounts[song.id]} times"/>
						        						</c:otherwise>
						        					</c:choose>
						        					</i><!-- Span to hold the play/pause button -->
							                        <!-- Hidden Form for Metrics -->
										            <form id="metricsForm-play-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
										                <input type="hidden" name="playCount" value="1">
										                <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
										                <input type="hidden" name="reposts" value="0">
										                <input type="hidden" name="downloads" value="0">
										                <input type="hidden" name="user_id" value="${loggedInUser.id}">
										                <input type="hidden" name="song_id" value="${song.id}">
									   					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
										            </form>
								 		   		 </div>
										      </div>
										 	</div>
										 	<div id="non-id-play-panel-${song.id}" class="lead flexCover non-id-play-panel bright-colors hidePanel" style="display:none" onclick="playpauseTrack(this)">
											    <div class="flex-panel panel-car playerCover " style="">
											        <div id="${song.id}" class="flex-panel sm-hor-margin pause-panel" style="">
												        <c:out value="Play ${song.trackTitle}"/>
												        <span id="${song.id}" class="spreader "></span> <!-- Span to hold the play/pause icon -->
												        <span id="${song.id}" class="bright-colors upper-panel-play-btn playerCover">
								        					<i id="${song.id}"  class="playerCover upper-track-art-widget fa fa-play-circle fa-5x"></i>
													       	</span> <i class="sm-fonts type-writer word-slicer">
													       	<c:choose>
															    <c:when test="${playCounts[song.id] == 0}">
								        							<c:out value="Not Played Yet"/>
															    </c:when>
															    <c:when test="${playCounts[song.id] == 1}">
								        							<c:out value="Played ${playCounts[song.id]} once"/>
															    </c:when>
															    <c:otherwise>
								        							<c:out value="Played ${playCounts[song.id]} times"/>
								        						</c:otherwise>
								        					</c:choose>
								        					</i>
								        				<!-- Span to hold the play/pause button -->
								                        <!-- Hidden Form for Metrics -->
											            <form id="metricsForm-play-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
														    <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
											            </form>
										 	   		 </div>
											     </div>
										   </div>
											 	

										   <!-- Panel Media Area -->
										   <div class="mid-padding leadShowcase full-width">
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
												    <div class="duration-time">
													    <div id="total_duration_${song.id}" class="total-durations" style="">00.00</div>
													 </div>
													 <div class="upper-panel-remaining-durations-card remaining-time" style="">
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
										                    <a  id="${song.id}" class="leadShowcase download-link-card word-slicer" href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download onclick="handleDownload(${song.id})">
										                        <c:out value="Download ${song.trackTitle}"/>
										                        <!-- Hidden Form for Metrics -->
													            <form id="metricsForm-download-${song.id}" class="metricsForm-download" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST" style="display:none;">
													                <input type="hidden" name="playCount" value="0">
														          	<input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
													                <input type="hidden" name="reposts" value="0">
													                <input type="hidden" name="downloads" value="1">
													                <input type="hidden" name="user_id" value="${loggedInUser.id}">
													                <input type="hidden" name="song_id" value="${song.id}">
												   					<input type="submit" value="Download ${song.trackTitle}">
													            </form>   
														        <form id="metricsForm-pause-${song.id}" class="metricsForm-pause" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST" style="display:none;">
																     <input type="hidden" name="playCount" value="0">
															         <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
															         <input type="hidden" name="reposts" value="0">
															         <input type="hidden" name="downloads" value="0">
															         <input type="hidden" name="user_id" value="${loggedInUser.id}">
															         <input type="hidden" name="song_id" value="${song.id}">
														   	         <input type="submit" value="Download ${song.trackTitle}">
															    </form>
															    <form id="metricsForm-seek-slider-${song.id}" class="metricsForm-seek-slider-" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST" style="display:none;">
																     <input type="hidden" name="playCount" value="0">
															         <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
															         <input type="hidden" name="reposts" value="0">
															         <input type="hidden" name="downloads" value="0">
															         <input type="hidden" name="user_id" value="${loggedInUser.id}">
															         <input type="hidden" name="song_id" value="${song.id}">
														   			 <input type="submit" value="Download ${song.trackTitle}">
															     </form>
										                    </a>
													     </p>
													</c:if>
												</div>
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
									        <div class="media-buttons leadShowcase mediaShowcase sm-vert-margin sm-vert-padding" style="font-size:12px;"
									        onmouseout="this.style.transition = 'background-image 1s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}'"
			      							onmouseover="this.style.transition = 'background-image 1s ease-in'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'">
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
									        <c:if test="${not empty playlistTitleError}">
												<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
													<c:out value ="${playlistTitleError}"/>
												</p>
											</c:if>
											<c:if test="${loggedInUser.id == song.user.id}">	
									 		<form class="editForm leadShowcase mid-vert-margin " action="/melodydreams/editTrack/${song.id}" id="${song.id}" method="GET" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="GET">
											    <input id="${song.id}" class ="edit-input point-border-radius lrg-padding panel-card" type="submit" value="Edit ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form>	

											<!-- Play-list Forms -->
											<p class="show-playlist leadShowcase sm-vert-margin" onclick="showPlaylistForm()">
												<c:out value="Add Song To Playlist"/>
        										<i class="fa fa-chevron-down playlist-chevron-icon"></i>
											</p>
											<!-- Form to create a new playlist and add the song to it >
											<form id="newPlaylistForm-${song.id}" class="playlist-form playerCover hidePanel" action="/melodydreams/process/createNewPlaylist/${song.id}" method="POST" style="" modelAttribute="playlist">
											    <input class="playlist-input leadShowcase full-width sm-vert-margin" name="title" placeholder="Title Of Your Playlist">
											    <textarea class="leadShowcase full-width sm-vert-margin" name="description" placeholder="Description"></textarea>
											    <input type="hidden" name="user_id" value="${loggedInUser.id}">
											    <input class="leadShowcase" type="submit" value="Create New Playlist" onclick="handlePlaylistForm()">
											</form>
											
											<!-- Form to add the song to an existing playlist >
											<form id="addSongToPlaylistForm-${song.id}" class="playlist-form playerCover hidePanel" action="/melodydreams/process/createNewPlaylistSong/${song.id}" method="POST" style="" modelAttribute="playlistSong">
											    <select name="playlist_id" class="panel-card lead track-creation-input full-width round-border-radius sm-vert-margin" style="cursor:pointer">
											        <option value="" class="leadShowcase text-primary sm-vert-margin mid-padding">Select Playlist</option>
											        <c:forEach items="${playlists}" var="playlist">
											           <option value="${playlist.id}" class="leadShowcase text-primary sm-vert-margin mid-padding" style="cursor:pointer;">${playlist.title}</option>
											        </c:forEach>
											    </select>
											    <input type="hidden" name="user_id" value="${loggedInUser.id}">
											    <input class="leadShowcase" type="submit" value="Add Song To Playlist" onclick="handlePlaylistForm()">
											</form-->
				        						 <c:choose>
											        <c:when test="${song.playlistSongs.size() < 1}">	
													<form id="playlistForm-${song.id}" class="playlist-form playerCover hidePanel" action="/melodydreams/process/createNewPlaylist/${song.id }" method="POST" style="" modelAttribute="playlist">
												            <input class="playlist-input leadShowcase full-width sm-vert-margin" name="title" placeholder="Title Of Your Play List">
												            <textarea class="leadShowcase full-width sm-vert-margin" name="description" placeholder="Description"></textarea>
										           		<input type="hidden" name="user_id" value="${loggedInUser.id}">
												   		<input class="leadShowcase" type="submit" value="Create New Playlist" onclick="handlePlaylisForm()">
									            	</form>
											        </c:when>
											        <c:otherwise>    
													<form id="playlistForm-${song.id}" class="playlist-form playerCover hidePanel" action="/melodydreams/process/createNewPlaylist/${song.id }" method="POST" style="" modelAttribute="playlistSong">
											            <select name="song_id" class="panel-card lead track-creation-input full-width round-border-radius sm-vert-margin" style="cursor:pointer">
											                <option value="" class="leadShowcase text-primary sm-vert-margin mid-padding">Select Playlist</option>
											                <c:forEach items="${song.playlistSongs}" var="track">
											                   <option value="${track.playlist.id}" class="leadShowcase text-primary sm-vert-margin mid-padding" style="cursor:pointer;">${track.playlist.title}</option>
											                </c:forEach>
											            </select>
										           		<input type="hidden" name="song_id" value="${song.id}">
										           		<input type="hidden" name="user_id" value="${loggedInUser.id}">
												   		<input class="leadShowcase" type="submit" value="Add Song To Playlist" onclick="handlePlaylisForm()">
											        </c:otherwise>
											     </c:choose>

								            <!-- Social Actions Button -->
											<p class="show-stats leadShowcase sm-vert-margin" onclick="showMetricsForm()">
											    <c:out value="Show Track Metrics "/>
											    <i class="fa fa-chevron-down metrics-chevron-icon"></i>
											</p>

											<!-- Social Actions Forms -->
											<div id="socialActionsPanel-${song.id}" class="social-actions-panel leadShowcase full-width">
											    <!-- Like Song Form -->
											    <div id="trackStatId-${song.id}" class="track-stat leadShowcase full-width hidePanel">
											        <div class="">
												        <form id="likeSongForm-${song.id}" class="like-song-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/process/createNewLikedSong/${song.id}" method="POST">
												            <input type="hidden" name="song_id" value="${song.id}">
												            <input type="hidden" name="user_id" value="${loggedInUser.id}">
												            <!-- i class="fa fa-solid fa-heart"></i-->
												            <input class="leadShowcase input-slicer" type="submit" value="Like ${song.trackTitle}">
												        </form>
												
												        <!-- Unlike Song Form --><!-- Unlike Song Form -->
														<c:forEach items="${song.likedSongs}" var="likedSong">
														    <c:if test="${loggedInUser.id == likedSong.likingUserId}">
														        <form id="unlikeSongForm-${likedSong.id}" class="unlike-song-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/deleteLikedSong/${likedSong.id}" method="POST">
														            <input type="hidden" name="song_id" value="${likedSong.song.id}">
														            <input type="hidden" name="user_id" value="${loggedInUser.id}">
														            <input type="hidden" name="_method" value="DELETE">
														            <button class="leadShowcase input-slicer" type="submit">Unlike ${likedSong.song.trackTitle}</button>
														        </form>
														    </c:if>
														</c:forEach>

													</div>


											
											        <!-- Like User Form -->
											        <div class="flex-btw">
												        <form id="likeUserForm-${song.id}" class="like-user-form playerCover leadShowcase mid-margin full-width" action="/melodydreams/process/createNewLikedUser/${song.user.id}" method="POST">
												            <input type="hidden" name="song_id" value="${song.id}">
												            <input type="hidden" name="user_id" value="${loggedInUser.id}">
												            <input class="leadShowcase input-slicer" type="submit" value="Like ${song.trackArtist}">
												        </form>
												
												        <!-- Unlike User Form -->
												        <c:forEach items="${song.user.likedUsers}" var="likedUser">
														    <c:if test="${loggedInUser.id == likedUser.user.id}">
														        <form id="unlikeUserForm-${likedUser.id}" class="unlike-user-form playerCover leadShowcase mid-margin full-width" action="/melodydreams/deleteFollowedUser/${followedUser.id}" method="POST">
														            <input type="hidden" name="song_id" value="${song.id}">
														            <input type="hidden" name="user_id" value="${loggedInUser.id}">
														            <input type="hidden" name="_method" value="DELETE">
														            <button class="leadShowcase input-slicer" type="submit">Unlike ${likedUser.user.firstName}</button>
														        </form>
														    </c:if>
														 </c:forEach>
													</div>

										        	<!-- Unfollow User Form -->
											      	<c:forEach items="${song.user.followedUsers}" var="followedUser">
													    <c:if test="${loggedInUser.id == followedUser.user.id}">
													        <form id="unfollowUserForm-${followedUser.id}" class="unfollow-user-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/deleteFollowedUser/${followedUser.id}" method="POST">
													            <input type="hidden" name="song_id" value="${song.id}">
													            <input type="hidden" name="user_id" value="${loggedInUser.id}">
													            <input type="hidden" name="_method" value="DELETE">
													            <button class="bright-colors leadShowcase input-slicer" type="submit">Unfollow ${followedUser.user.firstName}</button>
													        </form>
													    </c:if>
													</c:forEach>

											    </div>
											    <!-- Follow User Form -->
											    <form id="followUserForm-${song.id}" class="follow-user-form playerCover mid-vert-margin full-width hidePanel" action="/melodydreams/process/createNewFollowedUser/${song.user.id}" method="POST">
											        <input type="hidden" name="followingUserId" value="${loggedInUser.id}">
											        <input type="hidden" name="user_id" value="${loggedInUser.id}">
											        <input class="leadShowcase input-slicer" type="submit" value="Follow ${song.trackArtist}">
											    </form>

											</div>

											<!-- Delete Track Prompts -->
											<p class="show-delete delete-prompt leadShowcase sm-vert-margin" onclick="showDeleteForm()">
												<c:out value="Delete Track"/>
        										<i class="fa fa-chevron-down chevron-icon"></i>
											</p>
									 		<form id="delete-${song.id}" class="deleteForm upper-delete-form leadShowcase mid-vert-margin hidePanel" action="/melodydreams/deleteTrack/${song.id}" method="post" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="DELETE">
											    <input class ="delete-input point-border-radius lrg-padding panel-card" id="${song.id}" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form> 
											</c:if>
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
						  <div class="leadShowcase columnCover media-showcase " style="">		
							  <!-- Track Status -->
							  <a href="/melodydreams/artists/${loggedInUser.id}"  class ="track-status hidePanel mid-fonts">
								</a>
							  <input type="range" min="1" max="100" value="0" class="seek_slider main_bottom_seek_slider mid-vert-margin" onchange="seekTo(this)" style="width:100%;">
						      <div class="main-media-buttons imageCover playerCover media-buttons leadShowcase" 
						      	onmouseover="this.style.transition = 'background-image 1.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			      			  	onmouseout="changePlayerCover(this)"style="">
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
			 </div>
	 
	  		<!-- Middle Section Display Panel -->
	  		<div class="profileShowcase lrg-vert-margin playerCover" 		    
	    	style="border-radius:5%;" id="section-player-cover">
		  	<!-- <h1>Section Music Player</h1> -->
			      <div class="flexCover playerCover" style="">
				      <div class="playerCover  lead profileShowcase details mid-margin-right" 
				      		onmouseover="this.style.transition = 'background-image 1.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			  				onclick="playpauseTrack(this)" onmouseout="changePlayerCover(this)"
					   		style="border-radius:5%;">
					   		
					        <div class="now-playing" style="width:100%;text-align:center">
					        </div>				       	

						    <div class="playerCover audioLink leadShowcase">
							    <div class="imageCover track-art"></div>
							</div> 
							
							<!-- Mini Panel Display -->
					        <div class="leadShowcase flexCover">
								 <!-- Track Details -->
								 <div class="playerCover bluebtn sm-hor-margin" style="">
						        	  <div class="leadShowcase">
							        		<div class="leadShowcase track-name sm-padding sm-fonts artist-link-card panel-card" style="cursor:pointer;">
							        		</div>
							        		<div class="play-count sm-padding sm-fonts sm-vert-margin artist-link-card panel-card bright-colors" style="cursor:pointer;">
							        			<c:out value=""/>
							        		</div>
							          </div>
							          <div class="playerCover" style="">
							          		<div class="textShowcase track-artist sm-fonts" style="">
							          		</div>
							          </div>
						          </div>
	
						          <!-- Left Mini Slider Panel -->
						          <div class="leadShowcase columnCover" style="">
								       <div class="playerCover bluebtn slider_container  flexCover mid-padding sm-vert-margin" style="">
					  					   <input id="main-slider" type="range" min="1" max="100" value="0" class="seek_slider left-mini-player-slider" onchange="seekTo(this)" style="width:100%;text-align:center">
								       </div>
									   <div class="playerCover bluebtn slider_container  panel-info mid-padding" style="">
									      	<div class=" panel-remaining-durations-card leadShowcase mid-margin-right" style="font-size:10px;">
									      	 	<c:out value="Time Remaining"></c:out>
									      	 	<div class="remaining_duration" style="font-size:10px;">00.00
									      	 	</div>
									        </div>
									   	 	<div class="total-duration leadShowcase" style="font-size:10px;">00.00
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
					  <div class="leadShowcase over-flow imageCover playerCover" id="" style="">
						<c:forEach items="${singleSongList}" var="song">							 
							<div id=${song.id } class="section-div-slicer playerCover imageCover x-lrg-margin-bottom mid-padding-bottom round-border-radius" onmouseout="changePlayerCover(this)">	
						    
								<div class="leadShowcase round-border-radius imageCover" style="
				        			background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
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
									<input type="hidden" class="songId" value="${song.id}"/>
									
								       	<div class="leadShowcase circle-dark-overlay imageCover full-width" id ="section-play-panel-${song.id}" style="
				        				background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName})
				        				" id ="${song.id}"
				        				>
									       	<c:choose>
											    <c:when test="${playCounts[song.id] == 0}">
											        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
											            <c:out value="${song.trackTitle} By ${song.user.firstName}: Not Yet Played!"/>
											        </a>
											    </c:when>
											    <c:when test="${playCounts[song.id] == 1}">
											        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
											            <c:out value="${song.trackTitle} By ${song.user.firstName}: Played ${playCounts[song.id]} Once"/>
											        </a>
											    </c:when>
											    <c:otherwise>
											        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
											            <c:out value="${song.trackTitle} By ${song.user.firstName} Played ${playCounts[song.id]} Times"/>
											        </a>
											    </c:otherwise>
											</c:choose>
								       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
							       		
									      	<div  id="${song.id}" class="lead flexCover track-panel id-play-panel" style="">
											    <div id="${song.id}" class="flex-panel bright-colors panel-card" style=""  onclick="panelPlaypauseTrack(this)">
											        <div class="flex-panel sm-hor-margin section-outer-panel-player-cover" style="">
												        <c:out value="Play ${play} ${song.trackTitle}"/>
												        <span id="id-play-panel-${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
												        <span id="${song.id}" class="bright-colors upper-panel-play-btn">
								        					<i id="${song.id}" class="track-art-widget fa fa-play-circle fa-5x"></i>
													   </span>
								        				 <i class="sm-fonts type-writer word-slicer">
													       	<c:choose>
															    <c:when test="${playCounts[song.id] == 0}">
								        							<c:out value="Not Played Yet"/>
															    </c:when>
															    <c:when test="${playCounts[song.id] == 1}">
								        							<c:out value="Played ${playCounts[song.id]} once"/>
															    </c:when>
															    <c:otherwise>
								        							<c:out value="Played ${playCounts[song.id]} times"/>
								        						</c:otherwise>
								        					</c:choose>
								        					</i><!-- Span to hold the play/pause button -->
								                        <!-- Hidden Form for Metrics -->
											            <form id="metricsForm-play-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
											                <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										   					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
											            </form>
											            <form id="metricsForm-pause-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
											                <input type= "range"  min="1" max="100" value="100" name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
												        </form>
											            <form id="metricsForm-seek-slider-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
											                <input type= "range"  min="1" max="100" value="100" name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
												        </form>
									 		   		 </div>
											      </div>
										 	</div>
										 	<div class="lead flexCover non-id-play-panel bright-colors playerCover hidePanel" style="display:none" id="${song.id}"  onclick="playpauseTrack(this)">
											    <div id="non-id-play-panel-${song.id}" class="flex-panel panel-car" style="">
											        <div id="${song.id}" class="flex-panel sm-hor-margin pause-panel" style="">
												        <c:out value="Play ${song.trackTitle}"/>
												        <span id="${song.id}" class=""></span> <!-- Span to hold the play/pause icon -->
												        <span id="${song.id}" class="bright-colors panelPlayPauseBtn ">
								        					<i id="${song.id}"  class="track-art-widget fa fa-play-circle fa-5x"			        	
								        					style="background-image:url(${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
												        	background-repeat:no-repeat;background-position: center;background-size:cover;"				
												  	 		onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'" 
												  			onmouseout="changePlayerCover(this)"></i>
													       	<i class="sm-fonts type-writer word-slicer">
													       	<c:choose>
															    <c:when test="${playCounts[song.id] == 0}">
								        							<c:out value="Not Played Yet"/>
															    </c:when>
															    <c:when test="${playCounts[song.id] == 1}">
								        							<c:out value="Played ${playCounts[song.id]} once"/>
															    </c:when>
															    <c:otherwise>
								        							<c:out value="Played ${playCounts[song.id]} times"/>
								        						</c:otherwise>
								        					</c:choose>
								        					</i>
								        				</span> <!-- Span to hold the play/pause button -->
								                        <!-- Hidden Form for Metrics -->
											            <form id="metricsForm-play-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
											                <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
											            </form>
											            <form id="metricsForm-pause-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
											                <input type= "range"  min="1" max="100" value="100" name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
												        </form>
											            <form id="metricsForm-seek-slider-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
											                <input type="hidden" name="playCount" value="1">
											                <input type= "range"  min="1" max="100" value="100" name="seekSliderValue" class="seek_slider" value="0" style="display:none">
											                <input type="hidden" name="reposts" value="0">
											                <input type="hidden" name="downloads" value="0">
											                <input type="hidden" name="user_id" value="${loggedInUser.id}">
											                <input type="hidden" name="song_id" value="${song.id}">
										 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
												        </form>
										 	   		 </div>
											     </div>
						       		</div>

								    <!-- Panel Media Area -->
								    <div class="mid-padding leadShowcase full-width">
									  	<!-- Section Upper Panel Display -->
									  	<div class="mini-panel-info" style="cursor:pointer;">
										   <div class="current-time prev-track" id="${song.id}" style=""> 00.00</div>
										   <div class="panel-total-durations" id="${song.id}" onclick="playpauseTrack(this)" style="font-size:12px;">00.00</div>
										   <div class="panel-remaining-durations next-track" id="${song.id}" >00.00</div>
									  	</div>
										  
									      <!-- Section Panel Slider Display -->
										  <!-- input id ="${song.id}" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(${JSON.stringify(song.id)})" style="width:100%;text-align:center; margin:5px 0;"-->
										  <input id ="sectionSlider_${song.id}" type="range" min="1" max="100" value="0" class="seek_slider hidePanel sectionSlider" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
										   <div class="leadShowcase slider_container sm-fonts" style="">
										      	<c:out value="Volume Controls "/>
											    <div class="duration-time">
												    <div id="total_duration_${song.id}" class="total-durations" style="">00.00</div>
												 </div>
												 <div class="remaining-time">
												    <span>Time Remaining: </span>
												    <div id="${song.id}" class="remaining_durations" style="">00.00</div>
												 </div>
										        <input type="range" min="1" max="100" value="100" class="volume_slider" onchange="setVolume(this)" style="">
										    </div>
						
											<!-- Section Artist Info -->
											<c:if test="${loggedInUser.id == song.user.id}">
											    <p class="mini-panel-info bluebtn" style="align-items:center;font-size:12px;">
											    	<a class="artist-link-card music-link-card" id="${song.id}"  href="/melodydreams/${song.id}" style="">
												    	<c:out value="${song.genre} Music From ${song.user.firstName}"/>
											        </a>
											        <!--Download Track -->
										            <a  id="${song.id}" class="leadShowcase download-link-card word-slicer" href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download onclick="handleDownload(${JSON.stringify(song.id)}})">
										                <c:out value="Download ${song.trackTitle}"/>
										                <!-- Hidden Form for Metrics -->
													    <form id="metricsForm-download-${song.id}" class="metricsForm-download" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST" style="display:none;">
														      <input type="hidden" name="playCount" value="0">
													          <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
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
										<input type="hidden" class="trackTitle" value="${song.trackTitle}" />
										<input type="hidden" class="trackArtist" value="${song.trackArtist}" />
										<input type="hidden" class="trackImageSrc" value="${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}" />
										<input type="hidden" class="audioSrc" value="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" />
										
					
										<audio class="audioPlayer" id="audioPlayer">
										    <source src="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" type="audio/mpeg">
										    <c:out value="Your browser does not support the audio element."/>
										</audio>
										<div class ="section-media-panel hidePanel" id="${song.id}">
									        <div class="media-buttons imageCoverleadShowcase mediaShowcase sm-vert-margin" 
									        onmouseover="this.style.transition = 'background-image 1.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
									        onmouseover="changePlayerCover(this)"
									        style="font-size:12px;">
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
									        <c:if test="${not empty playlistTitleError}">
												<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
													<c:out value ="${playlistTitleError}"/>
												</p>
											</c:if>
											<c:if test="${loggedInUser.id == song.user.id}">	
									 		<form class="editForm leadShowcase mid-vert-margin " action="/melodydreams/editTrack/${song.id}" id="${song.id}" method="GET" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="GET">
											    <input id="${song.id}" class ="edit-input point-border-radius lrg-padding panel-card" type="submit" value="Edit ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form>	

											<!-- Play-list Forms -->
											<p class="show-section-playlist leadShowcase sm-vert-margin" onclick="showPlaylistForm()">
												<c:out value="Add Song To Playlist"/>
        										<i class="fa fa-chevron-down section-playlist-chevron-icon"></i>
											</p>
											<form id="playlistForm-${song.id}" class="section-playlist-form playerCover hidePanel" action="/melodydreams/process/createNewPlaylist/${song.id }" method="POST" style="" modelAttribute="playlist">
				        						 <c:choose>
											        <c:when test="${loggedInUser.playlists.size() < 1}">	
											            <input class="playlist-input leadShowcase full-width sm-vert-margin" name="title" placeholder="Title Of Your Play List">
											            <textarea class="leadShowcase full-width sm-vert-margin" name="description" placeholder="Description"></textarea>
											        </c:when>
											        <c:otherwise>    
											            <select name="title" class="panel-card lead track-creation-input full-width round-border-radius sm-vert-margin" style="cursor:pointer">
											                <option value="" class="leadShowcase text-primary sm-vert-margin mid-padding">Select Playlist</option>
											                <c:forEach items="${loggedInUser.playlists}" var="playlist">
											                   <option value="${playlist.title}" class="leadShowcase text-primary sm-vert-margin mid-padding" style="cursor:pointer;">${playlist.title}</option>
											                </c:forEach>
											            </select>
											            <textarea class="leadShowcase full-width sm-vert-margin" name="description" placeholder="Description"></textarea>
											        </c:otherwise>
											     </c:choose>
									            <input type="hidden" name="user_id" value="${loggedInUser.id}">
											   	<input class="leadShowcase" type="submit" value="Add Playlist">
								            </form>

								            <!-- Social Actions Button -->
											<p class="show-section-stats leadShowcase sm-vert-margin" onclick="showMetricsForm()">
											    <c:out value="Show Track Metrics "/>
											    <i class="fa fa-chevron-down section-metrics-chevron-icon"></i>
											</p>
											
											<!-- Social Actions Forms -->
											<div id="socialActionsPanel-${song.id}" class="social-actions-panel leadShowcase full-width">
											    <!-- Like Song Form -->
											    <div id="trackStatId-${song.id}" class="section-track-stat leadShowcase full-width hidePanel">
												   <div class="">
												        <form id="likeSongForm-${song.id}" class="like-song-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/process/createNewLikedSong/${song.id}" method="POST">
												            <input type="hidden" name="song_id" value="${song.id}">
												            <input type="hidden" name="user_id" value="${loggedInUser.id}">
												            <!-- i class="fa fa-solid fa-heart"></i-->
												            <input class="leadShowcase input-slicer" type="submit" value="Like ${song.trackTitle}">
												        </form>
												
												        <!-- Unlike Song Form --><!-- Unlike Song Form -->
														<c:forEach items="${song.likedSongs}" var="likedSong">
														    <c:if test="${loggedInUser.id == likedSong.likingUserId}">
														        <form id="unlikeSongForm-${likedSong.id}" class="unlike-song-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/deleteLikedSong/${likedSong.id}" method="POST">
														            <input type="hidden" name="song_id" value="${likedSong.song.id}">
														            <input type="hidden" name="user_id" value="${loggedInUser.id}">
														            <input type="hidden" name="_method" value="DELETE">
														            <button class="bright-flash leadShowcase input-slicer" type="submit">Unlike ${likedSong.song.trackTitle}</button>
														        </form>
														    </c:if>
														</c:forEach>

													</div>


											
											        <!-- Like User Form -->
											        <div class="flex-btw">
												        <form id="likeUserForm-${song.id}" class="like-user-form playerCover leadShowcase mid-margin full-width" action="/melodydreams/process/createNewLikedUser/${song.user.id}" method="POST">
												            <input type="hidden" name="song_id" value="${song.id}">
												            <input type="hidden" name="user_id" value="${loggedInUser.id}">
												            <input class="leadShowcase input-slicer" type="submit" value="Like ${song.trackArtist}">
												        </form>
												
												        <!-- Unlike User Form -->
												        <c:forEach items="${song.user.likedUsers}" var="likedUser">
														    <c:if test="${loggedInUser.id == likedUser.user.id}">
														        <form id="unlikeUserForm-${likedUser.id}" class="unlike-user-form playerCover leadShowcase mid-margin full-width" action="/melodydreams/deleteFollowedUser/${followedUser.id}" method="POST">
														            <input type="hidden" name="song_id" value="${song.id}">
														            <input type="hidden" name="user_id" value="${loggedInUser.id}">
														            <input type="hidden" name="_method" value="DELETE">
														            <button class="leadShowcase input-slicer" type="submit">Unlike ${likedUser.user.firstName}</button>
														        </form>
														    </c:if>
														 </c:forEach>
													</div>

										        	<!-- Unfollow User Form -->
											      	<c:forEach items="${song.user.followedUsers}" var="followedUser">
													    <c:if test="${loggedInUser.id == followedUser.user.id}">
													        <form id="unfollowUserForm-${followedUser.id}" class="unfollow-user-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/deleteFollowedUser/${followedUser.id}" method="POST">
													            <input type="hidden" name="song_id" value="${song.id}">
													            <input type="hidden" name="user_id" value="${loggedInUser.id}">
													            <input type="hidden" name="_method" value="DELETE">
													            <button class="bright-colors leadShowcase input-slicer" type="submit">Unfollow ${followedUser.user.firstName}</button>
													        </form>
													    </c:if>
													</c:forEach>

											    </div>
											    <!-- Follow User Form -->
											    <form id="followUserForm-${song.id}" class="follow-user-form playerCover mid-vert-margin full-width hidePanel" action="/melodydreams/process/createNewFollowedUser/${song.user.id}" method="POST">
											        <input type="hidden" name="followingUserId" value="${loggedInUser.id}">
											        <input type="hidden" name="user_id" value="${loggedInUser.id}">
											        <input class="leadShowcase input-slicer" type="submit" value="Follow ${song.trackArtist}">
											    </form>
											</div>

											<!-- Delete Track Prompts -->
											<p class="show-section-delete delete-prompt leadShowcase sm-vert-margin" onclick="showDeleteForm()">
												<c:out value="Delete Track"/>
        										<i class="fa fa-chevron-down section-delete-chevron-icon"></i>
											</p>
									 		<form id="section-delete-${song.id}" class="deleteForm section-delete-form leadShowcase mid-vert-margin hidePanel" action="/melodydreams/deleteTrack/${song.id}" method="post" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="DELETE">
											    <input class ="delete-input  point-border-radius lrg-padding panel-card" id="${song.id}" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form> 
											</c:if>
										</div>
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
			      <div class="media-buttons imageCover playerCover leadShowcase mediaShowcase" 
			      	onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			      	onmouseout="changePlayerCover(this)"
			      	style="">
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

			 
		</div>
			
  		<!-- Footer Upload and Logout Panel -->
		<p id="playCounts" data-play-count-list="${playCountsData}" style="display: none;">
			${playCounts}
		</p>

		<p id="musicListData" data-musiclist="${singleMusicList}" style="display: none;">
			${singleMusicList}
		</p>

		<p id="singleSongListData" data-musiclist="${singleSongList}" style="display: none;">
			${singleSongList}
		</p>
			
		<p id="loggedUser" data-musiclist="${loggedInUser.id}" style="display: none;">
			${loggedInUser.id}
		</p>

  		<!-- Footer Playlist Panel -->
		<div id="footer-player-cover" class="profileShowcase flexBox playerCover x-lrg-padding imageCover"	
		onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'" 
		onmouseout="changePlayerCover(this)" style="">
			<div class="profileShowcase imageCover over-flow"
			onmouseover="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
			onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/jorik-kleen-Nio0W6TWkaU-unsplash.jpg)'"
			onmouseout="this.style.transition = 'background-image 0.5s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/anthony-delanoix-hzgs56Ze49s-unsplash.jpg)'" 
			id="" style="">
			<c:forEach items="${singleSongList}" var="song">
			  	<div id ="${song.id}" class="footer-div-slicer imageCover mid-vert-margin round-border-radius" style="">
				<input type="hidden" class="songId" value="${song.id}"/>
				
				  	<!--Footer Title Display -->
					<div id="${song.id}" class=" circle-dark-overlay titleCover leadShowcase" style="
			        background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
			   		background-repeat: no-repeat;background-position: center;background-size: cover;
		        	" id ="${song.id}">
				       	<c:choose>
						    <c:when test="${playCounts[song.id] == 0}">
						        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
						            <c:out value="${song.trackTitle} By ${song.user.firstName}: Not Yet Played!"/>
						        </a>
						    </c:when>
						    <c:when test="${playCounts[song.id] == 1}">
						        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="leadShowcase word-slicer" style="">
						            <c:out value="${song.trackTitle} By ${song.user.firstName}: Played ${playCounts[song.id]} Once"/>
						        </a>
						    </c:when>
						    <c:otherwise>
						        <a href="/melodydreams/tracks/${song.id}" id="${song.id}" class="bright-flash leadShowcase word-slicer" style="">
						            <c:out value="${song.trackTitle} By ${song.user.firstName} Played ${playCounts[song.id]} Times"/>
						        </a>
						    </c:otherwise>
						</c:choose>
				  	</div>
				  	<!--Footer Play Display -->
					<div class="" style="">
						<div style="
			        	background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
			   			background-repeat: no-repeat;background-position: center;background-size: cover; 
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
				       		
					      	<div  id="id-play-panel-${song.id}" class="lead flexCover bright-colors track-panel id-play-panel" style="">
							    <div id="${song.id}" class="flex-panel panel-card full-width" style=""  onclick="panelPlaypauseTrack(this)">
							        <div class="flex-panel sm-hor-margin footer-outer-panel-player-cover" style="">
								        <span class="leadShowcase rotate spining-car">
								        	<c:out value="Play ${song.trackTitle}"/>
								        </span>
							        <span id="span-id-play-panel-${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
								        <span id="${song.id}" class="spreader bright-flash upper-panel-play-btn">
				        					<i id="${song.id}"  class="footer-inner-panel-player-cover track-art-widget fa fa-play-circle fa-5x">
				        					</i>
				        				</span> 
			        				<span class="leadShowcase">
								       	<i class="mid-fonts type-writer word-slicer">
									       	<c:choose>
											    <c:when test="${playCounts[song.id] == 0}">
				        							<c:out value="Not Played Yet"/>
											    </c:when>
											    <c:when test="${playCounts[song.id] == 1}">
				        							<c:out value="Played ${playCounts[song.id]} once"/>
											    </c:when>
											    <c:otherwise>
				        							<c:out value="Played ${playCounts[song.id]} times"/>
				        						</c:otherwise>
				        					</c:choose>
			        					</i>
			        				</span><!-- Span to hold the play/pause button -->
								                        <!-- Hidden Form for Metrics -->
						            <form id="metricsForm-play-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
						                <input type="hidden" name="playCount" value="1">
						                <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
						                <input type="hidden" name="reposts" value="0">
						                <input type="hidden" name="downloads" value="0">
						                <input type="hidden" name="user_id" value="${loggedInUser.id}">
						                <input type="hidden" name="song_id" value="${song.id}">
					   					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
						            </form>
				 		   		 </div>
						      </div>
					 	</div>
					 	<div id="non-id-play-panel-${song.id}" class="lead flexCover non-id-play-panel bright-colors hidePanel" style="display:none" onclick="playpauseTrack(this)">
						    <div class="flex-panel panel-car playerCover " style="">
						        <div id="${song.id}" class="flex-panel sm-hor-margin pause-panel" style="">
						        <c:out value="Play ${song.trackTitle}"/>
							        <span id="${song.id}" class="spreader "></span> <!-- Span to hold the play/pause icon -->
							        <span id="${song.id}" class="bright-colors footer-panel-play-btn playerCover">
			        					<i id="${song.id}"  class="playerCover upper-track-art-widget fa fa-play-circle fa-5x"></i>
									    <i class="sm-fonts type-writer word-slicer">
									       	<c:choose>
											    <c:when test="${playCounts[song.id] == 0}">
				        							<c:out value="Not Played Yet"/>
												</c:when>
												<c:when test="${playCounts[song.id] == 1}">
				        							<c:out value="Played ${playCounts[song.id]} once"/>
											    </c:when>
											    <c:otherwise>
				        							<c:out value="Played ${playCounts[song.id]} times"/>
				        						</c:otherwise>
				        					</c:choose>
			        					</i>
				        				</span> <!-- Span to hold the play/pause button -->
				                        <!-- Hidden Form for Metrics -->
							            <form id="metricsForm-play-${song.id}" action="/melodydreams/process/createNewMetrics/${song.id}" method="POST">
						                <input type="hidden" name="playCount" value="1">
										    <input type= "range"  min="1" max="100" value="100"  name="seekSliderValue" class="seek_slider" value="0" style="display:none">
							                <input type="hidden" name="reposts" value="0">
							                <input type="hidden" name="downloads" value="0">
							                <input type="hidden" name="user_id" value="${loggedInUser.id}">
							                <input type="hidden" name="song_id" value="${song.id}">
						 					<input type="hidden" type="submit" value="Play ${song.trackTitle}">
							            </form>
						 	   		 </div>
							     </div>
						   </div>
					<div id="${song.id}" class="footer-links flexCover leadShowase sm-vert-margin hidePanel" style="background:rgba(1.12, 11, 11, 0.9);">
						
						<div class="profileShowcase flex-column playerCover mid-round-margin bright-colors" id="${song.id}" style="">
							<a href="/melodydreams/newTrack" class="panel-card track-upload a-tag-links" id="${song.id}" style="">
								<c:out value="UPLOAD NEW TRACK"/>
							</a>
							<a href="/melodydreams/logout" class="panel-card a-tag-links" id="${song.id}" style="">
								<c:out value="LOGOUT HERE"/>
							</a>
				 			<a style="" href="/melodydreams/artists/${loggedInUser.id }" class="panel-card a-tag-links">
				 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
				 			</a>
					 	</div>
						
						<div class="profileShowcase flex-column playerCover sm-round-margin" id="${song.id}" style="">
							<a href="/melodydreams/artists" class ="track-status hidePanel">
							</a>
					
					  	<form class="trackListLink" action="/melodydreams/tracks" method="get" style="">
						    <input type="hidden" name="_method" value="GET">
						    <input class ="panel-card playlist-link" type="submit" value="PLAYLIST PANEL" style="" >
						</form>
											  								  
						<div class ="track-description hidePanel" style="" >
						</div>
							
					</div>
				</div>	
											 	
	       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
					       		
				<p class="panel-card leadShowcae mid-vert-padding mid-vert-margin word-slicer" onclick="playpauseTrack(this)" style="">
					<c:out value="${song.trackTitle}: ${song.description}"/> 	
						<c:choose>
						    <c:when test="${playCounts[song.id] == 0}">
					    		<c:out value="Not Played Yet"/>
						    </c:when>
						    <c:when test="${playCounts[song.id] == 1}">
					    		<c:out value="Played ${playCounts[song.id]} once"/>
						    </c:when>
						    <c:otherwise>
					    		<c:out value="Played ${playCounts[song.id]} times"/>
					    	</c:otherwise>
					    </c:choose>
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
					        <div class="media-buttons imageCover leadShowcase media-showcase mid-vert-margin"  
					        onmouseover="this.style.transition = 'background-image 0.9s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			      			onmouseout="this.style.transition = 'background-image 1.2s ease-in-out'; this.style.backgroundImage='url(${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName})'"
					        style="">
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

						    <c:if test="${not empty playlistTitleError}">
								<p class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin">
									<c:out value ="${playlistTitleError}"/>
								</p>
							</c:if>
							<c:if test="${loggedInUser.id == song.user.id}">	
							<div class="leadShowcase mid-padding">
						 		<form class="editForm leadShowcase mid-vert-margin " action="/melodydreams/editTrack/${song.id}" id="${song.id}" method="GET" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
								    <input type="hidden" name="_method" value="GET">
								    <input id="${song.id}" class ="edit-input point-border-radius lrg-padding panel-card" type="submit" value="Edit ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
								</form>	

								<!-- Play-list Forms -->
								<p class="show-footer-playlist leadShowcase sm-vert-margin" onclick="showPlaylistForm()">
									<c:out value="Add Song To Playlist"/>
   										<i class="fa fa-chevron-down footer-playlist-chevron-icon"></i>
								</p>
								<form id="playlistForm-${song.id}" class="footer-playlist-form playerCover hidePanel" action="/melodydreams/process/createNewPlaylist/${song.id }" method="POST" style="" modelAttribute="playlist">
	        						 <c:choose>
								        <c:when test="${loggedInUser.playlists.size() < 1}">	
								            <input class="playlist-input leadShowcase full-width sm-vert-margin" name="title" placeholder="Title Of Your Play List">
								            <textarea class="leadShowcase full-width sm-vert-margin" name="description" placeholder="Description"></textarea>
								        </c:when>
							        <c:otherwise>    
								            <select name="title" class="panel-card lead track-creation-input full-width round-border-radius sm-vert-margin" style="cursor:pointer">
								                <option value="" class="leadShowcase text-primary sm-vert-margin mid-padding">Select Playlist</option>
								                <c:forEach items="${loggedInUser.playlists}" var="playlist">
								                   <option value="${playlist.title}" class="leadShowcase text-primary sm-vert-margin mid-padding" style="cursor:pointer;">${playlist.title}</option>
								                </c:forEach>
								            </select>
								            <textarea class="leadShowcase full-width sm-vert-margin" name="description" placeholder="Description"></textarea>
							        </c:otherwise>
								     </c:choose>
							            <input type="hidden" name="user_id" value="${loggedInUser.id}">
									   	<input class="leadShowcase input-slicer" type="submit" value="Add Playlist">
					            </form>

								            <!-- Social Actions Button -->
											<p class="show-footer-stats leadShowcase sm-vert-margin" onclick="showMetricsForm()">
											    <c:out value="Show Track Metrics "/>
											    <i class="fa fa-chevron-down footer-metrics-chevron-icon"></i>
											</p>
											
											<!-- Social Actions Forms -->
											<div id="socialActionsPanel-${song.id}" class="social-actions-panel leadShowcase bluebtn">
											    <!-- Like Song Form -->
											    <div id="trackStatId-${song.id}" class="footer-track-stat leadShowcase full-width hidePanel">
													<div class="">
												        <form id="likeSongForm-${song.id}" class="like-song-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/process/createNewLikedSong/${song.id}" method="POST">
												            <input type="hidden" name="song_id" value="${song.id}">
												            <input type="hidden" name="user_id" value="${loggedInUser.id}">
												            <!-- i class="fa fa-solid fa-heart"></i-->
												            <input class="leadShowcase input-slicer" type="submit" value="Like ${song.trackTitle}">
												        </form>
												
												        <!-- Unlike Song Form --><!-- Unlike Song Form -->
														<c:forEach items="${song.likedSongs}" var="likedSong">
														    <c:if test="${loggedInUser.id == likedSong.likingUserId}">
														        <form id="unlikeSongForm-${likedSong.id}" class="unlike-song-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/deleteLikedSong/${likedSong.id}" method="POST">
														            <input type="hidden" name="song_id" value="${likedSong.song.id}">
														            <input type="hidden" name="user_id" value="${loggedInUser.id}">
														            <input type="hidden" name="_method" value="DELETE">
														            <button class="bright-flash leadShowcase input-slicer" type="submit">Unlike ${likedSong.song.trackTitle}</button>
														        </form>
														    </c:if>
														</c:forEach>

													</div>


											
											        <!-- Like User Form -->
											        <div class="flex-btw">
												        <form id="likeUserForm-${song.id}" class="like-user-form playerCover leadShowcase mid-margin full-width" action="/melodydreams/process/createNewLikedUser/${song.user.id}" method="POST">
												            <input type="hidden" name="song_id" value="${song.id}">
												            <input type="hidden" name="user_id" value="${loggedInUser.id}">
												            <input class="leadShowcase input-slicer" type="submit" value="Like ${song.trackArtist}">
												        </form>
												
												        <!-- Unlike User Form -->
												        <c:forEach items="${song.user.likedUsers}" var="likedUser">
														    <c:if test="${loggedInUser.id == likedUser.user.id}">
														        <form id="unlikeUserForm-${likedUser.id}" class="unlike-user-form playerCover leadShowcase mid-margin full-width" action="/melodydreams/deleteFollowedUser/${followedUser.id}" method="POST">
														            <input type="hidden" name="song_id" value="${song.id}">
														            <input type="hidden" name="user_id" value="${loggedInUser.id}">
														            <input type="hidden" name="_method" value="DELETE">
														            <button class="leadShowcase input-slicer" type="submit">Unlike ${likedUser.user.firstName}</button>
														        </form>
														    </c:if>
														 </c:forEach>
													</div>

										        	<!-- Unfollow User Form -->
											      	<c:forEach items="${song.user.followedUsers}" var="followedUser">
													    <c:if test="${loggedInUser.id == followedUser.user.id}">
													        <form id="unfollowUserForm-${followedUser.id}" class="unfollow-user-form playerCover leadShowcase mid-vert-margin full-width" action="/melodydreams/deleteFollowedUser/${followedUser.id}" method="POST">
													            <input type="hidden" name="song_id" value="${song.id}">
													            <input type="hidden" name="user_id" value="${loggedInUser.id}">
													            <input type="hidden" name="_method" value="DELETE">
													            <button class="bright-colors leadShowcase input-slicer" type="submit">Unfollow ${followedUser.user.firstName}</button>
													        </form>
													    </c:if>
													</c:forEach>

											    </div>
											    <!-- Follow User Form -->
											    <form id="followUserForm-${song.id}" class="follow-user-form playerCover mid-vert-margin full-width hidePanel" action="/melodydreams/process/createNewFollowedUser/${song.user.id}" method="POST">
											        <input type="hidden" name="followingUserId" value="${loggedInUser.id}">
											        <input type="hidden" name="user_id" value="${loggedInUser.id}">
											        <input class="leadShowcase input-slicer" type="submit" value="Follow ${song.trackArtist}">
											    </form>
											</div>

											<!-- Delete Track Prompts -->
											<p class="show-footer-delete delete-prompt leadShowcase sm-vert-margin" onclick="showDeleteForm()">
												<c:out value="Delete Track"/>
        										<i class="fa fa-chevron-down chevron-icon"></i>
											</p>
									 		<form id="footer-delete-${song.id}" class="deleteForm footer-delete-form leadShowcase mid-vert-margin hidePanel" action="/melodydreams/deleteTrack/${song.id}" method="post" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
											    <input type="hidden" name="_method" value="DELETE">
											    <input class ="delete-input  point-border-radius lrg-padding panel-card" id="${song.id}" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
											</form> 
										</div>
										</c:if>
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
</div>	

<link rel="stylesheet" href="/styles/main_player.css"/>
<link rel="stylesheet" href="/styles/privateStyles.css"/>
<script>
    const formattedSongs = [
       <c:forEach items="${singleSongList}" var="song" varStatus="loop">
            {
            	music: "${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}",
                img: "${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName}",
                artist: "${song.trackArtist}",
                name: "${song.trackTitle}"
            }
            <c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    function handlePlay(songId) {
        document.getElementById(`metricsForm-${songId}-play`).submit();
        const audioPlayer = document.getElementById(`audioPlayer-${songId}`);
        if (audioPlayer.paused) {
            audioPlayer.play();
        } else {
            audioPlayer.pause();
        }
    }

	//const formattedMusicMapList = formattedSongs; 

    // Now you can use formattedSongs in your player.js file
    //console.log(formattedSongs);


    const musicListMap = ${singleMusicList};
    const deleteTrackLink = 'songs/deleteTrack/';
    const viewTrackLink = 'tracks/';
    const playCounts = `${playCounts}`;
</script>
<!--  -->
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>
</html>