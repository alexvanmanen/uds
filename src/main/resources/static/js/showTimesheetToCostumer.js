function getMonth(timesheetId){
    $.get("/uren/api/v1/getTimeSheet/"+timesheetId, function(timesheet, status){
        $(document).ready(function(){
               $("#yearMonth").text(timesheet.yearMonth.substring(5));
        });
    });
}
