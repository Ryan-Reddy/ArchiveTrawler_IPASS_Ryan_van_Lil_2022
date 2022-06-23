/** Login
 * gebruikt informatie van het formulier
 * slaat bij succes de JWToken op in de sessionstorage client side.
 */

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
      if (response.ok) return response.json(); // als de gegevens herkend zijn krijgen we n body json incl token !!
      else throw 'Wrong username/password'; // zo niet dan breakt de chain ook.
      }) //als er geen 200 is er ook geen body
    .then(myJson => window.sessionStorage.setItem('JWT', myJson.JWT)) // bij een goede uitkomst hebben we een JWT, slaan we op in de sessionStorage
    // .then((res) => console.log(res)) // console log //TODO verwijder
    // .then(document.getElementById('postresponse').innerText = 'Succesvol ingelogd.')// mocht de pagina niet laden dan alsnog een melding.
    // .then(open('http://localhost:8080/index.html','_self')) //open homepage in de huidige tab
    .catch((error) => console.log(error)); // hiermee handelen we een potentiele error af
}

document.querySelector('#login').addEventListener('click', login);
