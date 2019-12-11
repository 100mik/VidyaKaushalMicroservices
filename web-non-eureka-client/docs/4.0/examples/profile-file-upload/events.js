//const userRepoURL="http://localhost:9000";
///const appRepoURL="http://localhost:7171";
//const fileRepoURL="http://localhost:8000";

if (window.File && window.FileReader && window.FileList && window.Blob) {
    document.getElementById('poiFile').addEventListener('change', processFile, false);
    document.getElementById('poaFile').addEventListener('change', processFile, false);
  
    document.getElementById('downloadPoiFile').addEventListener('click', function(){downloadFile('poiFile')}, false);
    document.getElementById('downloadPoaFile').addEventListener('click', function(){downloadFile('poaFile')}, false);
  
    document.getElementById('deletePoiFile').addEventListener('click', function(){deleteFile('poiFile')}, false);
    document.getElementById('deletePoaFile').addEventListener('click', function(){deleteFile('poaFile')}, false);
    document.getElementById('save').addEventListener('click', saveme, false);
    document.getElementById('search').addEventListener('click', search, false);
  } else {
    alert('The File APIs are not fully supported in this browser.');
  }
  

function search(){
    var id=document.getElementById('id').value;
    fetch(userRepoURL+'/getUser/'+id)
    .then(function(response) {
      return response.json();
    })
    .then(function(myJson) {
        if(myJson.poiFileID!="" && myJson.poiFileID!=null){
            document.getElementById('poiFile').dataset.refid=myJson.poiFileID;
            document.getElementById('poiFile').style="display:none";
        }

        if(myJson.poaFileID!=""&& myJson.poaFileID!=null){
            document.getElementById('poaFile').dataset.refid=myJson.poaFileID;
            document.getElementById('poaFile').style="display:none";
        }
    });
}

  function saveme(){
    //alert("hi");
    var id=document.getElementById("id").value;
    if(id!="" && id!=null){
        var poaFileId=document.getElementById("poaFile").dataset.refid;
        var poiFileId=document.getElementById("poiFile").dataset.refid;
        
        fetch(userRepoURL+'/updateUser/'+id, {
                method: 'PATCH',
                body: JSON.stringify({
                  poiFileID: poiFileId,// Had to add 1 to get actual index
                  poaFileID:poaFileId
                }),
                headers: {
                  "Content-type": "application/json; charset=UTF-8"
                }
              })
              .then(response => response.json())
              .then(json => alert(json.id+' Has been saved'));
    }else{
        alert('No user to save');
    }
    
   // };
  }
  
  function downloadFile(file){
    var fileField=document.getElementById(file).dataset.refid;
    console.log('downloading..'+fileField);
    if(fileField!=""){
        fetch(fileRepoURL+'/getFile/'+fileField)
        .then(function(response) {
        return response.json();
        })
        .then(function(myJson) {
        var blob = b64toBlob(myJson.fileContent,myJson.fileType);
        let objectURL = window.URL.createObjectURL(blob);
        let anchor = document.createElement('a');
        anchor.href = objectURL;
        anchor.download = name;
        anchor.click();
        URL.revokeObjectURL(objectURL);
        });
    }else{
        alert("No file to download");
    }
  }
  
  function deleteFile(file){
      
    var fileField=document.getElementById(file).dataset.refid;
    console.log('deleting..'+fileField);
    if(fileField!=""){
        fetch(fileRepoURL+'/removeFile/'+fileField,{method: 'PATCH'}).then(function(response) {
            return response.json();
          }).then(function(myJson) {
              console.log('response received from delte:'+myJson);
              if(myJson==="OK"){
                  document.getElementById(file).style='';
                  document.getElementById(file).dataset.refid='';
              }
          });
    }else{
        alert("No file to delete");
    }
    
    
  }
  //image/jpeg;base64,
  function processFile(){
    var f = this.files[0]; // FileList object
    var att=this.id;
    var reader = new FileReader();
  
    // Closure to capture the file information.
  
    reader.onload = (function(theFile,myFile) {
    return function(e) {
    var base64Data = e.target.result;
    //console.log(base64Data);
    var metaDataIndex=base64Data.indexOf(",");
    document.getElementById(myFile).dataset.base=base64Data;
    fetch(fileRepoURL+'/addFile', {
            method: 'POST',
            body: JSON.stringify({
              fileContent: base64Data.substring(metaDataIndex+1),// Had to add 1 to get actual index
              fileType:f.type
            }),
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then(response => response.json())
          .then(json => document.getElementById(myFile).dataset.refid=json);
          document.getElementById(myFile).style="display:none";
    };
  
    })(f,att);
    reader.readAsDataURL(f);
  }
  
  
    
     
function b64toBlob(b64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(b64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
    var slice = byteCharacters.slice(offset, offset + sliceSize);

    var byteNumbers = new Array(slice.length);
    for (var i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
    }

    var byteArray = new Uint8Array(byteNumbers);

    byteArrays.push(byteArray);
    }
    
    var blob = new Blob(byteArrays, {type: contentType});
    return blob;
}