// when file is inputted append extra pics
$(document).ready(function() {	
	
	// if input field is changed run function
	$('body').on('change', '#file', function() {		
		// show td
		$('.hideaddmorepicture').show();
			
		// add five extra inputs like in tori.fi
		// first one has the Lisäkuvat td
		$( "<td>" +
			"<input name=\"fileBacker.multipartFile\" type=\"file\" class=\"inputfile\" >" +
			"</td>" ).insertAfter( "#input-td" );
		
		// add the four remaining
		for (i = 0; i < 4; i++) {
			$( "<tr><td>" +
				"<input name=\"fileBacker.multipartFile\" type=\"file\" class=\"inputfile\" >" +
				"</td></tr>"
			).insertAfter( "#input-tr" );
		}	
	});	
});