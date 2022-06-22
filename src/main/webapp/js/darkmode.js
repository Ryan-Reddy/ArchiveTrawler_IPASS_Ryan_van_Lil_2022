/** Deze functie toggled van darkmode naar light mode en slaat deze keuze op in de localstorage
 *   @Function darkmodeToggle()
 *   @returns void
 */
function darkmodeToggle() {
  const bodyElement = document.getElementById('body');
  // const classesAsList = bodyElement.classList;


  bodyElement.classList.toggle('bodyDark');
  bodyElement.classList.toggle('bodyLight');


  console.log(bodyElement.classList);


  // Toggle het id van 'body' <=> 'body-dark-mode'
  // //   localStorage.setItem('sheet', sheet);
  // TODO implement localstorage for darkmode so it will toggle sitewide

  const discoBallElement = document.getElementById('discoball');
  discoBallElement.style.display = 'none'; // hide discoball gif
}

// https://stackoverflow.com/questions/46868253/how-to-enable-dark-mode-in-all-pages

// window.onload = () => swapStyleSheet(localStorage.getItem('body') || 'default.css');

function retroModeToggle() {
  const discoBallElement = document.getElementById('discoball');
  discoBallElement.style.display = 'block'; // show discoball gif

  const bodyElement = document.getElementById('body');
  const idAttribute = bodyElement.getAttribute('class');
  // TODO retroMode
  if (!bodyElement.classList.contains('bodyDisco')) {
    bodyElement.classList.toggle('bodyDisco');
  }

  window.onload = () => bodyElement.classList.add(localStorage.getItem('chosenColorScheme') || 'default.css',);

  // // function swapStyleSheet(sheet) {
  // bodyElement.setAttribute(
  //   'class',
  //   idAttribute === 'bodyDisco',
  // )
}
