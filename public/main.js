/**
 * Created by robculclasure on 3/14/17.
 */

function getFiles(filesData){
    for (var i in filesData){
        var elem = $("<a>");
        elem.attr("href", "files/" + filesData[i].filename, filesData[i].filename);
        elem.text(filesData[i].linkText);
        $("#filelist").append(elem);

        var form = $("<form action='/delete' method='post'>");
        var inputId = $("<input type='hidden' name='deleteId'/>");
            inputId.attr("value", filesData[i].id);
        var inputP = $("<input type='password' placeholder='password' name='deletePass'/>");
        var button = $("<button id='deleteButton' type='submit'>Delete</button>");

        form.append(inputId);
        form.append(inputP);
        form.append(button);
        $("#filelist").append(form).append("<br>");


    }
}

$.get("/files", getFiles);