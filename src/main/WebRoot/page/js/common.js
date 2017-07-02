function isNotNull(id) {
    if ($(id).val() != "" && $(id).val() != null && $(id).val() != undefined) {
        return true;
    }
    return false;
}

function isNotNullOfStr(str) {
    if (str != "" && str != null && str != undefined) {
        return true;
    }
    return false;
}


