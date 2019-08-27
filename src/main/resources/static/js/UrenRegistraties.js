function ajax_get(url, callback) {
    alert("hoi");
    var xmlhttp = new XMLHttpRequest();
    console.log(xmlhttp);
    xmlhttp.onreadystatechange = function() {
        alert("in functie")
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
    xmlhttp.open("POST", url, true);
    alert("na open");
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send();
}

function registreer() {
    var totaal = 0;
    var i;

    var a = document.getElementsByClassName("MAAND");
    console.log(a.length);

    for (i = 0; i < document.getElementsByClassName("MAAND").length; i++) {
        var b = a[i].value;
        var c = Number(b);
        totaal += c;
        console.log("value: " + totaal);

    }

    document.getElementById("totaal").value = totaal;

}


function doorverwijzen(){
    window.location.assign("https://www.w3schools.com");
}



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

function postAjax(url, data, success) {
    var params = typeof data == 'string' ? data : Object.keys(data).map(
        function (k) {
            return encodeURIComponent(k) + '=' + encodeURIComponent(data[k])
        }
    ).join('&');

    var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    xhr.open('POST', url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState > 3 && xhr.status == 200) {
            success(xhr.responseText);
        }
    };
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(params);
    return xhr;
}

function sendData() {
    $.ajax({
        url: 'http://localhost:8080/uren/api/v1//urenRegistratie/1/2/8/1993-09-19',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            firstName: "Bob",
            lastName: "Martin",
            emailId: "test@test.nl"
        }),
        dataType: 'json'
    });
}