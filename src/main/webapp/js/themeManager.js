// LET OP: dit adres wordt in alle fetches gebruikt in *.js
// const localhost = 'http://localhost:8080/'; // TODO toggle for heroku
const localhost = '/'; // TODO toggle for heroku
// LET OP: dit adres wordt in alle fetches gebruikt in *.js

function setFooter() {
  console.log('setting up the footergrid');
  const footer = document.getElementById('footer');
  footer.classList.add('grid-container');
  // footer.innerHTML = ''; // start met een verse lege innerHTML
  footer.innerHTML +=
    "<div class='footerThemeChoice'>" +
    "<label class='switch' for='darkmodeButton'>" +
    "<img class='themeIcon' src='/assets/icons/first-quarter-moon.png' alt='halfmoon emoji as identifyer for the dark/light mode switch'>" +
    "<input id='darkmodeButton' type='checkbox' onclick='darkmodeToggle()'>" +
    "<span class='slider round'>" +
    '</span></label>' +
    "<div class='footerThemeChoice'>" +
    "<label class='switch' for='retroModeButton'>" +
    "<img class='themeIcon' src='/assets/icons/mirror-ball_1faa9.png' alt='mirrorball emoji as identifyer for the disco mode switch'>" +
    "<input id='retroModeButton' type='checkbox' onclick='retroModeToggle()'>" +
    "<span class='slider round'>" +
    '</span></label></div>' +
    '' +
    '';
  footer.innerHTML +=
    "<div class='footerCenter' id='footerCenter'><ul> <li><label for='homeButton' hidden>Back to homepage.</label><input id='homeButton' type='button' value='home' onclick='window.open(href=\"/\")')'></li> <li><label for='contactButton' hidden>Contact an admin.</label><input id='contactButton' type='button' value='contact' onclick='window.open(href=\"/contact\")')'></li><li><label for='loginButton' hidden>Button to log into an account.</label><input id='loginButton' type='button' value='login' onclick='logOut()')'></li><li><label for='logoutButton' hidden>Button to logout of account.</label><input id='logoutButton' type='button' value='logout' onclick='logOut()'></li></ul></div>";
  footer.innerHTML +=
    '<div class=\'footerLeft\' id="discoballFooter"><iframe referrerpolicy="no-referrer" width="400" height="100vh" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/51195936&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true"></iframe> <div style="font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;"> <a href="https://soundcloud.com/rss" title="RSS Disco" target="_blank" style="color: #cccccc; text-decoration: none;"</a> \u00B7 <a href="https://soundcloud.com/rss/rss4"\r\n title="RSS DISCO #004 / summer 2012" target="_blank" style="color: #cccccc; text-decoration: none;">RSS DISCO\r\n #004 / summer 2012</a> </div></div>';
}

/** laad een eventueel opgeslagen thema voorkeur uit localstorage of maak deze bodyDark */
function LoadPage() {
  const bodyElement = document.getElementById('body');
  console.log('loading page..');
  let storedTheme = null;

  try {
    storedTheme = localStorage.getItem('myArchiveTrawlerTheme'); // probeer thema op te halen uit localStorage
  } catch {
    console.log('no theme found in storage, loading default dark mode.');
  }
  setFooter();
  if (storedTheme != null) {
    console.log(`found stored theme: ${storedTheme}`);
    bodyElement.classList.remove('bodyLight', 'bodyDark', 'bodyDisco');
    bodyElement.classList.add(storedTheme);
    if (bodyElement.className === 'bodyLight') {
      document.getElementById('darkmodeButton').checked = true; // set darkmode button op false
    }

    if (bodyElement.className === 'bodyLight' || 'bodyDark') {
      document.getElementById('discoballFooter').style.display = 'none'; // hide soundcloud embed
    }
    if (bodyElement.className === 'bodyDisco') {
      document.getElementById('retroModeButton').checked = true; // zet retroModeButton aan
      document.getElementById('discoball').style.display = 'inline'; //  discomode > activeer discobal
      const dbFoot = document.getElementById('discoballFooter');
      dbFoot.style.display = 'inline'; // show soundcloud embed
    }
  }
}

/** Dit draait bij het openen pagina: */
window.addEventListener('load', () => LoadPage());

/** Deze functie toggled van darkmode naar light mode en slaat deze keuze op in de localstorage
 *   @Function darkmodeToggle()
 *   @returns void
 */
function darkmodeToggle() {
  document.getElementById('retroModeButton').checked = false; // zet retroModeButton aan

  // TODO schrijf functie darkmode toggle naar default als er op de slider geklikt wordt
  const bodyElement = document.getElementById('body');
  bodyElement.classList.replace('bodyDisco', 'bodyDark');

  // Toggle het id van 'bodyDark' <=> 'bodyLight'
  bodyElement.classList.toggle('bodyDark');
  bodyElement.classList.toggle('bodyLight');

  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className); // sla in localstorage de themakeuze op

  document.getElementById('discoball').style.display = 'none'; // hide discoball gif
  document.getElementById('discoballFooter').style.display = 'none'; // hide soundcloud embed
}

/** <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <br />
 * Deze functie zet de normale dark/lightmode uit
 * En activeert discoMode kleurenschema, en een discobal!
 * Ook tovert deze een mooie plaat tevoorschijn om bij te dansen. <br />
 * <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3
 */
function retroModeToggle() {
  darkmodeToggle();
  document.getElementById('darkmodeButton').checked = false; // set darkmode button op false
  document.getElementById('retroModeButton').checked = true; // zet retroModeButton aan

  const bodyElement = document.getElementById('body');
  bodyElement.classList.remove('bodyLight', 'bodyDark');

  // document.getElementById('switchLabel').innerHTML = 'hello';

  bodyElement.classList.add('bodyDisco');

  document.getElementById('discoball').style.display = 'block'; // show discoball gif
  document.getElementById('discoballFooter').style.display = 'block'; // show soundcloud embed

  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className); // sla in localstorage de themakeuze op
}

function printData() {
  const divToPrint = document.getElementById('resultatenOmTePrinten');
  const newWin = window.open('');
  newWin.document.write(divToPrint.outerHTML);
  newWin.print();
  newWin.close();
}
