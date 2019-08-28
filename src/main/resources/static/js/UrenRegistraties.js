
function buildTable(maand) {
    var tabel = "<tr><td>" + maand + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td></tr><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";
    if (maand == "Januari") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='opdracht' type='number'></td><td><input class='overwerk' type='number'></td><td><input class='verlof' type='number'></td><td><input class='ziek' type='number'></td><td><input class='training' type='number'></td><td><input class='overig' type='number'></td><td><input id='verklaring' class='verklaring' type='String'></td></tr>"
        }
    }
    if (maand == "Februari") {
        //alert("hoi");
        for (var i = 1; i < 29; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "Maart") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "April") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "Mei") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "Juni") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "Juli") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "Augustus") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "September") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "Oktober") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "November") {
        //alert("hoi");
        for (var i = 1; i < 31; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td><td><input class='MAAND' type='number'></td></tr>"
        }
    }
    if (maand == "December") {
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