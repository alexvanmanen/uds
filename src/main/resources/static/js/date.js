function calculateNumberOfDaysInMonth(month){
    return new Date(2019, month, 0).getDate();
}
function getMonthName(month) {
    var months = ["January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"];
   return months[(month-1)];
}
