/**
 * Created by jeremypitt on 10/11/16.
 */

function getFiles(filesData) {
    for (i in filesData){
        var elem3 = $("<a>");
        elem3.text(filesData[i].id)
        $("#filelist").append(elem3);

        var elem = $("<a>");
        elem.attr("href", "files/" + filesData[i].id, filesData[i].filename);
        elem.text(filesData[i].comment);
        $("#filelist").append(elem);

        var elem2 = $("<br>");
        $("#filelist").append(elem2);
    }
}

$.get("/files", getFiles);
