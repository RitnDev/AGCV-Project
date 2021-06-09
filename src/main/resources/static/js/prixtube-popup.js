const popupPrixTube = document.querySelector(".popup-content-prixtube");
const bgPrixTube = document.querySelector('.bg-popup-prixtube');
const bpMenuPrixTube = document.getElementById('topnav-menu-prixtube');
const bpClosePrixTube = document.getElementById('close-prixtube');


//Fonctions :
function openPopupPrixTube() {
    bgPrixTube.style.display = 'flex';
    popupPrixTube.className = "popup-content-prixtube open";
};

function closePopupPrixTube() {
    popupPrixTube.className = "popup-content-prixtube close";
    setTimeout(function(){bgPrixTube.style.display = 'none';}, 500);
};


//Events :
bpMenuPrixTube.addEventListener('click', openPopupPrixTube);
bpClosePrixTube.addEventListener('click', closePopupPrixTube);