// import Config from './AAAglobalVAR';

// const localhost = 'http://localhost:8080/'; // TODO toggle for heroku
// // const localhost = '/';
console.log('loading resetAccountWW.js');
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |    reset input<email> account:                                               |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/** pakt email van het formulier en fetch POST deze data naar de backend
 * @returns {Promise<void>}
 */
function stuurWachtwoordReset() {
  console.log('stuurEmailMetWachtwoordReset()');
  const jsonRequestBody = {};

  const formData = new FormData(document.querySelector('#resetAccount'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  const feedbackspan = document.getElementById('feedbackspan');
  // LETOP als fetch niet werkt door missende localhost,
  // check dat de HTTP file themeManager.js importeert als eerste.
  // eslint-disable-next-line no-undef
  fetch(`${localhost}restservices/account-reset/`, fetchOptions) // POST, maakt een nieuw acc.
    .then((response) => {
      if (response.status === 200) { // er is een account gevonden !
        const feedback = response.json();
        console.log(feedback);
        feedbackspan.innerText = feedback;
      }
      if (response.status === 204) { // no content
        const feedback = 'geen content gevonden voor die input';
        console.log(feedback);
        feedbackspan.innerText = feedback;
      }
    })
  .catch((err) => {
    const feedback = `Error: ${err}`;
    console.log(feedback);
    feedbackspan.innerText = feedback;
  });
}


async function wijzigWachtwoord() {
  const feedbackspan = document.getElementById('postresponse');
  feedbackspan.innerText = 'Attempting to edit account info.';
  console.log('Editing account');
  const jsonRequestBody = {};
  const ww1 = document.getElementById('password').value;
  const ww2 = document.getElementById('password2').value;
  console.log(ww1+ww2)
  console.log(ww1.length+ '   '+ww2.length)

    if(ww1.length >= 5) {
      if (ww1 === ww2) {


    let formData = new FormData(document.querySelector('#wijzigWachtwoord'));
    formData.forEach((value, key) => (jsonRequestBody[key] = value));
    console.log('with data: ' + jsonRequestBody);

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
          const feedback = "Accountinfo wijzigen is gelukt!";
          feedbackspan.innerText = feedback;
          return feedback;
        } else {
          feedbackspan.innerText = 'bent u ingelogd?';
          throw 'er ging iets mis';
        } // if !200 there will be no body
      })
      .catch((error) => {
        console.log(error)
      });
  }
  feedbackspan.innerHTML = '<p color=red>Uw wachtwoorden komen niet overeen, controleer nogmaals</p>';
}
  feedbackspan.innerHTML = '<p color=red>Uw wachtwoord is te kort!</p>';
}




// document.getElementById('stuurEmailMetWachtwoordReset').addEventListener('click', stuurEmailMetWachtwoordReset());
