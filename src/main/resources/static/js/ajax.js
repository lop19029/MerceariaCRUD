document.querySelector('#name').addEventListener('keyup', event => {
                const nameContains = event.target.value;
                findProductsByNameContains(stringToBase64(nameContains));
            });

            async function findProductsByNameContains(nameContains) {
            let products = await fetch('http://localhost:8080/product/json?name=' + nameContains )
                .then(data => data.json());

            let count = Object.keys(products).length;

            document.querySelector('#tableBody').innerHTML = '';

            if(count <= 0 ){
                document.querySelector('#tableBody').innerHTML = 'Nenhum producto encontrado.';
            } else {
                document.querySelector('#tableBody').innerHTML = products.map(product => `
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.quantity}</td>
                    <td>${product.price}</td>
                    <td><a class="btn-primary btn-sm" href="http://localhost:8080/product/details?id=${product.id}">Detalhes</a></td>
                    <td><a class="btn-primary btn-sm" href="http://localhost:8080/product/edit?id=${product.id}">Editar</a></td>
                    <td><a class="btn-danger btn-sm" href="http://localhost:8080/product/delete?id=${product.id}" onclick="return confirm('Você realmente deseja excluir esse producto?')">Excluir</a></td>
                </tr>
            `).join('');
            }
        }

        function stringToBase64(string) {
            return btoa(unescape(encodeURIComponent(string)));
        }