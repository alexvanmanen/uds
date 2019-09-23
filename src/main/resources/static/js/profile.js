function getprofile(id) {
    var id1;
    var email;
    var firstname;
    var lastname;
    var street;
    var housenumber;
    var zipcode;
    var city;
    var avatar;
    var avatarcolor;
    var accountnumber;
    var phonenumber;
    var birthdate;
    var project;
    $.get("/uren/api/v1/getUser/" + id, function (user) {
            id1 = user.id
            firstname = user.firstname;
            email = user.username;
            lastname = user.lastname;
            zipcode = user.zipcode;
            street = user.street;
            housenumber = user.housenumber;
            city = user.city;
            avatar = user.avatar;
            if (avatar == null) {
                avatar = "giraffe_lg.png";
            }
            avatarcolor = user.avatarcolor;
            if (avatarcolor == null) {
                avatarcolor = "#A34AFE";
            }
            accountnumber = user.accountnumber;
            phonenumber = user.phonenumber;
            birthdate = user.dateofbirth;
            $("#email").val(email);
            $("#id1").val(id1);
            $("#firstname").val(firstname);
            $("#lastname").val(lastname);
            $("#street").val(street);
            $("#housenumber").val(housenumber);
            $("#phonenumber").val(phonenumber);
            $("#accountnumber").val(accountnumber);
            $("#dateofbirth").val(birthdate);
            $("#zipcode").val(zipcode);
            $("#city").val(city);
            $("#avatar").val(avatar);
            $("#avatarcolor").val(avatarcolor);
        }
    )
    ;
}

$(document).ready(function () {
    $("#saveprofile").click(function () {
        var data = {
            firstname: document.forms["updateprofile"]["firstname"].value,
            id: document.forms["updateprofile"]["id1"].value,
            lastname: document.forms["updateprofile"]["lastname"].value,
            username: document.forms["updateprofile"]["email"].value,
            street: document.forms["updateprofile"]["street"].value,
            housenumber: document.forms["updateprofile"]["housenumber"].value,
            zipcode: document.forms["updateprofile"]["zipcode"].value,
            city: document.forms["updateprofile"]["city"].value,
            avatar: document.forms["updateprofile"]["avatar"].value,
            avatarcolor: document.forms["updateprofile"]["avatarcolor"].value,
            dateofbirth: document.forms["updateprofile"]["dateofbirth"].value,
            accountnumber: document.forms["updateprofile"]["accountnumber"].value,
            phonenumber: document.forms["updateprofile"]["phonenumber"].value,
        };
        $.ajax({
            url: '/uren/api/v1/updateUser/' + document.forms["updateprofile"]["id1"].value,
            type: 'put',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                alert("profiel opgeslagen");
                getprofile(document.forms["updateprofile"]["id1"].value);
            },
            data: JSON.stringify(data)
        });
    });
});
