package com.ceiba.dominio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Calendar;
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
