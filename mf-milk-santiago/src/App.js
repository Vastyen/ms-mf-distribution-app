import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HeaderComponent from './components/HeaderComponent';
import ListarProveedorComponent from './components/ListarProveedorComponent';
import CrearProveedorComponent from './components/CrearProveedorComponent';
import SubirAcopioComponent from './components/SubirAcopioComponent';
import SubirGrasasComponent from './components/SubirGrasasComponent';
import CrearPlanillaComponent from './components/CrearPlanillaComponent';
import ListarPlanillaComponent from './components/ListarPlanillaComponent';

function App() {
  return (
    <div>
        <BrowserRouter>
          <HeaderComponent />
          <div>
            <Routes> 
              <Route path = "/listarProveedor" element = {<ListarProveedorComponent />}/>
              <Route path = "/crearProveedor" element = {<CrearProveedorComponent />}/>
              <Route path = "/subirAcopio" element = {<SubirAcopioComponent />}/>
              <Route path = "/subirGrasas" element = {<SubirGrasasComponent />}/>
              <Route path = "/crearPlanilla" element = {<CrearPlanillaComponent />}/>
              <Route path = "/listarPlanilla" element = {<ListarPlanillaComponent />}/>
            </Routes>
          </div>
        </BrowserRouter>
    </div>
  );
}

export default App;
