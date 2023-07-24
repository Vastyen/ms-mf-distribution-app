package com.milkstgo.grasas.Services;

import com.milkstgo.grasas.Entities.GrasasEntity;
import com.milkstgo.grasas.Repositories.GrasasRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class GrasasService {

    @Autowired
    private GrasasRepository dataRepository;

    private final Logger logg = LoggerFactory.getLogger(GrasasService.class);

    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public GrasasEntity leerArchivo(String direccion){
        String texto = "";
        BufferedReader bf = null;
        dataRepository.deleteAll();
        GrasasEntity nuevaGrasas = new GrasasEntity();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    nuevaGrasas = crearGrasas(bfRead.split(";")[0], Integer.parseInt(bfRead.split(";")[1]), Integer.parseInt(bfRead.split(";")[2]));
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;

            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
        return nuevaGrasas;
    }

    public GrasasEntity crearGrasas(String proveedor, Integer grasas, Integer solidoTotal){
        GrasasEntity nuevaGrasa = new GrasasEntity();
        nuevaGrasa.setProveedor(proveedor);
        nuevaGrasa.setGrasas(grasas);
        nuevaGrasa.setSolidoTotal(solidoTotal);
        return dataRepository.save(nuevaGrasa);
    }

    public ArrayList<GrasasEntity> verGrasas(){
        return dataRepository.findAll();
    }

    public GrasasEntity buscarPorProveedor(String codigo){return dataRepository.findByProveedor(codigo);}

    public void eliminarData(){
        dataRepository.deleteAll();
    }


}
