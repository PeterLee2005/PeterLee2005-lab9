import java.util.ArrayList;

public class TransferStation extends Station {
    public ArrayList<Station> otherStations;

    public TransferStation (String line, String name) {
        super(line, name);
        this.otherStations = new ArrayList<>();
    }

    public void addTransferStationPrev(Station s) {
        s.next = this;
        otherStations.add(s);
    }
    
    public void addTransferStationNext(Station s) {
        s.prev = this;
        otherStations.add(s);
    }    

    public String toString() {
        String result = "TRANSFERSTATION " + name + ": " + line + " line, in service: " + inService;

        if (prev != null) {
            result += ", previous station: " + prev.name;
        }
        else {
            result += ", previous station: none";
        }

        if (next != null) {
            result += ", next station: " + next.name;
        }
        else {
            result += ", next station: none";
        }

        result += "\n\tTransfers: \n";

        for (Station s : otherStations) {
            result += "\t" + s.toString() + "\n";
        }

        return result;
    }
}
