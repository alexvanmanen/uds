$(document).ready(function(){

    function fillTable(yearAndMonth){
        $("#tableOfUsers tbody").empty();
        $.get("/uren/api/v1/users", function(users){
            var user_data = '<tbody>';
            $.each(users, function (elementNumber, user) {
                user_data += '<trgit>';
                user_data += '<td>'+clean(user.id)+'</td>';
                user_data += '<td>'+clean(user.firstname)+'</td>';
                user_data += '<td>'+clean(user.lastname)+'</td>';
                user_data += '<td>'+clean(user.username)+'</td>';
                user_data += '<td>'+clean(user.employer)+'</td>';
                user_data += '<td>'+clean(showState2(user.timesheets,yearAndMonth))+'</td>';
                user_data += '</tr>';
            });
            user_data += "</tbody>";
            $('#tableOfUsers').append(user_data);
        });
    }

    fillTable("00");
    function clean(string){
        return (!string || string === 0 ? "-" : string);
    }

    function showState2(timesheets, yearAndMonth){

        if(timesheets.length === 0){
            return "-";
        }else{

            for(var i =0;i<timesheets.length;i++){
                if(timesheets[i].yearAndMonth === yearAndMonth)
                    return timesheets[i].state;
            }

        }

    }

    $('#monthField').on('change',function () {
        var month = document.getElementById("monthField").value;
        var year  = document.getElementById("yearField").value;
        var yearAndMonth = year +"-"+ month;
        fillTable(yearAndMonth);


    });

    $('#yearField').on('change', function () {
        var month = document.getElementById("monthField").value;
        var year  = document.getElementById("yearField").value;
        var yearAndMonth = year +"-"+ month;
        fillTable(yearAndMonth);

    });
});
