function myTestFunction(id){
    console.log(id);

}
var dataBase = [];

function Gebruikers(inlognaam, wachtwoord, level) {
    this.inlognaam = inlognaam;
    this.wachtwoord = wachtwoord;
    this.level = level
}

dataBase.push(new Gebruikers("Bart", "Bart01", 3));
dataBase.push(new Gebruikers("Robin", "Robin01",1));
dataBase.push(new Gebruikers("Kalim", "Kalim01",1));
dataBase.push(new Gebruikers("Maartje", "Maartje01",1));
dataBase.push(new Gebruikers("Dwight", "Dwight01",1));
dataBase.push(new Gebruikers("Alex", "Alex01",1));

function setSessie(a,b) {
    if(typeof(Storage) !== "undefined") {
        if (!sessionStorage.username) {
            sessionStorage.username = a;
        }
        if(!sessionStorage.wachtwoord) {
            sessionStorage.wachtwoord = b;
        }
        if(!sessionStorage.level) {
            sessionStorage.level = getlevel();
        }
    } else {
        document.getElementById("result").innerHTML = "Sorry, your browser does not support web storage...";
    }
}
function inlog() {
    var a = document.forms["inlogformulier"]["inlognaam"].value;
    var b = document.forms["inlogformulier"]["wachtwoord"].value;
    if (checkgebruiker(a)) {
        if (checkwachtwoord(b)) {
            setSessie(a,b);
            window.location.href = "./admin.html";
        } else {
            alert("Wachtwoord is fout!");
        }
    } else {
        alert("Onbekende gebruikersnaam");
    }
}

function checkgebruiker(inlognaam) {
    var i = 0;
    while (i < dataBase.length) {
        if (inlognaam == dataBase[i].inlognaam) {
            return true;
        }
        i = i + 1;
    }
    return false;
}

function checkwachtwoord(wachtwoord) {
    var i = 0;
    while (i < dataBase.length) {
        if (wachtwoord == dataBase[i].wachtwoord) {
            return true;
        }
        i = i + 1;
    }
    return false;
}

function wachtwoordvergeten() {
    var z = document.forms["wachtwoordvergetenformulier"]["inlognaam"].value;
    if (checkgebruiker(z)) {
        var wachtwoord = getwachtwoord();
        document.getElementById('inlog').innerHTML = "Hoi: " + z + ", je wachtwoord is: " + wachtwoord;
    } else {
        document.getElementById('inlog').innerHTML = "Deze gebruikersnaam is niet bekend.";
    }
}

function zoekWachtwoord(gebruikersnaam) {
    return gebruikersnaam.inlognaam == document.forms["wachtwoordvergetenformulier"]["inlognaam"].value;
}
function getwachtwoord() {
    var a = dataBase.find(zoekWachtwoord);
    return a.wachtwoord;
}
function zoekLevel(gebruikersnaam) {
    return gebruikersnaam.inlognaam == document.forms["inlogformulier"]["inlognaam"].value;
}
function getlevel() {
    var a = dataBase.find(zoekLevel);
    return a.level;
}