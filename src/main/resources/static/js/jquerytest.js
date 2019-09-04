$(document).ready(function(){

    var $users = $('#users');

    $.get("/uren/api/v1/users", function(users){
        $.each(users, function (i, users) {
            $users.append('<li>name'+ users.name + ', users id: ' + users.id+ '</li>')
        })

         //   $("table").append("<tr><td>"+timesheets[id].yearMonth +"</td><td>TODO</td><td>"+timesheets[id].state +"</td></tr>");

    });
});