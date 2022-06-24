// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');


// el_up.innerHTML = "Click on the button to create " + "the table from the JSON data.<br><br>" + JSON.stringify(list[0]) + "<br>" + JSON.stringify(list[1]) + "<br>" + JSON.stringify(list[2]);


function getAllUsersButtonFunc() {
  const fetchOptions = {
    method: 'GET', headers: {
      'Authorization': 'Bearer ' + window.sessionStorage.getItem('JWT')
    }
  };

  fetch('/restservices/users/', fetchOptions)
    .then((response) => {
      if (!response.ok) {                  // if response =  NOT ok:
        switch (response.status) {
          case 404: return console.log("could not find the stuff") ;
          case 401: return console.log("unauthorized")
          case 403: return console.log("not high enough clearance")
          default:         throw new Error(response.status);
        }
      }
      return response.json()              // if response = ok:
        // .then(json => console.log('the response.json: ',json))
        .then(json => console.log(JSON.stringify(json.map(person => person.naam.string)))) //finally a list of usersnaam
    })







    // disassemble array pieces individually - json objects  /** jsonarray */
    // json object contains -> json array  /** jsonarray */
    //      - 'email': jsonarray  /** jsonarray */
    //      - 'naam': jsonarray  /** jsonarray */
    //      - 'role': jsonarray  /** jsonarray */
    //      - 'hoeveelheid zoekopdrachten':
    //            - 'integral' :true;
    // for (i in jsonResponse)   TODO still trying to display on screen
    // {
    //   for (j in jsonResponse[i])
    //   {
    //     x = jsonResponse[i][j];
    //     console.log(x);
    //     console.table(x);
    // }
    // }
    // } else if (response.status == 404) {
    //   console.log("could not find the stuff");
    // } else if (response.status == 401) {
    //   console.log("unauthorized")
    // }
    // }
    .catch(error => {
      console.log(error);
    })
}
