package laborai.ManoLab;


import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;

public class SoldierCallout {
    public static SetADT<String> automobiliuMarkes(Soldier[] soldier) {
        SetADT<Soldier> uni = new BstSetKTU<>(Soldier.byName);
        SetADT<String> kart = new BstSetKTU<>();
        for (Soldier a : soldier) {
            int sizeBefore = uni.size();
            uni.add(a);

            if (sizeBefore == uni.size()) {
                kart.add(a.getName());
            }
        }
        return kart;
    }
}
