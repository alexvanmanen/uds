
function buildTable(maand) {
    var tabel = "<tr><td>" + maand + "</td><td>uren</td><td>overwerk</td><td>ziekteverlof</td><td>vakantie</td><td>vakantie</td><td>vakantie</td><td>vakantie</td><td>vakantie</td><td>vakantie</td><td>vakantie</td><td>vakantie</td><td>vakantie</td></tr>";

    if (maand == "januari") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr class=''><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "februari") {
        for (var i = 1; i < 29; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "maart") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "april") {
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "mei") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "juni") {
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "juli") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "augustus") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "september") {
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "oktober") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "november") {
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "december") {
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    tabel+="<tr style='height: 100px'></tr>";
    tabel += "<tr><td>TOTAAL</td><td><input></td><td><input></td><td><input></td><td><input></td></tr>";
    tabel+="<button id='btn' onclick='Registreer()'>DeclareerUren</button>"
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