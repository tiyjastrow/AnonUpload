/**
 * Created by halleyfroeb on 10/11/16.
 */

function getFiles(filesData){
    for (i in filesData){
        var elem = $("<a>");
        var elem2 = $("<br>");
        var elem3 = $("<a>");
        elem.attr("href", "files/", filesData[i].id, filesData[i].filename);
        elem.text(filesData[i].comment);
        elem3.text(filesData[i].id);
        $("#filelist").append(elem);
        $("#filelist").append(elem3);
        $("#filelist").append(elem2);
    }
}

$.get("/files", getFiles);