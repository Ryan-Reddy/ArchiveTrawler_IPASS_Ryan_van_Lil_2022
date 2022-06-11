const toBase64 = file => new Promise((resolve, reject) => {
    if (file === undefined) resolve("");

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.addEventListener("load", () => resolve(reader.result));
    reader.addEventListener("error", error => reject(error));
});

async function sendFormData(event) {
    let element = document.querySelector("#postresponse");

    let file = document.querySelector("#fileupload").files[0];
    let formData = new FormData(document.querySelector("#postaccount"));
    formData.append("avatarBase64", (await toBase64(file)));

    let fetchOptions = {
        method: "POST",
        body: new URLSearchParams(formData)
    }

    let response = await fetch("/restservices/accounts", fetchOptions);
    element.textContent = "Statuscode: " + response.status;
}

async function sendJsonData(event) {
    let element = document.querySelector("#postresponse");

    let file = document.querySelector("#fileupload").files[0];
    let jsonRequestBody = { "avatarBase64" : await toBase64(file) };
    let formData = new FormData(document.querySelector("#postaccount"));
    formData.forEach( (value, key) => jsonRequestBody[key] = value);

    let fetchOptions = {
        method: "POST",
        body: JSON.stringify(jsonRequestBody),
        headers: {'Content-Type': 'application/json' }
    }

    let response = await fetch("/restservices/accounts", fetchOptions);
    element.textContent = "Statuscode: " + response.status;
}

async function loadAccount(event) {
    let element = document.querySelector("#getresponse");

    let username = document.querySelector("#usertoload").value;
    element.textContent = "";
    document.querySelector("#accountview").innerHTML = "";

    let response = await fetch("/restservices/account/" + username);

    if (response.status == 200) {
        let jsonData = await response.json();
        showAccount(jsonData);
    } else element.textContent = "Statuscode: " + response.status;
}

function showAccount(myjson) {
    let template = document.querySelector("#accounttemplate");
    let templateDiv = template.content.querySelector("div");
    let newDiv = document.importNode(templateDiv, true);

    newDiv.querySelector(".a_un").innerText = myjson.username;
    newDiv.querySelector(".a_fn").innerText = myjson.fullname;
    newDiv.querySelector(".a_ad").innerText = myjson.address;
    newDiv.querySelector(".a_av").setAttribute("src", myjson.avatarBase64);

    if (myjson.avatarUploadId != null) {
        newDiv.querySelector(".a_al").setAttribute("href", "restservices/files/" + myjson.avatarUploadId);
    } else {
        newDiv.querySelector(".a_al").removeAttribute("href");
    }

    document.querySelector("#accountview").appendChild(newDiv);
}