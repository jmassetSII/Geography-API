/**
 * @ namespace app
 * 
 */

function villeSelect() {
	var departement = $('#departementSelect option:selected').val();
	$("#villesHeader").removeClass("hidden");
	$("#villes tbody").empty();
	$.getJSON("/villesList/" + departement, function(data) {
		
		var table = [];
		for (var i=0; i<data.length; i++) {
			table.push([
				data[i].nom, data[i].codePostal, data[i].population_1999, data[i].population_2010, data[i].population_2012,
				"<a id= " +data[i].id+ " class='btn btn-info' onclick = 'createPDF("+data[i].id+")'><i class='glyphicon glyphicon-download-alt'></i></a>"
			])
		}
		
		$("#villes").DataTable({
			destroy: true,
			data : table,
		});
		
	});	
	
	
}


function resetPassword() {
    var email = $("#email").val();
    
    $.ajax({
    	  type: "POST",
    	  url: '/resetPassword',
    	  data: JSON.stringify(email),
    	  dataType: 'json'
    	})
    
    .fail(function(data) {
        if(data.responseJSON.error.indexOf("MailError") > -1) {
            window.location.href = "emailError.html";
        }
        else {
            window.location.href = "login.html" + "message=" + data.responseJSON.message;
        }
    });
}

function createPDF(elt) {
	var villeId = elt;
	window.location = "detailVillePDF/" + villeId;
}
