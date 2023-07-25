import React, { Component } from 'react'
import ProveedorService from '../services/ProveedorService'

class ListarProveedorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            proveedores: []
        }
    }

    componentDidMount(){
        ProveedorService.getProveedores().then((res) => {
            this.setState({ proveedores: res.data});
        });
    }

    render() {
        return (


            <div className="tabla">
                <table className=" mx-auto mt-7 text-sm text-left text-gray-500 dark:text-gray-400">
                    <thead className="font-inter text-lg text-center text-white bg-indigo-950 dark:bg-indigo-950 dark:text-white">
                    <tr>
                        <th scope="col" className="px-6 py-3">Código</th>
                        <th scope="col" className="px-6 py-3">Nombre</th>
                        <th scope="col" className="px-6 py-3">Categoría</th>
                        <th scope="col" className="px-6 py-3">Retención</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.proveedores.map(
                            proveedor => 
                            <tr className="font-inter font-medium text-lg text-center bg-gray-500 dark:bg-gray-500 text-black dark:text-black rounded-bl-xl rounded-br-xl">
                                    <td className="px-6 py-4"> {proveedor.codigo} </td> 
                                    <td className="px-6 py-4"> {proveedor.nombre} </td>   
                                    <td className="px-6 py-4"> {proveedor.categoria}</td>
                                    <td className="px-6 py-4"> {proveedor.afecto}</td>
                            </tr>
                        )
                    }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default ListarProveedorComponent;