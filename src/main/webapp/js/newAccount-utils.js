// Bind pagina elementen aan const
const doc = document.getElementById.bind(document);
const emailElement = document.getElementById("email");



/** This function should check wether the password is correct.
 * rules:
 * -password is at least 6 digits
 *
 * @Function wachtwoordCheck
 * @returns boolean
 */
function formulierCheck() {
    // Bind pagina elementen aan const
    const ww = document.getElementById("password").value;
    const email = document.getElementById("email").value;
    const emailElement = document.getElementById("email");
    const passwordElement = document.getElementById("password");
    
    let emailEcht = checkEmailIsEchtEmail(email);
    
    if (emailEcht === false) {
        emailElement.classList.add("formIncomplete");
        alert("Uw klopt niet, \r\n voer aub een geldig e-mail adres in.");
    }
    if (ww.length < 6 ) {
        passwordElement.classList.add("formIncomplete");
        alert("Uw wachtwoord is niet veilig genoeg. \r\n  Voer een combinatie in van ten minste zes cijfers, letters en leestekens (zoals ! en &).")
    }

    if (ww.length > 5 && emailEcht === true) {
        alert("Uw account is aangemaakt, u ontvangt van ons een email met daarin een bevestigingslink. Om uw account te kunnen gebruiken, moet u bevestigen dat uw opgegeven e-mailadres geldig is, door klikken op de bevestigingslink die in uw e-mail is verzonden.\n" +
        "Als u op de bevestigingslink klikt, wordt u doorgestuurd naar de inlogpagina van uw account. Typ uw inloggegevens en klik op Inloggen om je account te activeren.")
        sendEmail(email);
        sendJsonData();
    }
}

// /** Deze functie moet een alert te tonen dat het wachtwoord te kort is
// *   @Function wachtWoordTeKort
//  *   @returns null
//  */
// function wachtWoordTeKortMelding() {
    
// }

// /** Deze functie moet een alert te tonen dat het email te kort is
// *   @Function emailFoutMelding
//  *   @returns null
//  */
// function emailFoutMelding() {
// }


// /** Deze functie moet een popup melding geven dat het account is aangemaakt
// *   @Function accountAangemaaktMelding
//  *   @returns null
//  */
// function accountAangemaaktMelding() {
// }

/** Deze functie controleert of een email echt een email is
 *   @Function checkEmailIsEchtEmail
 *   @returns boolean
 */
function checkEmailIsEchtEmail(email) {
    const re = /(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
    return re.test(email);
}

/** Deze functie stuurt een mail
 *   @Function checkAccountconfirmatie
 *   @param email
 *   @returns boolean
 */
function sendEmail(email) {
    // Email.send({
        // Host: "smtp.gmail.com",
        // Username: "noresp.archivetrawler@gmail.com",
        // Password: "sWsmanu5MXtzuBe",
        // // To: email,
        // To: "ryanreddy@hotmail.com",
        // From: "noresp.archivetrawler@gmail.com",
        // Subject: "Accountbevestiging",
        // Body: "Well that was easy!!",

        nodemailer.createTransport({
            host: "smtp.mailtrap.io",
            port: 2525,
            auth: {
              user: "2e726d380b8965",
              pass: "1af43b7b73b93b"
            }
        })
    // })
        .then(console.log('yo sent that mail'))
        .then(function () {
            accountAangemaaktMelding();
        })
    ;
}
const passwordEle = document.getElementById('password');
const toggleEle = document.getElementById('toggle');

/** Deze functie togglet de zichtbaarheid van het wachtwoord heen en weer
 *   @Function wwZichtbaarToggle()
 *   @returns void
 */
function wwZichtbaarToggle() {
    const passwordElement = document.getElementById("password");
    const typeAttribute = passwordElement.getAttribute('type');
        
    // Switch het type van 'password' <=> 'text' en vice versa
    passwordElement.setAttribute(
        'type',
        typeAttribute === 'password' ? 'text' : 'password'
    );
}
