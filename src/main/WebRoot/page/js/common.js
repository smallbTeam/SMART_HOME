function isNotNull(id) {
    if ($(id).val() != "" && $(id).val() != null && $(id).val() != undefined) {
        return true;
    }
    return false;
}
