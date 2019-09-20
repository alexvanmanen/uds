function getProjects() {
    ajax_get('/uren/api/v1/getprojects', function (data) {
        var tableContent = "<tr><th>Email</th><th>Projectnaam</th><th>Wijzigen/ De-activeren</th></tr>";
        for (i = 0; i < data.length; i++) {
            var email = data[i]['email'];
            var name = data[i]['name'];
            var id = data[i]['id'];
            tableContent = tableContent +
                '<tr><td>' + email + '</td>' +
                '<td>' + name + '</td>'+
                '<td><button type="button" class="btn btn-outline-secondary" onclick="showEditUser(`' + id + '`);">Wijzig/ De-activeer</button> </td></tr>';
        }
        document.getElementById("projecttable").innerHTML = tableContent;

    });
}

function dropDownProjects() {
    ajax_get('/uren/api/v1/getprojects', function (data) {
        var tableContent;
        for (i = 0; i < data.length; i++) {
            var email = data[i]['email'];
            var name = data[i]['name'];
            var id = data[i]['id'];
            tableContent += "<option>" + name + "</option>";

        }
        document.getElementById("robin").innmerHtml = tableContent;

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

$(document).ready(function(){
    $("#saveproject").click(function(){
        var data = {
            name: document.forms["createprojectform"]["projectname"].value,
            email: document.forms["createprojectform"]["email"].value
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
