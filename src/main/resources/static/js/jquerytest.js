$(document).ready(function(){

    //var users = $('#users');

    //users.append("<tr><th>firstname</th><th>user id</th></tr>")
    $.get("/uren/api/v1/users", function(users){
        var user_data = '';
        $.each(users, function (elementNumber, user) {
            user_data += '<tr>';
            user_data += '<td>'+clean(user.id)+'</td>';
            user_data += '<td>'+clean(user.firstname)+'</td>';
            user_data += '<td>'+clean(user.lastname)+'</td>';
            //user_data += '<td>'+clean(user.active)+'</td>';
            user_data += '<td>'+clean(user.emailadress)+'</td>';
            user_data += '<td>'+clean(user.employer)+'</td>';
            user_data += '<td>'+clean(showState2(user.timesheets))+'</td>';
            //user_data += '<td>'+clean(user.street)+'</td>';
            //user_data += '<td>'+clean(user.housenumber)+'</td>';
            //user_data += '<td>'+clean(user.zipcode)+'</td>';
           // user_data += '<td>'+clean(user.city)+'</td>';
           // user_data += '<td>'+clean(user.phonenumber)+'</td>';
           // user_data += '<td>'+clean(user.accountnumber)+'</td>';
            user_data += '</tr>';
        });
            $('#tableOfUsers').append(user_data);

    });

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
                     return timesheets[0].state;
             //   }else{
              //       return "-";
             //    }
          //   }
          //  return timesheets[0].state + timesheets[0].yearMonth;
        }

    }

    $('#monthField').on('change',function () {
        alert(document.getElementById("monthField").value)
        var month = $(this).text();
       // $('#tableOfUsers').alert($(this).value);
    });
});


