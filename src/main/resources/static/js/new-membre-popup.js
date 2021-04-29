const popupNewMembre = document.querySelector(".popup-content-new-membre");
const bgNewMembre = document.querySelector('.bg-popup-new-membre');
const bpMenuNewMembre = document.getElementById('topnav-menu-membre');
const bpCloseNewMembre = document.getElementById('close-membre');


//Fonctions :
function openPopupNewMembre() {
    bgNewMembre.style.display = 'flex';
    popupNewMembre.className = "popup-content-new-membre open";
};

function closePopupNewMembre() {
    popupNewMembre.className = "popup-content-new-membre close";
    setTimeout(function(){bgNewMembre.style.display = 'none';}, 500);
};


//Events :
bpMenuNewMembre.addEventListener('click', openPopupNewMembre);
bpCloseNewMembre.addEventListener('click', closePopupNewMembre);