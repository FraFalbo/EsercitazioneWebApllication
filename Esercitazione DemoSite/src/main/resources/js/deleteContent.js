document.getElementById("removeContentButton").addEventListener("click", function() {
    document.getElementById("removeContentForm").style.display = "block";
    document.getElementById("contentForm").style.display = "none";
});

function removeBookByNumber(event) {
    event.preventDefault();

    const contentNumber = parseInt(document.getElementById("contentNumber").value);

    const rows =document.getElementById('contentTable').querySelectorAll('tr');

    if (contentNumber >=1  && contentNumber <= rows.length) {
        rows[contentNumber - 1].remove();
        document.getElementById("removeContentForm").style.display = "none";
        document.getElementById("removeContentByNumberForm").reset();
    } else {
        alert("Numero Content errato!");
    }
}


document.querySelectorAll(".deleteButton").forEach(button => {
    button.addEventListener("click", function() {
        const row = button.closest("tr");
        row.parentNode.removeChild(row);
    });
});
