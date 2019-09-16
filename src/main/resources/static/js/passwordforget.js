function showUsername(user) {
    $("#username").text('Welkom, ' +user);
}

$(document).ready(function(){
    $("#forgetpassword").click(function(){
        var username = document.forms["emailResetForm1"]["email1"].value;
        var data = {
            username: username
        };
        $.ajax({
            url: '/uren/api/v1/forgotPassword',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
            },
            data: JSON.stringify(data)
        });
    });
});
$(document).ready(function(){
    $("#changepassword").click(function(){
        var password = document.forms["changepasswordform"]["password"].value;
        var passwordvalid = document.forms["changepasswordform"]["password1"].value;
        var id = document.forms["changepasswordform"]["id"].value;
        var passwordkey = document.forms["changepasswordform"]["passwordkey"].value;
        if(password == passwordvalid) {
            var data = {
                password: password
            };
            $.ajax({
                url: '/uren/api/v1/setNewPassword/' + id + '/' + passwordkey,
                type: 'post',
                dataType: 'json',
                contentType: 'application/json',
                success: function (data) {
                    alert("Wachtwoord gewijzigd");
                },
                data: JSON.stringify(data)
            });
        } else {
            alert("Passwords komen niet overeen.");
        }
    });
});