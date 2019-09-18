function getMonth(timesheetId){
    $.get("/uren/api/v1/getTimeSheet/"+timesheetId, function(timesheet, status){
        $(document).ready(function(){
            $("#month").text(timesheet.yearMonth.substring(5));
            $("#year").text(timesheet.yearMonth.substring(0,4));
        });
    });
}

$(document).ready(function(){
     $("#timesheetTable tbody").empty();

    $.get("/uren/api/v1/getTimeSheet/8", function (timesheet){

        var table_data = '<tbody>';
           table_data += getRow();
        table_data += "</tbody>";

        $('#timesheetTable').append(table_data);
        // var table_data = '<tbody>';
        // $.each(timesheet, function (elementnumber, timesheet) {
        //     table_data += '<trgit>';
        //
        //
        //     table_data += '<td>'+timesheet.id+'</td>';
        //     table_data += '</tr>';
        // });
       // var entriesPerDay = getTimesheetDay(timesheet);



        //$.each(timesheets, function (timesheet) {


        //});

       // $('#timesheetTable').append(table_data);

    });


});

function getRow(timesheet, day){
   var timesheetDay =  getTimesheetDay(1,1);
   var table_data = '<tr>'
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
            if(timesheet.state == "APPROVED") {
                $("#state2").text("DEZE TIMESHEET IS REEEDS GOEDGEKEURD");
            } if(timesheet.state == "DECLINED") {
                $("#state2").text("DEZE TIMESHEET IS REEDS AFGEKEURD");
            }

            if (timesheet.state != "PENDING"){
                //$("#tsbuttons").hide();
            }
        });

    });
}