



// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');

function getAllUsersButtonFunc() {
  const fetchOptions = {
    method: 'GET', headers: {
      'Authorization': 'Bearer ' + window.sessionStorage.getItem('JWT')
    }
  };

  const localhost = 'https://localhost:8080/';

  // fetch('/restservices/users/', fetchOptions)
  fetch(localhost+ 'restservices/users/', fetchOptions) // TODO remove outside of normal testing
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
        .then(json => {
          const array = JSON.stringify(json.map(person => person.naam.string));
          console.log('array',array);
          console.log('array.split(',array.split(','));
          console.log('array.split( [2]',array.split(',')[2]);


          } //finally a list of usersnaam // Keep this for troubleshooting
        // .then(json => {
        //     let array = JSON.stringify(json.map(person => person.naam.string));
        //     // let array = json.map(person => person.naam.string);
        //     for(user in array) {
        //       document.getElementById('testingspace').innerHTML += user + '<br \>';
        //     }
        //   }
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
