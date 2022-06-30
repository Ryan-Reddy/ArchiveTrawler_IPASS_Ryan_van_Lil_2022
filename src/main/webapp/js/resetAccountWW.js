import { localhost } from './AAAglobalVAR.js';

console.log('loading resetAccountWW.js');
console.log(global.localhost);
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
  fetch(`${localhost}/restservices/users/getAccount/`, fetchOptions) // POST, maakt een nieuw acc.
    .then((response) => {
      if (response.status === 200) { // er is een account gevonden !
        const myJson = response.json();
        const feedback = myJson;
        console.log(feedback);
        feedbackspan.innerText = feedback;
      }
      if (response.status === 204) { // no content
        const feedback = 'geen content gevonden voor die input';
        console.log(feedback);
        feedbackspan.innerText = feedback;
      }
    });
  // .catch((err) => {
  //   const feedback = `Error: ${err}`;
  //   console.log(feedback);
  //   feedbackspan.innerText = feedback;
  // });
}

// document.getElementById('stuurEmailMetWachtwoordReset').addEventListener('click', stuurEmailMetWachtwoordReset());
