/**
 * Empresa.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

   nombre:{
     type:'string'
   },
   usuariosDeEmpresas: {//nombre de atributo de la relación
    collection: 'Usuario', //nombre del modelo a relacionar
    via: 'fkEmpresa',//nombre del atributo FK del otro modelo
  }

}
}
