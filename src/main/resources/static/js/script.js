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

function  getUsers() {
    ajax_get('/uren/api/v1/users', function (data) {
        var tableContent = "<tr><th>Email</th><th>Naam</th><th>Wijzigen/ De-activeren</th><th>Contact</th></tr>";
        for (i = 0; i < data.length; i++) {
            var email = data[i]['username'];
            var firstname = data[i]['firstname'];
            var id = data[i]['id'];
            tableContent = tableContent +
                '<tr><td>' + email + '</td>' +
                '<td>' + firstname + '</td>'+
                '<td><button type="button" class="btn btn-outline-secondary" onclick="showEditUser(`' + id + '`);">Wijzig/ De-activeer</button> </td>' +
                '<td><button type="button" onclick="setEmailAddress(`' + email + '`);" class="btn btn-outline-secondary" data-toggle="modal" data-target="#mailfade">Stuur E-mail</button></td></tr>';
        }
        document.getElementById("usertable").innerHTML = tableContent;

    });
}

function showEditUser(id){
 //   document.getElementById("editUser").innerHTML= " ID is "+ id  ;
    window.location.assign( "./profile.html?id=" + id)
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
var retrievedTimesheets = "";
$(document).ready(function(){
        $.get("/uren/api/v1/getAllTimeSheetsByEmployee/"+getEmployeeId(), function(timesheets, status){
            retrievedTimesheets = timesheets;
            for(var id in timesheets){
                $("#taskOverview").append("<tr onclick='buildHourTable2("+id+")'><td >"+timesheets[id].yearMonth +"</td><td>TODO</td><td>"+timesheets[id].state +"</td></tr>");
            }
        });
});

$(window).scroll(function() {
    var winScrollTop = $(window).scrollTop();
    var winHeight = $(window).height();
    var floaterHeight = $('#hamburger_knop, #hamburger').outerHeight(true);
    //true so the function takes margins into account
    var frombottom = 580;

    var top = winScrollTop + winHeight - floaterHeight - frombottom;
    $('#hamburger_knop, #hamburger').css({'top': top + 'px'});
});
function apiPutRequest(url, json) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", url);
    var xmlDoc;
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            xmlDoc = xmlhttp.responseXML;
            console.log(xmlDoc);
        }
    };
    xmlhttp.setRequestHeader('Content-Type', 'application/json');
    xmlhttp.send(json);
}
