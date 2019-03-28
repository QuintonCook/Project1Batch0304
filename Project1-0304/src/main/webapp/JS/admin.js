/**
 * 
 */

window.onload = function() {
	getTickets(-1);
	getAdminsTickets();
}

// click listeners
document.getElementById('approvebtn').addEventListener("click", function() {
	makeChanges(1);
	clearTickets("tickets");
	populateReimbursements(-1);
});

document.getElementById('denybtn').addEventListener("click", function() {
	makeChanges(2);
	clearTickets("tickets");
	populateReimbursements(-1);
});

// click listeners
document.getElementById("boom").addEventListener("click", function() {
	clearTickets("myTickets");
	getEmployeeTickets();
});

// gets the tickets that the admin can approve
function getTickets(statusId) {
	let data = new XMLHttpRequest();

	data.onreadystatechange = function() {
		if (data.readystate == 4 && data.status == 200) {
			let reimb = JSON.parse(data.responseText);
			populateReimbursements(reimb, "tickets");
		}
	}

	data.open("GET",
			"/Project1-0304/controller?command=ViewReimbursementsByStatus&reimbStatusId="
					+ statusId);
	data.send();
}

function getAdminsTickets() {
	let data = new XMLHttpRequest();

	data.onreadystatechange = function() {
		if (data.readystate == 4 && data.status == 200) {
			let reimb = JSON.parse(data.responseText);
			populateMyReimbursements(reimb);
		}
	}

	data.open("GET", "/Project1-0304/controller?command=ViewTickets");
	data.send();

}

function populateReimbursements(reimb) {

	// iterate through the reimbursement array
	for (i = 0; i < reimb.length; i++) {

		// create a form
		let formContainer = document.createElement("form");
		formContainer
				.setAttribute('action',
						'/Project1-0304/controller?command="ReviewReimbursement"&reimbStatusId=');
		formContainer.setAttribute('method', 'POST');

		// create a new row in the table
		let newRow = document.createElement("div");
		newRow.setAttribute("class", "row");

		// append the new row to the form container
		formContainer.appendChild(newRow);

		// grab the current record we want to insert
		let newRecord = reimb[i];

		// create the first column to be a text area so it gets submitted to the
		// server
		let firstCol = document.createElement("div");
		col.setAttribute("class", "col-sm");

		// create the text area
		let id = document.createElement("input")
		id.setAttribute("name", "id")
		id.setAttribute("readonly", "readonly");
		id.textContent = newRecord[0];

		// append the items
		firstCol.appendChild(id);
		newRow.appendChild(firstCol);

		// for each of the columns in the new record create a new table entry
		for (j = 1; j < newRecord.length; j++) {
			// create a div that will be the column of the record
			let col = document.createElement("div");
			col.setAttribute("class", "col-sm");

			// append the data to the column
			let newContent = document.createTextNode(newRecord[j]);
			col.appendChild(newContent);

			// append the column to the table
			newRow.appendChild(col);
		}

		// create the last column
		let lastCol = document.createElement("div");
		lastCol.setAttribute("class", "col-sm");

		// create a checkbox
		let check = document.createElement("input");
		check.setAttribute("type", "checkbox");

		// append the checkbox to the last column
		lastCol.appendChild(check);

		// add the column to the new row
		newRow.appendChild(lastCol);

		// add the new row to the table
		document.getElementById("tickets").appendChild(newRow);
	}
}

function populateMyReimbursements(reimb) {

	// iterate through the reimbursement array
	for (i = 0; i < reimb.length; i++) {

		// create a new row in the table
		let newRow = document.createElement("div");
		newRow.setAttribute("class", "row");

		// grab the current record we want to insert
		let newRecord = reimb[i];

		// for each of the columns in the new record create a new table entry
		Object.keys(newRecord).forEach(function(key) {

			let col = document.createElement("div");
			col.setAttribute("class", "col-sm");

			let newContent = document.createTextNode(newRecord[key]);

			col.appendChild(newContent);
			newRow.appendChild(col);
		})

		// add the column to the new row
		newRow.appendChild(lastCol);

		// add the new row to the table
		document.getElementById("myTickets").appendChild(newRow);
	}
}

function clearTickets(elementId) {
	// Removes an element from the document
	let element = document.getElementById(elementId);
	let divs = element.getElementsByTagName("div");

	for (i = 0; i < divs.length; i++) {
		if (divs[i].getAttribute("id") != "columnHeaders") {
			element.removeChild(divs[i]);
		}
	}
}

function makeChanges(statusId) {
	// get the div tickets and all of the forms that are a child of tickets
	let table = document.getElementById('tickets');
	let forms = table.getElemenetsByTagName('form');

	for (i = 0; i < forms.length; i++) {
		let attr = forms[i].getAttribute("action");
		forms[i].setAttribute("action", attr + statusId);
		forms[i].submit();
	}
}
