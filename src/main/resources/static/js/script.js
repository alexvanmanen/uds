function myTestFunction(id){
    console.log(id);

}
var dataBase = [];

function Gebruikers(email, password, level) {
    this.email = email;
    this.password = password;
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
    var a = document.forms["loginform"]["email"].value;
    var b = document.forms["loginform"]["password"].value;
    if (checkuser(a)) {
        if (checkpassword(b)) {
            setSessie(a,b);
            window.location.href = "./admin.html";
        } else {
            alert("Wrong password!");
        }
    } else {
        alert("Unknown username!");
    }
}

function checkuser(email) {
    var i = 0;
    while (i < dataBase.length) {
        if (inlognaam == dataBase[i].email) {
            return true;
        }
        i = i + 1;
    }
    return false;
}

function checkpassword(password) {
    var i = 0;
    while (i < dataBase.length) {
        if (wachtwoord == dataBase[i].password) {
            return true;
        }
        i = i + 1;
    }
    return false;
}

function forgetpassword() {
    var z = document.forms["forgetpassword"]["email"].value;
    if (checkuser(z)) {
        var password = getpassword();
        document.getElementById('login').innerHTML = "Hello: " + z + ", your password is: " + password;
    } else {
        document.getElementById('login').innerHTML = "Username unknown!";
    }
}

function findPassword(gebruikersnaam) {
    return gebruikersnaam.inlognaam == document.forms["forgetpassword"]["email"].value;
}
function getpassword() {
    var a = dataBase.find(findPassword);
    return a.wachtwoord;
}
function findLevel(gebruikersnaam) {
    return gebruikersnaam.inlognaam == document.forms["loginform"]["email"].value;
}
function getlevel() {
    var a = dataBase.find(findLevel);
    return a.level;
}