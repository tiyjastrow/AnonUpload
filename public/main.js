/**
 * Created by jakefroeb on 10/11/16.
 */


function getFiles(filesData) {
    for (i in filesData){
        var elem = $("<a>");
        elem.attr("href", "files/", filesData[i].filename);
        elem.text(filesData[i].name);
        $("#fileList").append(elem);
        var elem2 = $("<br/>");
        $("#fileList").append(elem2);
    }

}

$.get("/files", getFiles);