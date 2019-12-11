//const userRepoURL="http://localhost:9000";
//const appRepoURL="http://localhost:7171";
//const fileRepoURL="http://localhost:8000";

if (window.File && window.FileReader && window.FileList && window.Blob) {
    document.getElementById('save').addEventListener('click', processSave, false);
  } else {
    alert('The File APIs are not fully supported in this browser.');
  }
  
  
  function processSave(){
    if(document.getElementById('name').value===''){
      alert('Please enter name');
      return;
    };
    fetch(userRepoURL+'/addUser', {
            method: 'POST',
            body: JSON.stringify({
              name: document.getElementById('name').value
            }),
            headers: {
              "Content-type": "application/json; charset=UTF-8"
            }
          })
          .then(response => response.json())
          .then(json => document.getElementById('id').value=json.id);
  }
  
  
    
  