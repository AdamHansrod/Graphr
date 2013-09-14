<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.24/jquery-ui.min.js "></script>
<style>
li{
display:inline;
float:left;
padding:1em;
margin:1em;
border-radius: 1em;
-moz-border-radius: 1em;
-webkit-border-radius: 1em;	
background-color: lightblue;
}
*{
text-align:center;
}
.Filler{
position:fixed;
top:1%;
}
</style>
</head>
<body>
<ul>
	<li><p>Moodle Users</p><img src="<s:url action='ImageView?Filename=moodle.gif' />" alt="Moodle User Numbers"></li>
	<li><p>Student Records event Count</p><img src="<s:url action='ImageView?Filename=srEventCount.gif' />" alt="Student Records Event CountUtilisation"></li>
	</ul>
	<div class="Filler"></div>
	</body>	
	<script>
		$(document).ready(function() {
			var d = new Date();
			$('.Filler').append("refreshing next at " + (d.getMinutes() + 5));
			setInterval("location.reload(true)", 300000);//reload every 5 mins
		});
	</script>
</html>