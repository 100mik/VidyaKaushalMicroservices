//const userRepoURL="http://localhost:9000";
//const appRepoURL="http://localhost:7171";
//const fileRepoURL="http://localhost:8000";
//var mydata = JSON.parse(data);
//const userRepoURL=mydata[0].userRepoURL;
//const appRepoURL=mydata[0].appRepoURL;
//const fileRepoURL=mydata[0].fileRepoURL;

if (window.File && window.FileReader && window.FileList && window.Blob) {
    document.getElementById('show').addEventListener('click', listAll, false);
  } else {
    alert('The File APIs are not fully supported in this browser.');
  }
  show.click();
  tab.onclick = function(e) {
    console.log(e.target.dataset.fileid);
    if(e.target.tagName==="A"){
      if(e.target.dataset.fileid!="undefined"){
        downloadFile(e.target.dataset.fileid);
      }
    }
  }
  
  function listAll(){
    fetch(appRepoURL+'/getAllApp')
    .then(function(response) {
      return response.json();
    })
    .then(function(myJson) {
      console.log(myJson);
      var data =myJson; 
      console.log(data);

      var table = document.getElementById("tab");
      var tr = document.createElement("tr");
      var td1 = document.createElement("th");
      var td2 = document.createElement("th");
      var td3 = document.createElement("th");
      var td4 = document.createElement("th");
     // var td5 = document.createElement("th");
     // var td6 = document.createElement("th");
      
      td1.appendChild(document.createTextNode("userID"));
      td2.appendChild(document.createTextNode("schemeID"));
      td3.appendChild(document.createTextNode("POA File ID"));
      td4.appendChild(document.createTextNode("POI File ID"));
    //  td5.appendChild(document.createTextNode("Created at"));
     // td6.appendChild(document.createTextNode("Modified at"));

      tr.appendChild(td1);
      tr.appendChild(td2);
      tr.appendChild(td3);
      tr.appendChild(td4);
    //  tr.appendChild(td5);
    //  tr.appendChild(td6); 
      table.appendChild(tr);
      
      tr = document.createElement("tr");
      td1 = document.createElement("td");
      td2 = document.createElement("td");
      td3 = document.createElement("td");
      td4 = document.createElement("td");
   //   td5 = document.createElement("td");
  //    td6 = document.createElement("td");
      var dateFormatter;         
      var o;
      var a = document.createElement('a');
      var linkText = document.createTextNode("File");
         for( var i = 0; i < data.length; i++ )
        { console.log(data[i]);
               o = data[i];
               td1.appendChild(document.createTextNode(o.userID));
               td2.appendChild(document.createTextNode(o.schemeID));
               
               a = document.createElement('a');
                if(o.poaFileID!="" && o.poaFileID!=null){
                  linkText = document.createTextNode("File");
                  a.title = o.poaFileID;
                  a.dataset.fileid = o.poaFileID;
                  a.href ="#";
                }else{
                  linkText = document.createTextNode("File not present");
                  a.title = "File not present";
                }
                a.appendChild(linkText);
                
               td3.appendChild(a);
               
               a = document.createElement('a');
                if(o.poiFileID!="" && o.poiFileID!=null){
                  linkText = document.createTextNode("File");
                  a.title = o.poiFileID;
                  a.dataset.fileid = o.poiFileID;
                  a.href ="#";
                }else{
                  linkText = document.createTextNode("File not present");
                  a.title = "File not present";
                }
                a.appendChild(linkText);

               td4.appendChild(a);

       //        dateFormatter=new Date(o.createdAt);
       //        td5.appendChild(document.createTextNode(dateFormatter));
       //        dateFormatter=new Date(o.updatedAt);
       //        td6.appendChild(document.createTextNode(dateFormatter));
               tr.appendChild(td1);
               tr.appendChild(td2);
               tr.appendChild(td3);
               tr.appendChild(td4);
        //       tr.appendChild(td5);
        //       tr.appendChild(td6); 
               table.appendChild(tr);
               tr = document.createElement("tr");
               td1 = document.createElement("td");
               td2 = document.createElement("td");
               td3 = document.createElement("td");
               td4 = document.createElement("td");
        //       td5 = document.createElement("td");
        //       td6 = document.createElement("td");
               
        }
                
    });
  }
  

  function downloadFile(fileField){
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
  
    
  