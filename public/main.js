function getFiles(filesData) {
    for ( var i in filesData) {
        var elem = $("<a>");
        var elem2 = $("<br>");
        var elem3 = $("<a>");
        elem.attr("href", "files/", filesData[i].filename);
        elem.text(filesData[i].comment);
        // elem3.attr("href", "/delete");
        elem3.text(filesData[i].id);
        $("#filelist").append(elem);
        $("#filelist").append(elem3);
        $("#filelist").append(elem2);
    }

}

$.get("/files", getFiles);