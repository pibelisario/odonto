package br.caixa.odonto.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {

    SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");

    public String formatarData(Date date) {
        return formatter.format(date);
    }

}
