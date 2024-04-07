function getAllItems() {
    window.location.href = "http://localhost:8080/items/info?category=&name=";
}

function addNewItem() {
    window.location.href = "http://localhost:8080/items/creation";
}
function updateItem(itemId) {
    let item = {
        id: document.getElementById('itemId_' + itemId).value,
        name: document.getElementById('itemName_' + itemId).value,
        description: document.getElementById('itemDescription_' + itemId).value,
        price: document.getElementById('itemPrice_' + itemId).value,
        category: document.getElementById('itemCategory_' + itemId).value,
        wantedItem: document.getElementById('wantedItem_' + itemId).value

    };
    if(validateEmptyData(item)){
    let xhr = new XMLHttpRequest();
    xhr.open("PATCH", "http://localhost:8080/items", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {
                console.error("Error:", xhr.responseText);
                alert("Error: " + xhr.responseText);
            }
        }
    };
    xhr.send(JSON.stringify(item));}
}

function deleteItem(itemId) {
    let item = {
        id: document.getElementById('itemId_' + itemId).value,
        name: document.getElementById('itemName_' + itemId).value,
        description: document.getElementById('itemDescription_' + itemId).value,
        price: document.getElementById('itemPrice_' + itemId).value,
        category: document.getElementById('itemCategory_' + itemId).value,
        wantedItem: document.getElementById('wantedItem_' + itemId).value

    };
    if(validateEmptyData(item)){
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "http://localhost:8080/items", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {

                console.error("Error:", xhr.responseText);
                alert("Error: " + xhr.responseText);
            }
        }
    };
    xhr.send(JSON.stringify(item));}
}
document.getElementById("userForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let formData = {
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        phoneNumber: document.getElementById("phone").value
    };
    if(validateEmptyData(formData)){
    let xhr = new XMLHttpRequest();
    xhr.open("PATCH", "http://localhost:8080/user", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {
                console.error("Error:", xhr.responseText);
                alert("Error: " + xhr.responseText);
            }
        }
    };
    xhr.send(JSON.stringify(formData));}
});