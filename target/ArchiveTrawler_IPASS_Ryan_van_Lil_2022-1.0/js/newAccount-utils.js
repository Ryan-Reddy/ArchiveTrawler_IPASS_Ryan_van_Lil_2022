// Bind pagina elementen aan const
const doc = document.getElementById.bind(document);

/** Deze functie controleert of een email echt een email is
 *   @Function checkEmailIsEchtEmail
 *   @returns boolean
 */
function checkEmailIsEchtEmail(email) {
  const re = /(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
  return re.test(email);
}

/** This function should check wether the password is correct.
 * rules:
 * -password is at least 6 digits
 *
 * @Function wachtwoordCheck
 * @returns boolean
 */
function formulierCheck() {
  // Bind pagina elementen aan const
  const ww = doc.getElementById('password').value;
  const email = doc.getElementById('email').value;
  const emailElement = doc.getElementById('email');
  const passwordElement = doc.getElementById('password');

  // check of de email een email format heeft, geeft boolean terug
  const emailEcht = checkEmailIsEchtEmail(email);

  console.log('start formulierCheck()');
  // reset alle css waarschuwingen
  passwordElement.classList.replace('formIncomplete', 'inputfield');
  emailElement.classList.replace('formIncomplete', 'inputfield');

  if (emailEcht === false) {
    console.log('email is nep');
    emailElement.classList.replace('inputfield', 'formIncomplete');
    // alert("Uw klopt niet, \r\n voer aub een geldig e-mail adres in.");
  }
  if (ww.length < 6) {
    console.log('ww incompleet');
    passwordElement.classList.replace('inputfield', 'formIncomplete');
    // alert("Uw wachtwoord is niet veilig genoeg. \r\n
    // Voer een combinatie in van ten minste zes cijfers, letters en leestekens (zoals ! en &).")
  }

  if (ww.length > 5 && emailEcht === true) {
    alert(
      'Uw account is aangemaakt, u ontvangt van ons een email met daarin een bevestigingslink. Om uw account te kunnen gebruiken, moet u bevestigen dat uw opgegeven e-mailadres geldig is, door klikken op de bevestigingslink die in uw e-mail is verzonden.\n'
      + 'Als u op de bevestigingslink klikt, wordt u doorgestuurd naar de inlogpagina van uw account. Typ uw inloggegevens en klik op Inloggen om je account te activeren.',
    );
    sendEmail(email);
    sendJsonData();
  }
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
    typeAttribute === 'password' ? 'text' : 'password',
  );
}
