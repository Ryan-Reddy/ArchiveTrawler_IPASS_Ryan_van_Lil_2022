/** Dit draait bij het openen pagina: */
window.onload = () => {
  LoadPage();
};


/** laad een eventueel opgeslagen thema voorkeur uit localstorage of maak deze bodyDark */
function LoadPage() {
  const bodyElement = document.getElementById('body');
  console.log('loading page..')
  let storedTheme = null;

  try { storedTheme = localStorage.getItem('myArchiveTrawlerTheme'); // probeer thema op te halen uit localStorage
  } catch {
    console.log('no theme found in storage, loading default dark mode.')
  }
  if (storedTheme != null) {
    console.log('found theme!' + storedTheme)
    bodyElement.classList.remove('bodyLight', 'bodyDark', 'bodyDisco');
    bodyElement.classList.add(storedTheme);
    if (bodyElement.className === 'bodyDisco') { // als discomode > activeer discobal
      document.getElementById('discoball').style.display = 'block'; // show discoball gif
    }
  }
  const footerCelement = document.getElementById('footerCenter');
  footerCelement.innerHTML += 'hello';
}

/** Deze functie toggled van darkmode naar light mode en slaat deze keuze op in de localstorage
 *   @Function darkmodeToggle()
 *   @returns void
 */
function darkmodeToggle() {
  const bodyElement = document.getElementById('body');
  bodyElement.classList.replace('bodyDisco', 'bodyDark');

  // Toggle het id van 'bodyDark' <=> 'bodyLight'
  bodyElement.classList.toggle('bodyDark');
  bodyElement.classList.toggle('bodyLight');

  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className);  // sla in localstorage de themakeuze op


  document.getElementById('discoball').style.display = 'none';    // hide discoball gif
}

/** <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <br />
 * Deze functie zet de normale dark/lightmode uit
 * En activeert discoMode kleurenschema, en een discobal!
 * Ook tovert deze een mooie plaat tevoorschijn om bij te dansen. <br />
 * <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3
 */
function retroModeToggle() {
  const bodyElement = document.getElementById('body');
  bodyElement.classList.remove('bodyLight', 'bodyDark');

  bodyElement.classList.add('bodyDisco');

  document.getElementById('discoball').style.display = 'block'; // show discoball gif
  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className);  // sla in localstorage de themakeuze op

}





