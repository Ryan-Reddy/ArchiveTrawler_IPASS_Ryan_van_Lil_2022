function makeHttpObject() {
  try {return new XMLHttpRequest();}
  catch (error) {}
  try {return new ActiveXObject("Msxml2.XMLHTTP");}
  catch (error) {}
  try {return new ActiveXObject("Microsoft.XMLHTTP");}
  catch (error) {}

  throw new Error("Could not create HTTP request object.");
}

function fetchToPage() {
  var request = makeHttpObject();
  request.open("GET", "https://archief.amsterdam/indexen/persons?ss=%7B%22q%22:%22van%20lil%22%7D&rows=250", true);
  request.send(null);
  request.onreadystatechange = function() {
    if (request.readyState == 4)
      alert(request.responseText);
  };
  // $.ajax({ url: 'https://archief.amsterdam/indexen/persons?ss=%7B%22q%22:%22van%20lil%22%7D&rows=250', success: function(data) { alert(data); } });
}


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

//  https://webservices.picturae.com/genealogy/person?apiKey=eb37e65a-eb47-11e9-b95c-60f81db16c0e&page=1&q=lil&rows=10000&sort=score+desc  <------API

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

// https://genealogy.nationalarchives.ie/                                    <---- OVERVIEW
//     http://titheapplotmentbooks.nationalarchives.ie/search/tab/index.jsp    <----#protected somewhat

//     https://www.censussearchforms.nationalarchives.ie/

/** wills */
//     http://census.nationalarchives.ie/search/dw/home.jsp

/** catholic qualification rolls */
//     http://census.nationalarchives.ie/search/cq/home.jsp

/** marriages */
//     http://census.nationalarchives.ie/search/dm/home.jsp

/** office books valuations */
//     http://census.nationalarchives.ie/search/vob/home.jsp

/** */
//     http://census.nationalarchives.ie/search/cl/home.jsp .... BROKEN

/** Will Registers */
//     http://census.nationalarchives.ie/search/wr/home.jsp

/// irish Genealogy.ie: GREAT SOURCE
/** Church records */
//     https://www.soldierswills.nationalarchives.ie/

/** Civil records */
// https://civilrecords.irishgenealogy.ie/churchrecords/civil-perform-search.jsp?namefm=&namel=Reddy&location=&yyfrom=&yyto=&submit=Search

// ~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;~~;~~;~~~;;;~~~;~~;~~;~~;~~;~~~~;~~;~~;~~

// https://www.askaboutireland.ie/links/public-library-informatio/                    <---- OVERVIEW

// http://census.nationalarchives.ie/                    <---- OVERVIEW
