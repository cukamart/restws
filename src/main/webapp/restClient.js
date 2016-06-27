function doGet(){
	$.ajax({
    	type: "GET",
    	 contentType: "application/json; charset=utf-8",
         dataType: "json",
        url: "http://localhost:8080/restws/services/patientservice/patients/"+$('#patientID').val()
    }).then(function(data) {
       $('#patientID').val(data.id);
       $('#patientName').val(data.name);
    });
}
	
function doPost(){
$.ajax({
	type: "POST",
	 contentType: "application/json; charset=utf-8",
     dataType: "json",
     data: JSON.stringify({name:$('#patientName').val()}),
    url: "http://localhost:8080/restws/services/patientservice/patients"
    	
	}).then(function(data) { // data je to co vracia PatientServiceImpl v tomto pripade vracia celeho Patienta
		$('#patientID').val(data.id);
		$('#patientName').val(data.name);
	});
    
}

function doPut(){
	$.ajax({
		type: "PUT",
		 contentType: "application/json; charset=utf-8",
	     dataType: "json",
	     data: JSON.stringify({id:$('#patientID').val(), name:$('#patientName').val()}),
	     url: "http://localhost:8080/restws/services/patientservice/patients"
	    	
		}).then(function(data) { // data je to co vracia PatientServiceImpl v tomto pripade vracia len status OK...
			alert('Patient Updated');
		});
	    
	}
	
	
function doDelete(){
	$.ajax({
    	type: "DELETE",
    	 contentType: "application/json; charset=utf-8",
         dataType: "json",
        url: "http://localhost:8080/restws/services/patientservice/patients/"+$('#patientID').val()
    }).then(function(data) {
       alert('Patient Deleted');
    });
}