const popupNewStock = document.querySelector(".popup-content-stock");
const bgNewStock = document.querySelector('.bg-popup-stock');
const bpMenuNewStock = document.getElementById('topnav-menu-stock');
const bpCloseNewStock = document.getElementById('close-stock');


//Fonctions :
function openPopupNewStock() {
    bgNewStock.style.display = 'flex';
    popupNewStock.className = "popup-content-stock open";
};

function closePopupNewStock() {
    popupNewStock.className = "popup-content-stock close";
    setTimeout(function(){bgNewStock.style.display = 'none';}, 500);
};


//Events :
bpMenuNewStock.addEventListener('click', openPopupNewStock);
bpCloseNewStock.addEventListener('click', closePopupNewStock);