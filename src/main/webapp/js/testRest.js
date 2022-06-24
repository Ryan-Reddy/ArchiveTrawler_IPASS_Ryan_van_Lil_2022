// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');

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
          case 404:
            console.log("could not find the stuff"); break;
          case 401:
            console.log("unauthorized"); break;
          case 403:
            console.log("not high enough clearance"); break;
          default:         throw new Error(response.status.toString());
        }
      }
      return response.json()              // if response = ok:
        // .then(json => console.log('the response.json: ',json))  // Keep this for troubleshooting
        // .then(json => console.log(JSON.stringify(json.map(person => person.naam.string)))) //finally a list of usersnaam // Keep this for troubleshooting
        .then(json => {
            // let array = JSON.stringify(json.map(person => person.naam.string));
            let array = json.map(person => person.naam.string);
            for(user in array) {
              document.getElementById('testingspace').innerHTML += user + '<br \>';
            }
          }
        ) //finally a list of usersnaam // Keep this for troubleshooting
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
