const popupCommande = document.querySelector(".popup-content-commande");
const bgCommande = document.querySelector('.bg-popup-commande');
const bpMenuCommande = document.getElementById('topnav-menu-commande');
const bpCloseCommande = document.getElementById('close-commande');
const popupCommandeSuite = document.querySelector(".popup-content-commande-suite");
const bgCommandeSuite = document.querySelector('.bg-popup-commande-suite');
const bpMenuCommandeSuite = document.getElementById('topnav-menu-commande-suite');
const bpCloseCommandeSuite = document.getElementById('close-commande-suite');

//Fonctions :
function openPopupCommande() {
    bgCommande.style.display = 'flex';
    popupCommande.className = "popup-content-commande open";
};

function closePopupCommande() {
    popupCommande.className = "popup-content-commande close";
    setTimeout(function(){bgCommande.style.display = 'none';}, 500);
};


function openPopupCommandeSuite() {
    bgCommandeSuite.style.display = 'flex';
    popupCommandeSuite.className = "popup-content-commande-suite open";
};

function closePopupCommandeSuite() {
    popupCommandeSuite.className = "popup-content-commande-suite close";
    setTimeout(function(){bgCommandeSuite.style.display = 'none';}, 500);
};



//Events :
bpMenuCommande.addEventListener('click', openPopupCommande);
bpCloseCommande.addEventListener('click', closePopupCommande);
bpMenuCommandeSuite.addEventListener('click', openPopupCommandeSuite);
bpCloseCommandeSuite.addEventListener('click', closePopupCommandeSuite);