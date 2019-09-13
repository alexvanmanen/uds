function timesheet(yearAndMonth, state, entries, id){
    var timesheet = {
        id: id,
        yearAndMonth : yearAndMonth,
        state  : state,
        entries: entries
    };
    return timesheet;
}

function timesheetEntry(hoursSpent, dayOftheMonth, entryKind){
    var entry = {
        hoursSpent : hoursSpent,
        dayOfTheMonth  : dayOftheMonth,
        entryKind: entryKind
    };
    return entry;
}


function getTimesheetDay(timesheet, day){

    var entriesPerDay = {
        work: getHours(timesheet, day, "WORK"),
        leaveOfAbsence: getHours(timesheet, day, "LEAVE_OF_ABSENCE"),
        ill: getHours(timesheet, day, "ILL"),
        training: getHours(timesheet, day, "TRAINING"),
        overtime: getHours(timesheet, day, "OVERTIME"),
        others: getHours(timesheet, day, "OTHERS")
    }
    return entriesPerDay;
}

function getHours(timesheet, day, type){
    for(var i = 0; i<timesheet.entries.length; i++){
        var entry = timesheet.entries[i];
        if(entry.dayOfTheMonth == day && entry.entryKind == type){
            return entry.hoursSpent;
        }
    }
    return "-";
}
