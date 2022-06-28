// function getParsedJwt(token) {
//   try {
//     return JSON.parse(atob(token.split('.')[1]));
//   } catch (error) {
//     return undefined
//   }
// }

import jwt_decode from 'jwt-decode';

async function deleteAccount() {
  const feedbackspan = document.getElementById('feedbackspan');
  console.log('deleting account');
  const jsonRequestBody = {};

  // let formData = new FormData(document.querySelector('#deleteAccount-form'));
  // formData.forEach((value, key) => (jsonRequestBody[key] = value));
  const JWT = sessionStorage.getItem('JWT');
  console.log(JWT);
  // console.log(getParsedJwt(JWT));
  // console.log(getParsedJwt(JWT).sub);
  console.log(jwt_decode(JWT));
  console.log(jwt_decode(JWT).sub);

  const localhost = 'http://localhost:8080';

  const fetchOptions = {
    method: 'DELETE',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Authorization: `Bearer${sessionStorage.JWT}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  await fetch(`${localhost}/restservices/users/`, fetchOptions) // een POST naar dit adres maakt een nieuw acc.
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
