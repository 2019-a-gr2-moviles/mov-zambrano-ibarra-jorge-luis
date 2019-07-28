/**
 * Equipo.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
      nombre: {
        type: 'string',
        required:true
      },
      liga: {
        type: 'string',
        required:true
      },
      fechaCreacion: {
        type: 'string',
        required:true
      },
      
      numeroCopasInternacionales: {
        type: 'number',
        min: 0,
        required:true
      },  
      campeonActual: {
        type: 'boolean',
        defaultsTo: false
      },   
  
      jugadorDeEquipo: {     // Nombre atributo de la relación
        collection: 'jugador', // Nombre del modelo a relacionar
        via: 'equipoFutbolId'        // Nombre del campo a hacer la relacion
      },
    
  },

}



