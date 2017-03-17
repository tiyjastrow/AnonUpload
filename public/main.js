function getFiles(filesData) {
    for (var i in filesData) {
        var input = $("<input>", {type:"checkbox",
            onclick: "swapValue(" + filesData[i].id + ")",
            checked: filesData[i].toSave
        });
        $("#fileList").append(input);
        var elem = $("<a>");
        elem.attr("href", "files/" + filesData[i].filename, filesData[i].filename);
        elem.text(filesData[i].originalFilename);
        $("#fileList").append(elem);
        console.log(filesData[i].id);
        var inputPass = $("<input>", {type:"password",
            id: filesData[i].id,
            placeholder: "Pass if applicable"

        });
        var deleteButton = $("<button>", {type:"submit", onclick: "deleteEle(" + filesData[i].id + ")"});
        deleteButton.text("delete");

        $("#fileList").append(inputPass);
        $("#fileList").append(deleteButton);
        var elem2 = $("<br>");
        $("#fileList").append(elem2);
    }
}

function swapValue(id){
    $.post("/save-item?id=" + id);
}

function deleteEle(id) {
    console.log(id);
    var password = $('#' + id).val();
    console.log(password);
    $.post("/delete-item", { id: id, password: password });
    window.location.href = '/';
}

$.get("/files", getFiles)