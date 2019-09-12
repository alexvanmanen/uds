function timesheet(yearMonth, state, entries, id){
    var timesheet = {
        id: id,
        yearMonth : yearMonth,
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
        work: 1,
        leaveOfAbsence: 2,
        ill: 3,
        training: 4,
        overtime: 5,
        others: 6
    }
    return entriesPerDay;
}