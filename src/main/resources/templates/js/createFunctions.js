function getAllItems() {
    window.location.href = "http://localhost:8080/items/info?category=&name=";
}

function addNewItem() {
    window.location.href = "http://localhost:8080/items/creation";
}
document.getElementById("itemForm").addEventListener("submit", function(event) {
    event.preventDefault();
    let formData = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        price: document.getElementById("price").value,
        category: document.getElementById("category").value,
        wantedItem: document.getElementById("wantedItem").value
    };
    if(validateEmptyData(formData)){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/items", true);
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
    xhr.send(JSON.stringify(formData));
}});