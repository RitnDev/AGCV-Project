const popupConsoMois = document.querySelector(".popup-content-consomois");
const bgConsoMois = document.querySelector('.bg-popup-consomois');
const bpMenuConsoMois = document.getElementById('topnav-menu-consomois');
const bpCloseConsoMois = document.getElementById('close-consomois');


//Fonctions :
function openPopupConsoMois() {
    bgConsoMois.style.display = 'flex';
    popupConsoMois.className = "popup-content-consomois open";
};

function closePopupConsoMois() {
    popupConsoMois.className = "popup-content-consomois close";
    setTimeout(function(){bgConsoMois.style.display = 'none';}, 500);
};


//Events :
bpMenuConsoMois.addEventListener('click', openPopupConsoMois);
bpCloseConsoMois.addEventListener('click', closePopupConsoMois);