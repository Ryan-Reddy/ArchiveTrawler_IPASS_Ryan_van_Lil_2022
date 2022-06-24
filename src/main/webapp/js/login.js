/** De event listeners*/
//overal op deze pagina, kan ook specifieke input zijn
document.addEventListener("keydown", async function (event) {

//            ////////////////////////////////////////////////////////////////
  if (event.ctrlKey && event.shiftKey) { // Wacht op enterKeystroke + shift  TODO comment out for reference and use elsewhere
    event.preventDefault(); // Stopt default actie, voor het geval dat...
    console.log('shift key recognized');
    login();
    getAllUsersButtonFunc();
    document.getElementById("getAllUsers").click(); //clickt op button
  }
//            ////////////////////////////////////////////////////////////////
  //    TODO keep this follwowing code:
//            ////////////////////////////////////////////////////////////////
  if (event.key === "Enter") { // Wacht op enterKeystroke
    event.preventDefault(); // Stopt default actie, voor het geval dat...
    document.getElementById("login()_button").click(); //clickt op button
  }
//            ////////////////////////////////////////////////////////////////
});



/** Login
 * gebruikt informatie van het formulier
 * slaat bij succes de JWToken op in de sessionstorage client side.
 */
function login() {
  const preloginJWT = window.sessionStorage.getItem('JWT'); // sla de lokale JWT op voor controle achteraf
  let formData = new FormData(document.querySelector('#login_account'));
  let jsonRequestBody = {};
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const localhost = 'http://localhost:8080/';

  fetch(localhost+ 'restservices/authentication', { // TODO remove outside of testing
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(jsonRequestBody),
  })
    .then(function (response) {
      if (response.ok) return response.json(); // als de gegevens herkend zijn krijgen we n body json incl token !!
      else {
      document.getElementById('postresponse').innerText = 'Foutieve login.'; // een melding.
        throw 'User login failed';
      } // zo niet dan breakt de chain ook.
      }) //als er geen 200 is er ook geen body
    .then(myJson => {
      console.log('User login successful!');
      window.sessionStorage.setItem('JWT', myJson.JWT)
    }) // bij een goede uitkomst hebben we een JWT, slaan we op in de sessionStorage
    .then(document.getElementById('postresponse').innerText = 'Succesvol ingelogd.')// een melding.
    // .then(open('http://localhost:8080/index.html','_self')) //open homepage in de huidige tab
    .catch((error) => console.log(error)); // hiermee handelen we een potentiele error af
  // controleer of de JWT geupdate is
  if(window.sessionStorage.getItem('JWT') !== preloginJWT) console.log('JWT updated'); // geef bericht als jwt is geupdate
}

