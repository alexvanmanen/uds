
function buildTable(maand){
    var tabel= "<th>" + maand + "</th>";
    if(maand == "januari"){
        //alert("hoi");
        for( var i = 1 ; i < 32 ; i++){
        tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td></tr>"
        }
        tabel+="<tr><td><input id='totaal'>totaal</td></tr>";
        document.getElementById("tabel").innerHTML = tabel;
    }


}




function Uren(){
    Registreer(1,1,8,"2019-09-19");
}

function Registreer(id, projectid, aantalUren,datum){
    var object = {
        "id": id,
        "projectid": projectid,
        "aantalUren": aantalUren,
        "datum" : datum
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