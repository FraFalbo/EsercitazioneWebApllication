console.log('HELLO JS');


document.getElementById("addContent").addEventListener("click", function() {
    document.getElementById("contentForm").style.display = "block";
    document.getElementById("removeContentForm").style.display = "none";
});

document.getElementById("newContentForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const title = document.getElementById("title").value;
    const year = document.getElementById("year").value;
    const publisher = document.getElementById("publisher").value;

    const tableBody = document.getElementById("contentTable").getElementsByTagName('tbody')[0];


    addNewRowV1(tableBody , title , year , publisher);



    document.getElementById("contentForm").style.display = "none";
    document.getElementById("newContentForm").reset();



});


function addNewRowV1(tableBody , title, year,publisher){

    const newRowHTML = `
        <tr>
            <td>${title}</td>
            <td>${year}</td>
            <td><button  type="button" class="btn btn-outline-dark btn-sm">
                            <a class="nav-link text-dark " href=${publisher}>Link</a>
                        </button></td>
            <td><button class="btn btn-danger btn-sm deleteButton"><i class="fa-solid fa-trash"></i></button></td>
        </tr>
    `;

    tableBody.innerHTML += newRowHTML;

    console.log('Adding new Row , with method 1');
}


