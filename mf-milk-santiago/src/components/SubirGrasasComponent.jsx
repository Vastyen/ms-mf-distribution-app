import React, { Component } from "react";
import GrasasService from "../services/GrasasService";


class SubirGrasasComponent extends Component{
  constructor(props) {
    super(props);
    this.state = {
      file: null,
    };
    this.onFileChange = this.onFileChange.bind(this);
  }

  onFileChange(event) {
    this.setState({ file: event.target.files[0] });
  }
  
  onFileUpload = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("file", this.state.file);
    try {
        GrasasService.SubirGrasas(formData);
        window.location.href = "/subirGrasas";
    } catch (error) {
        console.log(error);
    }
      
  };

  render() {
    return (
      <main>
        <div className="relative mx-auto mt-7 w-1/2 bg-gray-500 dark:bg-gray-500 rounded-xl border-gray-200">
          <label className="block text-center pt-2 text-sm font-bold text-gray-800 dark:text-gray-800">
            Cargar el archivo de datos para Grasas y Solidos Totales
          </label>
          <form encType="multipart/form-data">
            <br />
            <input
              className="block mx-auto w-3/4 text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400"
              type="file"
              id="file_input"
              name="file"
              accept=".csv"
              onChange={this.onFileChange}
            />
            <br />
            <button
              type="button"
              onClick={this.onFileUpload}
              className=" boton block mx-auto text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Cargar el archivo a la Base de Datos
            </button>
          </form>
          <br />
        </div>
      </main>
    );
  }
}

export default SubirGrasasComponent;