<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
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
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>

    <!-- Your own local CSS -->
    <link rel="stylesheet" href="/styles/privateStyles.css"/>
    <link rel="stylesheet" href="/styles/main_player.css"/>

    <!-- Bootstrap JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <!-- Local JS 
	<script src="<c:url value='/scripts.js'/>"></script>-->

    <meta charset="ISO-8859-1">
    <title>Melody Dreams!</title>
</head>
<body class="imageCover" style="font-family: cursive;">
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
			    <form class="playerCover imageCover point-border-radius" action="/melodydreams/artists"style="">    
			        <input class="purple-circle-containe leadShowcase imageCover point-border-radius"  style="" type="text" name="searchedArtist" placeHolder="Enter Artist Name"/>
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
				   		<a class="bluebtn mid-fonts party-lights" href="/melodydreams/artists" style="background: rgba(9, 6, 53, 0.466);">
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
					   	<a class="bluebtn mid-fonts"  href="/melodydreams/userPlaylists" style="background: rgba(9, 6, 13, 0.466);">
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
					   	<a class="bluebtn mid-fonts party-lights" href="/melodydreams/artists" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
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
					   	<a class="bluebtn panel-card mid-fonts word-slicer party-lights" href="/melodydreams/artists" style="background:rgba(0.1, 0.2, 0.9, 0.7);">
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
	<div class="profileShowcase" style="margin:15px;">
    <h1 class="main-greeting-panel text-center border-bottom border-2" style="margin-top:5px;border-radius:5%;background: rgba(0.21, 0.180, 25, 0.9);color: brown; font-family: fantasy;font-size:32px">
        <a class="main-greeting type-writer word-slicer leadShowcase imageCover text-decoration-none text-brown" href="/melodydreams/login" style="font-size-32px;">
           	<c:out value="Melody Dreams Error Dashboard ${currentDateTime}!"/>
        </a>
    </h1>

    <div class="container-fluid p-8 profileShowcase"  style="text-align:center;">
  		<div class="profileShowcase imageCover" style="">
        	<p class="main-greeting type-writer word-slicer leadShowcase btn btn-outline-success" style="margin:15px;text-align:center;align-items:center;">
        		<c:out value="ErrorCode"/>
		        <a class="inner-column-card" href="/melodydreams/artists"  style="">
	        		<span class="leadShowcase bright-flash"><c:choose>
		        		<c:when test="">
				        	<c:out value="${statusCode} Page Not Found Error!"/>
		        		</c:when>
		        		<c:otherwise>
				        	<c:out value="${statusCode} Internal Server Error!"/>
				        </c:otherwise>
			        </c:choose>
			        </span>
	            	<div class="imageCover columnCover btn btn-outline-primary " style="display:flex;flex-wrap:wrap;justify-content:center;align-items:center;text-align:center;margin:10px 0; padding:10px;background:rgba(1.33, 0.64, 0.60, 0.9);">
			            <c:out value="Error Message: The Page You are Looking For Does Not Exist"/>
		           		<div class="leadShowcase full-wide btn btn-outline-primary" style="display:flex;flex-direction:column;justify-content:center;text-align:center;margin:5px; padding:10px;background:rgba(1.33, 10.64, 0.60, 0.9);">
			        		<c:out value="Use The Links Provided In Links-Panel Bellow To Renavigate, ${dayCurrentDateTime}"/>
			            </div>
			            <c:out value="Error Details: ${errorMessage} Today, ${currentDateTime}"/>
		            </div>
					<div class="btn btn-outline-success imageCover flexCover" style="padding:15px;">
						<div class="btn btn-outline-success inner-generic-display-container flexCover" style="padding:10px;background:rgba(1.33, 0.64, 0.60, 0.9);background:rgba(13.33, 0.64, 0.60, 0.9);">
		        			<form action="/melodydreams/artists" method="get" style="margin:5px;width:100%;">
							    <input type="hidden" name="_method" value="get">
							    <input class ="btn btn-outline-success" type="submit" value="Mellow Artist Portal" style="width:100%;background:rgba(13.33, 0.64, 0.60, 0.9);" >
							</form>
				    		<form action="/melodydreams/register" method="get" style="margin:5px;width:100%;">
							    <input type="hidden" name="_method" value="get">
							    <input class ="btn btn-outline-warning" type="submit" value="Create New Mellow Account" style="width:100%;background:rgba(13.33, 0.64, 0.60, 0.9);" >
							</form>
		        			<form action="/melodydreams/tracks" method="get" style="margin:5px;width:100%;">
							    <input type="hidden" name="_method" value="get">
							    <input class ="btn btn-outline-danger" type="submit" value="Mellow Tracks Portal" style="width:100%;background:rgba(13.33, 0.64, 0.60, 0.9);" >
							</form>
			        	</div>
		       		</div>
				</a>
			</p>
  		</div>
  	</div>
</div>
<!--  -->
<link rel="stylesheet" href="/styles/privateStyles.css"/>
<link rel="stylesheet" href="/styles/main_player.css"/>
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>

</body>
</html>