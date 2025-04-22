// let authenticated = false

// // for now, if the user clicks the button, we will just toggle the authenticated variable
// // TODO update
// const authButton = document.getElementById("auth-button")
// const closedChest = document.getElementById("closed-chest")
// const openChest = document.getElementById("open-chest")
// const loggedOutContent = document.getElementById("logged-out-content")
// const loggedInContent = document.getElementById("logged-in-content")
// authButton.addEventListener("click", () => {

//     authenticated = !authenticated
//     console.log(`authenticated ${authenticated}`)
//     if (authenticated) {
//       authButton.innerText = "Logout"
//       closedChest.style.display = "none"
//       openChest.style.display = "block"
//       loggedOutContent.style.display = "none"
//       loggedInContent.style.display = "block"
//     } else {
//       authButton.innerText = "Login"
//       closedChest.style.display = "block"
//       openChest.style.display = "none"
//       loggedOutContent.style.display = "block"
//       loggedInContent.style.display = "none"
//     }
// })

let authenticated = false;

const authButton = document.getElementById("auth-button");
const closedChest = document.getElementById("closed-chest");
const openChest = document.getElementById("open-chest");
const loggedOutContent = document.getElementById("logged-out-content");
const loggedInContent = document.getElementById("logged-in-content");

const loginDialog = document.getElementById("login-dialog");
const loginClose = document.getElementById("login-close");
const loginSubmit = document.getElementById("login-submit");

authButton.addEventListener("click", () => {
  if (!authenticated) {
    loginDialog.showModal();
  } else {
    logout();
  }
});

loginClose.addEventListener("click", () => {
  loginDialog.close();
});

loginSubmit.addEventListener("click", (e) => {
  e.preventDefault(); // prevent form from submitting
  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;

  // TODO: Replace this with real authentication
  if (username && password) {
    authenticated = true;
    loginDialog.close();
    updateUI();
  } else {
    alert("Please enter username and password");
  }
});

function logout() {
  authenticated = false;
  updateUI();
}

function updateUI() {
  if (authenticated) {
    authButton.innerText = "Logout";
    closedChest.style.display = "none";
    openChest.style.display = "block";
    loggedOutContent.style.display = "none";
    loggedInContent.style.display = "block";
  } else {
    authButton.innerText = "Login";
    closedChest.style.display = "block";
    openChest.style.display = "none";
    loggedOutContent.style.display = "block";
    loggedInContent.style.display = "none";
  }
}
