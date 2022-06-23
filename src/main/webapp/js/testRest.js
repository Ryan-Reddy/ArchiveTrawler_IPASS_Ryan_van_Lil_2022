// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');


// document.getElementById('getAllUsers').addEventListener('click', function () {
// document.querySelector('#getAllUsers').addEventListener('click', function () {


function halleluja(node) {
  console.log('clicked getAllUsersButton')
  document.querySelector('#getAllUsers').setAttribute('color', 'green');
  const fetchOptions = {
    method: 'GET',
    headers: {
      'Authorization': 'Bearer ' + window.sessionStorage.getItem('JWT')
    }
  };
  console.log('starting fetch')
  fetch('restservices/users/', fetchOptions)
    .then(function (response) {
      if (response.ok) {
        console.log('credentials correct, you are a user')
        return response;
      }
      else if (response.status === 404) console.log("could not find the stuff")
      else if (response.status === 401) console.log("unauthorized")
    })
    .then(function (data) {
      appendData(data.json());
    })
    .catch(error => console.log(error));
}

function appendData(data) {
  console.log('appendData() testRest.js')
  console.log(data)
  var mainContainer = document.getElementById("myData");
  for (var i = 0; i < data.length; i++) {
    console.log('another one')
    console.log(data)
    var div = document.createElement("div");
    div.innerHTML = 'Name: ' + data[i].firstName + ' ' + data[i].lastName;
    mainContainer.appendChild(div);
  }
}
