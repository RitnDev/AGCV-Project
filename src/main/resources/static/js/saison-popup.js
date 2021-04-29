const popupSaison = document.querySelector(".popup-content-saison");
const bgSaison = document.querySelector('.bg-popup-saison');
const bpMenuSaison = document.getElementById('topnav-menu-saison');
const bpCloseSaison = document.getElementById('close-saison');


//Fonctions :
function openPopupSaison() {
    bgSaison.style.display = 'flex';
    popupSaison.className = "popup-content-saison open";
};

function closePopupSaison() {
    popupSaison.className = "popup-content-saison close";
    setTimeout(function(){bgSaison.style.display = 'none';}, 500);
};


//Events :
bpMenuSaison.addEventListener('click', openPopupSaison);
bpCloseSaison.addEventListener('click', closePopupSaison);
