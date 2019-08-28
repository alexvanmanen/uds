
function buildTable(maand) {
    var tabel = "<tr><td>" + maand + "</td><td>uren</td><td>overwerk</td><td>ziekteverlof</td><td>vakantie</td></tr>";
    if (maand == "januari") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "februari") {
        //alert("hoi");
        for (var i = 1; i < 29; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "maart") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "april") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "mei") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "juni") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "juli") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "augustus") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "september") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "oktober") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "november") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "december") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    tabel+="<tr><br><tr\>";
    tabel += "<tr><td>TOTAAL</td><td><input></td><td><input></td><td><input></td><td><input></td></tr>";
    document.getElementById("tabel").innerHTML = tabel;
}

function Registreer(){
    var object = {
        "employeeId": 500
    };
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/urenRegistratie", json);
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

function getUren(){
    ajax_get("/uren/api/v1/urencount",function(data){
        if (data == 0) {
            alert("dit is mijn data: " + data);
        }
    });
}