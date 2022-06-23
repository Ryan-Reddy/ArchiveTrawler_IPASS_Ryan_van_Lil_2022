// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');


// document.getElementById('getAllUsers').addEventListener('click', function () {
// document.querySelector('#getAllUsers').addEventListener('click', function () {



function halleluja() {
  console.log('clicked getAllUsersButton')
    button.setbackgroundColor = "red";
    const fetchOptions = {
        method: 'GET', headers: {
            'Authorisation': 'Bearer' + window.sessionStorage.getItem("myJWT")
        }
    };
    console.log('starting fetch')

  fetch('/restservices/users/', fetchOptions)
    .then(function (response) {
      if (response.ok) console.log('credentials correct, you are a user')
      else if (response.status == 404) console.log("could not find the stuff")
      else if (response.status == 401) console.log("unauthorized")
    }).catch(error => console.log(error));
};
