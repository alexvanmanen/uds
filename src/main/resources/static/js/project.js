function getProjects() {
    ajax_get('/uren/api/v1/getprojects', function (data) {
        var tableContent = "<tr><th>E-mail</th><th>Telefoonnummer</th><th>Projectnaam</th><th>Wijzigen/ De-activeren</th></tr>";
        for (i = 0; i < data.length; i++) {
            var email = data[i]['email'];
            var phonenumber = data[i]['phonenumber'];
            var name = data[i]['name'];
            var id = data[i]['id'];
            var active = data[i]['active'];
            tableContent = tableContent +
                '<tr><td>' + email + '</td>' +
                '<td>' + phonenumber + '</td>' +
                '<td>' + name + '</td>' +
                '<td><button type="button" class="btn btn-outline-secondary" onclick="showEditProject(`' + id + '`);">Wijzig/ De-activeer</button> </td></tr>';
        }
        document.getElementById("projecttable").innerHTML = tableContent;

    });
}

function showEditProject(id){
    window.location.assign( "./projectprofile.html?id=" + id)
}

function dropDownProjects() {
    ajax_get('/uren/api/v1/getprojects', function (data) {
        var tableContent;
        for (i = 0; i < data.length; i++) {
            var name = data[i]['name'];
            var id = data[i]['id'];
            tableContent = tableContent +
                "<option id='project' value='" + id + "'>" + name + "</option>";

        }
        document.getElementById("dropdownprojects").innerHTML = tableContent;

    });
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

$(document).ready(function () {
    $("#saveproject").click(function () {
        var data = {
            name: document.forms["createprojectform"]["projectname"].value,
            email: document.forms["createprojectform"]["email"].value,
            phonenumber: document.forms["createprojectform"]["phonenumber"].value
        };
        $.ajax({
            url: '/uren/api/v1/createProject',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                getProjects();
            },
            data: JSON.stringify(data)
        });
    });
});

function saveProject(id) {
    var data = {
        id: id,
        project: {
            id: document.forms["useradmin"]["dropdownprojects"].value
        },
    };
    $.ajax({
        url: '/uren/api/v1/editProject',
        type: 'put',
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            alert("Project aangepast");
        },
        data: JSON.stringify(data)
    });
}
