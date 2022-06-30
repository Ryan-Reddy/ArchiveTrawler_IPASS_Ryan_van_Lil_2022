
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |    getting current account:                                               |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
async function fetchCurrentUser() {
  console.log('fetchSingleUser() (jwt.thisUser)')
  const jsonRequestBody = {};

  // const formData = new FormData(document.querySelector('#emailToSearchForm'));
  // formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      "Authorization": "Bearer " + window.sessionStorage.getItem("JWT"),
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  const testingspaceSpan = document.getElementById('testingspace');
  await fetch('/restservices/users/getAccount/', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.status === 200) { // er is een account gevonden !
        let myJson = await response.json();
        console.log(myJson);
        document.getElementById('naam').value = myJson.naam;
        document.getElementById('email').value = myJson.email;
        // testingspaceSpan.innerHTML += 'naam:  ' + myJson.naam;
        // testingspaceSpan.innerHTML += '<br>email: ' + myJson.email;
        // testingspaceSpan.innerHTML += '<br>rol:   ' + myJson.role;
      }
      if (response.status === 204) { //no content
        console.log('geen content gevonden voor die input');
      }
    })
    .catch((err) => {
      console.log('Error: ', err);
    });
}

// document.getElementById('searchUserButton').addEventListener('click', (event) => {fetchToPage()})


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
    method: 'PATCH',
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
/** Dit draait bij het openen pagina: */
window.onload = () => {
  fetchCurrentUser();
};
// window.addEventListener('load',fetchCurrentUser())