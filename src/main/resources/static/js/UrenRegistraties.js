function buildTable(month) { //
    var tabel = "<table><tr><td>" + getMonthName(month) + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";
    for (var i = 1; i < calculateNumberOfDaysInMonth(month) + 1; i++) {
        tabel += "<tr 'month'><td>" + i + " " + getMonthName(month) + "</><td><input id='WORK" + i + "' type='number'></td><td><input id='OVERTIME" + i + "' type='number'></td><td><input id='LEAVE_OF_ABSENCE" + i + "' type='number'></td><td><input id='ILL" + i + "' type='number'></td><td><input id='TRAINING" + i + "' type='number'></td><td><input id='OTHERS" + i + "' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
    }

    tabel += "<tr><br><tr\>";
    tabel += "<button id='btn' onclick='SaveHours("+month+")'>Declareer Uren</button>"
    document.getElementById("tabel").innerHTML = tabel;
}
var timesheetID;
function buildHourTable2(id) {
    function disabled(state) {
        if (state == "APPROVED" || state == "PENDING") {
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
    var table = document.getElementById("2018-8");
    if (table.innerHTML != "") {
        table.innerHTML = ""
    } else {
        var tableContent = "<tr><th>Overzicht voor de maand " + getMonthName(month) + "</th></tr>";
        tableContent += "<tr><th>"+ getMonthName(month) + "</th><th>Opdracht</th><th>Overwerk</th><th>Verlof</th><th>Ziek</th><th>Training</th><th>Overig</th><th>Verklaring overig</th></tr>";
        for (var day = 1; day < calculateNumberOfDaysInMonth(month) + 1; day++) {
            tableContent += "<tr 'month'><td>" + day + " " + getMonthName(month) + "</>" +
                "<td><input value='"+getEntry(day, "WORK")+"' "+disabled+" id='WORK" + day + "'  type='number'></td>" +
                "<td><input value='"+getEntry(day, "OVERTIME")+"' "+disabled+" id='OVERTIME" + day + "' type='number'></td>" +
                "<td><input value='"+getEntry(day, "LEAVE_OF_ABSENCE")+"' "+disabled+"  id='LEAVE_OF_ABSENCE" + day + "' type='number'></td>" +
                "<td><input value='"+getEntry(day, "ILL")+"' "+disabled+" id='ILL" + day + "' type='number'></td>" +
                "<td><input value='"+getEntry(day, "TRAINING")+"'  "+disabled+" id='TRAINING" + day + "' type='number'></td>" +
                "<td><input value='"+getEntry(day, "OTHERS")+"' "+disabled+" id='OTHERS" + day + "' type='number'></td>" +
                "<td><input " +disabled+" id='verklaring' type='String'></td>" +
                "</tr>";
        }

        tableContent += "<br>";
        tableContent += "<p><button id='btn' onclick='SaveHours(" + month + ")'>Opslaan</button></p>";
        tableContent += "<p><button id='btn' onclick='SubmitHours(" + month + ")'>Verzenden</button></p>";
        tableContent += "<p><button id='btn' style='text-align:right;' onclick='approveTimesheet(8, \"8928308ALEX87283279\")'>Akkoord</button></p>";
        tableContent += "<p><button id='btn' style='text-align:right;' onclick='rejectTimesheet(" + month + ")'>Niet Akkoord</button></p>";
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








function approveTimesheet(id, customerkey) {
    /*alert("Hoi");
    alert(id);
    alert(customerkey);*/
    $.post("/uren/approveTimesheet/"+id+"/"+customerkey, function(){
        alert("Timesheet van medewerker-id: " + id + " is geaccordeerd.")}
    );

}

function rejectTimesheet(id, customerkey) {
    /*alert("Hoi");
    alert(id);
    alert(customerkey);*/
    $.post("/uren/rejectTimesheet/"+id+"/"+customerkey, function(){
        alert("Timesheet van medewerker-id: " + id + " is afgekeurd.")}
    );

}

