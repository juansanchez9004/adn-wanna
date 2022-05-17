package com.ceiba.dominio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DescomponerFecha {

    private DescomponerFecha() {}

    public static Integer porDia(Date fechaADescomponer) {
        return convertirDateALocalDate(fechaADescomponer).getDayOfMonth();
    }

    public static LocalDate convertirDateALocalDate(Date fechaAConvertir) {
        return LocalDate.ofInstant(fechaAConvertir.toInstant(), ZoneId.systemDefault());
    }

    public static Integer porDiaDeSemana(Date fechaADescomponer) {
        return convertirDateALocalDate(fechaADescomponer).getDayOfWeek().getValue();
    }
}
