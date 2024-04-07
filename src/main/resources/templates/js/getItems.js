let categoryVal = '';

function selectCategory(category) {
    let categories = document.querySelectorAll('.category');
    categories.forEach(function (element) {
        if (element.getAttribute('data-category') === category) {
            if (!element.classList.contains('selected')) {
                categoryVal = category;
                element.classList.add('selected');
            } else {
                element.classList.remove('selected');
            }
        } else {
            element.classList.remove('selected');
        }
    });
}

function addNewItem() {
    window.location.href = "http://localhost:8080/items/creation";
}

function searchItems() {
    let searchQuery = document.getElementById('searchInput').value;
    window.location.href = "http://localhost:8080/items/info?category=" + categoryVal + "&name=" + searchQuery;
}