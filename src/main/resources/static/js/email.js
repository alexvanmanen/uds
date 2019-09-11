function setEmailAddress(receiver) {
    document.getElementById("receiver").value = receiver;
}
function sendEmail() {
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
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/sendmail", json);
}

function sendValidationMail(receiver, subject, message, timesheet) {
    var object = {
        "receiver": receiver,
        "subject": subject,
        "message": message,
        "timesheet" : timesheet
    };
    alert(object);
    var json = JSON.stringify(object);
    apiPostRequest("/uren/api/v1/sendTimesheetMail", json)

}
