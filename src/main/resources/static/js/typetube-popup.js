const popupTypeTube = document.querySelector(".popup-content-typetube");
const bgTypeTube = document.querySelector('.bg-popup-typetube');
const bpMenuTypeTube = document.getElementById('topnav-menu-typetube');
const bpCloseTypeTube = document.getElementById('close-typetube');


//Fonctions :
function openPopupTypeTube() {
    bgTypeTube.style.display = 'flex';
    popupTypeTube.className = "popup-content-typetube open";
};

function closePopupTypeTube() {
    popupTypeTube.className = "popup-content-typetube close";
    setTimeout(function(){bgTypeTube.style.display = 'none';}, 500);
};


//Events :
bpMenuTypeTube.addEventListener('click', openPopupTypeTube);
bpCloseTypeTube.addEventListener('click', closePopupTypeTube);