$(document).ready(function(){
    var id = 8;
    $.get("/uren/api/v1/getTimeSheet/"+id, function(timesheet, status){
        $("#yearMonth").text(timesheet.yearMonth.substring(5));

    });
});
