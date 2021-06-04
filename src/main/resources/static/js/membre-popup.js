const popupNewMembre = document.querySelector(".popup-content-membre");
const bgNewMembre = document.querySelector('.bg-popup-membre');
const bpMenuNewMembre = document.getElementById('topnav-menu-membre');
const bpCloseNewMembre = document.getElementById('close-membre');


//Fonctions :
function openPopupNewMembre() {
    bgNewMembre.style.display = 'flex';
    popupNewMembre.className = "popup-content-membre open";
};

function closePopupNewMembre() {
    popupNewMembre.className = "popup-content-membre close";
    setTimeout(function(){bgNewMembre.style.display = 'none';}, 500);
};


//Events :
bpMenuNewMembre.addEventListener('click', openPopupNewMembre);
bpCloseNewMembre.addEventListener('click', closePopupNewMembre);