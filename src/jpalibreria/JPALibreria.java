package jpalibreria;

import entidades.Autor;
import persistencia.AutorPersistencia;
import persistencia.EditorialPersistencia;

public class JPALibreria {

    public static void main(String[] args) {
        AutorPersistencia autor = new AutorPersistencia();
        EditorialPersistencia ed = new EditorialPersistencia();
        Autor nuevoAutor = new Autor();
//        autor.guardarAutorPorNombre("Damian");
//        autor.guardarAutorPorNombre("Mario");
//        autor.guardarAutorPorNombre("Martin");
//        
//        autor.editarAutor("Damian", "Mike");
//        
//        autor.guardarAutorPorNombre("Damian");
//        autor.borrarAutorPorNombre("Martin");
//        
//        autor.listaAutorPorNombre("Mario");
        autor.borrarListaAutorPorNombre("Mario");
//        ed.crearEditorial("Mongocho ed");
//        ed.editarEditorial("Mongocho ed", "Mongocho Ed.");
    }

}
