function calculateNumberOfDaysInMonth(month){
    return new Date(2019, month, 0).getDate();
}
function getMonthName(month) {
    var months = ["Januari", "Februari", "Maart", "April", "Mei", "Juni",
        "Juli", "Augustus", "September", "October", "November", "December"];
   return months[(month-1)];
}
