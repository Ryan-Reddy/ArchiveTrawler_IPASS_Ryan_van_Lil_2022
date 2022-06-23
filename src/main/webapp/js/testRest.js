// function testGetAllUsers()  {
const button = document.querySelector('#getAllUsers');


// document.getElementById('getAllUsers').addEventListener('click', function () {
// document.querySelector('#getAllUsers').addEventListener('click', function () {


function halleluja() {
  console.log('clicked getAllUsersButton')
  document.querySelector('#getAllUsers').setAttribute('color', 'green');
  const fetchOptions = {
    method: 'GET',
    headers: {
      'Authorization': 'Bearer ' + window.sessionStorage.getItem('JWT')
    }
  };

  const myList = document.querySelector('#myData');

  fetch('restservices/users/', fetchOptions)
    .then(function (response) {
      if (response.ok) {
        let jsonResponse = response.json();
        console.log(jsonResponse); // so far so good




        for (const user of jsonResponse) {
          let listItem = document.createElement('li');
          listItem.appendChild(
            document.createElement('strong')
          ).textContent = user.naam;
          listItem.append(
            ` can be found in ${
              user.email              
            }. Cost: `
          );
          listItem.appendChild(
            document.createElement('strong')
          ).textContent = `£${user.naam}`;
          myList.appendChild(listItem);
        }

      }


      // else if (response.status == 404) console.log("could not find the stuff")
      // else if (response.status == 401) console.log("unauthorized")
    }).catch(error => console.log(error));
}
document.querySelector('#myData').innerHTML = responseString;
console.log('credentials correct, you are a user');
console.log(response);
const responseString = JSON.stringify(response.body);