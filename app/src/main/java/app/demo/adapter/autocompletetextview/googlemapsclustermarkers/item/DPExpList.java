package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mehmetcolak on 15/03/16.
 */
public class DPExpList {

    public static HashMap<String, List<String>> getInfo()
    {
                HashMap<String, List<String>> CarDetails = new  HashMap<String, List<String>>();
        List<String> Truck_Type = new ArrayList<String>();
        Truck_Type.add("34 TK 0001");
        Truck_Type.add("34 TK 0002");
        Truck_Type.add("34 TK 0003");
        Truck_Type.add("34 TK 0004");
        List<String> TIR_Type = new ArrayList<String>();
        TIR_Type.add("34 TR 0001");
        TIR_Type.add("34 TR 0002");
        TIR_Type.add("34 TR 0003");
        TIR_Type.add("34 TR 0004");
        List<String> Automobile_Type = new ArrayList<String>();
        Automobile_Type.add("34 AU 0001");
        Automobile_Type.add("34 AU 0002");
        Automobile_Type.add("34 AU 0003");
        Automobile_Type.add("34 AU 0004");
        List<String> Motorcycle_Type = new ArrayList<String>();
        Motorcycle_Type.add("34 MC 0001");
        Motorcycle_Type.add("34 MC 0002");
        Motorcycle_Type.add("34 MC 0003");
        Motorcycle_Type.add("34 MC 0004");

        CarDetails.put("Trucks",Truck_Type);
        CarDetails.put("TIR",TIR_Type);
        CarDetails.put("Automobiles",Automobile_Type);
        CarDetails.put("Motorcycles",Motorcycle_Type);

        return CarDetails;


    }
}
