// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');


// document.getElementById('getAllUsers').addEventListener('click', function () {
// document.querySelector('#getAllUsers').addEventListener('click', function () {


function halleluja() {
  console.log('clicked getAllUsersButton')
  document.querySelector('#getAllUsers').setAttribute('color', 'green');
  const fetchOptions = {
    method: 'GET',
    headers: {
      'Authorization': 'Bearer ' + window.sessionStorage.getItem('JWT')
    }
  };
  console.log('starting fetch')
  console.log('restservices/users/', fetchOptions)

  fetch('restservices/users/', fetchOptions)
    .then(function (response) {
      if (response.ok) {
        console.log('credentials correct, you are a user');
        console.log(response);
        document.querySelector('#myData').innerText = response;
      }
      else if (response.status == 404) console.log("could not find the stuff")
      else if (response.status == 401) console.log("unauthorized")
    }).catch(error => console.log(error));
}
