async function deleteAccount() {
  console.log("deleting account");

  let jsonRequestBody = {};

  let formData = new FormData(document.querySelector('#deleteAccount-form'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));
  console.log(jsonRequestBody);

  const fetchOptions = {
    method: 'DELETE',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  await fetch('/restservices/users/deleteaccount', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async function (response) {
      if (response.ok) {
        // als er een nieuw account gecreeerd is dan inloggen
        throw 'Uw account is verwijderd. Hopelijk was dat de bedoeling, ' +
        'als dat niet zo is neem dan contact op met de beheerder.';
      } else {
        alert(
          "Er ging iets mis, controleer of de email klopt met het account dat u wil verwijderen."
        );
        throw 'er ging iets mis';
      } //if !200 there will be no body
    });
}