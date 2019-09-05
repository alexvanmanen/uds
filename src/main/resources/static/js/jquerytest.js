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


        if(timesheets.length == 0){
            return "-";
        }else{
            return timesheets[0].state;
        }

    }
});