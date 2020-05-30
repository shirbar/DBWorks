package com.myapp.fieldsbs;

import java.security.Key;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class AddressHelper {

    Dictionary<String, ArrayList<String>> dictionary;

    public void setValues(){
        //making an arrayList for each neightborhood
        ArrayList<String> neigh_Alef = new ArrayList<>();
        ArrayList<String> neigh_Bet = new ArrayList<>();
        ArrayList<String> neigh_Gimmel = new ArrayList<>();
        ArrayList<String> neigh_Dalet = new ArrayList<>();
        ArrayList<String> neigh_Hei = new ArrayList<>();
        ArrayList<String> neigh_Vav = new ArrayList<>();
        ArrayList<String> neigh_Tet = new ArrayList<>();
        ArrayList<String> neigh_Yud_Alef = new ArrayList<>();
        ArrayList<String> Hatserim = new ArrayList<>();
        ArrayList<String> Neot_Ilan = new ArrayList<>();
        ArrayList<String> Neot_Lon = new ArrayList<>(); //near neigh_I 'ט
        ArrayList<String> Neve_Zehev = new ArrayList<>();
        ArrayList<String> Neve_Menachem = new ArrayList<>(); //it's the same as Nachal_Ashan
        ArrayList<String> Neva_Noy = new ArrayList<>();
        ArrayList<String> Nahal_Beka = new ArrayList<>();
        ArrayList<String> Sigaliot = new ArrayList<>();
        ArrayList<String> Old_City = new ArrayList<>();
        ArrayList<String> Kiryat_Yehudit = new ArrayList<>();
        ArrayList<String> Ramot = new ArrayList<>();

        //adding each Array the names inside the Fields neighborhood.
        neigh_Alef.add("א");
        neigh_Alef.add("א'");
        neigh_Alef.add("שכונה א'");
        neigh_Alef.add("ב'");

        neigh_Bet.add("ב'");
        neigh_Bet.add("א");
        neigh_Bet.add("א'");
        neigh_Bet.add("שכונה א'");

        neigh_Gimmel.add("ג");
        neigh_Gimmel.add("ג'");
        neigh_Gimmel.add("שכונה ג'");

        neigh_Dalet.add("ד'");
        neigh_Dalet.add("שכ' ד'");

        neigh_Hei.add("ה'");
        neigh_Hei.add("ה' הישנה");

        neigh_Vav.add("ו' החדשה");
        neigh_Vav.add("ו' הישנה");

        neigh_Tet.add("ט'");
        neigh_Tet.add("שכ' ט'");
        neigh_Tet.add("שכונה ט'");

        neigh_Yud_Alef.add("י\"א");
        neigh_Yud_Alef.add("יא");
        neigh_Yud_Alef.add("יי\"א");
        neigh_Yud_Alef.add("יא'");

        Hatserim.add("עיר עתיקה");
        Hatserim.add("רמב\"ם");
        Hatserim.add("ט'");
        Hatserim.add("שכ' ט'");
        Hatserim.add("שכונה ט'");

        Neot_Ilan.add("פלח 7");
        Neot_Ilan.add("נווה נוי");

        Neot_Lon.add("נאות לון");
        Neot_Lon.add("ט'");
        Neot_Lon.add("שכ' ט'");
        Neot_Lon.add("שכונה ט'");

        Neve_Zehev.add("פלח 7");
        Neve_Zehev.add("נווה נוי");

        Neve_Menachem.add("נחל עשן");
        Neve_Menachem.add("י\"א");
        Neve_Menachem.add("יא");
        Neve_Menachem.add("יי\"א");
        Neve_Menachem.add("יא'");

        Neva_Noy.add("נווה נוי");
        Neva_Noy.add("נחל בקע");

        Nahal_Beka.add("נחל בקע");
        Nahal_Beka.add("נווה נוי");

        Sigaliot.add("י\"א");
        Sigaliot.add("יא");
        Sigaliot.add("יי\"א");
        Sigaliot.add("יא'");

        Old_City.add("עיר עתיקה");
        Old_City.add("רמב\"ם");

        Kiryat_Yehudit.add("נווה נוי");

        Ramot.add("רמות");

        //adding all of the array list into the dictionary.
        dictionary = new Hashtable<>();
        dictionary.put("א", neigh_Alef);
        dictionary.put("ב", neigh_Bet);
        dictionary.put("ג", neigh_Gimmel);
        dictionary.put("ד", neigh_Dalet);
        dictionary.put("ה", neigh_Hei);
        dictionary.put("ו", neigh_Vav);
        dictionary.put("חצרים", Hatserim);
        dictionary.put("ט", neigh_Tet);
        dictionary.put("יא", neigh_Yud_Alef);
        dictionary.put("נאות אילן", Neot_Ilan);
        dictionary.put("נאות לון", Neot_Lon);
        dictionary.put("נווה זאב", Neve_Zehev);
        dictionary.put("נווה מנחם", Neve_Menachem);
        dictionary.put("נווה נוי", Neva_Noy);
        dictionary.put("נחל בקע", Nahal_Beka);
        dictionary.put("סיגליות", Sigaliot);
        dictionary.put("עיר עתיקה", Old_City);
        dictionary.put("קרית יהודית", Kiryat_Yehudit);
        dictionary.put("רמות", Ramot);
    }

    ArrayList<String> getCloseNeighborhood(String neigh){
        ArrayList<String> neighList;
        neighList = dictionary.get(neigh);
        return neighList;
    }
}
