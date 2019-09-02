
function buildTable(month) {
    var tabel = "<tr><td>" + getMonthName(month) + "</td><td>Opdracht</td><td>Overwerk</td><td>Verlof</td><td>Ziek</td><td>Training</td><td>Overig</td><td>Verklaring overig</td></tr>";
    for (var i = 1; i < calculateNumberOfDaysInMonth(month)+1; i++) {
        tabel += "<tr 'month'><td>" + i + " " + getMonthName(month) + "</><td><input id='opdracht" + i + "' type='number'></td><td><input id='overwerk" + i + "' type='number'></td><td><input id='verlof" + i + "' type='number'></td><td><input id='" + i + "' type='number'></td><td><input id='training' type='number'></td><td><input id='overig" + i + "' type='number'></td><td><input id='verklaring' type='String'></td></tr>"
    }

    tabel+="<tr><br><tr\>";
    tabel += "<button id='btn' onclick='Registreer()'>Declareer Uren</button>"
    document.getElementById("tabel").innerHTML = tabel;
}

function Registreer(){
    var object = {
        "hoursSpent": document.getElementById("opdracht1").value,
        "dayOfTheMonth" : 1,
        "entryKind" : "WORK",
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


