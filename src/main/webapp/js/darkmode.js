const bodyElement = document.getElementById('body');

// laad een eventueel opgeslagen thema voorkeur uit localstorage of maak deze bodyDark
window.onload = () => bodyElement.classList.replace(localStorage.getItem('myArchiveTrawlerTheme')||'bodyDark');

window.onload = () => {if(bodyElement.className === 'bodyDisco') { // als discomode > activeer discobal
  document.getElementById('discoball').style.display = 'block'; // show discoball gif
} };

/** Deze functie toggled van darkmode naar light mode en slaat deze keuze op in de localstorage
 *   @Function darkmodeToggle()
 *   @returns void
 */
function darkmodeToggle() {
  const bodyElement = document.getElementById('body');
  bodyElement.classList.replace('bodyDisco','bodyDark');

  // Toggle het id van 'bodyDark' <=> 'bodyLight'
  bodyElement.classList.toggle('bodyDark');
  bodyElement.classList.toggle('bodyLight');

    localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className);  // sla in localstorage de themakeuze op


  document.getElementById('discoball').style.display = 'none';    // hide discoball gif
}

function retroModeToggle() {
  const bodyElement = document.getElementById('body');
  bodyElement.classList.remove('bodyLight','bodyDark');

  bodyElement.classList.add('bodyDisco');

  document.getElementById('discoball').style.display = 'block'; // show discoball gif
  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className);  // sla in localstorage de themakeuze op

}




