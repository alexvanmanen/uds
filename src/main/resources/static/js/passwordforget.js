$(document).ready(function(){
    $("#forgetpassword").click(function(){
        var username = document.forms["emailResetForm1"]["email1"].value;
        var data = {
            username: username,
        };
        alert(username);
        $.ajax({
            url: '/uren/api/v1/forgotPassword',
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                alert("password vergeten");
            },
            data: JSON.stringify(data)
        });
    });
});