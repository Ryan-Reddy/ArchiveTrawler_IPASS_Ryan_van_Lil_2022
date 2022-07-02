async function wijzigWachtwoord() {
  const feedbackspan = document.getElementById('postresponse');
  const ww1 = document.getElementById('password').value;
  const ww2 = document.getElementById('password2').value;
  if(ww1.length < 6 || ww2.length < 6) {
    console.log("wachtwoord te kort")
    feedbackspan.innerHTML = '<p color=red>Uw wachtwoord is te kort!</p>';
  }
  if (ww1 != ww2) {
    console.log("wachtwoorden verschillen")
    feedbackspan.innerHTML = '<p>Uw wachtwoorden komen niet overeen, controleer nogmaals</p>';
  }
  if (ww1 === ww2 && ww1.length >= 6 && ww2.length >= 6)  {
    const jsonRequestBody = {};
    let formData = new FormData(document.querySelector('#wijzigWachtwoord'));
    formData.forEach((value, key) => (jsonRequestBody[key] = value));
    console.log('with data: ' + jsonRequestBody.toString());

    const fetchOptions = {
      method: 'PATCH', body: JSON.stringify(jsonRequestBody), headers: {
        Authorization: `Bearer ${sessionStorage.JWT}`, Accept: 'application/json', 'Content-Type': 'application/json',
      },
    };
    await fetch(`${localhost}restservices/account-reset/wijzig-wachtwoord/`, fetchOptions) // een POST naar dit adres maakt een nieuw acc.
      .then(async (response) => {
        if (response.ok) {
          const feedback = "Accountinfo wijzigen is gelukt!";
          feedbackspan.innerText = feedback;
          return feedback;
        } else {
          feedbackspan.innerText = 'bent u ingelogd?';
          throw 'er ging iets mis';
        } // if !200 there will be no body
      })
      .catch((error) => {
        console.log(error)
      });
  }
  else {
    console.log('whoops ... have you tried turning it on and off again?')
  }
}