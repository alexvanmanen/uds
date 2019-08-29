function ajax_get(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch (err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function setSessie(email) {
    var id = 0;
    if(typeof(Storage) !== "undefined") {
        ajax_get('/uren/api/v1/users', function(data) {
            for (var i = 0; i < data.length; i++) {
                if(data[i]['emailAdress'] === email) {
                    id = data[i]['id'];
                }
            }
        });
        if (!sessionStorage.email) {
            sessionStorage.email = email;
        }
        if (!sessionStorage.id) {
                sessionStorage.id = id;

        }
    } else {
        document.getElementById("result").innerHTML = "Sorry, your browser does not support web storage...";
    }
}

function getWelcome() {
    document.getElementById("welcome").innerHTML = "Welkom " + sessionStorage.email + " " + sessionStorage.id;
}

function getPassword() {
    var a = document.forms["loginform"]["email"].value;
    var b = document.forms["loginform"]["password"].value;
    ajax_get("/uren/api/v1/checkPassword/" + a + "/" + b, function (data) {
        if (data === false) {
            document.getElementById("login").innerHTML = "<font color='red'>Password and/or Email incorrect</font>";
        } else if (data === true) {
            setSessie(a);
            window.location.assign("./dashboard.html");
        } else {
            document.getElementById("login").innerHTML = "<font color='red'>Unexpected response from server.</font>";
        }
    });
}

function showAddUserForm() {
    document.getElementById("ajax").innerHTML = '<form action="" id="createuserform" name="createuserform">' +
        'Email: <input id="email" type="text" name="email"><br>' +
        'Password: <input id="password" type="password" name="password"><br>' +
        '<input type="submit" onclick="addUser(); return false;" value="Submit"></form>';
}

function addUser() {
    var a = document.forms["createuserform"]["email"].value;
    var b = document.forms["createuserform"]["password"].value;
    ajax_get("/uren/api/v1/register/" + a + "/" + b, function (data) {
        if (data === false) {
            document.getElementById("test").innerHTML = "<font color='red'>Error adding user.</font>";
        } else if (data === true) {
            document.getElementById("test").innerHTML = "<font>User added.</font>";
        } else {
            document.getElementById("test").innerHTML = "<font color='red'>Unexpected response from server.</font>";
        }
    });
}

function getUsers() {
    ajax_get('/uren/api/v1/users', function (data) {
        var tableContent = "<tr><th>Email</th><th>Password</th></tr>";
        for (i = 0; i < data.length; i++) {
            tableContent = tableContent + "<tr><td>" + data[i]['emailAdress'] + "</td><td>" + data[i]['password'] + "</td></tr>";
        }
        document.getElementById("ajax").innerHTML = tableContent;

    });
}

function createUserExample(){
    var email = document.forms["createuserform"]["email"].value;
    var email1 = document.forms["createuserform"]["email1"].value;
    var firstname = document.forms["createuserform"]["firstname"].value;
    var lastname = document.forms["createuserform"]["lastname"].value;
    if(email === email1) {
        createUser(email, firstname, lastname);
    } else {
        return alert("email komen niet overeen");
    }
}

function createUser(email, firstname, lastname){
    var object = {
        "firstname" : firstname,
        "lastname": lastname,
        "emailadress": email,
        "active" : true,
        "password": null,
        "id": null,
        "adress" : null
    };
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/createuser", json);
}

function apiPostRequest(url, json) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST",url);
    var xmlDoc;
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            xmlDoc = xmlhttp.responseXML;
            console.log(xmlDoc);
        }
    };
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    xmlhttp.send(json);
}