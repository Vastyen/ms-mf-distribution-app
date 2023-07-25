import React, { Component } from 'react'
import PlanillaService from '../services/PlanillaService'

class CrearPlanillaComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
          };
          this.savePlanilla = this.savePlanilla.bind(this);
    }

    savePlanilla = async (e) => {
        e.preventDefault();
        try {
          await PlanillaService.crearPlanilla();
          window.location.href = "/crearPlanilla";
        } catch (error) {
          console.log(error);
        }
      };

    render() {
        return (
          <main>
            <div className="relative mx-auto mt-7 w-1/2 bg-gray-500 dark:bg-gray-500 rounded-xl border-gray-200">
              <label className="block text-center pt-2 pb-5 text-sm font-bold text-gray-800 dark:text-gray-800">
                Calcular la planilla de pago
              </label>
              <form>
                <div className="pb-5">
                  <button
                    type="submit"
                    value="Cargar el archivo a la Base de Datos"
                    className="boton block mx-auto text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
                    onClick={this.savePlanilla}>
                    Calcular
                  </button>
                </div>
              </form>
            </div>
          </main>
        );
      }
}

export default CrearPlanillaComponent