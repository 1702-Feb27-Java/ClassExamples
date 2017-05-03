/**
 * 
 */

var modal = document.getElementById('myModal');

// Get the image and insert it inside the modal - use its "alt" text as a caption
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");


// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

/*function popUpImage()
{
    $.ajax({
        cache: false,
        url: 'master.do', 
        type: 'GET',
        data: {"pictureId":this.id},
        success: function(result,status,xhr){

            //pop up modal
            modal.style.display = "block";
            //modalImg.src = "data:image/jpg;base64," + xhr.responseText; //set picture of modal
            //captionText.innerHTML = "Image"; //set caption 
        },
        error: function(xhr,status){
            console.log("error");
        },
        complete: function(xhr, status){
            console.log("complete");
        }
    });
}

window.onload = function(){
    var list = document.getElementsByClassName("filelink");
    
    for(var i = 0;i < list.length; i++){
        list[i].addEventListener("click", popUpImage, false);
    }
}*/