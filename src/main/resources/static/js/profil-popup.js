const popupNewProfil = document.querySelector(".popup-content-profil");
const bgNewProfil = document.querySelector('.bg-popup-profil');
const bpMenuNewProfil = document.getElementById('topnav-menu-profil');
const bpCloseNewProfil = document.getElementById('close-profil');


//Fonctions :
function openPopupNewProfil() {
    bgNewProfil.style.display = 'flex';
    popupNewProfil.className = "popup-content-profil open";
};

function closePopupNewMembre() {
    popupNewProfil.className = "popup-content-profil close";
    setTimeout(function(){bgNewProfil.style.display = 'none';}, 500);
};


//Events :
bpMenuNewProfil.addEventListener('click', openPopupNewProfil);
bpCloseNewProfil.addEventListener('click', closePopupNewProfil);