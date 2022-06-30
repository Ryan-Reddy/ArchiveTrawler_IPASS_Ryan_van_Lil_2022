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
  const testingspaceSpan = document.getElementById('testingspace');
  await fetch('/restservices/users/getAccount/', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
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
        console.log('geen content gevonden voor die input');
      }
    })
    .catch((err) => {
      console.log('Error: ', err);
    });
}

document.getElementById('searchUserButton').addEventListener('click', (event) => {fetchToPage()})
