/**
 * 
 */

window.onload = function() {
	var searchParams = window.location.href.split("?");

	if (searchParams.length > 1) {
		document.getElementById('loginFailure').textContent = "invalid login"
	}
}