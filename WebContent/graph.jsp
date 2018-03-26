<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">

<h:head>
	<title>Tabular Reports</title>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<style>
#table {
	font-size: 16px;
	font-family: "GTCinetypeRegular", sans-serif;
	color: #303030;
	width: 700px;
	margin: 0 auto;
}

thead {
	background: #f6efc3;
}

#table th, #table td {
	padding: 5px 10px;
}

#table tr:nth-child(even) {
	background: #faf8ee;
}

#table tr:hover {
	background: #F2E69C;
}
</style>
</h:head>
<body>
	<h1>Accel Pi</h1>
	<h2>Tabular Reports</h2>

	<table id="table" class="table table-bordered">
		<thead>
			<th>Pitch</th>
			<th>Row</th>
			<th>Yaw</th>
			<th>Time</th>
		</thead>
		<tbody>
		</tbody>
	</table>
	<p>
		<a href="index.jsp">return home</a>
	</p>

	<script>
		// JSON received
		function responseSuccess(data) {
			console.log("REST call returned success!")
			console.log(JSON.stringify(data))
			
			// Fill table
			var tbody = $("#table tbody")
			for (let reading of data.list) {
				tbody.append('<tr>\n' +
						'<td>'+reading.pitch+'</td>\n' +
						'<td>'+reading.roll+'</td>\n' +
						'<td>'+reading.yaw+'</td>\n' +
						'<td>'+reading.time+'</td>\n' +
						'</tr>\n')
			}
		}
	
		// When REST call returns error
		function responseError(request, error) {
			console.log("ERROR. Request: " + JSON.stringify(request))
		}
	
		// Make AJAX call with provided link
		function sendRequest() {
			$.ajax({
				url: '../PiSense/accel/readings',
				crossDomain: true,
				type: 'get',
				dataType: "json",
				success: function(data)
				{
			    	// Display Orders in the jQuery Data Table
					responseSuccess(data);
				}
			});
		}
		sendRequest();
	</script>
	<table id="reading" class="display table-striped table-bordered"
		width="50%">
		<tbody>
		</tbody>
	</table>
</body>
</html>
