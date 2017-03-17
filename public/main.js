function getFiles(filesData) {
    for (var i in filesData) {
        var elem = $("<a>");
        elem.attr("href", "files/" + filesData[i].filename, filesData[i].filename);
        elem.text(filesData[i].comment);
        $("#fileList").append(elem);

        var space = $("<span style='padding-left: 2em;'></span>");
        $("#fileList").append(space);

        var deleteInput = $("<input>", {
            type: "password",
            placeholder: "Enter delete password",
            name: "deletePassword",
            id: filesData[i].id
        });
        $("#fileList").append(deleteInput);

        var space = $("<span style='padding-left: 2em;'></span>");
        $("#fileList").append(space);

        var deleteButton = $("<button>", {text: "Delete", onclick: "deleteFile(" + filesData[i].id + ")" }); //todo fix alert
        $("#fileList").append(deleteButton);


        var elem2 = $("<br>");
        $("#fileList").append(elem2);
    }
}

function deleteFile(id) {
    var password = $("#" + id).val();
    $.post("/deleteFile", {id: id, password: password}, function (data) {refresh();});
}

function refresh() {
    $("#fileList").empty();
    $.get("/files", getFiles);
}
refresh();