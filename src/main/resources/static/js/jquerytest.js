$(document).ready(function(){

    //var users = $('#users');

    //users.append("<tr><th>firstname</th><th>user id</th></tr>")
    function fillTable(month){
        $.get("/uren/api/v1/users", function(users){
            var user_data = '';
            $.each(users, function (elementNumber, user) {
                user_data += '<tr>';
                user_data += '<td>'+clean(user.id)+'</td>';
                user_data += '<td>'+clean(user.firstname)+'</td>';
                user_data += '<td>'+clean(user.lastname)+'</td>';
                user_data += '<td>'+clean(user.emailadress)+'</td>';
                user_data += '<td>'+clean(user.employer)+'</td>';
                user_data += '<td>'+clean(showState2(user.timesheets,month))+'</td>';
                user_data += '</tr>';
            });
                $('#tableOfUsers').append(user_data);

        });
    }

    fillTable("08");
    function clean(string){
        return (!string || string === 0 ? "-" : string);
    }

    function showState2(timesheets, month, year){
         //var month = document.getElementById("monthField").value;
        // var year = document.getElementById("yearField").value;
        // var yearMonth = year+month;
        //
        if(timesheets.length == 0){
            return "-";
        }else{
           //  for (var i = 0; i<timesheets.length; i++) {
              //   if(month == timesheets[i].yearMonth.substring(5,8)){
            for(var i =0;i<timesheets.length;i++){
                if(timesheets[i].yearMonth == "2019-"+month)
                return timesheets[i].state;
            }

             //   }else{
              //       return "-";
             //    }
          //   }
          //  return timesheets[0].state + timesheets[0].yearMonth;
        }

    }

    $('#monthField').on('change',function () {
        fillTable(document.getElementById("monthField").value);


    });
});


