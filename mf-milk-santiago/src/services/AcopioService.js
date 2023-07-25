import axios from "axios";

const API_URL = "http://localhost:8080/acopios";

class AcopioService{
    
    SubirAcopio(file){
        return axios.post(API_URL, file);
    }
}

export default new AcopioService()