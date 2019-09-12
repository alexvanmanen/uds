function getProfile(id) {
    if (!id) {
         id = document.forms["getprofile"]["id"].value;
    }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText);
            document.getElementById("email").value = myObj.username;
            document.getElementById("id1").value = myObj.id;
            document.getElementById("password").value = myObj.password;
            document.getElementById("firstname").value = myObj.firstname;
            document.getElementById("lastname").value = myObj.lastname;
            document.getElementById("street").value = myObj.street;
            document.getElementById("housenumber").value = myObj.housenumber;
            document.getElementById("city").value = myObj.city;
            document.getElementById("zipcode").value = myObj.zipcode;
        }
    };
    xmlhttp.open("GET", "http://localhost:8080/uren/api/v1/getUser/" + id, true);
    xmlhttp.send();
}

function updateExample() {
    var id = document.forms["updateprofile"]["id1"].value;
    var email = document.forms["updateprofile"]["email"].value;
    var password = document.forms["updateprofile"]["password"].value;
    var firstname = document.forms["updateprofile"]["firstname"].value;
    var lastname = document.forms["updateprofile"]["lastname"].value;
    var avatar = document.forms["updateprofile"]["avatar"].value;
    var avatarcolor = document.forms["updateprofile"]["avatarcolor"].value;
    var street = document.forms["updateprofile"]["street"].value;
    var housenumber = document.forms ["updateprofile"]["housenumber"].value;
    var city = document.forms ["updateprofile"]["city"].value;
    var zipcode = document.forms ["updateprofile"]["zipcode"].value;
    updateUser(id, email, password, firstname, lastname, avatar, avatarcolor, street, housenumber, city, zipcode);
}

function updateUser(id, email, password, firstname, lastname, avatar, avatarcolor, street, housenumber, city, zipcode) {
    var object = {
        "id": id,
        "username": email,
        "password": password,
        "firstname": firstname,
        "lastname": lastname,
        "avatar": avatar,
        "avatarcolor": avatarcolor,
        "street": street,
        "housenumber": housenumber,
        "zipcode": zipcode,
        "city": city
    };
    var json = JSON.stringify(object);
    apiPutRequest("/uren/api/v1/updateUser/" + id, json);
}

function apiPutRequest(url, json) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", url);
    var xmlDoc;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            xmlDoc = xmlhttp.responseXML;
            console.log(xmlDoc);
        }
    };
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    xmlhttp.send(json);
}