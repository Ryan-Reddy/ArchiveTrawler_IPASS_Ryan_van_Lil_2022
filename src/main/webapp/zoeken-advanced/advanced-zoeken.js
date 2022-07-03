function advancedSearchButtonClicked() {
  console.log('advancedSearchButtonClicked()');
  const jsonRequestBody = {};

  const formData = new FormData(document.querySelector('#advancedSearchForm'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Authorization: `Bearer ${window.sessionStorage.getItem('JWT')}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  const feedbackSpan = document.getElementById('feedbackSpan');
  const searchDataTable = document.getElementById('tableSearchData');

  fetch(`${localhost}restservices/search-advanced-service/`, fetchOptions)
    .then(async (response) => {
      if (response.status === 200) {
        // er is een account gevonden !
        const myJson = await response.json(); // return the search results incl changelog
        console.log(myJson);
        const myPrettyJsonString = JSON.stringify(myJson, null, 2);

        // for (const key in myJson.key,values,entries) {
        //   const value = obj[key];
        //   document.write(`<br> - ${key}: ${value}`);
        // }
        searchDataTable.innerHTML = '';
        const JsonPersonsAsArrayArchAms = JSON.parse(myPrettyJsonString).person;
        // const JsonPersonsAsArrayOpenArch = JSON.parse(myPrettyJsonString);

        JsonPersonsAsArrayArchAms.forEach((element) => { // for amsterdam archive
          searchDataTable.innerHTML += `<tr><th>${element.metadata.voornaam}</th> `
          + ` <th>${element.metadata.achternaam}</th> `
          + ` <th><a href="https://webservices.picturae.pro/genealogy/person/${element.id}">Link</a></th></tr>`;
        });
      }
      if (response.status === 403) {
        feedbackSpan.innerHTML = '<p>Check of je bent ingelogd!</p>';
      }
      // if (response.status === 204) {
      //   // no content
      //   const message = 'geen content gevonden voor dit account';
      //   feedbackSpan.innerHTML = message;
      //   console.log(message);
      // }
    })
    .catch((err) => {
      console.log('Error: ', err);
    });
}
/** fetch openArch
 * https://www.openarch.nl/api/docs/
 * fetch van Nederlandse OpenArch
 */
function fetchOpenArch(link) {
  console.log('fetchOpenArch');
  const fetchOptions = {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      // 'Content-Type': 'application/json',
      'Content-Type': 'application/json;charset=UTF-8',
    },
  };
  // fetch("https://api.openarch.nl/1.0/records/search.json?name=van%20Lil&lang=nl&number_show=100&sort=1&start=0&archive", fetchOptions) //
  fetch(link, fetchOptions).then().catch();
}
