/**
 * Servicio.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
      nombre:{
        type:'string'
      },
      //Configuración del hijo
      fkUsuario:{ //nombre del fk para la relacion
        model: 'Usuario'
      }
      
  },

};

