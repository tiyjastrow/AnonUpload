/**
 * Created by halleyfroeb on 10/11/16.
 */

function getFiles(filesData){
    for (i in filesData){
        var elem = $("<a>");
        elem.attr("href", "files/", filesData[i].id, filesData[i].filename);
        elem.text(filesData[i].originalFilename);
        $("#filelist").append(elem);
        var elem2 = $("<br>");
        $("#filelist").append(elem2);
    }
}

$.get("/files", getFiles);