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


function getTimesheetDay(timesheetEntries){
    var entriesPerDay = {
        WORK: 1,
        LEAVE_OF_ABSENCE: 2,
        ILL: 3,
        TRAINING: 4,
        OVERTIME: 5,
        OTHERS: 6
    }
    return entriesPerDay;
}