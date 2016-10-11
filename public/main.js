

function getFiles(filesData) {
    for (i in filesData) {
        var elem = $("<a>");
        elem.attr("href", "files/" + filesData[i].id, filesData[i].filename);
        elem.text(filesData[i].originalFilename);
        $("#filelist").append(elem);
        var elem2 = $("<br>");
        $("#filelist").append(elem2);

        var elem3 = $("<form>");
        elem3.attr("action", "/delete-file");
        elem3.attr("method", "post");
        var elem4 = $("<input>");
        elem4.attr("name", "id");
        elem4.attr("type", "hidden");
        elem4.attr("value", filesData[i].id);
        var elem5 = $("<button>");
        elem5.attr("type", "submit");
        elem5.text("Delete");
        elem3.append(elem4);
        elem3.append(elem5);
        $("#filelist").append(elem3);
    }
}

$.get("/files", getFiles);