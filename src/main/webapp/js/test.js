console.log('hello');
console.log(
  fetch(' http://httpbin.org/ip', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: {
      name: 'User 1',
    },
  })
    .then((res) => {
      res.json();
    })
    .then((data) => console.log(data))
    .catch((error) => console.log(`error: ${error}`))
);
