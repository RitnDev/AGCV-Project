const popupCommande = document.querySelector(".popup-content-commande");
const bgCommande = document.querySelector('.bg-popup-commande');
const bpMenuCommande = document.getElementById('topnav-menu-commande');
const bpCloseCommande = document.getElementById('close-commande');


//Fonctions :
function openPopupCommande() {
    bgCommande.style.display = 'flex';
    popupCommande.className = "popup-content-commande open";
};

function closePopupCommande() {
    popupCommande.className = "popup-content-commande close";
    setTimeout(function(){bgCommande.style.display = 'none';}, 500);
};


//Events :
bpMenuCommande.addEventListener('click', openPopupCommande);
bpCloseCommande.addEventListener('click', closePopupCommande);