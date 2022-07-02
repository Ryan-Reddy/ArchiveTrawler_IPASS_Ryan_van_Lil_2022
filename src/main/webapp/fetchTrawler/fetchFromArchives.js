function makeHttpObject() {
  try {
    return new XMLHttpRequest();
  } catch (error) {
  }
  try {
    return new ActiveXObject("Msxml2.XMLHTTP");
  } catch (error) {
  }
  try {
    return new ActiveXObject("Microsoft.XMLHTTP");
  } catch (error) {
  }

  throw new Error("Could not create HTTP request object.");
}

/** fetchToPageAmsterdamArchief
 * fetch van Nederlandse Amsterdam Archief
 */
//  TODO checkout : https://webservices.picturae.com/genealogy/person?apiKey=eb37e65a-eb47-11e9-b95c-60f81db16c0e&page=1&q=lil&rows=10000&sort=score+desc  <------API
// example load resource: https://archief.amsterdam/indexen/deeds/985333e2-5771-56a3-e053-b784100ade19?person=985333e2-5772-56a3-e053-b784100ade19
// example load resource: https://archief.amsterdam/indexen/deeds/99e87e41-e00e-2bb2-e053-b784100a6a2e?person=afa57c3a-c5e6-2667-ff1b-40fc8bd32b61
function fetchToPageAmsterdamArchief(link) {
  console.log('fetchToPageAmsterdamArchief')
  const exampleLink = "https://webservices.picturae.com/genealogy/person?apiKey=eb37e65a-eb47-11e9-b95c-60f81db16c0e&page=1&q=van lil&rows=250&sort=score desc";
  const fetchOptions = {
    method: "POST",
    headers: {
      Accept: 'application/json',
      // 'Content-Type': 'application/json',
      'Content-Type': "application/json;charset=UTF-8",
    }
  }
  fetch(link,fetchOptions)
    .then(
    )
    .catch(
    )


}

/** fetch openArch
 * https://www.openarch.nl/api/docs/
 * fetch van Nederlandse OpenArch
 */
function fetchOpenArch(link) {
  console.log('fetchOpenArch')
  const fetchOptions = {
      method: "POST",
    headers: {
      Accept: 'application/json',
      // 'Content-Type': 'application/json',
      'Content-Type': "application/json;charset=UTF-8",
    }
  }
  // fetch("https://api.openarch.nl/1.0/records/search.json?name=van%20Lil&lang=nl&number_show=100&sort=1&start=0&archive", fetchOptions) //
  fetch(link, fetchOptions)
    .then(
    )
    .catch(
    )
}

// =================================================================================================
// -------------------------------------------------------------------------------------------------

// TODO schrijf functie
//  Some pages hold an overview of the seperate archives,
//  probably will contain more links when more archives get uploaded. Will mark these OVERVIEW
//  Basic instructions on getting an API:
//      1. So first navigate to the site run the search
//      2. Then go ctrl+shift+i into dev mode
//      3. open tab network
//      4. Refresh the page
//      5. Search for the Fetch/XHR disc
//      6. save that link below:


// ~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~~;;;~~~;~~

// https://www.archives.gov/research                    <---- OVERVIEW
//      https://catalog.archives.gov/v1 + {parameters}<------------------------------------------------------------------API
//                  action=search :default
//                  q= to search by keyword.                            https://catalog.archives.gov/api/v1/?q=armadillo
//                  default 10 rows, offset default=0, max 10000        https://catalog.archives.gov/api/v1/?rows=10&offset=9

// https://github.com/usnationalarchives/Catalog-API/blob/master/federalregister.gov
// http://www.archives.gov/open/available-datasets.html
// http://www.archives.gov/open/available-datasets.html    <---- OVERVIEW
// https://www.archives.gov/research                    <---- OVERVIEW
// ~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;

/** NOCORS */// https://genealogy.nationalarchives.ie/                                    <---- OVERVIEW
                /** */
/** NOCORS *///     http://titheapplotmentbooks.nationalarchives.ie/search/tab/index.jsp    <----#protected somewhat
                /** */
/** NOCORS *///     https://www.censussearchforms.nationalarchives.ie/
                /** wills */
/** NOCORS *///     http://census.nationalarchives.ie/search/dw/home.jsp
                /** catholic qualification rolls */
/** NOCORS *///     http://census.nationalarchives.ie/search/cq/home.jsp
                /** marriages */
/** NOCORS *///     http://census.nationalarchives.ie/search/dm/home.jsp
                /** office books valuations */
/** NOCORS *///     http://census.nationalarchives.ie/search/vob/home.jsp
                /** */
/** NOCORS *///     http://census.nationalarchives.ie/search/cl/home.jsp .... BROKEN
                /** Will Registers */
/** NOCORS *///     http://census.nationalarchives.ie/search/wr/home.jsp
/// irish Genealogy.ie: GREAT SOURCE
                /** Church records */
/** NOCORS *///     https://www.soldierswills.nationalarchives.ie/
                /** Civil records */
/** NOCORS */ // https://civilrecords.irishgenealogy.ie/churchrecords/civil-perform-search.jsp?namefm=&namel=Reddy&location=&yyfrom=&yyto=&submit=Search

// ~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;~~
/** NOCORS */// https://www.askaboutireland.ie/links/public-library-informatio/                    <---- OVERVIEW
/** NOCORS */// http://census.nationalarchives.ie/                    <---- OVERVIEW


// /** fetchToPageWieIsWie
//  * no CORS allowed
//  */
// function fetchToPageWieIsWie() {
//   console.log('fetchToPageWieIsWie')
//   const fetchOptions = {
//       method: "POST",
//     headers: {
//       Accept: 'application/json',
//       'Content-Type': 'application/json',
//       // 'Content-Type': "application/json;charset=UTF-8",
//     }, "body": JSON.stringify({
//       "SearchTerm": "van Lil", "Page": 1, "PersonA": {
//         "VoornaamSearchType": 3,
//         "TussenvoegselSearchType": 3,
//         "AchternaamSearchType": 3,
//         "PatroniemSearchType": 3,
//         "BeroepSearchType": 3,
//         "WithoutTussenvoegsel": false
//       }, "PersonB": {
//         "VoornaamSearchType": 3,
//         "TussenvoegselSearchType": 3,
//         "AchternaamSearchType": 3,
//         "PatroniemSearchType": 3,
//         "BeroepSearchType": 3,
//         "WithoutTussenvoegsel": false
//       }, "IsAdvancedSearch": false, "PlaatsSearchType": 3, "SortColumn": "lastname.sort", "SortDirection": 1
//     })
//   }
//   fetch("https://www.wiewaswie.nl/Umbraco/Api/nl-NL/Service/GetSearchResults", fetchOptions) //
// }


// fetch(
//     'https://archief.amsterdam/indexen/persons?ss=%7B%22q%22:%22van%20lil%22%7D&rows=250',
//     {
//       // TODO remove outside of testing
//       mode: 'same-origin',
//       method: 'GET',
//       headers: {
//         Accept: 'application/json',
//         'Content-Type': 'application/json',
//       },
//     //   body: JSON.stringify(jsonRequestBody),
//     },
//   )
//     // .then(res => console.log(res))
//     .then((res) => res.JSON)
//     .then((data) => console.log(data))
//     .catch((ERROR) => console.log(ERROR));
// }


// const doc = document.getElementById.bind(document);

//   console.log(doc.select("ul#searched-products"));
//   itemElements = itemElements.select("li");
//   for(Element ele : itemElements){
//   String text = ele.text();
//   console.log(text); //this will return Prodict 1 and Prodict 2
//   }
//   // dataSpan

//   if(tbody) {
//       for (i = 0; i < c.length(); i++) {
//           c[i].setAttribute('id','tr'+i);
//           console.log(i)
//       }
//   }

// function fetchToPage() {
//   fetch('https://catalog.archives.gov/api/v1/')
//     // .then(res => console.log(res))
//     .then((res) => res.JSON)
//     .then((data) => console.log(data))
//     .catch((ERROR) => console.log(ERROR));

// // const doc = document.getElementById.bind(document);

// console.log(doc.select("ul#searched-products"));
// itemElements = itemElements.select("li");
// for(Element ele : itemElements){
// String text = ele.text();
// console.log(text); //this will return Prodict 1 and Prodict 2
// }
// // dataSpan

// if(tbody) {
//     for (i = 0; i < c.length(); i++) {
//         c[i].setAttribute('id','tr'+i);
//         console.log(i)
//     }
// }

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
