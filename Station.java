import java.util.ArrayList;

public class Station {
    protected String line;
    protected String name;
    protected boolean inService;
    protected Station prev;
    protected Station next;

    public Station (String line, String name) {
        this.line = line;
        this.name = name;
        this.inService = true;
        this.prev = null;
        this.next = null;
    }

    public void switchAvailable() {
        this.inService = !this.inService;
    }

    public boolean isAvailable() {
        return inService;
    }

    public void addNext (Station s) {
        this.next = s;
        if (s.prev == null) {
            s.prev = this;
        }
    }

    public void addPrev (Station s) {
        this.prev = s;
        if (s.next == null) {
            s.next = this;
        }
    }

    public void connect (Station s) {
        this.addNext(s);
        s.addPrev(this);
    }

    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Station)) {
            return false;
        }

        Station other = (Station) o;
        return this.name.equals(other.name) && this.line.equals(other.line);
    }

    public String toString() {
        String result = "STATION " + name + ": " + line + " line, in service: " + inService;

        if (prev != null) {
            result += ", previous station: " + prev.name;
        } else {
            result += ", previous station: none";
        }

        if (next != null) {
            result += ", next station: " + next.name;
        } else {
            result += ", next station: none";
        }

        return result;
    }

    public int tripLength (Station dest) {
        ArrayList<Station> visited = new ArrayList<>();
        return tripLengthHelper(dest, visited);
    }

    private int tripLengthHelper (Station dest, ArrayList<Station> visited) {
        if (this.equals(dest)) {
            return 0;
        }

        if (visited.contains(this)) {
            return -1;
        }

        visited.add(this);

        if (this.next != null) {
            int steps = this.next.tripLengthHelper(dest, visited);
            if (steps != -1) {
                return 1 + steps;
            }
        }

        if (this instanceof TransferStation) {
            TransferStation ts = (TransferStation) this;
            for (Station s : ts.otherStations) {
                int steps = s.tripLengthHelper(dest, visited);
                if (steps != -1) {
                    return 1 + steps;
                }
            }
        }

        return -1;
    }
}
