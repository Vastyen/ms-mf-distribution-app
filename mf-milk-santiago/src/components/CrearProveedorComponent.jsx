import React, { Component } from 'react';
import ProveedorService from '../services/ProveedorService';

class CrearProveedorComponent extends Component {
  constructor(props) {
    super(props);

    this.state = {
      codigo: '',
      nombre: '',
      categoria: '',
      afecto: ''
    };

    this.changeCodigoHandler = this.changeCodigoHandler.bind(this);
    this.changeNombreHandler = this.changeNombreHandler.bind(this);
    this.changeCategoriaHandler = this.changeCategoriaHandler.bind(this);
    this.changeAfectoHandler = this.changeAfectoHandler.bind(this);
    this.saveProveedor = this.saveProveedor.bind(this);
  }

  saveProveedor = async (e) => {
    e.preventDefault();
    let proveedor = {
      codigo: this.state.codigo,
      nombre: this.state.nombre,
      categoria: this.state.categoria,
      afecto: this.state.afecto
    };

    try {
      await ProveedorService.crearProveedor(proveedor);
      window.location.href = "/crearProveedor";
    } catch (error) {
      console.log(error);
    }
  };

  changeCodigoHandler = (event) => {
    this.setState({ codigo: event.target.value });
  };

  changeNombreHandler = (event) => {
    this.setState({ nombre: event.target.value });
  };

  changeCategoriaHandler = (event) => {
    this.setState({ categoria: event.target.value });
  };

  changeAfectoHandler = (event) => {
    this.setState({ afecto: event.target.value });
  };

  render() {
    return (
      <form>
        <div className="relative mx-auto w-1/2 bg-gray-500 dark:bg-gray-500 rounded-xl border-gray-200">
          <div className="m-7">
            <div className="relative z-0 w-full mt-7 mb-6 group pt-5 pb-2.5">
              <input
                type="text"
                value={this.state.codigo}
                onChange={this.changeCodigoHandler}
                className="block mt-5 py-0 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                placeholder=" "
                required
              />
              <label
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-3 scale-75 top-8 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-3"
              >
                Codigo
              </label>
            </div>
            <div className="relative z-0 w-full mb-6 group py-2.5">
              <input
                type="text"
                value={this.state.nombre}
                onChange={this.changeNombreHandler}
                className="block py-0 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                placeholder=" "
                required
              />
              <label
                htmlFor="nombre"
                className="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-3 scale-75 top-1 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-3"
              >
                Nombre
              </label>
            </div>
          </div>
          <div className ="relative ml-6">
            <div className="relative z-0 w-1/2 mb-6 group">
              <label
                htmlFor="categoria"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Selecciona una categoria
              </label>
              <select
                id="categoria"
                name="categoria"
                value={this.state.categoria}
                onChange={this.changeCategoriaHandler}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              >
                <option value="">Elige una categoria</option>
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
              </select>
            </div>
            <div className="relative z-0 w-1/2 mb-6 group">
              <label
                htmlFor="afecto"
                className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
              >
                Afecto a retención
              </label>
              <select
                id="afecto"
                name="afecto"
                value={this.state.afecto}
                onChange={this.changeAfectoHandler}
                className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              >
                <option value="">Elige si tiene afecto</option>
                <option value="Si">Si</option>
                <option value="No">No</option>
              </select>
            </div>
            <button
              className="boton text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 mb-7 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              onClick={this.saveProveedor}
            >
              Guardar Proveedor
            </button>
          </div>
        </div>
      </form>
    );
  }
}

export default CrearProveedorComponent;