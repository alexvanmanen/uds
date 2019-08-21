function ajax_get(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch(err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function getPassword() {
    var a = document.forms["loginform"]["email"].value;
    var b = document.forms["loginform"]["password"].value;
    ajax_get("/uren/api/v1/checkPassword/" + a + "/" + b , function(data) {
        if(data === false) {
            document.getElementById("login").innerHTML = "<font color='red'>Password and/or Email incorrect</font>";
        }
        else if(data === true) {
            window.location.assign("./dashboard.html");
        }
        else {
            document.getElementById("login").innerHTML = "<font color='red'>Unexpected response from server.</font>";
        }
    });
}

function sendData() {
    $.ajax({
        url: 'http://localhost:8080/uren/api/v1/employees',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            firstName:"Bob",
            lastName:"Martin",
            emailId:"test@test.nl"
        }),
        dataType: 'json'
    });
}