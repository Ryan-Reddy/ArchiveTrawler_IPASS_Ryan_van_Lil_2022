async function fetchSingleUser() {
  console.log('fetchSingleUser() (jwt.thisUser)')
  const jsonRequestBody = {};

  // const formData = new FormData(document.querySelector('#emailToSearchForm'));
  // formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      "Authorization": "Bearer " + window.sessionStorage.getItem("JWT"),
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  const testingspaceSpan = document.getElementById('feedbackspan');
  // LETOP als fetch niet werkt door missende localhost,
  // check dat de HTTP file themeManager.js importeert als eerste.
  // eslint-disable-next-line no-undef
  await fetch(`${localhost}restservices/users/getAccount/`, fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.status === 200) { // er is een account gevonden !
        let myJson = await response.json();
        console.log(myJson);
        getElementById("naam").defaultValue = myJson.naam;
        getElementById("email").defaultValue = myJson.email;
        // testingspaceSpan.innerHTML += 'naam:  ' + myJson.naam;
        // testingspaceSpan.innerHTML += '<br>email: ' + myJson.email;
        // testingspaceSpan.innerHTML += '<br>rol:   ' + myJson.role;
      }
      if (response.status === 204) { //no content
        const message = 'geen content gevonden voor dit account';
        feedbackspan.innerHTML = message;
        console.log();
      }
    })
    .catch((err) => {

      console.log('Error: ', err);
    });
}

document.getElementById('searchUserButton').addEventListener('click', (event) => {fetchToPage()})
