let resJS = [];

fetch('http://localhost:8080/people')
    .then(response => response.json())
    .then(json => document.write(json));



// let serializeUser = JSON.stringify(resJS[1]);
//
// let userOne = JSON.parse(serializeUser);
//
// console.log(userOne.name);