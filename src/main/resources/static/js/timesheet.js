function timesheet(yearMonth, state, entries){
    var timesheet = {
        yearMonth : yearMonth,
        state  : state,
        entries: entries
    };
    return timesheet;
}

function timesheetEntry(hoursSpent, dayOftheMonth, entryKind){
    var entry = {
        hoursSpent : hoursSpent,
        dayOfTheMonth  : dayOftheMonth,
        entryKind: entryKind
    };
    return entry;
}

function getTimeSheet(id) {
    if (!id) {
        id = document.forms["getprofile"]["id"].value;
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText);
            document.getElementById("state").value = myObj.state;
            document.getElementById("project").value = myObj.project;
            document.getElementById("user").value = myObj.user;
            document.getElementById("yearMonth").value = myObj.yearMonth;
        }
    };
    xmlhttp.open("GET", "http://localhost:8080/uren/api/v1/getTimeSheet/" + id, true);
    xmlhttp.send();
}

/*function updateExample() {
    var id = document.forms["updateprofile"]["id1"].value;
    var email = document.forms["updateprofile"]["email"].value;
    var password = document.forms["updateprofile"]["password"].value;
    var firstname = document.forms["updateprofile"]["firstname"].value;
    var lastname = document.forms["updateprofile"]["lastname"].value;
    updateUser(id, email, password, firstname, lastname);
    return alert("Personeel geupdate")
}*/

function updateTimesheet(state, project, user, yearMonth) {
    var object = {
        "state": state,
        "project": project,
        "user": user,
        "yearMonth": yearMonth,
    };
    var json = JSON.stringify(object);
    apiPutRequest("/uren/api/v1/updateTimeSheet/" + id, json);
}
