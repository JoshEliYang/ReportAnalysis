/**
 * Created by johnson on 2016/10/28.
 */


setTimeout(function () {
    /* Dialog({
     type: "DATE_SELECTOR",
     btn: "NEXT_CANCEL"
     }, function (data) {
     console.log(data.date1);
     console.log(data.date2);
     });*/

    /*Dialog({
     type: "WARNING",
     contain: "警告信息",
     btn: "CONFIRM"
     });*/

    Dialog({
        type: "SELECTOR",
        btnList: ["btn1", "btn2", "btn3", "btn4", "btn5"]
    }, function callback(data) {
       /* Dialog({
            type: "WARNING",
            contain: "you have selected " + data,
            btn: "CONFIRM"
        });*/
    });
}, 500);