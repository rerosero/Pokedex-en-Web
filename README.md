Configuración de Lombok (para las anotaciones @Getter, @Setter, etc.)

1.Abrir el Menú Principal: -Ve al menú principal y selecciona File > Settings. -También puedes usar el atajo de teclado Ctrl + Alt + S.

2.Acceder a la configuración del compilador: -En la sección de configuración, busca y selecciona Build, Execution, Deployment. -Luego, elige la opción Compiler.

3.Habilitar el procesamiento de anotaciones: -En el menú del lado izquierdo, selecciona Annotation Processors. -Verás una lista con el proyecto y tres opciones: --Default --Annotation profile for pokedexweb --pokedexweb

4.Configurar las opciones para cada perfil: -Haz clic en Default. En el lado derecho, asegúrate de marcar las casillas: --Enable annotation processing --Obtain processors from project classpath -Repite este proceso para Annotation profile for pokedexweb

5.Finalización: -Una vez hecho esto, Lombok estará configurado correctamente.

Tener en cuenta que hay que en la clase PokewebAplication hay que descomentar pokemonApiClient.fetch(pokemonInfo); para que los datos de los pokemones puedan enviarse a la base de dato
