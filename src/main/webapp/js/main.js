function submitForm(formID) {
    document.getElementById(formID).submit();
}

function setLimits() {
    setDateLimits();
    setTimeLimits();
}

function setDateLimits() {
    let dateElement = document.getElementById("date");
    dateElement.min = getCurrentDate();
    dateElement.max = getMaxDate();
}

function setTimeLimits() {
    let timeElement = document.getElementById("time");
    timeElement.min = getMinTime();
    timeElement.max = "21:00";
}

function setNewTimeLimits() {
    let dateElement = document.getElementById("date");
    let chosenDate = dateElement.value;

    if (chosenDate !== getCurrentDate()) {
        let timeElement = document.getElementById("time");
        timeElement.min = "09:00";
    } else {
        setTimeLimits();
    }
}

function getCurrentDate() {
    let curDate = new Date();

    let month = curDate.getMonth() + 1;
    let day = curDate.getDate();
    let year = curDate.getFullYear();

    return convertToString(year, month, day);
}

function getMaxDate() {
    let curDate = new Date();

    let month = curDate.getMonth() + 2;
    let day = curDate.getDate();
    let year = curDate.getFullYear();

    return convertToString(year, month, day);
}

function convertToString(year, month, day) {
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();

    return year + '-' + month + '-' + day;
}

function getMinTime() {
    let curDate = new Date();
    let hours = curDate.getHours() + 1;
    let minutes = curDate.getMinutes();

    return hours + ':' + minutes;
}