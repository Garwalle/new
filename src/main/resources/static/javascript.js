function hideConfirm() {
	document.getElementById("messageConfirmDelete").style.display = "none";
}

function confirmDelete(name, id) {
	document.getElementById("messageConfirmDelete").style.display = "block";
	// demander une explication précise au prof' ?
	document.getElementById("confirmDeletingButton").onclick = function() { window.location = "/orga/delete/" + id; };
	document.getElementById("name").innerHTML = name;
}

function goBackButton() {
	window.history.back();
}

function details(id) {

}