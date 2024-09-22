async function getData() {
    const url = "http://localhost:8080/products";
    var products = [];
    await fetch(url)
        .then((res) => res.json())
        .then((resp) => products = resp)
        .catch((err) => console.log(err))

    populateTable(products)
}

const populateTable = (products) => {
    const tb = document.querySelector("#userTableBody");
    products.map(({ id, name, description, price, rating, stock, status }) => {
        tb.innerHTML += `
       <td>${id}</td>
       <td>${name}</td>
       <td>${description}</td>
       <td>${rating}</td>
       <td>R$ ${price}</td>
       <td>${stock}</td>
       <td>${status}</td>
       <td>
             <button onclick="updateStatus(${id})">Desativar/Ativar</button>
       </td>
       <td>
             <button disabled="true">  <a href="../AtualizarProduto/AtualizarProduto.html?id=${id}" target="_blank">Alterar</a></button>
       </td>

       `
    })
}
const updateStatus = (id) => {

    const url = `http://localhost:8080/products/status/${id}`;
    fetch(url, {
        method: "PUT",
        "Content-Type": "application/json",
    })
        .then((res) => {
            if (res.status == 403) {
                alert("Acesso negado!")
            }
            if (res.status == 200) {
                alert("Status atualizado! ");
            }
        })
        .catch((err) => {
            alert("Erro ao atualizar status");
        });
}