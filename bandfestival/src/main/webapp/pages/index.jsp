
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/festival.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 

<script> 
$(document).ready(function(){
	$("button").click(function(){
		 $("div").html("");
		  $.getJSON("<%=request.getContextPath()%>/festivals", function(result){
			if(result.length != 0){
				$("div").html("").append("<h2 class=\"title\">Fantastic Bands</h2>");
			    $.each(result, function(key, value){
			      	$("div").append("<p class = \"recordLabel\">"+value.recordLabel+"</p>");
			      	$.each(value.bandList, function(k, v){
				      	$("div").append("<p class =  \"bandname \">" + v.bandName +"</p>");
				      	$.each(v.festival, function(ik, iv){
					      	$("div").append("<p class =  \"festival\">" + iv +"</p>");
				      	});
			      	});
			      	
			    });
			}else{ //
				$("div").html("");
				$("div").append("<h2 class=\"title\">No data returned, please try later.</h2>");
			}
		  });
		});
});


</script>

</head>
<body>
	<h1>Test Festival Bands</h2>

	<button>clickMe</button>
	
	<div></div>
 
</body>
</html>
