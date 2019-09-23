$(document).ready(function(){

    function fillTable(yearMonth){
        $("#tableOfUsers tbody").empty();
        $.get("/uren/api/v1/employees", function(users){
            var user_data = '<tbody>';
            $.each(users, function (elementNumber, user) {
                if(user.active == true) {
                    var id = clean(showId(user.timesheets, yearMonth));
                    user_data += '<trgit>';
                    user_data += '<td>' + clean(user.firstname) + '</td>';
                    user_data += '<td>' + clean(user.lastname) + '</td>';
                    user_data += '<td>' + clean(user.username) + '</td>';
                    user_data += '<td>' + clean(showProjectName(user.timesheets, yearMonth)) + '</td>';
                    user_data += '<td>' + clean(showState2(user.timesheets, yearMonth)) + '</td>';
                    if(id != "-") {
                        user_data += '<td><a data-toggle="modal" data-target="#classModal" onclick="fillTimesheetTable(' + id + ')"><img src="../images/timesheet.png" style="color: #A34AFE" width="25"></a></td>';
                    } else {
                        user_data += '<td>-</td>';
                    }
                    user_data += '</tr>';
                }
            });

            user_data += "</tbody>";
            $('#tableOfUsers').append(user_data);
        });
    }

    fillTable("00");
    function clean(string){
        return (!string || string === 0 ? "-" : string);
    }

    function showState2(timesheets, yearMonth){

        if(timesheets.length === 0){
            return "-";
        }else{

            for(var i =0;i<timesheets.length;i++){
                if(timesheets[i].yearMonth === yearMonth)
                    return timesheets[i].state;
            }

        }

    }
    function showId(timesheets, yearMonth){

        if(timesheets.length === 0){
            return "-";
        }else{

            for(var i =0;i<timesheets.length;i++){
                if(timesheets[i].yearMonth === yearMonth)
                    return timesheets[i].id;
            }

        }

    }
    function showProjectName(timesheets, yearMonth){

        if(timesheets.length === 0){
            return "-";
        }else{

            for(var i =0;i<timesheets.length;i++){
                if(timesheets[i].yearMonth === yearMonth)
                    return timesheets[i].customerName;
            }

        }

    }

    $('#monthField').on('change',function () {
        var month = document.getElementById("monthField").value;
        var year  = document.getElementById("yearField").value;
        var yearMonth = year +"-"+ month;
        fillTable(yearMonth);


    });

    $('#yearField').on('change', function () {
        var month = document.getElementById("monthField").value;
        var year  = document.getElementById("yearField").value;
        var yearMonth = year +"-"+ month;
        fillTable(yearMonth);

    });
});
