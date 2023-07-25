import React, { Component } from 'react'
import PlanillaService from '../services/PlanillaService'

class ListarPlanillaComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            planillas: []
        }
    }

    componentDidMount(){
        PlanillaService.getPlanillas().then((res) => {
            this.setState({ planillas: res.data});
        });
    }

    render() {
        return (
            <div className="relative overflow-x-auto">
                <table className="relative mx-auto mt-7 text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="font-inter text-xs text-center text-white bg-indigo-950 dark:bg-indigo-950 dark:text-white">
                    <tr>
                        <th scope="col" className="px-1 py-1">ID</th>
                        <th scope="col" className="px-1 py-1">Quincena</th>
                        <th scope="col" className="px-1 py-1">Codigo</th>
                        <th scope="col" className="px-1 py-1">Nombre</th>
                        <th scope="col" className="px-1 py-1">Total Leche (kg)</th>
                        <th scope="col" className="px-1 py-1">Dias que envio leche</th>
                        <th scope="col" className="px-1 py-1">Promedio Leche (kg)</th>
                        <th scope="col" className="px-1 py-1">Variacion Leche (%)</th>
                        <th scope="col" className="px-1 py-1">Grasas (%)</th>
                        <th scope="col" className="px-1 py-1">Variacion Grasas (%)</th>
                        <th scope="col" className="px-1 py-1">Solidos Totales (%)</th>
                        <th scope="col" className="px-1 py-1">Variacion ST (%)</th>
                        <th scope="col" className="px-1 py-1">Pago Leche ($)</th>
                        <th scope="col" className="px-1 py-1">Pago Grasas ($)</th>
                        <th scope="col" className="px-1 py-1">Pago ST ($)</th>
                        <th scope="col" className="px-1 py-1">Bono Frecuencia ($)</th>
                        <th scope="col" className="px-1 py-1">Dcto x Var. Leche (%)</th>
                        <th scope="col" className="px-1 py-1">Dcto x Var. Grasas (%)</th>
                        <th scope="col" className="px-1 py-1">Dcto x Var. ST (%)</th>
                        <th scope="col" className="px-1 py-1">Pago total ($)</th>
                        <th scope="col" className="px-1 py-1">Retencion (%)</th>
                        <th scope="col" className="px-1 py-1">Pago Final ($)</th>

                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.planillas.map(
                            planilla =>
                            <tr className="font-inter font-medium text-xs text-center bg-gray-500 dark:bg-gray-500 text-black dark:text-black rounded-bl-xl rounded-br-xl">
                                <td className="px-1 py-2" >{planilla.ID}</td>
                                <td className="px-1 py-2" >{planilla.quincena}</td>
                                <td className="px-1 py-2" >{planilla.codigoProv}</td>
                                <td className="px-1 py-2" >{planilla.nombreProv}</td>
                                <td className="px-1 py-2" >{planilla.totalKlsLeche}</td>
                                <td className="px-1 py-2" >{planilla.diasEnvioLeche}</td>
                                <td className="px-1 py-2" >{planilla.promKlsLeche}</td>
                                <td className="px-1 py-2" >{planilla.variacionLeche}</td>
                                <td className="px-1 py-2" >{planilla.porcentajeGrasas}</td>
                                <td className="px-1 py-2" >{planilla.variacionGrasas}</td>
                                <td className="px-1 py-2" >{planilla.solidosTotales}</td>
                                <td className="px-1 py-2" >{planilla.variacionST}</td>
                                <td className="px-1 py-2" >{planilla.pagoLeche}</td>
                                <td className="px-1 py-2" >{planilla.pagoGrasa}</td>
                                <td className="px-1 py-2" >{planilla.pagoST}</td>
                                <td className="px-1 py-2" >{planilla.bonoFrecuencia}</td>
                                <td className="px-1 py-2" >{planilla.dctoVarLeche}</td>
                                <td className="px-1 py-2" >{planilla.dctoVarGrasa}</td>
                                <td className="px-1 py-2" >{planilla.dctoVarST}</td>
                                <td className="px-1 py-2" >{planilla.pagoTotal}</td>
                                <td className="px-1 py-2" >{planilla.montoRetencion}</td>
                                <td className="px-1 py-2" >{planilla.montoFinal}</td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default ListarPlanillaComponent;