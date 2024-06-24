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
<body class="guest-login container-fluid p-6" style="background:aliceblue;font-family:cursive;">

   	<nav id="navbar" class="playerCover imageCover navbar" 
	onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
   	style=""
   	onmouseout="changePlayerCover(this)" >
	    <ul class="navbar-list navbar" style="">
	        <li class="purple-circle-container lead imageCover" 
    			onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	style="background-repeat: no-repeat;background-position: center;background-size: cover;">
	            <a class="bluebtn leadShowcase main-logo playerCover imageCover" href="/melodydreams/artists" style="">
	            	<c:out value="Cloud Melody Dreams"/>
	            </a>
	        </li>
	        <li class="panel-card point-border-radius sm-padding" style="">
			    <form class="playerCover imageCover point-border-radius" action="/melodydreams/artists"style="">    
			        <input class="purple-circle-containe leadShowcase imageCover point-border-radius"  style="" type="text" name="searchedArtistName" placeHolder="Enter Artist Name"/>
			        <input class="leadShowcase panel-card playerCover imageCover point-border-radius" type="submit" value="Search Artist" style=""/>
			    </form>
	        </li>
	        <li class="navShowcase">
	            <c:out value="Already Have An Account?"/> 
	            <i class="fas fa-angle-down"></i> 
	            <ul class="navShowcase"
			    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/landing-img.jpg)'"
			 	onmouseout="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
			   	style="background-repeat: no-repeat;background-position: center;background-size: cover;position:fixe;">
	                <li class="navShowcase playerCover imageCover"
					    onmouseover="this.style.backgroundImage='url(${pageContext.request.contextPath}/img/purplecrowd-unsplash.jpg)'"
					 	onmouseout="changePlayerCover(this)" 
					   	style=";background-repeat: no-repeat;background-position: center;background-size: cover;">
					   	<a class="bluebtn panel-card mid-fonts"  href="/melodydreams/login" style="background: rgba(9, 6, 13, 0.466);">
	                			
	                    <i class ="word-slicer mid-fonts ">
	                    	<c:out value="Click Here To Login?"/>
	                    </i>
	                	</a>
			        </li> 
	            </ul>
	        </li> 
	    </ul>
	</nav>	
	
	<!--Main Title Display
	<h1  class="main-greeting-panel" style="">
		<a class="main-greeting word-slicer type-writer" href="/melodydreams/login" style="">
			<c:out value="Welcome To Cloud Melody Dreams!"/>
		</a>
	</h1>	 -->

	<div class="profileShowcase imageCover mid-vert-margin">						      	
	<div  id="0" class="lead flexCover track-panel id-play-panel panelPlayPauseBtn" style="">
	    <div id="0" class="flex-panel bright-colors imageCover panel-card full-width" style="">
	        <div class="flex-panel sm-hor-margin" style="">
		        <c:out value="Share Your Music With Fans All Over The World!"/>
		        <span id="id-play-panel-0" class="playpause-icon"></span> <!-- Span to hold the play/pause icon -->
	        	<span id="" class="upper-panel-play-btn">
	    			<i id=""  class="upper-track-art-widget fa fa-play-circle fa-5x"></i>
	    		</span>	
				<h5 class="word-slicer type-writer lrg-fonts" style="">
					<c:out value="Please Fill In The Form To Make Changes To Your Profile"/>
				</h5> 					      
			 </div> 
        </div>
 	</div>
	<form:form class="profileShowcase lrg-hor-padding" action="/melodydreams/process/register" method="post" modelAttribute="user">
		<div class="profileShowcase playerCover imageCover bright-cover over-flow">
		    <div class="form-group party-lights leadShowcase mid-vert-margin">
		        <label style="text-align:center;">First Name</label>
		        <form:input path="firstName" class="form-control track-creation-input round-border-radius" placeholder="Please enter first name"/>
		        <form:errors path="firstName" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
		    </div>

		    <div class="form-group party-lights leadShowcase mid-margin-bottom">
		        <label style="text-align:center;">Last Name</label>
		        <form:input path="lastName" class="form-control track-creation-input round-border-radius" placeholder="Please enter last name"/>
		        <form:errors path="lastName" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
		    </div>
				    
		  	<div class="form-group party-lights leadShowcase mid-margin-bottom">
		        <label style="text-align:center;width:100%;">Email</label>
		        <form:input path="email" class="form-control track-creation-input round-border-radius" placeholder="Please Enter Email Address!" style=""/>
		        <form:errors path="email" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
		    </div>

		    <div class="form-group party-lights leadShowcase mid-margin-bottom">
		        <label style="text-align:center;width:100%;">Password</label>
		        <form:input type="password" path="password" class="form-control track-creation-input round-border-radius" placeholder="Please Enter Password!" style=""/>
		        <form:errors path="password" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin text-danger"/>
		    </div>
 
		    <div class="form-group leadShowcase party-lights">
		        <label style="padding:5px 0;">Confirm Password</label>
		        <form:input path="confirmPassword" value="${user.password}" class="form-control track-creation-input round-border-radius" placeholder="Please Confirm Password!" />
		        <form:errors path="confirmPassword" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin text-danger" />
		    </div>

		    <div class="form-group leadShowcase party-lights mid-margin-bottom"">
		        <label style="padding:5px 0;">Date Of Birth</label>
			    <div class="form-group">
			        <form:errors path="dateOfBirth" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin text-danger" />
			    </div>
		        <form:input type="date" path="dateOfBirth" style="cursor:pointer;" class="form-control track-creation-input round-border-radius" placeholder="Please Select Date Of Birth!" />
		    </div>

            <div class="form-group leadShowcase party-lights mid-margin-bottom"">
                <label style="">Gender</label>
                <form:select path="gender" class="form-control track-creation-input round-border-radius">
                    <form:option class="form-control leadShowcase" value="" label="Select-Gender"/>
                    <form:option class="form-control leadShowcase" value="Male" label="Male"/>
                    <form:option class="form-control leadShowcase" value="Female" label="Female"/>
                	<form:option class="form-control leadShowcase" value="Other" label="Other"/>
                </form:select>
                <form:errors path="gender" class="type-writer text-danger columnCover leadShowcase bold-fonts sm-vert-margin"/>
           </div>

	 	</div>
		<div class="form-group party-lights bluebtn mid-vert-margin"style="">
		  	<div class="form-group flexCover" style="">
		    	<input type="submit" value="Update Mellow Account Today, ${currentDate}" class="leadShowcase submit-track" id="submitBtn"/>
				<a href="/melodydreams/login" class="word-slicer btn btn-outline-warning leadShowcase back-btn" style="">
					<c:out value="Already Have An Account?"/>
		 		</a>
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
	
<link rel="stylesheet" href="/styles/main_player.css"/>	
<link rel="stylesheet" href="/styles/privateStyles.css"/>	
<!--  -->
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>
</body>
</html>