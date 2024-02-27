function validationForm(name) {
    if (name === "") {
        form.name.focus();
        alert("카테고리 이름은 공백일 수 없습니다.");
        return false;
    } else if (name.length < 1) {
        form.name.focus();
        alert("카테고리 이름은 1글자 이상이어야합니다.");
        return false;
    } else if (name.length > 20) {
        form.name.focus();
        alert("카테고리 이름은 20글자 이하여야합니다.");
        form.name.value = "";
        return false;
    }
    return true;
}