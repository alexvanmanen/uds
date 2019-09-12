function getMonth(timesheetId){
    $.get("/uren/api/v1/getTimeSheet/"+timesheetId, function(timesheet, status){
        $(document).ready(function(){
            $("#month").text(getMonthName(timesheet.yearMonth.substring(5)));
            $("#year").text(timesheet.yearMonth.substring(0,4));
        });
    });
}

function getUser(id) {
    $.get("/uren/api/v1/getUser/" + id, function (user) {
        $(document).ready(function () {
            if (user != null) {
                var firstname = user.firstname == null ? "" : user.firstname;
                var lastname = user.lastname == null ? "" : user.lastname;
                $("#user").text(firstname + " " + lastname);
            }
        });
    });
}



function fillTimesheetTable(timesheetId){
$.get("/uren/api/v1/getTimeSheet/"+timesheetId, function (timesheet){
    $(document).ready(function(){
        $("#timesheetTable tbody").empty();
        $('#timesheetTable').append(getTimesheetTable(timesheet));
        });
    });
}

function getTimesheetTable(timesheet){
    var table_data = '<tbody>';
    for(var dayNumber = 1; dayNumber<=calculateNumberOfDaysInMonth(timesheet.month, timesheet.year); dayNumber++){
        table_data += getTimesheetRow(timesheet, dayNumber);
    }
    return table_data += "</tbody>";
}


function getTimesheetRow(timesheet, day){
    var dayname = timesheet.month.toString().concat("/", day.toString(), "/", timesheet.year.toString());
    var dayname1 = getDayName(dayname, "nl-NL")
    //alert(dayname1);
   var timesheetDay =  getTimesheetDay(timesheet,day);
    if(dayname1 == "zaterdag") {
        var table_data = '<tr class="table-purple">'
    } else if(dayname1 == "zondag") {
        var table_data = '<tr class="table-purple">'
    } else {
        var table_data = '<tr>'
    }

        table_data += '<td>'+day+'</td>';
        table_data += '<td>'+timesheetDay.work+'</td>';
        table_data += '<td>'+timesheetDay.leaveOfAbsence+'</td>';
        table_data += '<td>'+timesheetDay.ill+'</td>';
        table_data += '<td>'+timesheetDay.training+'</td>';
        table_data += '<td>'+timesheetDay.overtime+'</td>';
        table_data += '<td>'+timesheetDay.others+'</td>';
        table_data += '</tr>';
        return table_data;

}

function approveTimesheet(id, customerkey) {
    $.post("/uren/approveTimesheet/"+id+"/"+customerkey, function(){
        alert("Timesheet van medewerker-id: " + id + " is geaccordeerd.")}
    );

}

function rejectTimesheet(id, customerkey) {
    $.post("/uren/rejectTimesheet/"+id+"/"+customerkey, function(){
        alert("Timesheet van medewerker-id: " + id + " is afgekeurd.")}
    );

}

function getState(timesheetId) {
    $.get("/uren/api/v1/getTimeSheet/" + timesheetId, function (timesheet, status) {
        $(document).ready(function () {
            if (timesheet.state != "PENDING") {
                $("#tsbuttons").hide();
            }
        });

    });
}
