// function getParsedJwt(token) {
//   try {
//     return JSON.parse(atob(token.split('.')[1]));
//   } catch (error) {
//     return undefined
//   }
// }

import jwt_decode from 'jwt-decode';
/**
 * Deze functie delete de huidige gebruikers account.
 * Dit doet hij d.m.v. een fetch naar de backend, waar deze authenticeert met de JWT
 * En het gekoppelde account ook gelijk zo opzoekt. 
 * Het account wordt niet echt verwijderd, de login wordt alleen geblokkeerd. 
 * Bij de volgende backup wordt deze welliswaar niet meegenomen en is deze voor altijd verwijderd. 
 */
async function deleteAccount() {
  const feedbackspan = document.getElementById('feedbackspan');
  console.log('deleting account');
  const jsonRequestBody = {};
  const JWT = sessionStorage.getItem('JWT');
  const fetchOptions = {
    method: 'DELETE',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Authorization: `Bearer ${sessionStorage.JWT}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  // LETOP als fetch niet werkt door missende localhost,
  // check dat de HTTP file themeManager.js importeert als eerste.
  // eslint-disable-next-line no-undef
  await fetch(`${localhost}restservices/users/`, fetchOptions) // een DELETE naar dit adres verwijdert huidig ingelogd acc.
    .then(async (response) => {
      if (response.ok) {
        const feedback = 'Uw account is verwijderd, en u bent uitgelogd. Hopelijk was dat de bedoeling, '
          + 'als dat niet zo is neem dan contact op met de beheerder.';
        // als het gelukt is dan user uitloggen en melding geven.
        feedbackspan.innerText = feedback;
        sessionStorage.removeItem('JWT');
        throw feedback;
      } else {
        feedbackspan.innerText = 'Er ging iets mis, controleer of de email klopt met het account dat u wil verwijderen.';
        throw 'er ging iets mis';
      } // if !200 there will be no body
    });
}
