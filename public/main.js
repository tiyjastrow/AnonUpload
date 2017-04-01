function getFiles(filesData){
    for(var i in filesData){
        var elem = $("<a>")
        elem.attr("href", "files/" + filesData[i].filename, filesData[i].fileDesc);
        elem.text(filesData[i].fileDesc);
        $("#filelist").append(elem);
        var elem2 = $("<br>");
        $("#filelist").append(elem2);
    }
}

$.get("/files", getFiles);