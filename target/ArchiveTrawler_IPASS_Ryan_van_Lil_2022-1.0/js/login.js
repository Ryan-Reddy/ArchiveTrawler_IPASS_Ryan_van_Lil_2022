function login() {
  let formData = new FormData(document.querySelector("#loginform"));
  let jsonRequestBody = {}
  formData.forEach((value, key) => jsonRequestBody[key] = value);
  console.log(JSON.stringify(jsonRequestBody));
  fetch("/restservices/authentication", {
    method: "POST", headers: {
      'Accept': 'application/json', 'Content-Type': 'application/json'
    }, body: JSON.stringify(jsonRequestBody)
  })

    .then(function (response) {
      if (response.ok) return response.json(); //if 200 there will be a body
      else throw "Wrong username/password";    //if !200 there will be no body
    })
    .then(myJson => window.sessionStorage.setItem("myJWT", myJson.JWT))
    .then(res => console.log(res))
    .catch(error => console.log(error)); //to handle the possibly thrown error
}

document.querySelector("#login").addEventListener("click", login);
