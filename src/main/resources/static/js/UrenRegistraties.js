var timesheetID;
function buildHourTable2(id) {
    function disabled(state) {
        if (state == "GOEDGEKEURD" || state == "AFWACHTEND") {
            return disabled = "disabled";
        }
        return "";
    }
    function getEntry(dayOfTheMonth, entryKind){
        var entries = retrievedTimesheets[id].entries;
        for(var i = 0 ; i<entries.length ; i++){
            if(entries[i].dayOfTheMonth == dayOfTheMonth && entryKind == entries[i].entryKind) {
                return entries[i].hoursSpent;
            }
        }
        return "";
    }

    var disabled = disabled(retrievedTimesheets[id].state);
    timesheetID = retrievedTimesheets[id].id;
    var timesheet = retrievedTimesheets[id];
    var month =  timesheet.yearMonth.substring(6);
    var table = document.getElementById("timesheetoverview");
    if (table.innerHTML != "") {
        table.innerHTML = ""
    } else {
        var tableContent = "<tr><th>Overzicht voor de maand " + getMonthName(month) + "</th></tr>";
        tableContent += "<tr><th>"+ getMonthName(month) + "</th><th>Project</th><th>Overwerk</th><th>Verlof</th><th>Ziek</th><th>Training</th><th>Overig</th><th>Verklaring overig</th></tr>";
        for (var day = 1; day < calculateNumberOfDaysInMonth(month) + 1; day++) {
            tableContent += "<tr 'month'><td>" + day + " " + getMonthName(month) + "</>" +
                "<td><input class='form-control input-sm' value='"+getEntry(day, "WORK")+"' "+disabled+" id='WORK" + day + "'  type='number'></td>" +
                "<td><input class='form-control input-sm' value='"+getEntry(day, "OVERTIME")+"' "+disabled+" id='OVERTIME" + day + "' type='number'></td>" +
                "<td><input class='form-control input-sm' value='"+getEntry(day, "LEAVE_OF_ABSENCE")+"' "+disabled+"  id='LEAVE_OF_ABSENCE" + day + "' type='number'></td>" +
                "<td><input class='form-control input-sm' value='"+getEntry(day, "ILL")+"' "+disabled+" id='ILL" + day + "' type='number'></td>" +
                "<td><input class='form-control input-sm' value='"+getEntry(day, "TRAINING")+"'  "+disabled+" id='TRAINING" + day + "' type='number'></td>" +
                "<td><input class='form-control input-sm' value='"+getEntry(day, "OTHERS")+"' "+disabled+" id='OTHERS" + day + "' type='number'></td>" +
                "<td><input class='form-control input-sm' " +disabled+" id='verklaring' type='String'></td>" +
                "</tr>";
        }

        tableContent += "<br>";
        tableContent += "<p><button id='btn' onclick='SaveHours(" + month + ")'>Opslaan</button></p>";
        tableContent += "<p><button id='btn' onclick='SubmitHours(" + month + ")'>Verzenden</button></p>";
        table.innerHTML = tableContent;
    }
}

function SubmitHours(month){
 SaveHours(month);
    $.post("/uren/submitTimeSheet/"+timesheetID, function(){
        alert("Gelukt. Met id: " + timesheetID)}
    );
}

function SaveHours(month) {
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
    var ts1 = timesheet("2019-"+ stringmonth, "OPEN", entries, timesheetID);
    var jsonTimesheet = JSON.stringify(ts1);
    apiPostRequest("/uren/api/v1/updateTimesheet", jsonTimesheet);
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

