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
	clearTickets();
	setTimeout(function(){
		document.getElementById('filter').selectedIndex=2;
		getTickets(1);
	},500);
});

document.getElementById('denybtn').addEventListener("click", function() {
	makeChanges(2);
	clearTickets();
	setTimeout(function(){
		document.getElementById('filter').selectedIndex=3;
		getTickets(2);
	},500);
});

document.getElementById('filter').addEventListener("change",function(){
	clearTickets();
	getTickets(this.value);
});

// gets the tickets that the admin can approve
function getTickets(statusId) {
	// setup a connection
	let data = new XMLHttpRequest();
	data.onreadystatechange = function() {
		if (data.readyState == 4 && data.status == 200) {
			// parse the json then populate the tickets
			let reimb = JSON.parse(data.responseText);
			populateReimbursements(reimb);
		}
	}

	// FIRE!
	data.open("GET",
			"/Project1-0304/controller?command=ViewReimbursementsByStatus&reimbStatusId="
					+ statusId);
	data.send();
}

// gets the admins own tickets
function getAdminsTickets() {
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

// populates the tickets the admin can approve
function populateReimbursements(reimb) {

	// iterate through the reimbursement array
	for (i = 0; i < reimb.length; i++) {

		// create a new row in the table
		let newRow = document.createElement("tr");

		// grab the current record we want to insert
		let newRecord = reimb[i];

		// for each of the columns in the new record create a new table entry
		Object.keys(newRecord).forEach(function(key) {
			// create a td that will be the column of the record
			let col = document.createElement("td");
			// append the data to the column
			let newContent = document.createTextNode(newRecord[key]);
			col.appendChild(newContent);

			// append the column to the table
			newRow.appendChild(col);
		})

		// create the last column
		let lastCol = document.createElement("td");

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

// populates the admins own tickets
function populateMyReimbursements(reimb) {

	// iterate through the reimbursement array
	for (i = 0; i < reimb.length; i++) {

		// create a new row in the table
		let newRow = document.createElement("tr");

		// grab the current record we want to insert
		let newRecord = reimb[i];

		Object.keys(newRecord).forEach(function(key) {

			// for each of the columns in the new record create a new table
			// entry
			let col = document.createElement("td");
			let newContent = document.createTextNode(newRecord[key]);

			// append the data to the column then append the column to the new
			// row
			col.appendChild(newContent);
			newRow.appendChild(col);
		})

		// add the new row to the table
		document.getElementById("myTickets").appendChild(newRow);
	}
	
}

// clears out elements from tables
function clearTickets() {
	// finds the root element
	let element = document.getElementById("TheTable");

	//removes the table body
	element.removeChild(element.children[1]);
	
	//create a new table body with the same id
	let newBody = document.createElement("tbody");
	newBody.setAttribute("id","tickets");
	element.appendChild(newBody);
	
}

// approves or denies requests
function makeChanges(statusId) {
	// get the div tickets and all of the forms that are a child of tickets
	let table = document.getElementById('tickets');
	let rows = table.children

	alert("made it here");
	for (i = 0; i < rows.length; i++) {
		let fields = rows[i].children

		// if the form is checked update the record
		if (fields[fields.length-1].children[0].checked) {
			fireUpdate(fields[0].textContent, statusId);
		}

	}
}

//This fires the update
function fireUpdate(id, statusId) {

	// get a new request
	let updater = new XMLHttpRequest();

	console.log(id);
	// fire the update
	updater.open("GET",
			"/Project1-0304/controller?command=ReviewReimbursement&newStatus="
					+ statusId + "&id=" + id);
	updater.send();

}
