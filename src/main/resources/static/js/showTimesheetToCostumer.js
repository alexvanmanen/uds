function getMonth(timesheetId){
    $.get("/uren/api/v1/getTimeSheet/"+timesheetId, function(timesheet, status){
        $(document).ready(function(){
            $("#month").text(timesheet.yearMonth.substring(5));
            $("#year").text(timesheet.yearMonth.substring(0,4));
        });
    });
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
            if (timesheet.state != "PENDING"){
                $("#tsbuttons").hide();
            }
        });

    });
}