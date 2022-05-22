package com.ceiba.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DescomponerFecha {

    private DescomponerFecha() {}

    public static Integer porDia(LocalDate fechaADescomponer) {
        return fechaADescomponer.getDayOfMonth();
    }

    public static Integer porDiaDeSemana(LocalDate fechaADescomponer) {
        return fechaADescomponer.getDayOfWeek().getValue();
    }
}
