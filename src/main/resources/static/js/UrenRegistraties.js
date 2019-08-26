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

function registreer() {
    var totaal = 0;
    var i;

    var a = document.getElementsByClassName("MAAND");
    console.log(a.length);

    for (i = 0; i <= 30; i++) {
        var b = a[i].value;
        var c = Number(b);
        totaal += c;
        console.log("value: " + totaal);

    }

    totaal = document.getElementById("totaal").value;

    ajax_get("/urenRegistratie/5/1/" + totaal + "/1993-09-19", function (data) {
        if (data === true) {
            window.location.assign("./legePagina.html");
        } else window.location.assign("./legePagina.html")
    });
}


function doorverwijzen() {
    window.location.assign("https://www.w3schools.com");
}

function doorverwijzenHTML() {
    window.location.assign("./legePagina.html");
}

function methode() {
    window.location.assign("./UrenDeclaratieFormulier.html");
}

function buildTable(maand) {
    String; ziekteUren = "Ziekte Uren"
    String; overwerkUren = "Uren Overgewerkt"
    String; verlofUren = "Uren Verlof"
    String; urenGewerkt = "Uren Gewerkt"
    var tabel = "<th>" + maand + "</th>" + "<th>" + urenGewerkt + "</th>" + "<th>" + ziekteUren + "</th>" + "<th>" + overwerkUren + "</th>" + "<th>" + verlofUren + "</th>";
    if (maand == "januari") {
        //alert("hoi");
        for (var i = 1; i < 32; i++) {
            tabel += "<tr><td>" + i + " " + maand + "</td><td><input class='MAAND' type='number'></td><td><input class='ZIEKTEUREN' type='number'></td><td><input class='OVERWERKUREN' type='number'</td><td><input class='VERLOFUREN' type='number'</td></td></tr>"
        }
        tabel += "<tr><td><input id='TOTAAL'>totaal</td></tr>";
        document.getElementById("tabel").innerHTML = tabel;
    }


}