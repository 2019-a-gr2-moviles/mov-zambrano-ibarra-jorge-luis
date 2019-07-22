/**
 * Reserva.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    fecha: {
      type: 'string',
      required:true
    },
    horaInicial: {
      type: 'number',
      min: 0,
      required:true
    },

    horaFinal: {
      type: 'number',
      min: 0,
      required:true
    },
    
    codigoCli: {         // Nombre del fk para la relación
      model: 'cliente',   // Nombre del modelo a relacionar (padre) 
      required: true   // OPCIONAL-> Simpre se ingrese el fk
    },
   
     codigoCancha: {         // Nombre del fk para la relación
      model: 'cancha',   // Nombre del modelo a relacionar (padre) 
      required: true   // OPCIONAL-> Simpre se ingrese el fk
    },
   

  },

};

