
function buildTable(maand) {
<<<<<<< Updated upstream
    var tabel = "<tr><td>" + maand + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";

        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr 'maand'><td>" + i + " " + maand + "</><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
        }

    // if (maand == "Februari") {
    //     //alert("hoi");
    //     for (var i = 1; i < 29; i++) {
    //         tabel += "<tr class='dagfeb'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "Maart") {
    //     //alert("hoi");
    //     for (var i = 1; i < 32; i++) {
    //         tabel += "<tr class='dagmrt'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "April") {
    //     //alert("hoi");
    //     for (var i = 1; i < 31; i++) {
    //         tabel += "<tr class='dagapr'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "Mei") {
    //     //alert("hoi");
    //     for (var i = 1; i < 32; i++) {
    //         tabel += "<tr class='dagmei'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "Juni") {
    //     //alert("hoi");
    //     for (var i = 1; i < 31; i++) {
    //         tabel += "<tr class='dagjun'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "Juli") {
    //     //alert("hoi");
    //     for (var i = 1; i < 32; i++) {
    //         tabel += "<tr class='dagjul'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "Augustus") {
    //     //alert("hoi");
    //     for (var i = 1; i < 32; i++) {
    //         tabel += "<tr class='dagaug'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "September") {
    //     //alert("hoi");
    //     for (var i = 1; i < 31; i++) {
    //         tabel += "<tr class='dagsep'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "Oktober") {
    //     //alert("hoi");
    //     for (var i = 1; i < 32; i++) {
    //         tabel += "<tr class='dagokt'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "November") {
    //     //alert("hoi");
    //     for (var i = 1; i < 31; i++) {
    //         tabel += "<tr class='dagnov'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
    // if (maand == "December") {
    //     //alert("hoi");
    //     for (var i = 1; i < 32; i++) {
    //         tabel += "<tr class='dagdec'><td>" + i + " " + maand + "</td><td><input id='opdracht' type='number'></td><td><input id='overwerk' type='number'></td><td><input id='verlof' type='number'></td><td><input id='ziek' type='number'></td><td><input id='training' type='number'></td><td><input id='overig' type='number'></td><td><input id='verklaring' type='String'></td></tr>"        }
    // }
=======
    var tabel = "<tr><td>" + maand + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>"
        for (var i = 1; i < 32; i++) {
            tabel += "<tr class='dag "+ maand +"'><td>" + i + " " + maand + "</><td><input id='opdracht"+i+"' type='number'></td><td><input id='overwerk"+i+"' type='number'></td><td><input id='verlof"+i+"' type='number'></td><td><input id='ziek"+i+"' type='number'></td><td><input id='training"+i+"' type='number'></td><td><input id='overig"+i+"' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
    }

>>>>>>> Stashed changes
    tabel+="<tr><br><tr\>";
    tabel += "<button id='btn' onclick='Registreer()'>Declareer Uren</button>"
    document.getElementById("tabel").innerHTML = tabel;
}

function Registreer(){
    var object = {
        "uren": document.getElementById("opdracht").value,
        "dag" : "1993-09-19"
    };
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/createTimesheetEntry", json);
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


