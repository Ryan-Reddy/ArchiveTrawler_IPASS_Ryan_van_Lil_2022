// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |    reset input<email> account:                                               |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
/** pakt email van het formulier en fetch POST deze data naar de backend
 * @returns {Promise<void>}
 */
async function stuurEmailMetWachtwoordReset() {
  console.log('stuurEmailMetWachtwoordReset()')
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
  const feedbackSpan = document.getElementById('feedbackspan');
  await fetch('/restservices/users/getAccount/', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.status === 200) { // er is een account gevonden !
        let myJson = await response.json();
        const feedback = myJson;
        console.log(feedback);
        feedbackspan.innerText = feedback;

      }
      if (response.status === 204) { //no content
        const feedback = 'geen content gevonden voor die input';
        console.log(feedback);
        feedbackspan.innerText = feedback;
      }
    })
    .catch((err) => {
      const feedback = 'Error: ' + err;
      console.log(feedback);
      feedbackspan.innerText = feedback;
    });
}

console.log()