// (Pure) Javascript

function hideConfirm() {
	document.getElementById("messageConfirmDelete").style.display = "none";
}

function confirmDelete(name, id) {
	document.getElementById("messageConfirmDelete").style.display = "block";
	// demander une explication précise au prof' ?
	document.getElementById("confirmDeletingButton").onclick = function() { window.location = "/orga/delete/" + id; };
	document.getElementById("orgaName").innerHTML = name;
}

function goBackButton() {
	window.history.back();
}


// Jquery (Javascript library)

$(document).ready(function(){
	//$("#test").click(function(){
		//$("#test").text(maVar);
	//});
});