const toBase64 = (file) => new Promise((resolve, reject) => {
  if (file === undefined) resolve('');

  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.addEventListener('load', () => resolve(reader.result));
  reader.addEventListener('error', (error) => reject(error));
});

async function sendFormData(event) {
  const element = document.querySelector('#postresponse');

  const file = document.querySelector('#fileupload').files[0];
  const formData = new FormData(document.querySelector('#postaccount'));
  formData.append('avatarBase64', await toBase64(file));

  const fetchOptions = {
    method: 'POST',
    body: new URLSearchParams(formData),
  };

  const response = await fetch('/restservices/accounts', fetchOptions);
  element.textContent = `Statuscode: ${response.status}`;
}

async function sendJsonData(event) {
  const element = document.querySelector('#postresponse');

  const file = document.querySelector('#fileupload').files[0];
  const jsonRequestBody = {avatarBase64: await toBase64(file)};
  const formData = new FormData(document.querySelector('#postaccount'));
  formData.forEach((value, key) => (jsonRequestBody[key] = value));

  const fetchOptions = {
    method: 'POST',
    body: JSON.stringify(jsonRequestBody),
    headers: {'Content-Type': 'application/json'},
  };
  // http://localhost:8080/restservices/users/addnew/name=Ryry&email=ruru@fufu.mumu&wachtwoord=magicword
  const response = await fetch('/restservices/users/addnew/', fetchOptions);
  element.textContent = `Statuscode: ${response.status}`;
}

function showAccount(myjson) {
  const template = document.querySelector('#accounttemplate');
  const templateDiv = template.content.querySelector('div');
  const newDiv = document.importNode(templateDiv, true);

  newDiv.querySelector('.a_un').innerText = myjson.username;
  newDiv.querySelector('.a_fn').innerText = myjson.fullname;
  newDiv.querySelector('.a_ad').innerText = myjson.address;
  newDiv.querySelector('.a_av').setAttribute('src', myjson.avatarBase64);

  if (myjson.avatarUploadId != null) {
    newDiv
      .querySelector('.a_al')
      .setAttribute('href', `restservices/files/${myjson.avatarUploadId}`);
  } else {
    newDiv.querySelector('.a_al').removeAttribute('href');
  }

  document.querySelector('#accountview').appendChild(newDiv);
}

async function loadAccount(event) {
  const element = document.querySelector('#getresponse');

  const username = document.querySelector('#usertoload').value;
  element.textContent = '';
  document.querySelector('#accountview').innerHTML = '';

  const response = await fetch(`/restservices/account/${username}`);

  if (response.status === 200) {
    const jsonData = await response.json();
    showAccount(jsonData);
  } else element.textContent = `Statuscode: ${response.status}`;
}
