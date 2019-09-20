function getprojectprofile(id) {
    var email;
    var active;
    var projectname;
    var avatar = "giraffe_lg.png"
    var avatarcolor = "#A34AFE";

    $.get("/uren/api/v1/getProject/" + id, function (project) {
            var url = "https://ssl.gstatic.com/docs/common/profile/";
            var avatarwidth = "width:80px; background-color: ";
            projectname = project.name;
            email = project.email;
            active = project.active;

            if(user.avatar != null) {
                avatar = user.avatar;
            }
            if(user.avatarcolor != null) {
                avatarcolor = user.avatarcolor;
            }

            $("#email").val(email);
            $("#projectname").val(projectname);
            $("#active").val(active);

            $("#avatar").attr({
                "src": url.concat(avatar),
                "style": avatarwidth.concat(avatarcolor),
            });
            $("#avatarcolor").text(avatarcolor);

            if (active == true) {
                document.getElementById("activebtn").innerHTML = '<a class="btn btn-danger" onclick="activateproject(false, ' + id + ')" role="button">Deactiveer</a>';
            }
            if (active == false) {
                document.getElementById("activebtn").innerHTML = '<a class="btn btn-success" onclick="activateproject(true, ' + id + ')" role="button">activeer</a>';
            }
        }
    )
    ;
}

function activateproject(setproject, id) {
    var url;
    var action;
    var active;
    if (setuser === true) {
        url = '/uren/api/v1/activateUser/' + id;
        action = '<a class="btn btn-danger" onclick="activateproject(false, ' + id + ')" role="button">Deactiveer</a>';
        active = true;
    } else if (setuser === false) {
        url = '/uren/api/v1/deactivateUser/' + id;
        action = '<a class="btn btn-success" onclick="activateproject(true, ' + id + ')" role="button">activeer</a>';
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
        error: function (request, msg, error) {
            alert('stront aan de knikker');
        }
    });
}
