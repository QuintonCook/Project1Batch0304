/**
 * 
 */
window.onload = function() {
	getEmployeeTickets();
}

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
		let newRow = document.createElement("tr");

		// grab the current record we want to insert
		let newRecord = reimb[i];

		Object.keys(newRecord).forEach(function(key) {

			// for each of the columns in the new record create a new table
			// entry
			let col = document.createElement("td");

			let newContent = document.createTextNode(newRecord[key]);

			col.appendChild(newContent);
			newRow.appendChild(col);
		})

		// add the new row to the table
		document.getElementById("myTickets").appendChild(newRow);
	}
}
