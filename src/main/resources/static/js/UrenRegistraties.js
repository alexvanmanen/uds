
function buildTable(month) {
    var tabel = "<tr><td>" + getMonthName(month) + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";
    for (var i = 1; i < calculateNumberOfDaysInMonth(month)+1; i++) {
        tabel += "<tr 'month'><td>" + i + " " + getMonthName(month) + "</><td><input id='opdracht" + i + "' type='number'></td><td><input id='overwerk" + i + "' type='number'></td><td><input id='verlof" + i + "' type='number'></td><td><input id='ziekte" + i + "' type='number'></td><td><input id='training" + i + "gi' type='number'></td><td><input id='overig" + i + "' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
    }

    tabel+="<tr><br><tr\>";
    tabel += "<button id='btn' onclick='Registreer()'>Declareer Uren</button>"
    document.getElementById("tabel").innerHTML = tabel;
}

function timesheet(yearMonth, state, entries){
    var timesheet = {
        yearMonth : yearMonth,
        state  : state,
        entries: entries
    };
    return timesheet;
}

function Registreer() {
    var entries = [];
    for (var i = 1; i < 32; i++) {
        entries.push(timesheetEntry(document.getElementById("opdracht" + i).value, 19, "WORK"));
        entries.push(timesheetEntry(document.getElementById("verlof" + i).value, 19, "LEAVE_OF_ABSENCE"));
        entries.push(timesheetEntry(document.getElementById("ziekte" + i).value, 19, "ILL"));
        entries.push(timesheetEntry(document.getElementById("training" + i).value, 19, "TRAINING"));
        entries.push(timesheetEntry(document.getElementById("overwerk" + i).value, 19, "OVERTIME"));
    }
    var ts1 = timesheet("2018-02", "OPEN", entries);
    var jsonTimesheet = JSON.stringify(ts1);
    apiPostRequest("/uren/api/v1/createTimesheet", jsonTimesheet);
}

function apiPostRequest(url, json) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST",url);
    var xmlDoc;
    xmlhttp.onreadystatechange = function() {
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

function getUren(){
    ajax_get("/uren/api/v1/urencount",function(data){
        if (data == 0) {
            alert("dit is mijn data: " + data);
        }
    });
}


