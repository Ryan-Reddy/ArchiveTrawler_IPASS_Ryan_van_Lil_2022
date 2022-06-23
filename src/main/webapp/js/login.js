function login() {
  let formData = new FormData(document.querySelector('#login_account'));
  let jsonRequestBody = {};
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  fetch('/restservices/authentication', {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(jsonRequestBody),
  })
    .then(function (response) {
      if (response.ok) {
       document.getElementById('postresponse').innerText = 'Succesvol ingelogd.';
        return open('http://localhost:8080/index.html');;
      } // als er wel 200 us er ook een body
      else {
        throw 'Wrong username/password'; //dan breakt de chain ook.
      } //als er geen 200 is er ook geen body
    })
    .then((myJson) => window.sessionStorage.setItem('JWT', myJson.JWT)) // bij een goede uitkomst hebben we een JWT, slaan we op in de sessionStorage
    .then((res) => console.log(res))
    .catch((error) => console.log(error)); // hiermee handelen we een potentiele error af
}

document.querySelector('#login').addEventListener('click', login);
