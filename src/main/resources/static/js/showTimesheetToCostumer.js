function getMonth(timesheetId){
    $.get("/uren/api/v1/getTimeSheet/"+timesheetId, function(timesheet, status){
        $(document).ready(function(){
            $("#month").text(timesheet.yearMonth.substring(5));
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
        var table_data = '<tbody>';
        //forloop t/m lengte van de maand (we beginnen met 31) ;) van 1 tot en met 31 (dus tot en met 31 en niet tot en met 30)
        for(var x = 1; x<32; x++){
           table_data += getRow(timesheet, x);
        }
        table_data += "</tbody>";
        $('#timesheetTable').append(table_data);
        });
    });
}




function getRow(timesheet, day){
   var timesheetDay =  getTimesheetDay(timesheet,day);
   var table_data = '<tr>'
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

function getState(timesheetId) {
    $.get("/uren/api/v1/getTimeSheet/" + timesheetId, function (timesheet, status) {
        $(document).ready(function () {
            alert("timesheet state = "+ timesheet.state)
            $("#state").text(timesheet.state);
            if (timesheet.state != "PENDING") {
                $("#tsbuttons").hide();
            }
        });

    });
}