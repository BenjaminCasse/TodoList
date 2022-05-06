$('#daterange').daterangepicker({
	timePicker: true,
	autoUpdateInput: false,
	startDate: moment().startOf('hour'),
	endDate: moment().startOf('hour').add(32, 'hour'),
	locale: {
		format: 'M/DD hh:mm A'
	}
});

$('#daterange').on('apply.daterangepicker', function(ev, picker) {
	const input_startDate = picker.startDate;
	const input_stopDate = picker.endDate;

	const tr = document.querySelectorAll("#myTable tbody tr")

	for (let i = 0; i < tr.length; i++) {
		let td = tr[i].getElementsByTagName("td");
		if (!td || !td[2]) continue;

		let td_date = new Date(td[3].textContent);

		if (td_date) {
			if ((td_date >= input_startDate && td_date <= input_stopDate)) {
				tr[i].style.display = "";
			} else if ((input_startDate.toString() === 'Invalid Date' && td_date <= input_stopDate)) {
				tr[i].style.display = "";
			} else if ((td_date >= input_startDate && input_stopDate.toString() === 'Invalid Date')) {
				tr[i].style.display = "";
			} else if ((input_startDate.toString() === 'Invalid Date' && input_stopDate.toString() == 'Invalid Date')) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = 'none';
			}
		}

	}
});

document.getElementById("ajouterButton").onclick = function() {
	location.href = "/ajouter-tache";
};

document.getElementById("logoutButton").onclick = function() {
	location.href = "/logout";
};

function filtrerParTitre() {

	var input, filter, table, tr, td, i, txtValue;
	input = document.getElementById("myInput");
	filter = input.value.toUpperCase();
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");

	for (i = 1; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[1];
		if (td) {
			txtValue = td.textContent || td.innerText;
			if (txtValue.toUpperCase().indexOf(filter) > -1) {
				tr[i].style.display = "";
			} else {
				tr[i].style.display = "none";
			}
		}
	}
}

$(document).ready(function() {
	$("#tags").select2({
		placeholder: "Chercher par tags"
	});
});

$('#tags').on('select2:select', function(e) {
	table = document.getElementById("myTable");
	tr = table.getElementsByTagName("tr");
	var selected = [];

	for (var option of document.getElementById('tags').options) {
		if (option.selected) {
			selected.push(option.text);
		}
	}
	for (i = 1; i < tr.length; i++) {
		td = tr[i].getElementsByTagName("td")[4];
		div = td.getElementsByTagName("div");
		var spansValues = [];
		for (j = 0; j < div.length; j++) {
			var spans = div[j].getElementsByTagName('span');
			for (var k = 0; k < spans.length; k++) {
				spansValues.push(spans[k].innerText);
			}
		}
		let found = true;
		if (selected.length >= 2) {
			found = selected.every(ai => spansValues.includes(ai));
		} else {
			found = selected.some(r => spansValues.indexOf(r) >= 0);
		}

		if (found) {
			tr[i].style.display = "";
		} else {
			tr[i].style.display = "none";
		}

	}
});