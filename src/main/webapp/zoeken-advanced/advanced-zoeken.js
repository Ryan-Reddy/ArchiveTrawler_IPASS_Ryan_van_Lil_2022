console.log('loading advanced-zoeken.js');
const feedbackSpan = document.getElementById('feedbackSpan');

/** Haalt opgeslagen zoekqueries op vanaf de server.
 *
 */
function laadZoekertjesVanGebruiker() {
  console.log('loading laadZoekertjesVanGebruiker()');
  document.getElementById('feedbackSpan');

  const combobox = document.getElementById('currentUserZoekertjes');
  var option = document.createElement('option');
  option.value = 'hand';
  console.log(option.value);
  option.text = 'Hand';
  combobox.add(option);

  const fetchOptions = {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${window.sessionStorage.getItem('JWT')}`,
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  fetch(`${localhost}restservices/zoekertjes/haal-op`, fetchOptions).then(
    async (response) => {
      if (response.status === 200) {
        const myJson = await response.json(); // return the search results incl changelog
        console.log(myJson);
      }
      if (response.status === 401 || response.status === 403) {
        feedbackSpan.innerHTML = 'Controleer dat je bent ingelogd.';
      }
    },
  );
}
/** Haalt opgeslagen zoekqueries op vanaf de server.
 *
 */
function slaHuidigeZoekertjeOp() {
  console.log('opslag van huidige zoekopdracht begonnen');
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
  fetch(`${localhost}restservices/zoekertjes/sla-op`, fetchOptions).then(
    async (response) => {
      if (response.status === 200) {
        const myJson = await response.json(); // return the search results incl changelog
        console.log(myJson);
        const myPrettyJsonString = JSON.stringify(myJson, null, 2);
        console.log(myPrettyJsonString);
        const JsonPersonsAsArray = JSON.parse(myPrettyJsonString);
        console.log(JsonPersonsAsArray);
      }
      if (response.status === 401 || response.status === 403) {
        feedbackSpan.innerHTML = 'Controleer dat je bent ingelogd.';
      }
    },
  );
}

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
        feedbackSpan.innerHTML = '<hr><br><br>Zoekresultaten:<br>';
        const myJson = await response.json(); // return the search results incl changelog
        console.log(myJson);
        const myPrettyJsonString = JSON.stringify(myJson, null, 2);

        // for (const key in myJson.key,values,entries) {
        //   const value = obj[key];
        //   document.write(`<br> - ${key}: ${value}`);
        // }
        searchDataTable.innerHTML = ''; // reset de tabel data
        const JsonPersonsAsArrayArchAms = JSON.parse(myPrettyJsonString).person;
        // const JsonPersonsAsArrayOpenArch = JSON.parse(myPrettyJsonString);
        searchDataTable.innerHTML +=
          '' +
          ` <tr><th class="header">Voornaam</th> ` +
          ` <th class="header">Achternaam</th> ` +
          ` <th class="header">Rol</th> ` +
          ` <th class="header">Datum</th> ` +
          ` <th class="header">Plaats</th> ` +
          ` <th class="header">Geboorte</th> ` +
          ` <th class="header">Gewijzigd</th> ` +
          ` <th class="header">Link</th></tr>`;

        JsonPersonsAsArrayArchAms.forEach((element) => {
          // for amsterdam archive
          searchDataTable.innerHTML +=
            '' +
            ` <tr><th class="resultaten">${element.metadata.voornaam}</th> ` +
            ` <th class="resultaten">${element.metadata.achternaam}</th> ` +
            ` <th class="resultaten">${element.metadata.deed_type_title}</th> ` +
            ` <th class="resultaten">${element.metadata.datum}</th> ` +
            `<th  class="resultaten">${element.metadata.register_gemeente}</th> ` +
            ` <th class="resultaten">${element.metadata.datum_geboorte}</th> ` +
            ` <th class="resultaten">${element.metadata.modified_time}</th> ` +
            ` <th class="resultaten"><a href="https://archief.amsterdam/indexen/deeds/${element.deed_id}?person=${element.id}">Link</a></th></tr>`;
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

window.addEventListener('load', () => {
  console.log('window loaded');
  laadZoekertjesVanGebruiker();
});
