/**
 * Created by joe on 11/10/2016.
 */

function getFiles(filesData){
    for(i in filesData){
        var elem = $("<a>");
        elem.attr("href", "files/", filesData[i].filename);
        elem.text(filesData[i].comment);
        $("#filelist").append(elem);
        var elem2 = $("<br>");
        $("#filelist").append(elem2);
    }
}

$.get("/files", getFiles);