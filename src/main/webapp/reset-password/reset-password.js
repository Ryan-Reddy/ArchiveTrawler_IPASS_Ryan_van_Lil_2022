/** Deze functie controleert het nieuwe wachtwoord, en geeft deze
 * aan de backend via een fetch PATCH. De backend regelt dan verder de wijzigingen.
 * Vervolgens geeft deze functie weer feedback aan de gebruiker. 
 */
async function wijzigWachtwoord() {
  const feedbackspan = document.getElementById('postresponse');
  const ww1 = document.getElementById('password').value;
  const ww2 = document.getElementById('password2').value;
  if (ww1.length < 6 || ww2.length < 6) {
    console.log('wachtwoord te kort');
    feedbackspan.innerHTML = '<p color=red>Uw wachtwoord is te kort!</p>';
  }
  if (ww1 != ww2) {
    console.log('wachtwoorden verschillen');
    feedbackspan.innerHTML = '<p>Uw wachtwoorden komen niet overeen, controleer nogmaals</p>';
  }
  if (ww1 === ww2 && ww1.length >= 6 && ww2.length >= 6) {
    const jsonRequestBody = {};
    const formData = new FormData(document.querySelector('#wijzigWachtwoord'));
    formData.forEach((value, key) => (jsonRequestBody[key] = value));
    console.log(`with data: ${jsonRequestBody.toString()}`);

    const fetchOptions = {
      method: 'PATCH',
      body: JSON.stringify(jsonRequestBody),
      headers: {
        Authorization: `Bearer ${sessionStorage.JWT}`, Accept: 'application/json', 'Content-Type': 'application/json',
      },
    };
    await fetch(`${localhost}restservices/account-reset/wijzig-wachtwoord/`, fetchOptions) // een POST naar dit adres maakt een nieuw acc.
      .then(async (response) => {
        if (response.ok) {
          const feedback = 'Accountinfo wijzigen is gelukt!';
          feedbackspan.innerText = feedback;
          return feedback;
        }
        feedbackspan.innerText = 'bent u ingelogd?';
        throw 'er ging iets mis';
        // als het niet response 200 is dan is er iets mis gegaan.
      })
      .catch((error) => {
        console.log(error);
      });
  } else {
    console.log('whoops ... have you tried turning it on and off again?');
  }
}

/** Deze functie togglet de zichtbaarheid van het wachtwoord heen en weer.
 *   @Function wwZichtbaarToggle()
 *   @returns void
 */
function wwZichtbaarToggle() {
  const passwordElement = document.getElementById('password');
  const passwordElement2 = document.getElementById('password2');
  const typeAttribute = passwordElement.getAttribute('type');

  // Switch het type van 'password' <=> 'text' en vice versa
  passwordElement.setAttribute(
    'type',
    typeAttribute === 'password' ? 'text' : 'password',
  );
  passwordElement2.setAttribute(
    'type',
    typeAttribute === 'password' ? 'text' : 'password',
  );
}
