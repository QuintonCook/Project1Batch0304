/**
 * 
 */
window.onload = function() {
	getEmployeeTickets();
}

// click listeners
document.getElementById("boom").addEventListener("click", function() {
	let data = new XMLHttpRequest();

	data.onreadystatechange = function() {
		if (data.readyState == 4 && data.status == 200) {
			let reimb = JSON.parse(data.responseText);
			populateMyReimbursements(reimb);
		}
	}

	data.open("GET", "/Project1-0304/controller?command=ViewTickets");
	data.send();
});

// gets the employees tickets
function getEmployeeTickets() {
	let data = new XMLHttpRequest();

	data.onreadystatechange = function() {
		if (data.readyState == 4 && data.status == 200) {
			let reimb = JSON.parse(data.responseText);
			populateMyReimbursements(reimb);
		}
	}

	data.open("GET", "/Project1-0304/controller?command=ViewTickets");
	data.send();

}

function populateMyReimbursements(reimb) {

	// iterate through the reimbursement array
	for (i = 0; i < reimb.length; i++) {

		// create a new row in the table
		let newRow = document.createElement("div");
		newRow.setAttribute("class", "row");

		// grab the current record we want to insert
		let newRecord = reimb[i];

		Object.keys(newRecord).forEach(function(key) {

			// for each of the columns in the new record create a new table
			// entry
			let col = document.createElement("div");
			col.setAttribute("class", "col-sm");

			let newContent = document.createTextNode(newRecord[key]);

			col.appendChild(newContent);
			newRow.appendChild(col);
		})

		// add the new row to the table
		document.getElementById("myTickets").appendChild(newRow);
	}
}

function clearTickets() {
	// Removes an element from the document
	let element = document.getElementById("myTickets");
	let divs = element.children;

	for (i = 0; i < divs.length; i++) {
		if (divs[i].getAttribute("id") !== "columnHeaders") {
			element.removeChild(divs[i]);
		}
	}
}
