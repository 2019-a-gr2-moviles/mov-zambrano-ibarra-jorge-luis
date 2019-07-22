/**
 * Cancha.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    numero: {
      type: 'number',
      required: true,
      unique: true
    },
    desc: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 15,
    },
    precio: {
      type: 'number',
      required: false,
    },
    metros: {
      type: 'number',
      required: true
    },
    canchaDeCliente: {     // Nombre atributo de la relación
      collection: 'reserva', // Nombre del modelo a relacionar
      via: 'codigoCancha'        // Nombre del campo a hacer la relacion
    },
  
  },

};

