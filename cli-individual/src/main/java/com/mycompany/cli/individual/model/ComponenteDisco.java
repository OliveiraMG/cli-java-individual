package com.mycompany.cli.individual.model;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class ComponenteDisco {
    private Looca looca = new Looca();

    public String getNomeDisco() {
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
        return discos.get(0).getNome();
    }

    public Long getTotal() {
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
        return discos.get(0).getTamanho();
    }

    public Double getUsoDisco() {
        File disk = new File("/");
        Long totalSpace = disk.getTotalSpace();
        Long usedSpace = totalSpace - disk.getFreeSpace();

        Double usedSpaceGb = (double) usedSpace / (1024 * 1024 * 1024);

        Double value = usedSpaceGb;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String formattedValue = df.format(value);
        Double number = Double.parseDouble(formattedValue.replace("," , "."));

        return number;
    }

    public Long getTempoTransferencia() {
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
        return discos.get(0).getTempoDeTransferencia();
    }
}

