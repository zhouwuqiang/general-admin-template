/**
 * 初始化input输入框
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function inputFormatter(value, row, index) {

    let self = this;

    if ($.isNull(value)) {
        value = "";
    }

    if (row.editable) {
        return "<input type='text' value='" + value + "' class='form-control' id='" + index + self.field + "' name='" + self.field + "' onblur='inputEdit(this)'>";
    }

    return "<input type='text' readonly='readonly' value='" + value + "' class='form-control' id='" + index + self.field + "' name='" + self.field + "'>";
}


/**
 * 初始化select输入框
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function selectFormatter(value, row, index) {
    let self = this;
    return "<select type='text' class='form-control' id='" + index + self.field + "' name='" + self.field + "' onblur='inputEdit(this)'></select>";
}


function inputEdit(self) {
    console.log($(self).val());
}
