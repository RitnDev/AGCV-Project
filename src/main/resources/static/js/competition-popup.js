const popupCompetition = document.querySelector(".popup-content-competition");
const bgCompetition = document.querySelector('.bg-popup-competition');
const bpMenuCompetition = document.getElementById('topnav-menu-compet');
const bpCloseCompetition = document.getElementById('close-competition');


//Fonctions :
function openPopupCommande() {
    bgCompetition.style.display = 'flex';
    popupCompetition.className = "popup-content-competition open";
};

function closePopupCommande() {
    popupCompetition.className = "popup-content-competition close";
    setTimeout(function(){bgCompetition.style.display = 'none';}, 500);
};


//Events :
bpMenuCompetition.addEventListener('click', openPopupCommande);
bpCloseCompetition.addEventListener('click', closePopupCommande);