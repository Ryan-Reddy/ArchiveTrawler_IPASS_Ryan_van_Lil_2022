/** De event listeners */
// overal op deze pagina, kan ook specifieke input zijn
document.addEventListener('keydown', async (event) => {
  //            ////////////////////////////////////////////////////////////////
  if (event.ctrlKey && event.shiftKey) {
    // Wacht op enterKeystroke + shift  TODO comment out for reference and use elsewhere
    event.preventDefault(); // Stopt default actie, voor het geval dat...
    console.log('shift key recognized');
    login();
    getAllUsersButtonFunc();
    document.getElementById('getAllUsers').click(); // clickt op button
  }
  //            ////////////////////////////////////////////////////////////////
  //    TODO keep this follwowing code:
  //            ////////////////////////////////////////////////////////////////
  if (event.key === 'Enter') {
    // Wacht op enterKeystroke
    event.preventDefault(); // Stopt default actie, voor het geval dat...
    document.getElementById('login()_button').click(); // clickt op button
  }
  //            ////////////////////////////////////////////////////////////////
});

/** Login
 * gebruikt informatie van het formulier
 * slaat bij succes de JWToken op in de sessionstorage client side.
 */
async function login() {
  const preloginJWT = window.sessionStorage.getItem('JWT'); // sla de lokale JWT op voor controle achteraf
  const formData = new FormData(document.querySelector('#login_account'));
  const jsonRequestBody = {};
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  // const localhost = 'http://localhost:8080/';
  const localhost = '';

  await fetch(`${localhost}restservices/authentication/`, {
    // TODO remove outside of testing
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(jsonRequestBody),
  })
    .then((response) => {
      if (response.ok) { return response.json(); } //als de gegevens herkend zijn > body json incl token!!

      document.getElementById('postresponse').innerHTML = '<a href="/html/reset-password.html">Fout bij login. Wachtwoord vergeten?</a>\n'; // een melding.
      throw 'User login failed';
      // zo niet dan breakt de chain ook.
    }) // als er geen 200 is er ook geen body
    .then((myJson) => {
      console.log('User login successful!');
      window.sessionStorage.setItem('JWT', myJson.JWT);
    }) // bij een goede uitkomst hebben we een JWT, slaan we op in de sessionStorage
    .then(
      (document.getElementById('postresponse').innerText = 'Succesvol ingelogd.'),
    ) // een melding.
    // .then(open('http://localhost:8080/index.html','_self')) //open homepage in de huidige tab
    .catch((error) => console.log(error)); // hiermee handelen we een potentiele error af
  // controleer of de JWT geupdate is
  if (window.sessionStorage.getItem('JWT') !== preloginJWT) { console.log('JWT updated'); } // geef bericht als jwt is geupdate
}
