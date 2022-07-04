/** Deze functie controleert of een email echt een email is
 *   @Function checkEmailIsEchtEmail
 *   @returns boolean
 */
function checkEmailIsEchtEmail(email) {
  const res =
    /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return res.test(String(email).toLowerCase());
}

/** Deze functie controleert of het wachtwoord correct is;
 * rules:
 * -password is ten minste 6 tekens lang
 * - email is echt
 * Zo ja dan regelt deze dat de data bij de backend komt,
 * die dit opslaat en de gebruiker hiervan een bevestiging stuurt.
 * De gebruiker krijgt in de nieuwAccount pagina, ook gelijk een melding of dit gelukt is of niet.
 *
 * @Function wachtwoordCheck
 * @returns boolean
 */
function formulierCheck() {
  // Bind pagina elementen aan const
  const ww = document.getElementById('password').value;
  const email = document.getElementById('email').value;
  const emailElement = document.getElementById('email');
  const passwordElement = document.getElementById('password');
  emailElement.addEventListener(
    'mouseover',
    function () {
      emailElement.style.backgroundColor = '#ccc';
    },
    false
  );

  // check of de email een email format heeft, geeft boolean terug
  const emailEcht = checkEmailIsEchtEmail(email);

  console.log('Start formulierCheck().');
  // reset alle css waarschuwingen
  passwordElement.classList.replace('formIncomplete', 'inputfield');
  emailElement.classList.replace('formIncomplete', 'inputfield');

  if (emailEcht === false) {
    console.log('Email is incorrect geformuleerd.');
    emailElement.classList.replace('inputfield', 'formIncomplete');
    alert('Uw email klopt niet, \r\n voer aub een geldig e-mail adres in.');
  }
  if (ww.length < 6) {
    console.log('WW is incompleet');
    passwordElement.classList.replace('inputfield', 'formIncomplete');
    alert(
      'Uw wachtwoord is niet veilig genoeg. \r\n' +
        'Voer een combinatie in van ten minste zes cijfers, letters en leestekens (zoals ! en &).'
    );
  }

  if (ww.length > 5 && emailEcht === true) {
    // sendEmail(email); // EmailService > Backend verplaatst
    alert(      'Uw account is aangemaakt, u ontvangt van ons een email met daarin een bevestigingslink. Om uw account te kunnen gebruiken, moet u bevestigen dat uw opgegeven e-mailadres geldig is, door klikken op de bevestigingslink die in uw e-mail is verzonden.\nAls u op de bevestigingslink klikt, wordt u doorgestuurd naar de inlogpagina van uw account. Typ uw inloggegevens en klik op Inloggen om je account te activeren.'    );

    sendNewAccount();
  }
}

/**
 * Deze functie vervangt normale sendForm, met een eigen SendNewAccount Fetch
 * @param event dit is het klikken op de button
 * @returns {Promise<void>} retourneert de status of het gelukt is.
 */
async function sendNewAccount(event) {
  const element = document.querySelector('#postresponse'); // response span in de newAccountPagina
  const jsonRequestBody = {};

  const formData = new FormData(document.querySelector('#newaccount-form'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  };
  // LETOP als fetch niet werkt door missende localhost,
  // check dat de HTTP file themeManager.js importeert als eerste.
  // eslint-disable-next-line no-undef
  await fetch(`${localhost}restservices/accounts/`, fetchOptions) // een POST naar dit adres maakt een nieuw acc.
    .then((response) => {
      if (response.status === 200) {
        // als er een nieuw account gecreeerd is dan inloggen
        open(`${localhost}/login`, '_self'); // als deze klaar is open de log in pagina
        const message = 'Welkom! je nieuwe account is aangemaakt';
        alert(message);
        element.innerHTML = message;
        throw message;
      }
      if (response.status === 409) {
        // als 409 bestond er al een account met dit email adres
        const message =
          "Er bestaat waarschijnlijk al een account met dit email, of er ging iets anders mis. Als u uw wachtwoord vergeten bent kunt u hieronder klikken op 'Wachtwoord vergeten'.";
        element.innerHTML = message;
        throw message;
      }
    })
    .catch((err) => {
      element.innerHTML = "ERROR, zie de console.";
      console.log('Error: ', err);
    });
}

/** Deze functie zorgt ervoor dat het klikken op een invoerveld hem weer een normale stijl geeft
 * @Function terugNaarNormaleStijl()
 * @param id:String
 * @returns null
 */
function switchIncompleteNormaleStijl(id) {
  const fromIdElement = document.getElementById('id');

  fromIdElement.classList.toggle('inputfield');
  // // const typeAttribute = fromIdElement.getAttribute('class');
  // fromIdElement.classList.replace("formIncomplete",'inputfield');

  // // if(typeAttribute==="formIncomplete") {
  //     fromIdElement.setAttribute('class','inputfield');
  // // }
}

/** Deze functie togglet de zichtbaarheid van het wachtwoord heen en weer
 *   @Function wwZichtbaarToggle()
 *   @returns void
 */
function wwZichtbaarToggle() {
  const passwordElement = document.getElementById('password');
  const typeAttribute = passwordElement.getAttribute('type');

  // Switch het type van 'password' <=> 'text' en vice versa
  passwordElement.setAttribute(
    'type',
    typeAttribute === 'password' ? 'text' : 'password'
  );
}
