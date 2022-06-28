function getThisUser() {

}

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
// |  This method adds all users to the page       needs work                  |
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');

function getAllUsersButtonFunc() {
  const fetchOptions = {
    method: 'GET',
    headers: {
      Authorization: `Bearer ${window.sessionStorage.getItem('JWT')}`,
    },
  };

  const localhost = 'http://localhost:8080/';

  // fetch('/restservices/users/', fetchOptions)
  fetch(`${localhost}restservices/users/`, fetchOptions) // TODO remove outside of normal testing
    .then((response) => {
      if (!response.ok) { // if response =  NOT ok:
        switch (response.status) {
          case 404:
            console.log('could not find the stuff');
            break;
          case 401:
            console.log('unauthorized');
            break;
          case 403:
            console.log('not high enough clearance');
            break;
          default:
            throw new Error(response.status.toString());
        }
      } // if response NOT ok
      return response.json() // if response IS ok:
      // .then(json => console.log('the response.json: ',json))  // Keep this for troubleshooting
        .then((json) => {
          const array = JSON.stringify(json.map((person) => person.naam.string));
          // console.log('the response.json: ', json)   // do not use the response before assigning it elsewhere
          console.log('array', array);
          console.log('array.split(', array.split(','));
          console.log('array.split( [2]', array.split(',')[2]);
          const arraySplit = array.split(',');

          arraySplit.forEach((user) => document.getElementById('testingspace').innerHTML += `<br>${user}<br>`);
          // arraySplit.forEach(user => document.getElementById('testingspace').innerHTML += JSON.stringify(user) + '<br \>')
        }, // finally a list of usersnaam // Keep this for troubleshooting:
          // .then(json => {
          //     let array = JSON.stringify(json.map(person => person.naam.string));
          //     // let array = json.map(person => person.naam.string);
          //     for(user in array) {
          //       document.getElementById('testingspace').innerHTML += user + '<br \>';
          //     }
          //   }
        ); // finally a list of usersnaam // Keep this for troubleshooting
    })

    .catch((error) => {
      console.log(error);
    });
}

function allUsers() {
  // fetch(xxxxxxxxxxxxxxxx)
  const userlistelement = document.getElementById('userlist-html');
  const bodUsersList = document.getElementById('bodyofUsersList');
  userlistelement.append('hello');
  userlistelement.append('hello');
}

allUsers();
