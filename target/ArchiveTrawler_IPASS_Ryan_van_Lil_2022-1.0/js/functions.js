const toBase64 = (file) => new Promise((resolve, reject) => {
  if (file === undefined) resolve('');

  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.addEventListener('load', () => resolve(reader.result));
  reader.addEventListener('error', (error) => reject(error));
});

// async function sendFormData(event) {
//   const element = document.querySelector('#postresponse');
//
//   const file = document.querySelector('#fileupload').files[0];
//   const formData = new FormData(document.querySelector('#postaccount'));
//   formData.append('avatarBase64', await toBase64(file));
//
//   const fetchOptions = {
//     method: 'POST',
//     body: new URLSearchParams(formData),
//   };
//
//   const response = await fetch('/restservices/accounts', fetchOptions);
//   element.textContent = `Statuscode: ${response.status}`;
// }
//


// async function sendNewAccount(event) {
//   // const element = document.querySelector('#postresponse');
//   const file = document.querySelector('#fileupload').files[0];
//   const jsonRequestBody = {avatarBase64: await toBase64(file)}; // TODO re implement for security
//
//   const formData = new FormData(document.querySelector('#newaccount-form'));
//   formData.forEach((value, key) => (jsonRequestBody[key] = value));
//   console.log(formData.stringify);
//
//   const fetchOptions = {
//     method: 'POST',
//     body: JSON.stringify(jsonRequestBody),
//     headers: {'Content-Type': 'application/json'},
//   };
//   // http://localhost:8080/restservices/users/addnew/name=Ryry&email=ruru@fufu.mumu&wachtwoord=magicword
//   const response = await fetch('/restservices/users/addnew', fetchOptions);
//   element.textContent = `Statuscode: ${response.status}`;
// }

async function sendNewAccount(event) {
  // const element = document.querySelector('#postresponse');
  // const file = document.querySelector('#fileupload').files[0];
  // const jsonRequestBody = {avatarBase64: await toBase64(file)}; // TODO re implement for security
  let jsonRequestBody = {};

  let formData = new FormData(document.querySelector('#newaccount-form'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      'Accept': 'application/json', 'Content-Type': 'application/json'
    },
  };

  // http://localhost:8080/restservices/users/addnew/name=Ryry&email=ruru@fufu.mumu&wachtwoord=magicword
  let response = await fetch('/restservices/users/addnew/', fetchOptions)


    .then(async function (response) {
      if (response.ok) { // als er een nieuw account gecreeerd is dan inloggen
        throw "welcome! je nieuwe account is aangemaakt"
      }
        // let authJsonRequestBody = {};
//         authJsonRequestBody.put(formData.email);
//         authJsonRequestBody.put(formData.password);
//         const autFetchOptions = {
//             method: "POST",
//             body: JSON.stringify(authJsonRequestBody),
//           headers: {
//             'Accept': 'application/json', 'Content-Type': 'application/json'
//           },
//       };
//       await fetch("/restservices/authentication",)
//       open("http://localhost:8080/html/zoeken.html")
//       return response.json();
//     } //if 200 there will be a body
else
  {
    throw "Wrong username/password";
  }    //if !200 there will be no body
}
)
//
// .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
//   .then(res => console.log(res))
//   .catch(error => console.log(error)); //to handle the possibly thrown error
}


function showAccount(myjson) {
  const template = document.querySelector('#accounttemplate');
  const templateDiv = template.content.querySelector('div');
  const newDiv = document.importNode(templateDiv, true);

  newDiv.querySelector('.a_un').innerText = myjson.username;
  newDiv.querySelector('.a_fn').innerText = myjson.fullname;
  newDiv.querySelector('.a_ad').innerText = myjson.address;
  newDiv.querySelector('.a_av').setAttribute('src', myjson.avatarBase64);

  if (myjson.avatarUploadId != null) {
    newDiv
      .querySelector('.a_al')
      .setAttribute('href', `restservices/files/${myjson.avatarUploadId}`);
  } else {
    newDiv.querySelector('.a_al').removeAttribute('href');
  }

  document.querySelector('#accountview').appendChild(newDiv);
}

async function loadAccount(event) {
  const element = document.querySelector('#getresponse');

  const username = document.querySelector('#usertoload').value;
  element.textContent = '';
  document.querySelector('#accountview').innerHTML = '';

  const response = await fetch(`/restservices/account/${username}`);

  if (response.status === 200) {
    const jsonData = await response.json();
    showAccount(jsonData);
  } else element.textContent = `Statuscode: ${response.status}`;
}
