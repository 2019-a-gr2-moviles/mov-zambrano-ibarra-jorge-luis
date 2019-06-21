/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre:{
      type:'string',
      required: true,
      minLength:3,
      maxLength:60
    },
    cedula:{
      type: 'string',
      required: true,
      unique: true,
      minLength:10,
      maxLength:25

    },
    username:{
      type: 'string',
      required: true,
      unique: true
    },
    fechaNacimiento:{
      type: 'string'
    },
    sueldo:{
      type: 'number',
      min: 100,
      max:5000,
      defaultsTo:100.00
    },
    estaCasado:{
      type: 'boolean',
      defaultsTo:false
    },
    latitudCasa:{
      type: 'string'
    },
    longitudCasa:{
      type: 'string',
    },
    tipoUsuario:{
      type: 'string',
      enum:['normal','pendiente','premium']
      
    },
    correo:{
      type: 'string',
      isEmail:true
      
    },
    //Configuración del papá
    serviciosDeUsuario: {//nombre de atributo de la relación
      collection: 'Servicio', //nombre del modelo a relacionar
      via: 'fkUsuario',//nombre del atributo FK del otro modelo
    },
     //Configuración del hijo
     fkEmpresa:{ //nombre del fk para la relacion
      model: 'Empresa'
    }
    
  },

};




//CREAR
//http://localhost:1337/usuario
//ESTANDAR RESTFUL
//MÉTODO HTTP : POST
//Body Params: usuario

//Actualizar 
//http://localhost:1337/usuario/:id
//ESTANDAR RESTFUL
//MÉTODO HTTP : Put
//Body Params: usuario


//buscar por id
