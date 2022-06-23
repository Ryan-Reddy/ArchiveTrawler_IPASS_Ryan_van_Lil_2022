function setFooter() {
  console.log('setting up the footergrid');
  const footer = document.getElementById('footer');
  footer.classList.add('grid-container');
  // footer.innerHTML = ''; // start met een verse lege innerHTML
  footer.innerHTML += '<div class=\'footerLeft\'><iframe width=\"100%\" height=\"300\" scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" src="https:\/\/w.soundcloud.com\/player\/?url=https%3A\/\/api.soundcloud.com\/tracks\/51195936&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true"></iframe> <div style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\"> <a href=\"https:\/\/soundcloud.com\/rss\" title=\"RSS Disco\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;" RSS Disco</a> \u00B7 <a href=\"https:\/\/soundcloud.com\/rss\/rss4\"\r\n title=\"RSS DISCO #004 \/ summer 2012\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">RSS DISCO\r\n #004 \/ summer 2012</a> </div></div>';
  footer.innerHTML += '<div class=\'footerCenter\' id=\'footerCenter\'><a href="\\/html\\/contact.html\\">Contact</a><br></div>';
  footer.innerHTML += '<div class=\'footerRight\'><label for=\'darkmodeButton\'>Dark/Light mode:</label><input id=\'darkmodeButton\' onclick=\'darkmodeToggle()\' type="button" value=\'☾☼\'/><br/><label for=\'retroModeButton\'>DISCO mode:</label><input alt=\'retroModeButton\' height=\'25\' id=\'retroModeButton\' onclick=\'retroModeToggle()\'src="/assets/icons/mirror-ball_1faa9.png" type="image" width=\'25\'/></div>';

  const leftFoot = document.getElementById('footerLeft');
  leftFoot.innerHTML += '<iframe width=\"100%\" height=\"300\" scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\"\r\n    src=\"https:\/\/w.soundcloud.com\/player\/?url=https%3A\/\/api.soundcloud.com\/tracks\/51195936&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true\"><\/iframe>\r\n<div\r\n    style=\"font-size: 10px; color: #cccccc;line-break: anywhere;word-break: normal;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; font-family: Interstate,Lucida Grande,Lucida Sans Unicode,Lucida Sans,Garuda,Verdana,Tahoma,sans-serif;font-weight: 100;\">\r\n    <a href=\"https:\/\/soundcloud.com\/rss\" title=\"RSS Disco\" target=\"_blank\"\r\n        style=\"color: #cccccc; text-decoration: none;\">RSS Disco<\/a> \u00B7 <a href=\"https:\/\/soundcloud.com\/rss\/rss4\"\r\n        title=\"RSS DISCO #004 \/ summer 2012\" target=\"_blank\" style=\"color: #cccccc; text-decoration: none;\">RSS DISCO\r\n        #004 \/ summer 2012<\/a><\/div>';

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
  if (storedTheme != null) {
    console.log(`found theme!${storedTheme}`);
    bodyElement.classList.remove('bodyLight', 'bodyDark', 'bodyDisco');
    bodyElement.classList.add(storedTheme);
    if (bodyElement.className === 'bodyDisco') {
      // als discomode > activeer discobal
      document.getElementById('discoball').style.display = 'block'; // show discoball gif
    }
  }
  setFooter();
}

/** Dit draait bij het openen pagina: */
window.onload = () => {
  LoadPage();
};

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

  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className); // sla in localstorage de themakeuze op

  document.getElementById('discoball').style.display = 'none'; // hide discoball gif
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
  localStorage.setItem('myArchiveTrawlerTheme', bodyElement.className); // sla in localstorage de themakeuze op
}
