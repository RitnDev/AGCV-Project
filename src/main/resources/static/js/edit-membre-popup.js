const popupEditMembre = document.querySelector(".popup-content-edit-membre");
const bgEditMembre = document.querySelector('.bg-popup-edit-membre');
const bpMenuEditMembre = document.getElementById('action-edit-membre');
const bpCloseEditMembre = document.getElementById('close-membre');


//Fonctions :
function openPopupEditMembre() {
    bgEditMembre.style.display = 'flex';
    popupEditMembre.className = "popup-content-edit-membre open";
};

function closePopupEditMembre() {
    popupEditMembre.className = "popup-content-edit-membre close";
    setTimeout(function(){bgEditMembre.style.display = 'none';}, 500);
};


//Events :
bpMenuEditMembre.addEventListener('click', openPopupEditMembre);
bpCloseEditMembre.addEventListener('click', closePopupEditMembre);