/**
 * Jugador.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombreCamiseta: {
      type: 'string',
      required:true
    },
    nombreCompletoJugador: {
      type: 'string',
      required:true
    },
    poderEspecialDos: {
      type: 'string',
      required:true
    },
    fechaIngresoEquipo: {
      type: 'string',
      required:true
    },
    goles: {
      type: 'number',
      min: 0,
      required:true
    },    
    equipoFutbolId: {         // Nombre del fk para la relación
      model: 'equipo',   // Nombre del modelo a relacionar (padre) 
      required: true   // OPCIONAL-> Simpre se ingrese el fk
    },
  },

};

