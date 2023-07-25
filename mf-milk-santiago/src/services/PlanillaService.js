import axios from 'axios';

const PROVEEDOR_API_URL = "http://localhost:8080/planillaPago";

class ProveedorService {

    getPlanillas(){
        return axios.get(PROVEEDOR_API_URL);
    }

    crearPlanilla(){
        return axios.post(PROVEEDOR_API_URL);
    }
}

export default new ProveedorService()