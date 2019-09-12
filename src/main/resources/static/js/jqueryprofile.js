function getprofile(id) {
    var email;
    var firstname;
    var lastname;
    var active;
    var street;
    var housenumber;
    var zipcode;
    var city;
    var avatar;
    var avatarcolor;
    $.get("/uren/api/v1/getUser/" + id, function (user) {
        var url = "https://ssl.gstatic.com/docs/common/profile/";
        var avatarwidth = "width:80px; background-color: ";
            firstname = user.firstname;
            email = user.username;
            lastname = user.lastname;
            active = user.active;
            zipcode = user.zipcode;
            street = user.street;
            housenumber = user.housenumber;
            city = user.city;
            avatar = user.avatar;
            avatarcolor = avatarwidth.concat(user.avatarcolor);

            $("#email").val(email);
            $("#firstname").val(firstname);
            $("#lastname").val(lastname);
            $("#active").val(active);
            $("#street").val(street);
            $("#housenumber").val(housenumber);
            $("#zipcode").val(zipcode);
            $("#city").val(city);
            $("#avatar").attr({
                "src": url.concat(avatar),
                "style": avatarcolor,
            });
            $("#avatarcolor").text(avatarcolor);

            if (active == true) {
                document.getElementById("activebtn").innerHTML = '<a class="btn btn-danger" onclick="activateuser(false, ' + id + ')" role="button">Deactiveer</a>';
            }
            if (active == false) {
                document.getElementById("activebtn").innerHTML = '<a class="btn btn-success" onclick="activateuser(true, ' + id + ')" role="button">activeer</a>';
            }
        }
    )
    ;
}

function activateuser(setuser, id) {
    var url;
    var action;
    var active;
    if (setuser === true) {
        url = '/uren/api/v1/activateUser/' + id;
        action = '<a class="btn btn-danger" onclick="activateuser(false, ' + id + ')" role="button">Deactiveer</a>';
        active = true;
    } else if (setuser === false) {
        url = '/uren/api/v1/deactivateUser/' + id;
        action = '<a class="btn btn-success" onclick="activateuser(true, ' + id + ')" role="button">activeer</a>';
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
            alert('strond aan de knikker');
        }
    });
}