const popupTypeVolant = document.querySelector(".popup-content-typevolant");
const bgTypeVolant = document.querySelector('.bg-popup-typevolant');
const bpMenuTypeVolant = document.getElementById('topnav-menu-typevolant');
const bpCloseTypeVolant = document.getElementById('close-typevolant');


//Fonctions :
function openPopupTypeVolant() {
    bgTypeVolant.style.display = 'flex';
    popupTypeVolant.className = "popup-content-typevolant open";
};

function closePopupTypeVolant() {
    popupTypeVolant.className = "popup-content-typevolant close";
    setTimeout(function(){bgTypeVolant.style.display = 'none';}, 500);
};


//Events :
bpMenuTypeVolant.addEventListener('click', openPopupTypeVolant);
bpCloseTypeVolant.addEventListener('click', closePopupTypeVolant);