function buildTable(month) { //
    var tabel = "<table><tr><td>" + getMonthName(month) + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";
    for (var i = 1; i < calculateNumberOfDaysInMonth(month) + 1; i++) {
        tabel += "<tr 'month'><td>" + i + " " + getMonthName(month) + "</><td><input id='WORK" + i + "' type='number'></td><td><input id='OVERTIME" + i + "' type='number'></td><td><input id='LEAVE_OF_ABSENCE" + i + "' type='number'></td><td><input id='ILL" + i + "' type='number'></td><td><input id='TRAINING" + i + "' type='number'></td><td><input id='OTHERS" + i + "' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
    }

    tabel += "<tr><br><tr\>";
    tabel += "<button id='btn' onclick='Registreer("+month+")'>Declareer Uren</button>"
    document.getElementById("tabel").innerHTML = tabel;
}

function buildHourTable2(month) {
    var table = document.getElementById("2018-8");
    if (table.innerHTML != "") {
        table.innerHTML = ""
    } else {
        var tableContent = "<tr><td>" + getMonthName(month) + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";
        for (var i = 1; i < calculateNumberOfDaysInMonth(month) + 1; i++) {
            tableContent += "<tr 'month'><td>" + i + " " + getMonthName(month) + "</><td><input id='WORK" + i + "' type='number'></td><td><input id='OVERTIME" + i + "' type='number'></td><td><input id='LEAVE_OF_ABSENCE" + i + "' type='number'></td><td><input id='ILL" + i + "' type='number'></td><td><input id='TRAINING" + i + "' type='number'></td><td><input id='OTHERS" + i + "' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
        }

        tableContent += "<tr><br><tr\>";
        tableContent += "<button id='btn' onclick='Registreer(" + month + ")'>Declareer Uren</button>"
        table.innerHTML = tableContent;
    }
}

function timesheet(yearMonth, state, entries) {
    var timesheet = {
        yearMonth: yearMonth,
        state: state,
        entries: entries
    };
    return timesheet;
}

function Registreer(month) {
    var categories = ["WORK", "LEAVE_OF_ABSENCE", "ILL", "TRAINING", "OVERTIME", "OTHERS"];
    var entries = [];
    for (var dayOfTheMonth = 1; dayOfTheMonth <= calculateNumberOfDaysInMonth(month); dayOfTheMonth++) {
        for (var category = 0; category < categories.length; category++) {
            if (document.getElementById(categories[category] + dayOfTheMonth).value != "") {
                entries.push(timesheetEntry(document.getElementById(categories[category] + dayOfTheMonth).value, dayOfTheMonth, categories[category]));
            }
        }
    }
    if (month < 10) {
        stringmonth = "0" + month;
    }
    var ts1 = timesheet("2019-"+ stringmonth, "OPEN", entries);
    var jsonTimesheet = JSON.stringify(ts1);
    apiPostRequest("/uren/api/v1/createTimesheet", jsonTimesheet);
}

function apiPostRequest(url, json) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", url);
    var xmlDoc;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            xmlDoc = xmlhttp.responseXML;
            console.log(xmlDoc);
        }
    };
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    xmlhttp.send(json);
}

function ajax_get(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch (err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function getUren() {
    ajax_get("/uren/api/v1/urencount", function (data) {
        if (data == 0) {
            alert("dit is mijn data: " + data);
        }
    });
}
function getMonthName(month) {
    var months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
    return months[(month-1)];
}

function calculateNumberOfDaysInMonth(month){
    return new Date(2019, month, 0).getDate();
}

function sloopTable2() {
    if (document.getElementById("augustustabel").innerHTML != "") {
        document.getElementById("augustustabel").innerHTML = ""
    }
}