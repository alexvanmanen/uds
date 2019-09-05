function showEmailForm() {
    document.getElementById("ajax").style.display = "none";
    document.getElementById('mailform').style.display="block";
}
function sendEmail() {
    document.getElementById("ajax").style.display = "block";
    document.getElementById('mailform').style.display="none";
    document.getElementById("verzonden").innerHTML = "E-mail verzonden";
    var receiver = document.forms["mailform"]["receiver"].value;
    var subject = document.forms["mailform"]["subject"].value;
    var message = document.forms["mailform"]["message"].value;
    sendMail(receiver, subject, message);
}
function sendResetMail() {
    var receiver = document.forms["emailResetForm1"]["email1"].value;
    var subject = "Qien wachtwoord vergeten";
    var message = "FORGETPASSWORD";
    sendMail(receiver, subject, message);
}

function sendMail(receiver, subject, message) {
    var object = {
        "receiver": receiver,
        "subject": subject,
        "message": message,
    };
    alert(object);
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/sendmail", json);
}
