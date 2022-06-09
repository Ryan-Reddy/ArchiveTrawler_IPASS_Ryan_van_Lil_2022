const doc = document.getElementById.bind(document);


/** This function should check wether the password is correct.
 * rules:
 * -password is at least 6 digits
 *
 * @Function wachtwoordCheck
 * @returns boolean
 */
function wachtwoordCheck() {
    const ww = document.getElementById("wachtwoord").value;
    const email = document.getElementById("email").value;
    const emailEcht = checkEmailIsEchtEmail(email);
    let check = true;

    if (emailEcht === false) {
        check = false;
        emailFoutMelding();
    }
        if (ww.length < 6 ) {
        check = false;
        wachtWoordTeKortMelding();

    }
    if (ww.length > 5 && emailEcht === true && check===true) {
        accountAangemaaktMelding()
        sendJsonData();
    }
}

/** Deze functie moet een alert te tonen dat het wachtwoord te kort is
*   @Function wachtWoordTeKort
 *   @returns null
 */
function wachtWoordTeKortMelding() {
    alert("Uw wachtwoord is niet veilig genoeg. \r\n  Voer een combinatie in van ten minste zes cijfers, letters en leestekens (zoals ! en &).")
}

/** Deze functie moet een alert te tonen dat het email te kort is
*   @Function emailFoutMelding
 *   @returns null
 */
function emailFoutMelding() {
    alert("Uw klopt niet, \r\n voer aub een geldig e-mail adres in.")
}



/** Deze functie moet een popup melding geven dat het account is aangemaakt
*   @Function wachtwoordLangGenoeg
 *   @returns null
 */
function accountAangemaaktMelding() {
    alert("Uw account is aangemaakt, u ontvangt van ons een email met daarin een bevestigingslink. Om uw account te kunnen gebruiken, moet u bevestigen dat uw opgegeven e-mailadres geldig is, door klikken op de bevestigingslink die in uw e-mail is verzonden.\n" +
        "Als u op de bevestigingslink klikt, wordt u doorgestuurd naar de inlogpagina van uw account. Typ uw inloggegevens en klik op Inloggen om je account te activeren.")
}

/** Deze functie controleert of een email echt een email is
 *   @Function checkEmailIsEchtEmail
 *   @returns boolean
 */
function checkEmailIsEchtEmail(email) {
    const re = /(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
    return re.test(email);
}
