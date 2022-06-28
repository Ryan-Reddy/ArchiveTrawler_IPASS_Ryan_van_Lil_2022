console.log(JWT);

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |   example for later use as fetch for getting current account:             |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// const jsonRequestBody = {};
// formData.forEach((value, key) => (jsonRequestBody[key] = value));

// const localhost = 'http://localhost:8080/';

// await fetch(`${localhost}restservices/authentication/`, {
//   // TODO remove outside of testing
//   method: 'POST',
//   headers: {
//     Accept: 'application/json',
//     'Content-Type': 'application/json',
//   },
//   body: JSON.stringify(jsonRequestBody),
// })
//   .then((response) => {
//     if (response.ok) { return response.json(); } // als de gegevens herkend zijn krijgen we n body json incl token !!

//     document.getElementById('postresponse').innerHTML = '        <a href="/html/reset-password.html">Fout bij login. Wachtwoord vergeten?</a>\n'; // een melding.
//     throw 'User login failed';
//     // zo niet dan breakt de chain ook.
//   }) // als er geen 200 is er ook geen body
//   .then((myJson) => {
//     console.log('User login successful!');
//     window.sessionStorage.setItem('JWT', myJson.JWT);
//   }) // bij een goede uitkomst hebben we een JWT, slaan we op in de sessionStorage
//   .then(
//     (document.getElementById('postresponse').innerText = 'Succesvol ingelogd.'),
//   ) // een melding.
//   // .then(open('http://localhost:8080/index.html','_self')) //open homepage in de huidige tab
//   .catch((error) => console.log(error)); // hiermee handelen we een potentiele error af
// // controleer of de JWT geupdate is
// if (window.sessionStorage.getItem('JWT') !== preloginJWT) { console.log('JWT updated'); } // geef bericht als jwt is geupdate
// }

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |   Post fetch om account info te wijzigen, heeft nog werk nodig.           |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

async function wijzigAccount() {
  const naamInputBox = document.getElementById('naam');
  const emailInputBox = document.getElementById('email');
  const feedbackspan = document.getElementById('feedbackspan');
  console.log('deleting account');
  const jsonRequestBody = {};

  const jWT = window.sessionStorage.JWT; // sla de lokale JWT op voor controle achteraf

  naamInputBox.innerText = jWT.naam;
  emailInputBox.innerText = jWT.email;

  let formData = new FormData(document.querySelector('#deleteAccount-form'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));
  console.log(jsonRequestBody);

  const fetchOptions = {
    method: 'DELETE',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Authorization: `Bearer${sessionStorage.JWT}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  await fetch('/restservices/users/', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.ok) {
        const feedback =
          'Uw account is verwijderd, en u bent uitgelogd. Hopelijk was dat de bedoeling, ' +
          'als dat niet zo is neem dan contact op met de beheerder.';
        // als het gelukt is dan user uitloggen en melding geven.
        feedbackspan.innerText = feedback;
        sessionStorage.removeItem('JWT');
        throw feedback;
      } else {
        feedbackspan.innerText =
          'Er ging iets mis, controleer of de email klopt met het account dat u wil verwijderen.';
        throw 'er ging iets mis';
      } // if !200 there will be no body
    });
}
// ***************************************