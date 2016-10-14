/**
 * Created by joshuakeough on 10/11/16.
 */
function getFiles(filesData) {
    for (i in filesData) {
        var elem = $("<a>");
        elem.attr("href", "files/" + filesData[i].id, filesData[i].filename);
        elem.text(filesData[i].comment);
        $("#filelist").append(elem);
        var elem2 = $("<br>");
        $("#filelist").append(elem2);
        var elem3 = $("<form>");
        elem3.attr("action", "/delete");
        elem3.attr("method", "post");
        var elem4 = $("<input>");
        elem4.attr("name", "id");
        elem4.attr("type","hidden");
        elem4.attr("value", filesData[i].id);
        var elem6 = $("<input>");
        elem6.attr("name", "password");
        elem6.attr("type","text");
        elem6.attr("placeholder", "Password");
        var elem5 = $("<button>");
        elem5.attr("type", "submit");
        elem5.text("Delete");
        elem3.append(elem4);
        elem3.append(elem5);
        elem3.append(elem6);
        $("#filelist").append(elem3);
    }
}

$.get("/files", getFiles);