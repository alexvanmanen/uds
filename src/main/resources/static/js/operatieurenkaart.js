$(document).ready(function () {
maketimesheetoverview();
});
function maketimesheetoverview() {
    $.get("/uren/api/v1/getAllTimeSheetsByEmployee/" + getEmployeeId(), function (timesheets, status) {
        $("#taskOverview tbody").empty();
        $("#taskOverview").append("<tbody>");
        for (var i = 0; i < timesheets.length; i++) {
            $("#taskOverview").append("<tr onclick='fillTimesheetTable(" + timesheets[i].id + ")'><td >" + timesheets[i].yearMonth + "</td><td>" + timesheets[i].customerName + "</td><td>" + timesheets[i].state + "</td></tr>");
        }
        $("#taskOverview").append("</tbody>");
    });
}
function tsdisabled(state) {
    if (state == "GOEDGEKEURD" || state == "AFWACHTEND") {
        return "disabled";
    }
    return "";
}

function fillTimesheetTable(timesheetId) {
    $.get("/uren/api/v1/getTimeSheet/" + timesheetId, function (timesheet) {
        $(document).ready(function () {
            $("#timesheetTable tbody").empty();
            $("#timesheetTable thead").empty();
            $("#timesheetTable").append('<thead><tr><th>Dag</th><th>Werk</th><th>Verlof</th><th>Ziek</th><th>Training</th><th>Overwerk</th> <th>Overig</th> </tr> </thead>');
            $('#timesheetTable').append(getTimesheetTable(timesheet));
            if (tsdisabled(timesheet.state) == "disabled") {
                $('#tsbuttons1').html('<a class="btn btn-primary" onclick="" role="button" disabled>Opslaan</a>&nbsp;<a class="btn btn-primary" onclick="" role="button" disabled>Verstuur</a>');
            } else {
                $('#tsbuttons1').html('<a class="btn btn-primary" onclick="SaveHours(' + timesheetId + ')" role="button">Opslaan</a>&nbsp;<a class="btn btn-primary" onclick="SubmitHours(' + timesheetId + ')" role="button">Verstuur</a>');
            }
        });
    });
}

function getTimesheetTable(timesheet) {
    var table_data = '<tbody>';
    for (var dayNumber = 1; dayNumber <= calculateNumberOfDaysInMonth(timesheet.month, timesheet.year); dayNumber++) {
        table_data += getTimesheetRow(timesheet, dayNumber);
    }
    table_data += '<br></tbody>';
    return table_data;
}


function getTimesheetRow(timesheet, day) {
    var dayname = timesheet.month.toString().concat("/", day.toString(), "/", timesheet.year.toString());
    var disabled = tsdisabled(timesheet.state);
    var dayname1 = getDayName(dayname, "nl-NL")
    var timesheetDay = getTimesheetDay(timesheet, day);
    if (dayname1 == "zaterdag") {
        var table_data = '<tr class="table-purple">'
    } else if (dayname1 == "zondag") {
        var table_data = '<tr class="table-purple">'
    } else {
        var table_data = '<tr>'
    }
    table_data += '<td>' + day + '</td>';
    table_data += '<td><input type="number" class="form-control input-sm" value="' + timesheetDay.work + '" id="WORK' + day + '" ' + disabled + '></td>';
    table_data += '<td><input type="number" class="form-control input-sm" value="' + timesheetDay.leaveOfAbsence + '" id="LEAVE_OF_ABSENCE' + day + '" ' + disabled + '></td>';
    table_data += '<td><input type="number" class="form-control input-sm" value="' + timesheetDay.ill + '" id="ILL' + day + '" ' + disabled + '></td>';
    table_data += '<td><input type="number" class="form-control input-sm" value="' + timesheetDay.training + '" id="TRAINING' + day + '" ' + disabled + '></td>';
    table_data += '<td><input type="number" class="form-control input-sm" value="' + timesheetDay.overtime + '" id="OVERTIME' + day + '" ' + disabled + '></td>';
    table_data += '<td><input type="number" class="form-control input-sm" value="' + timesheetDay.others + '" id="OTHERS' + day + '" ' + disabled + '></td>';
    table_data += '</tr>';
    return table_data;

}

function SubmitHours(id) {
    SaveHours(id);
    $.post("/uren/submitTimeSheet/" + id, function () {
            alert("Urenkaart verstuurd");
            maketimesheetoverview();
            fillTimesheetTable(id);
        }
    );
}

function SaveHours(id) {
    $.get("/uren/api/v1/getTimeSheet/" + id, function (timesheet1) {
        var categories = ["WORK", "LEAVE_OF_ABSENCE", "ILL", "TRAINING", "OVERTIME", "OTHERS"];
        var entries = [];

        for (var dayOfTheMonth = 1; dayOfTheMonth <= calculateNumberOfDaysInMonth(timesheet1.month, timesheet1.year); dayOfTheMonth++) {
            for (var category = 0; category < categories.length; category++) {
                if (document.getElementById(categories[category] + dayOfTheMonth).value != "") {
                    entries.push(timesheetEntry(document.getElementById(categories[category] + dayOfTheMonth).value, dayOfTheMonth, categories[category]));
                }
            }
        }
        var ts1 = timesheet("2019-" + timesheet1.month, "OPEN", entries, timesheet1.id);
        var jsonTimesheet = JSON.stringify(ts1);
        apiPostRequest("/uren/api/v1/updateTimesheet", jsonTimesheet);
    });
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
