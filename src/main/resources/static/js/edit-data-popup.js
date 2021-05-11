const popupEditData = document.querySelector(".popup-content-edit-data");
const bgEditData = document.querySelector('.bg-popup-edit-data');
const bpMenuEditData = document.getElementById('action-edit-data');
const bpCloseEditData = document.getElementById('close-data');


//Fonctions :
function openPopupEditData() {
    bgEditData.style.display = 'flex';
    popupEditData.className = "popup-content-edit-data open";
};

function closePopupEditData() {
    popupEditData.className = "popup-content-edit-data close";
    setTimeout(function(){bgEditData.style.display = 'none';}, 500);
};


//Events :
bpMenuEditData.addEventListener('click', openPopupEditData);
bpCloseEditData.addEventListener('click', closePopupEditData);