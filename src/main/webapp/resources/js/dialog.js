'use strict'

const createUserDialog = document.querySelector('#create-user-dialog');
const openCreateUserDialog = document.querySelector('#open-create-user-dialog');
const closeCreateUserDialog = document.querySelector('#close-create-user-dialog');

const updateUserDialog = document.querySelector('#update-user-dialog');
const openUpdateUserDialog = document.querySelector('#open-update-user-dialog');
const closeUpdateUserDialog = document.querySelector('#close-update-user-dialog');

const deleteUserDialog = document.querySelector('#delete-user-dialog');
const openDeleteUserDialog = document.querySelector('#open-delete-user-dialog');
const closeDeleteUserDialog = document.querySelector('#close-delete-user-dialog');

openCreateUserDialog.onclick = () => {
    createUserDialog.showModal();
};

openUpdateUserDialog.onclick = () => {
    updateUserDialog.showModal();
};

openDeleteUserDialog.onclick = () => {
    deleteUserDialog.showModal();
};

closeCreateUserDialog.onclick = () => {
    createUserDialog.close();
};

closeUpdateUserDialog.onclick = () => {
    updateUserDialog.close();
};

closeDeleteUserDialog.onclick = () => {
    deleteUserDialog.close();
};