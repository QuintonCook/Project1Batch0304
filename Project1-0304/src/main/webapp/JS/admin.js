/**
 * 
 */

document.getElementById('approvebtn').addEventlistener("onclick", function() {
	makechanges(1);
});

function makechanges(statusId) {
	let forms = document.getElementsByTagName('Form')
	for (i = 0; i < forms.length; i++) {
		let c = forms[i].querySelectorAll('input[type="checkbox"]');
		if (c[0].checked) {
			let action = forms[i].getAttribute("action")
			forms[i].setAttribute("action", action + statusId)
			forms[i].submit();
		}

	}

}
