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
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Melody Dreams!</title>
</head>
<body class="container-fluid p-8" style="background:rgba(110.2, 100.4, 336.6, 0.9);font-family:cursive;">
   	<nav id="navbar"  
    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	    <ul class="navbar-list navShowcase" style="padding:5px;">
	        <li class="purple-circle-container lead" 
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
			 	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
	            <a class="leadShowcase" href="/melodydreams/tracks" style="">
	            	<c:out value="Cloud Melody Dreams"/>
	            </a>
	        </li>
	        <li class="purple-circle-container" style="">
			    <form action="/melodydreams/artists/${loggedInUser.id}" class="bluebtn" style="display:flex;flex-wrap:wrap;justify-content:space-between;align-items:center;text-align:center; padding:5px;background:rgba(1.33, 50.64, 70.60, 0.9);border-radius:7%;width:100%;">    
			        <input style="border-radius:7%;margin:5px" type="text" name="searchedArtistName" placeHolder="Enter Artist Name"/>
			        <input class="btn btn-outline-primary" type="submit" value="Search Artist" style="margin:5px;"/>
			    </form>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Artists Hub"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase bluebtn"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/tracks/${mostRecentSong.id}" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="Most Recent Track"/>
					    </a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/artists" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="View All Artists"/>
					   	</a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/favoriteArtists" style="background: rgba(9, 6, 53, 0.466);">Favorite Artists</a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/artists/${loggedInUser.id}" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="My Profile"/>
					   	</a>
					</li>
	            </ul>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Playlists Hub"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase bluebtn"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/usersPlaylists" style="background: rgba(9, 6, 53, 0.466);">
					   		<c:out value="My Playlists"/>
					   	</a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
				   		<a class="bluebtn" href="/melodydreams/favoritePlaylists" style="background: rgba(9, 6, 53, 0.466);">
				   			<c:out value="Favorite Playlists"/>
				   		</a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
				   		<a class="bluebtn" href="/melodydreams/favoriteSongs" style="background: rgba(9, 6, 53, 0.466);">
				   			<c:out value="Favorite Songs"/>
				   		</a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/albumns" style="background: rgba(9, 6, 153, 0.466);">
					   		<c:out value="Veiw Albums"/>
					   	</a>
					</li>
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
					   	<a class="bluebtn" href="/melodydreams/favoriteAlbums" style="background: rgba(9, 6, 153, 0.466);">
					   		<c:out value="Favorite Albums"/>
					   	</a>
					</li>
	            </ul>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Album Panel"/> 
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="leadShowcase" style="
			        	background-image:url( ${pageContext.request.contextPath}/FileUploads/${onePatient.patientImageUploads[0].imageDataUrl}/${onePatient.patientImageUploads[0].imageFileName} ); 
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;"
			        	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'">
	                		<a class="bluebtn" href="/melodydreams/albums" style="background: rgba(9, 6, 13, 0.466);">
	                			<c:out value="All Albums"/>
	                		</a>
	                        <c:out value="${allTrackList.size()} Today"/>
	                        
			        </li> 
	                <li class="navShowcase"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
					   	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                		<a class="bluebtn" href="/melodydreams/userPlaylists/${loggedInUser.id}" style="background: rgba(9, 6, 13, 0.466);">
	                			<c:out value="My Albums"/>
	                		</a>
	                        <c:out value="${loggedInUser.albums.size()} New"/>
	                        
	                </li>	
                    <li class="navShowcase" 
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
			        	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                 	<a class="bluebtn" href="/melodydreams/albums" style="background: rgba(9, 6, 13, 0.466);">
	                		<c:out value="Favorite Albums"/>
	                	</a>
	                    <c:out value="${loggedInUser.favoriteAlbums.size()} New"/>
	                </li>
                    <li class="navShowcase" 
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="
			        	background: url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif);background-repeat: no-repeat;background-position: center;background-size: cover;
			        	background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                    <a class="bluebtn" href="/melodydreams/albums/newAlbum" style="background: rgba(9, 6, 13, 0.466);">
	                        <c:out value="Add New Album"/>
	                    </a>
	                </li>
	            </ul>
	        </li>
	        
	        <li class="navShowcase">
	            <c:out value="Track Console Panel"/>
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase"
				    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
				 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
				   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                	<a class="bluebtn" href="/melodydreams/artists/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Update Profile"/>
	                	</a>
	                </li>
	                <li class="navShowcase"
				    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
				 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
				   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                	<a class="bluebtn" href="/melodydreams/tracks/newTrack" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Upload New Track"/>
	                	</a>
	                </li>
	                <li class="navShowcase"
				    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
				 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
				   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                	<a class="bluebtn" href="/melodydreams/userImageDairy/newUserImageDairy" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
	                		<c:out value="Upload New Image"/>
	                	</a>
	                </li>
	            </ul>
	        </li>

	        <li class="navShowcase">
	            <c:out value="Mellow Profile"/>
	            <i class="fas fa-angle-down"></i>
	            <ul class="navShowcase column-card"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;
			   	background-image:url( ${pageContext.request.contextPath}/FileUploads/${onePatient.patientImageUploads[0].imageDataUrl}/${onePatient.patientImageUploads[0].imageFileName} );
	            background: url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg);background-repeat: no-repeat;background-position: center;background-size: cover;">
			        <li class="leadShowcase" style="
			        	background-image:url( ${pageContext.request.contextPath}/FileUploads/${onePatient.patientImageUploads[0].imageDataUrl}/${onePatient.patientImageUploads[0].imageFileName} ); 
			        	background: url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg);background-repeat: no-repeat;background-position: center;background-size: cover;"  	
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'">
			            <a class="bluebtn btn btn-outline-success" href="/melodydreams/artist/${loggedInUser.id}" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
			                <c:out value="${loggedInUser.firstName} Artist Access"/>
			            </a>
			        </li>
			        <li class="leadShowcase" style="
			        	background-image:url( ${pageContext.request.contextPath}/FileUploads/${onePatient.patientImageUploads[0].imageDataUrl}/${onePatient.patientImageUploads[0].imageFileName} ); 
			        	background: url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg);background-repeat: no-repeat;background-position: center;background-size: cover;"
			        	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'">
			            <a class="bluebtn btn btn-outline-warning" href="/melodydreams/userAddresses/newUserAddress" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
			                <c:out value="Update Contact Details"/>
			            </a>
			        </li>
			        <li class="leadShowcase" 
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'"
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;
			        	background-image:url( ${pageContext.request.contextPath}/FileUploads/${onePatient.patientImageUploads[0].imageDataUrl}/${onePatient.patientImageUploads[0].imageFileName} ); 
			        	background: url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg);background-repeat: no-repeat;background-position: center;background-size: cover;"
			        	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/samuel-regan-asante-3BcNKoySAq0-unsplash.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/photo-1492684223066-81342ee5ff30.avif)'">
			            <a class="bluebtn btn btn-outline-danger" href="/melodydreams/logout" style="background:rgba(30.1, 0.2, 0.9, 0.6);">
			                <c:out value="Console Log Out"/>
			            </a>
			        </li>
	            </ul>
	        </li>  
	    </ul>
	</nav>
	<div class="" style="">
	<h1 style="text-align:center;border-bottom: 2px solid chocolate;color: brown; font-family:fantasy;background:rgba(10.2, 3.3, 3.6, 0.9);margin-top:5px;border-radius:5%;">
		<a href="/melodydreams/artists/${loggedInUser.id}" style=" margin: 0 15px 0 0; display:block; padding: 10px;color: khaki;text-decoration:none;font-size:28px;">
			<c:out value="Welcome To Cloud Melody Dreams ${loggedInUser.firstName}!"/>
		</a>
	</h1>
		      <!-- <h1>Music Player</h1> -->
			<div class="profileShowcase" 
		    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
		 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
		   	style="background-repeat: no-repeat;background-position: center;background-size: cover;width:100%;display:flex;flex-direction:column;justify-content:space-between;align-items:center;border-radius:5%;">
		      <div class="leadShowcase" style="position:fixe;width:100%;display:flex;justify-content:space-between;">
			      <div class="lead profileShowcase details" 
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
					 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;display:flex;justify-content:space-between;width:100%; margin:0 15px;border-radius:5%;">
				        <div class="now-playing" style="width:100%;text-align:center"></div>
				       	
				       	<a class="audioLink" href="">
						    <div class="track-art"></div>
						</a> 
				        <div class='leadShowcase' style="display:flex;justify-content:space-between;align-items:center;text-align:center;width:100%;">
							<div class="bluebtn btn btn-outline-success" style="align-items:center;width:100%;text-align:center;margin:0 15px;padding:10px;cursor:pointer;background:rgba(1.33, 10.64, 0.60, 0.9);">
					        	<div class="leadShowcase">
						        	<div class='track-name leadShowcase'  onclick="playpauseTrack(this)" style="cursor:pointer;width:100%;text-align:center;background:rgba(0,0,0,0.7);font-size:12px;">
						        	</div>
						        </div>
						        <div class='track-artist leadShowcase' onclick="playpauseTrack(this)" style="cursor:pointer;width:100%;text-align:center;font-size:12px;">
						        </div>
					        </div>
					        <div class='leadShowcase ' style="display:flex;flex-direction:column;justify-content:space-between;align-items:center;text-align:center;width:100%;">
							      	<div class="bluebtn slider_container btn btn-outline-success" style="display:flex;justify-content:space-between;align-items:center;width:100%;text-align:center;margin:5px 0;padding:10px;cursor:pointer;background:rgba(1.33, 10.64, 0.60, 0.9);">
				  						<input id="main-slider" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(this)" style="width:100%;text-align:center">
							      	</div>
								    <div class="bluebtn slider_container btn btn-outline-success" style="display:flex;justify-content:space-between;align-items:center;width:100%;text-align:center;margin:5px 0;padding:10px;cursor:pointer;background:rgba(1.33, 10.64, 0.60, 0.9);">

								      	 <div style="display:flex;">
								      	 	<div class="total-duration" onclick="playpauseTrack(this)" style="margin:0 5px;font-size:12px;">00.00</div>
								      	 </div>
								      	 <div style="display:flex;flex-direction:column;font-size:12px;">
								      	 	<c:out value="Time Remaining"></c:out><div class="remaining_duration" onclick="playpauseTrack()" style="margin:0 5px;font-size:12px;">00.00</div>
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
				  <div class="profileShowcase" id="" style="overflow-y:auto; max-height:500px;border-radius:5%;margin:0 5px;padding:5px;width:100%;">
					<c:forEach items="${allTrackList}" var="song">
				  	<div class="leadShowcase bluebtn" id="table table-dark " style="text-align:center;border-radius:5%;margin:5px 0;">
						 
						<div class="leadShowcase">	
						<tr style="margin:5px 0;display:flex;flex-direction:column;padding:15px;background:rgba(2.11, 0.03, 1, 0.9);border-radius:5%">
						<div class="leadShowcase" id="">
					    
							<td style="">
								<input type="hidden" class="songId" value="${song.id}"/>
								<input type="hidden" id="musicListData2" value="${musicList}"/>
							  	
								<div class="" style="
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
							       		<div class="lead circle-dark-overlay" style="
			        						font-size:16px;margin:5px 0;" id ="${song.id}">
											<a href="/melodydreams/tracks/${song.id}" id ="${song.id}" class="mini-panel-info leadShowcase" style="width:100%;font-size:16px;">
												<c:out value="${song.trackTitle} By ${song.trackArtist}"/>
											</a> 
							       		
								       		<!-- h3 id ="${song.id}" style="font-size:16px;" onclick="detailedPlaypauseTrack(${JSON.stringify(song.id)})" -->
								       		<div class="panelPlayPauseBtn" id="${song.id}">
										       	<div class="lead panelPlaypauseBtn" style="margin:5px 0;" id="${song.id}" onclick="panelPlaypauseTrack(this)">
												    <div id="${song.id}" class="playpause-Track flex-panel btn btn-outline-warning panel-card" style="font-size:16px;padding:10px 0;width:100%;">
												        <p class="flex-panel" style="margin:5px;">
													        <c:out value="Play ${song.trackTitle}"/>
													        <span id="${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
													        <span id="${song.id}" class="playpauseTrack panelPlaypause_btn" onclick="playpauseTrack(this)">
									        					<i class="fa fa-play-circle fa-5x"></i>
									        				</span> <!-- Span to hold the play/pause button -->
												   		</p>
												    </div>
												  </div>
											    </div>	
			    id_seek_slider[track_index].classList.remove('hidePanel');	
			    panel_seek_slider[track_index].classList.remove('hidePanel');
									       		<!-- -<div class="playPanel" id="playPanel${song.id}">
												    <div class="lead panelPlaypauseBtn" style="margin:5px 0;" id="${song.id}" onclick="panelPlaypauseTrack(this)">
												        <div id="${song.id}" class="playpause-Track flex-panel btn btn-outline-warning panel-card" style="font-size:16px;padding:10px 0;width:100%;">
												            <p class="flex-panel" style="margin:5px;">
												                <c:out value="Play ${song.trackTitle}"/>
												                <span id="${song.id}" class="playpause-icon"></span> <!-- Span to hold the play/pause icon >
												                <span id="${song.id}" class="playpauseTrack panelPlaypause_btn" onclick="playpauseTrack(this)">
												                    <i class="fa fa-play-circle fa-5x"></i>
												                </span> <!-- Span to hold the play/pause button>
												            </p>
												        </div>
												    </div>
												</div-->

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
								  <input id ="${song.id}" type="range" min="1" max="100" value="0" class="id_seek_slider hidePanel" onchange="songSeekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
								   <div class="leadShowcase slider_container btn btn-outline-success panel-info" style="">
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
				
									<!-- Artist Info -->
									<c:if test="${loggedInUser.id == song.user.id}">
									    <p class="mini-panel-info bluebtn" style="align-items:center;font-size:12px;">
									    	<a href="/melodydreams/${song.id}" style="">
									    		<c:out value="Artist: ${song.trackArtist}- ${song.genre}"/>
									        </a>
									        <!--Download Track -->
									        <a href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download>
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
									<c:if test="${loggedInUser.id == song.user.id}">	
							 		<form class="deleteForm" action="/melodydreams/songs/deleteTrack/${song.id}" method="post" style="padding:10px;border-radius:5%;background:rgba(1.33, 10.64, 0.60, 0.9);">
									    <input type="hidden" name="_method" value="DELETE">
									    <input class ="btn btn-outline-danger panel-card" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
									</form>
									</c:if>
									
								</td>
						 	</div>
						</tr>
						</div>
					</div> 
				</c:forEach> 
				</div>
			  </div>
		      
			  <div class="leadShowcase" style="display:flex;justify-content:space-between;align-items:center;width:100%;padding:2px;margin:5px 0;font-size:12px;cursor:pointer;">
				   <div class="current-time prev-track" id="allCurrentTime"> 00.00</div>
				   <div class="total-duration" onclick="playpauseTrack(this)">00.00</div>
				   <div class="remaining_duration next-track">00.00</div>
			  </div>
			  <div class="bluebtn" style="align-items:center;width:100%;padding:2px;margin:5px 0;font-size:12px;">
				  <input type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
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
		         
		      <div class="slider_container btn btn-outline-success" style="display:flex;justify-content:space-between;align-items:center;width:100%;text-align:center;margin:5px 0;padding:10px;cursor:pointer;background:rgba(1.33, 10.64, 0.60, 0.9);">
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
	  
	  		<div class="leadShowcase lg-vert-margin" 
		    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
		 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
	    	style="background-repeat: no-repeat;background-position: center;background-size: cover;border-radius:5%;">
		  		<!-- Artist Upload and Logout Panel -->
		  		<div class="profileShowcase lg-vert-margin ">
					<div class="lg-vert-margin flexbtn form-group" style="background:rgba(1.12, 11, 11, 0.9);">
						<h1 class="" style="margin:5px;width:100%;">
							<a href="/melodydreams/newTrack" class="btn btn-warning panel-card" style="margin:5px;width:100%;display:block;padding:10px;font-weight:bold;color:rgba(12.11, 2.03, 11, 0.9);">
								<c:out value="UPLOAD NEW TRACK"/>
							</a>
						</h1>
						<h1 style="margin:5px;width:100%;">
							<a href="/melodydreams/logout" class="btn btn-outline-danger panel-card" id="" style="margin:5px;width:100%;display:block;padding:10px;background:rgba(2.11, 0.03, 1, 0.9);font-weight:bold;">
								<c:out value="LOGOUT HERE"/>
							</a>
						</h1>
				 		<h1 style="margin:5px;width:100%;">
				 			<a style="background:rgba(6.8, 8, 0.8, 0.9);margin:5px;width:100%; display:block; padding: 10px" href="/melodydreams/artists/${loggedInUser.id }" class="btn btn-outline-primary panel-card">
				 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
				 			</a>
				 		</h1>
					</div>	
		
					<div class="profileShowcase" id="" style="padding:10px;background:rgba(2.11, 0.03, 1, 0.9)">
					  <div class="btn btn-outline-warning panel-card">
					  	<form class="trackListLinklg-vert-margin " action="/melodydreams/tracks" method="get" style="padding:10px;border-radius:5%;background:rgba(1.33, 10.64, 0.60, 0.9);">
						    <input type="hidden" name="_method" value="GET">
						    <input class ="btn btn-outline-danger panel-card" type="submit" value="Playlists" style="width: 100%;padding:7px;width:100%;" >
						</form>
					   </div>
					</div>
				</div>
			</div>
			
  		<!-- Footer Upload and Logout Panel -->
		<p id="musicListData" data-musiclist="${userMusicList}" style="display: none;">
			${userMusicList}
		</p>

		<p id="allTrackListData" data-musiclist="${allTrackList}" style="display: none;">
			${allTrackList}
		</p>
			
		<p id="loggedUser" data-musiclist="${loggedInUser.id}" style="display: none;">
			${loggedInUser.id}
		</p>

		<div class="profileShowcase flexBox" >
  		<!-- Footer Playlist Panel -->
		<div class="profileShowcase" id="" style="overflow-y:auto; max-height:500px;">
		<c:forEach items="${allTrackList}" var="song">
		<input type="hidden" class="songId" value="${song.id}"/>
		<input type="hidden" id="musicListData2" value="${musicList}"/>
		  	<div class="table table-dark " style="text-align:center;border-radius:5%;">
				<tbody>	
					<div class="lead" style="border-radius:5%;">
					  	<!-- Title Display -->
						<div class=" circle-dark-overlay" style="
				        background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
				   		background-repeat: no-repeat;background-position: center;background-size: cover;padding:5px;border-radius:5%;
			        	font-size:16px;margin:5px 0;" id ="${song.id}">
		            	    <a href="/melodydreams/tracks/${song.id}" id ="${song.id}" class="mini-panel-info leadShowcase" style="width:100%;font-size:16px;">
								<c:out value="${song.trackTitle} By ${song.trackArtist}"/>
							</a> 
					  	</div>
					  	<!-- Play Display -->
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
					       		<div class="panelPlayPauseBtn" id="${song.id}">
							       	<div class="lead panelPlaypause_btn" style="margin:5px 0;" id="${song.id}" onclick="panelPlaypauseTrack(this)">
									    <div id="${song.id}" class="playpause-Track flex-panel btn btn-outline-warning panel-card" style="font-size:16px;padding:10px 0;width:100%;">
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
					       		<h3 style="font-size:16px;" onclick="panelPlaypauseTrack(this)">
									<p id ="${song.id}" class="btn btn-outline-warning panel-card" onclick="detailedPlaypauseTrack(this)" style="width:100%;display:block;padding:10px 0; font-weight:bold;">
										<c:out value="${song.trackTitle}: ${song.description}"/>
									</p>
					       		</h3>
				       	   </div>
				       	   
				       	   <!-- Panel Duration Details -->
						   <div class="leadShowcase bluebtn" style="display:flex;justify-content:space-between;align-items:center;width:100%;padding:2px;margin:10px 0;font-size:12px;cursor:pointer;">
							   <div class="current-time prev-track" id="${song.id}"> 00.00</div>
							   <div class="panel-total-durations display-duration-info" id="total_duration_${song.id}" onclick="playpauseTrack(${JSON.stringify(song.id)})">00.00</div>
							   <div class="panel-remaining-durations next-track" id="${song.id}" >00.00</div>
						   </div>
				       	   
				       	   <!-- Slider Controls -->
						   <!-- div class="flexbtn profileShowcase bluebtn" style="
				        background-image:url( ${pageContext.request.contextPath}/FileUploads/${song.trackImageDataUrl}/${song.trackImageFileName});
				   		background-repeat: no-repeat;background-position: center;background-size: cover;"-->   
						   <div class="flexbtn profileShowcase bluebtn panel-card" style="">   
							  	<!-- input id ="${song.id}" type="range" min="1" max="100" value="0" class="seek_slider" onchange="seekTo(${JSON.stringify(song.id)})" style="width:100%;text-align:center; margin:5px 0;"-->
							  	<input id ="${song.id}" type="range" min="1" max="100" value="0" class="panel_seek_slider" onchange="songSeekTo(this)" style="width:100%;text-align:center; margin:5px 0;">
							  	<div class="slider_container btn btn-outline-success" style="">
							      	<c:out value="Volume Controls "/>
							     	<div style="display:flex;">
								    	<div id="total_duration_${song.id}" class="total-durations" style="margin:0 5px">00.00</div>
								 	</div>
									<div style="display:flex;">
									    <span>Time Remaining: </span>
									    <div id="remaining_duration_${song.id}" class="remaining_durations" style="margin:0 5px">00.00</div>
									</div>
							        <input type="range" min="1" max="100" value="100" class="volume_slider" onchange="setVolume(this)" style="text-align:center;cursor:pointer">
							    </div>
	
								<c:if test="${loggedInUser.id == song.user.id}">
							    <div class="footer-flex-panel">
							    	<c:out value="Artist: ${song.trackArtist} - ${song.genre} Music"/>
							        <!--Download Track -->
							        <a href="${pageContext.request.contextPath}/FileUploads/${song.audioDataUrl}/${song.audioFileName}" download>
							        	<c:out value="Download ${song.trackTitle}"/>
							        </a>
						        </div>
						        </c:if>
					        </div>
					        </div>	
					
							<c:if test="${loggedInUser.id == song.user.id}">	
					 		<form class="deleteForm" action="/melodydreams/songs/deleteTrack/${song.id}" method="post" style="padding:10px;border-radius:5%;background:rgba(1.33, 10.64, 0.60, 0.9);">
							    <input type="hidden" name="_method" value="DELETE">
							    <input class ="btn btn-outline-danger panel-card" type="submit" value="Delete Your ${song.trackTitle} Track" style="width: 100%;padding:7px;width:100%;" >
							</form>
							</c:if>
					  </div>
				</tbody>
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
  
  		<!-- Playlist Info Side Panel -->				
        <div class="lead profileShowcase" 
	    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
	 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
    	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;display:flex;justify-content:space-between;width:100%; margin:10px 0;border-radius:5%;">	   
			<div class="flex-panel" id="" style="display:flex;justify-content:space-between;margin:5px 0;padding:10px;background:rgba(2.11, 0.03, 1, 0.9)">
				 <div class="btn btn-outline-warning panel-card" style="width:100%;">
				  	<form class="trackListLink" action="/melodydreams/artists/${loggedInUser.id}" method="get" style="width:100%;padding:10px;border-radius:5%;background:rgba(1.33, 10.64, 0.60, 0.9);">
					    <input type="hidden" name="_method" value="GET">
					    <input class ="btn btn-outline-danger panel-card" type="submit" value="View ${loggedInUser.firstName}'s Tracks" style="width: 100%;padding:7px;" >
					</form>
				 </div>
				 <div class="btn btn-outline-warning panel-card " style="width:100%;">
				  	<form class="trackListLink" action="/melodydreams/tracks" method="get" style="width:100%;padding:10px;border-radius:5%;background:rgba(1.33, 10.64, 0.60, 0.9);">
					    <input type="hidden" name="_method" value="GET">
					    <input class ="btn btn-outline-danger panel-card" type="submit" value="View All Tracks" style="width: 100%;padding:7px;" >
					</form>
				 </div>
			 <div class="profileShowcase" id="" style="background:rgba(2.11, 0.03, 1, 0.9)">
				 <div class="btn btn-outline-warning panel-card" style="width:100%;">
				  	<form class="trackListLink" action="/melodydreams/artists" method="get" style="background:rgba(1.33, 10.64, 0.60, 0.9);">
					    <input type="hidden" name="_method" value="GET">
					    <input class ="btn btn-outline-danger panel-card" type="submit" value="View All Artists Tracks" style="width: 100%;padding:7px;" >
					</form>
				 </div>
			 </div>
			</div>	
		</div>
		</div>
		</div>
				
		<div class="profileShowcase leadShowcase lg-vert-margin">
			<div class="form-group"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
				<h1 style="margin:5px;width:100%;">
					<a href="/melodydreams/newTrack" class="btn btn-warning panel-card" style="margin:5px;width:100%;display:block;padding:10px;font-weight:bold;color:rgba(12.11, 2.03, 11, 0.9);">
						<c:out value="UPLOAD NEW TRACK"/>
					</a>
				</h1>
				<h1 style="margin:5px;width:100%;">
					<a href="/melodydreams/logout" class="btn btn-outline-danger panel-card" style="margin:5px;width:100%;display:block;padding:10px;background:rgba(2.11, 0.03, 1, 0.9);font-weight:bold;">
						<c:out value="LOGOUT HERE"/>
					</a>
				</h1>
		 		<h1 style="margin:5px;width:100%;">
		 			<a style="background:rgba(6.8, 8, 0.8, 0.9);margin:5px;width:100%; display:block; padding: 10px" href="/melodydreams/artists/${loggedInUser.id }" class="btn btn-outline-primary panel-card">
		 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
		 			</a>
		 		</h1>
			 </div>
			 <div class="form-group"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:15px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
				<h1 style="width:100%">
					<a href="/melodydreams/artists" class="btn btn-outline-warning panel-card" style="width:100%;display:block;padding:10px 0; font-weight:bold;">
						<c:out value="ARTIST HUB"/>
					</a>
				</h1>
		</div>
	 </div>

	  		<!-- Upload and Logout Panel -->
			<!-- div class="profileShowcase" id="trackPlayList" style="overflow-y:auto; max-height:500px;border-radius:5%;padding:5px;">
			 </div -->
	<div>
	 <div class="form-group panelDiv"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
			<h1 style="margin:5px;width:100%;">
				<a href="/melodydreams/newTrack" class="btn btn-warning panel-card" style="margin:5px;width:100%;display:block;padding:10px;font-weight:bold;color:rgba(12.11, 2.03, 11, 0.9);">
					<c:out value="UPLOAD NEW TRACK"/>
				</a>
			</h1>
			<h1 style="margin:5px;width:100%;">
				<a href="/melodydreams/logout" class="btn btn-outline-danger panel-card" style="margin:5px;width:100%;display:block;padding:10px;background:rgba(2.11, 0.03, 1, 0.9);font-weight:bold;">
					<c:out value="LOGOUT HERE"/>
				</a>
			</h1>
	 		<h1 style="margin:5px;width:100%;">
	 			<a style="background:rgba(6.8, 8, 0.8, 0.9);margin:5px;width:100%; display:block; padding: 10px" href="/melodydreams/artists/${loggedInUser.id }" class="btn btn-outline-primary panel-card">
	 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
	 			</a>
	 		</h1>
		</div>
		<div class="form-group"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:15px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
			<h1 style="width:100%">
				<a href="/melodydreams/artists" class="btn btn-outline-warning panel-card" style="width:100%;display:block;padding:10px 0; font-weight:bold;">
					<c:out value="ARTIST HUB"/>
				</a>
			</h1>	
		</div>
	</div>
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
    const musicListMap = ${userMusicList};
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
<!--  -->
