/**
 * Created by johnson on 2016/10/29.
 */


var dialogTitles = {
    "WARNING": "<h2>警告</h2>",
    "DATE_SELECTOR": "<h2>请选择日期</h2>",
    "SELECTOR": "<h2>请选择</h2>"
};

var dialogContainers = {
    "DATE_SELECTOR": '<div class="dialogContainer"><label for="dialog_input1">开始时间:</label><input id="dialog_input1" type="date"><label for="dialog_input2">结束时间:</label><input id="dialog_input2" type="date"><p class="dialogError">不能为空！</p></div>'
};

var dialogBtnPanels = {
    'CONFIRM': '<div class="dialogBtnPanel"><button onclick="dialogConfirm();">确定</button></div>',
    'CONFIRM_CANCEL': '<div class="dialogBtnPanel"><button onclick="dialogConfirm();">确定</button><button onclick="dialogCancel()">取消</button></div>',
    'NEXT_CANCEL': '<div class="dialogBtnPanel"><button onclick="dialogConfirm();">下一步</button><button onclick="dialogCancel()">取消</button></div>'
};

var callbackFnc;
var dialog_type;

function Dialog(data, callback) {
    callbackFnc = callback;

    var dialogs = document.getElementsByClassName("dialog");
    dialogs[0].style.visibility = "visible";
    dialogs[0].innerHTML = "";

    if (data.type == "DATE_SELECTOR") {
        dialog_type = "DATE_SELECTOR";
        if (!data.hasOwnProperty("height")) {
            data.height = 180;
        }
        dialogs[0].style.height = data.height + "px";
        dialogs[0].style.marginTop = "-" + data.height / 2 + "px";

        dialogs[0].innerHTML += dialogTitles.DATE_SELECTOR;
        dialogs[0].innerHTML += dialogContainers.DATE_SELECTOR;
    } else if (data.type == "WARNING") {
        dialog_type = "WARNING";
        if (!data.hasOwnProperty("height")) {
            data.height = 150;
        }
        dialogs[0].style.height = data.height + "px";
        dialogs[0].style.marginTop = "-" + data.height / 2 + "px";

        dialogs[0].innerHTML += dialogTitles.WARNING;
        dialogs[0].innerHTML += '<div class="dialogContainer">' + data.contain + '</div>';
    } else if (data.type == "SELECTOR") {
        dialog_type = "SELECTOR";
        if (!data.hasOwnProperty("height")) {
            data.height = 200;
        }
        dialogs[0].style.height = data.height + "px";
        dialogs[0].style.marginTop = "-" + data.height / 2 + "px";

        dialogs[0].innerHTML += dialogTitles.SELECTOR;
        dialogs[0].innerHTML += '<div class="dialogContainer">';
        for (var i = 0; i < data.btnList.length; i++) {
            dialogs[0].innerHTML += '<button class="dialogButtonSelector" onclick="dialogConfirm(\'' + data.btnList[i] + '\')">' + data.btnList[i] + '</button>';
        }
        dialogs[0].innerHTML += '</div>';
    }

    if (data.hasOwnProperty("btn")) {
        if (data.btn == "NEXT_CANCEL") {
            dialogs[0].innerHTML += dialogBtnPanels.NEXT_CANCEL;
        } else if (data.btn == "CONFIRM_CANCEL") {
            dialogs[0].innerHTML += dialogBtnPanels.CONFIRM_CANCEL;
        } else if (data.btn == "CONFIRM") {
            dialogs[0].innerHTML += dialogBtnPanels.CONFIRM;
        }
    }
}

function dialogConfirm(arg) {
    if ("DATE_SELECTOR" == dialog_type) {
        var date1 = document.getElementById("dialog_input1").value;
        var date2 = document.getElementById("dialog_input2").value;
        if (date1 == "" || date2 == "") {
            document.getElementsByClassName("dialogError")[0].style.visibility = "visible";
            return;
        }
        var data = {
            "date1": date1,
            "date2": date2
        };
        dialogCancel();
        callbackFnc(data);
    } else if ("WARNING" == dialog_type) {
        dialogCancel();
    } else if ("SELECTOR" == dialog_type) {
        dialogCancel();
        callbackFnc(arg);
    }
}

function dialogCancel() {
    document.getElementsByTagName("h2")[0].style.visibility = "hidden";
    if (document.getElementsByClassName("dialogBtnPanel").length > 0)
        document.getElementsByClassName("dialogBtnPanel")[0].style.visibility = "hidden";
    if (document.getElementsByClassName("dialogContainer").length > 0)
        document.getElementsByClassName("dialogContainer")[0].style.visibility = "hidden";

    var errors = document.getElementsByClassName("dialogError");
    for (var i = 0; i < errors.length; i++) {
        errors[i].style.visibility = "hidden";
    }

    var dialogs = document.getElementsByClassName("dialog");
    dialogs[0].style.height = "1px";
    dialogs[0].style.marginTop = "0";
    dialogs[0].style.visibility = "hidden";
}
