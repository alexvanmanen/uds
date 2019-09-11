function getprofile(id) {
    var email;
    var firstname;
    var lastname;
    var active;
    $.get("/uren/api/v1/getUser/" + id, function (user) {
        firstname = user.firstname;
        email = user.username;
        lastname = user.lastname;
        active = user.active
        document.getElementById("email").innerHTML = email;
        document.getElementById("firstname").innerHTML = firstname;
        document.getElementById("lastname").innerHTML = lastname;
        document.getElementById("active").innerHTML = active;
        if (active == true) {
            document.getElementById("activebtn").innerHTML = '<a class="btn btn-danger" onclick="activateuser(false, '+ id + ')" role="button">Deactiveer</a>';

        }
        if (active == false) {
            document.getElementById("activebtn").innerHTML = '<a class="btn btn-success" onclick="activateuser(true, '+ id + ')" role="button">activeer</a>';
        }
    });
}
function activateuser(setuser, id) {
    var url;
    var action;
    var active;
    if (setuser === true) {
        url = '/uren/api/v1/activateUser/' + id;
        action = '<a class="btn btn-danger" onclick="activateuser(false, '+ id + ')" role="button">Deactiveer</a>';
        active = true;
    }
    else if(setuser === false) {
        url = '/uren/api/v1/deactivateUser/' + id;
        action = '<a class="btn btn-success" onclick="activateuser(true, '+ id + ')" role="button">activeer</a>';
        active = false;
    }
    $.ajax({
        url: url,
        method: 'PUT',
        contentType: 'application/json',
        success: function (result) {
            document.getElementById("activebtn").innerHTML = action;
            document.getElementById("active").innerHTML = active;
        },
        error: function (request, msg, error) {
            alert('strond aan de knikker');
        }
    });
}