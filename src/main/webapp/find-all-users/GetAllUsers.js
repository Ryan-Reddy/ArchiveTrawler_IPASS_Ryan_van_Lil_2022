console.log('loading getAllUsers.js');
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |  This method adds all users to the page       needs work                  |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

// const button = document.querySelector('#getAllUsers');
const responseSpanElement = document.getElementById('feedbackspan');

function getAllUsersButtonFunc() {
  console.log('getAllUsersButtonFunc() in UserUtils.js');
  const fetchOptions = {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${sessionStorage.getItem('JWT')}`,
      // Authorization: `Bearer ${token}`,
    },
  };

  // fetch('/restservices/users/', fetchOptions)
  // LETOP als fetch niet werkt door missende localhost,
  // check dat de HTTP file themeManager.js importeert als eerste.
  fetch(`${localhost}restservices/users/getall`, fetchOptions) // TODO remove outside of normal testing
    .then((response) => {
      if (!response.ok) {
        // if response =  NOT ok:
        switch (response.status) {
          case 404:
            console.log('could not find the stuff');
            break;
          case 401:
            responseSpanElement.innerText = 'UNAUTHORIZED, make sure you are logged in for this one!';
            console.log('unauthorizedeee');
            break;
          case 403:
            console.log('not high enough clearance');
            break;
          default:
            throw new Error(response.status.toString());
        }
      } // if response NOT ok
      return (
        response
          .json() // if response IS ok:
          // .then(json => console.log('the response.json: ',json))
          // Keep this for troubleshooting
          .then(
            (json) => {
              const array = JSON.stringify(
                json.map((person) => person.naam.string),
              );
              // console.log('the response.json: ', json)
              // do not use the response before assigning it elsewhere
              console.log('array', array);
              console.log('array.split(', array.split(','));
              console.log('array.split( [2]', array.split(',')[2]);
              const arraySplit = array.split(',');

              arraySplit.forEach((user) => {
                responseSpanElement.innerHTML += `<br>${user}<br>`;
              });
              // arraySplit.forEach(user => document.getElementById('testingspace').innerHTML 
              // += JSON.stringify(user) + '<br \>')
            }, // finally a list of usersnaam // Keep this for troubleshooting:
            // .then(json => {
            //     let array = JSON.stringify(json.map(person => person.naam.string));
            //     // let array = json.map(person => person.naam.string);
            //     for(user in array) {
            //       document.getElementById('testingspace').innerHTML += user + '<br \>';
            //     }
            //   }
          )
      ); // finally a list of usersnaam // Keep this for troubleshooting
    })

    .catch((error) => {
      console.log(error);
    }); 
}

function allUsers() {
  console.log('allUsers() in UserUtils.js');
  // fetch(xxxxxxxxxxxxxxxx)
  // const userlistelement = document.getElementById('userlist-html');
  // const bodUsersList = document.getElementById('bodyofUsersList');

  // const testingspaceElement = document.getElementById('testingspace');
  responseSpanElement.innerHTML = 'hello';
}

allUsers();
