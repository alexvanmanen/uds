$(document).ready(function(){
    $("#saveuser").click(function(){
        var data = {
            firstname: document.forms["createuserform"]["firstname"].value,
            lastname: document.forms["createuserform"]["lastname"].value,
            username: document.forms["createuserform"]["email"].value,
            active: true,
        };
        $.ajax({
            url: '/uren/api/v1/createUser',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
               getUsers();
            },
            data: JSON.stringify(data)
           });
    });
});