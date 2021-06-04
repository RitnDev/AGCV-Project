const popupNewUser = document.querySelector(".popup-content-user");
const bgNewUser = document.querySelector('.bg-popup-user');
const bpMenuNewUser = document.getElementById('topnav-menu-user');
const bpCloseNewUser = document.getElementById('close-user');


//Fonctions :
function openPopupNewUser() {
    bgNewUser.style.display = 'flex';
    popupNewUser.className = "popup-content-user open";
};

function closePopupNewUser() {
    popupNewUser.className = "popup-content-user close";
    setTimeout(function(){bgNewUser.style.display = 'none';}, 500);
};


//Events :
bpMenuNewUser.addEventListener('click', openPopupNewUser);
bpCloseNewUser.addEventListener('click', closePopupNewUser);