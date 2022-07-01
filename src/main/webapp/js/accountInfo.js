// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |    getting current account:                                               |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
async function fetchCurrentUser() {
  console.log('fetchSingleUser() (jwt.thisUser)');
  const jsonRequestBody = {};

  // const formData = new FormData(document.querySelector('#emailToSearchForm'));
  // formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Authorization: `Bearer ${window.sessionStorage.getItem('JWT')}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  const testingspaceSpan = document.getElementById('testingspace');
  // LETOP als fetch niet werkt door missende localhost,
  // check dat de HTTP file themeManager.js importeert als eerste.
  // eslint-disable-next-line no-undef
  await fetch(`${localhost}restservices/users/getAccount/`, fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.status === 200) { // er is een account gevonden !
        const myJson = await response.json();
        console.log(myJson);
        document.getElementById('naam').value = myJson.naam;
        document.getElementById('email').value = myJson.email;
        // testingspaceSpan.innerHTML += 'naam:  ' + myJson.naam;
        // testingspaceSpan.innerHTML += '<br>email: ' + myJson.email;
        // testingspaceSpan.innerHTML += '<br>rol:   ' + myJson.role;
      }
      if (response.status === 204) { // no content
        console.log('geen content gevonden voor die input');
      }
    })
    .catch((err) => {
      console.log('Error: ', err);
    });
}
// ***************************************

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |   Post fetch om account info te wijzigen, heeft nog werk nodig.           |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

async function wijzigAccount() {
  const feedbackspan = document.getElementById('feedbackspan');
  feedbackspan.innerText = 'Attempting to edit account info.';
  console.log('Editing account');
  const jsonRequestBody = {};

  const formData = new FormData(document.querySelector('#postaccount'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));
  console.log(`with data: ${jsonRequestBody}`);

  const fetchOptions = {
    method: 'PATCH',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Authorization: `Bearer ${sessionStorage.JWT}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  await fetch('/restservices/users/wijzigUser', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.ok) {
        const feedback = 'Accountinfo wijzigen is gelukt!';
        feedbackspan.innerText = feedback;
        return feedback;
      }
      feedbackspan.innerText = 'bent u ingelogd?';
      throw 'er ging iets mis';
      // if !200 there will be no body
    })
    .catch((error) => {
      console.log(error);
    });
}

// ***************************************
/** Dit draait bij het openen pagina: */
console.log('window on load');
window.addEventListener('load', () => fetchCurrentUser());
// ***************************************
// document.getElementById('searchUserButton').addEventListener('click', (event) => {fetchToPage()})
