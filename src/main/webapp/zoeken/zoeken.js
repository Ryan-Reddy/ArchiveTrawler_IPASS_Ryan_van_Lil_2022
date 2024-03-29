/** Dit is de basiczoekfunctie, deze opent de verschillende sites afzonderlijk,
 *  en toont de resultaten op de website zelf.
 */
function zoekButtonClicked() {
  const voornaam = document.getElementById('voornaam').value;
  const achternaam = document.getElementById('achternaam').value;
  const tussenvoegsel = document.getElementById('tussenvoegsel').value;
  const keywords = document.getElementById('keywords').value;

  let jaarVan = document.getElementById('jaarVan').value;
  let jaarTot = document.getElementById('jaarTot').value;

  const dublinArchive = document.getElementById('archiveDublin');
  const amsterdamArchive = document.getElementById('archiveAmsterdam');
  const nhArchive = document.getElementById('archiveNoordHolland');
  if (jaarVan === '') {
    jaarVan = 1500;
  }
  if (jaarTot === '') {
    jaarTot = 2000;
  }
  const archiefAmsBaseURL = 'https://archief.amsterdam/indexen/persons?';
  if (archiveCivilIreland.checked === true) {
    window.open(
      `https://civilrecords.irishgenealogy.ie/churchrecords/civil-perform-search.jsp?namefm=${achternaam}&namel=${voornaam}&location=&yyfrom=${jaarVan}&yyto=${jaarTot}&submit=Search`,
      '_blank', // _blank opent in nieuw tab
    );
  }
  if (churchRecordsIreland.checked === true) {
    window.open(
      `https://churchrecords.irishgenealogy.ie/churchrecords/search.jsp?namefm=${achternaam}&namel=${voornaam}&location=&yyfrom=${jaarVan}&yyto=${jaarTot}&submit=Search`,
      '_blank', // _blank opent in nieuw tab
    );
  }
  if (usaArchive.checked === true) {
    window.open(
      `https://catalog.archives.gov/search?q=${achternaam}`,
      '_blank', // _blank opent in nieuw tab
    );
  }
  if(us)

  if (amsterdamArchive.checked === true) {
    window.open(
      `${archiefAmsBaseURL}f=%7B%22search_i_datum%22:%7B%22v%22:%5B%22${jaarVan}0000%22,%22${jaarTot}9999%22%5D,%22d%22:%22${jaarVan}%20-%20${jaarTot}%22%7D%7D&sa=%7B%22person_1%22:%7B%22q%22:%22${keywords}%22,%22search_t_geslachtsnaam%22:%22${achternaam}%22,%22search_t_tussenvoegsel%22:%22${tussenvoegsel}%22,%22search_t_voornaam%22:%22${voornaam}%22%7D%7D`,
      '_blank', // _blank opent in nieuw tab
    );
  }
  if (openArchBenelux.checked === true) {
    window.open(
      `https://www.openarch.nl/search.php?name=${voornaam}+${tussenvoegsel}+${achternaam}+${jaarVan}-${jaarTot}&zm1=1&zm2=1&archive=&eventplace=&sourcetype=&relationtype=&eventtype=&sort=1&number_show=10`,
      '_blank', // _blank opent in nieuw tab
    );
  }
}

/** Deze functies zijn momenteel niet geimplementeerd, maar eventueel in een later stadium wel: */
// function fetchFunctionForTesting() {
//   const feedbackElement = document.getElementById('postresponse');

//   fetch('https://archief.amsterdam/indexen/persons?ss=%7B%22q%22:%22henk%22%7D')
//     .then((response) =>
//       // When the page is loaded convert it to text
//       response.text())
//     .then((html) => {
//       // Initialize the DOM parser
//       const parser = new DOMParser();
//       // Parse the text
//       const doc = parser.parseFromString(html, 'text/html');
//       // You can now even select part of that html as you would in the regular DOM
//       // Example:
//       // var docArticle = doc.querySelector('article').innerHTML;
//       console.log(doc);
//     })
//     .catch((err) => {
//       console.log('Failed to fetch page: ', err);
//     });
// }

// function fetchFunctionForTesting() {
//   const elem = document.getElementById('postresponse');

//   fetch('https://archief.amsterdam/indexen/persons?ss=%7B%22q%22:%22henk%22%7D')
//     .then((response) => response.text())
//     .then((text) => {
//       console.log(text);
//       elem.innerHTML = text;
//     });
// }

//   //   const url = 'https://www.wiewaswie.nl/nl/zoeken/?advancedsearch=1';
// //   const url = 'https://www.wiewaswie.nl/nl/zoeken/';
// //   let toddy = {
// //     SearchTerm: '',
// //     Page: 1,
// //     PersonA: {
// //       VoornaamSearchType: 3,
// //       TussenvoegselSearchType: 3,
// //       AchternaamSearchType: 3,
// //       PatroniemSearchType: 3,
// //       BeroepSearchType: 3,
// //       WithoutTussenvoegsel: false,
// //       Achternaam: `${achternaam}`,
// //       Tussenvoegsel: `${tussenvoegsel}`,
// //       Voornaam: `${voornaam}`,
// //     },
// //     PersonB: {
// //       VoornaamSearchType: 3,
// //       TussenvoegselSearchType: 3,
// //       AchternaamSearchType: 3,
// //       PatroniemSearchType: 3,
// //       BeroepSearchType: 3,
// //       WithoutTussenvoegsel: false,
// //     },
// //     IsAdvancedSearch: true,
// //     PlaatsSearchType: 3,
// //     SortColumn: 'lastname.sort',
// //     SortDirection: 1,
// //     PeriodeVan: 1555,
// //     PeriodeTot: 1666,
// //   };
// //   let dataObjectString = JSON.stringify(toddy);
// //   let serializedJson = window.btoa(dataObjectString); //base64 encode functie

// //   //   window.open(`${url}?json=${serializedJson}`);

//   console.log(
//     fetch(url, {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json;charset=utf-8',
//       },
//       body: serializedJson,
//     })
//   );
// }
