function getprojectprofile(id) {
    var email;
    var active;
    var projectname;
    var phonenumber;

    $.get("/uren/api/v1/getProject/" + id, function (project) {
        projectname = project.name;
        email = project.email;
        phonenumber = project.phonenumber;
        active = project.active;

        $("#email").val(email);
        $("#phonenumber").val(phonenumber);
        $("#projectname").val(projectname);
       /* $("#avatar").val(avatar);*/
        $("#active").val(active);

    if (active == true) {
        document.getElementById("activebtn").innerHTML = '<a class="btn btn-danger" onclick="activateproject(false, ' + id + ')" role="button">Deactiveer</a>';
    }
    if (active == false) {
        document.getElementById("activebtn").innerHTML = '<a class="btn btn-success" onclick="activateproject(true, ' + id + ')" role="button">activeer</a>';
    }
    });
}

function activateproject(setproject, id) {
    var url;
    var action;
    var active;
    if (setproject === true) {
        url = '/uren/api/v1/activateProject/' + id;
        action = '<a class="btn btn-danger" onclick="activateproject(false, ' + id + ')" role="button">Deactiveer</a>';
        active = true;
    } else if (setproject === false) {
        url = '/uren/api/v1/deactivateProject/' + id;
        action = '<a class="btn btn-success" onclick="activateproject(true, ' + id + ')" role="button">Activeer</a>';
        active = false;
    }
    $.ajax({
        url: url,
        method: 'PUT',
        contentType: 'application/json',
        success: function (result) {
            document.getElementById("activebtn").innerHTML = action;
            $("#active").val(active);
        },

    });
}

$(document).ready(function () {
    $("#updateproject").click(function () {
        var data = {
            name: document.forms["updateproject1"]["projectname"].value,
            email: document.forms["updateproject1"]["email"].value,
            phonenumber: document.forms["updateproject1"]["phonenumber"].value,
        };
        $.ajax({
            url: '/uren/api/v1/updateProject/' + document.forms["updateproject1"]["id"].value,
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                alert("project opgeslagen");
                getprojectprofile(document.forms["updateproject1"]["id"].value);
            },
            data: JSON.stringify(data)
        });
    });
});
