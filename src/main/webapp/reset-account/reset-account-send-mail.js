console.log('loading reset-account-send-mail.js');

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
    method: 'POST', body: JSON.stringify(jsonRequestBody), headers: {
      Accept: 'application/json', 'Content-Type': 'application/json',
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
        console.log('Er is een email gestuurd naar uw account.');
        feedbackspan.innerText = 'Als er een account bestaat met die gegevens is er een email gestuurd naar u.';
      }
      if (response.status === 204) { // no content
        const feedback = 'Als er een account bestaat met die gegevens is er een email gestuurd naar u.';
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
