package com.milkstgo.planillaPago.Services;

import com.milkstgo.planillaPago.Entities.PlanillaPagoEntity;
import com.milkstgo.planillaPago.Models.Acopio;
import com.milkstgo.planillaPago.Models.Grasas;
import com.milkstgo.planillaPago.Models.Proveedor;
import com.milkstgo.planillaPago.Repositories.PlanillaPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.*;

@Service
public class PlanillaPagoService {

    @Autowired
    PlanillaPagoRepository planillaPagoRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<PlanillaPagoEntity> crearPlanillaPago(){
        List<String> codigoProveedor = getcodigos();
        List<PlanillaPagoEntity> planillas = new ArrayList<>();

        if (codigoProveedor.isEmpty()){
            return planillas;
        }

        else {
            for (String codigo: codigoProveedor){
                planillas.add(planillaPagoRepository.save(calcularPlanilla(codigo)));
            }
        }

        return planillas;
    }

    public PlanillaPagoEntity calcularPlanilla(String codigoProveedor) {

        PlanillaPagoEntity planillaPagoEntity = new PlanillaPagoEntity();
        PlanillaPagoEntity quincenaAnt = new PlanillaPagoEntity();
        List<PlanillaPagoEntity> quincenasProveedor = traerPlanillaDeProv(codigoProveedor);


        Proveedor proveedor = traerProveedor(codigoProveedor);
        System.out.println(proveedor);
        Grasas grasas = traerGrasasPorProveedor(codigoProveedor);
        System.out.println(grasas);
        List<Acopio> acopios = traerAcopiosPorProveedor(codigoProveedor);
        System.out.println(acopios);

        if ((grasas != null) && (!acopios.isEmpty())) {

            if (!quincenasProveedor.isEmpty()) {
                quincenaAnt = quincenasProveedor.get(quincenasProveedor.size() - 1);
            }

            List<LocalDate> fechas = stringToFecha(acopios);

            System.out.println(fechas);
            planillaPagoEntity.setQuincena(calcQuincena(fechas));
            planillaPagoEntity.setCodigoProv(codigoProveedor);
            planillaPagoEntity.setNombreProv(proveedor.getNombre());
            planillaPagoEntity.setTotalKlsLeche(totalKlsLeche(acopios));
            planillaPagoEntity.setDiasEnvioLeche(diasEnvio(fechas));
            planillaPagoEntity.setPromKlsLeche(promedioKlsLeche(acopios));
            planillaPagoEntity.setVariacionLeche(variacionLeche(planillaPagoEntity.getTotalKlsLeche(), quincenaAnt));
            planillaPagoEntity.setPorcentajeGrasas(grasas.getGrasas());
            planillaPagoEntity.setVariacionGrasas(variacionGrasas(planillaPagoEntity.getPorcentajeGrasas(), quincenaAnt));
            planillaPagoEntity.setSolidosTotales(grasas.getSolidoTotal());
            planillaPagoEntity.setVariacionST(variacionST(planillaPagoEntity.getSolidosTotales(), quincenaAnt));
            planillaPagoEntity.setPagoLeche(pagoPorLeche(proveedor.getCategoria(), planillaPagoEntity.getTotalKlsLeche()));
            planillaPagoEntity.setPagoGrasa(pagoPorGrasa(planillaPagoEntity.getPorcentajeGrasas(), planillaPagoEntity.getTotalKlsLeche()));
            planillaPagoEntity.setPagoST(pagoPorST(planillaPagoEntity.getSolidosTotales(), planillaPagoEntity.getTotalKlsLeche()));
            planillaPagoEntity.setBonoFrecuencia(bonificacion(turnosAcopio(acopios), planillaPagoEntity));
            planillaPagoEntity.setDctoVarLeche(descuentoPorLeche(planillaPagoEntity.getVariacionLeche()));
            planillaPagoEntity.setDctoVarGrasa(descuentoPorGrasa(planillaPagoEntity.getVariacionGrasas()));
            planillaPagoEntity.setDctoVarST(descuentoPorST(planillaPagoEntity.getVariacionST()));
            planillaPagoEntity.setPagoTotal(calcPagoTotal(planillaPagoEntity));
            planillaPagoEntity.setMontoRetencion(calcRetencion(planillaPagoEntity, proveedor));
            planillaPagoEntity.setMontoFinal(pagoFinal(planillaPagoEntity));

        }
        return planillaPagoEntity;
    }

    public List<PlanillaPagoEntity> traerPlanillaDeProv(String codigoProveedor){
        return planillaPagoRepository.findByCodigo(codigoProveedor);
    }

    public LocalDate calcQuincena(List<LocalDate> fechas) {
        int quincena;
        int month;
        int year;
        if (!fechas.isEmpty()) {
            if (fechas.get(0).getDayOfMonth() < 15) {
                quincena = 1;
            } else {
                quincena = 2;
            }
            month = fechas.get(0).getMonthValue();
            year = fechas.get(0).getYear();
        }
        else {
            return null;
        }
        return LocalDate.of(year, month, quincena);
    }

    public double promedioKlsLeche(List<Acopio> acopios){

        double prom = 0;
        for(Acopio acopio : acopios){
            prom = prom + acopio.getKgleche();
        }

        return Math.round(prom / acopios.size());
    }

    public int totalKlsLeche(List<Acopio> acopios) {
        int totalKls = 0;
        for (Acopio acopio : acopios) {
            totalKls = totalKls + acopio.getKgleche();
        }
        return totalKls;
    }

    public double variacionLeche( Integer totalKls, PlanillaPagoEntity quincenaAnt) {

        if (quincenaAnt.getTotalKlsLeche() == null){
            return 0;
        }
        else{
            return (Math.round( ((double)(totalKls - quincenaAnt.getTotalKlsLeche()) / (double) quincenaAnt.getTotalKlsLeche()) * 10000))/100.0;
        }

    }

    public double variacionGrasas( double grasas, PlanillaPagoEntity quincenaAnt) {

        if (quincenaAnt.getPorcentajeGrasas() == null){
            return 0;
        }

        else{
            return (Math.round((grasas - quincenaAnt.getPorcentajeGrasas()) / quincenaAnt.getPorcentajeGrasas() * 10000))/100.0;
        }
    }

    public double variacionST( double solidos, PlanillaPagoEntity quincenaAnt) {

        if (quincenaAnt.getSolidosTotales() == null){
            return 0;
        }
        else {
            return (Math.round((solidos - quincenaAnt.getSolidosTotales()) / quincenaAnt.getSolidosTotales() * 10000)) / 100.0;
        }
    }

    public int pagoPorLeche(String categoria, int totalKlsLeche) {
        int pago = 0;
        switch (categoria) {
            case "A":
                pago = totalKlsLeche * 700;
                break;
            case "B":
                pago = totalKlsLeche * 550;
                break;
            case "C":
                pago = totalKlsLeche * 400;
                break;
            case "D":
                pago = totalKlsLeche * 250;
                break;
            default:
                return pago;
        }
        return pago;
    }

    public int pagoPorGrasa(int grasas, int totalKlsLeche) {
        int pago = 0;
        if (grasas < 0){
            return pago;
        }
        else if (grasas <= 20) {
            pago = totalKlsLeche * 30;
        }
        else if (grasas <= 45) {
            pago = totalKlsLeche * 80;
        }
        else{
            pago = totalKlsLeche * 120;
        }
        return pago;
    }

    public int pagoPorST(int solidosTotales, int totalKlsLeche) {

        int pago = 0;

        if (solidosTotales < 0){
            return pago;
        }
        else if (solidosTotales <= 7) {
            pago = totalKlsLeche * -130;
        }
        else if (solidosTotales <= 18) {
            pago = totalKlsLeche * -90;
        }
        else if (solidosTotales <= 35) {
            pago = totalKlsLeche * 95;
        }
        else{
            pago = totalKlsLeche * 150;
        }
        return pago;
    }

    public List<String> turnosAcopio(List<Acopio> acopios){

        ArrayList<String> turnos = new ArrayList<>();
        for (Acopio acopio : acopios){
            turnos.add(acopio.getTurno());
        }
        return turnos;
    }

    public double bonificacion(List<String> turnos, PlanillaPagoEntity planillaPagoEntity){

        double bono = 0;
        int manana = Collections.frequency(turnos, "M");
        int tarde = Collections.frequency(turnos, "T");

        if ((manana > 10) && (tarde > 10)){
            bono = 0.2;
        }
        else if(manana > 10){
            bono = 0.12;
        }
        else if(tarde > 10){
            bono = 0.08;
        }
        return (planillaPagoEntity.getPagoST() + planillaPagoEntity.getPagoGrasa() + planillaPagoEntity.getPagoLeche()) * bono;
    }

    public int descuentoPorLeche(double varLeche) {
        int dcto = 0;

        if (varLeche <= -9 && varLeche >= -25) {
            dcto = 7;
        }
        else if(varLeche <= -26 && varLeche >= -45) {
            dcto = 15;
        }
        else if(varLeche <= -46) {
            dcto = 30;
        }
        return dcto;
    }

    public int descuentoPorGrasa(double varGrasa) {
        int dcto = 0;
        if (varGrasa <= -16 && varGrasa >= -25) {
            dcto = 12;
        }
        else if(varGrasa <= -26 && varGrasa >= -40) {
            dcto = 20;
        }
        else if(varGrasa <= -41) {
            dcto = 30;
        }
        return dcto;
    }

    public int descuentoPorST(double varST) {
        int dcto = 0;
        if (varST <= -7 && varST >= -12) {
            dcto = 18;
        }
        else if(varST <= -13 && varST >= -35) {
            dcto = 27;
        }
        else if(varST <= -36) {
            dcto = 45;
        }
        return dcto;
    }

    public int calcPagoTotal(PlanillaPagoEntity planillaPagoEntity){

        double pagoTotal;
        double descuentos;

        pagoTotal = planillaPagoEntity.getPagoLeche() + planillaPagoEntity.getPagoGrasa() + planillaPagoEntity.getPagoST() + planillaPagoEntity.getBonoFrecuencia();
        descuentos = (planillaPagoEntity.getDctoVarLeche() + planillaPagoEntity.getDctoVarGrasa() + planillaPagoEntity.getDctoVarST())/ 100.0;

        return ((int) (pagoTotal - (pagoTotal * descuentos)));
    }

    public int calcRetencion(PlanillaPagoEntity planillaPagoEntity, Proveedor proveedor){

        double retencion = 0.0;

        if ((proveedor.getAfecto().equals("Si")) && (planillaPagoEntity.getPagoTotal() > 950000)){
            retencion = 0.13;
        }

        return (int) (planillaPagoEntity.getPagoTotal() * retencion);
    }

    public int pagoFinal(PlanillaPagoEntity planillaPagoEntity){
        return (planillaPagoEntity.getPagoTotal() - planillaPagoEntity.getMontoRetencion());
    }

    public List<LocalDate> stringToFecha(List<Acopio> acopios) {
        List<LocalDate> fechas = new ArrayList<>();
        for (Acopio acopio : acopios) {
            fechas.add(acopio.getFecha());
        }
        return fechas;
    }

    public int diasEnvio(List<LocalDate> fechas) {

        Set<LocalDate> set = new LinkedHashSet<>(fechas);
        List<LocalDate> fechasSinRepetir = new ArrayList<>(set);
        return fechasSinRepetir.size();
    }

    public List<PlanillaPagoEntity> traerPlanilla(){
        return planillaPagoRepository.findAll();
    }

    public void borrarPlanillas(){planillaPagoRepository.deleteAll();}

    public List<String> getcodigos() {
        return restTemplate.getForObject("http://localhost:8080/proveedores/codigos", List.class);
    }

    public Proveedor traerProveedor(String id) {
        return restTemplate.getForObject("http://localhost:8080/proveedores/" + id, Proveedor.class);
    }

    public Grasas traerGrasasPorProveedor(String id) {
        return restTemplate.getForObject("http://localhost:8080/grasas/" + id, Grasas.class);
    }

    public List<Acopio> traerAcopiosPorProveedor(String id) {
        ResponseEntity<List<Acopio>> response = restTemplate.exchange(
                "http://localhost:8080/acopios/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Acopio>>() {}
        );
        return response.getBody();
    }
}

