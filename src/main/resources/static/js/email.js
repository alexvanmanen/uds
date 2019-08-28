function showEmailForm() {
    document.getElementById("ajax").style.display = "none";
    document.getElementById('mailform').style.display='block';
}
function sendEmail() {
    var receiver = document.forms["mailform"]["receiver"].value;
    var subject = document.forms["mailform"]["subject"].value;
    var message = document.forms["mailform"]["message"].value;
    sendMail(receiver, subject, message);
}

function sendMail(receiver, subject, message) {
    var object = {
        "receiver": receiver,
        "subject": subject,
        "message": message,
    };
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/sendmail", json);
}
