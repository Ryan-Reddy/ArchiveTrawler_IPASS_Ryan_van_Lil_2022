// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');


el_up.innerHTML = "Click on the button to create " + "the table from the JSON data.<br><br>" + JSON.stringify(list[0]) + "<br>" + JSON.stringify(list[1]) + "<br>" + JSON.stringify(list[2]);


function getAllUsersButtonFunc() {
  const fetchOptions = {
    method: 'GET', headers: {
      'Authorization': 'Bearer ' + window.sessionStorage.getItem('JWT')
    }
  };
  fetch('restservices/users/', fetchOptions)
    .then(function (response) {
      if (response.ok) {
        const jsonResponse = response.json();

        // for (i in jsonResponse)   TODO still trying to display on screen
        // {
        //   for (j in jsonResponse[i])
        //   {
        //     x = jsonResponse[i][j];
        //     console.log(x);
        //     console.table(x);

          // }
        // }
      }

      // } else if (response.status == 404) {
      //   console.log("could not find the stuff");
      // } else if (response.status == 401) {
      //   console.log("unauthorized")
      // }
    })
    .catch(error => {
      console.log(error);
    })
}
