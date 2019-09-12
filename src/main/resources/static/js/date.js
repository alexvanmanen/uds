function calculateNumberOfDaysInMonth(month, year){
    return new Date(year, month,0).getDate();
}
function getMonthName(month) {
    var months = ["Januari", "Februari", "Maart", "April", "Mei", "Juni",
        "Juli", "Augustus", "September", "October", "November", "December"];
   return months[(month-1)];
}
function getDayName(dateStr, locale)
{
    var date = new Date(dateStr);
    return date.toLocaleDateString(locale, { weekday: 'long' });
}

var dateStr = '05/23/2014';
var day = getDayName(dateStr, "nl-NL");