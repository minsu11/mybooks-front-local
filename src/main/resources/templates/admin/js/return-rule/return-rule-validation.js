function returnRuleValidation(name) {
    console.log(name);
    if (name.returnName === "") {
        name.returnName.focus();
        alert("이름이 공백입니다.")
        return false;
    } else if (name.returnName.size() < 4) {
        name.returnName.focus();
        alert("4글자 이상")
        return false;
    } else if (name.returnName.size() > 50) {
        name.returnName.focus();
        alert("50글자 이하");
        return false;
    }
    return true;
}