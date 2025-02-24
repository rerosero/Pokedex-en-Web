// main.js
// Tabla con todos los pokemones ordenado de manera alfabetica
// Variable para almacenar la referencia del gráfico
// Variable para almacenar la referencia del gráfico
let chart = null;

// Event listener para cuando el contenido se haya cargado
document.addEventListener('DOMContentLoaded', () => {
    // Llamamos a la API para obtener la lista de Pokémon
    fetch('/api/pokemon/all')  // Esta ruta devuelve todos los Pokémon
        .then(response => response.json())
        .then(data => {
            // Llenamos la lista desplegable con los nombres de los Pokémon
            const select = document.getElementById('pokemonSelect');
            const idSelect = document.getElementById('searchInput');

            data.forEach((pokemon, index) => {
                // Llenar el select de nombres
                const option = document.createElement('option');
                option.value = pokemon.id;  // Usamos el ID del Pokémon
                option.textContent = pokemon.name;  // Agregar el nombre del Pokémon
                if (index === 0) option.selected = true;  // Marcar el primer Pokémon como seleccionado
                select.appendChild(option);

                // Llenar el select de IDs
                const idOption = document.createElement('option');
                idOption.value = pokemon.id;  // Usamos el ID del Pokémon
                idOption.textContent = pokemon.id;  // Agregar el ID del Pokémon
                idSelect.appendChild(idOption);
            });

            // Mostrar los detalles del primer Pokémon (o del seleccionado)
            showPokemonDetails(data[0].id);  // Mostrar detalles del primer Pokémon al cargar

            // Event listener para cuando el usuario cambia de Pokémon en la lista de nombres
            select.addEventListener('change', (event) => {
                const selectedPokemonId = event.target.value;
                showPokemonDetails(selectedPokemonId);
            });

            // Event listener para cuando el usuario cambia de Pokémon en la lista de IDs
            idSelect.addEventListener('change', (event) => {
                const selectedPokemonId = event.target.value;
                showPokemonDetails(selectedPokemonId);
            });

            // Función para mostrar los detalles de un Pokémon
            function showPokemonDetails(pokemonId) {
                // Hacer la solicitud para obtener los detalles del Pokémon por ID
                fetch(`/api/pokemon/${pokemonId}`)
                    .then(response => response.json())
                    .then(pokemon => {
                        document.getElementById('name').textContent = pokemon.name;
                        document.getElementById('height').textContent = pokemon.height;
                        document.getElementById('weight').textContent = pokemon.weight;
                        document.getElementById('description').textContent = pokemon.description;

                        // Mostrar los nuevos detalles (tipos, habilidades, generación, hábitat)
                        document.getElementById('types').textContent = pokemon.types.join(', ');
                        document.getElementById('abilities').textContent = pokemon.abilities.join(', ');
                        document.getElementById('generation').textContent = pokemon.generationName;
                        document.getElementById('habitat').textContent = pokemon.habitatName;
                        document.getElementById('species').textContent = pokemon.speciesName;
                        // Asignamos la imagen del Pokémon
                        const pokemonImage = document.getElementById('pokemonImagen');
                        pokemonImage.src = pokemon.spriteUrl;
                        pokemonImage.alt = pokemon.name;

                        // Actualizar el gráfico de barras
                        updateGraph(pokemon);
                    })
                    .catch(error => console.error('Error al obtener los detalles del Pokémon:', error));
            }

            // Función para actualizar el gráfico
            function updateGraph(pokemon) {
                const ctx = document.getElementById('statsChart').getContext('2d');

                // Si ya existe un gráfico, destrúyelo antes de crear uno nuevo
                if (chart) {
                    chart.destroy(); // Destruir el gráfico previo
                }

                // Crear el nuevo gráfico
                chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['HP', 'Ataque', 'Defensa', 'Velocidad'], // Los atributos que se mostrarán
                        datasets: [{
                            label: pokemon.name,
                            data: [pokemon.hp, pokemon.attack, pokemon.defense, pokemon.speed],
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true // Asegura que la escala del eje Y comience en 0
                            }
                        }
                    }
                });
            }
        })
        .catch(error => console.error('Error al cargar la lista de Pokémon:', error));
});

/*let chart = null;

// Event listener para cuando el contenido se haya cargado
document.addEventListener('DOMContentLoaded', () => {
    // Llamamos a la API para obtener la lista de Pokémon
    fetch('/api/pokemon/all')  // Esta ruta devuelve todos los Pokémon
        .then(response => response.json())
        .then(data => {
            // Llenamos la lista desplegable con los nombres de los Pokémon
            const select = document.getElementById('pokemonSelect');
            data.forEach((pokemon, index) => {
                const option = document.createElement('option');
                option.value = pokemon.id;  // Usamos el ID del Pokémon
                option.textContent = pokemon.name;  // Agregar el nombre del Pokémon
                if (index === 0) option.selected = true;  // Marcar el primer Pokémon como seleccionado
                select.appendChild(option);
            });

            // Mostrar los detalles del primer Pokémon (o del seleccionado)
            showPokemonDetails(data[0].id);  // Mostrar detalles del primer Pokémon al cargar

            // Event listener para cuando el usuario cambia de Pokémon en la lista
            select.addEventListener('change', (event) => {
                const selectedPokemonId = event.target.value;
                showPokemonDetails(selectedPokemonId);
            });

            // Función para mostrar los detalles de un Pokémon
            function showPokemonDetails(pokemonId) {
                // Hacer la solicitud para obtener los detalles del Pokémon por ID
                fetch(`/api/pokemon/${pokemonId}`)
                    .then(response => response.json())
                    .then(pokemon => {
                        document.getElementById('name').textContent = pokemon.name;
                        document.getElementById('height').textContent = pokemon.height;
                        document.getElementById('weight').textContent = pokemon.weight;
                        document.getElementById('description').textContent = pokemon.description;

                        // Mostrar los nuevos detalles (tipos, habilidades, generación, hábitat)
                        document.getElementById('types').textContent = pokemon.types.join(', ');
                        document.getElementById('abilities').textContent = pokemon.abilities.join(', ');
                        document.getElementById('generation').textContent = pokemon.generationName;
                        document.getElementById('habitat').textContent = pokemon.habitatName;
                        document.getElementById('species').textContent = pokemon.speciesName;
                        // Asignamos la imagen del Pokémon
                        const pokemonImage = document.getElementById('pokemonImagen');
                        pokemonImage.src = pokemon.spriteUrl;
                        pokemonImage.alt = pokemon.name;

                        // Actualizar el gráfico de barras
                        updateGraph(pokemon);
                    })
                    .catch(error => console.error('Error al obtener los detalles del Pokémon:', error));
            }

            // Función para actualizar el gráfico
            function updateGraph(pokemon) {
                const ctx = document.getElementById('statsChart').getContext('2d');

                // Si ya existe un gráfico, destrúyelo antes de crear uno nuevo
                if (chart) {
                    chart.destroy(); // Destruir el gráfico previo
                }

                // Crear el nuevo gráfico
                chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ['HP', 'Ataque', 'Defensa', 'Velocidad'], // Los atributos que se mostrarán
                        datasets: [{
                            label: pokemon.name,
                            data: [pokemon.hp, pokemon.attack, pokemon.defense, pokemon.speed],
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true // Asegura que la escala del eje Y comience en 0
                            }
                        }
                    }
                });
            }
        })
        .catch(error => console.error('Error al cargar la lista de Pokémon:', error));
});*/

// tabla
document.addEventListener('DOMContentLoaded', function () {
    // Llamada al endpoint de la API para obtener todos los Pokémon
    fetch('/api/pokemon/all')
        .then(response => response.json()) // Convertir la respuesta en JSON
        .then(data => {
            const tableBody = document.getElementById('pokemon-table').getElementsByTagName('tbody')[0];

            // Iteramos sobre los Pokémon y los agregamos a la tabla
            data.forEach(pokemon => {
                const row = tableBody.insertRow(); // Crear una nueva fila en la tabla

                // Crear y agregar celdas a la fila
                const idCell = row.insertCell(0);
                idCell.textContent = pokemon.id; // Agregar ID del Pokémon

                const nameCell = row.insertCell(1);
                nameCell.textContent = pokemon.name; // Agregar Nombre del Pokémon

                const heightCell = row.insertCell(2);
                heightCell.textContent = pokemon.height; // Agregar Altura

                const weightCell = row.insertCell(3);
                weightCell.textContent = pokemon.weight; // Agregar Peso

                const descriptionCell = row.insertCell(4);
                descriptionCell.textContent = pokemon.description; // Agregar Descripción

                const pokeImagenCell = row.insertCell(5);
                const pokeImage = document.createElement('img');// Crear un elemento de imagen

                pokeImage.src = pokemon.spriteUrl; // Asignar la URL de la imagen
                pokeImage.alt = pokemon.name; // Establecer un alt en caso de que la imagen falle
                pokeImage.classList.add('pokemon-img'); // Añadir la clase para aplicar el estilo desde CSS
                pokeImagenCell.appendChild(pokeImage); // Agregar la imagen a la celda

                const hpCell = row.insertCell(6);
                hpCell.textContent = pokemon.hp; // Agregar HP

                const attackCell = row.insertCell(7);
                attackCell.textContent = pokemon.attack; // Agregar Ataque

                const defenseCell = row.insertCell(8);
                defenseCell.textContent = pokemon.defense; // Agregar Defensa

                const speedCell = row.insertCell(9);
                speedCell.textContent = pokemon.speed; // Agregar Velocidad

                const totalCell = row.insertCell(10);
                totalCell.textContent = pokemon.total; // Agregar Total
            });
        })
        .catch(error => console.error('Error al obtener Pokémon:', error)); // Manejo de errores
});

