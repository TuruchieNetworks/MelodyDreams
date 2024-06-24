<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
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
<link rel="stylesheet" href="/Styles/styles.css"/>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<script src="<c:url value='/countryCodeParser.js'/>"></script>
<meta charset="ISO-8859-1">
<title>Melody Dreams!</title>
</head>
<body class="container-fluid p-8"style="background:rgba(110.2, 100.4, 336.6, 0.9);font-family:cursive;">
	<h1 style="text-align:center;border-bottom: 2px solid chocolate;color: brown; font-family:fantasy;background:rgba(10.2, 3.3, 3.6, 0.9);margin-top:5px;border-radius:5%;"> <a href="/melodydreams/artists/${loggedInUser.id}" style=" margin: 0 15px 0 0; display:block; padding: 10px;color: khaki;text-decoration:none;">Welcome To Cloud Melody Dreams <c:out value="${loggedInUser.firstName}"/>!</a></h1>

		<div id="countries" style="display: flex; flex-direction: column; padding: 15px; margin: 5px; background: rgba(2.11, 0.03, 1, 0.9); border-radius: 5%;"></div>

		<div class="form-group"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:10px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
			<h1 style="margin:5px;width:100%;">
				<a href="/melodydreams/newTrack" class="btn btn-warning" style="margin:5px;width:100%;display:block;padding:10px;font-weight:bold;color:rgba(12.11, 2.03, 11, 0.9);">
					<c:out value="UPLOAD NEW TRACK"/>
				</a>
			</h1>
			<h1 style="margin:5px;width:100%;">
				<a href="/melodydreams/logout" class="btn btn-outline-danger" style="margin:5px;width:100%;display:block;padding:10px;background:rgba(2.11, 0.03, 1, 0.9);font-weight:bold;">
					<c:out value="LOGOUT HERE"/>
				</a>
			</h1>
	 		<h1 style="margin:5px;width:100%;">
	 			<a style="background:rgba(6.8, 8, 0.8, 0.9);margin:5px;width:100%; display:block; padding: 10px" href="/melodydreams/artists/${loggedInUser.id }" class="btn btn-outline-primary">
	 				<c:out value="${loggedInUser.firstName} Artist Profile"/>
	 			</a>
	 		</h1>
		</div>
		<div class="form-group"style="display:flex; justify-content:space-between;align-items:center; border-radius:5%;padding:15px;margin:10px 0;background:rgba(1.12, 11, 11, 0.9);">
			<h1 style="width:100%">
				<a href="/melodydreams/artists" class="btn btn-outline-warning" style="width:100%;display:block;padding:10px 0; font-weight:bold;">
					<c:out value="ARTIST HUB"/>
				</a>
			</h1>	
		</div>
<!--  -->
<script src="<c:url value='/scripts/player.js'/>"></script>
<script src="<c:url value='/scripts/randomBackgrounds.js'/>"></script>
<script src="<c:url value='/scripts/dynamicWidgets.js'/>"></script>
<script src="<c:url value='/scripts/loadedPanelEvents.js'/>"></script>
<script src="<c:url value='/scripts/socials.js'/>"></script>
<script src="<c:url value='/scripts/uploads.js'/>"></script>
</body>
</html>

