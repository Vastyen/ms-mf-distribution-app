import axios from "axios";

const API_URL = "http://grasas:8080/grasas";

class GrasasService{
    
    SubirGrasas(file){
        return axios.post(API_URL, file);
    }
}

export default new GrasasService()