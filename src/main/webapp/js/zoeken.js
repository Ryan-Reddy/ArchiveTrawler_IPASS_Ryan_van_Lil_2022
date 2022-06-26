function zoekButtonClicked() {
  const keywords = document.getElementById('keywords').value;
  const voornaam = document.getElementById('voornaam').value;
  const achternaam = document.getElementById('achternaam').value;
  const tussenvoegsel = document.getElementById('tussenvoegsel').value;

  let jaarVan = document.getElementById('jaarVan').value;
  let jaarTot = document.getElementById('jaarTot').value;

  const dublinArchive = document.getElementById('archiveDublin');
  const amsterdamArchive = document.getElementById('archiveAmsterdam');

  const archiefAmsBaseURL = 'https://archief.amsterdam/indexen/persons?';

  console.log(keywords);
  console.log(voornaam);
  console.log(achternaam);
  console.log(jaarVan);
  console.log(jaarTot);

  console.log(`checked? dub archive:${dublinArchive.checked}`);
  console.log(amsterdamArchive.checked);
  console.log(amsterdamArchive);

  if (amsterdamArchive.checked === true) {
    if (jaarVan === '') {
      jaarVan = 1400;
    }
    if (jaarTot === '') {
      jaarTot = 2000;
    }
    window.open(
      `${archiefAmsBaseURL}f=%7B%22search_i_datum%22:%7B%22v%22:%5B%22${jaarVan}0000%22,%22${jaarTot}9999%22%5D,%22d%22:%22${jaarVan}%20-%20${jaarTot}%22%7D%7D&sa=%7B%22person_1%22:%7B%22q%22:%22${keywords}%22,%22search_t_geslachtsnaam%22:%22${achternaam}%22,%22search_t_tussenvoegsel%22:%22${tussenvoegsel}%22,%22search_t_voornaam%22:%22${voornaam}%22%7D%7D`,
      '_blank',
    );
  }
}
