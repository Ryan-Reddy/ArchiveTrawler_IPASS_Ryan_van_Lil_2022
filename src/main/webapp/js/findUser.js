async function fetchSingleUser() {
  console.log('fetchToPage()')
  const jsonRequestBody = {};

  const formData = new FormData(document.querySelector('#emailToSearchForm'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      "Authorization": "Bearer " + window.sessionStorage.getItem("JWT"),
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  await fetch('/restservices/accounts/getAccount/', fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then(async (response) => {
      if (response.status === 200) { // er is een account gevonden !
        let myJson = await response.json();
        console.log(myJson);
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
