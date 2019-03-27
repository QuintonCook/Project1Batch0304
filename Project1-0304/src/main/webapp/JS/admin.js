/**
 * 
 */

window.onload = function() {
	loadTable();
};
document.getElementById("approvebtn").addEventListener("click", function() {
	loadTable();
});
document.getElementById("denybtn").addEventListener("click", function() {
	loadTable();
});

document.getElementById("submitrequestbtn").addEventListener("click",
		function() {
			loadTable();
		});

function updatePage(reimbursements) {
	for (i = 0; i < reimbursements.length; i++) {
		let newrow = document.createElement("tr")
		let row = reimbursement[i];

		for (j = 0; j < row.length; j++) {
			newrow.appendChild("td").textContent(row[j]);
		}

		document.getElementById("infotable").appendChild(newrow);
	}
}

      document.getElementByTag("td").rows[0].cells.length;
      cell1.innerHTML = "reimbursements";
      

function loadTable() {
	let xhttp = new XMLHttpRequest();
	if (xhttp.readyState == 4 && xhttp.status == 200) {

		let reimbursements = JSON.parse(xhttp.responseText);
		updatePage();
	}
	xhttp.open("GET", '/admin ? command = ReviewReimbursement NewStatus = ?', true);

	xhttp.send();
}

function otherpeople() {
	let xhttp = new XMLHttpRequest();
	if (xhttp.readystate === 4 && xhttp.status == 200) {

		let reimbursements = JSON.parse(xhttp.responseText);
		updatePage();
	}
	xhttp.open("GET", "/admin", true);

	xhttp.send();
}
