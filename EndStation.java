public class EndStation extends Station {
    
    public EndStation (String line, String name) {
        super(line, name);
    }

    public void makeEnd() {
        if (this.next != null && this.prev == null) {
            this.prev = this.next;
        }

        if (this.prev != null && this.next == null) {
            this.next = this.prev;
        }
    }

    public String toString () {
        String result = "ENDSTATION " + name + ": " + line + " line, in service: " + inService;

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

        return result;
    }
}
